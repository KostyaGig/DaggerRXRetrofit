package com.kostya_ubutnu.daggerrxretrofit.modules;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.kostya_ubutnu.daggerrxretrofit.db.DbHelper;
import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModules {

   private Context context;

   public DbModules(Context context){
       this.context = context;
   }

//    @Singleton
    @Provides
    public Context provideContext(){
       return context;
    }

    @Provides
    public DbHelper provideHelper(Context context){
        return new DbHelper(context);
    }

}
