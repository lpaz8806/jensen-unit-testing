package com.example.jensen_hr.demo;

import com.example.TableTestBase;
import com.example.demo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class EmployeesSchemaTest extends TableTestBase {
    public EmployeesSchemaTest() throws SQLException {
        super(Config.getInstance().get("DB_CONNECTION_JENSEN_HR"));
    }

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("employees");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("employees", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void firstNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void lastNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void firstNameIsAtMost30CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(30, column.getMaxLength());
    }

    @Test
    public void lastNameIsAtMost30CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(30, column.getMaxLength());
    }

    @Test
    public void phoneNumberIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "phone_number");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void phoneNumberIsAtMost20CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "phone_number");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(20, column.getMaxLength());
    }

    @Test
    public void emailIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void emailIsAtMost200CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(200, column.getMaxLength());
    }

    @Test
    public void idDepartmentIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "id_department");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }

    @Test
    public void idJobIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("employees", "id_job");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }

    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("employees");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenEmployees_Departments() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("employees")
                .filterByParentTable("departments");

        Assertions.assertNotNull(foreignKeysList);
    }

    @Test
    public void foreignKeyExistsBetweenEmployees_Jobs() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("employees")
                .filterByParentTable("jobs");

        Assertions.assertNotNull(foreignKeysList);
    }

    @Test
    public void idDepartmentReferencesDepartments() throws SQLException {
        ForeignKey fkIdDepartment = info
                .getForeignKeys()
                .findByChildColumn(new Column("employees", "id_department"));

        Assertions.assertNotNull(fkIdDepartment);
        Assertions.assertTrue(fkIdDepartment
                .getParentColumn()
                .equals(new Column("departments", "id"))
        );
    }

    @Test
    public void idJobReferencesJobs() throws SQLException {
        ForeignKey fkIdJob = info
                .getForeignKeys()
                .findByChildColumn(new Column("employees", "id_job"));

        Assertions.assertNotNull(fkIdJob);
        Assertions.assertTrue(fkIdJob
                .getParentColumn()
                .equals(new Column("jobs", "id"))
        );
    }

    @Test
    public void foreignKeyUsesTheNamingConvention() throws SQLException {
        ForeignKey fkIdJobs = info
                .getForeignKeys()
                .findByChildColumn(new Column("employees", "id_job"));

        Assertions.assertNotNull(fkIdJobs);
        Assertions.assertTrue(fkIdJobs
                .getName()
                .equals("fk__%s__%s".formatted(
                        fkIdJobs.getChildColumn().getTable(),
                        fkIdJobs.getParentColumn().getTable()
                ))
        );
    }
}
