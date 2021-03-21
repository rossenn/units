package com.example.unts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitsController {

    private final UnitsService unitsService;

    public UnitsController(UnitsService unitsService) {
        this.unitsService = unitsService;
    }

    @GetMapping("/units/si")
    public ResponseEntity<Response> convertUnits(@RequestParam String unit) {
        return ResponseEntity.ok().body(unitsService.convertUnits(unit));
    }
}
