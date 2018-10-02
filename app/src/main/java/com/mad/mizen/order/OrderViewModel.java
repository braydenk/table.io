package com.mad.mizen.order;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;
import com.mad.mizen.data.source.ItemRepository;
import com.mad.mizen.menu.MenuViewModel;
import java.util.List;
import javax.inject.Inject;

public class OrderViewModel extends ViewModel {

    @SuppressWarnings("unused")
    private static final String TAG = MenuViewModel.class.getSimpleName();

    private LiveData<Order> order;
    private ItemRepository itemRepo;

    @Inject
    OrderViewModel(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void init() {
        if (this.order != null) {
            return;
        }

        order = itemRepo.getOrder();
    }

    public LiveData<Order> getOrder() { return this.order; }
}
