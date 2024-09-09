package com.example.food.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class CreateAccount extends AppCompatActivity {

    EditText userName_craeate, userEmail_craeate, userPassword_craeate;
    CheckBox userCheckBox_craeate;

    String name, email, password;
    boolean checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();


    }

    public void createUserAccount(View v){

        if(checkBox) ifExistData();
        else Toast.makeText(this, "Примите соглашение", Toast.LENGTH_SHORT).show();

    }

    public void getFlag(View v){
        if(userCheckBox_craeate.isChecked()){
            checkBox = true;
        }else checkBox = false;
    }

    private void setData(){
        name = userName_craeate.getText().toString();
        userName_craeate.setText("");
        email = userEmail_craeate.getText().toString();
        userEmail_craeate.setText("");
        password = userPassword_craeate.getText().toString();
        userPassword_craeate.setText("");
    }

    private void isUserPresentListOrSetIfNot(){
        boolean flagIsUsers = false;
        for(UsersAccount account: SaveUsersAccount.usersList){
            if (account.getUserName().equals(name) && account.getUserPassword().equals(password)) {
                flagIsUsers = true;
                break;
            }
        }
        if(!flagIsUsers) saveUserAccount();
        else Toast.makeText(this, "Такой аккаунт уже есть", Toast.LENGTH_SHORT).show();
    }

    private void ifExistData(){

        if(getData()){
            setData();

            if(SaveUsersAccount.usersList.isEmpty()) saveUserAccount();
            else isUserPresentListOrSetIfNot();

            startActivity(new Intent(this, MyAccount.class));
        }
    }

    private void saveUserAccount(){
        SaveUsersAccount.usersAccount = new UsersAccount(name, email, password);
        SaveUsersAccount.usersList.add(SaveUsersAccount.usersAccount);
        Toast.makeText(this, "Аккаунт сохранен", Toast.LENGTH_SHORT).show();
    }
    

    private boolean getData(){
        int isGood = 0;

        if(userName_craeate.getText().toString().isEmpty()) {
            isGood = 1;
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        if(userEmail_craeate.getText().toString().length() < 5){
            isGood = 2;
            Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show();
        }
        if(userPassword_craeate.getText().toString().length() < 3) {
            isGood = 3;
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();

        }

        return isGood == 0;
    }

    private void setUI(){
        userName_craeate = findViewById(R.id.userName_craeate);
        userEmail_craeate = findViewById(R.id.userEmail_craeate);
        userPassword_craeate = findViewById(R.id.userPassword_craeate);
        userCheckBox_craeate = findViewById(R.id.userCheckBox_craeate);

    }

    public void logIn(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
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