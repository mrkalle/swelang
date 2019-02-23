package com.sogeti.swelang;

import android.app.Application;

public class App extends Application {

    public interface OnAppEventListener {
        void onLanguageChoosen(String fromLanguage, String toLanguage);
    }

    public static final int APP_VERSION = 1;

    
    public static final String FROM_LANGUAGE = "fromLanguage";
    public static final String TO_LANGUAGE = "toLanguage";

    //public static final String IMAGE_URL = "https://cached-images.bonnier.news/cms30/UploadedImages/2018/7/3/6b0f4550-a3c4-441c-88c2-f709c39136f6/bigOriginal.jpg";
    public static final String IMAGE_URL = "https://i.imgur.com/FlS7BiE.jpg"; //bil

}
