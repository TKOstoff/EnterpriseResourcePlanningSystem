public class Project {
    private String projectName;

    public Project(String projectName){
        this.projectName = projectName;
    }

    public String getName() {
        return projectName;
    }

    @Override
    public String toString() {
        return projectName;
    }
}
