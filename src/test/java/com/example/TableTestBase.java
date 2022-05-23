package com.example;

import com.example.demo.Config;
import com.example.demo.SchemaInformation;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class TableTestBase {
    protected final SchemaInformation info;

    protected TableTestBase() throws SQLException {
        this(
                Config.getInstance().get("DB_CONNECTION"),
                Config.getInstance().get("DB_USER"),
                Config.getInstance().get("DB_CONNECTION")
        );
    }
    protected TableTestBase(String connectionString, String user, String pass) throws SQLException {
        this.info = new SchemaInformation(DriverManager.getConnection(
                connectionString,
                user,
                pass
        ));
    }
}
