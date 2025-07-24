package com.benim.model;

public class University {
    private String name;
    private String website;

    public University() {}

    public University(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() { return name; }
    public String getWebsite() { return website; }
}

