package com.mad.mizen.di;


import android.app.Application;
import com.mad.mizen.App;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
