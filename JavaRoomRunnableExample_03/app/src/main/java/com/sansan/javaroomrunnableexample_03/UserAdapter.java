package com.sansan.javaroomrunnableexample_03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User> userList;

    public UserAdapter(Context context, ArrayList<User> list){
        this.context = context;
        this.userList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User users = userList.get(position);

        holder.user_name.setText(users.getUserName());
        holder.user_age.setText(users.getUserAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(context, UpdateActivity.class);
                intent_update.putExtra("uId", users.getuId());
                intent_update.putExtra("userName", users.getUserName());
                intent_update.putExtra("userAge", users.getUserAge());
                context.startActivity(intent_update);

                ((Activity) context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != userList ? userList.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView user_age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.txt_userName);
            user_age = itemView.findViewById(R.id.txt_userAge);
        }
    }
}
