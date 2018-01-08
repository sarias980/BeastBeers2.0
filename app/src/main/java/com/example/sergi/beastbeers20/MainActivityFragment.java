package com.example.sergi.beastbeers20;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Categories> items;
    private ArrayAdapter<Categories> adapter;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvBeer = (ListView) view.findViewById(R.id.lvBeer);

        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_beer_row,
                R.id.tvName,
                items
        );
        lvBeer.setAdapter(adapter);


        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Categories>> {
        @Override
        protected ArrayList<Categories> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String tipusConsulta = preferences.getString("type_list", "categorias");
            //String filtro = preferences.getString("id", "");

            BreweryAPI api = new BreweryAPI();
            ArrayList<Categories> result;

            if (tipusConsulta.equals("categorias")) {
                result = api.getCategorias();
            } else {
                result = api.getIngredientes();
            }

            if (result != null){Log.d("Sarias",result.toString());}
            //Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Categories> categories) {
            adapter.clear();
            for (Categories categori : categories) {
                adapter.add(categori);
            }

        }
    }
}
