package com.sogeti.swelang;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class ChooseLanguageFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private App.OnAppEventListener mCallback;

    Spinner mFromLanguageSpinner;
    Spinner mToLanguageSpinner;
    Button mLanguageOkButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chooselanguage, container, false);
        mFromLanguageSpinner = view.findViewById(R.id.fromlanguage_spinner);
        mFromLanguageSpinner.setOnItemSelectedListener(this);

        mToLanguageSpinner = view.findViewById(R.id.tolanguage_spinner);
        mToLanguageSpinner.setOnItemSelectedListener(this);

        mLanguageOkButton = view.findViewById(R.id.languageok_button);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mLanguageOkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("GataGps", "ChooseLanguageFragment::onStart::setOnClickListener mLanguageOkButton clicked");

                mCallback.onLanguageChoosen(getFromLanguage(), getToLanguage());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (App.OnAppEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAppEventListener");
        }

        Log.d("Swelang", "ChooseLanguageFragment::onAttach klar");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected called");
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected fromlanguage fromSelectedVal: " + getFromLanguage());
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected tolanguage toSelectedVal: " + getToLanguage());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("Swelang", "ChooseLanguageFragment::onNothingSelected called");
    }

    private String getFromLanguage() {
        return getResources().getStringArray(R.array.fromlanguage_val_array)[mFromLanguageSpinner.getSelectedItemPosition()];
    }

    private String getToLanguage() {
        return getResources().getStringArray(R.array.tolanguage_val_array)[mToLanguageSpinner.getSelectedItemPosition()];
    }
}
