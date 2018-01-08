package com.example.sergi.beastbeers20;

import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sarias on 02/01/2018.
 */

public class BreweryAPI {
    private final String BASE_URL = "http://api.brewerydb.com/v2/";
    private final String API_KEY = "2e2e5069bca1ea80238b2eecb97fdf1f";

   /* ArrayList<Beer> getBeers(String filtro) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("beer")
                .appendQueryParameter("filtro", filtro)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }*/

    ArrayList<Categories> getCategorias() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("categories")
               // .appendQueryParameter("filtro", filtro)
                .appendQueryParameter("key", API_KEY)
                .build();
        String url = builtUri.toString();

        Log.d("URL", url);

        return doCall(url);
    }


    ArrayList<Categories> getIngredientes() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("ingredients")
               // .appendQueryParameter("filtro", filtro)
                .appendQueryParameter("key", API_KEY)
                .build();
        String url = builtUri.toString();
        Log.d("URL", url);

        return doCall(url);
    }

    private ArrayList<Categories> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ArrayList<Categories> processJson(String jsonResponse) {
        ArrayList<Categories> categories = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCategories = data.getJSONArray("data");
            Log.d("Sarias",jsonCategories.toString());
            for (int i = 0; i < jsonCategories.length(); i++) {
                JSONObject jsonCategori = jsonCategories.getJSONObject(i);

                Categories categori = new Categories();
                if(jsonCategori.has("id")){categori.setId(jsonCategori.getInt("id"));}
                if(jsonCategori.has("name")){categori.setName(jsonCategori.getString("name"));}

                categories.add(categori);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;

    }
}
