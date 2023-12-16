
import java.util.*;

class Employee extends User {
    private ReportManager reportManager;

    public Employee(String username, String password) {
        super(username, password);
        this.reportManager = new ReportManager(username);
    }

    @Override
    void menu() {
        Menus.printEmployeeMenu();

        Scanner scanner = new Scanner(System.in);
        int employeeChoice = scanner.nextInt();

        switch (employeeChoice) {
            case 1:
                System.out.println("1. Създаване на протокол за деня...");
                System.out.println(" ");
                reportManager.createDailyReport();
                break;
            case 0:
                System.out.println("0. Връщане в менюто...");
                System.out.println(" ");
                UserManager.mainMenu();
                break;
            default:
                System.out.println("Грешен избор. Опитай пак!");
                System.out.println(" ");
        }
        System.out.println(" ");
    }

//това пак е от цецо
}
