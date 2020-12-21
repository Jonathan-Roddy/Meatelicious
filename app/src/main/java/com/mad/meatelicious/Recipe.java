package com.mad.meatelicious;

/**
 * This is my Recipe class.
 * <p>
 * This is an Object Oriented approach to saving data into my system.
 */

public class Recipe {
    private String recipe_id;
    private String name;
    private String description;
    private String category;
    private String prep_time;
    private String cook_time;
    private String serving;
    private String author;
    private String difficulty;
    private int thumbnail;

    public Recipe(String id, String name, String difficulty, int cover) {
        this.recipe_id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.thumbnail = cover;
    }

    public Recipe() {

    }

    public int getThumbnail() {
        return thumbnail;
    }

    public Recipe(String recipe_id, String name, String description, String category, String prep_time, String cook_time, String serving, String author, String difficulty) {
        this.recipe_id = recipe_id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.prep_time = prep_time;
        this.cook_time = cook_time;
        this.serving = serving;
        this.author = author;
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipe_id='" + recipe_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", prep_time='" + prep_time + '\'' +
                ", cook_time='" + cook_time + '\'' +
                ", serving='" + serving + '\'' +
                ", author='" + author + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }

    public String getCook_time() {
        return cook_time;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
