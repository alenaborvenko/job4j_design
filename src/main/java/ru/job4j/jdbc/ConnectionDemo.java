package ru.job4j.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try {
            List<String> properties = Files.readAllLines(Path.of("./src/main/java/ru/job4j/jdbc/app.properties"));
            if (properties.size() != 3) {
                throw new IllegalArgumentException("Ivalidate file!");
            }
            try (Connection connection = DriverManager.getConnection(properties.get(0), properties.get(1), properties.get(2))) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
