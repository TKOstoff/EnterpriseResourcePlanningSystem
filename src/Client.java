import java.time.LocalDate;
import java.util.Date;

public class Client {
    private String name;
    private Project project;
    private LocalDate date;
    private Date contractExpirationDate;

    public Client(String name, Project project, LocalDate date, Date contractExpirationDate) {
        this.name = name;
        this.project = project;
        this.date = date;
        this.contractExpirationDate = contractExpirationDate;
    }

    public Client(String name, String projectName, Date contractExpirationDate) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(Date contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }
}

