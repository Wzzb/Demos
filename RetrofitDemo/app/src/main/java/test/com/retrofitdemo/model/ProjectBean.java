package test.com.retrofitdemo.model;

public class ProjectBean {

    private int id;
    private String projectname;
    private String url;

    public ProjectBean() {
        super();
    }

    public ProjectBean(int id, String projName, String imgName) {
        super();
        this.id = id;
        this.projectname = projName;
        this.url = imgName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"projectname\":\"" + projectname + "\",\"url\":\"" + url
                + "\"}";
    }
}
