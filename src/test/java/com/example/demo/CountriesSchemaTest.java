package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CountriesSchemaTest {
    private SchemaInformation info = new SchemaInformation();

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("countries");
        Assertions.assertEquals(1, columns.count());
    }
    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("countries", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("countries", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void nameIsAtMost40CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("countries", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(40, column.getMaxLength());
    }
}
//type of column c is T