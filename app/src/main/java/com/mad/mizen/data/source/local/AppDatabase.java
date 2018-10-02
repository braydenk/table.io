package com.mad.mizen.data.source.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;

// TODO: Handle migrations better.
@Database(entities = {Item.class, Order.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ItemDao itemDao();
    public abstract OrderDao orderDao();

    // TODO: This is not being used. Can it be removed due to DI modules?
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "App.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

    // TODO: This is also not being used.
    public static void destroyInstance() { INSTANCE = null; }
}
