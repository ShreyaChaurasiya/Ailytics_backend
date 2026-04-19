package com.ai.ailytics.model;

import java.util.Map;

public class DataRow {

    private Map<String, String> data;

    public DataRow(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}