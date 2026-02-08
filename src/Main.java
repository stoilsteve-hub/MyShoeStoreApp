import java.sql.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            Customer customer = login(conn);
            if (customer == null) {
                System.out.println("Login failed.");
                return;
            }

            List<Product> products = getProducts(conn);

            System.out.println("\nAvailable products:");
            for (Product p : products) {
                System.out.println(p);
            }

            System.out.print("\nChoose product ID: ");
            int productId = scanner.nextInt();

            callAddToCart(conn, customer.getId(), productId);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // LOGIN
    static Customer login(Connection conn) throws Exception {

        System.out.print("Username: ");
        String user = scanner.nextLine();

        System.out.print("Password: ");
        String pass = scanner.nextLine();

        String sql =
                "SELECT * FROM Customer WHERE username=? AND password=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Login successful!");
            return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }

        return null;
    }

    // PRODUCT LIST
    static List<Product> getProducts(Connection conn) throws Exception {

        List<Product> list = new ArrayList<>();

        String sql =
                "SELECT product_id, model_name, price FROM Product WHERE stock > 0";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3)
            ));
        }

        return list;
    }

    // STORED PROCEDURE CALL
    static void callAddToCart(Connection conn,
                              int customerId,
                              int productId) throws Exception {

        CallableStatement cs =
                conn.prepareCall("{CALL AddToCart(?, ?, ?)}");

        cs.setInt(1, customerId);
        cs.setNull(2, Types.INTEGER); // let DB pick active order
        cs.setInt(3, productId);

        cs.execute();

        System.out.println("Product added to cart!");
    }
}
