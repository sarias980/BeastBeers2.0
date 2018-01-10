package com.example.sergi.beastbeers20;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sarias on 10/01/2018.
 */
@Dao
public interface DataDAO {
    @Query("select * from categori")
    LiveData<List<Categories>> getCategories();

    @Insert
    void addCategori(Categories categori);

    @Insert
    void addCategories(List<Categories> categories);

    @Delete
    void deleteCategories(Categories categori);

    @Query("DELETE FROM categori")
    void deleteCategories();

}
