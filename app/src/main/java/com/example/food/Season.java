package com.example.food;

import static com.example.food.Help.SaveUsersAccount.usersAccount;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SetterInDish;
import com.example.food.adapter.DishLoveAdapter;
import com.example.food.adapter.ProductAdapter;
import com.example.food.model.DishModel;
import com.example.food.model.SeasonProductModel;
import com.example.food.profile.SingIn;

import java.util.LinkedList;

public class Season extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_season);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        context = Season.this;
        try {

            productRecycler = findViewById(R.id.productRecycler);
            CollectionCloud.seasonProductList.addAll(SetterInDish.setSeasonProduct());
            setApplicationRecycle(CollectionCloud.seasonProductList);
            setAvaOfUsers();

        }catch (Exception e){
            Toast.makeText(this, "error recycler", Toast.LENGTH_SHORT).show();}
    }

    private void setAvaOfUsers () {
        if(usersAccount != null){
            ImageView main_ava = findViewById(R.id.main_ava);
            main_ava.setImageURI(usersAccount.getUserPhoto());
        }
    }

    public void goToAva(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    public void setApplicationRecycle(LinkedList<SeasonProductModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 1);
            productRecycler.setLayoutManager(layoutManagers);
            productAdapter = new ProductAdapter(context, listApp);
            productRecycler.setAdapter(productAdapter);
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
    }public void goSeson(View v){}
    public void update(View v){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}