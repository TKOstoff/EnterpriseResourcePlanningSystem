public class Admin extends User{

   private List<Client> clients = new ArrayList<>();
    private Map<String, Employee> employees = new HashMap<>();

    public Admin(String username, String password) {
        super(username, password);
    }
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
