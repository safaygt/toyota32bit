package com.safatoyota32bit.backendproject.service.impl;

import com.safatoyota32bit.backendproject.dto.discountDTO;
import com.safatoyota32bit.backendproject.dto.productDTO;
import com.safatoyota32bit.backendproject.dto.saleDTO;
import com.safatoyota32bit.backendproject.dto.totalDTO;
import com.safatoyota32bit.backendproject.entity.discount;
import com.safatoyota32bit.backendproject.entity.product;
import com.safatoyota32bit.backendproject.entity.sale;
import com.safatoyota32bit.backendproject.entity.total;
import com.safatoyota32bit.backendproject.repo.discountRepository;
import com.safatoyota32bit.backendproject.repo.productRepository;
import com.safatoyota32bit.backendproject.repo.saleRepository;
import com.safatoyota32bit.backendproject.repo.totalRepository;
import com.safatoyota32bit.backendproject.service.saleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class saleServiceImpl implements saleService {

    private final saleRepository SaleRepository;
    private final totalRepository TotalRepository;
    private final discountRepository DiscountRepository;
    private final productRepository ProductRepository;
    @Override
    public void recordSale(saleDTO SaleDTO) {

        sale Sale = new sale();

        productDTO ProductDTO = SaleDTO.getProductDTO();

        product Product = ProductRepository.findById(ProductDTO.getProductID()).orElseThrow(() -> new RuntimeException("Product not found: " + ProductDTO.getProductName()));

        Sale.setProduct(Product);
        SaleRepository.save(Sale);

    }

    @Override
    public void recordTotal(totalDTO TotalDTO) {

        total Total = new total();
        discountDTO DiscountDTO = TotalDTO.getDiscountDTO();
        discount Discount = DiscountRepository.findById(DiscountDTO.getDiscountID()).orElseThrow(() -> new RuntimeException("Discount not found: " + DiscountDTO.getDiscountName()));
        saleDTO SaleDTO = TotalDTO.getSaleDTO();
        sale Sale = SaleRepository.findById(SaleDTO.getSaleID()).orElseThrow(() -> new RuntimeException("Sale not found"));


        Total.setTotalPrice(TotalDTO.getTotalPrice());
        Total.setChange(TotalDTO.getChange());
        Total.setPay(TotalDTO.getPay());
        Total.setDate(TotalDTO.getDate());
        Total.setDiscount(Discount);
        Total.setSale(Sale);
        TotalRepository.save(Total);

    }

    @Override
    @Transactional
    public List<discountDTO> getAllDiscounts() {
        List<discount> discountList = DiscountRepository.findAll();
        List<discountDTO> discountDTOList = new ArrayList<>();

        discountList.forEach(item -> {

            discountDTO DiscountDTO = new discountDTO();
            DiscountDTO.setDiscountID(item.getDiscountID());
            DiscountDTO.setDiscountName(item.getDiscountName());
            DiscountDTO.setDiscountPrice(item.getDiscountPrice());

            discountDTOList.add(DiscountDTO);
        });

       return discountDTOList;
    }
}
