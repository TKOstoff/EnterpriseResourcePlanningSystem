import java.util.*;
import java.io.*;
class UserManager {
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Въведете тип на потребителя (admin/employee): ");
        String userType = scanner.next();


        if (userType.equals("admin")) {
            checkAdmin();
        } else if (userType.equals("employee")) {
            checkEmployee();
        } else {
            System.out.println("Невалиден тип на потребителя. Опитай отново.");
            System.out.println(" ");
            mainMenu();
        }
    }

    private static void checkAdmin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Потребителско име: ");
        String adminUsername = scanner.next();
        System.out.print("Парола: ");
        String adminPassword = scanner.next();

        if ("admin".equals(adminUsername) && "admin".equals(adminPassword)) {
            Admin admin = new Admin(adminUsername, adminPassword);
            admin.menu();
        } else {
            System.out.println("Невалидни потребителско име или парола. Опитай отново.");
            System.out.println(" ");
            checkAdmin();
        }
    }

    private static void checkEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Потребителско име: ");
        String employeeUsername = scanner.next();
        System.out.print("Парола: ");
        String employeePassword = scanner.next();

        if ("employee".equals(employeeUsername) && "employee".equals(employeePassword)) {
            Employee employee = new Employee(employeeUsername, employeePassword);
            employee.menu();
        } else if ("admin".equals(employeeUsername) && "admin".equals(employeePassword)) {
            Admin admin = new Admin(employeeUsername, employeePassword);
            admin.menu();
        } else {
            if (loginAsEmployee(employeeUsername, employeePassword)) {
                return;
            }

            System.out.println("Невалидни потребителско име или парола. Опитай отново.");
            System.out.println(" ");
            checkEmployee();
        }
    }

    private static boolean loginAsEmployee(String username, String password) {
        String fileName = "employees.txt";

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        Employee employee = new Employee(username, password);
                        employee.menu();
                        return true;
                    }
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    }
