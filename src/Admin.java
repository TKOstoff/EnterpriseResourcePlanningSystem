import java.io.*;
import java.text.ParseException;
import java.util.*;

class Admin extends User implements EmployeeManagement, ClientManagement {
    private List<Client> clients = new ArrayList<>();
    private Map<String, Employee> employees = new HashMap<>();
   

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu(Client client) {
        Scanner scanner = new Scanner(System.in);
        int adminChoice;

        while (true) {
            System.out.println(" ");
            System.out.println("Меню за администратор:");
            System.out.println("1. Въвеждане на нови клиенти");
            System.out.println("2. Регистрация на нови служители");
            System.out.println("3. Гледане на статистика");
            System.out.println("4. Връщане");
            System.out.println("0. Излизане от програмата.");

            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    newClient();
                    break;

                case 2:
                    System.out.println(" ");
                    System.out.print("Потребителско име: ");
                    String employeeUsername = scanner.next();
                    System.out.print("Парола: ");
                    String employeePassword = scanner.next();

                    registerEmployee(employeeUsername, employeePassword);

                    break;

                case 3:
                    showStatisticsMenu();
                    break;
                case 4:
                    System.out.println("Връщане...");
                    System.out.println(" ");
                    UserManager.mainMenu();
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

            
            if (contractExpirationDate != null) {
                addClient(name, projectName, contractExpirationDate);
                break;  
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

    public void showMainMenu() {
    }

    private void showStatisticsMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Статистика:");
            System.out.println("1. Търсене по име на служител");
            System.out.println("2. Търсене по номер на седмица");
            System.out.println("3. Назад");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchByName();
                    break;

                case 2:
                    searchByWeek();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Грешен избор.");
            }
        }
    }

    private void searchByName() {
        
    }

    private void searchByWeek() {
       
    }
}
