package com.example.cyk.coachingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {"_id","score1","score2","name","time","Shots","ShotsOn","Fouls","Offsides","Yellows","Reds","X","Y","uri"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dbHelper = new DbHelper(this);

        database = dbHelper.getReadableDatabase();
        Cursor c=null;
        c = database.rawQuery("Select * from matchs order by _id desc limit 3 ",null );

        int i=1;

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    View v = null;
                    if (i==1) {
                        v = findViewById(R.id.include3);
                    }
                    else if (i==2){
                        v = findViewById(R.id.include2);
                    }
                    else {
                        v = findViewById(R.id.include4);
                    }
                    i++;
                    TextView score1 = (TextView) v.findViewById(R.id.score1);
                    score1.setText(c.getString(c.getColumnIndex("score1")));

                    TextView score2 = (TextView) v.findViewById(R.id.score2);
                    score2.setText(c.getString(c.getColumnIndex("score2")));

                    TextView name = (TextView) v.findViewById(R.id.name);
                    name.setText(c.getString(c.getColumnIndex("name")));

                    TextView time = (TextView) v.findViewById(R.id.time);
                    time.setText(c.getString(c.getColumnIndex("time")));

                    TextView shots = (TextView) v.findViewById(R.id.shots);
                    shots.setText(c.getString(c.getColumnIndex("shots")));

                    TextView shotsOn = (TextView) v.findViewById(R.id.shotsOn);
                    shotsOn.setText(c.getString(c.getColumnIndex("ShotsOn")));

                    TextView fouls = (TextView) v.findViewById(R.id.fouls);
                    fouls.setText(c.getString(c.getColumnIndex("Fouls")));

                    TextView offsides = (TextView) v.findViewById(R.id.offsides);
                    offsides.setText(c.getString(c.getColumnIndex("Offsides")));

                    TextView yellows = (TextView) v.findViewById(R.id.yellows);
                    yellows.setText(c.getString(c.getColumnIndex("Yellows")));

                    TextView reds = (TextView) v.findViewById(R.id.reds);
                    reds.setText(c.getString(c.getColumnIndex("Reds")));

                    TextView X = (TextView) v.findViewById(R.id.X);
                    X.setText(c.getString(c.getColumnIndex("X")));

                    TextView Y = (TextView) v.findViewById(R.id.Y);
                    Y.setText(c.getString(c.getColumnIndex("Y")));

                    ImageView img = (ImageView) v.findViewById(R.id.picture);
                    if(c.getString(c.getColumnIndex("uri"))!=null) {
                        img.setImageURI(Uri.parse(c.getString(c.getColumnIndex("uri"))));
                    }
                }while (c.moveToNext());
            }
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new) {
            Intent myIntent = new Intent(this, MapsActivity.class);
            this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
