package com.firstapp.tolani.myfirstapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    ListView lv;
    Button back,search;
    EditText sTable,sField,sValue;
    TextView sText,svText,sfText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (Button) findViewById(R.id.back);
        search = (Button) findViewById(R.id.search);
        sTable = (EditText) findViewById(R.id.searchTable);
        sField=(EditText) findViewById(R.id.searchField);
        sValue=(EditText) findViewById(R.id.searchValue);
        sText=(TextView) findViewById(R.id.searchtext);
        svText=(TextView) findViewById(R.id.searchValueText);
        sfText=(TextView) findViewById(R.id.searchFieldText);



        lv = (ListView) findViewById(R.id.searchlist);

    }

    public void onBackClicked(View view)
    {
        sTable.setVisibility(View.VISIBLE);
        sField.setVisibility(View.VISIBLE);
        sValue.setVisibility(View.VISIBLE);
        sText.setVisibility(View.VISIBLE);
        svText.setVisibility(View.VISIBLE);
        sfText.setVisibility(View.VISIBLE);
        lv.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        search.setVisibility(View.VISIBLE);
    }

    public void onSearchClicked(View view)
    {
        String t,f,v;
        int i;

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String dbName = preferences2.getString("DBName", "");

        sTable.setVisibility(View.INVISIBLE);
        sField.setVisibility(View.INVISIBLE);
        sValue.setVisibility(View.INVISIBLE);
        sText.setVisibility(View.INVISIBLE);
        svText.setVisibility(View.INVISIBLE);
        sfText.setVisibility(View.INVISIBLE);
        lv.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        search.setVisibility(View.INVISIBLE);

        t= sTable.getText().toString();
        f= sField.getText().toString();
        v= sValue.getText().toString();

        String [] data = {""};
        for(i=0;i<EditDatabase.dataManagers.size();++i)
        {
            if(EditDatabase.dataManagers.get(i).getDb_name().equals(dbName))
            {
                data = EditDatabase.dataManagers.get(i).searchAllRows(t,v,f);
            }
        }

        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(la);
    }

}
