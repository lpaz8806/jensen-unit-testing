package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.Column;
import com.example.demo.ColumnType;
import com.example.demo.ColumnsList;
import com.example.demo.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class JobsSchemaTest extends TableTestBase {
    public JobsSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_JENSEN_HR"));
    }

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("jobs");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("jobs", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("jobs", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void nameIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("jobs", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }

    @Test
    public void minSalaryIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("jobs", "min_salary");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }

    @Test
    public void maxSalaryIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("jobs", "max_salary");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
}
