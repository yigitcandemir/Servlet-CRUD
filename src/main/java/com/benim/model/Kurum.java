package com.benim.model;

public class Kurum{
    private int id;
    private String name;
    private String type;
    private String city;
    private String website;

    public Kurum(){}

    public Kurum(int id, String name,String type, String city,String website){
        this.id = id;
        this.name = name;
        this.type = type;
        this.city = city;
        this .website = website;
    }

    public int getId (){
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

    public String getCity(){
        return name;
    }
    
    public void setCity(String city){
        this.city = city;
    }

    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }

    public String getWebsite(){
        return website;
    }
    
    public void setWebsite(String website){
        this.website = website;
    }
}