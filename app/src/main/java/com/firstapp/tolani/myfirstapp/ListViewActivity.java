package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    ListView lv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        b = (Button) findViewById(R.id.onListBack);

        int i,size=EditDatabase.dataManagers.size();
        String [] dbList= new String[size];

        for(i=0;i<size;++i)
        {
            dbList[i]=EditDatabase.dataManagers.get(i).getDb_name();
        }

        lv = (ListView) findViewById(R.id.databaselist);
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dbList);
        lv.setAdapter(la);

    }

    public void onListBackClicked(View view)
    {
        Intent i = new Intent(this, EditDatabase.class);
        startActivity(i);
    }

}
