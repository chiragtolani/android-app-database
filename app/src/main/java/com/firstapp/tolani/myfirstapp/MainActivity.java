package com.firstapp.tolani.myfirstapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button lg,sp;
    RegisterDBHandler rdbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        lg = (Button) findViewById(R.id.login);
        sp = (Button) findViewById(R.id.signup);

        rdbh = new RegisterDBHandler(this, null, null, 1);


    }

    public void saveData(View view) {

        Intent I = new Intent(this, EditDatabase.class);

        String u = userName.getText().toString();
        String p = password.getText().toString();
        String sp = rdbh.getSingleEntry(u);

        if (p.equals(sp)) {
            Toast.makeText(this, "Logging in...", Toast.LENGTH_LONG).show();
            startActivity(I);

        }
    }

    public void onSUclicked(View view) {

        Intent I = new Intent(this, RegisterPage.class);
        startActivity(I);

    }

}



