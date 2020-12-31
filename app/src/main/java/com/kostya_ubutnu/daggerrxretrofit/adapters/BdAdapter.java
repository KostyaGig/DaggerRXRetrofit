package com.kostya_ubutnu.daggerrxretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.experimental.UseExperimental;
import androidx.recyclerview.widget.RecyclerView;

import com.kostya_ubutnu.daggerrxretrofit.R;
import com.kostya_ubutnu.daggerrxretrofit.models.User;

import java.util.List;

public class BdAdapter extends RecyclerView.Adapter<BdAdapter.ViewHolder>{

    private List<User> list;
    private Context context;

    public BdAdapter(List<User> list, Context context) {
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

        void bind(User currentUser){
            title.setText(currentUser.getName());
            subTitle.setText(currentUser.getPassword());
        }

    }

    @NonNull
    @Override
    public BdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BdAdapter.ViewHolder holder, int position) {
        User currentUser = list.get(position);
        holder.bind(currentUser);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
