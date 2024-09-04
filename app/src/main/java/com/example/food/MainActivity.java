package com.example.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.food.Help.CategoryDishLish;
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

    Context context = MainActivity.this;


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
        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));


        try {
            categoryListView = findViewById(R.id.categoryColumn);
            getCategorySearch = findViewById(R.id.getCategorySearch);
            clearCategorySearch = findViewById(R.id.clearCategorySearch);
            setBeginDishList();
            setCategoryListFull();
            categoryListNow.addAll(CollectionCloud.commonCategoryList);
            setApplicationRecycle(categoryListNow);
            setTextDescription();
        }catch (Exception e){Toast.makeText(this, "Error: full main", Toast.LENGTH_SHORT).show();}


    }
    private void setBeginDishList(){
        try {
            if(CollectionCloud.flagCommonCategoryList == 0){
                SetterInDish.setFirstDish(context);
                SetterInDish.setSecondDish(context);
                SetterInDish.setSaladDish(context);
                SetterInDish.setSnackDish(context);
                CollectionCloud.flagCommonCategoryList = 1;
            }
        }catch (Exception e){Toast.makeText(this, "error begin", Toast.LENGTH_SHORT).show();}
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
    public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
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
            try {
                CollectionCloud.commonCategoryList.clear();
                CollectionCloud.commonCategoryList.add(new CategoryModel(1, getDrawable(R.drawable.category_first),
                        "Первые блюда", CategoryDishLish.first));
                CollectionCloud.commonCategoryList.add(new CategoryModel(2, getDrawable(R.drawable.category_second),
                        "Вторые блюда", CategoryDishLish.second));
                CollectionCloud.commonCategoryList.add(new CategoryModel(3, getDrawable(R.drawable.category_salat),
                        "Салаты", CategoryDishLish.salad));
                CollectionCloud.commonCategoryList.add(new CategoryModel(4, getDrawable(R.drawable.category_snacks),
                        "Закуски", CategoryDishLish.snack));
                CollectionCloud.commonCategoryList.add(new CategoryModel(5, getDrawable(R.drawable.category_cake),
                        "Выпечка", CategoryDishLish.a1));
                CollectionCloud.commonCategoryList.add(new CategoryModel(6, getDrawable(R.drawable.category_sous),
                        "Соусы и маринады", CategoryDishLish.a2));
                CollectionCloud.commonCategoryList.add(new CategoryModel(7, getDrawable(R.drawable.category_zacuski),
                        "Заготовки", CategoryDishLish.a3));
                CollectionCloud.commonCategoryList.add(new CategoryModel(8, getDrawable(R.drawable.category_drinks),
                        "Напитки", CategoryDishLish.a4));
                CollectionCloud.commonCategoryList.add(new CategoryModel(10, getDrawable(R.drawable.category_garnir),
                        "Гарниры", CategoryDishLish.a5));
                CollectionCloud.commonCategoryList.add(new CategoryModel(9, getDrawable(R.drawable.category_desert),
                        "Десерты", CategoryDishLish.a6));
            } catch (Exception e) {Toast.makeText(this, "Error: set commonCategoryList \n MainActivity", Toast.LENGTH_SHORT).show();}
        }

    }



