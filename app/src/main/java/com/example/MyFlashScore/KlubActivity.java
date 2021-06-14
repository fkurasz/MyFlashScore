package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KlubActivity extends AppCompatActivity {

    TextView nazwaklubu;
    TextView nazwaligi;
    TextView meczerozegrane;
    TextView bilansbramkowy;
    TextView punktyzdobyte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klub);
        Intent intent = getIntent();

        nazwaklubu = (TextView) findViewById(R.id.nazwaklubu);
        nazwaligi = (TextView) findViewById(R.id.nazwaligi);
        meczerozegrane = (TextView) findViewById(R.id.meczerozegrane);
        bilansbramkowy = (TextView) findViewById(R.id.bilansbramkowy);
        punktyzdobyte = (TextView) findViewById(R.id.punktyzdobyte);

        nazwaklubu.setText(intent.getStringExtra("nazwaklubu"));
        nazwaligi.setText(intent.getStringExtra("nazwaligi"));
        meczerozegrane.setText(intent.getStringExtra("meczerozegrane"));
        bilansbramkowy.setText(intent.getStringExtra("bilansbramkowy"));
        punktyzdobyte.setText(intent.getStringExtra("punktyzdobyte"));

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