package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ReviewersSchemaTest{

    private SchemaInformation info = new SchemaInformation();

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("reviewers");
        Assertions.assertEquals(1, columns.count());
    }
    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("reviewers", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("reviewers", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(column.getType(), "varchar");
    }

    @Test
    public void nameIsAtMost40CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("reviewers", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(45, column.getMaxLength());
    }
}