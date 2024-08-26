package com.example.food;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.food.adapter.DishAdapter;
import com.example.food.model.DishModel;
import java.util.ArrayList;
import java.util.List;

public class DishActivity extends AppCompatActivity {

    RecyclerView dishListView;
    DishAdapter dishAdapter;
    List<DishModel> dishList;

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

        try {
            dishListView = findViewById(R.id.dishColumn);
            setDishList();
            setApplicationRecycle(dishList);
        }catch (Exception e){
            Toast.makeText(this, "dish", Toast.LENGTH_SHORT).show();
        }

    }

    private void setDishList(){
        try {
            dishList = (List<DishModel>) getIntent().getSerializableExtra("dishList");
        }catch (Exception e){

        }
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