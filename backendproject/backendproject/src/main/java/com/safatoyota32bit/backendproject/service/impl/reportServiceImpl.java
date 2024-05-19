package com.safatoyota32bit.backendproject.service.impl;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.properties.TextAlignment;
import com.lowagie.text.PageSize;
import com.safatoyota32bit.backendproject.dto.*;
import com.safatoyota32bit.backendproject.entity.sale;
import com.safatoyota32bit.backendproject.entity.total;
import com.safatoyota32bit.backendproject.repo.*;
import com.safatoyota32bit.backendproject.service.reportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.kernel.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Paragraph;






@Service
@RequiredArgsConstructor
public class reportServiceImpl implements reportService {

    private final totalRepository TotalRepository;
    private final saleRepository SaleRepository;
    private final productRepository ProductRepository;
    private final userRepository UserRepository;
    private final salesTypeRepository SalesTypeRepository;



    @Override
    public ByteArrayInputStream generateInvoice(List<totalDTO> sales, int totalID) {

        ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(OutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);


        try {
            for (totalDTO sale : sales) {
                if (sale.getID() == totalID) {

                    Paragraph title = new Paragraph("Fatura").setFontSize(18)
                            .setTextAlignment(TextAlignment.CENTER);
                document.add(title);
                document.add(new Paragraph("\n"));

                    Paragraph saleInfo = new Paragraph()
                            .add("Sale ID: "+sale.getID())
                            .add("Date: "+ sale.getDate())
                            .add("Total Price: " + sale.getTotalPrice())
                            .add("Payment: " + sale.getPay())
                            .add("Change: "+ sale.getChange())
                            .add("Cashier: "+ sale.getUserDTO().getName() + " " +sale.getUserDTO().getLastName())
                            .add("Payment Type: "+sale.getSalesTypeDTO().getSaleType())
                            .setTextAlignment(TextAlignment.LEFT);
                    document.add(saleInfo);



                    Table table = new Table(new float[]{3,2,2})
                            .setWidth(100)
                            .setMarginTop(10f)
                            .setMarginBottom(10f);


                    table.addCell(new Cell().add(new Paragraph("Product").setBold()));
                    table.addCell(new Cell().add(new Paragraph("Amount").setBold()));
                    table.addCell(new Cell().add(new Paragraph("Price").setBold()));

                    for (saleDTO item : sale.getSaleDTO()) {

                        table.addCell(item.getProductDTO().getProductName());
                        table.addCell(String.valueOf(item.getCount()));
                        table.addCell(String.valueOf(item.getPriceDTO().getPrice()));
                    }
                document.add(table);
                    break;


                }

            }



        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            pdfDocument.close();
        }



        return new ByteArrayInputStream(OutputStream.toByteArray());

    }


    private totalDTO convertToDTO(total Total) {

        totalDTO TotalDTO = new totalDTO();
        TotalDTO.setID(Total.getID());
        TotalDTO.setDate(Total.getDate());
        TotalDTO.setPay(Total.getPay());
        TotalDTO.setChange(Total.getPay());
        TotalDTO.setTotalPrice(Total.getTotalPrice());

        salesTypeDTO SalesTypeDTO = TotalDTO.getSalesTypeDTO();
        SalesTypeDTO.setSalesTypeID(Total.getSalesType().getSaleTypeID());
        SalesTypeDTO.setSaleType(Total.getSalesType().getSaleType());
        TotalDTO.setSalesTypeDTO(SalesTypeDTO);

        discountDTO DiscountDTO = TotalDTO.getDiscountDTO();
        DiscountDTO.setDiscountID(Total.getDiscount().getDiscountID());
        DiscountDTO.setDiscountName(Total.getDiscount().getDiscountName());
        DiscountDTO.setDiscountPrice(Total.getDiscount().getDiscountPrice());

        userDTO UserDTO = TotalDTO.getUserDTO();
        UserDTO.setUserID(Total.getUser().getUserID());
        UserDTO.setName(Total.getUser().getName());
        UserDTO.setLastName(Total.getUser().getLastName());


        List<saleDTO> saleDTOList = Total.getSale().stream()
                .map(this::convertSaleToDTO)
                .collect(Collectors.toList());
        TotalDTO.setSaleDTO(saleDTOList);

        return TotalDTO;

    }

    private saleDTO convertSaleToDTO(sale Sale) {
        saleDTO SaleDTO = new saleDTO();


        productDTO ProductDTO = new productDTO();
        ProductDTO.setProductID(Sale.getProduct().getProductID());
        ProductDTO.setProductName(Sale.getProduct().getProductName());


        salesTypeDTO SalesTypeDTO = SaleDTO.getSalesTypeDTO();
        SalesTypeDTO.setSalesTypeID(Sale.getSaleType().getSaleTypeID());
        SalesTypeDTO.setSaleType(Sale.getSaleType().getSaleType());

        priceDTO PriceDTO = SaleDTO.getPriceDTO();
        PriceDTO.setPriceID(Sale.getPrice().getPriceID());
        PriceDTO.setDate(Sale.getPrice().getDate());
        PriceDTO.setPrice(Sale.getPrice().getPrice());

        discountDTO DiscountDTO = new discountDTO();
        DiscountDTO.setDiscountID(Sale.getPrice().getDiscount().getDiscountID());
        DiscountDTO.setDiscountPrice(Sale.getPrice().getDiscount().getDiscountPrice());
        DiscountDTO.setDiscountName(Sale.getPrice().getDiscount().getDiscountName());

        PriceDTO.setDiscountDTO(DiscountDTO);
        SaleDTO.setPriceDTO(PriceDTO);
        SaleDTO.setSaleID(Sale.getSaleID());
        SaleDTO.setDate(Sale.getDate());
        SaleDTO.setCount(Sale.getCount());
        SaleDTO.setSalesTypeDTO(SalesTypeDTO);
        SaleDTO.setProductDTO(ProductDTO);
        // Diğer Sale alanlarını da doldurabilirsiniz
        return SaleDTO;
    }




    @Override
    public List<totalDTO> getAllSales() {
        List<total> Total = TotalRepository.findAll();


        return Total.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<totalDTO> getAllSales(Pageable pageable, String filter) {
        Page<total> totals;

        if (filter != null && !filter.isEmpty()) {
            totals = TotalRepository.findByCriteria(filter, pageable);
        } else {
            totals = TotalRepository.findAll(pageable);
        }

        return totals.map(this::convertToDTO);
    }

}


