package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;

public class UserDatabase {

    public static void saveUser(User user) {

        String sql =
                "INSERT INTO users (name, email) VALUES (?, ?)";

        try (
            Connection connection =
                    DatabaseManager.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();

            System.out.println(
                    "AURA: User saved to database successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "AURA: Unable to save user."
            );

            e.printStackTrace();
        }
    }
}