package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.productDTO;
import com.safatoyota32bit.backendproject.service.productService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Log4j2
public class productController {

    private final productService ProductService;

    @GetMapping
    public ResponseEntity<List<productDTO>> getAllProducts(){


        log.info("Fetching all products");


        List<productDTO> products;

        try{
            products = ProductService.getAllProducts();
            log.debug("Number of products fetched: {}", products.size());

        }catch (Exception e){
            log.error("Error occurred while fetching products", e);
            return ResponseEntity.status(500).build();

        }
        log.info("Successfully fetched all products");
        return ResponseEntity.ok(products);

    }


}
