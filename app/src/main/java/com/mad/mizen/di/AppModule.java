package com.mad.mizen.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.mad.mizen.data.source.ItemRepository;
import com.mad.mizen.data.source.local.AppDatabase;
import com.mad.mizen.data.source.local.ItemDao;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

@Module(includes = {AndroidInjectionModule.class, ViewModelModule.class})
public class AppModule {

    // --- DATABASE INJECTION ---

    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "item.db")
                .build();
    }

    @Provides
    @Singleton
    ItemDao provideItemDao(AppDatabase database) { return database.itemDao(); }

    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    ItemRepository provideUserRepository(ItemDao itemDao, Executor executor) {
        return new ItemRepository(itemDao, executor);
    }
}
