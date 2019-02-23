package com.sogeti.swelang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements App.OnAppEventListener {

    public String fromLanguage = "sv";
    public String toLanguage = "so";

    private static final int Image_Capture_Code = 1;
    private ImageView mImgCapture;

    static final int REQUEST_TAKE_PHOTO = 1;

    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Swelang", "MainActivity::onCreate called");
/*
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });*/

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Log.d("Swelang", "MainActivity::onCreate new ShowResultFragment");

            //Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(cameraIntent, Image_Capture_Code);
/*
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    String apa = "test";
                }

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }*/

            //ImageFragment firstFragment = new ImageFragment();
            ChooseLanguageFragment firstFragment = new ChooseLanguageFragment();
            //ShowResultFragment firstFragment = new ShowResultFragment();
            //CameraFragment firstFragment = new CameraFragment();
            Intent intent = getIntent();
            Bundle extras = null;

            if (intent != null) {
                extras = intent.getExtras();
            }

            Log.d("Swelang", "MainActivity::onCreate startNewFragment");

            startNewFragment(firstFragment, extras);
        }


        //mImgCapture = findViewById(R.id.camera_view);
    }
/*
    public void speech(String text){
        //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onStart() {
        super.onStart();

        //speech("APA APA APA");
    }*/

    @Override
    public void onLanguageChoosen(String fromLanguage, String toLanguage) {
        Log.d("GataGps", "MainActivity::onTransportNrLost called, from: " + fromLanguage + ", toLanguage: " + toLanguage);

        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;

        Bundle extras = new Bundle();
        extras.putString(App.FROM_LANGUAGE, this.fromLanguage);
        extras.putString(App.TO_LANGUAGE, this.toLanguage);

        ShowResultFragment newFragment = new ShowResultFragment();
        startNewFragment(newFragment, extras);
    }

    private void startNewFragment(Fragment newFragment, Bundle args) {
        if (args != null) {
            newFragment.setArguments(args);
        }

        Log.d("Swelang", "MainActivity::startNewFragment start it up");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                mImgCapture.setImageBitmap(bp);

            } else if (resultCode ==  Activity.RESULT_CANCELED) {
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    String currentPhotoPath;

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
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
