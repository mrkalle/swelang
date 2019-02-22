package com.sogeti.swelang;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class ChooseLanguageFragment  extends Fragment implements AdapterView.OnItemSelectedListener {

    private App.OnAppEventListener mCallback;

    Spinner mFromLanguageSpinner;
    Spinner mToLanguageSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chooselanguage, container, false);
        mFromLanguageSpinner = view.findViewById(R.id.fromlanguage_spinner);
        mFromLanguageSpinner.setOnItemSelectedListener(this);

        mToLanguageSpinner = view.findViewById(R.id.tolanguage_spinner);
        mToLanguageSpinner.setOnItemSelectedListener(this);

        return view;
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
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected called, id: " + id + ", position: " + position);

        int fromSelectedPos = mFromLanguageSpinner.getSelectedItemPosition();
        String fromSelectedVal = getResources().getStringArray(R.array.fromlanguage_val_array)[mFromLanguageSpinner.getSelectedItemPosition()];
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected fromlanguage fromSelectedVal: " + fromSelectedVal);

        int toSelectedPos = mToLanguageSpinner.getSelectedItemPosition();
        String toSelectedVal = getResources().getStringArray(R.array.tolanguage_val_array)[mToLanguageSpinner.getSelectedItemPosition()];
        Log.d("Swelang", "ChooseLanguageFragment::onItemSelected tolanguage toSelectedVal: " + toSelectedVal);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("Swelang", "ChooseLanguageFragment::onNothingSelected called");

    }
}
