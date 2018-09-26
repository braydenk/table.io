package com.mad.mizen.data.source;

import android.arch.lifecycle.LiveData;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.source.local.ItemDao;
import com.mad.mizen.data.source.remote.ItemsRemoteDataSource;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemRepository {

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

    private void refreshItems() {
        executor.execute(() -> {
            itemDao.saveItems(ItemsRemoteDataSource.populateData());
        });
    }
}
