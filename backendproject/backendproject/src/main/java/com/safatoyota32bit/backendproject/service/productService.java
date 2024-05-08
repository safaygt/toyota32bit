package com.safatoyota32bit.backendproject.service;


import com.safatoyota32bit.backendproject.dto.productDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface productService {
        List<productDTO> getAllProducts();
}
