package com.mad.mizen.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.mad.mizen.data.models.Item;
import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    LiveData<List<Item>> loadAllItems();

    @Query("SELECT * FROM item WHERE isOrdered = 1")
    LiveData<List<Item>> getOrderedItems();

    @Query("UPDATE Item SET isOrdered = :isOrdered, quantity = :quantity WHERE itemId = :id")
    void updateItemOrdered(int id, boolean isOrdered, int quantity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveItems(List<Item> items);
}
