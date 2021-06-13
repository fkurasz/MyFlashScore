package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UlubioneActivity extends AppCompatActivity {

    DBHelper db;
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // baza danych
        db = new DBHelper(this);
        Cursor res = db.getData();
        if(res.getCount()==0)
        {
            setContentView(R.layout.activity_ulubione);
        }
        else {
            setContentView(R.layout.activity_ulubione_db);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append(res.getString(0)+" "+res.getString(1)+" "+res.getString(2)+"\n");
            }
            System.out.println(buffer);
        }
        // end baza danych
        btn = (ImageButton) findViewById(R.id.ulubBtn);
        btn.setBackgroundResource(R.drawable.ic_ulubione_zlote);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usunac mecz
                btn.setBackgroundResource(R.drawable.ic_ulubione_puste);
            }
        });

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