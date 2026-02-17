package com.skillbrain.sonar.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PriceService {

    public BigDecimal total(List<BigDecimal> values) {
        if (values == null || values.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal value : values) {
            if (value != null) {
                result = result.add(value);
            }
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal addVat(BigDecimal amount, int vatPercent) {
        if (amount == null || vatPercent < 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal vatMultiplier = BigDecimal.valueOf(vatPercent).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal vat = amount.multiply(vatMultiplier);
        return amount.add(vat).setScale(2, RoundingMode.HALF_UP);
    }
}
