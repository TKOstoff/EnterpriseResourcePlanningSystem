import java.io.*;
import java.text.*;
import java.util.*;

class Admin extends User implements EmployeeManagement, ClientManagement {
    private List<Client> clients = new ArrayList<>();
    private Map<String, Employee> employees = new HashMap<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    void menu() {
        Scanner scanner = new Scanner(System.in);
        int adminChoice;

        while (true) {
            Menus.printAdminMenu();
            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    newClient();
                    break;

                case 2:
                    newEmployee();
                    break;

                case 3:
                    DisplayStatistics.displayStatisticsMenu();
                    break;
                case 4:
                    goBack();
                    return;
                case 0:
                    System.out.println("Излизане от програмата...");
                    break;
                default:
                    System.out.println("Грешен избор.");
            }
        }
    }

    @Override
    public void addClient(String name, String projectName, Date contractExpirationDate) {
        clients.add(new Client(name, projectName, contractExpirationDate));

        saveClientToFile(name, projectName, contractExpirationDate);

        System.out.println("Клиента с име " + name + " и проект '" + projectName +
                "' с изтичане на договора на '" + dateFormat.format(contractExpirationDate) + "' е успешно добавен.");
    }

    private void saveClientToFile(String name, String projectName, Date contractExpirationDate) {
        String fileName = "clients.txt";

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(name + "," + projectName + "," + dateFormat.format(contractExpirationDate) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newClient() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Име на клиента: ");
            String name = scanner.next();
            System.out.print("Име на проекта: ");
            String projectName = scanner.next();

            Date contractExpirationDate = getValidContractExpirationDate();
            if (contractExpirationDate != null) {
                addClient(name, projectName, contractExpirationDate);
                break;
            }
        }
    }

    private Date getValidContractExpirationDate() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Дата на изтичане на договора (dd-MM-yyyy): ");
            String contractExpirationDateStr = scanner.next();

            try {
                dateFormat.setLenient(false);
                Date contractExpirationDate = dateFormat.parse(contractExpirationDateStr);

                Date currentDate = new Date();
                if (contractExpirationDate.before(currentDate)) {
                    System.out.println("Дата не може да бъде преди текущата дата. Опитайте отново.");
                    continue;
                }

                return contractExpirationDate;
            } catch (ParseException e) {
                System.out.println("Невалидна дата. Опитайте отново.");
            }
        }
    }


    @Override
    public void registerEmployee(String username, String password) {
        if (isEmployeeUsernameUnique(username)) {
            employees.put(username, new Employee(username, password));
            System.out.println(username + " е успешно регистриран като нов служител.");

            try (FileWriter writer = new FileWriter("employees.txt", true)) {
                writer.write(username + "," + password + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Потребителското име " + username + " вече съществува. Моля, изберете друго.");
        }
    }

    private boolean isEmployeeUsernameUnique(String username) {
        return !employees.containsKey(username);
    }

    public void newEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Потребителско име на нов служител: ");
        String employeeUsername = scanner.next();
        System.out.print("Парола на нов служител: ");
        String employeePassword = scanner.next();

        registerEmployee(employeeUsername, employeePassword);
    }

    public void goBack() {
        System.out.println("Връщане...");
        System.out.println(" ");
        UserManager.mainMenu();
    }


}
