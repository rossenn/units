package com.example.unts;

public class Response {

    private final String unit_name;
    private final String multiplication_factor;

    public Response(String unit_name, String multiplication_factor) {
        this.unit_name = unit_name;
        this.multiplication_factor = multiplication_factor;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public String getMultiplication_factor() {
        return multiplication_factor;
    }
}
