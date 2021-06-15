package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MeczeLigaActivity extends AppCompatActivity {
    TextView name;
    TextView liga;
    ImageView image;
    ListView listView;

    //ilosc meczów
    int index = 0;
    String [] klub1_tab = new String[100];
    String [] klub2_tab = new String[100];
    String [] data_tab = new String[100];
    String [] godzina_tab = new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecze_liga);
        Intent intent = getIntent();
        this.setTitle("MECZE");


        //pokaz te mecze ktore sa z ligii angielskiej a jak nie to brak meczy xml
        // baza danych
        DBHelper db = new DBHelper(this);
        //wszystkie mecze
        Cursor mecze = db.getDataFromSQL("SELECT * FROM Mecze WHERE liga=\""+intent.getStringExtra("liga")+"\"");


        if(mecze.getCount()==0)
        {
            setContentView(R.layout.activity_tabele_wybor_brak);
            // ustawienie naglowka
            name = findViewById(R.id.listKraj);
            liga = findViewById(R.id.listLiga);
            image = findViewById(R.id.listImage);
            name.setText(intent.getStringExtra("name"));
            liga.setText(intent.getStringExtra("liga"));
            image.setImageResource(intent.getIntExtra("image",0));
        }
        else {
            setContentView(R.layout.activity_mecze_liga);
            // ustawienie naglowka
            name = findViewById(R.id.listKraj);
            liga = findViewById(R.id.listLiga);
            image = findViewById(R.id.listImage);
            name.setText(intent.getStringExtra("name"));
            liga.setText(intent.getStringExtra("liga"));
            image.setImageResource(intent.getIntExtra("image",0));
            //jesli sa mecze
            while (mecze.moveToNext()){
                klub1_tab[index] = mecze.getString(1);
                klub2_tab[index] = mecze.getString(2);
                data_tab[index] = mecze.getString(3);
                godzina_tab[index] = mecze.getString(7);
                index++;
            }


                listView = findViewById(R.id.listviewmecze);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),UlubioneMeczActivity.class);
                        intent.putExtra("klub1", klub1_tab[position]);
                        intent.putExtra("klub2", klub2_tab[position]);
                        startActivity(intent);
                    }
                });
        }
        // end baza danych

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
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return index;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.row_ulubione,null);
            TextView klub = view1.findViewById(R.id.klub);
            TextView godzina = view1.findViewById(R.id.godzina);
            TextView data = view1.findViewById(R.id.data);


            // zapytanie o wszytkie kluby z bazy danych
            klub.setText(klub1_tab[position]+"\n"+klub2_tab[position]);
            godzina.setText(godzina_tab[position]);
            data.setText(data_tab[position]);

            return view1;
        }
    }
}