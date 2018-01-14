package com.example.sergi.beastbeers20;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Sergi on 13/01/2018.
 */

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Categories> selected = new MutableLiveData<Categories>();

    public void select(Categories categori) {
        selected.setValue(categori);
    }

    public LiveData<Categories> getSelected() {
        return selected;
    }
}

