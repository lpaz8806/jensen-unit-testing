package com.example.demo;

public class ColumnType {
    private Column column;
    private String type;
    private long maxLength;
    private Boolean _isNullable;

    public ColumnType(Column column, String type, long maxLength, Boolean isNullable) {
        this.column = column;
        this.type = type;
        this.maxLength = maxLength;
        this._isNullable = isNullable;
    }

    public Column getColumn() {
        return column;
    }

    public String getType() {
        return type;
    }

    public long getMaxLength() {
        return maxLength;
    }

    public Boolean isNullable() {
        return _isNullable;
    }

    @Override
    public String toString() {
        return String.format(
                "%s.%s (%s, %s)\n",
                column.getTable(), column.getColumn(),
                type,
                _isNullable? "NULL": "NOT NULL"
        );
    }
}
