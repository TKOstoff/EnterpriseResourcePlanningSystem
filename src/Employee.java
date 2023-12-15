public class Employee extends User {
    private String name;
    private double hoursWorked;

    public Employee(String username, String password) {
        super(username, password);
        super.setUsername(username);
        super.setPassword(password);
    }

    public Employee() {

    }
    public Employee(String name, String username, String password, double hoursWorked) {
        super(username, password);
        this.name = name;
        super.setUsername(username);
        super.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    protected void menu() {

    }
}
