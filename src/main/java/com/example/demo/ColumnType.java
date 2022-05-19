package com.example.demo;

public class ColumnType extends Column {
    private String type;
    private int maxLength;
    private Boolean isNullable;

    public ColumnType(String table, String column, String type, int maxLength, Boolean isNullable) {
        super(table, column);
        this.type = type;
        this.maxLength = maxLength;
        this.isNullable = isNullable;
    }
}
