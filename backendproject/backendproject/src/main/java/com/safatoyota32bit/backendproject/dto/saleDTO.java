package com.safatoyota32bit.backendproject.dto;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class saleDTO {

    private int saleID;
    private productDTO ProductDTO;
    private salesTypeDTO SalesTypeDTO;
    private priceDTO PriceDTO;
    private int count;
    private Date date;
}
