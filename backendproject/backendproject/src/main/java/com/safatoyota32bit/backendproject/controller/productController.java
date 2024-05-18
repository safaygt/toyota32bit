package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.productDTO;
import com.safatoyota32bit.backendproject.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")

public class productController {

    private final productService ProductService;

    @GetMapping
    public ResponseEntity<List<productDTO>> getAllProducts(){
        List<productDTO> products = ProductService.getAllProducts();
        return ResponseEntity.ok(products);

    }


}
