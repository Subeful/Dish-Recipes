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

import com.example.food.DishActivity;
import com.example.food.R;
import com.example.food.model.CategoryModel;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context categoryContext;
    LinkedList<CategoryModel> categoryList;

    public CategoryAdapter(Context categoryContext, LinkedList<CategoryModel> categoryList) {
        this.categoryContext = categoryContext;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View category_item = LayoutInflater.from(categoryContext).inflate(R.layout.category_model, parent, false);
        return new CategoryViewHolder(category_item);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.image_panel.setBackground(categoryList.get(position).getBackground());
        holder.name_category.setText(categoryList.get(position).getName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categoryContext, DishActivity.class);
                if (!categoryList.get(position).getDishList().isEmpty()){
                    intent.putExtra("dishList", (Serializable) categoryList.get(position).getDishList());
                    categoryContext.startActivity(intent);
                }else Toast.makeText(categoryContext, "Error: non dish", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView name_category;
        ImageView image_panel;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name_category = itemView.findViewById(R.id.name_category);
            image_panel = itemView.findViewById(R.id.image_panel);

        }



    }
}
