public class Customer {

    private int id;
    private String username;
    private String password;

    public Customer(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
