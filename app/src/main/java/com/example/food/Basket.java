package com.example.food;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SaveUsersAccount;
import com.example.food.profile.IfNotAccount;
import com.example.food.profile.SingIn;

public class Basket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));
        try {

            ifNotAccount();

            ListView basketList = findViewById(R.id.basketList);
            basketList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                    CollectionCloud.BasketList.toArray()));

            setAvaIfHaveAccount();

        }catch (Exception e){Toast.makeText(this, "Error: full basket", Toast.LENGTH_SHORT).show();}
    }

    private void ifNotAccount(){
        if(SaveUsersAccount.usersAccount == null){
            Intent intent = new Intent(this, IfNotAccount.class);
            intent.putExtra("activity", "Карзина доступна");
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

    public void goBasket(View v){}
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
    }public void goSetting(View v){
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


    
}