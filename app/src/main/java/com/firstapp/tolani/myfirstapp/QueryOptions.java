package com.firstapp.tolani.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QueryOptions extends AppCompatActivity {

    Button e,u,v,j,s,b,l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e=(Button) findViewById(R.id.entrec);
        u=(Button) findViewById(R.id.uptab);
        v=(Button) findViewById(R.id.viewtab);
        j=(Button) findViewById(R.id.jointab);
        s=(Button) findViewById(R.id.sfr);
        b=(Button) findViewById(R.id.back);
        l=(Button) findViewById(R.id.logout);


    }

    public void onERClicked(View view)
    {
        Intent E = new Intent(this,EnterRecords.class);
        startActivity(E);
    }

    public void onUTClicked(View view)
    {
        Intent U = new Intent(this,UpdateTable.class);
        startActivity(U);
    }

    public void onVTClicked(View view)
    {
        Intent V = new Intent(this,TableView.class);
        startActivity(V);
    }

    public void onJTClicked(View view)
    {
        Intent J = new Intent(this,JoinActivity.class);
        startActivity(J);
    }

    public void onSFRClicked(View view)
    {
        Intent S = new Intent(this,SearchActivity.class);
        startActivity(S);
    }

    public void onBackClicked(View view)
    {
        Intent B = new Intent(this,CreateTable2.class);
        startActivity(B);
    }

    public void onLogoutClicked(View view)
    {
        Intent L = new Intent(this,MainActivity.class);
        startActivity(L);
    }

    public void onCNTClicked(View view)
    {
        Intent C = new Intent(this,CreateTable.class);
        startActivity(C);
    }

    public void onDeleteRecClicked(View view)
    {
        Intent D = new Intent(this,DeleteRecActivity.class);
        startActivity(D);
    }
}
