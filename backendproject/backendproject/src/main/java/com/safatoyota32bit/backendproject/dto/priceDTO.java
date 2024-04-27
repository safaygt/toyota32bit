package com.safatoyota32bit.backendproject.dto;

import java.util.Date;
import lombok.Data;

import java.util.List;
@Data
public class priceDTO {

    private int priceID;
    private double price;

    private Date date;

    private discountDTO discount;

    private productDTO product;


}
