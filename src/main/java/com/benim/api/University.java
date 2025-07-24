package com.benim.api;


//Apiden gelen verileri görmek için
public class University {
    private String name;
    private String[] web_pages;
    private String[] domains;

    public String getName(){
        return name;
    }
    public String getWebsite (){
        if (web_pages != null && web_pages.length > 0)
            return web_pages[0];
        else
            return "";
    }
}
