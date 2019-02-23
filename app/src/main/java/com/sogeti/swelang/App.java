package com.sogeti.swelang;

import android.app.Application;

public class App extends Application {

    public interface OnAppEventListener {
        void onLanguageChoosen(String fromLanguage, String toLanguage);
    }

    public static final int APP_VERSION = 1;
    public static final String FROM_LANGUAGE = "fromLanguage";
    public static final String TO_LANGUAGE = "toLanguage";
}
