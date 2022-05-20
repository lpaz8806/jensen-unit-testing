package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ActorsSchemaTest {

    private SchemaInformation info = new SchemaInformation();

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("actors");
        Assertions.assertEquals(1,columns.count());
    }
<<<<<<< HEAD
    @Test
    public void tableHasLastName() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "last_name");
        Assertions.assertNotNull(column);
    }
    @Test
    public void LastNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals( "varchar", column.getType());
    }
=======

    @Test
    public void checkDateOfBirth() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "born_on");
        Assertions.assertNotNull(column);
    }
>>>>>>> 043a6019cd66ccf2705617d7a53f206457a1fa56
}

