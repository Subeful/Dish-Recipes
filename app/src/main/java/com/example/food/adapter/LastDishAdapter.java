package com.example.food.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.DescriptionDish;
import com.example.food.R;
import com.example.food.model.DishModel;

import java.util.Deque;
import java.util.LinkedList;

public class LastDishAdapter extends RecyclerView.Adapter<LastDishAdapter.LastDishViewHolder> {

    LinkedList<DishModel> lastDishList;
    Context context;

    public LastDishAdapter(LinkedList<DishModel> lastDishList, Context context) {
        this.lastDishList = lastDishList;
        this.context = context;
    }

    @NonNull
    @Override
    public LastDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lasts_add_dish, parent, false);
        return new LastDishAdapter.LastDishViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull LastDishViewHolder holder, int position) {
        holder.lastDishName.setText(lastDishList.get(position).getNameDish());
        holder.lastsDishView.setBackground(lastDishList.get(position).getBackgroundImage());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DescriptionDish.class);
            intent.putExtra("name", lastDishList.get(position).getNameDish());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lastDishList.size();
    }

    public static final class LastDishViewHolder extends RecyclerView.ViewHolder{

        ImageView lastsDishView;
        TextView lastDishName;

        public LastDishViewHolder(@NonNull View itemView) {
            super(itemView);

            lastDishName = itemView.findViewById(R.id.lastsDishName);
            lastsDishView = itemView.findViewById(R.id.lastsDishView);

        }
    }
}
