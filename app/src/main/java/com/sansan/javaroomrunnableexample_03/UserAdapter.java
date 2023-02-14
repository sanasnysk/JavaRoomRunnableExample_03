package com.sansan.javaroomrunnableexample_03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;

    public void setUserList(List<User> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText(userList.get(position).userName);
        holder.txt_age.setText(userList.get(position).userAge);

    }

    @Override
    public int getItemCount() {
        return (null != userList ? userList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        TextView txt_age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_userName);
            txt_age = itemView.findViewById(R.id.txt_userAge);
        }
    }
}
