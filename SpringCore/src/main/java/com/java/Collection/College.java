package com.java.Collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class College {

    private List<String> staffName;
    private Set<String> depName;
    private String colors[];
    Map<String,String >faculties;
    Properties capitals;

    public List<String> getStaffName() {
        return staffName;
    }

    public void setStaffName(List<String> staffName) {
        this.staffName = staffName;
    }

    public Set<String> getDepName() {
        return depName;
    }


    public void setDepName(Set<String> depName) {
        this.depName = depName;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public Map<String, String> getFaculties() {
        return faculties;
    }

    public void setFaculties(Map<String, String> faculties) {
        this.faculties = faculties;
    }

    public Properties getCapitals() {
        return capitals;
    }

    public void setCapitals(Properties capitals) {
        this.capitals = capitals;
    }

    public void getCollegeDetails(){
        System.out.println("staffName : " + staffName);
        System.out.println("depName : " + depName);
        System.out.println("colors : " + colors[0] +":" + colors[1]);
        System.out.println("faculties : " + faculties);
        System.out.println("staffName : " + capitals);
    }


}
