package com.mad.mizen.data.source.remote;

import com.mad.mizen.data.models.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemsRemoteDataSource {

    public static List<Item> populateData() {
        List<Item> items = new ArrayList<>();

        items.add(new Item(0, "Burrata", "burrata cheese, roasted capsicum & caper berries", "entree", 0, 16));
        items.add(new Item(0, "Burrata", "burrata cheese, roasted capsicum & caper berries", "entree", 0, 16));
        items.add(new Item(0, "Burrata", "burrata cheese, roasted capsicum & caper berries", "entree", 0, 16));
        items.add(new Item(0, "Burrata", "burrata cheese, roasted capsicum & caper berries", "entree", 0, 16));

        return items;
    }
}
