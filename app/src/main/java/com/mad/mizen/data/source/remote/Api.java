package com.mad.mizen.data.source.remote;

import com.mad.mizen.data.models.Item;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/")
    Call<List<Item>> getItems();

}
