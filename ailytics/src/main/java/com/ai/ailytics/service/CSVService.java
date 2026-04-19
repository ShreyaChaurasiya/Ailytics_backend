package com.ai.ailytics.service;

import com.ai.ailytics.model.DataRow;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class CSVService {

    public List<DataRow> parseAndProcessCSV(MultipartFile file) throws Exception {

        List<DataRow> dataList = new ArrayList<>();

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord record : csvParser) {

            Map<String, String> rowMap = new HashMap<>();

            for (String header : csvParser.getHeaderNames()) {

                String value = record.get(header);

                if (value == null || value.trim().isEmpty()) {
                    value = "0";
                }

                value = value.trim();

                try {
                    double num = Double.parseDouble(value);
                    value = String.valueOf(num);
                } catch (Exception e) {
                    value = value.toLowerCase();
                }

                rowMap.put(header.toLowerCase(), value);
            }

            dataList.add(new DataRow(rowMap));
        }

        csvParser.close();
        return dataList;
    }
}