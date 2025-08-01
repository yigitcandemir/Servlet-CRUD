package com.benim.model;

public class Department{
    private int id;
    private String name;
    private int facultyId;

    public Department(){}

    public Department(int id,int facultyId,String name){
        this.id = id;
        this.name = name;
        this.facultyId = facultyId;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getFacultyId(){
        return facultyId;
    }
    public void setFacultyId(int facultyId){
        this.facultyId = facultyId;
    }
}