package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ForeignKeysList {
    private List<ForeignKey> foreignKeys;

    public ForeignKeysList(List<ForeignKey> list) {
        this.foreignKeys = list;
    }

    public int count() { return foreignKeys.size(); }

    public ForeignKeysList filterByChildTable(String tableName) {
        List<ForeignKey> foundFks = foreignKeys.stream()
                .filter(fk -> fk.getChildColumn().getTable().equals(tableName))
                .collect(Collectors.toList());

        return new ForeignKeysList(foundFks);
    }

    public ForeignKey findByChildColumn(Column column) {
        Optional<ForeignKey> foundFk = foreignKeys.stream()
                .filter(fk -> fk.getChildColumn().equals(column))
                .findFirst();

        return foundFk.isPresent() ? foundFk.get() : null;
    }
    public ForeignKeysList filterByParentTable(String tableName) {
        List<ForeignKey> foundFks = foreignKeys.stream()
                .filter(fk -> fk.getParentColumn().getTable().equals(tableName))
                .collect(Collectors.toList());

        return new ForeignKeysList(foundFks);
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