package com.safatoyota32bit.backendproject.service.impl;

import com.safatoyota32bit.backendproject.dto.productDTO;
import com.safatoyota32bit.backendproject.entity.product;
import com.safatoyota32bit.backendproject.repo.productRepository;
import com.safatoyota32bit.backendproject.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class productServiceImpl implements productService {

    private final productRepository ProductRepository;
    @Override
    @Transactional
    public List<productDTO> getAllProducts() {

        List<product> productList = ProductRepository.findAll();
        List<productDTO> productDTOList = new ArrayList<>();

        productList.forEach(item -> {

            productDTO ProductDTO = new productDTO();
            ProductDTO.setProductID(item.getProductID());
            ProductDTO.setProductName(item.getProductName());
            productDTOList.add(ProductDTO);

        });

        return productDTOList;
    }
}
