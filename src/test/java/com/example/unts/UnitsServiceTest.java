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
    public void testWidelyUsedToSI() {
        assertEquals("rad", unitsService.convertUnits("degree").getUnit_name());
        assertEquals(".017453292519943", unitsService.convertUnits("degree").getMultiplication_factor());

        assertEquals("rad/s", unitsService.convertUnits("degree/minute").getUnit_name());
        assertEquals(".00029088820866572", unitsService.convertUnits("degree/minute").getMultiplication_factor());

        assertEquals("(rad/(s*m^2))", unitsService.convertUnits("(degree/(minute*hectare))").getUnit_name());
        assertEquals("29.088820866572E-9", unitsService.convertUnits("(degree/(minute*hectare))").getMultiplication_factor());

        assertEquals("m^2*rad", unitsService.convertUnits("ha*°").getUnit_name());
        assertEquals("174.53292519943", unitsService.convertUnits("ha*°").getMultiplication_factor());

        assertEquals("m^2*rad", unitsService.convertUnits("ha*'").getUnit_name());
        assertEquals("2.9088820866572", unitsService.convertUnits("ha*'").getMultiplication_factor());

        assertEquals("m^2*rad", unitsService.convertUnits("ha*\"").getUnit_name());
        assertEquals(".048481368110954", unitsService.convertUnits("ha*\"").getMultiplication_factor());
    }

    @Test
    public void testSIToSI() {
        assertEquals("m^2*s" , unitsService.convertUnits("m^2*s").getUnit_name());
        assertEquals("1" , unitsService.convertUnits("m^2*s").getMultiplication_factor());

        assertEquals("(kg/m^3)" , unitsService.convertUnits("(kg/m^3)").getUnit_name());
        assertEquals("1" , unitsService.convertUnits("(kg/m^3)").getMultiplication_factor());
    }

    @Test
    public void testMixedUnits() {
        assertEquals("m^2*m^2" , unitsService.convertUnits("m^2*ha").getUnit_name());
        assertEquals("10E+3" , unitsService.convertUnits("m^2*ha").getMultiplication_factor());

        assertEquals("(m^3/m^3)" , unitsService.convertUnits("(L/m^3)").getUnit_name());
        assertEquals(".001" , unitsService.convertUnits("(L/m^3)").getMultiplication_factor());
    }

}
