package com.example.cyk.coachingapp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        matchFragment = (MatchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        statFragment = (StatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        endButton = (Button) findViewById(R.id.buttonEnd);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultStat = statFragment.returnValues();
                resultMatch = matchFragment.returnValues();
                Toast.makeText(MatchActivity.this,String.valueOf(resultMatch[2]),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
