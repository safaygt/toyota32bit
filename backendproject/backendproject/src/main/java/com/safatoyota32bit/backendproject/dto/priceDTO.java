package com.safatoyota32bit.backendproject.dto;

import java.util.Date;
import lombok.Data;


@Data
public class priceDTO {

    private int priceID;
    private double price;

    private Date date;

    private discountDTO DiscountDTO;

    private productDTO ProductDTO;


}
