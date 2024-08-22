package com.example.food;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.adapter.CategoryAdapter;
import com.example.food.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryListView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

        categoryListView = findViewById(R.id.categoryColumn);
        categoryList = new ArrayList<>();
        setCategoryList();
        setApplicationRecycle(categoryList);




    }
    private void setCategoryList(){
        categoryList.add(new CategoryModel(1, getDrawable(R.drawable.category_first), "Первые блюда"));
        categoryList.add(new CategoryModel(2, getDrawable(R.drawable.category_second), "Вторые блюда"));
        categoryList.add(new CategoryModel(3, getDrawable(R.drawable.category_salat), "Салаты"));
        categoryList.add(new CategoryModel(4, getDrawable(R.drawable.category_snacks), "Закуски"));
        categoryList.add(new CategoryModel(5, getDrawable(R.drawable.category_cake), "Выпечка"));
        categoryList.add(new CategoryModel(6, getDrawable(R.drawable.category_sous), "Соусы и маринады"));
        categoryList.add(new CategoryModel(7, getDrawable(R.drawable.category_zacuski), "Заготовки"));
        categoryList.add(new CategoryModel(8, getDrawable(R.drawable.category_drinks), "Напитки"));
        categoryList.add(new CategoryModel(9, getDrawable(R.drawable.category_desert), "Десерты"));
        categoryList.add(new CategoryModel(10, getDrawable(R.drawable.category_garnir), "Гарниры"));
    }
    private void setApplicationRecycle(List<CategoryModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 2);
            categoryListView.setLayoutManager(layoutManagers);
            categoryListView.setLayoutManager(layoutManagers);
            categoryAdapter = new CategoryAdapter(this, listApp);
            categoryListView.setAdapter(categoryAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Скрываем статус-бар и навигационные кнопки
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

