package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class SchemaInformation {
    private Connection db;
    private String dbName;

    public SchemaInformation() {
        try {
            Config config = Config.getInstance();
            dbName = config.get("DB_NAME");
            System.out.println("db name = " + dbName);
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
        ResultSet rs = executeQuery("SELECT * FROM %s_pk", dbName);
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
        ResultSet rs = executeQuery("select * from %s_fk", dbName);
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

    public ColumnsTypesList getTypes() throws SQLException {
        ResultSet rs = executeQuery("select * from %s_types", dbName);
        List<ColumnType> list = new ArrayList<>();
        while(rs.next()) {
            ColumnType current = new ColumnType(
                    new Column(rs.getString("table"),
                            rs.getString("column")
                    ),
                    rs.getString("type"),
                    parseMaxLength(rs.getString("max_length")),
                    rs.getString("is_nullable").equals("YES")
            );
            list.add(current);
        }

        return new ColumnsTypesList(list);
    }

    private ResultSet executeQuery(String query, String... params) throws SQLException {
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(query, params));
        return rs;
    }
    private long parseMaxLength(String maxLength) {
        return maxLength == null ? -1 : Long.parseLong(maxLength);
    }
}
