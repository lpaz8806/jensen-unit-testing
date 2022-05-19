package com.example.demo;

public class Column {
    private String table;
    private String column;

    public Column(String table, String column) {
        this.column = column;
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public String getTable() {
        return table;
    }

    @Override
    public String toString() {
        return String.format("%20s  %20s\n", table, column);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Column)) {
            return false;
        }
        Column other = (Column)obj;
        return this.table.equals(other.table) &&
                this.column.equals(other.column);
    }
}
