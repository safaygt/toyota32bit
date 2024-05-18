package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.discountDTO;
import com.safatoyota32bit.backendproject.dto.saleDTO;
import com.safatoyota32bit.backendproject.dto.totalDTO;
import com.safatoyota32bit.backendproject.service.saleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class saleController {

    private final saleService SaleService;

    @PostMapping("/record")
    @Secured("Role_Cashier")
    public ResponseEntity<Void> recordSale(@RequestBody saleDTO SaleDTO){

        SaleService.recordSale(SaleDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recordTotal")
    @Secured("Role_Cashier")
    public ResponseEntity<Void> recordTotal(@RequestBody totalDTO TotalDTO) {
        SaleService.recordTotal(TotalDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/discounts")
    @Secured("Role_Cashier")
    public ResponseEntity<List<discountDTO>> getAllDiscounts() {
        List<discountDTO> discounts = SaleService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }


    @PostMapping("/addDiscount")
    @Secured("Role_Cashier")
    public ResponseEntity<Void> addDiscount(@RequestBody discountDTO DiscountDTO) {
        SaleService.addDiscount(DiscountDTO);
        return ResponseEntity.ok().build();
    }
}
