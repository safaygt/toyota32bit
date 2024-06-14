package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.totalDTO;
import com.safatoyota32bit.backendproject.service.reportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/report")

public class reportController {

    private final reportService ReportService;

    @PreAuthorize("hasRole('Role_Store_Manager')")
    @GetMapping("/sales")
    public ResponseEntity<Page<totalDTO>> getAllSales(Pageable pageable, @RequestParam(required = false) String filter){
        log.info("Fetching all sales with filter: {}", filter);
        Page<totalDTO> sales;


        try {
            sales = ReportService.getAllSales(pageable, filter);
            log.debug("Fetched {} sales records", sales.getTotalElements());
        }catch (Exception e){

            log.error("Error occurred while fetching sales", e);
            return ResponseEntity.status(500).build();

        }

        log.info("Successfully fetched sales");
        return ResponseEntity.ok(sales);

    }

    @PreAuthorize("hasRole('Role_Store_Manager')")
    @GetMapping("/invoice/{totalID}")
    @ResponseBody
    public ResponseEntity<byte[]> generateInvoice(@PathVariable int totalID){
        log.info("Generating invoice for totalID: {}", totalID);
        List<totalDTO> Sales;
        ByteArrayInputStream bis;

        try {
            Sales = ReportService.getAllSales();
            bis = ReportService.generateInvoice(Sales, totalID);
            log.debug("Invoice generated for totalID: {}", totalID);
        }catch (Exception e){
            log.error("Error occurred while generating invoice for totalID: {}", totalID, e);
            return ResponseEntity.status(500).build();
        }

        HttpHeaders Headers = new HttpHeaders();
        Headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf");

        log.info("Successfully generated invoice for totalID: {}", totalID);

        return ResponseEntity
                .ok()
                .headers(Headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());

    }

}
