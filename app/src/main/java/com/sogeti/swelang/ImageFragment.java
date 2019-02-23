package com.sogeti.swelang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        Log.d("Swelang", "ImageFragment::onCreateView called");

        mImageView = view.findViewById(R.id.imageview);

        return view;
    }

    @Override
    public void onStart () {
        super.onStart();

        Log.d("Swelang", "ImageFragment::onStart called");

        Picasso.get().load("https://cached-images.bonnier.news/cms30/UploadedImages/2018/7/3/6b0f4550-a3c4-441c-88c2-f709c39136f6/bigOriginal.jpg").into(mImageView);

        Log.d("Swelang", "ImageFragment::onStart done");
    }
}
