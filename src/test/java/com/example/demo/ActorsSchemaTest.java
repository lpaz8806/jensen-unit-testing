package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ActorsSchemaTest {

    private SchemaInformation info = new SchemaInformation();
    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(column.getType(), "varchar");
    }

    @Test
    public void nameIsAtMost40CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(50, column.getMaxLength());
    }
    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("actors");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void firstNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(column.getType(), "varchar");
    }

    @Test
    public void genderIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "gender");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("char",column.getType());
    }

    @Test
    public void columnBornOnExists() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "born_on");
        Assertions.assertNotNull(column);
    }
}

