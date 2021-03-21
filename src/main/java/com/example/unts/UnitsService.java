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
        Double dResult = (Double) exp.getValue();
        if(dResult==null) throw new RuntimeException("Error processing unit");
        BigDecimal bdResult = BigDecimal.valueOf(dResult);
        return adjustNumberOfSignificantDigits(bdResult);
    }

    private String adjustNumberOfSignificantDigits(BigDecimal bd) {
        int newScale = 14-bd.precision()+bd.scale();
        String result = bd.setScale(newScale, RoundingMode.HALF_UP).toEngineeringString();
        if(result.startsWith("0.")) result = result.substring(1);
        return result;
    }

    private String substituteUnits(String nonSiUnit, Function<FactorUnit, String> supplier) {
        String result = nonSiUnit;
        for( Map.Entry<String,FactorUnit> entry : definitions.mapStringToFactorUnit().entrySet() ) {
            String regEx;
            switch(entry.getKey().charAt(0)) {
                case '°': regEx = "\\u00b0"; break;
                case '\'': regEx = "'"; break;
                case '"': regEx = "\""; break;
                default: regEx = "(?<=\\b)(" + entry.getKey() + ")+(?=\\b)";
            }
            result = result.replaceAll(regEx, supplier.apply(entry.getValue()));
        }
        return result;
    }

}
