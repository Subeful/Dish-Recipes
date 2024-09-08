package com.example.food.profile;

import static com.example.food.Help.SaveUsersAccount.usersAccount;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Basket;
import com.example.food.Global;
import com.example.food.MainActivity;
import com.example.food.R;
import com.example.food.Season;
import com.example.food.Setting;

public class MyAccount extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;
    ImageView main_ava, account_foto;
    ImageButton account_add_foto;

    TextView name, status, mail, gender, dateOfBirth, phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });getWindow().setNavigationBarColor(Color.parseColor("#37383B"));


        setUI();
        setInfoIfExist();


        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri selectedImage = data.getData();
                    ImageView view = new ImageView(this);
                    view.setImageURI(selectedImage);
                    account_foto.setImageURI(selectedImage);
                    main_ava.setImageURI(selectedImage);
                    account_add_foto.setVisibility(View.INVISIBLE);
                    usersAccount.setUserPhoto(selectedImage);
                }
            }
        });
        account_add_foto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/");
            launcher.launch(intent);
        });


    }

    private void setInfoIfExist(){

        setMainInfo();

        setAdditionalInfo();

    }

    private void setMainInfo(){
        name.setText(usersAccount.getUserName());
        mail.setText(usersAccount.getUserEmail());
    }

    private void setAdditionalInfo(){

        try {

            if(usersAccount.getUserPhoto() != null){
                account_foto.setImageURI(usersAccount.getUserPhoto());
                main_ava.setImageURI(usersAccount.getUserPhoto());
            }

            if(usersAccount.getUserStatus() != null)
                status.setText(usersAccount.getUserStatus());

            if(usersAccount.getUserGender() != null)
                gender.setText(usersAccount.getUserGender().toString());

            if(usersAccount.getUserDateOfBirth() != null)
                dateOfBirth.setText(usersAccount.getUserDateOfBirth());

            if(usersAccount.getUserPhoneNumber() != null)
                phone.setText(usersAccount.getUserPhoneNumber());

        }catch (Exception e) {Toast.makeText(this, "Ошибка аккаунта_1", Toast.LENGTH_SHORT).show();}
    }

    private void setUI(){

        try {
            account_foto = findViewById(R.id.account_foto);

            account_add_foto = findViewById(R.id.account_add_foto);
            main_ava = findViewById(R.id.main_ava);

            name = findViewById(R.id.user_name);
            status = findViewById(R.id.user_state);
            mail = findViewById(R.id.user_mail);
            gender = findViewById(R.id.user_gender);
            dateOfBirth = findViewById(R.id.user_dateOfBirth);
            phone = findViewById(R.id.user_phoneNamber);
        }catch (Exception e) {Toast.makeText(this, "Ошибка аккаунта_2", Toast.LENGTH_SHORT).show();}
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