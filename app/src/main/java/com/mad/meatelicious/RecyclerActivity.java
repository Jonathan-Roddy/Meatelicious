package com.mad.meatelicious;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.meatelicious.food.Food1Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is my RecyclerActivity class.
 * <p>
 * This will show the user a list of recipes in a recycler view format
 * Each recipe will be in a card view allowing for better UI
 * <p>
 * I have had many issues trying to get this recycler view working as well as getting to pull the data from firbase
 * <p>
 * I have had to resort to hardcoding in order to show you that I cam able to work and manipulate data into recycler view and card view
 * Firebase is set up and is connecting. On output screen you can see the items being passed.
 */

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    //    private List<Album> albumList;
    private static final String TAG = "MainActivity";

    private AppBarConfiguration mAppBarConfiguration;


    // this will store my lists from firebase
    final public List<Ingredient> ingredientlistFromFirebase = new ArrayList<Ingredient>();
    final public List<Recipe> recipelistFromFirebase = new ArrayList<Recipe>();
    final public List<Recipe_Ingredients> recipeIngredientlistFromFirebase = new ArrayList<Recipe_Ingredients>();
    final public List<Recipe_Steps> recipeStepslistFromFirebase = new ArrayList<Recipe_Steps>();

    // this will store my list of ingredients
    public List<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    public List<Recipe> recipelist;
    public List<Recipe_Ingredients> recipeIngredientlist = new ArrayList<Recipe_Ingredients>();
    public List<Recipe_Steps> recipeStepslist = new ArrayList<Recipe_Steps>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cardview);

        // Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding a new Recipe", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                Intent a = new Intent(RecyclerActivity.this, AddRecipeActivity.class);
                startActivity(a);
            }
        });


        recyclerView = findViewById(R.id.recycler_view);

//        albumList = new ArrayList<>();
//        recipelist = new ArrayList<>();


        prepareFirebase();

        prepareRecipe();
//        prepareAlbums();

//        AlbumAdapter albumAdapter = new AlbumAdapter(this, albumList);
        recipeAdapter = new RecipeAdapter(this, recipelist);

        prepareRecyclerView();

//        System.out.println("//////////////////////////// inside main- hardcode"+ recipelist);

    }

    private void prepareRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recipeAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //start new activity here
                Intent i = new Intent(getBaseContext(), Food1Activity.class);
                i.putExtra("position", position);

                startActivity(i);
                System.out.println("////////////////////////// position " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
//        recyclerView.setAdapter(albumAdapter);
    }


    /**
     * Adding firebase
     */
    private void prepareFirebase() {

        // firebase
        DatabaseReference dbIngredient = FirebaseDatabase.getInstance().getReference("0/ingredient/");
        // Attach a listener to read the data at our posts reference
        dbIngredient.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for (DataSnapshot child : children) {
//                    System.out.println(child);
//                    System.out.println("///////////// " + child.getValue());
                    Ingredient ingredient = child.getValue(Ingredient.class);
                    ingredientlistFromFirebase.add(ingredient);
                }

//                /**
//                 *  uncomment this to see list display to the console
//                 */
//                System.out.println("///////////// ingredientlistFromFirebase  " + "\n" + ingredientlistFromFirebase.toString());
//                for(Ingredient ing : ingredientlist)
//                {
//                    System.out.println("///////////////////// get name: " + ing.getName());
//                    System.out.println("///////////////////// get id: " + ing.getIngredient_id());
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        // End goal
        ingredientlist = ingredientlistFromFirebase;


        ////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference dbRecipe = FirebaseDatabase.getInstance().getReference("0/recipe");
        // Attach a listener to read the data at our posts reference
        dbRecipe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for (DataSnapshot child : children) {
                    Recipe recipe = child.getValue(Recipe.class);
                    recipelistFromFirebase.add(recipe);
                }
//                System.out.println("////////////////////////////////// recipelistFromFirebase : " +recipelistFromFirebase);
//                System.out.println("///////////// recipelistFromFirebase  " + "\n" + recipelistFromFirebase.toString());
//                for(Recipe rep : recipelist)
//                {
//                    System.out.println("///////////////////// getName: " + rep.getName());
//                    System.out.println("///////////////////// getDifficulty: " + rep.getDifficulty());
//                }

                storageContainer(recipelistFromFirebase);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // End goal
        recipelist = recipelistFromFirebase;

        ////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference dbRecipeIngredients = FirebaseDatabase.getInstance().getReference("0/recipe_ingredients");
        // Attach a listener to read the data at our posts reference
        dbRecipeIngredients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for (DataSnapshot child : children) {
                    Recipe_Ingredients recipeIngredient = child.getValue(Recipe_Ingredients.class);
                    recipeIngredientlistFromFirebase.add(recipeIngredient);
                }

//                System.out.println("///////////// recipeIngredientlistFromFirebase  " + "\n" + recipeIngredientlistFromFirebase.toString());
//                for(Recipe_Ingredients repIng : recipeIngredientlist)
//                {
//                    System.out.println("///////////////////// getIngredient_id: " + repIng.getIngredient_id());
//                    System.out.println("///////////////////// getAmount: " + repIng.getAmount());
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // End goal
        recipeIngredientlist = recipeIngredientlistFromFirebase;

        ////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference dbRecipeSteps = FirebaseDatabase.getInstance().getReference("0/recipe_steps");
        // Attach a listener to read the data at our posts reference
        dbRecipeSteps.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for (DataSnapshot child : children) {
                    Recipe_Steps recipeSteps = child.getValue(Recipe_Steps.class);
                    recipeStepslistFromFirebase.add(recipeSteps);

                }

//                System.out.println("///////////// recipeStepslistFromFirebase  " + "\n" + recipeStepslistFromFirebase.toString());
//                for(Recipe_Steps repStep : recipeStepslist)
//                {
//                    System.out.println("///////////////////// getIngredient_id: " + repStep.getInstructions());
//                    System.out.println("///////////////////// getAmount: " + repStep.getStep_number());
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // End goal
        recipeStepslist = recipeStepslistFromFirebase;

    }

    public void storageContainer(List<Recipe> recipeFromFirebase) {
        recipelist = recipeFromFirebase;
        System.out.println("/////////////////////////////////////// inside storageContainer recipelist : " + recipelist);
    }


    /**
     * Adding few albums for testing
     */
//    private void prepareAlbums() {
//        int[] covers = new int[]{
//                R.drawable.album1,
//                R.drawable.album2,
//                R.drawable.album3,
//                R.drawable.album4,
//                R.drawable.album5,
//                R.drawable.album6,
//                R.drawable.album7,
//                R.drawable.album8,
//                R.drawable.album9,
//                R.drawable.album10};
//
//        Album a = new Album("True Romance", 13, covers[0]);
//        albumList.add(a);
//
//        a = new Album("Xscpae", 8, covers[1]);
//        albumList.add(a);
//
//        a = new Album("Maroon 5", 11, covers[2]);
//        albumList.add(a);
//
//        a = new Album("Born to Die", 12, covers[3]);
//        albumList.add(a);
//
//        a = new Album("Honeymoon", 14, covers[4]);
//        albumList.add(a);
//
//        a = new Album("I Need a Doctor", 1, covers[5]);
//        albumList.add(a);
//
//        a = new Album("Loud", 11, covers[6]);
//        albumList.add(a);
//
//        a = new Album("Legend", 14, covers[7]);
//        albumList.add(a);
//
//        a = new Album("Hello", 11, covers[8]);
//        albumList.add(a);
//
//        a = new Album("Greatest Hits", 17, covers[9]);
//        albumList.add(a);
//
//        // UNCOMMENT WHEN READY
//        //adapter.notifyDataSetChanged();
//    }

    /**
     * Adding Recipes from firebase into arraylist
     * <p>
     * <p>
     * Had To hardcode due to timeframe left
     * Images have comments below
     */
    private void prepareRecipe() {


        int[] image = new int[]{
                /**
                 *      Image source: BBC GoodFoods. Copyright protected. URL : https://www.bbcgoodfood.com/recipes/rib-eye-steak-pan-potatoes-peas
                */
                R.drawable.ribeye,
                /**
                 *      Image source: BBC GoodFoods. Copyright protected. URL : https://www.bbcgoodfood.com/recipes/golden-roast-potatoes
                */
                R.drawable.roastpotatoes,
                /**
                 *      Image source: BBC GoodFoods. Copyright protected. URL : https://www.bbcgoodfood.com/recipes/beef-wellington
                */
                R.drawable.beefwellington,
                /**
                 *      Image source: BBC GoodFoods. Copyright protected. URL : https://www.bbcgoodfood.com/recipes/mango-lime-chicken-wings
                */
                R.drawable.mangolimewings,
                /**
                 *      Image source: BBC GoodFoods. Copyright protected. URL : https://www.bbcgoodfood.com/recipes/sticky-toffee-apple-pudding
                */
                R.drawable.stickytoffee
        };
//        Recipe(String recipe_id, String name, String description, String category, String prep_time, String cook_time, String serving, String author, String difficulty)

//        Recipe r = new Recipe( "1", "Rib-eye with steak pan potatoes & peas", "Indulge in rib-eye steak, which takes just 20", "Gluten-free", "5 mins", "15 mins", "4","Tom Kerridge","Easy", covers[0] );
        Recipe t = new Recipe("1", "Rib-eye with steak pan potatoes & peas", "Easy", image[0]);
        recipelist.add(t);

        t = new Recipe("2", "Golden roast potatoes", "Easy", image[1]);
        recipelist.add(t);

        t = new Recipe("3", "Beef wellington", "A challenge", image[2]);
        recipelist.add(t);

        t = new Recipe("4", "Mango & lime chicken wings", "Easy", image[3]);
        recipelist.add(t);

        t = new Recipe("5", "Sticky toffee apple pudding", "Easy", image[4]);
        recipelist.add(t);

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onStart() {
        super.onStart();

    }


}
