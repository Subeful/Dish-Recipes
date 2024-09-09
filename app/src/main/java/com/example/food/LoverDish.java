package com.example.food;

import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SaveUsersAccount;
import com.example.food.adapter.DishAdapter;
import com.example.food.adapter.DishLoveAdapter;
import com.example.food.model.DishModel;
import com.example.food.profile.IfNotAccount;
import com.example.food.profile.SingIn;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LoverDish extends AppCompatActivity {

    RecyclerView loverDishRecycler;
    DishLoveAdapter dishAdapter;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lover_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        ifNotAccount();

        context = LoverDish.this;
        try {

            loverDishRecycler = findViewById(R.id.loverDishRecycler);
            setApplicationRecycle(CollectionCloud.loverDishList);

            setAvaIfHaveAccount();

        }catch (Exception e){Toast.makeText(this, "error recycler", Toast.LENGTH_SHORT).show();}
    }

    private void ifNotAccount(){
        if(SaveUsersAccount.usersAccount == null){
            Intent intent = new Intent(this, IfNotAccount.class);
            intent.putExtra("activity", "Избранное доступно");
            startActivity(intent);
            finish();
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

    public void setApplicationRecycle(LinkedList<DishModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 1);
            loverDishRecycler.setLayoutManager(layoutManagers);
            dishAdapter = new DishLoveAdapter(listApp, this);
            loverDishRecycler.setAdapter(dishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
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
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSearch(View v){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSetting(View v){
        try {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void update(View v){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}
