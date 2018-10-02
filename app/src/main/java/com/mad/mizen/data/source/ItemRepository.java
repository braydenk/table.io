package com.mad.mizen.data.source;

import android.arch.lifecycle.LiveData;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import com.mad.mizen.data.source.local.ItemDao;
import com.mad.mizen.data.source.local.OrderDao;
import com.mad.mizen.data.source.remote.ItemsRemoteDataSource;
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
    private final OrderDao orderDao;
    private final Executor executor;

    @Inject
    public ItemRepository(ItemDao itemDao, OrderDao orderDao, Executor executor) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.executor = executor;
    }

    public LiveData<List<Item>> getItems() {
        refreshItems();

        return itemDao.loadAllItems();
    }

    public LiveData<Order> getOrder() {
        return orderDao.loadOrder();
    }

    public void addItemToOrder(Item item) {
        List<Item> items = new ArrayList<>();
        items.add(item);

        Order order = new Order(0, 0, 16);
        executor.execute(() -> orderDao.createOrder(order));
    }

    private void refreshItems() {
        // TODO: Change to a static call.
        ItemsRemoteDataSource itemsRemoteDataSource = new ItemsRemoteDataSource();
        executor.execute(() -> itemDao.saveItems(itemsRemoteDataSource.populateData()));
    }
}
