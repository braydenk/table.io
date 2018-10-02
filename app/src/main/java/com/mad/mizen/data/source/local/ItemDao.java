package com.mad.mizen.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.mad.mizen.data.models.Item;
import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    LiveData<List<Item>> loadAllItems();

    @Query("SELECT * FROM item WHERE isOrdered = 1")
    LiveData<List<Item>> getOrderedItems();

    @Query("UPDATE Item SET isOrdered = 1 WHERE itemId = :id")
    void updateItemOrdered(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveItems(List<Item> items);
}
