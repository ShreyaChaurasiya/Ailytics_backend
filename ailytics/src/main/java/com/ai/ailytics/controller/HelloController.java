package com.ai.ailytics.controller;

import com.ai.ailytics.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private DataAnalysisService service;

    @GetMapping("/sum")
    public double getSum(@RequestParam String column) {
        return service.calculateSum(column);
    }

    @GetMapping("/avg")
    public double avg(@RequestParam String column) {
        return service.calculateAvg(column);
    }

    @GetMapping("/group")
    public Map<String, Double> group(
            @RequestParam String groupBy,
            @RequestParam String value) {
        return service.groupBySum(groupBy, value);
    }
}