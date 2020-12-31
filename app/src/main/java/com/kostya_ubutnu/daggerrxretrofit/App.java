package com.kostya_ubutnu.daggerrxretrofit;

import android.app.Application;

import com.kostya_ubutnu.daggerrxretrofit.components.AppComponent;
import com.kostya_ubutnu.daggerrxretrofit.components.DaggerAppComponent;
import com.kostya_ubutnu.daggerrxretrofit.modules.DbModules;


public class App extends Application{

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        DbModules dbModules = new DbModules(this);

        component = DaggerAppComponent
                .builder()
                .dbModules(dbModules)
                .build();
    }

    public static AppComponent getComponent(){
        return component;
    }
}
