package com.example.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {

        holder.difficultyDish.setBackground(dishList.get(position).getDifficultyDish());
        holder.backgroundImage.setBackground(dishList.get(position).getBackgroundImage());
        holder.cookingTime.setText(dishList.get(position).getCookingTime());
        holder.nameDish.setText(dishList.get(position).getNameDish());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static final class DishViewHolder extends RecyclerView.ViewHolder{

        ImageView backgroundImage, difficultyDish;
        TextView nameDish, cookingTime;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);

            backgroundImage = itemView.findViewById(R.id.backgroundImage);
            difficultyDish = itemView.findViewById(R.id.difficultyDish);
            nameDish = itemView.findViewById(R.id.nameDish);
            cookingTime = itemView.findViewById(R.id.cookingTime);

        }

    }
}
