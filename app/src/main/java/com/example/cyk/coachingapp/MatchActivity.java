package com.example.cyk.coachingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchActivity extends FragmentActivity implements MatchFragment.OnFragmentInteractionListener, StatFragment.OnFragmentInteractionListener {

    private Button endButton;

    private Button camButton;
    private ImageView mImageView;
    String mCurrentPhotoPath;
    Uri photoURI;

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
                if(mCurrentPhotoPath!=null){
                    values.put(allColumns[13],mCurrentPhotoPath);
                }
                long newRowId = database.insert("matchs", null, values);


                Intent intent = new Intent(MatchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        camButton = (Button) findViewById(R.id.buttCam);
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(MatchActivity.this,"Error Creating File",Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
                Toast.makeText(MatchActivity.this,photoURI.getPath(),Toast.LENGTH_LONG).show();
                Toast.makeText(MatchActivity.this,mCurrentPhotoPath,Toast.LENGTH_LONG).show();

            }
        }
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
