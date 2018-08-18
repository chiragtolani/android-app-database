package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText userName;
    EditText password;
    EditText confirm_pass;
    Button back;
    RegisterDBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirm_pass = (EditText) findViewById(R.id.confirm_password);
        back = (Button) findViewById(R.id.back);
        dbh = new RegisterDBHandler(this, null, null, 1);
    }

    public void onSignUpClicked(View view)
    {
        Intent I = new Intent(this, MainActivity.class);

        String user = userName.getText().toString();
        String pass = password.getText().toString();
        String con_pass = confirm_pass.getText().toString();

        if(pass.equals("") || user.equals("") || con_pass.equals(""))
        {
            Toast.makeText(this, "Fields vacant...Please fill properly", Toast.LENGTH_LONG).show();
            return;
        }

        else if(!pass.equals(con_pass))
        {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
            return;
        }

        else
        {
            RegisterDB rdb = new RegisterDB(user,pass);
            dbh.addInfo(rdb);
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
            startActivity(I);
        }

    }

    public void onBackClicked(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
