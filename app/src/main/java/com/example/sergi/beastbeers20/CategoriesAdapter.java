package com.example.sergi.beastbeers20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by Sarias on 08/01/2018.
 */

public class CategoriesAdapter extends ArrayAdapter<Categories> {
    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull Categories[] objects) {
        super(context, resource, objects);
    }
}
