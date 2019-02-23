package com.sogeti.swelang;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CameraFragment extends Fragment {

    private static final int Image_Capture_Code = 1;

    private ImageView mImgCapture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        Log.d("Swelang", "CameraFragment::onCreateView called");

        mImgCapture = view.findViewById(R.id.camera_view);

        return view;
    }

    @Override
    public void onStart () {
        super.onStart();

        Log.d("Swelang", "CameraFragment::onStart called");

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, Image_Capture_Code);

        Log.d("Swelang", "CameraFragment::onStart done");
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
}
