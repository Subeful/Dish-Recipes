package com.example.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food.Help.CategoryDishLish;
import com.example.food.Help.CollectionCloud;
import com.example.food.Help.SaveUsersAccount;
import com.example.food.model.CategoryModel;
import com.example.food.model.DishModel;
import com.example.food.profile.IfNotAccount;
import com.example.food.profile.SingIn;

import java.util.LinkedList;

public class CreateCard extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;
    ImageView card_foto_layaut;
    ImageButton card_add_foto;

    EditText name, description, kitchen, time, allTime, date;

    private PopupWindow popupWindowPepper;
    private PopupWindow popupWindowCategory;
    private PopupWindow popupWindowLike;
    private PopupWindow popupWindowLvl;

    int spasy, lvl, category, like;

    CategoryModel categoryModel = null;
    int categoryDishs = 0;
    DishModel dishModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });getWindow().setNavigationBarColor(Color.parseColor("#37383B"));

        card_add_foto = findViewById(R.id.card_add_foto);
        setUI();

        ifNotAccount();
        setAvaIfHaveAccount();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri selectedImage = data.getData();
                    card_foto_layaut = findViewById(R.id.card_foto);
                    card_foto_layaut.setImageURI(selectedImage);
                    card_add_foto.setVisibility(View.INVISIBLE);
                }
            }
        });
        card_add_foto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/");
            launcher.launch(intent);
        });

        findViewById(R.id.card_spasy).setOnClickListener(view -> {showPopupMenuPepper(view);});
        findViewById(R.id.card_category).setOnClickListener(view -> {showPopupMenuCategory(view);});
        findViewById(R.id.card_like).setOnClickListener(view -> {showPopupMenuLike(view);});
        findViewById(R.id.card_lvl).setOnClickListener(view -> {showPopupMenuLvl(view);});


    }

    private void ifNotAccount(){
        if(SaveUsersAccount.usersAccount == null){
            Intent intent = new Intent(this, IfNotAccount.class);
            intent.putExtra("activity", "Создание рецепта доступно");
            startActivity(intent);
            finish();
        }
    }

    public void goToAva(View v){
        try {
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }catch (Exception e){Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();}
    }

    private void setAvaIfHaveAccount(){
        if(SaveUsersAccount.usersAccount != null) {
            ImageView main_ava = findViewById(R.id.main_ava);
            main_ava.setImageURI(SaveUsersAccount.usersAccount.getUserPhoto());
        }
    }

    private void setUI(){
        name = findViewById(R.id.card_name);
        description = findViewById(R.id.card_description);
        kitchen = findViewById(R.id.card_kitchen);
        time = findViewById(R.id.card_cooking_time);
        allTime = findViewById(R.id.card_all_time);
        date = findViewById(R.id.card_date);
    }

    public void createDish(View v){

        String nameDish;
        String cookingTime;
        String dateForPost;
        String descriptionDish;
        String authorPostDish;
        String descriptionAuthorPost;
        String kitchenDish;
        String timeOnKitchen;
        int idDish;
        int categoryDish;
        int difficultyDish;
        int starDish;
        int spicyDish;
        Drawable backgroundImage = null;


        idDish = CollectionCloud.commonDishList.size();
        nameDish = name.getText().toString();
        categoryDish = category;
        cookingTime = allTime.getText().toString();

        try {backgroundImage = card_foto_layaut.getDrawable();}
        catch (Exception e) {Toast.makeText(this, "Загрузите фотографию", Toast.LENGTH_SHORT).show();}

        difficultyDish = lvl;
        dateForPost = date.getText().toString();
        descriptionDish = description.getText().toString();
        authorPostDish = "Егор\nЯськин";
        descriptionAuthorPost = "Владелец\nFood Recipes";

        kitchenDish = kitchen.getText().toString();
        timeOnKitchen = time.getText().toString();
        starDish = like;
        spicyDish = spasy;

        if(firstBlock(idDish, nameDish, categoryDish, cookingTime, difficultyDish) &&
                secondBlock(dateForPost,descriptionDish, authorPostDish, descriptionAuthorPost) &&
                thirdBlock(kitchenDish, timeOnKitchen, starDish, spicyDish)) {

            try {

                dishModel = new DishModel(idDish, nameDish, categoryDish, cookingTime, backgroundImage, difficultyDish, dateForPost, descriptionDish, authorPostDish, descriptionAuthorPost, kitchenDish, timeOnKitchen, starDish, spicyDish);

                getCategory();
                setDishInCategory(nameDish);
                setDishInCommonAndMyRecipe(nameDish);


            } catch (Exception e){Toast.makeText(this, "Error: don't create", Toast.LENGTH_SHORT).show();}
        }
        else Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();

    }

    private void getCategory(){
        if(category == R.drawable.dish_category_first)  {
            categoryDishs = 1;
            categoryModel = CollectionCloud.commonCategoryList.get(0);
        }
        else if(category == R.drawable.dish_category_second)  {
            categoryDishs = 2;
            categoryModel = CollectionCloud.commonCategoryList.get(1);
        }
        else if(category == R.drawable.dish_category_salad)  {
            categoryDishs = 3;
            categoryModel = CollectionCloud.commonCategoryList.get(2);
        }
        else if(category == R.drawable.dish_category_snacs)  {
            categoryDishs = 4;
            categoryModel = CollectionCloud.commonCategoryList.get(3);
        }
    }

    private void setDishInCategory(String nameDish){
        if(categoryModel != null){
            int flagCategory = 0;

            for(DishModel model: categoryModel.getDishList()){
                if(model.getNameDish().equals(nameDish)){
                    flagCategory = 1;
                }
            }
            if(flagCategory == 0) {

                switch (categoryDishs){
                    case 1: {CategoryDishLish.first.add(dishModel);break;}
                    case 2: {CategoryDishLish.second.add(dishModel);break;}
                    case 3: {CategoryDishLish.salad.add(dishModel);break;}
                    case 4: {CategoryDishLish.snack.add(dishModel);break;}
                    default:;
                }

                Toast.makeText(this, "Рецепт сохранён", Toast.LENGTH_SHORT).show();

            } else Toast.makeText(this, "Такое блюдо уже есть", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDishInCommonAndMyRecipe(String nameDish){
        int flagMyRecipe = 0;

        for(DishModel model: CollectionCloud.myRecipeList){
            if(model.getNameDish().equals(nameDish)){
                flagMyRecipe = 1;
            }
        }
        if(flagMyRecipe == 0){
            CollectionCloud.commonDishList.add(dishModel);
            CollectionCloud.myRecipeList.add(dishModel);
        }
    }



    private boolean firstBlock(int idDish, String nameDish, int categoryDish,  String cookingTime, int difficultyDish){
        if(idDish != 0 && (nameDish != null && !nameDish.isEmpty()) && categoryDish != 0 &&
                (cookingTime != null && !cookingTime.isEmpty())  && difficultyDish != 0)
            return true;
        else return false;
    }
    private boolean secondBlock(String dateForPost, String descriptionDish, String authorPostDish,
                                String descriptionAuthorPost){
        if((dateForPost != null && !dateForPost.isEmpty())  && (descriptionAuthorPost != null && !descriptionAuthorPost.isEmpty()) &&
                (descriptionDish != null && !descriptionDish.isEmpty()) && (authorPostDish != null && !authorPostDish.isEmpty()))
            return true;
        else return false;
    }
    private boolean thirdBlock(String kitchenDish, String timeOnKitchen, int starDish, int spicyDish){
        if((kitchenDish != null && !kitchenDish.isEmpty())  && (timeOnKitchen != null && !timeOnKitchen.isEmpty()) &&
                starDish != 0 && spicyDish != 0)
            return true;
        else return false;
    }


    private void clickToLvl(View views){
        switch (Integer.parseInt(views.getTag().toString())){
            case 1: {lvl = R.drawable.lvl_1; break;}
            case 2: {lvl = R.drawable.lvl_2; break;}
            case 3: {lvl = R.drawable.lvl_3; break;}
            case 4: {lvl = R.drawable.lvl_4; break;}
            case 5: {lvl = R.drawable.lvl_5; break;}
            default: ;
        }
        popupWindowLvl.dismiss();
        TextView textView = findViewById(R.id.card_text_lvl);
        textView.setText("Сложность: " + views.getTag());
    }
    private void showPopupMenuLvl(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.card_menu_hard_lvl, null);

        ImageView image1 = popupView.findViewById(R.id.card_lvls_switch_1);
        ImageView image2 = popupView.findViewById(R.id.card_lvls_switch_2);
        ImageView image3 = popupView.findViewById(R.id.card_lvls_switch_3);
        ImageView image4 = popupView.findViewById(R.id.card_lvls_switch_4);
        ImageView image5 = popupView.findViewById(R.id.card_lvls_switch_5);

        image1.setOnClickListener(view1 -> {
            clickToLvl(view1);
        });
        image2.setOnClickListener(view2 -> {
            clickToLvl(view2);
        });
        image3.setOnClickListener(view3 -> {
            clickToLvl(view3);
        });
        image4.setOnClickListener(view4 -> {
            clickToLvl(view4);
        });
        image5.setOnClickListener(view5 -> {
            clickToLvl(view5);
        });

        popupWindowLvl = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true);
        popupWindowLvl.showAsDropDown(view, 105, -view.getHeight());
    }

    private void clickToLike(View views){
        switch (Integer.parseInt(views.getTag().toString())){
            case 1: {like = R.drawable.star_1; break;}
            case 2: {like = R.drawable.star_2; break;}
            case 3: {like = R.drawable.star_3; break;}
            case 4: {like = R.drawable.star_3_5; break;}
            case 5: {like = R.drawable.star_4; break;}
            case 6: {like = R.drawable.star_4_5; break;}
            case 7: {like = R.drawable.star_5; break;}
            default: ;
        }
        popupWindowLike.dismiss();
        TextView textView = findViewById(R.id.card_text_like);
        textView.setText("Оценка: " + views.getTag());
    }
    private void showPopupMenuLike(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.card_menu_lvl, null);

        ImageView image1 = popupView.findViewById(R.id.card_lvl_switch_1);
        ImageView image2 = popupView.findViewById(R.id.card_lvl_switch_2);
        ImageView image3 = popupView.findViewById(R.id.card_lvl_switch_3);
        ImageView image4 = popupView.findViewById(R.id.card_lvl_switch_4);
        ImageView image5 = popupView.findViewById(R.id.card_lvl_switch_5);
         ImageView image6 = popupView.findViewById(R.id.card_lvl_switch_6);
        ImageView image7 = popupView.findViewById(R.id.card_lvl_switch_7);

        image1.setOnClickListener(view1 -> {
            clickToLike(view1);
        });
        image2.setOnClickListener(view2 -> {
            clickToLike(view2);
        });
        image3.setOnClickListener(view3 -> {
            clickToLike(view3);
        });
        image4.setOnClickListener(view4 -> {
            clickToLike(view4);
        });
        image5.setOnClickListener(view5 -> {
            clickToLike(view5);
        });
        image6.setOnClickListener(view6 -> {
            clickToLike(view6);
        });
        image7.setOnClickListener(view7 -> {
            clickToLike(view7);
        });

        popupWindowLike = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true);
        popupWindowLike.showAsDropDown(view, 105, -view.getHeight());
    }


    private void clickToCategory(View views){
        switch ( Integer.parseInt(views.getTag().toString())){
            case 1: {category = R.drawable.dish_category_first; break;}
            case 2: {category = R.drawable.dish_category_second; break;}
            case 3: {category = R.drawable.dish_category_salad; break;}
            case 4: {category = R.drawable.dish_category_snacs; break;}
        }
        popupWindowCategory.dismiss();
        TextView textView = findViewById(R.id.card_text_category);
        textView.setText("Категория: " + views.getTag());
    }
    private void showPopupMenuCategory(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.card_menu_category, null);

        ImageView image1 = popupView.findViewById(R.id.card_category_switch_1);
        ImageView image2 = popupView.findViewById(R.id.card_category_switch_2);
        ImageView image3 = popupView.findViewById(R.id.card_category_switch_3);
        ImageView image4 = popupView.findViewById(R.id.card_category_switch_4);

        image1.setOnClickListener(view1 -> {
            clickToCategory(view1);
        });
        image2.setOnClickListener(view2 -> {
            clickToCategory(view2);
        });
        image3.setOnClickListener(view3 -> {
            clickToCategory(view3);
        });
        image4.setOnClickListener(view4 -> {
            clickToCategory(view4);
        });

        popupWindowCategory = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true);
        popupWindowCategory.showAsDropDown(view, 80, -view.getHeight());
    }


    private void clickToPepper(View views){
        switch (Integer.parseInt(views.getTag().toString())){
            case 1: {spasy = R.drawable.pepper_1; break;}
            case 2: {spasy = R.drawable.pepper_2; break;}
            case 3: {spasy = R.drawable.pepper_3; break;}
            case 4: {spasy = R.drawable.pepper_4; break;}
            case 5: {spasy = R.drawable.pepper_5; break;}
            default: ;
        }
        popupWindowPepper.dismiss();
        TextView textView = findViewById(R.id.card_text_spasy);
        textView.setText("Острота: " + views.getTag());
    }
    private void showPopupMenuPepper(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.card_menu_spasy, null);

        ImageView image1 = popupView.findViewById(R.id.card_pepper_switch_1);
        ImageView image2 = popupView.findViewById(R.id.card_pepper_switch_2);
        ImageView image3 = popupView.findViewById(R.id.card_pepper_switch_3);
        ImageView image4 = popupView.findViewById(R.id.card_pepper_switch_4);
        ImageView image5 = popupView.findViewById(R.id.card_pepper_switch_5);

        image1.setOnClickListener(view1 -> {
            clickToPepper(view1);
        });
        image2.setOnClickListener(view2 -> {
            clickToPepper(view2);
        });
        image3.setOnClickListener(view3 -> {
            clickToPepper(view3);
        });
        image4.setOnClickListener(view4 -> {
            clickToPepper(view4);
        });
        image5.setOnClickListener(view5 -> {
            clickToPepper(view5);
        });

        popupWindowPepper = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true);
        popupWindowPepper.showAsDropDown(view, 85, -view.getHeight());
    }


    public void goBasket (View v){
        try {
            Intent intent = new Intent(this, Basket.class);
            intent.putExtra("activity", "Корзина доступна");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();
        }
    }
    public void goGlobal (View v){
        try {
            Intent intent = new Intent(this, Global.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();
        }
    }
    public void goSearch (View v){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();
        }
    }
    public void goSetting (View v){
        try {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();
        }
    }
    public void goSeson (View v){
        try {
            Intent intent = new Intent(this, Season.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no intent", Toast.LENGTH_SHORT).show();
        }
    }

}