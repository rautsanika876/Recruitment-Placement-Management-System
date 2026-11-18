
package com.rpms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static final String URL =
            System.getenv().getOrDefault(
                    "RPMS_DB_URL",
                    "jdbc:mysql://localhost:3306/rpms_db?useSSL=false&serverTimezone=UTC"
            );

    private static final String USER =
            System.getenv("RPMS_DB_USER");

    private static final String PASSWORD =
            System.getenv("RPMS_DB_PASSWORD");

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {

        if (USER == null || PASSWORD == null) {
            throw new SQLException(
                    "Database environment variables are not configured!"
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
