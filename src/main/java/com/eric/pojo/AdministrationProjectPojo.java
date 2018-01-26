package com.eric.pojo;

import java.util.List;

public class AdministrationProjectPojo {
    private List<ProjectOnePojo> projectOnePojoList;
    private List<ProjectTwoPojo> projectTwoPojoList;

    public List<ProjectOnePojo> getProjectOnePojoList() {
        return projectOnePojoList;
    }

    public void setProjectOnePojoList(List<ProjectOnePojo> projectOnePojoList) {
        this.projectOnePojoList = projectOnePojoList;
    }

    public List<ProjectTwoPojo> getProjectTwoPojoList() {
        return projectTwoPojoList;
    }

    public void setProjectTwoPojoList(List<ProjectTwoPojo> projectTwoPojoList) {
        this.projectTwoPojoList = projectTwoPojoList;
    }
}
