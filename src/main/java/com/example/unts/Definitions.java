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
        addNonSi("minute", "min", BigDecimal.valueOf(60), "s");
        addNonSi("hour", "h", BigDecimal.valueOf(3600), "s");
        addNonSi("day", "d", BigDecimal.valueOf(86400), "s");
        addNonSi("degree", "\u00B0", pi.divide(BigDecimal.valueOf(180), MathContext.DECIMAL128), "rad");
        addNonSi("arcminute", "'", pi.divide(BigDecimal.valueOf(10800), MathContext.DECIMAL128), "rad");
        addNonSi("arcsecond", "\"", pi.divide(BigDecimal.valueOf(648000), MathContext.DECIMAL128), "rad");
        addNonSi("hectare", "ha", BigDecimal.valueOf(10000), "m^2");
        addNonSi("litre", "L", BigDecimal.valueOf(0.001), "m^3");
        addNonSi("tonne", "t", BigDecimal.valueOf(1000), "kg");

        addSi("s");
        addSi("rad");
        addSi("m^2");
        addSi("m^3");
        addSi("kg");
    }

    private void addNonSi(String name, String symbol, BigDecimal factor, String siUnit) {
        mapStringToFactorUnit.put(name, new FactorUnit(factor, siUnit));
        mapStringToFactorUnit.put(symbol, new FactorUnit(factor, siUnit));
    }

    private void addSi(String siUnit) {
        mapStringToFactorUnit.put(siUnit, new FactorUnit(BigDecimal.ONE, siUnit));
    }

    public Map<String, FactorUnit> mapStringToFactorUnit() {
        return Collections.unmodifiableMap(mapStringToFactorUnit);
    }

}
