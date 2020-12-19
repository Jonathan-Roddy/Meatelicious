package com.mad.meatelicious;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) Recycler view is null pointer exception, copied from CardView demo example. debugged them side by side from OnStart and mine shows null object
 * 2) Lists are generated in anomoyous inner class and not being stored globally, the lists can be called and displayed within this class
 * 3) Timer functions in mainActivity doesnt show until its -- setContentView( R.layout.fragment_timer); I wish for it to be called when timer class onClick is
 * <p>
 * Possibility that not linking layouts but disapear when I add "import static com.mad.... R.id.fragment_timer"
 * <p>
 * Firebase is connected and working
 * - saves each Child as an object of ingredient, recipe, recipe-step, recipe-ingredient
 * Timer code works
 * - Extra feature
 */

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private RecipeAdapter recipeAdapter;
    private List<Album> albumList;
    private static final String TAG = "MainActivity";

    private AppBarConfiguration mAppBarConfiguration;

    // firebase
    private DatabaseReference dbIngredient, dbRecipe, dbRecipeIngredients, dbRecipeSteps;


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
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();

        albumAdapter = new AlbumAdapter(this, albumList);
        recipeAdapter = new RecipeAdapter(this, recipelist);

        prepareAlbums();

        prepareFirebase();
        prepareRecipe();

        prepareRecyclerView();

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(albumAdapter);


//        recipeAdapter = new RecipeAdapter(this, recipelist);
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(recipeAdapter);

//        System.out.println("////////////////////////////////// INSIDE MAIN : " + recipelist);

    }

    private void prepareRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(albumAdapter);

    }


    /**
     * Adding firebase
     */
    private void prepareFirebase() {

        dbIngredient = FirebaseDatabase.getInstance().getReference("0/ingredient/");
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

        dbRecipe = FirebaseDatabase.getInstance().getReference("0/recipe");
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

        dbRecipeIngredients = FirebaseDatabase.getInstance().getReference("0/recipe_ingredients");
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

        dbRecipeSteps = FirebaseDatabase.getInstance().getReference("0/recipe_steps");
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
        recipelist = (ArrayList<Recipe>) recipeFromFirebase;
        System.out.println("/////////////////////////////////////// inside storageContainer recipelist : " + recipelist);
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10};

        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);

        // UNCOMMENT WHEN READY
        //adapter.notifyDataSetChanged();
    }

    /**
     * Adding Recipes from firebase into arraylist
     */
    private void prepareRecipe() {

//        int[] covers = new int[]{
//                R.drawable.album1,
//                R.drawable.album2,
//                R.drawable.album3,
//                R.drawable.album4,
//                R.drawable.album5};

//        Recipe r = new Recipe("True Romance", 13, covers[0]);
//        recipelist.add(r);

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
