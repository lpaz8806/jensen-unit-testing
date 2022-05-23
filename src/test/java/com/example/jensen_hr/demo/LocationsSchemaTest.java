package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.Column;
import com.example.demo.ColumnsList;
import com.example.demo.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class LocationsSchemaTest extends TableTestBase {
    public LocationsSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_JENSEN_HR"));
    }

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("locations");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("locations", "id");
        Assertions.assertNotNull(column);
    }
}
