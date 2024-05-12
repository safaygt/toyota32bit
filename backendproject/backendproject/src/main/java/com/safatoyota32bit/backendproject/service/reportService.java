package com.safatoyota32bit.backendproject.service;


import com.safatoyota32bit.backendproject.dto.totalDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface reportService {

    List<totalDTO> getAllSales();
    ByteArrayInputStream generateInvoice(int saleID);

}
