package com.skillbrain.sonar.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceServiceTest {

    private final PriceService priceService = new PriceService();

    @Test
    void totalShouldReturnSumWithTwoDecimals() {
        BigDecimal result = priceService.total(List.of(
                new BigDecimal("10.10"),
                new BigDecimal("5.25"),
                new BigDecimal("4.65")
        ));

        assertEquals(new BigDecimal("20.00"), result);
    }

    @Test
    void totalShouldReturnZeroForEmptyList() {
        BigDecimal result = priceService.total(List.of());
        assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    void addVatShouldReturnValueWithVat() {
        BigDecimal result = priceService.addVat(new BigDecimal("100.00"), 19);
        assertEquals(new BigDecimal("119.00"), result);
    }
}
