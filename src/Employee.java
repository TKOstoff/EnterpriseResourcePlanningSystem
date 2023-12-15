public class Employee extends User {
    private double hoursWorked;
    public Employee() {
    }
    public Employee(String username, String password) {
        super(username, password);
        super.username = username;
        super.password = password;
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
