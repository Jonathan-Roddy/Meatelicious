package com.mad.meatelicious;

public class Ingredient {
    private String ingredient_id;
    private String name;

    public Ingredient(){
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id=" + ingredient_id +
                ", name='" + name + '\'' +
                '}';
    }

    public Ingredient(String ingredient_id, String name) {
        this.ingredient_id = ingredient_id;
        this.name = name;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
