package com.sogeti.swelang;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements
        App.OnAppEventListener {

    public String fromLanguage = "sv";
    public String toLanguage = "som";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Swelang", "MainActivity::onCreate called");

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Log.d("Swelang", "MainActivity::onCreate new ShowResultFragment");

            //ChooseLanguageFragment firstFragment = new ChooseLanguageFragment();
            ShowResultFragment firstFragment = new ShowResultFragment();
            Intent intent = getIntent();
            Bundle extras = null;
            if (intent != null) {
                extras = intent.getExtras();
            }

            Log.d("Swelang", "MainActivity::onCreate startNewFragment");

            startNewFragment(firstFragment, extras);
        }
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
    public void onLanguageChoosen(String fromLanguage, String toLanguage) {
        Log.d("GataGps", "MainActivity::onTransportNrLost called, from: " + fromLanguage + ", toLanguage: " + toLanguage);

        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
    }
}
