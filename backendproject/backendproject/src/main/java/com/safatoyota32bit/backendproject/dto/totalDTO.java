package com.safatoyota32bit.backendproject.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class totalDTO {

    private int ID;

    private double totalPrice;

    private double change;

    private double pay;

    private Date date;

    private discountDTO DiscountDTO;

    private List<saleDTO> SaleDTO;

    private userDTO UserDTO;

    private salesTypeDTO SalesTypeDTO;

}
