package com.mad.mizen.order;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import com.mad.mizen.data.source.ItemRepository;
import com.mad.mizen.menu.MenuViewModel;
import java.util.List;
import javax.inject.Inject;

public class OrderViewModel extends ViewModel {

    @SuppressWarnings("unused")
    private static final String TAG = MenuViewModel.class.getSimpleName();

    private LiveData<List<Item>> orderedItems;
    private ItemRepository itemRepo;

    @Inject
    OrderViewModel(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void init() {
        if (this.orderedItems != null) {
            return;
        }

        orderedItems = itemRepo.getOrderedItems();
    }

    public LiveData<List<Item>> getOrder() { return this.orderedItems; }
}
