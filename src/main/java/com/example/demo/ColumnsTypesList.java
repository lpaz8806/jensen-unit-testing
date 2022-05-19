package com.example.demo;

import java.util.List;

public class ColumnsTypesList {
    private List<ColumnType> types;
    public ColumnsTypesList(List<ColumnType> list) {
        this.types = list;
    }

    public ColumnType findByColumn(String table, String column) {
        return findByColumn(new Column(table, column));
    }
    public ColumnType findByColumn(Column column) {
        for (ColumnType columnType: types) {
            if(columnType.getColumn().equals(column)) {
                return columnType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ColumnType c: types) {
            result.append(c.toString());
        }
        return result.toString();
    }
}
