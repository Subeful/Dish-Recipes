package com.example.food.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;
import com.example.food.model.SeasonProductModel;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context productContext;
    LinkedList<SeasonProductModel> productList;

    public ProductAdapter(Context productContext, LinkedList<SeasonProductModel> productList) {
        this.productContext = productContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(productContext).inflate(R.layout.product_model, parent, false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.product_name.setText(productList.get(position).getNameProduct());
        holder.bgImage.setBackground(productContext.getDrawable(productList.get(position).getBgImage()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView product_name;
        ImageView bgImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            bgImage = itemView.findViewById(R.id.productView);
        }
    }
}
