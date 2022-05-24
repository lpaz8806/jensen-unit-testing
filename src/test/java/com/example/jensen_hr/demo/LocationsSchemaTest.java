package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.*;
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

    @Test
    public void streetIsAtMost25CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "street");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(25, column.getMaxLength());
    }

    @Test
    public void zipcodeIsAtMost25CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "zipcode");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(12, column.getMaxLength());
    }

    @Test
    public void cityIsAtMost30CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "city");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(30, column.getMaxLength());
    }

    @Test
    public void streetIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "street");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void zipcodeIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "zipcode");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void cityIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "city");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void idCountryIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("locations", "id_country");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }

    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("locations");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenLocations_Countries() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("locations")
                .filterByParentTable("countries");

        Assertions.assertNotNull(foreignKeysList);
    }

    @Test
    public void idCountryReferencesCountries() throws SQLException {
        ForeignKey fkIdCountries = info
                .getForeignKeys()
                .findByChildColumn(new Column("locations", "id_country"));

        Assertions.assertNotNull(fkIdCountries);
        Assertions.assertTrue(fkIdCountries
                .getParentColumn()
                .equals(new Column("countries", "id"))
        );
    }

    @Test
    public void foreignKeyUsesTheNamingConvention() throws SQLException {
        ForeignKey fkIdCountries = info
                .getForeignKeys()
                .findByChildColumn(new Column("locations", "id_country"));

        Assertions.assertNotNull(fkIdCountries);
        Assertions.assertTrue(fkIdCountries
                .getName()
                .equals("fk__%s__%s".formatted(
                        fkIdCountries.getChildColumn().getTable(),
                        fkIdCountries.getParentColumn().getTable()
                ))
        );
    }
}
