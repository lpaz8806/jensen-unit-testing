package com.example.filmstaden.demo;

import com.example.TableTestBase;
import com.example.demo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DirectorsSchemaTest extends TableTestBase {
    public DirectorsSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_FILMSTADEN"));
    }

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("directors");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("directors", "id");
        Assertions.assertNotNull(column);
    }
    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("directors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }
    @Test
    public void nameIsAtMost50CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("directors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(50, column.getMaxLength());
    }
}
