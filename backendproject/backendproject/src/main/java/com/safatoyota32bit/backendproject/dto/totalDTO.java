package com.safatoyota32bit.backendproject.dto;

import lombok.Data;

import java.util.Date;
@Data
public class totalDTO {

    private int ID;

    private double totalPrice;

    private double change;

    private double pay;

    private Date date;

    private discountDTO DiscountDTO;

    private saleDTO SaleDTO;

}
