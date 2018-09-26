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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveItems(List<Item> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveItem(Item item);
}
