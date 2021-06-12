package com.example.MyFlashScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyFlashScore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabeleActivity extends AppCompatActivity {

    ListView listView;

    String[] kraje = {"Anglia","Francja","Hiszpania","Niemcy","Polska","Włochy"};
    String[] ligi = {"Premier League","Ligue 1","LaLiga","Bundesliga","PKO BP Ekstraklasa","Serie A"};
    int[] krajeFlagi = {R.mipmap.ic_anglia,R.mipmap.ic_francja,R.mipmap.ic_hiszpania,R.mipmap.ic_niemcy,R.mipmap.ic_polska,R.mipmap.ic_wlochy};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabele);

        listView = findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),TabeleWyborActivity.class);
                intent.putExtra("name",kraje[position]);
                intent.putExtra("image",krajeFlagi[position]);
                startActivity(intent);
            }
        });

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
    private class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return krajeFlagi.length;
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
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            TextView name = view1.findViewById(R.id.country);
            TextView name2 = view1.findViewById(R.id.league);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(kraje[position]);
            name2.setText(ligi[position]);
            image.setImageResource(krajeFlagi[position]);


            return view1;
        }
    }
}