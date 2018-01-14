package com.example.sergi.beastbeers20;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
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
public class DetailActivityFragment extends LifecycleFragment {

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
            Categories categorie = (Categories) i.getSerializableExtra("categorie");
            if (categorie != null) {
                updateUi(categorie);
            }
        }

        SharedViewModel sharedModel = ViewModelProviders.of(
                getActivity()
        ).get(SharedViewModel.class);
        sharedModel.getSelected().observe(this, new Observer<Categories>() {
            @Override
            public void onChanged(@Nullable Categories categori) {
                updateUi(categori);
            }
        });

        return view;
    }

    private void updateUi(Categories categorie) {
        Log.d("Categories", categorie.toString());

        binding.tvNameDe.setText(categorie.getName());
        binding.tvIdDe.setText(Integer.toString(categorie.getId()));
        if (categorie.getDesc() != null) {
            binding.tvDescDe.setText(categorie.getDesc());
        } else {
            binding.tvDescDe.setText("Not found more info");
        }
    }

}

