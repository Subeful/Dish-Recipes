package com.example.food;

import static com.example.food.R.id.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SetterInDish;
import com.example.food.adapter.DishAdapter;
import com.example.food.adapter.LastDishAdapter;
import com.example.food.adapter.LastStatesAdapter;
import com.example.food.model.DishModel;
import com.example.food.model.StatesModel;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Global extends AppCompatActivity {

    RecyclerView lastsDishRecycle1, StatesRecycle;

    LastDishAdapter lastDishAdapter;
    LastStatesAdapter statesAdapter;

    LinkedList<DishModel> lastDishList = CollectionCloud.lastDishList;
    List<StatesModel> statesList = CollectionCloud.statesList;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_global);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

        try {
            lastsDishRecycle1 =  findViewById(R.id.lastsDishRecycle1);
            lastDishList.addAll(SetterInDish.setFirstDish());
            setApplicationRecycle1(lastDishList);
        }catch (Exception e){Toast.makeText(this, "recycle 1", Toast.LENGTH_SHORT).show();}

        try {
            StatesRecycle =  findViewById(R.id.lastsStatesRecycle);
            statesList.add(new StatesModel(R.drawable.states_towel, "Как красиво и компактно сложить кухонные полотенца"));
            statesList.add(new StatesModel(R.drawable.states_top_9_dish, "9 оригинальных рецептов от читателя Food Recipe"));
            statesList.add(new StatesModel(R.drawable.states_kvas, "Можно ли опьянеть от кваса"));
            setApplicationRecycle2(statesList);
        }catch (Exception e){Toast.makeText(this, "recycle 1", Toast.LENGTH_SHORT).show();}

    }
    private void setApplicationRecycle1(LinkedList<DishModel> listApp) {
        try {
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            lastsDishRecycle1.setLayoutManager(layoutManagers);
            lastDishAdapter = new LastDishAdapter(listApp, this);
            lastsDishRecycle1.setAdapter(lastDishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
        }
    }
    private void setApplicationRecycle2(List<StatesModel> listApp) {
        try {
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            StatesRecycle.setLayoutManager(layoutManagers);
            statesAdapter = new LastStatesAdapter(listApp, this);
            StatesRecycle.setAdapter(statesAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
        }
    }



    public void goBasket(View v){
        try {
            Intent intent = new Intent(this, Basket.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goGlobal(View v){}
    public void goSearch(View v){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goLover(View v){
        try {
            Intent intent = new Intent(this, LoverDish.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}