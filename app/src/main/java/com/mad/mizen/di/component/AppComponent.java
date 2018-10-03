package com.mad.mizen.di.component;


import android.app.Application;
import com.mad.mizen.App;
import com.mad.mizen.di.modules.ActivityModule;
import com.mad.mizen.di.modules.AppModule;
import com.mad.mizen.di.modules.FragmentModule;
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
