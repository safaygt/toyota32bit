package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.discountDTO;
import com.safatoyota32bit.backendproject.dto.saleDTO;
import com.safatoyota32bit.backendproject.dto.totalDTO;
import com.safatoyota32bit.backendproject.service.saleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")

public class saleController {

    private final saleService SaleService;

    @PostMapping("/record")
    @PreAuthorize("hasRole('Role_Cashier')")
    public ResponseEntity<Void> recordSale(@RequestBody saleDTO SaleDTO){
        log.debug("Recording sale by cashier {}",SaleDTO.getSaleID());

        try{
            log.info("Sale recorded successfully");
            SaleService.recordSale(SaleDTO);
            return ResponseEntity.ok().build();

        }catch (Exception e){

            log.error("Error occurred while recording sale", e);
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping("/recordTotal")
    @PreAuthorize("hasRole('Role_Cashier')")
    public ResponseEntity<Void> recordTotal(@RequestBody totalDTO TotalDTO) {
        log.info("Recording total by cashier {}",TotalDTO.getID());

        try {
            SaleService.recordTotal(TotalDTO);
            log.info("Total recorded successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error occurred while recording total", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/discounts")
    @PreAuthorize("hasRole('Role_Cashier')")
    public ResponseEntity<List<discountDTO>> getAllDiscounts() {

        log.debug("Fetching all discounts by cashier");
        List<discountDTO> discounts;
        try {
            discounts = SaleService.getAllDiscounts();
            log.info("Fetched {} discounts", discounts.size());
            return ResponseEntity.ok(discounts);
        } catch (Exception e) {
            log.error("Error occurred while fetching discounts", e);
            return ResponseEntity.status(500).build();
        }
    }


    @PostMapping("/addDiscount")
    @PreAuthorize("hasRole('Role_Cashier')")
    public ResponseEntity<Void> addDiscount(@RequestBody discountDTO DiscountDTO) {

        log.debug("Adding discount by cashier: {}", DiscountDTO.getDiscountName());
        try {
            SaleService.addDiscount(DiscountDTO);
            log.info("Discount added successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error occurred while adding discount", e);
            return ResponseEntity.status(500).build();
        }
    }
}
