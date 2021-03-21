package com.example.unts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ComponentScan(basePackages = {"com.example.units"})
public class UnitsServiceTest {

    @Autowired private UnitsService unitsService;

    @Test
    public void test() {
        assertEquals("rad"             , unitsService.getSI("degree").getUnit_name());
        assertEquals(".017453292519943", unitsService.getSI("degree").getMultiplication_factor());

        assertEquals("rad/s"             , unitsService.getSI("degree/minute").getUnit_name());
        assertEquals(".00029088820866572", unitsService.getSI("degree/minute").getMultiplication_factor());

        assertEquals("(rad/(s*m^2))"     , unitsService.getSI("(degree/(minute*hectare))").getUnit_name());
        assertEquals("29.088820866572E-9", unitsService.getSI("(degree/(minute*hectare))").getMultiplication_factor());

        assertEquals("m^2*rad"        , unitsService.getSI("ha*°").getUnit_name());
        assertEquals("174.53292519943", unitsService.getSI("ha*°").getMultiplication_factor());

        assertEquals("m^2*rad"        , unitsService.getSI("ha*'").getUnit_name());
        assertEquals("2.9088820866572", unitsService.getSI("ha*'").getMultiplication_factor());

        assertEquals("m^2*rad"         , unitsService.getSI("ha*\"").getUnit_name());
        assertEquals(".048481368110954", unitsService.getSI("ha*\"").getMultiplication_factor());
    }
}
