package com.example.unts;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class Definitions {
    private static BigDecimal pi = new BigDecimal("3.14159265358979323846");
    private Map<String, FactorUnit> mapStringToFactorUnit = new HashMap<>();
    {
        add("minute", "min", BigDecimal.valueOf(60), "s");
        add("hour", "h", BigDecimal.valueOf(3600), "s");
        add("day", "d", BigDecimal.valueOf(86400), "s");
        add("degree", "\u00B0", pi.divide(BigDecimal.valueOf(180), MathContext.DECIMAL128), "rad");
        add("arcminute", "'", pi.divide(BigDecimal.valueOf(10800), MathContext.DECIMAL128), "rad");
        add("arcsecond", "\"", pi.divide(BigDecimal.valueOf(648000), MathContext.DECIMAL128), "rad");
        add("hectare", "ha", BigDecimal.valueOf(10000), "m^2");
        add("litre", "L", BigDecimal.valueOf(0.001), "m^3");
        add("tonne", "t", BigDecimal.valueOf(1000), "kg");
    }

    private void add(String name, String symbol, BigDecimal factor, String siUnit) {
        mapStringToFactorUnit.put(name, new FactorUnit(factor, siUnit));
        mapStringToFactorUnit.put(symbol, new FactorUnit(factor, siUnit));
    }

    public Map<String, FactorUnit> mapStringToFactorUnit() {
        return Collections.unmodifiableMap(mapStringToFactorUnit);
    }

}
