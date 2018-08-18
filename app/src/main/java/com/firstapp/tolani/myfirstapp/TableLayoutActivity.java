package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayoutActivity extends AppCompatActivity {

    TableLayout tlv;
    Button back;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (Button) findViewById(R.id.back);
        tlv = (TableLayout) findViewById(R.id.tabledata);
        sv = (ScrollView) findViewById(R.id.tablescroll);

        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        String tbName = preferences1.getString("ListTableName", "");
        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String dbName = preferences2.getString("DBName", "");

        String [] rowData = {""};

        for(int i=0;i<EditDatabase.dataManagers.size();++i)
        {
            if(dbName.equals(EditDatabase.dataManagers.get(i).getDb_name()))
            {
                rowData = EditDatabase.dataManagers.get(i).selectAllRows(tbName);
            }
        }

        int len = rowData.length;



        for (int current = 0; current < len ; current++)
        {
            // Create a TableRow and give it an ID
            TableRow tr = new TableRow(this);
            tr.setId(100+current);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // Create a TextView to house the name of the province
            TextView labelTV = new TextView(this);
            labelTV.setId(200+current);
            labelTV.setText(rowData[current]);
            labelTV.setTextColor(Color.BLACK);
            labelTV.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(labelTV);


            // Add the TableRow to the TableLayout
            tlv.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }


    }

    public void onTLVBackClicked(View view)
    {
        Intent i = new Intent(this, TableView.class);
        startActivity(i);
    }

}
