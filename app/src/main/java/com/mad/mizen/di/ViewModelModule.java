package com.mad.mizen.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.mad.mizen.FactoryViewModel;
import com.mad.mizen.menu.MenuViewModel;
import com.mad.mizen.order.OrderViewModel;
import com.mad.mizen.pay.PayViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel.class)
    abstract ViewModel bindMenuViewModel(MenuViewModel menuViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel.class)
    abstract ViewModel bindOrderViewModel(OrderViewModel orderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel.class)
    abstract ViewModel bindPayViewModel(PayViewModel payViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);


}