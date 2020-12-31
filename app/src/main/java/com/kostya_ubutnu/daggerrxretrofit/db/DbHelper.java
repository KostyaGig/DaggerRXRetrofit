package com.kostya_ubutnu.daggerrxretrofit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kostya_ubutnu.daggerrxretrofit.models.User;
import com.kostya_ubutnu.daggerrxretrofit.repositories.BdRepository;
import com.kostya_ubutnu.daggerrxretrofit.repositories.MainRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context) {
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User_table (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Password TEXT);");
        Log.d(BdRepository.TAG, "onCreate:db ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertUser(String name,String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name",name);
        cv.put("Password",password);

        if (db.insert("User_table",null,cv) != -1){
            return true;
        } else {
            return false;
        }
    }

    public Observable<List<User>> getUsers(){
        Log.d(BdRepository.TAG, "getUsers: ");
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> users = new ArrayList<>();
        Observable<List<User>> observable = null;

        Cursor c = db.rawQuery("SELECT * FROM User_table",null);

        if (c.getCount() == 0){
            users.add(new User("Default user","123456"));
            observable = Observable.fromArray(users);
            return observable;
        }

        if (c.moveToFirst()){
            while (c.moveToNext()){
                String name = c.getString(c.getColumnIndex("Name"));
                String password = c.getString(c.getColumnIndex("Password"));

                User user = new User(name,password);
                users.add(user);
            }
            observable = Observable.fromArray(users);
        } else {
            c.close();
        }

        return observable;
    }

}
