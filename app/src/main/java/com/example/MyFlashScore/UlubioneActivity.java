package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UlubioneActivity extends AppCompatActivity {

    DBHelper db;
    ImageButton btn;
    ListView listView;

    int index = 0;
    String [] klub1_tab = new String[100];
    String [] klub2_tab = new String[100];
    String [] data_tab = new String[100];
    String [] godzina_tab = new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("PIŁKA NOŻNA");

        // baza danych
        db = new DBHelper(this);
        Cursor res = db.getDataFromSQL("SELECT * FROM Mecze WHERE ulubione=1");
        if(res.getCount()==0)
        {
            setContentView(R.layout.activity_ulubione);
        }
        else {
            setContentView(R.layout.activity_ulubione_db);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                klub1_tab[index] = res.getString(1);
                klub2_tab[index] = res.getString(2);
                data_tab[index] = res.getString(3);
                godzina_tab[index] = res.getString(7);
                index++;
            }
            // wyslanie dalej
            listView = findViewById(R.id.listviewulubione);
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