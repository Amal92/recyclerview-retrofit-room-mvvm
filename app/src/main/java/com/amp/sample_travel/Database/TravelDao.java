package com.amp.sample_travel.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.amp.sample_travel.Models.LocationData;

import java.util.List;

/**
 * Created by amal on 02/01/19.
 */

@Dao
public interface TravelDao {

    @Query("SELECT * from travel_table")
    List<LocationData> getAllSavedData();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationData locationData);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<LocationData> locationDataList);

}
