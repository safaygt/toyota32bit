package com.safatoyota32bit.backendproject.service.impl;


import com.lowagie.text.PageSize;
import com.safatoyota32bit.backendproject.dto.*;
import com.safatoyota32bit.backendproject.entity.sale;
import com.safatoyota32bit.backendproject.entity.total;
import com.safatoyota32bit.backendproject.repo.*;
import com.safatoyota32bit.backendproject.service.reportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class reportServiceImpl implements reportService {

    private final totalRepository TotalRepository;
    private final saleRepository SaleRepository;
    private final productRepository ProductRepository;
    private final userRepository UserRepository;
    private final salesTypeRepository SalesTypeRepository;



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

}


