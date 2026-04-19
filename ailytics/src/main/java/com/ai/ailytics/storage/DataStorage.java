package com.ai.ailytics.storage;

import com.ai.ailytics.model.DataRow;

import java.util.*;

public class DataStorage {

    private static List<DataRow> data = new ArrayList<>();

    public static void save(List<DataRow> newData) {
        data = newData;
    }

    public static List<DataRow> getAll() {
        return data;
    }
}
