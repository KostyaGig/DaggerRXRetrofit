package com.kostya_ubutnu.daggerrxretrofit.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kostya_ubutnu.daggerrxretrofit.activities.MainActivity;
import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;
import com.kostya_ubutnu.daggerrxretrofit.network_service.NetworkApi;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    @Inject
    NetworkApi api;


    @Inject
    MutableLiveData<List<GitModel>> mutableData;

    @Inject
    public MainRepository() {}

    public void getDataOfInternet(){
            Observable<List<GitModel>> data = api.getData();
            data
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<GitModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<GitModel> gitModels) {
                            Log.d(MainActivity.TAG, "onNext: ");

                            for (GitModel model :gitModels){
                                Log.d(MainActivity.TAG, model.getArchiveUrl());
                            }


                            mutableData.setValue(gitModels);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(MainActivity.TAG, "onError: ");
                            getData().setValue(null);
                        }

                        @Override
                        public void onComplete(){

                        }
                    });

    }

    public MutableLiveData<List<GitModel>> getData(){
        return mutableData;
    }


}
