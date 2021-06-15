package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UlubioneMeczActivity extends AppCompatActivity {

    TextView klub1;
    TextView klub2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("PIŁKA NOŻNA");
        setContentView(R.layout.activity_ulubione_mecz);


        klub1 = findViewById(R.id.klub1);
        klub2 = findViewById(R.id.klub2);

        Intent intent = getIntent();

        klub1.setText(intent.getStringExtra("klub1"));
        klub2.setText(intent.getStringExtra("klub2"));

        //tutaj jeszcze obsluga tych ulubionych



    }
}