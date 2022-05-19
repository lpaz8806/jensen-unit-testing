package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ColumnsList {
    private List<Column> columns;
    
    public ColumnsList(List<Column> columns) {
        this.columns = columns;
    }

    public int count() { return columns.size(); }

    public ColumnsList filterByTableName(String tableName) {
        List<Column> list = new ArrayList<>();
        for (Column column : this.columns)
        {
            if (tableName.equals(column.getTable())) {
                list.add(column);
            }
        }

        return new ColumnsList(list);
    }
    public ColumnsList filterByColumnName(String columnName) {
        List<Column> list = new ArrayList<>();
        for (Column column : columns)
        {
            if (columnName.equals(column.getColumn())) {
                list.add(column);
            }
        }

        return new ColumnsList(list);
    }
    public Column find(String tableName, String columnName) {
        for (Column column : columns)
        {
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
        for (Column c: columns) {
            result.append(c.toString());
        }
        return result.toString();
    }
}
