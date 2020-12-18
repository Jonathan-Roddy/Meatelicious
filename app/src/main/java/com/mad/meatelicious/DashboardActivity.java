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
        cRecipe = (CardView) findViewById(R.id.menu_recipe);
        cTimer = (CardView) findViewById(R.id.menu_timer);
        cRecycler = (CardView) findViewById(R.id.menu_recycler);
        cAboutus = (CardView) findViewById(R.id.menu_aboutus);

        cRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DashboardActivity.this, HomeActivity.class);
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
                Intent a = new Intent(DashboardActivity.this, RecyclerActivity.class);
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
