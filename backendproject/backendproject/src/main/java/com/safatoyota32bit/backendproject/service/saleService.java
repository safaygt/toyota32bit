package com.safatoyota32bit.backendproject.service;


import com.safatoyota32bit.backendproject.dto.discountDTO;
import com.safatoyota32bit.backendproject.dto.saleDTO;
import com.safatoyota32bit.backendproject.dto.totalDTO;

import java.util.Date;
import java.util.List;


public interface saleService {

    void recordSale(saleDTO SaleDTO);

    void recordTotal(totalDTO TotalDTO);

    List<discountDTO> getAllDiscounts();
    void addDiscount(discountDTO DiscountDTO);
}
