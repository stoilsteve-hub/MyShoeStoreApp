/*
 * Make sure you have the MySQL JDBC driver added to your project's dependencies.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/MyShoeStore";

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Warning: Could not load .env file. Ensure it exists in the project root.");
            throw e;
        }

        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");

        if (user == null || password == null) {
            throw new RuntimeException("DB_USER or DB_PASSWORD not found in .env file");
        }

        return DriverManager.getConnection(URL, user, password);
    }
}
