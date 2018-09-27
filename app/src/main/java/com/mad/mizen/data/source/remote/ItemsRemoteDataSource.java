package com.mad.mizen.data.source.remote;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.mizen.data.models.Item;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

// TODO: Read this data from local file.
@Singleton
public class ItemsRemoteDataSource {

    private static final String TAG = ItemsRemoteDataSource.class.getSimpleName();

    private static final String menuJson = "[\n"
            + "  {\n"
            + "    \"id\": 0,\n"
            + "    \"name\": \"Burrata\",\n"
            + "    \"description\": \"burrata cheese, roasted capsicum & caper berries\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 16\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 1,\n"
            + "    \"name\": \"Topinambur\",\n"
            + "    \"description\": \"jerusalem artichoke, parsnip & goats curd\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 17\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 2,\n"
            + "    \"name\": \"Salumi\",\n"
            + "    \"description\": \"daily chef's selection 100g\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 21\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 3,\n"
            + "    \"name\": \"Lingua\",\n"
            + "    \"description\": \"grilled ox tongue, balsamic vinegar & pink peppercorns\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 16\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 4,\n"
            + "    \"name\": \"Calamari\",\n"
            + "    \"description\": \"cracked faro, saffron & pickled mussels\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 19\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 5,\n"
            + "    \"name\": \"Crudo\",\n"
            + "    \"description\": \"swordfish, mandarin, horseradish & oyster\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 18\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 6,\n"
            + "    \"name\": \"Fegato\",\n"
            + "    \"description\": \"pan-fried calves liver & balsamic sauce\",\n"
            + "    \"category\": \"entree\",\n"
            + "    \"price\": 18\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 7,\n"
            + "    \"name\": \"Riso carnaroli\",\n"
            + "    \"description\": \"W.A. scampi, fennel, garlic flower\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 46\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 8,\n"
            + "    \"name\": \"Riso vialone nano\",\n"
            + "    \"description\": \"basil pesto, smoked ricotta & pine nuts risotto\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 23\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 9,\n"
            + "    \"name\": \"Gnocchi di patate\",\n"
            + "    \"description\": \"braised duck, porcini mushroom & pecorino pepata\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 32\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 10,\n"
            + "    \"name\": \"Tortelloni\",\n"
            + "    \"description\": \"spiced pumpkin, chestnut & sage\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 27\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 11,\n"
            + "    \"name\": \"Tagliolini al nero\",\n"
            + "    \"description\": \"squid ink tagliolini, squid & bottarga\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 28\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 12,\n"
            + "    \"name\": \"Spaghetti\",\n"
            + "    \"description\": \"saffron spaghettini, spanner crab, confit tomato & zucchini\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 38\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 13,\n"
            + "    \"name\": \"Casarecce\",\n"
            + "    \"description\": \"pork sausage, white wine & radicchio\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 34\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 14,\n"
            + "    \"name\": \"Rigatoni\",\n"
            + "    \"description\": \"cauliflower, fior d’arancio & walnuts\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 26\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 15,\n"
            + "    \"name\": \"Pappardelle\",\n"
            + "    \"description\": \"raspberry & juniper pappardelle, wild boar bolognese\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 34\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 16,\n"
            + "    \"name\": \"Pesce\",\n"
            + "    \"description\": \"market fish, black rice, leeks & sorrel\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 36\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 17,\n"
            + "    \"name\": \"Manzo\",\n"
            + "    \"description\": \"roasted wagyu rump, green beans & macadamia\",\n"
            + "    \"category\": \"main\",\n"
            + "    \"price\": 38\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 18,\n"
            + "    \"name\": \"Insalata di finocchio\",\n"
            + "    \"description\": \"fennel, cos lettuce & black sesame\",\n"
            + "    \"category\": \"side\",\n"
            + "    \"price\": 11\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 19,\n"
            + "    \"name\": \"Broccolini\",\n"
            + "    \"description\": \"almond & parmesan crumb\",\n"
            + "    \"category\": \"side\",\n"
            + "    \"price\": 12\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 20,\n"
            + "    \"name\": \"Polenta fritta\",\n"
            + "    \"description\": \"crunchy rosemary chips\",\n"
            + "    \"category\": \"side\",\n"
            + "    \"price\": 9\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 21,\n"
            + "    \"name\": \"Tipomisù\",\n"
            + "    \"description\": \"chocolate, coffee & mascarpone\",\n"
            + "    \"category\": \"dessert\",\n"
            + "    \"price\": 14\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 22,\n"
            + "    \"name\": \"Pannacotta\",\n"
            + "    \"description\": \"yoghurt, rhubarb & white chocolate\",\n"
            + "    \"category\": \"dessert\",\n"
            + "    \"price\": 14\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 23,\n"
            + "    \"name\": \"Torta di carote\",\n"
            + "    \"description\": \"carrot cake, lemon curd & honey\",\n"
            + "    \"category\": \"dessert\",\n"
            + "    \"price\": 14\n"
            + "  },\n"
            + "  {\n"
            + "    \"id\": 24,\n"
            + "    \"name\": \"Formaggio\",\n"
            + "    \"description\": \"cheese selection\",\n"
            + "    \"category\": \"dessert\",\n"
            + "    \"price\": 16\n"
            + "  }\n"
            + "]";

    public List<Item> populateData() {

        /*
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<Item>>(){}.getType();

        return gson.fromJson(menuJson, itemListType);*/

        List<Item> item = new ArrayList<>();

        item.add(new Item(0, "Burrata", "----", "", 0,16));
        item.add(new Item(1, "Topinambur", "----", "", 0,16));
        item.add(new Item(2, "Salumi", "----", "", 0,16));
        item.add(new Item(3, "Lingua", "----", "", 0,16));
        item.add(new Item(4, "Calamari", "----", "", 0,16));
        item.add(new Item(5, "Crudo", "----", "", 0,16));
        item.add(new Item(6, "Fegato", "----", "", 0,16));
        item.add(new Item(7, "Riso carnaroli", "----", "", 0,16));
        item.add(new Item(8, "Riso vialone nano", "----", "", 0,16));
        item.add(new Item(9, "Gnocchi di patate", "----", "", 0,16));

        return item;
    }
}
