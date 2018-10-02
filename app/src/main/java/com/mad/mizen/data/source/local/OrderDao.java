package com.mad.mizen.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `Order`")
    LiveData<Order> loadOrder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createOrder(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveItems(List<Item> items);
}
