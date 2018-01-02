package com.example.sergi.beastbeers20;

import android.net.Uri;

import java.io.IOException;

/**
 * Created by Sarias on 02/01/2018.
 */

public class BreweryAPI {
    private final String BASE_URL = "http://api.brewerydb.com/v2/";
    private final String API_KEY = "2e2e5069bca1ea80238b2eecb97fdf1f";

    String getBeers( String filtro) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("beer")
                .appendQueryParameter("filtro", filtro)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    String getCategorias(String filtro) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("categories")
                .appendQueryParameter("filtro", filtro)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    private String doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
