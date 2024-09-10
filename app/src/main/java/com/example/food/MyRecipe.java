package com.example.food;

import static com.example.food.Help.SaveUsersAccount.usersAccount;

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
import com.example.food.adapter.DishLoveAdapter;
import com.example.food.model.DishModel;
import com.example.food.profile.IfNotAccount;
import com.example.food.profile.SingIn;

import java.util.LinkedList;

public class MyRecipe extends AppCompatActivity {

    RecyclerView MyDishRecycler;
    DishLoveAdapter myDishAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        ifNotAccount();

        context = MyRecipe.this;
        try {

            MyDishRecycler = findViewById(R.id.MyDishRecycler);

            setApplicationRecycle(CollectionCloud.myRecipeList);

            setAvaOfUsers();

        }catch (Exception e){Toast.makeText(this, "error recycler", Toast.LENGTH_SHORT).show();}
    }

    private void setAvaOfUsers () {
        if (usersAccount  != null){
            ImageView main_ava = findViewById(R.id.main_ava);
            main_ava.setImageURI(usersAccount.getUserPhoto());
        }
    }
    private void ifNotAccount(){
        if(SaveUsersAccount.usersAccount == null){
            Intent intent = new Intent(this, IfNotAccount.class);
            intent.putExtra("activity", "Мои рецепты доступны");
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

    public void setApplicationRecycle(LinkedList<DishModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 1);
            MyDishRecycler.setLayoutManager(layoutManagers);
            myDishAdapter = new DishLoveAdapter(listApp, this);
            MyDishRecycler.setAdapter(myDishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
        }
    }
    public void goBasket(View v){
        try {
            Intent intent = new Intent(this, Basket.class);
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
        startActivity(new Intent(this, CreateCard.class));
    }
}