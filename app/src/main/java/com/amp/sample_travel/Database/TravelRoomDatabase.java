package com.amp.sample_travel.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.amp.sample_travel.Models.LocationData;

/**
 * Created by amal on 02/01/19.
 */

@Database(entities = {LocationData.class}, version = 1, exportSchema = false)
public abstract class TravelRoomDatabase extends RoomDatabase {
    private static TravelRoomDatabase INSTANCE;

    public static TravelRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TravelRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TravelRoomDatabase.class, "travel_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TravelDao dataDao();
}
