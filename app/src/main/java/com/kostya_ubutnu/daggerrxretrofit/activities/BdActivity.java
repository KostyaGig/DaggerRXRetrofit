package com.kostya_ubutnu.daggerrxretrofit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kostya_ubutnu.daggerrxretrofit.App;
import com.kostya_ubutnu.daggerrxretrofit.R;
import com.kostya_ubutnu.daggerrxretrofit.adapters.BdAdapter;
import com.kostya_ubutnu.daggerrxretrofit.models.User;
import com.kostya_ubutnu.daggerrxretrofit.repositories.BdRepository;
import com.kostya_ubutnu.daggerrxretrofit.viewmodel.BdViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BdActivity extends AppCompatActivity {

    private EditText edName,edPassword;

    private RecyclerView recyclerView;
    private BdAdapter adapter;
    private List<User> list;

    private ProgressDialog pd;

    @Inject
    BdViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        setContentView(R.layout.activity_bd);

        init();
        initRecView();
        initPd();

        pd.show();
        viewModel.getUsers();

        viewModel.getData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                if (users == null){
                    Log.d(BdRepository.TAG, "activity users == null");
                } else {
                    Log.d(BdRepository.TAG, "activity users != null ");
                    for (User user: users){
                        Log.d(BdRepository.TAG, "useer:  " + user.getName());
                        list.add(user);
                    }
                    adapter.notifyDataSetChanged();
                    pd.dismiss();
                }
            }
        });

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                if (TextUtils.isEmpty(edName.getText().toString().trim()) || TextUtils.isEmpty(edPassword.getText().toString().trim())){
                    pd.dismiss();
                    Toast.makeText(BdActivity.this, "Заполни поля,олух!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (viewModel.insertUser(edName.getText().toString().trim(),edPassword.getText().toString().trim())){
                    Toast.makeText(BdActivity.this, "Юзер был вставлен", Toast.LENGTH_SHORT).show();
                    viewModel.getUsers();
                    pd.dismiss();
                } else {
                    pd.dismiss();
                    Toast.makeText(BdActivity.this, "Юзер не был вставлен!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void init(){
        edName = findViewById(R.id.name);
        edPassword = findViewById(R.id.password);
    }
    private void initRecView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add(new User("kkkdk","123456"));
        adapter = new BdAdapter(list,this);

        recyclerView.setAdapter(adapter);
    }

    private void initPd(){
        pd = new ProgressDialog(this);
        pd.setTitle("Загрузка");
        pd.setMessage("Жди");
    }

}
