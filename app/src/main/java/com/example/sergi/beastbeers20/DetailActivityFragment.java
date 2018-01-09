package com.example.sergi.beastbeers20;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergi.beastbeers20.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;
    private FragmentDetailBinding binding;


    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater);
        view = binding.getRoot();


        Intent i = getActivity().getIntent();

        if (i != null) {
            Categories categorie = (Categories) i.getSerializableExtra("Categories");

            if (categorie != null) {
                updateUi(categorie);
            }
        }

        return view;
    }

    private void updateUi(Categories categorie) {
        Log.d("Categories", categorie.toString());

        binding.tvName.setText(categorie.getName());
        binding.tvId.setText(categorie.getId());
        binding.tvDesc.setText(categorie.getDesc());
    }

}

