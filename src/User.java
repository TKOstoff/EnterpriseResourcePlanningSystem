abstract class User {
    protected  String username;
    protected  String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected abstract void menu();
}