package com.safatoyota32bit.backendproject.controller;

import com.safatoyota32bit.backendproject.dto.totalDTO;
import com.safatoyota32bit.backendproject.service.reportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class reportController {

    private final reportService ReportService;

    @Secured("Role_Magaza_Muduru")
    @GetMapping("/sales")
    public ResponseEntity<Page<totalDTO>> getAllSales(Pageable pageable, @RequestParam(required = false) String filter){

        Page<totalDTO> sales = ReportService.getAllSales(pageable, filter);
        return ResponseEntity.ok(sales);

    }

    @Secured("Role_Magaza_Muduru")
    @GetMapping("/invoice/{totalID}")
    @ResponseBody
    public ResponseEntity<byte[]> generateInvoice(@PathVariable int totalID){
        List<totalDTO> Sales = ReportService.getAllSales();
        ByteArrayInputStream bis = ReportService.generateInvoice(Sales, totalID);
        HttpHeaders Headers = new HttpHeaders();
        Headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf");

        return ResponseEntity
                .ok()
                .headers(Headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());

    }

}
