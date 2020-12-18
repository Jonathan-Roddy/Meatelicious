package com.mad.meatelicious;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mad.meatelicious.food.Food1Activity;

import java.util.List;

public class HomeActivity extends AppCompatActivity{

    CardView cFood1, cFood2, cFood3, cFood4, cFood5, cFood6;
    ImageButton btnHome;

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        btnHome = findViewById(R.id.btn_home);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(a);
            }
        });

        cFood1 = findViewById(R.id.food1);
        cFood2 = findViewById(R.id.food2);
        cFood3 = findViewById(R.id.food3);
        cFood4 = findViewById(R.id.food4);
        cFood5 = findViewById(R.id.food5);
        cFood6 = findViewById(R.id.food6);

        cFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, Food1Activity.class);
                startActivity(a);
            }
        });

//        cFood2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(HomeActivity.this, Food2Activity.class);
//                startActivity(a);
//            }
//        });
//
//        cFood3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(HomeActivity.this, Food3Activity.class);
//                startActivity(a);
//            }
//        });
//
//        cFood4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(HomeActivity.this, Food4Activity.class);
//                startActivity(a);
//            }
//        });
//
//        cFood5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(HomeActivity.this, Food5Activity.class);
//                startActivity(a);
//            }
//        });

    }
}
