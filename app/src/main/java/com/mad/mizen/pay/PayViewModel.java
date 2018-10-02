package com.mad.mizen.pay;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.source.ItemRepository;
import java.util.List;
import javax.inject.Inject;

public class PayViewModel extends ViewModel {

    private LiveData<List<Item>> orderedItems;
    private ItemRepository itemRepository;

    @Inject
    PayViewModel(ItemRepository itemRepository) { this.itemRepository = itemRepository; }

    public void init() {
        if (this.orderedItems != null) {
            return;
        }

        orderedItems = itemRepository.getOrderedItems();
    }

    public LiveData<List<Item>> getOrder() { return this.orderedItems; }
}
