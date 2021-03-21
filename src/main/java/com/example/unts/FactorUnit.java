package com.example.unts;

import java.math.BigDecimal;

public class FactorUnit {
    private BigDecimal factor;
    private String siUnit;

    public FactorUnit(BigDecimal factor, String siUnit) {
        this.factor = factor;
        this.siUnit = siUnit;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public String getSiUnit() {
        return siUnit;
    }
}
