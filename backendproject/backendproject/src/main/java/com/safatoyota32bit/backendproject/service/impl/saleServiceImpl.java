package com.safatoyota32bit.backendproject.service.impl;

import com.safatoyota32bit.backendproject.dto.*;
import com.safatoyota32bit.backendproject.entity.*;
import com.safatoyota32bit.backendproject.repo.*;
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
    private final userRepository UserRepository;
    private final salesTypeRepository SalesTypeRepository;
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
        List<sale> Sales = TotalDTO.getSaleDTO().stream()
                .map(saleDTO -> {
                    sale Sale = SaleRepository.findById(saleDTO.getSaleID())
                            .orElseThrow(() -> new RuntimeException("Sale not found"));
                    return Sale;
                })
                .collect(Collectors.toList());
        userDTO UserDTO = TotalDTO.getUserDTO();
        user User = UserRepository.findById(UserDTO.getUserID()).orElseThrow(() -> new RuntimeException("User not found: " + UserDTO.getUserID()));
        salesTypeDTO SalesTypeDTO = TotalDTO.getSalesTypeDTO();
        salestype SalesType = SalesTypeRepository.findById(SalesTypeDTO.getSalesTypeID()).orElseThrow(() -> new RuntimeException("Payment type not found: " + SalesTypeDTO.getSalesTypeID()));


        Total.setTotalPrice(TotalDTO.getTotalPrice());
        Total.setChange(TotalDTO.getChange());
        Total.setPay(TotalDTO.getPay());
        Total.setDate(TotalDTO.getDate());
        Total.setDiscount(Discount);
        Total.setSale(Sales);
        Total.setUser(User);
        Total.setSalesType(SalesType);
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
