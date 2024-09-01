package com.example.food;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.food.adapter.DishAdapter;
import com.example.food.model.CategoryModel;
import com.example.food.model.DishModel;
import java.util.ArrayList;
import java.util.List;

public class DishActivity extends AppCompatActivity {

    EditText getDishSearch;
    ImageButton clearDishSearch;

    RecyclerView dishListView;
    DishAdapter dishAdapter;
    List<DishModel> dishListFull = new ArrayList<>();
    List<DishModel> dishListNow = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

        dishListView = findViewById(R.id.dishColumn);
        getDishSearch = findViewById(R.id.getDishSearch);
        clearDishSearch = findViewById(R.id.clearDishSearch);

        try {

            setDishListFull();
            dishListNow.addAll(dishListFull);
            setApplicationRecycle(dishListNow);

        }catch (Exception e){Toast.makeText(this, "Error: full dish Recycler", Toast.LENGTH_SHORT).show();}

        try {
            getDishSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void afterTextChanged(Editable editable) {
                    if(getDishSearch.getText().toString().length() > 0){
                        clearDishSearch.setVisibility(View.VISIBLE);
                        clearDishSearch.setEnabled(true);
                    }else {
                        clearDishSearch.setVisibility(View.INVISIBLE);
                        clearDishSearch.setEnabled(false);
                    }
                }
            });
        }catch (Exception e){Toast.makeText(this, "Error: prepare search", Toast.LENGTH_SHORT).show();}

        clearDishSearch.setOnClickListener(view -> {
            if(getDishSearch.getText().toString().length() < 1){}
            else {
                getDishSearch.setText("");
                setApplicationRecycle(dishListFull);
            }
        });
    }
    public void goBasket(View v){
        try {
            Intent intent = new Intent(this, Basket.class);
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

    private void setDishListFull(){
        try {
            dishListFull.addAll((List<DishModel>) getIntent().getSerializableExtra("dishList"));
        }catch (Exception e){
            Toast.makeText(this, "Error: getIntent list \n DishActivity", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean searchCategory(View v){
        try {
            String category = getDishSearch.getText().toString().toLowerCase().trim();
            if(!category.isEmpty()){
                for(DishModel model: dishListFull){
                    if(model.getNameDish().toLowerCase().equals(category)) {
                        dishListNow.clear();
                        dishListNow.add(model);
                        setApplicationRecycle(dishListNow);
                        return true;
                    }
                }
                Toast.makeText(this, "Такой категории нет", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this, "Введите категорию", Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(this, "Error: search fatal", Toast.LENGTH_SHORT).show();}
        return false;

    }

    private void setApplicationRecycle(List<DishModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 1);
            dishListView.setLayoutManager(layoutManagers);
            dishAdapter = new DishAdapter(listApp, this);
            dishListView.setAdapter(dishAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }



}