package com.example.food.profile;

import static com.example.food.Help.SaveUsersAccount.usersAccount;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.example.food.model.UsersAccount;

public class EditDataFromAccount extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;

    ImageButton edit_account_button, account_add_foto;
    ImageView main_ava, account_foto;

    EditText name, email, password, phone, gender, date, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_data_from_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

    public void updateDataOfUserAccount(View v){
        setData();
        startActivity(new Intent(this, MyAccount.class));
    }

    private void setData(){

        if(name.getText().toString().length() > 3)  usersAccount.setUserName(name.getText().toString());
        if(email.getText().toString().length() > 5)  usersAccount.setUserEmail(email.getText().toString());
        if(password.getText().toString().length() > 3)  usersAccount.setUserPassword(password.getText().toString());
        if(date.getText().toString().length() > 7)  usersAccount.setUserDateOfBirth(date.getText().toString());
        if(gender.getText().toString().length() == 1)  setGender(gender.getText().toString());
        if(phone.getText().toString().length() == 11)  usersAccount.setUserPhoneNumber(phone.getText().toString());
        if(status.getText().toString().length() > 4)  usersAccount.setUserStatus(status.getText().toString());
    }
    private void setGender(String type){
        if(type.equals("М")) usersAccount.setUserGender(UsersAccount.Gender.Male);
        else if(type.equals("Ж")) usersAccount.setUserGender(UsersAccount.Gender.Female);
        else {
            gender.setText("");
            Toast.makeText(this, "Неверный пол", Toast.LENGTH_SHORT).show();
        }
    }


    
    private void setUI(){
        try {
            edit_account_button = findViewById(R.id.edit_account_button);
            account_add_foto = findViewById(R.id.account_add_foto);

            account_foto = findViewById(R.id.account_foto);
            main_ava = findViewById(R.id.main_ava);

            name = findViewById(R.id.edit_name);
            email = findViewById(R.id.edit_email);
            password = findViewById(R.id.edit_password);
            gender = findViewById(R.id.edit_gender);
            date = findViewById(R.id.edit_date);
            phone = findViewById(R.id.edit_phone);
            status = findViewById(R.id.edit_status);

        }catch (Exception e){
            Toast.makeText(this, "Error: set UI", Toast.LENGTH_SHORT).show();
        }

    }

    private void setInfoIfExist(){

        setMainInfo();
        setAdditionalInfo();
    }

    private void setMainInfo(){
        name.setText(usersAccount.getUserName());
        email.setText(usersAccount.getUserEmail());
        password.setText(usersAccount.getUserPassword());
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
                date.setText(usersAccount.getUserDateOfBirth());

            if(usersAccount.getUserPhoneNumber() != null)
                phone.setText(usersAccount.getUserPhoneNumber());

        }catch (Exception e) {Toast.makeText(this, "Error: in data", Toast.LENGTH_SHORT).show();}
    }
    public void goBasket(View v){
        startActivity(new Intent(this, Basket.class));
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