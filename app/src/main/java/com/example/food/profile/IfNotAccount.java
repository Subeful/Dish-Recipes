package com.example.food.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.food.Basket;
import com.example.food.Global;
import com.example.food.Help.SaveUsersAccount;
import com.example.food.MainActivity;
import com.example.food.R;
import com.example.food.Season;
import com.example.food.Setting;

public class IfNotAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_if_not_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setActivityText();

    }

    @SuppressLint("SetTextI18n")
    private void setActivityText(){
        TextView textView = findViewById(R.id.activityText);

        String text = getIntent().getStringExtra("activity");
        String beforeText = textView.getText().toString();

        textView.setText(text + beforeText);
    }

    public void goToSingIn(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    public void goBasket(View v){
        try {
            Intent intent = new Intent(this, Basket.class);
            intent.putExtra("activity", "Корзина доступна");
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
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
    }
    public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
}