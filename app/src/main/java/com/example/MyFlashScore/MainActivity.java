package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.MyFlashScore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String[] kraje = {"Anglia","Francja","Hiszpania","Niemcy","Polska","Włochy"};
    String[] ligi = {"Premier League","Ligue 1","LaLiga","Bundesliga","PKO BP Ekstraklasa","Serie A"};
    int[] krajeFlagi = {R.mipmap.ic_anglia,R.mipmap.ic_francja,R.mipmap.ic_hiszpania,R.mipmap.ic_niemcy,R.mipmap.ic_polska,R.mipmap.ic_wlochy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obsluga przycisku
        //button = (Button)findViewById(R.id.button);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openTabeleActivity();
        //    }
        //});

        // bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.mecze);

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
    //public void openTabeleActivity(){
    //    Intent intent = new Intent(this,TabeleActivity.class);
    //    startActivity(intent);
    //}


}