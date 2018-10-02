package com.mad.mizen.menu;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.source.ItemRepository;
import java.util.List;
import javax.inject.Inject;

public class MenuViewModel extends ViewModel {

    @SuppressWarnings("unused")
    private static final String TAG = MenuViewModel.class.getSimpleName();

    private LiveData<List<Item>> items;
    private ItemRepository itemRepo;

    @Inject
    MenuViewModel(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void init() {
        if (this.items != null) {
            return;
        }

        items = itemRepo.getItems();
    }

    public LiveData<List<Item>> getItems() {
        return this.items;
    }

    public void addItemToOrder(Item item) {
        itemRepo.updateItemOrdered(item.getItemId());
    }
}
