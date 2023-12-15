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
            printMenu();
            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    newClient();
                    break;

                case 2:
                    newEmployee();
                    break;

                case 3:
                    displayStatisticsMenu();
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

    public void printMenu() {
        System.out.println(" ");
        System.out.println("Меню за администратор:");
        System.out.println("1. Въвеждане на нови клиенти");
        System.out.println("2. Регистрация на нови служители");
        System.out.println("3. Гледане на статистика");
        System.out.println("4. Връщане");
        System.out.println("0. Излизане от програмата.");
    }


    public void goBack() {
        System.out.println("Връщане...");
        System.out.println(" ");
        UserManager.mainMenu();
    }


    private File[] getMatchingReportFiles(String fileNamePattern) {
        File dir = new File(".");
        return dir.listFiles((dir1, name) -> name.matches(fileNamePattern));
    }

    private void displayReportDetails(String employeeUsername, File reportFile) {
        try (Scanner scanner = new Scanner(reportFile)) {
            System.out.println("Протокол за деня от " + extractDateFromFileName(reportFile.getName()) + ":");
            String reportLine = scanner.nextLine();
            String[] reportParts = reportLine.split("-");

            if (reportParts.length == 2 && reportParts[0].equals(employeeUsername)) {
                String[] clientParts = reportParts[1].split(",");
                if (clientParts.length == 2) {
                    String clientName = clientParts[0].trim();
                    String duration = clientParts[1].trim();
                    System.out.println(clientName + ": " + duration + " минути");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private String extractDateFromFileName(String fileName) {
        String[] parts = fileName.split("_");
        if (parts.length >= 4) {
            return parts[3].substring(0, parts[3].lastIndexOf('.'));
        }
        return "";
    }

    private void displayStatisticsMenu() {
        Scanner scanner = new Scanner(System.in);
        printStatisticsMenu();
        int choice = scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    System.out.print("Въведете име на служител: ");
                    String employeeName = scanner.next();
                    displayEmployeeStatistics(employeeName);
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

    public void printStatisticsMenu() {
        System.out.println("Статистика:");
        System.out.println("1. Търсене по име на служител");
        System.out.println("2. Търсене по номер на седмица");
        System.out.println("3. Назад");
    }

    private boolean doesEmployeeNameExists(String employeeName, File reportFile) {
        try (Scanner fileScanner = new Scanner(reportFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("-");

                if (parts.length == 2) {
                    String storedUsername = parts[0].toLowerCase(); 

                    if (employeeName.toLowerCase().equals(storedUsername)) {
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

    private void displayEmployeeStatistics(String employeeUsername) {
        String fileName = "reports.txt";
        File reportFile = new File(fileName);

        if (reportFile.exists()) {
            if (doesEmployeeNameExists(employeeUsername, reportFile)) {
                displayReportDetails(employeeUsername, reportFile);
            } else {
                System.out.println("Няма налична статистика за служителя " + employeeUsername);
            }
        } else {
            System.out.println("Няма налична статистика за служителя " + employeeUsername);
        }
    }
    private void searchByWeek() {
        System.out.println("Търсене по седмица...");
    }
}
