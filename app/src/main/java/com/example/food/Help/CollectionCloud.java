package com.example.food.Help;

import com.example.food.model.CategoryModel;
import com.example.food.model.DishModel;
import com.example.food.model.StatesModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollectionCloud{

    public static int flagCommonCategoryList = 0;
    public static LinkedList<CategoryModel> commonCategoryList = new LinkedList<>();

    public static LinkedHashSet<DishModel> commonDishList = new LinkedHashSet<>();

    public static int flagStatesList = 0;
    public static List<StatesModel> statesList = new ArrayList<>();

    public static LinkedList<DishModel> loverDishList = new LinkedList<>();


    public static LinkedList<DishModel> lastDishList = new LinkedList<>();

    public static LinkedList<DishModel> myRecipeList = new LinkedList<>();

    public static LinkedList<DishModel> lastSeeDishList = new LinkedList<>();

    public static void setIfNotExist(LinkedList<DishModel> list, DishModel dishModel){
        for(DishModel model: list){
            if(!(model.getNameDish().equals(dishModel.getNameDish()))) {
                list.addLast(dishModel);
                break;
            }
        }
    }

}

