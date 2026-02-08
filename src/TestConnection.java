import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("--- Database Connection Test ---");

        // 1. Check for JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[OK] MySQL JDBC Driver found.");
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] MySQL JDBC Driver NOT found!");
            System.out.println("Please add the mysql-connector-j dependency to your project.");
            return;
        }

        // 2. Check Connection
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("[OK] Connection to 'MyShoeStore' successful!");
            } else {
                System.out.println("[ERROR] Connection failed (conn is null).");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Connection failed!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Check your URL, username, password, and ensure MySQL is running.");
            e.printStackTrace();
        }
    }
}
