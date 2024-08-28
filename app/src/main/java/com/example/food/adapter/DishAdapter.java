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

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    Context dishContext;
    List<DishModel> dishList;

    public DishAdapter(List<DishModel> dishList, Context dishContext) {
        this.dishList = dishList;
        this.dishContext = dishContext;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(dishContext).inflate(R.layout.dish_model, parent, false);
        return new DishViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {

        holder.difficultyDish.setBackground(dishContext.getDrawable(dishList.get(position).getDifficultyDish()));
        holder.backgroundImage.setBackground(dishContext.getDrawable(dishList.get(position).getBackgroundImage()));
        holder.cookingTime.setText(dishList.get(position).getCookingTime());
        holder.nameDish.setText(dishList.get(position).getNameDish());

        holder.itemView.setOnClickListener((View v) -> {
            Intent intent = new Intent(dishContext, DescriptionDish.class);

            DishModel dishModel = dishList.get(position);

            intent.putExtra("nameDish", dishModel.getNameDish());
            intent.putExtra("categoryDish", dishModel.getCategoryDish());
            intent.putExtra("cookingTime", dishModel.getCookingTime());
            intent.putExtra("backImage", dishModel.getBackgroundImage());
            intent.putExtra("hardLvl", dishModel.getDifficultyDish());
            intent.putExtra("timeOnKitchen", dishModel.getTimeOnKitchen());

            intent.putExtra("dataForPost", dishModel.getDateForPost());
            intent.putExtra("descDish", dishModel.getDescriptionDish());

            intent.putExtra("authorPost", dishModel.getAuthorPostDish());
            intent.putExtra("descAuthorPost", dishModel.getDescriptionAuthorPost());

            intent.putExtra("KitchenDish", dishModel.getKitchenDish());
            intent.putExtra("starDish", dishModel.getStarDish());
            intent.putExtra("spacyDish", dishModel.getSpicyDish());

            dishContext.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static final class DishViewHolder extends RecyclerView.ViewHolder{

        ImageView backgroundImage, difficultyDish;
        TextView nameDish, cookingTime;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);

            backgroundImage = itemView.findViewById(R.id.dishView);
            difficultyDish = itemView.findViewById(R.id.dish_lvl);
            nameDish = itemView.findViewById(R.id.name_dish);
            cookingTime = itemView.findViewById(R.id.dish_time);

        }

    }
}
