package com.mad.meatelicious;

/**
 * This is my Recipe_Steps class.
 * <p>
 * This is an Object Oriented approach to saving data into my system.
 */

public class Recipe_Steps {
    private String recipe_id;
    private String step_number;
    private String instructions;

    public Recipe_Steps() {
    }

    @Override
    public String toString() {
        return "Recipe_Steps{" +
                "recipe_id=" + recipe_id +
                ", step_number=" + step_number +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    public Recipe_Steps(String recipe_id, String step_number, String instructions) {
        this.recipe_id = recipe_id;
        this.step_number = step_number;
        this.instructions = instructions;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getStep_number() {
        return step_number;
    }

    public void setStep_number(String step_number) {
        this.step_number = step_number;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


}
