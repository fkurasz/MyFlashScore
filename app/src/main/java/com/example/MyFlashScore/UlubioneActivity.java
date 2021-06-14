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

    //ilosc meczów
    int index = 0;
    String [] klub1_tab = new String[100];
    String [] klub2_tab = new String[100];
    String [] data_tab = new String[100];
    String [] godzina_tab = new String[100];

    //index tablicy z id meczów ulubionych
    int index2= 0;
    String [] klub1_tab_ulub = new String[100];
    String [] klub2_tab_ulub = new String[100];
    String [] data_tab_ulub = new String[100];
    String [] godzina_tab_ulub = new String[100];
    int [] index_meczu = new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("PIŁKA NOŻNA");

        // baza danych
        db = new DBHelper(this);
        //wszystkie mecze
        Cursor mecze = db.getDataFromSQL("SELECT * FROM Mecze");


        if(mecze.getCount()==0)
        {
            setContentView(R.layout.activity_ulubione);
        }
        else {
            //jesli sa mecze
            while (mecze.moveToNext()){
                klub1_tab[index] = mecze.getString(1);
                klub2_tab[index] = mecze.getString(2);
                data_tab[index] = mecze.getString(3);
                godzina_tab[index] = mecze.getString(7);
                index++;
            }
            for (int i = 0; i < index; i++)
            {
                //usub wyszstkie z ulubionych
                db.setUsunUlubioneMecz(i+1);
            }
            Cursor cur = db.getDataFromSQL("SELECT * FROM Mecze");
            StringBuffer buff1 = new StringBuffer();
            while (cur.moveToNext()){
                buff1.append(cur.getInt(4));
            }

            Boolean jest = false;
            for (int i = 0; i < index; i++)
            {
                //wszystkie ulubione zespoly
                Cursor isulub1 = db.isUlubionaDruzyna(klub1_tab[i]);
                Cursor isulub2 = db.isUlubionaDruzyna(klub2_tab[i]);

                //jesli ktoras z druzyn jest w ulubionych to daj caly mecz jako ulubiony
                //na miejscu i
                if(isulub1.getCount() > 0)
                {
                    //ustaw mecz jako ulubiony dla id = i+1
                    if(db.setUlubioneMecz(i+1))
                    {
                        jest = true;
                        index_meczu[index2] = i;
                        index2++;
                    }
                }
                else if(isulub2.getCount() > 0){
                    //ustaw mecz jako ulubiony dla id = i+1
                    if(db.setUlubioneMecz(i+1))
                    {
                        jest = true;
                        index_meczu[index2] = i;
                        index2++;
                    }
                }
            }
            if(jest){
                setContentView(R.layout.activity_ulubione_db);
                // wyslanie dalej
                for (int i = 0; i < index2; i++){
                    klub1_tab_ulub[i] = klub1_tab[index_meczu[i]];
                    klub2_tab_ulub[i] = klub2_tab[index_meczu[i]];
                    data_tab_ulub[i] = data_tab[index_meczu[i]];
                    godzina_tab_ulub[i] = godzina_tab[index_meczu[i]];
                }

                listView = findViewById(R.id.listviewulubione);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),UlubioneMeczActivity.class);
                        intent.putExtra("klub1", klub1_tab_ulub[position]);
                        intent.putExtra("klub2", klub2_tab_ulub[position]);
                        startActivity(intent);
                    }
                });
            }
            else {
                setContentView(R.layout.activity_ulubione);
            }

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
            return index2;
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
            klub.setText(klub1_tab_ulub[position]+"\n"+klub2_tab_ulub[position]);
            godzina.setText(godzina_tab_ulub[position]);
            data.setText(data_tab_ulub[position]);



            return view1;
        }
    }
}