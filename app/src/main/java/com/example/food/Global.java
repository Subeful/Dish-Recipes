package com.example.food;

import static com.example.food.Help.CollectionCloud.commonDishList;
import static com.example.food.Help.CollectionCloud.lastDishList;
import static com.example.food.Help.CollectionCloud.statesList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SaveUsersAccount;
import com.example.food.adapter.LastDishAdapter;
import com.example.food.adapter.LastStatesAdapter;
import com.example.food.model.DishModel;
import com.example.food.model.StatesModel;
import com.example.food.profile.SingIn;

import java.util.LinkedList;
import java.util.List;

public class Global extends AppCompatActivity {

    RecyclerView lastsDishRecycle1, StatesRecycle;

    LastDishAdapter lastDishAdapter;
    LastStatesAdapter statesAdapter;

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

        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        try {
            lastsDishRecycle1 =  findViewById(R.id.lastsDishRecycle1);
            setLastDishList();
            setApplicationRecycle1(lastDishList);
        }catch (Exception e){Toast.makeText(this, "Error: full last dish", Toast.LENGTH_SHORT).show();}

        try {
            StatesRecycle =  findViewById(R.id.lastsStatesRecycle);
            setStatesList();
            setApplicationRecycle2(statesList);
        }catch (Exception e){Toast.makeText(this, "Error: full states", Toast.LENGTH_SHORT).show();}

        setAvaIfHaveAccount();
    }

    public void goToAva(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    private void setAvaIfHaveAccount(){
        if(SaveUsersAccount.usersAccount != null) {
            ImageView main_ava = findViewById(R.id.main_ava);
            main_ava.setImageURI(SaveUsersAccount.usersAccount.getUserPhoto());
        }
    }

    private void setLastDishList(){
        LinkedList<DishModel> listNow = new LinkedList<>(commonDishList);
        lastDishList.clear();
        for(int i = 0; i<3; i++){
            lastDishList.add(listNow.removeLast());
        }
    }
    private void setStatesList(){
        if(CollectionCloud.flagStatesList == 0){
            statesList.add(new StatesModel(R.drawable.states_towel, "Как красиво и компактно сложить кухонные полотенца"));
            statesList.add(new StatesModel(R.drawable.states_top_9_dish, "9 оригинальных рецептов от читателя Food Recipe"));
            statesList.add(new StatesModel(R.drawable.states_kvas, "Можно ли опьянеть от кваса"));
            CollectionCloud.flagStatesList = 1;
        }
    }

    private void setApplicationRecycle1(LinkedList<DishModel> listApp) {
        try {
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            lastsDishRecycle1.setLayoutManager(layoutManagers);
            lastDishAdapter = new LastDishAdapter(listApp, this);
            lastsDishRecycle1.setAdapter(lastDishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error: lastDish Recycler", Toast.LENGTH_SHORT).show();
        }
    }
    private void setApplicationRecycle2(List<StatesModel> listApp) {
        try {
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            StatesRecycle.setLayoutManager(layoutManagers);
            statesAdapter = new LastStatesAdapter(listApp, this);
            StatesRecycle.setAdapter(statesAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error: states Recycler", Toast.LENGTH_SHORT).show();
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
    public void goSetting(View v){
        try {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goMyDish(View v){
        try {
            Intent intent = new Intent(this, MyRecipe.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }public void goCreate(View v){
        try {
            Intent intent = new Intent(this, CreateCard.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

}