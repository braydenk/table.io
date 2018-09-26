package com.mad.mizen.di;

import com.mad.mizen.MenuFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract MenuFragment contributeMenuFragment();
}
