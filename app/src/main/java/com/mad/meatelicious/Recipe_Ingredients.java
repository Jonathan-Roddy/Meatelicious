package com.mad.meatelicious;

/**
 * This is my Recipe_Ingredients class.
 * <p>
 * This is an Object Oriented approach to saving data into my system.
 */

public class Recipe_Ingredients {
    private String recipe_id;
    private String ingredient_id;
    private String amount;

    public Recipe_Ingredients() {
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public Recipe_Ingredients(String recipe_id, String ingredient_id, String amount) {
        this.recipe_id = recipe_id;
        this.ingredient_id = ingredient_id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Recipe_Ingredients{" +
                "recipe_id='" + recipe_id + '\'' +
                ", ingredient_id='" + ingredient_id + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
