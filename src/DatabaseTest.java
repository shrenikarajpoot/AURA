import java.sql.Connection;
import database.DatabaseManager;

public class DatabaseTest {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseManager.getConnection();

            System.out.println(
                    "AURA: Database connected successfully!"
            );

            connection.close();

        } catch (Exception e) {

            System.out.println(
                    "AURA: Database connection failed."
            );

            e.printStackTrace();
        }
    }
}
