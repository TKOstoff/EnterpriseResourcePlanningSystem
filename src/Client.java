import java.time.LocalDate;

public class Client {
    private String name;
    private Project project;
    private LocalDate date;
    private LocalDate contractExpiyDate;

    public Client(String name, Project project, LocalDate date, LocalDate contractExpiyDate) {
        this.name = name;
        this.project = project;
        this.date = date;
        this.contractExpiyDate = contractExpiyDate;
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
}
