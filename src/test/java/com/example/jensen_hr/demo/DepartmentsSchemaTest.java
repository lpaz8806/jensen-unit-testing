package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.Column;
import com.example.demo.ColumnType;
import com.example.demo.ColumnsList;
import com.example.demo.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DepartmentsSchemaTest extends TableTestBase {
    public DepartmentsSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_JENSEN_HR"));
    }

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("departments");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("departments", "id");
        Assertions.assertNotNull(column);
    }
    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("departments", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void nameIsAtMost30CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("departments", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(30, column.getMaxLength());
    }
}
