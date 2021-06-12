package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MyFlashScore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabeleWyborActivity extends AppCompatActivity {
    TextView name;
    TextView liga;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabele_wybor);

        name = findViewById(R.id.listKraj);
        liga = findViewById(R.id.listLiga);
        image = findViewById(R.id.listImage);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        liga.setText(intent.getStringExtra("liga"));
        image.setImageResource(intent.getIntExtra("image",0));


        // bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.tabele);

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