package com.example.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Help.CollectionCloud;
import com.example.food.model.DishModel;

import java.util.Set;
@SuppressLint("UseCompatLoadingForDrawables")
public class DescriptionDish extends AppCompatActivity {

    ImageView backImageDish, categoryDish, starDish, spicyDish, hardLvl;
    TextView nameDish, datePostDish, descDish, authorPostDish, descAuthorDish, kitchenDish,
        cookingTimeDish, timeOnKitchen;
    ImageButton in_love;

    String name;
    DishModel dishModel;

    Context context = DescriptionDish.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_description_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        in_love = findViewById(R.id.in_love);

        try {
            name = getIntent().getStringExtra("name");
            dishModel = findDishModel(CollectionCloud.commonDishList);
            if(dishModel == null) Toast.makeText(this, "Error: dish is null", Toast.LENGTH_SHORT).show();

            findUI();
            setUI();

        }catch (Exception e){
            Toast.makeText(this, "Error: full Description Dish", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void inLove(View v){
        int x = 0;
        try {
            if(dishModel.getFlagOnLove() == 0){
                isInLove(v, x);
            }else if(dishModel.getFlagOnLove() == 1){
                isNotInLove(v, x);
            }
        }catch (Exception e){Toast.makeText(context, "Error: full 'in live'", Toast.LENGTH_SHORT).show();}

    }

    private void isInLove(View v, int x){

        v.setBackground(getDrawable(R.drawable.in_love_2));
        dishModel.setFlagOnLove(1);
        dishModel.setIsLover(R.drawable.in_love_2);

        for(DishModel model: CollectionCloud.loverDishList){
            if (model.getNameDish().equals(dishModel.getNameDish())) {
                x = 1;
                break;
            }
        }
        if(x == 0)
            CollectionCloud.loverDishList.add(dishModel);
    }

    private void isNotInLove(View v, int x){

        v.setBackground(getDrawable(R.drawable.in_love_1));
        dishModel.setFlagOnLove(0);
        dishModel.setIsLover(R.drawable.in_love_1);

        CollectionCloud.loverDishList.removeIf(model -> model.getNameDish().equals(dishModel.getNameDish()));
    }


    public void goBasket(View v){
        try {
            Intent intent = new Intent(context, Basket.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(context, "no intent", Toast.LENGTH_SHORT).show();}
    }
    public void goGlobal(View v){
        try {
            Intent intent = new Intent(context, Global.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(context, "no intent", Toast.LENGTH_SHORT).show();}
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
            finish();
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }public void goSeson(View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    public void inBasket(View v){
        CollectionCloud.BasketList.add(dishModel.getNameDish());
    }

    private Spanned getShortenedText(String text, int maxLines) {
        String[] lines = text.split("\\. ");
        StringBuilder shortenedText = new StringBuilder();

        for (int i = 0; i < maxLines; i++) {
            if (i < lines.length) {
                shortenedText.append(lines[i]).append(". ");
            }
        }
        if (lines.length > maxLines) {
            shortenedText.append(" Дальше");
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(shortenedText);

        if (lines.length > maxLines) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    descDish.setText(text);
                }
            };
            builder.setSpan(clickableSpan, shortenedText.length() - 6, shortenedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            descDish.setMovementMethod(LinkMovementMethod.getInstance());
        }

        return builder;
    }

    private void findUI(){
        backImageDish = findViewById(R.id.backImageDish);
        categoryDish = findViewById(R.id.categoryDish);
        nameDish = findViewById(R.id.nameDish);
        datePostDish = findViewById(R.id.datePostDish);
        starDish = findViewById(R.id.starDish);

        descDish = findViewById(R.id.descDish);
        authorPostDish = findViewById(R.id.authorPostDish);
        descAuthorDish = findViewById(R.id.descAuthorDish);

        kitchenDish = findViewById(R.id.kitchenDish);
        cookingTimeDish = findViewById(R.id.cookingTimeDish);
        timeOnKitchen = findViewById(R.id.timeOnKitchen);

        spicyDish = findViewById(R.id.spicyDish);
        hardLvl = findViewById(R.id.hardLvl);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setUI(){

        backImageDish.setBackground(dishModel.getBackgroundImage());
        categoryDish.setBackground(getDrawable(dishModel.getCategoryDish()));
        starDish.setBackground(getDrawable(dishModel.getStarDish()));
        hardLvl.setBackground(getDrawable(dishModel.getDifficultyDish()));
        spicyDish.setBackground(getDrawable(dishModel.getSpicyDish()));

        nameDish.setText(dishModel.getNameDish());
        datePostDish.setText(dishModel.getDateForPost());
        authorPostDish.setText(dishModel.getDescriptionAuthorPost());
        kitchenDish.setText(dishModel.getKitchenDish());
        cookingTimeDish.setText(dishModel.getCookingTime());
        timeOnKitchen.setText(dishModel.getTimeOnKitchen());

        descDish.setText(getShortenedText(dishModel.getDescriptionDish(), 2));
        descAuthorDish.setText(dishModel.getDescriptionAuthorPost());

        ifInLover();


    }
    private void ifInLover(){
        for(DishModel model: CollectionCloud.loverDishList){
            if(model.getNameDish().equals(dishModel.getNameDish())){
                in_love.setBackground(getDrawable(R.drawable.in_love_2));
            }
        }
    }

    private DishModel findDishModel(Set<DishModel> dishList){
        DishModel dishmodel = null;
        for(DishModel model: dishList){
            if(model.getNameDish().equals(name)){
                dishmodel = model;
            }
        }return dishmodel;
    }

}