package com.example.food.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

            intent.putExtra("name", dishList.get(position).getNameDish());
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
