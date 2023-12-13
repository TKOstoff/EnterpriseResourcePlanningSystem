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
    }
    @Override
    public void saveDataToFile(String filePath) {
       
    }
     @Override
    public void loadDataFromFile(String filePath) {
        
    }
     @Override
    public void addClient(String name, String projectName, String contractExpirationDate) throws ParseException {
        
    }

    @Override
    protected void login() {
    }
}
