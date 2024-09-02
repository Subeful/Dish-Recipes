package com.example.food.Help;

import android.content.Context;

import com.example.food.DescriptionDish;
import com.example.food.R;
import com.example.food.model.DishModel;
import com.example.food.model.SeasonProductModel;

import java.util.ArrayList;
import java.util.List;

public class SetterInDish {

    static Context context = new DescriptionDish().getBaseContext();
        ////1
    public static List<DishModel> setFirstDish(){
        List<DishModel> dishList = new ArrayList<>();

        dishList.add(new DishModel(1, "Московская солянка", R.drawable.dish_category_first,  "1 час", R.drawable.view_dish_global,
                R.drawable.lvl_4, "1 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Егор \nЯськин", "Администратор \nFood Recipe",
                "Россия", "50 мин", R.drawable.star_4, R.drawable.pepper_1));

        dishList.add(new DishModel(2, "Окрошка", R.drawable.dish_category_first,  "30 мин", R.drawable.dish_first_okroshka,
                R.drawable.lvl_2, "12 июля", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Россия", "25 мин", R.drawable.star_4_5, R.drawable.pepper_1));

        dishList.add(new DishModel(3, "Томатный суп", R.drawable.dish_category_first,  "50 мин", R.drawable.dish_first_tomat_soup,
                R.drawable.lvl_2, "13 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Пётр \nЧинаев", "Читатель и автор \nFood Recipe",
                "Европейская", "20 мин", R.drawable.star_2, R.drawable.pepper_2));

        for(DishModel model: dishList){
            CollectionCloud.commonDishList.add(model);
        }

        return dishList;
    }

        ////2
    public static List<DishModel> setSecondDish(){
        List<DishModel> dishList = new ArrayList<>();

        dishList.add(new DishModel(4, "Паста карбонара", R.drawable.dish_category_second,  "30 мин", R.drawable.dish_second_carbonara,
                R.drawable.lvl_2, "22 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Альберт \nЗунин", "Читатель и автор \nFood Recipe",
                "Итальянская", "30 мин", R.drawable.star_4_5, R.drawable.pepper_1));

        dishList.add(new DishModel(5, "Картошка в мундире", R.drawable.dish_category_second,  "1.5 часа", R.drawable.dish_second_potato,
                R.drawable.lvl_1, "19 июня", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Россия", "25 мин", R.drawable.star_5, R.drawable.pepper_1));

        dishList.add(new DishModel(6, "Мясная запеканка с овощами", R.drawable.dish_category_second,  "1.5 часа", R.drawable.dish_second_zapecanka,
                R.drawable.lvl_2, "13 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Россия", "20 мин", R.drawable.star_3_5, R.drawable.pepper_2));

        for(DishModel model: dishList){
            CollectionCloud.commonDishList.add(model);
        }

        return dishList;
    }

        ////3
    public static List<DishModel> setSaladDish(){
        List<DishModel> dishList = new ArrayList<>();

        dishList.add(new DishModel(7, "Оливье", R.drawable.dish_category_salad,  "30 мин", R.drawable.dish_salad_olive,
                R.drawable.lvl_1, "29 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Альберт \nЗунин", "Читатель и автор \nFood Recipe",
                "Россия", "20 мин", R.drawable.star_5, R.drawable.pepper_1));

        dishList.add(new DishModel(8, "Салат мимоза", R.drawable.dish_category_salad,  "50 мин", R.drawable.dish_salad_mimoza,
                R.drawable.lvl_2, "23 июля", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Россия", "20 мин", R.drawable.star_4_5, R.drawable.pepper_1));

        dishList.add(new DishModel(9, "Салат летний", R.drawable.dish_category_salad,  "5 мин", R.drawable.dish_salad_summer,
                R.drawable.lvl_1, "13 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Альберт \nЗунин", "Читатель и автор \nFood Recipe",
                "Россия", "5 мин", R.drawable.star_5, R.drawable.pepper_1));

        dishList.add(new DishModel(10, "Селёдка под шубой", R.drawable.dish_category_salad,  "45 мин", R.drawable.dish_salad_seledka,
                R.drawable.lvl_2, "30 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Пётр \nЧинаев", "Читатель и автор \nFood Recipe",
                "Интернацинальная", "30 мин", R.drawable.star_4, R.drawable.pepper_1));

        for(DishModel model: dishList){
            CollectionCloud.commonDishList.add(model);
        }

        return dishList;
    }

        ////4
    public static List<DishModel> setSnackDish(){
        List<DishModel> dishList = new ArrayList<>();

        dishList.add(new DishModel(11, "Бутерброды \"народные\"", R.drawable.dish_category_snacs,  "5 мин", R.drawable.dish_snacks_buter_narod,
                R.drawable.lvl_1, "13 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Пётр \nЧинаев", "Читатель и автор \nFood Recipe",
                "Россия", "5 мин", R.drawable.star_5, R.drawable.pepper_1));

        dishList.add(new DishModel(12, "Грибы в беконе", R.drawable.dish_category_snacs,  "30 мин", R.drawable.dish_snacks_grib_in_becon,
                R.drawable.lvl_3, "13 июля", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Альберт \nЗунин", "Читатель и автор \nFood Recipe",
                "Россия", "25 мин", R.drawable.star_4_5, R.drawable.pepper_2));

        dishList.add(new DishModel(13, "Кимчи из баклажана", R.drawable.dish_category_snacs,  "76 часов", R.drawable.dish_snacks_kimchi_in_baclajan,
                R.drawable.lvl_3, "10 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Азия", "30 мин", R.drawable.star_1, R.drawable.pepper_4));

        dishList.add(new DishModel(14, "Сырные колечки", R.drawable.dish_category_snacs,  "25 мин", R.drawable.dish_snaks_chees_kolco,
                R.drawable.lvl_2, "8 августа", "The purple elephant danced a waltz with the invisible rhinoceros. The clock tower sang opera while the moon ate cheese. My shoe is a sentient being and it wants to go to the beach. The tree whispered secrets to the wind, who then told them to the clouds. The spoon is angry because it can't eat the soup.",
                "Анна \nШишкина", "Читатель и автор \nFood Recipe",
                "Америка", "20 мин", R.drawable.star_4, R.drawable.pepper_1));

        for(DishModel model: dishList){
            CollectionCloud.commonDishList.add(model);
        }

        return dishList;
    }

    public static List<DishModel> setBakeryDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }

    public static List<DishModel> setSouseDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }

    public static List<DishModel> setPrepareDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }

    public static List<DishModel> setDrinksDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }

    public static List<DishModel> setDessertDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }


    public static List<DishModel> setGarnishDish(){
        List<DishModel> dishList = new ArrayList<>();
        return dishList;
    }

    public static List<SeasonProductModel> setSeasonProduct(){
        List<SeasonProductModel> list = new ArrayList<>();
        if(CollectionCloud.flagSeasonProductList == 0){
            list.add(new SeasonProductModel(1, R.drawable.product_currant, "Смородина"));
            list.add(new SeasonProductModel(2, R.drawable.product_apricot, "Абрикос"));
            list.add(new SeasonProductModel(3, R.drawable.product_zucchini, "Кабачок"));
            list.add(new SeasonProductModel(3, R.drawable.product_lemonade, "Лимонад"));
            list.add(new SeasonProductModel(3, R.drawable.product_mushrooms, "Лисички"));
            list.add(new SeasonProductModel(3, R.drawable.product_jam, "Варенье"));
            CollectionCloud.flagSeasonProductList = 1;
        }
        return list;
    }





}


