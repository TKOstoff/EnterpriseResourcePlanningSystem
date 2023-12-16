import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class ReportManager {
    private String username;

    public ReportManager(String username) {
        this.username = username;
    }

    public List<String> getInvalidClients(String[] clients) {
        List<String> invalidClients = new ArrayList<>();
        List<String> validClients = getValidClients();

        for (String client : clients) {
            if (!validClients.contains(client)) {
                invalidClients.add(client);
            }
        }

        return invalidClients;
    }

    public void createDailyReport() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("За кои клиенти сте работили днес? (Разделете със запетаи): ");
            String[] clients = scanner.next().split(",");

            List<String> invalidClients = getInvalidClients(clients);
            if (!invalidClients.isEmpty()) {
                System.out.println("Няма клиент на име " + String.join(", ", invalidClients) + ". Опитай пак!");
            } else {
                System.out.print("По колко време на клиент? (в минути, разделете със запетаи): ");
                String[] durations = scanner.next().split(",");

                for (int i = 0; i < clients.length; i++) {
                    saveReportToFile(clients[i], Integer.parseInt(durations[i]));
                }

                System.out.println("Протоколът за деня е създаден успешно.");
                break;
            }
        }
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

    private void saveReportToFile(String client, int duration) {
        String fileName = "reports.txt";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            String currentDate = dateFormat.format(new java.util.Date());
            String line = username + ": " + client + ", " + duration + " минути, " + currentDate + "\n";
            fileWriter.write(line);
            System.out.println("Записът е успешно добавен: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//този клас е от Цецо просто нямаше възможност да го качи 
