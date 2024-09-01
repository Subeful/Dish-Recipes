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

import com.example.food.Help.CollectionCloud;
import com.example.food.adapter.CategoryAdapter;
import com.example.food.model.CategoryModel;
import com.example.food.Help.SetterInDish;
import com.example.food.model.DishModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText getCategorySearch;
    ImageButton clearCategorySearch;

    RecyclerView categoryListView;
    CategoryAdapter categoryAdapter;

    LinkedList<CategoryModel> categoryListNow = new LinkedList<>();

    static int flag = 0;


    @SuppressLint("MissingInflatedId")
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


        try {
            categoryListView = findViewById(R.id.categoryColumn);
            getCategorySearch = findViewById(R.id.getCategorySearch);
            clearCategorySearch = findViewById(R.id.clearCategorySearch);
            setCategoryListFull();
            categoryListNow.addAll(CollectionCloud.commonCategoryList);
            setApplicationRecycle(categoryListNow);
        }catch (Exception e){Toast.makeText(this, "Error: full main", Toast.LENGTH_SHORT).show();}

        setTextDescription();

    }

    private void setTextDescription() {
        try {
            getCategorySearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    if(getCategorySearch.getText().toString().length() > 0){
                        clearCategorySearch.setVisibility(View.VISIBLE);
                        clearCategorySearch.setEnabled(true);
                    }else {
                        clearCategorySearch.setVisibility(View.INVISIBLE);
                        clearCategorySearch.setEnabled(false);
                    }
                }
            });
        }catch (Exception e){Toast.makeText(this, "Error: get text on search", Toast.LENGTH_SHORT).show();}
        clearCategorySearch.setOnClickListener(view -> {
            if(getCategorySearch.getText().toString().length() < 1){}
            else {
                getCategorySearch.setText("");
                setApplicationRecycle(CollectionCloud.commonCategoryList);
            }
        });
    }

    public boolean searchCategory(View v){
        String category = getCategorySearch.getText().toString().toLowerCase().trim();

        if(!category.isEmpty()){
            for(CategoryModel model: CollectionCloud.commonCategoryList){
                if(model.getName().toLowerCase().equals(category)) {
                    setCategoryListNow(model);
                    setApplicationRecycle(categoryListNow);
                    return true;
                }
            }
            Toast.makeText(this, "Такой категории нет", Toast.LENGTH_SHORT).show();

        }else Toast.makeText(this, "Введите категорию", Toast.LENGTH_SHORT).show();
        return false;
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
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goSetting(View v){
        try {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    private void setCategoryListNow(CategoryModel model){
        categoryListNow.clear();
        categoryListNow.add(model);
    }
    private void setApplicationRecycle(LinkedList<CategoryModel> listApp) {
        try {
            GridLayoutManager layoutManagers = new GridLayoutManager(this, 2);
            categoryListView.setLayoutManager(layoutManagers);
            categoryListView.setLayoutManager(layoutManagers);

            categoryAdapter = new CategoryAdapter(this, listApp);
            categoryListView.setAdapter(categoryAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "error in recycler", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setCategoryListFull(){
        if(CollectionCloud.flagCommonCategoryList == 0) {
            try {
                CollectionCloud.commonCategoryList.add(new CategoryModel(1, getDrawable(R.drawable.category_first),
                        "Первые блюда", SetterInDish.setFirstDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(2, getDrawable(R.drawable.category_second),
                        "Вторые блюда", SetterInDish.setSecondDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(3, getDrawable(R.drawable.category_salat),
                        "Салаты", SetterInDish.setSaladDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(4, getDrawable(R.drawable.category_snacks),
                        "Закуски", SetterInDish.setSnackDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(5, getDrawable(R.drawable.category_cake),
                        "Выпечка", SetterInDish.setBakeryDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(6, getDrawable(R.drawable.category_sous),
                        "Соусы и маринады", SetterInDish.setSouseDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(7, getDrawable(R.drawable.category_zacuski),
                        "Заготовки", SetterInDish.setPrepareDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(8, getDrawable(R.drawable.category_drinks),
                        "Напитки", SetterInDish.setDrinksDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(10, getDrawable(R.drawable.category_garnir),
                        "Гарниры", SetterInDish.setGarnishDish()));
                CollectionCloud.commonCategoryList.add(new CategoryModel(9, getDrawable(R.drawable.category_desert),
                        "Десерты", SetterInDish.setDessertDish()));

                CollectionCloud.flagCommonCategoryList = 1;
            } catch (Exception e) {Toast.makeText(this, "Error: set commonCategoryList \n MainActivity", Toast.LENGTH_SHORT).show();}
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

