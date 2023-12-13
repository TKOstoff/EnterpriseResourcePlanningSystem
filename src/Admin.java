import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin extends User{

   private Map<String, Project> projects;
    private List<Client> clients;
    private List<Employee> employees;
    private List<String> registeredEmployees;
    private boolean isAdmin;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    public Admin(String username, String password) {
        super(username, password);
        this.projects = new HashMap<>();
        this.clients = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.registeredEmployees = new ArrayList<>();
        this.isAdmin = true;
        super.username = ADMIN_USERNAME;
        super.password = ADMIN_PASSWORD;
      
   private List<Client> clients = new ArrayList<>();
    private Map<String, Employee> employees = new HashMap<>();


    @Override
    void menu() {
        
    }


    @Override
    public void addClient(String name, String projectName, String contractExpirationDate) {
        System.out.println("Име на клиента: " + name);
        System.out.println("Име на проекта: " + projectName);
        System.out.println("Дата на изтичане на договора: " + contractExpirationDate);

        clients.add(new Client(name, projectName, contractExpirationDate));

        System.out.println("Клиента с име " + name + " и проект '" + projectName +
                "' с изтичане на договора на '" + contractExpirationDate + "' е успешно добавен.");
    }

    @Override
    public void registerEmployee(String username, String password) {
        if (isEmployeeUsernameUnique(username)) {
            System.out.println(" ");
            System.out.println("Потребителско име: " + username);
            System.out.println("Парола: " + password);

            employees.put(username, new Employee(username, password));

            System.out.println(username + " е успешно регистриран като нов служител.");
        } else {
            System.out.println("Потребителското име " + username + " вече съществува. Моля, изберете  друго.");
        }
    }
    private boolean isEmployeeUsernameUnique(String username) {
        return !employees.containsKey(username);
    }
  

public void showMainMenu{

    }
    private void showStatisticsMenu() {
        
    }

    private void searchByName() {
       
    }

    private void searchByWeek() {
       
    }
}
