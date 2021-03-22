package com.example.unts;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;

@Service
public class UnitsService {

    private final Definitions definitions;

    UnitsService(Definitions definitions) {
        this.definitions = definitions;
    }

    public Response convertUnits(String nonSiUnit) {
        return new Response(getSiUnit(nonSiUnit), getMultiplicationFactor(nonSiUnit));
    }

    private String getSiUnit(String nonSiUnit) {
        return substituteUnits(nonSiUnit, FactorUnit::getSiUnit);
    }

    private String getMultiplicationFactor(String nonSiUnit) {
        String expression = substituteUnits(nonSiUnit,  factorUnit -> factorUnit.getFactor().toString());
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        String strResult = exp.getValue().toString();
        if(strResult==null) throw new RuntimeException("Error processing unit");
        BigDecimal bdResult = new BigDecimal(strResult);
        return adjustNumberOfSignificantDigits(bdResult);
    }

    private String adjustNumberOfSignificantDigits(BigDecimal bd) {
        int newScale = 14-bd.precision()+bd.scale();
        String result = bd.setScale(newScale, RoundingMode.HALF_UP).stripTrailingZeros().toEngineeringString();
        if(result.startsWith("0.")) result = result.substring(1);
        return result;
    }

    private String substituteUnits(String nonSiUnit, Function<FactorUnit, String> supplier) {
        String result = nonSiUnit;
        for( Map.Entry<String,FactorUnit> entry : definitions.mapStringToFactorUnit().entrySet() ) {
            String regEx;
            if (entry.getKey().equals("°")) {
                regEx = "\\u00b0";
            } else if(entry.getKey().equals("'")) {
                regEx = "'";
            } else if(entry.getKey().equals("\"")) {
                regEx = "\"";
            } else if(entry.getKey().equals("m^2")) {
                regEx = "m\\^2";
            } else if(entry.getKey().equals("m^3")) {
                regEx = "m\\^3";
            } else {
                regEx = "(?<=\\b)(" + entry.getKey() + ")+(?=\\b)";
            }
            result = result.replaceAll(regEx, supplier.apply(entry.getValue()));
        }
        return result;
    }

}
