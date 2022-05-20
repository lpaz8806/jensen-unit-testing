

package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class MoviesSchemaTest {
    private SchemaInformation info = new SchemaInformation();

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("movies");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("movies", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("movies", "title");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(column.getType(), "varchar");
    }

    @Test
    public void nameIsAtMost100CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("movies", "title");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(100, column.getMaxLength());
    }


    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("movies");
        Assertions.assertNotNull(foreignKeysList);
    }

    @Test
    public void foreignKeyExistsBetweenMovies_Directors() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("movies").filterByParentTable("directors");
        Assertions.assertNotNull(foreignKeysList);
    }
}

