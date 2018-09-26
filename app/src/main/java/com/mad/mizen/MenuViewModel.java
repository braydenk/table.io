package com.mad.mizen;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.source.ItemRepository;
import java.util.List;
import javax.inject.Inject;

public class MenuViewModel extends ViewModel {

    private LiveData<List<Item>> items;
    private ItemRepository itemRepo;

    @Inject
    public MenuViewModel(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void init() {
        if (this.items != null) {
            return;
        }

        items = itemRepo.getItems();
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }
}
