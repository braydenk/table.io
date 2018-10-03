package com.mad.mizen.di;

import com.mad.mizen.menu.MenuFragment;
import com.mad.mizen.order.OrderFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract MenuFragment contributeMenuFragment();

    @ContributesAndroidInjector
    abstract OrderFragment orderFragment();
}
