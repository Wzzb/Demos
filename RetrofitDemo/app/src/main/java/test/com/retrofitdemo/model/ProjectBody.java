package test.com.retrofitdemo.model;

import java.util.List;

/**
 * Created by H235952 on 2018/3/19.
 */

public class ProjectBody {
    private List<ProjectBean> projects;

    public List<ProjectBean> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectBean> projects) {
        this.projects = projects;
    }
}
