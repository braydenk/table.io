package com.mad.mizen.data.source.remote;

import com.mad.mizen.data.models.Item;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

// TODO: Read this data from local file.
@Singleton
public class ItemsRemoteDataSource {

    @SuppressWarnings("unused")
    private static final String TAG = ItemsRemoteDataSource.class.getSimpleName();

    public List<Item> populateData() {

        List<Item> item = new ArrayList<>();

        item.add(new Item(0, "Burrata", "burrata cheese, roasted capsicum & caper berries", "entree", 0,16));
        item.add(new Item(1, "Topinambur", "jerusalem artichoke, parsnip & goats curd", "entree", 0,16));
        item.add(new Item(2, "Salumi", "daily chef's selection 100g", "entree", 0,16));
        item.add(new Item(3, "Lingua", "grilled ox tongue, balsamic vinegar & pink peppercorns", "entree", 0,16));
        item.add(new Item(4, "Calamari", "cracked faro, saffron & pickled mussels", "entree", 0,16));
        item.add(new Item(5, "Crudo", "swordfish, mandarin, horseradish & oyster", "entree", 0,16));
        item.add(new Item(6, "Fegato", "pan-fried calves liver & balsamic sauce", "entree", 0,16));
        item.add(new Item(7, "Riso carnaroli", "W.A. scampi, fennel, garlic flower", "main", 0,16));
        item.add(new Item(8, "Riso vialone nano", "basil pesto, smoked ricotta & pine nuts risotto", "main", 0,16));
        item.add(new Item(9, "Gnocchi di patate", "braised duck, porcini mushroom & pecorino pepata", "main", 0,16));
        item.add(new Item(10, "Polenta frittata", "crunchy rosemary chips", "side", 0, 9));
        item.add(new Item(11, "Panacotta", "yoghurt, rhubarb & white chocolate", "dessert", 0, 14));
        item.add(new Item(12, "Coke", "", "drink", 0, 2.5));

        return item;
    }
}
