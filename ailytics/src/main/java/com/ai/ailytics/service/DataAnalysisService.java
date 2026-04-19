package com.ai.ailytics.service;

import com.ai.ailytics.model.DataRow;
import com.ai.ailytics.storage.DataStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataAnalysisService {

    public double calculateSum(String column) {
        List<DataRow> data = DataStorage.getAll();
        double sum = 0;

        for (DataRow row : data) {
            String value = row.getData().get(column);

            if (value == null) continue;

            try {
                sum += Double.parseDouble(value);
            } catch (Exception ignored) {}
        }

        return sum;
    }

    public double calculateAvg(String column) {
        List<DataRow> data = DataStorage.getAll();
        double sum = 0;
        int count = 0;

        for (DataRow row : data) {
            try {
                sum += Double.parseDouble(row.getData().get(column));
                count++;
            } catch (Exception ignored) {}
        }

        return count == 0 ? 0 : sum / count;
    }

    public Map<String, Double> groupBySum(String groupColumn, String valueColumn) {

        List<DataRow> data = DataStorage.getAll();
        Map<String, Double> result = new HashMap<>();

        for (DataRow row : data) {

            String key = row.getData().get(groupColumn);
            String value = row.getData().get(valueColumn);

            if (key == null || value == null) continue;

            try {
                double num = Double.parseDouble(value);
                result.put(key, result.getOrDefault(key, 0.0) + num);
            } catch (Exception ignored) {}
        }

        return result;
    }
}