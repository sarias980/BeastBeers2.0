package com.example.sergi.beastbeers20;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import com.example.sergi.beastbeers20.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends LifecycleFragment {

    private ArrayList<Categories> items;
    private CategoriesAdapter adapter;
    private FragmentMainBinding binding;
    private DataViewModel model;
    private SharedViewModel sharedModel;
    private ProgressDialog dialog;

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
        binding = FragmentMainBinding.inflate(inflater);
        View view = binding.getRoot();


        items = new ArrayList<>();
        adapter = new CategoriesAdapter(
                getContext(),
                R.layout.lv_beer_row,
                items
        );

        sharedModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        binding.lvBeer.setAdapter(adapter);

        binding.lvBeer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categories categori = (Categories) adapterView.getItemAtPosition(i);

                if (!esTablet()) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("categorie",categori);
                    startActivity(intent);
                } else {
                    sharedModel.select(categori);
                }
            }
        });

        model = ViewModelProviders.of(this).get(DataViewModel.class);
        model.getCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(@Nullable List<Categories> categori) {
                adapter.clear();
                adapter.addAll(categori);
            }
        });

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");

        model.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean mostrat) {
                if(mostrat)
                    dialog.show();
                else
                    dialog.dismiss();
            }
        });

        return view;
    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
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
    }

    private void refresh() {
        model.refresh();
    }
}
