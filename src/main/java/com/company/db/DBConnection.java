package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_db_database",
                            "java_db_user", "java_db_pswd");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void createTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "create table if not exists book(" +
                    "id serial primary key," +
                    "uuid varchar," +
                    "author varchar," +
                    "title varchar," +
                    "added_date timestamp default now()," +
                    "visible bool default true" +
                    ");" +
                    "create table if not exists student(" +
                    "id serial primary key," +
                    "uuid varchar," +
                    "firstname varchar," +
                    "lastname varchar," +
                    "phone varchar(13) unique," +
                    "added_date timestamp default now()," +
                    "visible bool default true" +
                    ");" +
                    "create table if not exists student_book(" +
                    "id serial primary key," +
                    "uuid varchar," +
                    "student_uuid varchar," +
                    "book_uuid varchar," +
                    "status varchar," +
                    "taken_date timestamp default now()" +
                    ");";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
