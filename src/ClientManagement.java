import java.text.ParseException;
import java.util.Date;

public interface ClientManagement {
    void addClient(String name, String projectName, Date contractExpirationDate);
}
