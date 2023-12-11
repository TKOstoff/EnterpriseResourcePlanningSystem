public class Admin extends User{

   private Map<String, Project> projects;
    private List<Client> clients;
    private List<Employee> employees;
    private List<String> registeredEmployees;
    private boolean isAdmin;

    public Admin(String username, String password) {
        super(username, password);
        this.projects = new HashMap<>();
        this.clients = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.registeredEmployees = new ArrayList<>();
        this.isAdmin = true;
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
