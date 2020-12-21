package com.mad.meatelicious.food;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mad.meatelicious.DashboardActivity;
import com.mad.meatelicious.R;
import com.mad.meatelicious.Recipe;

import java.util.ArrayList;
import java.util.Locale;

public class Food1Activity extends AppCompatActivity {

    //    public ImageView recipeImageBig;
    public ImageView recipeImageSmall;
    public TextView recipeTitle;
    public TextView recipeAuthor;
    public TextView recipeTitleSmall;
    public TextView prepTime;
    public TextView cookTime;
    public TextView recipeDifficulty;
    public TextView recipeServes;
    public TextView recipeDescription;
    public TextToSpeech textToSpeech;
    public Button TTS, TTS3;
    public TextView listIngredients;
    public TextView listSteps;
    public ArrayList recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipe);

        recipeImageSmall = findViewById(R.id.recipeImageSmall);
        recipeTitle = findViewById(R.id.recipeTitle);
        recipeAuthor = findViewById(R.id.recipeAuthorNameRecipe);
        recipeTitleSmall = findViewById(R.id.recipeTitleSmall);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookTime);
        recipeDifficulty = findViewById(R.id.recipeDifficulty);
        recipeServes = findViewById(R.id.recipeServes);
        recipeDescription = findViewById(R.id.recipeDescription);
        TTS = findViewById(R.id.TTS);
        TTS3 = findViewById(R.id.TTS3);

        listIngredients = findViewById(R.id.listIngredients);
        listSteps = findViewById(R.id.listSteps);

        recipeList = new ArrayList<>();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TTS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String data = listIngredients.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }
            }

        });

        TTS3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String data = listSteps.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }
            }

        });

        createHardCopyRecipes();

        updateScreen();

    }

    private void updateScreen() {
        Intent mIntent = getIntent();
        int recipePosition = mIntent.getIntExtra("position", 0);

        System.out.println("//////////////////////// inside Food1Activity : " + recipePosition);

//        R.drawable.roastpotatoes,
//                R.drawable.beefwellington,
//                R.drawable.mangolimewings,
//                R.drawable.stickytoffee

        switch (recipePosition) {
            case 0:
                Recipe firstRecipe = new Recipe();
                firstRecipe = (Recipe) recipeList.get(0);

                recipeImageSmall.setImageResource(R.drawable.ribeye);
                recipeTitle.setText(firstRecipe.getName());
                recipeAuthor.setText(firstRecipe.getAuthor());
                recipeTitleSmall.setText(firstRecipe.getName());
                prepTime.setText(firstRecipe.getPrep_time());
                cookTime.setText(firstRecipe.getCook_time());
                recipeDifficulty.setText(firstRecipe.getDifficulty());
                recipeServes.setText(firstRecipe.getServing());
                recipeDescription.setText(firstRecipe.getDescription());

                String ing1 = "2 rib-eye steaks , about 300g each and 2cm thick\n" +
                        "2 garlic cloves , grated\n" +
                        "2 tbsp red wine vinegar\n" +
                        "12 new potatoes , quartered\n" +
                        "4 tbsp olive oil , plus extra for drizzling\n" +
                        "25g butter\n" +
                        "50g frozen peas or fresh\n" +
                        "8 radishes , sliced, any leaves picked and washed";

                String steps1 = "STEP 1\n" +
                        "Flatten the steaks out slightly with your hands. Mix the garlic with the vinegar and a large pinch of salt and rub over both sides of the steaks and set aside.\n" +
                        "\n" +
                        "STEP 2\n" +
                        "Cook the potatoes in boiling water for 8-10 mins until just tender, then drain and set aside. Meanwhile, heat the oil and butter in a large non-stick frying pan over a high heat and sizzle the steaks for 5-6 mins on each side until nicely seared, then sit on a board to rest. Add the potatoes and pan-fry until starting to brown.\n" +
                        "\n" +
                        "STEP 3\n" +
                        "Pour some boiling water over the peas, then drain and add to the pan with the sliced radishes to heat through for 2 mins, then toss in the radish leaves (if you have them) for 30 secs.\n" +
                        "\n" +
                        "STEP 4\n" +
                        "Carve the steaks into thick slices and drizzle with a bit more oil and some seasoning to make a dressing with the juices. Fan out the meat and serve with the potatoes in a bowl on the side.";

                listIngredients.setText(ing1);
                listSteps.setText(steps1);

                break;
            case 1:

                Recipe secondRecipe = new Recipe();
                secondRecipe = (Recipe) recipeList.get(1);

                recipeImageSmall.setImageResource(R.drawable.roastpotatoes);
                recipeTitle.setText(secondRecipe.getName());
                recipeAuthor.setText(secondRecipe.getAuthor());
                recipeTitleSmall.setText(secondRecipe.getName());
                prepTime.setText(secondRecipe.getPrep_time());
                cookTime.setText(secondRecipe.getCook_time());
                recipeDifficulty.setText(secondRecipe.getDifficulty());
                recipeServes.setText(secondRecipe.getServing());
                recipeDescription.setText(secondRecipe.getDescription());

                String ing2 = "2 ½kg Desirée or King Edward potato\n" +
                        "2 tbsp flour\n" +
                        "5 tbsp goose fat or sunflower oil";

                String steps2 = "STEP 1\n" +
                        "Peel the potatoes, then cut into halves, or quarters if large. Bring a large pan of water to the boil, add a little salt, then tip the potatoes into the pan. Bring back to the boil and cook for 7 mins. Drain really well, then return to the pan and sprinkle over the flour. Place a lid on top of the pan, then pick up the pan using oven gloves or a tea towel and give it a few really hard shakes so the potatoes get bashed around (this will make them nice and crisp).\n" +
                        "\n" +
                        "STEP 2\n" +
                        "Heat oven to 190C/fan 170C/gas 5. Spoon the goose fat or sunflower oil onto a large, lipped baking tray and leave to heat on the top shelf of the oven for 5 mins. Carefully pull out the tray, make sure its surface is evenly coated with the fat, then tip on the potatoes. Roast for 20 mins, then turn up the oven to 220C/fan 200C/gas 7 and roast for another 30 mins, turning the potatoes once, until golden and crisp. Sprinkle with a little salt and serve straight away.\n" +
                        "\n" +
                        "RECIPE TIPS\n" +
                        "GET AHEAD\n" +
                        "If freezing ahead, simply follow the recipe to the end of step 1, then let the potatoes cool. Line 2 baking trays or sheets with greaseproof paper. Arrange the potatoes on the paper, making sure none of them are touching each other, then place in the freezer. Once frozen solid, tip the potatoes into a bag to free up some freezer space. Can be frozen for up to 1 month. Cook for 30 mins at 190C/fan 170C/gas 5 before turning the oven up.";

                listIngredients.setText(ing2);
                listSteps.setText(steps2);


                break;
            case 2:

                Recipe thirdRecipe = new Recipe();
                thirdRecipe = (Recipe) recipeList.get(2);

                recipeImageSmall.setImageResource(R.drawable.beefwellington);
                recipeTitle.setText(thirdRecipe.getName());
                recipeAuthor.setText(thirdRecipe.getAuthor());
                recipeTitleSmall.setText(thirdRecipe.getName());
                prepTime.setText(thirdRecipe.getPrep_time());
                cookTime.setText(thirdRecipe.getCook_time());
                recipeDifficulty.setText(thirdRecipe.getDifficulty());
                recipeServes.setText(thirdRecipe.getServing());
                recipeDescription.setText(thirdRecipe.getDescription());

                String ing3 = "a good beef fillet (preferably Aberdeen Angus) of around 1kg/2lb 4oz\n" +
                        "3 tbsp olive oil\n" +
                        "250g/9oz chestnut mushroom, include some wild ones if you like\n" +
                        "50g/2oz butter\n" +
                        "1 large sprig fresh thyme\n" +
                        "100ml/3.5 fl oz dry white wine\n" +
                        "12 slices prosciutto\n" +
                        "500g/1lb 2oz pack puff pastry, thawed if frozen\n" +
                        "a little flour, for dusting\n" +
                        "2 egg yolks beaten with 1 tsp water";

                String steps3 = "STEP 1\n" +
                        "Heat oven to 220C/fan 200C/gas 7.\n" +
                        "\n" +
                        "STEP 2\n" +
                        "Sit the 1kg beef fillet on a roasting tray, brush with 1 tbsp olive oil and season with pepper, then roast for 15 mins for medium-rare or 20 mins for medium. When the beef is cooked to your liking, remove from the oven to cool, then chill in the fridge for about 20 mins.\n" +
                        "\n" +
                        "STEP 3\n" +
                        "While the beef is cooling, chop 250g chestnut (and wild, if you like) mushrooms as finely as possible so they have the texture of coarse breadcrumbs. You can use a food processor to do this, but make sure you pulse-chop the mushrooms so they don’t become a slurry.\n" +
                        "\n" +
                        "STEP 4\n" +
                        "Heat 2 tbsp of the olive oil and 50g butter in a large pan and fry the mushrooms on a medium heat, with 1 large sprig fresh thyme, for about 10 mins stirring often, until you have a softened mixture.\n" +
                        "\n" +
                        "STEP 5\n" +
                        "Season the mushroom mixture, pour over 100ml dry white wine and cook for about 10 mins until all the wine has been absorbed. The mixture should hold its shape when stirred.\n" +
                        "\n" +
                        "STEP 6\n" +
                        "Remove the mushroom duxelle from the pan to cool and discard the thyme.\n" +
                        "\n" +
                        "STEP 7\n" +
                        "Overlap two pieces of cling film over a large chopping board. Lay 12 slices prosciutto on the cling film, slightly overlapping, in a double row.\n" +
                        "\n" +
                        "STEP 8\n" +
                        "Spread half the duxelles over the prosciutto, then sit the fillet on it and spread the remaining duxelles over.\n" +
                        "\n" +
                        "STEP 9\n" +
                        "Use the cling film’s edges to draw the prosciutto around the fillet, then roll it into a sausage shape, twisting the ends of cling film to tighten it as you go.\n" +
                        "\n" +
                        "STEP 10\n" +
                        "Chill the fillet while you roll out the pastry.\n" +
                        "\n" +
                        "STEP 11\n" +
                        "Dust your work surface with a little flour. Roll out a third of the 500g pack of puff pastry to a 18 x 30cm strip and place on a non-stick baking sheet.\n" +
                        "\n" +
                        "STEP 12\n" +
                        "Roll out the remainder of the 500g pack of puff pastry to about 28 x 36cm.\n" +
                        "\n" +
                        "STEP 13\n" +
                        "Unravel the fillet from the cling film and sit it in the centre of the smaller strip of pastry.\n" +
                        "\n" +
                        "STEP 14\n" +
                        "Beat the 2 egg yolks with 1 tsp water and brush the pastry’s edges, and the top and sides of the wrapped fillet.\n" +
                        "\n" +
                        "STEP 15\n" +
                        "Using a rolling pin, carefully lift and drape the larger piece of pastry over the fillet, pressing well into the sides.\n" +
                        "\n" +
                        "STEP 16\n" +
                        "Trim the joins to about a 4cm rim. Seal the rim with the edge of a fork or spoon handle.\n" +
                        "\n" +
                        "STEP 17\n" +
                        "Glaze all over with more egg yolk and, using the back of a knife, mark the beef Wellington with long diagonal lines taking care not to cut into the pastry.\n" +
                        "\n" +
                        "STEP 18\n" +
                        "Chill for at least 30 mins and up to 24 hrs. Heat oven to 200C/fan 180C/gas 6.\n" +
                        "\n" +
                        "STEP 19\n" +
                        "Brush the Wellington with a little more egg yolk and cook until golden and crisp – 20-25 mins for medium-rare beef, 30 mins for medium. Allow to stand for 10 mins before serving in thick slices.";

                listIngredients.setText(ing3);
                listSteps.setText(steps3);

                break;
            case 3:

                Recipe fourthRecipe = new Recipe();
                fourthRecipe = (Recipe) recipeList.get(3);

                recipeImageSmall.setImageResource(R.drawable.mangolimewings);
                recipeTitle.setText(fourthRecipe.getName());
                recipeAuthor.setText(fourthRecipe.getAuthor());
                recipeTitleSmall.setText(fourthRecipe.getName());
                prepTime.setText(fourthRecipe.getPrep_time());
                cookTime.setText(fourthRecipe.getCook_time());
                recipeDifficulty.setText(fourthRecipe.getDifficulty());
                recipeServes.setText(fourthRecipe.getServing());
                recipeDescription.setText(fourthRecipe.getDescription());

                String ing4 = "1kg chicken wings\n" +
                        "oil , for drizzling\n" +
                        "200g mango chutney\n" +
                        "zest of 1 lime\n" +
                        "1 red chilli , sliced\n" +
                        "coriander leaves, roughly chopped";

                String steps4 = "STEP 1\n" +
                        "Heat oven to 180C/160C fan/gas 4. Toss the chicken wings in a drizzle of oil and some seasoning in a baking tray. Cook for 30 mins. Mix the chutney with the lime zest. Brush all over the chicken, then return to the oven for 20 mins, turning and painting halfway through cooking. To serve, scatter with the chilli and coriander.";

                listIngredients.setText(ing4);
                listSteps.setText(steps4);

                break;
            case 4:

                Recipe fifthRecipe = new Recipe();
                fifthRecipe = (Recipe) recipeList.get(4);

                recipeImageSmall.setImageResource(R.drawable.stickytoffee);
                recipeTitle.setText(fifthRecipe.getName());
                recipeAuthor.setText(fifthRecipe.getAuthor());
                recipeTitleSmall.setText(fifthRecipe.getName());
                prepTime.setText(fifthRecipe.getPrep_time());
                cookTime.setText(fifthRecipe.getCook_time());
                recipeDifficulty.setText(fifthRecipe.getDifficulty());
                recipeServes.setText(fifthRecipe.getServing());
                recipeDescription.setText(fifthRecipe.getDescription());

                String ing5 = "85g butter, melted\n" +
                        "140g self-raising flour\n" +
                        "100g golden caster sugar\n" +
                        "1 tbsp baking powder\n" +
                        "200ml milk\n" +
                        "1 egg, beaten\n" +
                        "1 tsp vanilla extract\n" +
                        "2 Bramley apples (or other cooking) apples, peeled, cored and sliced\n" +
                        "140g dark brown sugar\n" +
                        "50g pecan, roughly chopped";

                String steps5 = "STEP 1\n" +
                        "Heat oven to 180C/fan 160C/gas 4. Grease a 2-litre/3½-pint ovenproof dish lightly with butter. Tip the flour, sugar and baking powder, along with a pinch of salt, into a large bowl. Mix together the milk, butter, egg and vanilla extract and stir into the dry ingredients until you get a smooth batter. Arrange the apples in the dish, spoon the batter on top and smooth with a knife until the apples are covered.\n" +
                        "\n" +
                        "STEP 2\n" +
                        "For the topping, pour 250ml boiling water over the sugar and stir together until smooth. Pour the liquid over the pudding mixture, then scatter over the pecans. Bake for about 40 mins until the pudding has risen and is golden. Use a big spoon to serve the pudding, making sure you get some of the gooey caramel sauce covering the bottom of the dish. Serve with pouring cream, warm custard or vanilla ice cream.";

                listIngredients.setText(ing5);
                listSteps.setText(steps5);

                break;

        }
    }

    private void createHardCopyRecipes() {
//        Recipe(String recipe_id, String name, String description, String category, String prep_time, String cook_time, String serving, String author, String difficulty)

        Recipe t = new Recipe("1", "Rib-eye with steak pan potatoes & peas", "Indulge in rib-eye steak, which takes just 20", "Gluten-free", "5 mins", "15 mins", "4", "Tom Kerridge", "Easy");
        recipeList.add(t);

        t = new Recipe("2", "Golden roast potatoes", "Don't spend Christmas morning peeling potatoes, make our freeze ahead roasties", "Vegetarian", "20 mins", "1 hr", "8", "Emma Lewis", "Easy");
        recipeList.add(t);

        t = new Recipe("3", "Beef wellington", "Gordon Ramsay's version of the classic steak dish – a showstopping centrepiece on a special occasion", "Freezable", "1 hr and 50mins", "40 mins", "6", "Cassie Best", "A challenge");
        recipeList.add(t);

        t = new Recipe("4", "Mango & lime chicken wings", "Mix mango chutney and lime zest to make the marinade for these sticky chicken wings. Serve with scattered red chilli slices and coriander", "Chicken", "5 mins", "50 mins", "4-6", "Cassie Best", "Easy");
        recipeList.add(t);

        t = new Recipe("5", "Mango & lime chicken wings", "Try a fruity version of the classic sticky toffee pudding for a rich after-dinner treat. Serve with cream, custard or vanilla ice cream", "Dessert", "20 mins", "40 mins", "6-9", "Emma Lewis", "Easy");
        recipeList.add(t);

    }

    public void home(View view) {
        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}
