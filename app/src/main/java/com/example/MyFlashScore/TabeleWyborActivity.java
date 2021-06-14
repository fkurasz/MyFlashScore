package com.example.MyFlashScore;

import androidx.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyFlashScore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabeleWyborActivity extends AppCompatActivity {
    TextView name;
    TextView liga;
    ImageView image;
    ListView listView;

    int [] index_tab = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    String [] kluby_tab = new String[20];
    int [] mecze_tab = new int[20];
    String [] bilans_tab = new String[20];
    int [] punkty_tab = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // baza danych
        DBHelper db = new DBHelper(this);
        // daj tylko kluby angielskie w "name"
        Cursor res = db.getDataFromSQL("SELECT * FROM WszystkieDruzyny WHERE kraj=\""+intent.getStringExtra("name")+"\"");
        if(res.getCount()>0)
        {
            setContentView(R.layout.activity_tabele_wybor);
            // ustawienie naglowka
            name = findViewById(R.id.listKraj);
            liga = findViewById(R.id.listLiga);
            image = findViewById(R.id.listImage);
            name.setText(intent.getStringExtra("name"));
            liga.setText(intent.getStringExtra("liga"));
            image.setImageResource(intent.getIntExtra("image",0));

            StringBuffer buffer = new StringBuffer();
            int i = 0;
            while (res.moveToNext()){
                kluby_tab[i] = res.getString(1);
                mecze_tab[i] = res.getInt(4);
                bilans_tab[i] = res.getString(5);
                punkty_tab[i] = res.getInt(6);
                i++;
            }
            // wyslanie dalej
            listView = findViewById(R.id.listviewtabela);
            CustomAdapter customAdapter = new CustomAdapter();
            listView.setAdapter(customAdapter);

            // end ustawienie naglowka
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent1 = new Intent(getApplicationContext(),KlubActivity.class);
                    intent1.putExtra("nazwaklubu",kluby_tab[position]);
                    intent1.putExtra("nazwaligi",liga.getText().toString());
                    intent1.putExtra("meczerozegrane",String.valueOf(mecze_tab[position]));
                    intent1.putExtra("bilansbramkowy",bilans_tab[position]);
                    intent1.putExtra("punktyzdobyte",String.valueOf(punkty_tab[position]));
                    startActivity(intent1);
                }
            });
        }
        else {
            setContentView(R.layout.activity_tabele_wybor_brak);
            name = findViewById(R.id.listKraj);
            liga = findViewById(R.id.listLiga);
            image = findViewById(R.id.listImage);
            name.setText(intent.getStringExtra("name"));
            liga.setText(intent.getStringExtra("liga"));
            image.setImageResource(intent.getIntExtra("image",0));
        }
        // end baza danych


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
            return kluby_tab.length;
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
            View view1 = getLayoutInflater().inflate(R.layout.row_tabela,null);
            TextView pozycja = view1.findViewById(R.id.pozycja);
            TextView klub = view1.findViewById(R.id.klub);
            TextView mecz = view1.findViewById(R.id.mecz);
            TextView bilans = view1.findViewById(R.id.bilans);
            TextView punkty = view1.findViewById(R.id.punkty);


            // zapytanie o wszytkie kluby z bazy danych
            pozycja.setText(String.valueOf(index_tab[position])+".");
            klub.setText(kluby_tab[position]);
            mecz.setText(String.valueOf(mecze_tab[position]));
            bilans.setText(bilans_tab[position]);
            punkty.setText(String.valueOf(punkty_tab[position]));


            return view1;
        }
    }
}