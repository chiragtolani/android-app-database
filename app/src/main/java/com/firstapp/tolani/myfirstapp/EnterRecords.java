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
import android.widget.Toast;


public class EnterRecords extends AppCompatActivity {

    TextView entText,colText;
    EditText entData,colData;
    Button rec,back,check;
    static  String [] colNames,rowData={""};
    static int current=0;
    static String tableName;
    static String dbName;
    int size = CreateTable.col;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entText = (TextView) findViewById(R.id.enttabletext);
        colText = (TextView) findViewById(R.id.entcoltext);
        entData = (EditText) findViewById(R.id.enttablename);
        colData = (EditText) findViewById(R.id.entcoldata);
        rec = (Button) findViewById(R.id.record);
        back = (Button) findViewById(R.id.back);
        check = (Button) findViewById(R.id.check);


    }

    public void onCheckClicked(View view)
    {
        tableName = entData.getText().toString();

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        dbName = preferences2.getString("DBName", "");


            entText.setVisibility(View.INVISIBLE);
            entData.setVisibility(View.INVISIBLE);
            check.setVisibility(View.INVISIBLE);
            colText.setVisibility(View.VISIBLE);
            colData.setVisibility(View.VISIBLE);
            rec.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            String c;
            for(int k =0;k<EditDatabase.dataManagers.size();++k) {
                if (dbName.equals(EditDatabase.dataManagers.get(k).getDb_name())) {
                    colNames = EditDatabase.dataManagers.get(k).getColData(tableName);
                }
            }
            c = colNames[0] + ":";
            colText.setText(c);

        if(entData.getText().toString().equals(""))
        {
            entData.setText("");
            Toast.makeText(this,"Enter a valid table name!",Toast.LENGTH_SHORT).show();
        }
    }

    public void onRecordClicked(View view) {
        boolean isEntered=false;
        String d;
        if (current <= size-1) {
            rowData[current] = colData.getText().toString();
            colData.setText("");
            d = colNames[++current] + ":";
            colText.setText(d);
        }
        else {
            for(int k =0;k<EditDatabase.dataManagers.size();++k) {
                if(dbName.equals(EditDatabase.dataManagers.get(k).getDb_name())) {
                    isEntered = EditDatabase.dataManagers.get(k).insertRecord(tableName, rowData, colNames.length);
                }
            }



        }

        if(isEntered) {
             Toast.makeText(this,"Record entered!",Toast.LENGTH_SHORT).show();
        }
    }
    public void onENTBackClicked(View view)
    {
        Intent i = new Intent(this,QueryOptions.class);
        startActivity(i);
    }


}


