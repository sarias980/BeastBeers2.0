package com.example.sergi.beastbeers20;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarias on 09/01/2018.
 */

public class DataViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final DataDAO dataDAO;
    private MutableLiveData<Boolean> loading;

    public DataViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.dataDAO = appDatabase.getDataDao();
    }

    public LiveData<List<Categories>> getCategories() {
        return dataDAO.getCategories();
    }

    public void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    public MutableLiveData<Boolean> getLoading() {
        if(loading == null){
            loading = new MutableLiveData<>();
        }
        return loading;
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Categories>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setValue(true);
        }

        @Override
        protected ArrayList<Categories> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
            String tipusConsulta = preferences.getString("type_list", "categorias");
            String filtro = preferences.getString("id", "");

            BreweryAPI api = new BreweryAPI();
            ArrayList<Categories> result;

            if (tipusConsulta.equals("categorias")) {
                result = api.getCategorias(filtro);
            } else {
                result = api.getIngredientes(filtro);
            }

            dataDAO.deleteCategories();
            dataDAO.addCategories(result);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Categories> categori) {
            super.onPostExecute(categori);
            loading.setValue(false);
        }

    }
}
