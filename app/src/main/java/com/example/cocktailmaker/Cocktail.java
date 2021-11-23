package com.example.cocktailmaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cocktail {
    String name;
    int id;

    public Cocktail(String name)
    {
        this.name=name;
        this.id=id;
        setCocktailInfo("vodka");
    }

    public String getNom() {
        return name;
    }
    public void setNom(String nom) {
        this.name = name;
    }


    public void setCocktailInfo(String name) {
        try{
            URL urlForGetRequest = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+name);


            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            conection.setRequestProperty("host", "www.thecocktaildb.com");
            int responseCode = conection.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();

                String jsonRep = response.toString();
                JSONObject obj = null;
                try {
                    obj = new JSONObject(jsonRep);
                    String Drinks = obj.getString("drinks");



                    JSONArray arrayHit = obj.getJSONArray("drinks");




                    List<String> listSearch= new ArrayList(); ;
                    for (int i = 0; i < arrayHit.length(); i++)
                    {
                        this.id =Integer.valueOf(arrayHit.getJSONObject(i).getString("id")) ;


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
