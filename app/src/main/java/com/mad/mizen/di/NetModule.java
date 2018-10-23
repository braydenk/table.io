package com.mad.mizen.di;

import android.support.annotation.NonNull;
import com.mad.mizen.data.source.remote.Api;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    @NonNull
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @NonNull
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
