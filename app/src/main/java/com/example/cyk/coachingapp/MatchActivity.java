package com.example.cyk.coachingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MatchActivity extends FragmentActivity implements MatchFragment.OnFragmentInteractionListener, StatFragment.OnFragmentInteractionListener {

    private Button endButton;

    private MatchFragment matchFragment;
    private StatFragment statFragment;

    private int[] resultStat;
    private Object[] resultMatch;

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {"_id","score1","score2","name","time","Shots","ShotsOn","Fouls","Offsides","Yellows","Reds","X","Y","uri"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        matchFragment = (MatchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        statFragment = (StatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        dbHelper = new DbHelper(this);

        database = dbHelper.getWritableDatabase();

        endButton = (Button) findViewById(R.id.buttonEnd);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultStat = statFragment.returnValues();
                resultMatch = matchFragment.returnValues();
                Toast.makeText(MatchActivity.this,String.valueOf(resultMatch[2]),Toast.LENGTH_LONG).show();
                ContentValues values = new ContentValues();
                values.put(allColumns[1],(int)resultMatch[0]);
                values.put(allColumns[2],(int)resultMatch[1]);
                values.put(allColumns[3],String.valueOf(resultMatch[2]));
                values.put(allColumns[4],""+resultMatch[3]);
                values.put(allColumns[5],resultStat[0]);
                values.put(allColumns[6],resultStat[1]);
                values.put(allColumns[7],resultStat[2]);
                values.put(allColumns[8],resultStat[3]);
                values.put(allColumns[9],resultStat[4]);
                values.put(allColumns[10],resultStat[5]);
                Intent mIntent = getIntent();
                values.put(allColumns[11],mIntent.getDoubleExtra("X",0));
                values.put(allColumns[12],mIntent.getDoubleExtra("Y",0));
                long newRowId = database.insert("matchs", null, values);
            }
        });


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
