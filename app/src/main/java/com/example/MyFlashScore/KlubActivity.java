package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KlubActivity extends AppCompatActivity {

    TextView nazwaklubu;
    TextView nazwaligi;
    TextView meczerozegrane;
    TextView bilansbramkowy;
    TextView punktyzdobyte;
    ImageButton btn;
    private int wynik = 0;
    private String nazwa_klubu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("TABELE");
        setContentView(R.layout.activity_klub);
        Intent intent = getIntent();

        nazwaklubu = (TextView) findViewById(R.id.nazwaklubu);
        nazwaligi = (TextView) findViewById(R.id.nazwaligi);
        meczerozegrane = (TextView) findViewById(R.id.meczerozegrane);
        bilansbramkowy = (TextView) findViewById(R.id.bilansbramkowy);
        punktyzdobyte = (TextView) findViewById(R.id.punktyzdobyte);

        nazwaklubu.setText(intent.getStringExtra("nazwaklubu"));
        nazwa_klubu = intent.getStringExtra("nazwaklubu");
        nazwaligi.setText(intent.getStringExtra("nazwaligi"));
        meczerozegrane.setText(intent.getStringExtra("meczerozegrane"));
        bilansbramkowy.setText(intent.getStringExtra("bilansbramkowy"));
        punktyzdobyte.setText(intent.getStringExtra("punktyzdobyte"));

        // serduszko z ulubione
        btn = (ImageButton) findViewById(R.id.ulubBtn);

        // baza danych
        DBHelper db = new DBHelper(this);
        Cursor res = db.getDataFromSQL("SELECT * FROM WszystkieDruzyny WHERE klub=\"" + intent.getStringExtra("nazwaklubu") + "\"");

        if(res.getCount()>0)
        {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append(res.getInt(7));
            }
            wynik = Integer.parseInt(buffer.toString());
        }
        // end baza danych

        // ustawienie serduszka na starcie
        if(wynik == 1){
            btn.setBackgroundResource(R.drawable.ic_ulubione_zlote);
        }
        else {
            btn.setBackgroundResource(R.drawable.ic_ulubione_puste);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zmien serduszko na przeciwne
                if(db.setUlubioneWszystkieDruzyny(nazwa_klubu))
                {
                    if(wynik == 1){
                        btn.setBackgroundResource(R.drawable.ic_ulubione_puste);
                        wynik = 0;
                    }
                    else {
                        btn.setBackgroundResource(R.drawable.ic_ulubione_zlote);
                        wynik = 1;
                    }
                }
            }
        });



    }
}