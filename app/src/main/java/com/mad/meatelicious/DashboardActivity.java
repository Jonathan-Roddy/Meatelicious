package com.mad.meatelicious;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    CardView cRecipe, cTimer, cRecycler, cAboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cRecipe = findViewById(R.id.menu_recipe);
        cTimer = findViewById(R.id.menu_timer);
        cRecycler = findViewById(R.id.menu_youtube);
        cAboutus = findViewById(R.id.menu_aboutus);

        cRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardActivity.this, RecyclerActivity.class);
                startActivity(a);
            }
        });

        cTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardActivity.this, TimerActivity.class);
                startActivity(a);
            }
        });

        cRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardActivity.this, FavouritesActivity.class);
                startActivity(a);
            }
        });

        cAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardActivity.this, AboutUs.class);
                startActivity(a);
            }
        });
    }


}
