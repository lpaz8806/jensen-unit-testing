package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchemaInformation {
    private Connection db;

    public SchemaInformation() {
        try {
            Config config = Config.getInstance();
            db = DriverManager.getConnection(
                    config.get("DB_CONNECTION"),
                    config.get("DB_USER"),
                    config.get("DB_PASS")
            );
        }
        catch(Exception e) {
            System.out.print("Error occurred: ");
            System.out.println(e);
        }
    }

    public void close() {
        try {
            db.close();
        } catch (SQLException e) {
            return;
        }
    }

    public ColumnsList getPrimaryKeys() throws SQLException {
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery("select * from jensen_hr_pk");
        List<Column> list = new ArrayList<>();
        while(rs.next()) {
            Column current = new Column(
                    rs.getString("table"),
                    rs.getString("column")
            );
            list.add(current);
        }
        return new ColumnsList(list);
    }

    public ForeignKeysList getForeignKeys() throws SQLException {
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery("select * from jensen_hr_fk");
        List<ForeignKey> list = new ArrayList<>();
        while(rs.next()) {
            ForeignKey current = new ForeignKey(
                    rs.getString("constraint_name"),
                    new Column(
                            rs.getString("parent_table"),
                            rs.getString("parent_column")
                    ),
                    new Column(
                            rs.getString("child_table"),
                            rs.getString("child_column")
                    )
            );
            list.add(current);
        }
        return new ForeignKeysList(list);
    }

    public ColumnsTypesList getTypes() {
        return null;
    }
}
