package com.kostya_ubutnu.daggerrxretrofit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.kostya_ubutnu.daggerrxretrofit.App;
import com.kostya_ubutnu.daggerrxretrofit.R;
import com.kostya_ubutnu.daggerrxretrofit.adapters.MainAdapter;
import com.kostya_ubutnu.daggerrxretrofit.models.GitModel;
import com.kostya_ubutnu.daggerrxretrofit.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "tgggg";
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<String> list;

    private  ProgressDialog pd;

    @Inject
    public MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        setContentView(R.layout.activity_main);

        initPd();
        pd.show();

        mainViewModel.getDataOfInternet();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new MainAdapter(list,this);
        recyclerView.setAdapter(adapter);

        list.add("String 1");
        list.add("String 2");
        list.add("String 3");

        adapter.notifyDataSetChanged();

        mainViewModel.getData().observe(this, new Observer<List<GitModel>>() {
            @Override
            public void onChanged(List<GitModel> gitModels) {

                for (GitModel model:gitModels) {
                    list.add(model.getName());
                }

                adapter.notifyDataSetChanged();
                pd.dismiss();
            }
        });

    }

   private void initPd(){
        pd = new ProgressDialog(this);
        pd.setTitle("Загрзука");
        pd.setMessage("Жди...");
    }
}
