package com.example.food;

import static com.example.food.Help.CollectionCloud.lastSeeDishList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.example.food.Help.SaveUsersAccount;
import com.example.food.adapter.DishLoveAdapter;
import com.example.food.adapter.LastDishAdapter;
import com.example.food.model.DishModel;
import com.example.food.profile.IfNotAccount;
import com.example.food.profile.SingIn;

import java.util.LinkedList;

public class LastSightDish extends AppCompatActivity {

    RecyclerView lastSeeDishRecycler;
    DishLoveAdapter dishAdapter;
    Context context;

    LinkedList<DishModel> lastSeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_last_sight_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        context = LastSightDish.this;
        setAvaIfHaveAccount();

        try {

            lastSeeDishRecycler =  findViewById(R.id.lastSeeDishRecycler);
            setLastSightDishList();
            setApplicationRecycle3(lastSeeList);


        }catch (Exception e){
            Toast.makeText(this, "error recycler", Toast.LENGTH_SHORT).show();}
    }

    private void setLastSightDishList(){
        int max = 0;
        LinkedList<DishModel> copyList = new LinkedList<>(lastSeeDishList);
        lastSeeList = new LinkedList<>();

        while (copyList.peekLast() != null){
            if(max != 4){
                lastSeeList.add(copyList.removeFirst());
                max++;
            }
            else break;
        }
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

    private void setApplicationRecycle3(LinkedList<DishModel> listApp) {
        try {
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            lastSeeDishRecycler.setLayoutManager(layoutManagers);
            dishAdapter = new DishLoveAdapter(listApp, this);
            lastSeeDishRecycler.setAdapter(dishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error: lastDish Recycler", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBasket(View v){
        try {
            Intent intent = new Intent(this, Basket.class);
            intent.putExtra("activity", "Корзина доступна");
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goGlobal(View v){
        try {
            Intent intent = new Intent(this, Global.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSearch(View v){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSetting(View v){
        try {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void update(View v){
        finish();
        setLastSightDishList();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}