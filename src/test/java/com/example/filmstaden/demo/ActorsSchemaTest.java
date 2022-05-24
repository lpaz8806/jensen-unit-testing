package com.example.filmstaden.demo;

import com.example.TableTestBase;
import com.example.demo.ColumnType;
import com.example.demo.ColumnsList;
import com.example.demo.Config;
import com.example.demo.SchemaInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ActorsSchemaTest extends TableTestBase {
    public ActorsSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_FILMSTADEN"));
    }
    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("actors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar",column.getType());
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
        Assertions.assertEquals("varchar", column.getType());
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

