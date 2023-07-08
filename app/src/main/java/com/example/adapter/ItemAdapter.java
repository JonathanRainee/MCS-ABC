package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcs_abc.R;
import com.example.model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    public ArrayList<Item> items = new ArrayList<>();
    Context context;

    public ItemAdapter(Context ctx){
        this.context = ctx;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userId.setText("User: " + items.get(position).getUserID());
        holder.id.setText("ID: " + items.get(position).getId());
        holder.title.setText(items.get(position).getTitle());
        holder.body.setText(items.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userId, id, title, body;
        public ViewHolder(View itemView) {
            super(itemView);
            userId = itemView.findViewById(com.example.mcs_abc.R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

}
