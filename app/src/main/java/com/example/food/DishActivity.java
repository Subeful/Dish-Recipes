package com.example.food;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

        dishListView = findViewById(R.id.dishColumn);
        getDishSearch = findViewById(R.id.getDishSearch);
        clearDishSearch = findViewById(R.id.clearDishSearch);

        try {
            setDishListFull();
            dishListNow.addAll(dishListFull);

            setApplicationRecycle(dishListNow);

        }catch (Exception e){
            Toast.makeText(this, "dish", Toast.LENGTH_SHORT).show();
        }

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
        }catch (Exception e){Toast.makeText(this, "error get text", Toast.LENGTH_SHORT).show();}

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
    private void setDishListFull(){
        try {
            List<DishModel> list = (List<DishModel>) getIntent().getSerializableExtra("dishList");
            dishListFull.addAll(list);
        }catch (Exception e){
            Toast.makeText(this, "error getIntent", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean searchCategory(View v){
        String category = getDishSearch.getText().toString().toLowerCase().trim();

        if(!category.isEmpty()){

            for(DishModel model: dishListFull){
                if(model.getNameDish().toLowerCase().equals(category)) {
                    setCategoryListNow(model);
                    setApplicationRecycle(dishListNow);
                    return true;
                }
            }
            Toast.makeText(this, "Такой категории нет", Toast.LENGTH_SHORT).show();

        }else Toast.makeText(this, "Введите категорию", Toast.LENGTH_SHORT).show();
        return false;
    }
    private void setCategoryListNow(DishModel model){
        dishListNow.clear();
        dishListNow.add(model);
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



}