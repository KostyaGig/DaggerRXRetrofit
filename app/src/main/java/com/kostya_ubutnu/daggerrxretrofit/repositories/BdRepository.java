package com.kostya_ubutnu.daggerrxretrofit.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kostya_ubutnu.daggerrxretrofit.db.DbHelper;
import com.kostya_ubutnu.daggerrxretrofit.models.User;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BdRepository {

    public static final String TAG = "bdrep";

    @Inject
    MutableLiveData<List<User>> data = new MutableLiveData<>();

    @Inject
    DbHelper helper;

    @Inject public BdRepository(){}


    public boolean insertUser(String name,String password){

        if (helper.insertUser(name, password)){
            return true;
        } else {
            return false;
        }

    }

    public void getUsers(){
       Observable<List<User>> observable = helper.getUsers();
       observable
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<List<User>>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(List<User> users) {
                        Log.d(TAG, "onNext: ");
                        data.setValue(users);

                        if (data.getValue() == null){
                            Log.d(TAG, "data == null");
                        } else {
                            Log.d(TAG, "data != null");
                        }

                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.d(TAG, "onError: ");
                        data.setValue(null);
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

    public MutableLiveData<List<User>> getMutableData(){
        return data;
    }
}
