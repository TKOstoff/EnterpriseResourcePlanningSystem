public class Admin extends User{

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
        super.username = username;
        super.password = password;
    }

    @Override
    protected void login() {
    }
}
