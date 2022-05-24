package com.example.demo;

import java.util.List;
import java.util.Optional;

public class ColumnsTypesList {
    private List<ColumnType> types;
    public ColumnsTypesList(List<ColumnType> list) {
        this.types = list;
    }

    public ColumnType findByColumn(String table, String column) {
        return findByColumn(new Column(table, column));
    }
    public ColumnType findByColumn(Column column) {
        Optional<ColumnType> foundColumn = types.stream()
                .filter(c -> c.getColumn().equals(column))
                .findFirst();

        return foundColumn.isPresent() ? foundColumn.get() : null;
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
