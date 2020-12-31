package com.kostya_ubutnu.daggerrxretrofit.network_service;

import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("repositories")
    Observable<List<GitModel>> getData();
}
