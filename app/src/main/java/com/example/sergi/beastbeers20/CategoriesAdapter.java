package com.example.sergi.beastbeers20;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sergi.beastbeers20.databinding.LvBeerRowBinding;

import java.util.List;

/**
 * Created by Sarias on 08/01/2018.
 */

public class CategoriesAdapter extends ArrayAdapter<Categories> {
    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull List<Categories> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Categories categorie = getItem(position);
        Log.w("XXXX", categorie.toString());

        LvBeerRowBinding binding = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_beer_row, parent, false);
        } else{
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.tvName.setText(categorie.getName());
        binding.tvId.setText(Integer.toString(categorie.getId()));

        return binding.getRoot();
    }

}
