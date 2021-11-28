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
    List<Integer> listIds= new ArrayList();
    List<String> listNames= new ArrayList();
    List<String> listInstructions= new ArrayList();
    List<String> listImgs= new ArrayList();
    Map<String,List<String>> dicIngredients = new HashMap<String,List<String>>();
    Map<String,List<String>> dicMeasures = new HashMap<String,List<String>>();
    public Cocktail(String name)
    {
        this.name=name;
        this.id=id;
        setCocktailInfo(name);

    }
    public List<String> setKeyInList(List<String> list,String key) {
        boolean isEmpty=false;
        if(list.toArray().length==0)
            isEmpty=true;

        for(int i=1;i<16;i++){
            if(isEmpty)
                list.add(key+String.valueOf(i));
            else
                list.set(i-1,key+String.valueOf(i));

        }
        return list;
    }
    public List<String> removeItemInList(List<String> list,String item) {

        for(int i=list.toArray().length-1;!list.contains(null)&&i>=0;i--){
            if(list.get(i).contains(item.toString()))
                list.remove(i);
        }
        return list;
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



                    JSONArray arrayDrinks = obj.getJSONArray("drinks");



                    int test233=0;
                    List<String> listIngredients= new ArrayList();
                    List<String> listMeasures= new ArrayList();

                    //listMeasures=setKeyInList(listMeasures,"strMeasure");
                    for (int i = 0; i < arrayDrinks.length(); i++)
                    {
                        listIngredients=setKeyInList(listIngredients,"strIngredient");
                        listMeasures=setKeyInList(listMeasures,"strMeasure");
                        listIds.add(Integer.valueOf(arrayDrinks.getJSONObject(i).getString("idDrink"))) ;
                        listNames.add(arrayDrinks.getJSONObject(i).getString("strDrink")) ;
                        listInstructions.add(arrayDrinks.getJSONObject(i).getString("strInstructions")) ;
                        listImgs.add(arrayDrinks.getJSONObject(i).getString("strDrinkThumb")) ;
                        for(int j=0;j<listIngredients.toArray().length;j++){
                            listIngredients.set(j,arrayDrinks.getJSONObject(i).getString(listIngredients.get(j)));
                        }
                        listIngredients=removeItemInList(listIngredients,"null");
                        List<String> copy= new ArrayList(listIngredients);
                        dicIngredients.put(listNames.get(i),copy);
                        listIngredients.clear();

                        for(int j=0;j<listMeasures.toArray().length;j++){
                            listMeasures.set(j,arrayDrinks.getJSONObject(i).getString(listMeasures.get(j)));
                        }
                        listMeasures=removeItemInList(listMeasures,"null");
                        dicMeasures.put(listNames.get(i),listMeasures);
                        listMeasures.clear();
                        test233++;
                    }

                    int test2=0;
                    test2++;
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
