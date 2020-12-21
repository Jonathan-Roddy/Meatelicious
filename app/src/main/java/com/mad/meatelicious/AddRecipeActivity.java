package com.mad.meatelicious;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This is my AddRecipeActivity class.
 * <p>
 * This will show the user a view with some editText views to add a new recipe
 * This is not working as I did not have enough time to finish off the coding due to consistent errors that took 5 days to try to fix.
 */

public class AddRecipeActivity extends AppCompatActivity {


    public EditText recipeName;
    public EditText recipeAuthorNameRecipe;
    public EditText recipeDifficulty;
    public EditText recipeServes;
    public EditText recipePrep;
    public EditText recipeCook;
    public EditText recipeDescription;
    public EditText recipeIngredient;
    public Button buttonSave;

    public ArrayList recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_add_recipe
        );

        recipeList = new ArrayList<>();
        final String recipe_id = "5";

        recipeName = findViewById(R.id.recipeName);
        recipeAuthorNameRecipe = findViewById(R.id.recipeAuthorNameRecipe);
        recipeDifficulty = findViewById(R.id.recipeDifficulty);
        recipeServes = findViewById(R.id.recipeServes);
        recipePrep = findViewById(R.id.recipePrep);
        recipeCook = findViewById(R.id.recipeCook);
        recipeDescription = findViewById(R.id.recipeDescription);
        recipeIngredient = findViewById(R.id.recipeIngredient);

        final String recipeName1 = String.valueOf(recipeName);
        final String recipeAuthorName1 = String.valueOf(recipeAuthorNameRecipe);
        final String recipeDifficulty1 = String.valueOf(recipeDifficulty);
        final String recipeServes1 = String.valueOf(recipeServes);
        final String recipePrep1 = String.valueOf(recipePrep);
        final String recipeCook1 = String.valueOf(recipeCook);
        final String recipeDescription1 = String.valueOf(recipeDescription);
        String recipeIngredient1 = String.valueOf(recipeIngredient);


        //Recipe(String recipe_id, String name, String description, String category, String prep_time, String cook_time, String serving, String author, String difficulty)

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe newRecipe = new Recipe(recipe_id, recipeName1, recipeDescription1, "Meat", recipePrep1, recipeCook1, recipeServes1, recipeAuthorName1, recipeDifficulty1);
                addNewRecipe(newRecipe);
                System.out.println(recipe_id + recipeName1 + recipeDescription1 + "Meat" + recipePrep1 + recipeCook1 + recipeServes1 + recipeAuthorName1 + recipeDifficulty1);
            }
        });


    }

    private void addNewRecipe(Recipe newRecipe) {

        System.out.println("//////////////////////////////////// newRecipe: " + newRecipe);

    }
}
