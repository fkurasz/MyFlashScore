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


        // bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ulubione);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mecze:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ulubione:
                        startActivity(new Intent(getApplicationContext(), UlubioneActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tabele:
                        startActivity(new Intent(getApplicationContext(), TabeleActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        //end bottom nav

    }
}