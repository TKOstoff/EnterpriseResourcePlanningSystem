import java.text.ParseException;
public interface ClientManagement {
    void addClient(String name, String projectName, String contractExpirationDate) throws ParseException;
}
