package com.hgwcapp.hgwcofficialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VideoMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main_page);
    }

    //*****Button method starts*********
    public void clickVideoEnglish(View v) {
        Intent intent = new Intent(this, VideoCategory.class);
        intent.putExtra("LANGUAGE_ID", "1");
        intent.putExtra("LANGUAGE_NAME", "English");
        startActivity(intent);
    }

    public void clickVideoTamil(View v) {
        Intent intent = new Intent(this, VideoCategory.class);
        intent.putExtra("LANGUAGE_ID", "2");
        intent.putExtra("LANGUAGE_NAME", "Tamil");
        startActivity(intent);
    }

    public void clickVideoUrdu(View v) {
        Intent intent = new Intent(this, VideoCategory.class);
        intent.putExtra("LANGUAGE_ID", "3");
        intent.putExtra("LANGUAGE_NAME", "Urdu");
        startActivity(intent);
    }
    //________Button method ends______
}
