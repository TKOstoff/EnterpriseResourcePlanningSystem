import java.util.Scanner;

public class Employee extends User {
    private String name;
    private Client client;
    private double hoursWorked;
    Scanner scanner = new Scanner(System.in);

    public Employee(String username, String password) {
        super(username, password);
        super.setUsername(username);
        super.setPassword(password);
    }

    public Employee() {

    }

    public Employee(String name, String username, String password, double hoursWorked, Client client) {
        super(username, password);
        this.name = name;
        super.setUsername(username);
        super.setPassword(password);
        this.client = client;
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
    public void menu(Client client) {
        int employeeChoice;
        while (true) {
            System.out.println(" ");
            System.out.println("Меню за служител:");
            System.out.println("1. Създаване на протокол за деня");
            System.out.println("2. Назад.");
            System.out.println("0. Изход.");

            employeeChoice = scanner.nextInt();

            switch (employeeChoice) {
                case 1:
                    createDailyProtocol(this.client);
                    break;

                case 2:
                    UserManager.mainMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Грешен избор.");
            }
        }


    }

    private void createDailyProtocol(Client client) {
        System.out.println("За кой клиент работихте днес?");
        String clientName = scanner.nextLine();
        System.out.println("Колко време работихе за избрания клиент?");
        this.hoursWorked = scanner.nextDouble();
    }
}
