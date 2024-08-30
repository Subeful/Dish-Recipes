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
import com.example.food.model.StatesModel;

import java.util.List;

public class LastStatesAdapter extends RecyclerView.Adapter<LastStatesAdapter.LastStatesViewHolder> {

    List<StatesModel> statesList;
    Context context;

    public LastStatesAdapter(List<StatesModel> statesList, Context context) {
        this.statesList = statesList;
        this.context = context;
    }

    @NonNull
    @Override
    public LastStatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_states, parent, false);
        return new LastStatesAdapter.LastStatesViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull LastStatesViewHolder holder, int position) {

        holder.statesBgImage.setBackground(context.getDrawable(statesList.get(position).getStatesBgImage()));
        holder.statesName.setText(statesList.get(position).getStatesName());

    }

    @Override
    public int getItemCount() {
        return statesList.size();
    }

    public static final class LastStatesViewHolder extends RecyclerView.ViewHolder{

        ImageView statesBgImage;
        TextView statesName;

        public LastStatesViewHolder(@NonNull View itemView) {

            super(itemView);

            statesName = itemView.findViewById(R.id.lastsStatesName);
            statesBgImage = itemView.findViewById(R.id.lastsStatesView);
        }
    }
}
