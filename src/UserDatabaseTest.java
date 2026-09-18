import database.UserDatabase;
import model.User;

public class UserDatabaseTest {

    public static void main(String[] args) {

        User user = new User(
                1,
                "AURA User",
                "user@example.com"
        );

        UserDatabase.saveUser(user);
    }
}