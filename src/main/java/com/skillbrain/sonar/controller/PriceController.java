package com.skillbrain.sonar.controller;

import com.skillbrain.sonar.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/total")
    public BigDecimal total(@RequestParam List<BigDecimal> values) {
        return priceService.total(values);
    }

    @GetMapping("/total-with-vat")
    public BigDecimal totalWithVat(@RequestParam List<BigDecimal> values, @RequestParam(defaultValue = "19") int vat) {
        BigDecimal total = priceService.total(values);
        return priceService.addVat(total, vat);
    }
}
