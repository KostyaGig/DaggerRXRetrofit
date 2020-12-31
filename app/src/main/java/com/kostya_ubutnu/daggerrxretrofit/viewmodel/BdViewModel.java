package com.kostya_ubutnu.daggerrxretrofit.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;
import com.kostya_ubutnu.daggerrxretrofit.models.User;
import com.kostya_ubutnu.daggerrxretrofit.repositories.BdRepository;

import java.util.List;

import javax.inject.Inject;

public class BdViewModel extends ViewModel {

    @Inject
    BdRepository repository;

    @Inject
    MutableLiveData<List<User>> data = new MutableLiveData<>();

    @Inject public BdViewModel(){}

    public boolean insertUser(String name,String password){

        if (repository.insertUser(name, password)){
            return true;
        } else {
            return false;
        }

    }

    public void getUsers(){
        repository.getUsers();
        data = repository.getMutableData();
    }

    public MutableLiveData<List<User>> getData(){
        return data;
    }

}
