package com.safatoyota32bit.backendproject.service;


import com.safatoyota32bit.backendproject.dto.totalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface reportService {

    List<totalDTO> getAllSales();
    ByteArrayInputStream generateInvoice(List<totalDTO> sales,int totalID);

    Page<totalDTO> getAllSales(Pageable pageable, String filter);

}
