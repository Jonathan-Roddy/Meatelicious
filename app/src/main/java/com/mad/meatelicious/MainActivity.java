package com.mad.meatelicious;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Here is the list of errors that I have mentioned in my demoistration that was taking me too long to fix.
 * I was able to work around some but not all.
 * <p>
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
 * <p>
 * <p>
 * This is my Main Activity class. This will call and Dashboard class that will be the home for the system.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    //private List<Album> albumList;
    private static final String TAG = "MainActivity";

    private AppBarConfiguration mAppBarConfiguration;


    // this will store my lists from firebase
    final public List<Ingredient> ingredientlistFromFirebase = new ArrayList<Ingredient>();
    final public List<Recipe> recipelistFromFirebase = new ArrayList<Recipe>();
    final public List<Recipe_Ingredients> recipeIngredientlistFromFirebase = new ArrayList<Recipe_Ingredients>();
    final public List<Recipe_Steps> recipeStepslistFromFirebase = new ArrayList<Recipe_Steps>();

    // this will store my list of ingredients
    public List<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    public List<Recipe> recipelist = new ArrayList<Recipe>();
    public List<Recipe_Ingredients> recipeIngredientlist = new ArrayList<Recipe_Ingredients>();
    public List<Recipe_Steps> recipeStepslist = new ArrayList<Recipe_Steps>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // Floating Action Button
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        // Drawer layout
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_timer)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //albumList = new ArrayList<>();
        prepareFirebase();


        // Calls the dashboard - recipe, timer, recycler view, about us

        Intent i = new Intent(getBaseContext(), DashboardActivity.class);
//        Intent i = new Intent(getBaseContext(), RecyclerActivity.class);
        startActivity(i);

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

                /**
                 *  uncomment this to see list display to the console
                 */
//                System.out.println("///////////// ingredientlistFromFirebase  " + "\n" + ingredientlistFromFirebase.toString());
//                for(Ingredient ing : ingredientlist)
//                {
//                    System.out.println("///////////////////// get name: " + ing.getName());
//                    System.out.println("///////////////////// get id: " + ing.getIngredient_id());
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
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
                for(DataSnapshot child : children ){
                    Recipe recipe = child.getValue(Recipe.class);
                    recipelistFromFirebase.add(recipe);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference dbRecipeIngredients = FirebaseDatabase.getInstance().getReference("0/recipe_ingredients");
        // Attach a listener to read the data at our posts reference
        dbRecipeIngredients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for(DataSnapshot child : children ){
                    Recipe_Ingredients recipeIngredient = child.getValue(Recipe_Ingredients.class);
                    recipeIngredientlistFromFirebase.add(recipeIngredient);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////

        DatabaseReference dbRecipeSteps = FirebaseDatabase.getInstance().getReference("0/recipe_steps");
        // Attach a listener to read the data at our posts reference
        dbRecipeSteps.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get all of the children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                // Shake hands with all of them
                for(DataSnapshot child : children ){
                    Recipe_Steps recipeSteps = child.getValue(Recipe_Steps.class);
                    recipeStepslistFromFirebase.add(recipeSteps);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
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
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putLong("startTimeInMillis", mStartTimeInMillis);
//        editor.putLong("millisLeft", mTimeLeftInMillis);
//        editor.putBoolean("timerRunning", mTimerRunning);
//        editor.putLong("endTime", mEndTime);
//        editor.apply();
//        if (mCountDownTimer != null) {
//            mCountDownTimer.cancel();
//        }
    }

    @Override
    public void onStart() {
        super.onStart();

//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
//        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
//        mTimerRunning = prefs.getBoolean("timerRunning", false);
//        updateCountDownText();
//        updateWatchInterface();
//        if (mTimerRunning) {
//            mEndTime = prefs.getLong("endTime", 0);
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//            if (mTimeLeftInMillis < 0) {
//                mTimeLeftInMillis = 0;
//                mTimerRunning = false;
//                updateCountDownText();
//                updateWatchInterface();
//            } else {
//                startTimer();
//            }
//        }

    }


}
