package com.mad.meatelicious.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mad.meatelicious.DashboardActivity;
import com.mad.meatelicious.R;

public class Food1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipe);

        // Fill up the data with Firebase


    }

    public void home(View view) {
        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
    }

}
