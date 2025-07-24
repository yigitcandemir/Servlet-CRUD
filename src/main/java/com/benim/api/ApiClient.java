package com.benim.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiClient{
    private static final String BASE_API_URL = "http://universities.hipolabs.com/search?country=Turkey";

    public static List<University> getTurkishUniversities(String name){
        List<University> universities = new ArrayList<>();

        try {
            String apiUrl = BASE_API_URL;
            if (name != null && !name.trim().isEmpty()) {
                apiUrl += "&name=" + name.trim();
            }
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while((inputLine = in.readLine())!= null){
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            universities = gson.fromJson(response.toString(), new TypeToken<List<University>>() {}.getType());

            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return universities;


    }
}