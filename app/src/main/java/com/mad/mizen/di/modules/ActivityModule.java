package com.mad.mizen.di.modules;

import com.mad.mizen.main.MainActivity;
import com.mad.mizen.pay.PaymentActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract PaymentActivity contributePaymentActivity();
}
