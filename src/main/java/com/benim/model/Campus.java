package com.benim.model;

import java.util.List;

public class Campus{
    private int id;
    private int universityId;
    private String name;
    private String city;
    private String district;
    private String address;
    private List<Faculty> faculties;

    public Campus(){}

    public Campus(int universityId, String name, String city, String district, String address){
        this.id = id;
        this.universityId = universityId;
        this.name =name;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public Campus(int id, int universityId, String name, String city, String district, String address){
        this.id = id;
        this.universityId = universityId;
        this.name =name;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getUniversityId(){
        return universityId;
    }
    public void setUniversityId(int universityId){
        this.universityId = universityId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName (){
        return name;
    }
    public String getDistrict(){
        return district;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public List<Faculty> getFaculties(){
        return faculties;
    }
    public void setFaculties(List<Faculty> faculties){
        this.faculties = faculties;
    }
}