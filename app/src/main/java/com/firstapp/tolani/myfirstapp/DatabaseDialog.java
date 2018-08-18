package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseDialog extends AppCompatActivity {

    EditText dn1;
    TextView createText;
    Button okay;
    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_database_dialog);

        dn1 = (EditText) findViewById(R.id.databaseName);
        okay = (Button) findViewById(R.id.okay);
        createText = (TextView) findViewById(R.id.ctext);



    }

    public void onOKClicked(View view)
    {
        Intent i = new Intent(this, EditDatabase.class);
        name = dn1.getText().toString() + ".db";
        DataManager dm = new DataManager(this,name);
        EditDatabase.dataManagers.add(dm);
        Toast.makeText(this,"Database created!",Toast.LENGTH_LONG).show();
        startActivity(i);
    }

}
