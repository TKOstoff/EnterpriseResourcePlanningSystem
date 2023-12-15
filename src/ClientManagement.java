import java.text.ParseException;
import java.util.Date;

public interface ClientManagement {
    void addClient(String name, String projectName, String contractExpirationDate) throws ParseException;

    void addClient(String name, String projectName, Date contractExpirationDate);
}
