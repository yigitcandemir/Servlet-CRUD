package com.benim.model;

import java.util.List;

public class Faculty{
    private int id;
    private  String name;
    private int campusId;
    private  String telephone;
    private  String dean;
    private List<Department> departments;

    public Faculty(){}

    public Faculty(int id, String name, int campusId, String telephone, String dean){
        this.id = id;
        this.name = name;
        this.campusId = campusId;
        this.telephone = telephone;
        this.dean = dean;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setCampusId(int campusId){
        this.campusId = campusId;
    }
    public int getCampusId(){
        return campusId;
    }
    public void setTelephone (String telephone){
        this.telephone = telephone;
    }
    public String getTelephone(){
        return telephone;
    }
    public void setDean (String dean){
        this.dean = dean;
    }
    public String getDean(){
        return dean;
    }
    public List<Department> getDepartments(){
        return departments;
    }
    public void setDepartments(List<Department> departments){
        this.departments = departments;
    }
}