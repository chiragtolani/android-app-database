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

public class ExistDBInfo extends AppCompatActivity {

    static String db_name;
    TextView tv;
    EditText et;
    Button b;
    String ctName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_dbinfo);


        et = (EditText) findViewById(R.id.existdbtext);
        b = (Button) findViewById(R.id.existDBbutton);
        tv = (TextView) findViewById(R.id.etext);

    }

    public void onExOKClicked(View view)
    {
        Intent I = new Intent(this,QueryOptions.class);

        db_name = et.getText().toString() + ".db";

        int size = EditDatabase.dataManagers.size();

        for(int i =0;i<size;++i) {
            if (EditDatabase.dataManagers.get(i).getDb_name().equals(db_name)) {
                ctName = db_name;
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("DBName",ctName);
                editor.apply();
                Toast.makeText(this, "Database exists!", Toast.LENGTH_LONG).show();
                startActivity(I);
            }
            else
            {
                Toast.makeText(this,"Invalid Database!",Toast.LENGTH_LONG).show();
                this.onBackPressed();
                break;
            }
        }

    }

}
