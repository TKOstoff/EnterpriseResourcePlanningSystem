import java.util.*;
import java.io.*;

class Employee extends User {
    Map<String, Integer> dailyLog = new HashMap<>();
    private int reportCounter = 1;

    public Employee(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println(" ");
        System.out.println("Меню за служител:");
        System.out.println("1. Създаване на протокол за деня.");
        System.out.println("0. Връщане в менюто.");

        Scanner scanner = new Scanner(System.in);
        int employeeChoice = scanner.nextInt();

        switch (employeeChoice) {
            case 1:
                System.out.println("1. Създаване на протокол за деня...");
                System.out.println(" ");
                createDailyReport();
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

    public String getPassword() {
        return super.getPassword();
    }

    private boolean areClientsValid(String[] clients) {
        List<String> validClients = getValidClients();
        for (String client : clients) {
            if (!validClients.contains(client)) {
                return false;
            }
        }
        return true;
    }

    private void createDailyReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("За кои клиенти сте работили днес? (Разделете със запетаи): ");
        String[] clients = scanner.next().split(",");

        String invalidClient = getInvalidClient(clients);

        if (invalidClient != null) {
            System.out.println("Няма клиент на име " + invalidClient + ". Опитай пак!");
            createDailyReportInternal();
            return;
        }

        System.out.print("По колко време на клиент? (в минути, разделете със запетаи): ");
        String[] durations = scanner.next().split(",");

        for (int i = 0; i < clients.length; i++) {
            dailyLog.put(clients[i], Integer.parseInt(durations[i]));
        }

        saveDailyReportToFile();

        System.out.println("Протоколът за деня е създаден успешно.");
        menu();
    }
    private void createDailyReportInternal() {
        createDailyReport();
    }

    private String getInvalidClient(String[] clients) {
        List<String> validClients = getValidClients();
        for (String client : clients) {
            if (!validClients.contains(client)) {
                return client;
            }
        }
        return null;
    }




    private List<String> getValidClients() {
        List<String> validClients = new ArrayList<>();
        String fileName = "clients.txt";

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 1) {
                    validClients.add(parts[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return validClients;
    }

    private void saveDailyReportToFile() {
        String fileName = "report_from_" + getUsername() + "_" + generateReportNumber() + ".txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Протокол за деня от " + getCurrentDate() + ":");
            for (Map.Entry<String, Integer> entry : dailyLog.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue() + " минути");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int generateReportNumber() {
        return reportCounter++;
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        return currentDate.toString();
    }
}
