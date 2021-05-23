package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("Driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void statamentExecute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
            System.out.println("Cant execute statement!");
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s(id serial primary key);", tableName);
        statamentExecute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s;", tableName);
        statamentExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        statamentExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        statamentExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        statamentExecute(sql);
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
