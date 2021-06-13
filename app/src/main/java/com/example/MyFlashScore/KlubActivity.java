package com.example.MyFlashScore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

        nazwaklubu = (TextView) findViewById(R.id.nazwaklubu);
        nazwaligi = (TextView) findViewById(R.id.nazwaligi);
        meczerozegrane = (TextView) findViewById(R.id.meczerozegrane);
        bilansbramkowy = (TextView) findViewById(R.id.bilansbramkowy);
        punktyzdobyte = (TextView) findViewById(R.id.punktyzdobyte);

        Intent intent = getIntent();

        nazwaklubu.setText(intent.getStringExtra("nazwaklubu"));
        nazwaligi.setText(intent.getStringExtra("nazwaligi"));
        meczerozegrane.setText(intent.getStringExtra("meczerozegrane"));
        bilansbramkowy.setText(intent.getStringExtra("bilansbramkowy"));
        punktyzdobyte.setText(intent.getStringExtra("punktyzdobyte"));

    }
}