package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/aura_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "";

    public static Connection getConnection()
            throws SQLException {

        DriverManager.registerDriver(new Driver());

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}