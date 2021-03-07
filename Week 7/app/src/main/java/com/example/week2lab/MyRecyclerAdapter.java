package com.example.week2lab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week2lab.provider.Item;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    //ArrayList<Item> data = new ArrayList<Item>();
    private List<Item> mItems;

    //public void setData(ArrayList<Item> data){this.data = data;}

    public MyRecyclerAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.nameTv.setText(mItems.get(position).getName());
        holder.costTv.setText(Double.toString(mItems.get(position).getCost()));
        holder.quantityTv.setText(Integer.toString(mItems.get(position).getQuantity()));
        holder.descTv.setText(mItems.get(position).getDescription());
        holder.freezeTv.setText(Boolean.toString(mItems.get(position).isFreeze()));
    }

    @Override
    public int getItemCount() {
        if (mItems == null){
            return 0;
        }else{
            return mItems.size();
        }
    }

    public void setItems(List<Item> newData){
        mItems = newData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTv;
        public TextView costTv;
        public TextView quantityTv;
        public TextView descTv;
        public TextView freezeTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.cv_name);
            costTv = itemView.findViewById(R.id.cv_cost);
            quantityTv = itemView.findViewById(R.id.cv_qty);
            descTv = itemView.findViewById(R.id.cv_desc);
            freezeTv = itemView.findViewById(R.id.cv_freeze);
        }
    }
}
