package com.firstapp.tolani.myfirstapp;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EditDatabase extends AppCompatActivity {

    TextView dataView;
    Button cnd,ued,sd,dd,logout;
    public static ArrayList<DataManager> dataManagers = new ArrayList<DataManager>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);

        cnd = (Button) findViewById(R.id.createDB);
        ued = (Button) findViewById(R.id.useExistDB);
        sd = (Button) findViewById(R.id.showDB);
        dd = (Button) findViewById(R.id.DelDB);
        logout = (Button) findViewById(R.id.logout);
        dataView = (TextView) findViewById(R.id.welc);

    }

    public void onCreateDBClicked(View view)
    {
        Intent i1 = new Intent(this, DatabaseDialog.class);
        startActivity(i1);

    }

    public void onUseExistDBClicked(View view)
    {
        Intent j = new Intent(this, ExistDBInfo.class);
        startActivity(j);
    }

    public void onDelDBClicked(View view)
    {
        Intent i = new Intent(this,DeleteDB.class);
        startActivity(i);

    }

    public void onShowDBClicked(View view)
    {
        Intent i = new Intent(this, ListViewActivity.class);
        startActivity(i);
    }

    public void onLogoutClicked(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



}
