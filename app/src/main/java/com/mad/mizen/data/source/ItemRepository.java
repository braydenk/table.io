package com.mad.mizen.data.source;

import android.arch.lifecycle.LiveData;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.source.local.ItemDao;
import com.mad.mizen.data.source.remote.Api;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ItemRepository {

    @SuppressWarnings("unused")
    private static final String TAG = ItemRepository.class.getSimpleName();

    private final ItemDao itemDao;
    private final Executor executor;
    private final Api api;

    @Inject
    public ItemRepository(ItemDao itemDao, Api api, Executor executor) {
        this.itemDao = itemDao;
        this.api = api;
        this.executor = executor;
    }

    public LiveData<List<Item>> getItems() {
        refreshItems();

        return itemDao.loadAllItems();
    }

    public LiveData<List<Item>> getOrderedItems() {
        return itemDao.getOrderedItems();
    }

    public void updateItemOrdered(int itemId, boolean isOrdered, int itemQuantity) {
        executor.execute(() -> itemDao.updateItemOrdered(itemId, isOrdered, itemQuantity));
    }


    private void refreshItems() {
        api.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> items = response.body();

                itemDao.saveItems(items);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}
