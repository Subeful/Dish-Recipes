package com.example.food.model;

import com.example.food.R;

import java.io.Serializable;

public class MyDishRecipe implements Serializable{

        int idDish;
        String nameDish;
        int categoryDish;
        String cookingTime;
        int backgroundImage;
        int difficultyDish;

        String dateForPost;
        String descriptionDish;
        String authorPostDish;
        String descriptionAuthorPost;

        String KitchenDish;
        String timeOnKitchen;
        int starDish;
        int spicyDish;

        int isLover = R.drawable.in_love_1;

        int flagOnLove;

        public MyDishRecipe(int idDish, String nameDish, int categoryDish, String cookingTime, int backgroundImage,
                         int difficultyDish, String dateForPost, String descriptionDish, String authorPostDish,
                         String descriptionAuthorPost, String kitchenDish, String timeOnKitchen, int starDish, int spicyDish) {
            this.idDish = idDish;
            this.nameDish = nameDish;
            this.categoryDish = categoryDish;
            this.cookingTime = cookingTime;
            this.backgroundImage = backgroundImage;
            this.difficultyDish = difficultyDish;
            this.dateForPost = dateForPost;
            this.descriptionDish = descriptionDish;
            this.authorPostDish = authorPostDish;
            this.descriptionAuthorPost = descriptionAuthorPost;
            KitchenDish = kitchenDish;
            this.timeOnKitchen = timeOnKitchen;
            this.starDish = starDish;
            this.spicyDish = spicyDish;
        }


        //    public DishModel(int idDish, String nameDish, int categoryDish, String cookingTime, int backgroundImage,
//                     int difficultyDish, String dateForPost, String descriptionDish, String authorPostDish,
//                     String descriptionAuthorPost, String kitchenDish, String timeOnKitchen, int starDish,
//                     int spicyDish, int isLover) {
//        this.idDish = idDish;
//        this.nameDish = nameDish;
//        this.categoryDish = categoryDish;
//        this.cookingTime = cookingTime;
//        this.backgroundImage = backgroundImage;
//        this.difficultyDish = difficultyDish;
//        this.dateForPost = dateForPost;
//        this.descriptionDish = descriptionDish;
//        this.authorPostDish = authorPostDish;
//        this.descriptionAuthorPost = descriptionAuthorPost;
//        KitchenDish = kitchenDish;
//        this.timeOnKitchen = timeOnKitchen;
//        this.starDish = starDish;
//        this.spicyDish = spicyDish;
//        this.isLover = isLover;
//    }
        public int getCategoryDish() {
            return categoryDish;
        }

        public int getIsLover() {
            return isLover;
        }
        public void setIsLover(int isLover) {
            this.isLover = isLover;
        }
        public void setCategoryDish(int categoryDish) {
            this.categoryDish = categoryDish;
        }
        public String getDateForPost() {
            return dateForPost;
        }
        public void setDateForPost(String dateForPost) {
            this.dateForPost = dateForPost;
        }
        public String getDescriptionDish() {
            return descriptionDish;
        }
        public void setDescriptionDish(String descriptionDish) {
            this.descriptionDish = descriptionDish;
        }
        public String getAuthorPostDish() {
            return authorPostDish;
        }
        public void setAuthorPostDish(String authorPostDish) {
            this.authorPostDish = authorPostDish;
        }
        public String getDescriptionAuthorPost() {
            return descriptionAuthorPost;
        }
        public void setDescriptionAuthorPost(String descriptionAuthorPost) {
            this.descriptionAuthorPost = descriptionAuthorPost;
        }
        public String getKitchenDish() {
            return KitchenDish;
        }
        public void setKitchenDish(String kitchenDish) {
            KitchenDish = kitchenDish;
        }
        public String getTimeOnKitchen() {
            return timeOnKitchen;
        }
        public void setTimeOnKitchen(String timeOnKitchen) {
            this.timeOnKitchen = timeOnKitchen;
        }
        public int getStarDish() {
            return starDish;
        }
        public void setStarDish(int starDish) {
            this.starDish = starDish;
        }
        public int getSpicyDish() {
            return spicyDish;
        }
        public void setSpicyDish(int spicyDish) {
            this.spicyDish = spicyDish;
        }
        public int getIdDish() {
            return idDish;
        }
        public void setIdDish(int idDish) {
            this.idDish = idDish;
        }
        public String getNameDish() {
            return nameDish;
        }
        public void setNameDish(String nameDish) {
            this.nameDish = nameDish;
        }
        public String getCookingTime() {
            return cookingTime;
        }
        public void setCookingTime(String cookingTime) {
            this.cookingTime = cookingTime;
        }
        public int getBackgroundImage() {
            return backgroundImage;
        }
        public void setBackgroundImage(int backgroundImage) {
            this.backgroundImage = backgroundImage;
        }
        public int getDifficultyDish() {
            return difficultyDish;
        }
        public void setDifficultyDish(int difficultyDish) {
            this.difficultyDish = difficultyDish;
        }
        public int getFlagOnLove() {
            return flagOnLove;
        }
        public void setFlagOnLove(int flagOnLove) {
            this.flagOnLove = flagOnLove;
        }

}
