package com.kostya_ubutnu.daggerrxretrofit.components;

import com.kostya_ubutnu.daggerrxretrofit.activities.MainActivity;
import com.kostya_ubutnu.daggerrxretrofit.activities.BdActivity;
import com.kostya_ubutnu.daggerrxretrofit.modules.DbModules;
import com.kostya_ubutnu.daggerrxretrofit.modules.NetworkModule;

import dagger.Component;

@Component(modules = {NetworkModule.class, DbModules.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(BdActivity bdActivity);
}
