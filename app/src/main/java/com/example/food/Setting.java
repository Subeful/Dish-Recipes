package com.example.food;


import static com.example.food.Help.SaveUsersAccount.usersAccount;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Help.SaveUsersAccount;
import com.example.food.profile.CreateAccount;
import com.example.food.profile.MyAccount;
import com.example.food.profile.SingIn;

public class Setting extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });getWindow().setNavigationBarColor(Color.parseColor("#37383B"));
        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        setDataIfHaveAccount();

    }

    private void setDataIfHaveAccount() {
        if (usersAccount != null) {

            setAvaOfUsers();
            setInfoOfAccount();
        }
    }

    public void goToAva(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    private void setInfoOfAccount(){

        TextView mainText = findViewById(R.id.setting_main_text);
        TextView profileText = findViewById(R.id.setting_profile);

        mainText.setText(usersAccount.getUserName());
        mainText.setTextSize(22);
        mainText.setTypeface(Typeface.create("inter_semibold", Typeface.BOLD_ITALIC));

        profileText.setText("Личный профиль");
    }

    private void setAvaOfUsers () {

        ImageView main_ava = findViewById(R.id.main_ava);
        ImageView setting_ava = findViewById(R.id.setting_ava);

        main_ava.setImageURI(usersAccount.getUserPhoto());
        setting_ava.setImageURI(usersAccount.getUserPhoto());
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
    public void goSetting(View v){}
    public void goLovers(View v){
        try {
            Intent intent = new Intent(this, LoverDish.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }public void goMyDish(View v){
        try {
            Intent intent = new Intent(this, MyRecipe.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void exitApp(View v){
        try {
            finishAffinity();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSingIn(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
}