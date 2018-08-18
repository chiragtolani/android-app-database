package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateTable extends AppCompatActivity {

    TextView updtext,newValtext;
    EditText updfield,newValfield;
    Button back,log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updtext = (TextView) findViewById(R.id.updtext);
        newValtext = (TextView) findViewById(R.id.newFieldText);
        updfield = (EditText) findViewById(R.id.updfield);
        newValfield = (EditText) findViewById(R.id.newvalue);
        back = (Button) findViewById(R.id.back);
        log = (Button) findViewById(R.id.logout);


    }

    public void onUpdClicked(View view)
    {
        String u,n;
        int i;

        u=updfield.getText().toString();
        n=newValfield.getText().toString();
        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String dbname = preferences2.getString("DBName", "");

        SharedPreferences preferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        String tbname = preferences3.getString("TableName", "");


        for(i=0;i<EditDatabase.dataManagers.size();++i)
        {
            if(EditDatabase.dataManagers.get(i).getDb_name().equals(dbname))
            {
                EditDatabase.dataManagers.get(i).updateRecord(tbname,u,n);
            }
        }

    }

    public void onBackClicked(View view)
    {
        Intent B = new Intent(this,QueryOptions.class);
        startActivity(B);
    }

    public void onLogoutClicked(View view)
    {
        Intent L = new Intent(this,MainActivity.class);
        startActivity(L);
    }

}
