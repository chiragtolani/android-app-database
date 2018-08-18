package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDB extends AppCompatActivity {

    TextView dtext;
    EditText et;
    Button b;
    static String del_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_db);

        et = (EditText) findViewById(R.id.DelText);
        b = (Button) findViewById(R.id.DelOK);
        dtext = (TextView) findViewById(R.id.dtext);


    }

    public void onDelOKClicked(View view)
    {
        Intent I = new Intent(this,EditDatabase.class);
        int size = EditDatabase.dataManagers.size();

        del_name = et.getText().toString() + ".db";
        for(int i =0;i<size;++i)
        {
            if(EditDatabase.dataManagers.get(i).getDb_name().equals(del_name))
            {
                EditDatabase.dataManagers.get(i).deleteDB(del_name,this);
                EditDatabase.dataManagers.remove(i);
                Toast.makeText(this,"Database deleted!",Toast.LENGTH_LONG).show();
                startActivity(I);
                break;
            }
        }

    }

}
