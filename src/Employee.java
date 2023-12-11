public class Employee extends User {

    public Employee() {
    }
    public Employee(String username, String password) {
        super(username, password);
        super.username = username;
        super.password = password;
    }
    @Override
    protected void login() {

    }
}
