package com.example.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.example.food.Help.BasketCloud;
import com.example.food.Help.CollectionCloud;
import com.example.food.model.DishModel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DescriptionDish extends AppCompatActivity {

    ImageView backImageDish, categoryDish, starDish, spicyDish, hardLvl;
    TextView nameDish, datePostDish, descDish, authorPostDish, descAuthorDish, kitchenDish,
        cookingTimeDish, timeOnKitchen;
    ImageButton in_love;

    String name;
    DishModel dishModel;

    static int lastNotLoveInd = 183;

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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

        in_love = findViewById(R.id.in_love);

        try {
            name = getIntent().getStringExtra("name");
            dishModel = findDishModel(CollectionCloud.commonDishList);
            if(dishModel == null) throw new Exception();
            findUI();
            setUI();
        }catch (Exception e){
            Toast.makeText(this, "error in UI", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void inLove(View v){
        try {
            int x = 0;

            if(dishModel.getFlagOnLove() == 0){
                v.setBackground(getDrawable(R.drawable.in_love_2));
                dishModel.setFlagOnLove(1);
                dishModel.setIdDish(R.drawable.in_love_2);

                for(DishModel model: CollectionCloud.loverDishList){
                    if (model.getNameDish().equals(dishModel.getNameDish())) {
                        x = 1;
                        break;
                    }
                }
                if(x == 0)
                    CollectionCloud.loverDishList.add(dishModel);


            }else if(dishModel.getFlagOnLove() == 1){
                v.setBackground(getDrawable(R.drawable.in_love_1));
                dishModel.setFlagOnLove(0);
                dishModel.setIsLover(R.drawable.in_love_2);

                CollectionCloud.loverDishList.removeIf(model -> model.getNameDish().equals(dishModel.getNameDish()));
                for(int i = 0; i < CollectionCloud.loverDishList.size(); i++){
                    if(CollectionCloud.loverDishList.get(i).getNameDish().equals(name)){
                        lastNotLoveInd = i;
                        CollectionCloud.loverDishList.remove(CollectionCloud.loverDishList.get(i));
                    }
                }
            }
        }catch (Exception e){
            Toast.makeText(context, "error love", Toast.LENGTH_SHORT).show();
        }

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
    }
    public void inBasket(View v){
        BasketCloud.dishInBasketList.add(dishModel.getNameDish());
    }

    private Spanned getShortenedText(String text, int maxLines) {
        String[] lines = text.split("\\. ");
        StringBuilder shortenedText = new StringBuilder();

        // Добавляем первые maxLines строк
        for (int i = 0; i < maxLines; i++) {
            if (i < lines.length) {
                shortenedText.append(lines[i]).append(". ");
            }
        }
        // Добавляем троеточие, если текст был сокращен
        if (lines.length > maxLines) {
            shortenedText.append(" Дальше");
        }

        // Создаем SpannableStringBuilder для создания ссылки "Читать далее"
        SpannableStringBuilder builder = new SpannableStringBuilder(shortenedText);

        // Добавляем ссылку "Читать далее" в конце сокращенного текста
        if (lines.length > maxLines) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // Обработка клика, например, отображаем весь текст
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

        backImageDish.setBackground(getDrawable(dishModel.getBackgroundImage()));
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