import java.io.*;
import java.util.Scanner;

public class DisplayStatistics {
    public static void displayReportDetails(String employeeUsername, File reportFile) {
        try (Scanner scanner = new Scanner(reportFile)) {
            System.out.println("Протокол за деня за служителя " + employeeUsername + ":");

            while (scanner.hasNextLine()) {
                String reportLine = scanner.nextLine();
                String[] reportParts = reportLine.split(":");

                if (reportParts.length == 2 && reportParts[0].trim().equalsIgnoreCase(employeeUsername)) {
                    String[] clientParts = reportParts[1].split(",");
                    if (clientParts.length == 3) {
                        String clientName = clientParts[0];
                        String duration = clientParts[1];
                        String date = clientParts[2];
                        System.out.println(clientName + ": " + duration + " минути, " + date);
                    }
                }
            }
            Menus.printStatisticsMenu();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesEmployeeNameExist(String employeeName, File reportFile) {
        try (Scanner fileScanner = new Scanner(reportFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length == 2) {
                    String storedUsername = parts[0].trim().toLowerCase();

                    if (employeeName.trim().toLowerCase().equals(storedUsername)) {
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

    public static void displayStatisticsMenu() {
        Scanner scanner = new Scanner(System.in);
        Menus.printStatisticsMenu();
        int choice = scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    System.out.print("Въведете име на служител: ");
                    String employeeName = scanner.next();
                    DisplayStatistics.displayEmployeeStatistics(employeeName);
                    break;

                case 2:
                    DisplayStatistics.searchByWeek();
                    break;

                case 3:
                    UserManager.mainMenu();
                    break;

                default:
                    System.out.println("Невалиден избор.");
            }
        }
    }

    public static void displayEmployeeStatistics(String employeeUsername) {
        String fileName = "reports.txt";
        File reportFile = new File(fileName);

        if (reportFile.exists()) {
            if (doesEmployeeNameExist(employeeUsername, reportFile)) {
                displayReportDetails(employeeUsername, reportFile);
            } else {
                System.out.println("Няма налична статистика за служителя " + employeeUsername);
            }
        } else {
            System.out.println("Няма налична статистика за служителя " + employeeUsername);
        }
    }

    public static void searchByWeek() {
        System.out.println("Търсене по седмица...");
    }
}
