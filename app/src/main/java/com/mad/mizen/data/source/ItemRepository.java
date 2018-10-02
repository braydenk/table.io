package com.mad.mizen.data.source;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.persistence.room.OnConflictStrategy;
import android.util.Log;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import com.mad.mizen.data.source.local.ItemDao;
import com.mad.mizen.data.source.local.OrderDao;
import com.mad.mizen.data.source.remote.ItemsRemoteDataSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemRepository {

    @SuppressWarnings("unused")
    private static final String TAG = ItemRepository.class.getSimpleName();

    private final ItemDao itemDao;
    private final Executor executor;

    @Inject
    public ItemRepository(ItemDao itemDao, Executor executor) {
        this.itemDao = itemDao;
        this.executor = executor;
    }

    public LiveData<List<Item>> getItems() {
        refreshItems();

        return itemDao.loadAllItems();
    }

    public LiveData<List<Item>> getOrderedItems() {
        return itemDao.getOrderedItems();
    }

    public void updateItemOrdered(int itemId, int itemQuantity) {
        executor.execute(() -> itemDao.updateItemOrdered(itemId, itemQuantity));
    }


    private void refreshItems() {
        // TODO: Change to a static call.
        ItemsRemoteDataSource itemsRemoteDataSource = new ItemsRemoteDataSource();
        executor.execute(() -> itemDao.saveItems(itemsRemoteDataSource.populateData()));
    }
}
