package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnsList {
    private List<Column> columns;

    public ColumnsList(List<Column> columns) {
        this.columns = columns;
    }

    public int count() {
        return columns.size();
    }

    public ColumnsList filterByTableName(String tableName) {
        List<Column> foundTableName = columns.stream()
                .filter(column -> column.getTable().equals(tableName))
                .collect(Collectors.toList());

        return new ColumnsList(foundTableName);
    }

    public ColumnsList filterByTableNameOld(String tableName) {
        List<Column> list = new ArrayList<>();
        for (Column column : this.columns) {
            if (tableName.equals(column.getTable())) {
                list.add(column);
            }
        }

        return new ColumnsList(list);
    }

    public ColumnsList filterByColumnName(String columnName) {
        List<Column> foundColumnName = columns.stream()
                .filter(column -> column.getColumn().equals(columnName))
                .collect(Collectors.toList());

        return new ColumnsList(foundColumnName);
    }

    public ColumnsList filterByColumnNameOld(String columnName) {
        List<Column> list = new ArrayList<>();
        for (Column column : columns) {
            if (columnName.equals(column.getColumn())) {
                list.add(column);
            }
        }

        return new ColumnsList(list);
    }

    public Column find(String tableName, String columnName) {
        for (Column column : columns) {
            if (columnName.equals(column.getColumn()) &&
                    tableName.equals(column.getTable())) {
                return column;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Column c : columns) {
            result.append(c.toString());
        }
        return result.toString();
    }
}
