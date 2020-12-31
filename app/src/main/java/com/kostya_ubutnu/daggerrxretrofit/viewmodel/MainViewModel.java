package com.kostya_ubutnu.daggerrxretrofit.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;
import com.kostya_ubutnu.daggerrxretrofit.repositories.MainRepository;

import java.lang.invoke.MutableCallSite;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    public MainRepository repository;

    @Inject
    MutableLiveData<List<GitModel>> data;

    @Inject
    public MainViewModel(){}

    public void getDataOfInternet(){
        repository.getDataOfInternet();
        data = repository.getData();
    }

    public MutableLiveData<List<GitModel>> getData(){
        return data;
    }

}
