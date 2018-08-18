package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreateTable2 extends AppCompatActivity {

    Button lg,bk,con;
    TextView attrTypeText,attrNameText;
    EditText attrType,attrName;
    static int i=0;

    static String[] column = new String[CreateTable.col];
    static String[] columnType = new String[CreateTable.col];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table2);



        lg = (Button) findViewById(R.id.logout);
        bk = (Button) findViewById(R.id.entBack);
        con = (Button) findViewById(R.id.cont);

        attrNameText = (TextView) findViewById(R.id.attrname);
        attrTypeText = (TextView) findViewById(R.id.attrtyp);

        attrName = (EditText) findViewById(R.id.attrnam);

        attrType = (EditText) findViewById(R.id.attrtype);





    }

    public void onContClicked(View view)
    {
        Intent N = new Intent(this, QueryOptions.class);
        int cL =CreateTable.col;
        int j;

        if(i < cL)
        {
                column[i] = attrName.getText().toString();
                columnType[i] = attrType.getText().toString();
                attrName.setText("");
                attrType.setText("");
                i++;
        }

        else {
            SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
            String tbName = preferences1.getString("TableName", "");
            SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
            String dbName = preferences2.getString("DBName", "");
            boolean isCreated = false;



                for (j = 0; j < EditDatabase.dataManagers.size(); ++j) {
                    if (dbName.equals(EditDatabase.dataManagers.get(j).getDb_name())) {
                        isCreated = EditDatabase.dataManagers.get(j).createTab(tbName, column, columnType, this, cL);
                    }
                }

                if (isCreated) {
                    startActivity(N);
                } else {
                    Toast.makeText(this, "Enter a valid table name!", Toast.LENGTH_SHORT).show();
                }

        }

    }

    public void onEntBackClicked(View view)
    {
        Intent I = new Intent(this,CreateTable.class);
        startActivity(I);
    }

    public void onEntLogoutClicked(View view)
    {
        Intent I = new Intent(this,MainActivity.class);
        startActivity(I);
    }
}
