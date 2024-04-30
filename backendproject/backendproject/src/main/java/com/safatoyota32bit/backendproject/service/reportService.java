package com.safatoyota32bit.backendproject.service;


import com.safatoyota32bit.backendproject.dto.saleDTO;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface reportService {

    List<saleDTO> getAllSales();
    ByteArrayInputStream generateInvoice(int saleID);

}
