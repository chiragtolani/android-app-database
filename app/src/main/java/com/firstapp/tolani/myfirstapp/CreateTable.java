package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTable extends AppCompatActivity {

    EditText colNoText;
    EditText tableNameText;
    TextView colNo, tabName;
    static int col;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_table);

        colNoText = (EditText) findViewById(R.id.colno);
        tableNameText = (EditText) findViewById(R.id.tablename);
        colNo = (TextView) findViewById(R.id.colNo);
        tabName = (TextView) findViewById(R.id.tabName);

    }

    public void onNextClicked(View view) {

        Intent I = new Intent(this, CreateTable2.class);

        String table_name = tableNameText.getText().toString();
        String column_no = colNoText.getText().toString();

        if(column_no.equals(""))
        {
            Toast.makeText(this, "Enter a valid number for attributes!", Toast.LENGTH_LONG).show();
            colNoText.setText("");
        }
        else
        {
            col = Integer.parseInt(column_no);
            Toast.makeText(this, "Loading....", Toast.LENGTH_LONG).show();
            startActivity(I);
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TableName",table_name);
        editor.apply();

    }

    public void onBackClicked(View view)
    {
        Intent B = new Intent(this,EditDatabase.class);
        startActivity(B);
    }

    public  void onLogoutClicked(View view)
    {
        Intent L = new Intent(this,MainActivity.class);
        startActivity(L);
    }

}
