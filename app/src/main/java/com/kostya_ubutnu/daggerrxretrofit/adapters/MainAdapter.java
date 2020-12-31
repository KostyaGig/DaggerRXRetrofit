package com.kostya_ubutnu.daggerrxretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kostya_ubutnu.daggerrxretrofit.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<String> list;
    private Context context;

    public MainAdapter(List<String> list,Context context) {
        this.context = context;
        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,subTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
        }

        void bind(String s){
            title.setText(s);
            subTitle.setText(s);
        }

    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        String currentStr = list.get(position);
        holder.bind(currentStr);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}