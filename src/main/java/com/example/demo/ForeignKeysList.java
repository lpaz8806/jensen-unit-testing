package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ForeignKeysList {
    private List<ForeignKey> foreignKeys;

    public ForeignKeysList(List<ForeignKey> list) {
        this.foreignKeys = list;
    }

    public ForeignKeysList filterByChildTable(String tableName) {
        List<ForeignKey> list = new ArrayList<>();
        for (ForeignKey foreignKey: foreignKeys) {
            if (foreignKey.getChildColumn().getTable().equals(tableName)) {
                list.add(foreignKey);
            }
        }
        return new ForeignKeysList(list);
    }

    public ForeignKey findByChildColumn(Column column) {
        for (ForeignKey foreignKey: foreignKeys) {
            if (foreignKey.getChildColumn().equals(column)) {
                return foreignKey;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ForeignKey c: foreignKeys) {
            result.append(c.toString());
        }
        return result.toString();
    }
}