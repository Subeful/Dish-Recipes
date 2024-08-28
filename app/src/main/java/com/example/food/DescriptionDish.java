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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Help.BasketCloud;

public class DescriptionDish extends AppCompatActivity {

    ImageView backImageDish, categoryDish, starDish, spicyDish, hardLvl;
    TextView nameDish, datePostDish, descDish, authorPostDish, descAuthorDish, kitchenDish,
        cookingTimeDish, timeOnKitchen;

    String nameDishes;
    String fullDescription;

    Context context = DescriptionDish.this;

    @SuppressLint("MissingInflatedId")
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

        fullDescription = getIntent().getStringExtra("descDish");

        try {
            findUI();
            setUI();
        }catch (Exception e){
            Toast.makeText(this, "error in UI", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBasket(View v){
        try {
            Intent intent = new Intent(context, Basket.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(context, "no intent", Toast.LENGTH_SHORT).show();}
    }

    public void inBasket(View v){
        BasketCloud.dishInBasketList.add(nameDishes);
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
                    descDish.setText(fullDescription);
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

        backImageDish.setBackground(getDrawable(getIntent().getIntExtra("backImage", 0)));
        categoryDish.setBackground(getDrawable(getIntent().getIntExtra("categoryDish", 0)));
        starDish.setBackground(getDrawable(getIntent().getIntExtra("starDish", 0)));
        spicyDish.setBackground(getDrawable(getIntent().getIntExtra("spacyDish", 0)));
        hardLvl.setBackground(getDrawable(getIntent().getIntExtra("hardLvl", 0)));

        nameDish.setText(getIntent().getStringExtra("nameDish"));
        nameDishes = getIntent().getStringExtra("nameDish");
        datePostDish.setText(getIntent().getStringExtra("dataForPost"));
        descDish.setText(getShortenedText(fullDescription, 2));
        authorPostDish.setText(getIntent().getStringExtra("authorPost"));

        descAuthorDish.setText(getIntent().getStringExtra("descAuthorPost"));
        kitchenDish.setText(getIntent().getStringExtra("KitchenDish"));
        cookingTimeDish.setText(getIntent().getStringExtra("cookingTime"));
        timeOnKitchen.setText(getIntent().getStringExtra("timeOnKitchen"));
    }


}