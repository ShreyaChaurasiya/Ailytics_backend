package com.ai.ailytics.controller;

import com.ai.ailytics.model.DataRow;
import com.ai.ailytics.service.DataAnalysisService;
import com.ai.ailytics.storage.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private DataAnalysisService service;

    // SUM
    @GetMapping("/sum")
    public double getSum(@RequestParam String column) {
        return service.calculateSum(column);
    }

    // AVG
    @GetMapping("/avg")
    public double avg(@RequestParam String column) {
        return service.calculateAvg(column);
    }

    // GROUP BY
    @GetMapping("/group")
    public Map<String, Double> group(
            @RequestParam String groupBy,
            @RequestParam String value) {
        return service.groupBySum(groupBy, value);
    }

    // ✅ COUNT
    @GetMapping("/count")
    public int count() {
        return DataStorage.getAll().size();
    }

    // MAX
    @GetMapping("/max")
    public double max(@RequestParam String column) {
        return DataStorage.getAll().stream()
                .map(row -> row.getData().get(column))
                .filter(Objects::nonNull)
                .mapToDouble(Double::parseDouble)
                .max()
                .orElse(0);
    }

    // MIN
    @GetMapping("/min")
    public double min(@RequestParam String column) {
        return DataStorage.getAll().stream()
                .map(row -> row.getData().get(column))
                .filter(Objects::nonNull)
                .mapToDouble(Double::parseDouble)
                .min()
                .orElse(0);
    }

    // FILTER
    @GetMapping("/filter")
    public List<DataRow> filter(
            @RequestParam String column,
            @RequestParam String value) {

        return DataStorage.getAll().stream()
                .filter(row -> {
                    String val = row.getData().get(column);
                    return val != null && val.equalsIgnoreCase(value);
                })
                .toList();
    }

    // TEST API
    @GetMapping("/hello")
    public String hello() {
        return "Backend connected successfully 🚀";
    }
}