package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TableView extends AppCompatActivity {

    ListView lv;
    Button back;
    ArrayList<String> tbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int i;


        lv = (ListView) findViewById(R.id.tablelist);
        back = (Button) findViewById(R.id.back);

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String dbName = preferences2.getString("DBName", "");

        for(i=0;i<EditDatabase.dataManagers.size();++i)
        {
            if(dbName.equals(EditDatabase.dataManagers.get(i).getDb_name()))
            {
                tbList = EditDatabase.dataManagers.get(i).getAllTables();
                break;
            }
        }

        ListAdapter la = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tbList);
        lv.setAdapter(la);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                Intent tl = new Intent(getBaseContext(),TableLayoutActivity.class);
                startActivity(tl);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("ListTableName",lv.getItemAtPosition(position).toString());
                editor.apply();

            }

        });
    }

    public void onTBListBackClicked(View view)
    {
        Intent i = new Intent(this, QueryOptions.class);
        startActivity(i);
    }

}
