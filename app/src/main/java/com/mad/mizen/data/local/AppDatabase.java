package com.mad.mizen.data.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import com.mad.mizen.data.models.Staff;
import com.mad.mizen.data.models.Table;

@Database(entities = {Item.class, Order.class, Staff.class, Table.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ItemDao itemDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "App.db")
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() { INSTANCE = null; }
}
