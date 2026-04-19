package com.ai.ailytics.controller;

import com.ai.ailytics.model.DataRow;
import com.ai.ailytics.service.CSVService;
import com.ai.ailytics.storage.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UploadController {

    @Autowired
    private CSVService csvService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "File is empty ❌";
        }

        try {
            List<DataRow> data = csvService.parseAndProcessCSV(file);

            System.out.println("Rows parsed: " + data.size()); // ✅ DEBUG

            DataStorage.save(data);

            return "File uploaded successfully ✅ Rows: " + data.size();

        } catch (Exception e) {
            e.printStackTrace(); // ✅ IMPORTANT
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/data")
    public List<DataRow> getData() {
        return DataStorage.getAll();
    }
}