package com.example.food.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.DescriptionDish;
import com.example.food.R;
import com.example.food.model.DishModel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DishLoveAdapter extends RecyclerView.Adapter<DishLoveAdapter.DishLoveVieHolder>{
    Context dishContext;
    LinkedList<DishModel> dishList;

    public DishLoveAdapter(LinkedList<DishModel> dishList, Context dishContext) {
        this.dishList =  dishList;
        this.dishContext = dishContext;
    }

    @Override
    @NonNull
    public DishLoveAdapter.DishLoveVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(dishContext).inflate(R.layout.dish_model, parent, false);
        return new DishLoveAdapter.DishLoveVieHolder(view);
    }

    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onBindViewHolder(@NonNull DishLoveAdapter.DishLoveVieHolder holder, int position) {

        holder.difficultyDish.setBackground(dishContext.getDrawable(dishList.get(position).getDifficultyDish()));
        holder.backgroundImage.setBackground(dishList.get(position).getBackgroundImage());
        holder.cookingTime.setText(dishList.get(position).getCookingTime());
        holder.nameDish.setText(dishList.get(position).getNameDish());

        holder.itemView.setOnClickListener((v) ->{
            Intent intent = new Intent(dishContext, DescriptionDish.class);
            intent.putExtra("name", dishList.get(position).getNameDish());
            intent.putExtra("isLove", dishList.get(position).getIsLover());

            dishContext.startActivity(intent);

        });


    }
    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static final class DishLoveVieHolder extends RecyclerView.ViewHolder{

        ImageView backgroundImage, difficultyDish;
        TextView nameDish, cookingTime;


        public DishLoveVieHolder(@NonNull View itemView) {
            super(itemView);

            backgroundImage = itemView.findViewById(R.id.dishView);
            difficultyDish = itemView.findViewById(R.id.dish_lvl);
            nameDish = itemView.findViewById(R.id.name_dish);
            cookingTime = itemView.findViewById(R.id.dish_time);


        }

    }
}

