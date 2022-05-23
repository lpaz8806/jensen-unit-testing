package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.*;
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

    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("departments");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenDepartments_Locations() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("departments")
                .filterByParentTable("locations");

        Assertions.assertNotNull(foreignKeysList);
    }

    @Test
    public void idLocationReferencesLocations() throws SQLException {
        ForeignKey fkIdLocations = info
                .getForeignKeys()
                .findByChildColumn(new Column("departments", "id_location"));

        Assertions.assertNotNull(fkIdLocations);
        Assertions.assertTrue(fkIdLocations
                .getParentColumn()
                .equals(new Column("locations", "id"))
        );
    }

    @Test
    public void foreignKeyUsesTheNamingConvention() throws SQLException {
        ForeignKey fkIdLocations = info
                .getForeignKeys()
                .findByChildColumn(new Column("departments", "id_location"));

        Assertions.assertNotNull(fkIdLocations);
        Assertions.assertTrue(fkIdLocations
                .getName()
                .equals("fk__%s__%s".formatted(
                        fkIdLocations.getChildColumn().getTable(),
                        fkIdLocations.getParentColumn().getTable()
                ))
        );
    }
}
