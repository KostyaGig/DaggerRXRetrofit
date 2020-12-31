package com.kostya_ubutnu.daggerrxretrofit.modules;

import androidx.lifecycle.MutableLiveData;

import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;
import com.kostya_ubutnu.daggerrxretrofit.models.User;
import com.kostya_ubutnu.daggerrxretrofit.network_service.NetworkApi;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    public MutableLiveData<List<GitModel>> provideGitData(){
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<List<User>> provideUserData(){
        return new MutableLiveData<>();
    }

    @Provides
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public NetworkApi provideApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }


}
