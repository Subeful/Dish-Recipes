package com.example.food.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.food.model.UsersAccount;

public class SingIn extends AppCompatActivity {

    EditText sing_name, sing_password;
    String name, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sing_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });getWindow().setNavigationBarColor(Color.parseColor("#37383B"));


        ifHaveOrNoAccount();
        setUI();

        

    }

    private void setUI(){
        sing_name = findViewById(R.id.sing_name);
        sing_password = findViewById(R.id.sing_password);
    }




    private void getDataOfUsers(){
        name = sing_name.getText().toString();
        password = sing_password.getText().toString();
    }

    private boolean isDataOfUsersExist(){
        if(sing_name.getText().toString().isEmpty() || sing_password.getText().toString().isEmpty())
            return false;
        else return true;
    }

    private UsersAccount searchUsersOrNull(){

        for(UsersAccount account: SaveUsersAccount.usersList){
            if(account.getUserName().equals(name) && account.getUserPassword().equals(password)) return account;
        }

        return null;
    }

    public void singIn(View v){

        if(isDataOfUsersExist()) getDataOfUsers();
        else Toast.makeText(this, "Введите все данные", Toast.LENGTH_SHORT).show();

        if(searchUsersOrNull() != null){
            SaveUsersAccount.usersAccount = searchUsersOrNull();
            startActivity(new Intent(this, MyAccount.class));
        }
        else Toast.makeText(this, "Аккаунт не найден", Toast.LENGTH_SHORT).show();
    }

    private void ifHaveOrNoAccount(){
        if(SaveUsersAccount.usersAccount != null) startActivity(new Intent(this, MyAccount.class));
        else if(SaveUsersAccount.usersList.isEmpty()) startActivity(new Intent(this, CreateAccount.class));
    }

    public void createAccount(View v){
        try {
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
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
    }
    public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
}