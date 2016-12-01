package com.hgwcapp.hgwcofficialapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.databaseall.DataBaseAdapterC;

public class VideoClassOrGeneral extends AppCompatActivity {

    private static final String TAG = "VideoClassOrGeneral";
    DataBaseAdapterC DAC;

    TextView tvVideoLangName;
    String lid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_class_or_general);

        tvVideoLangName = (TextView) findViewById(R.id.tvHeaderForVideoClassOrGeneral);

        try {
            openMethod();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        Bundle languageData = getIntent().getExtras();
        if (languageData == null) {
            return;
        }

        lid = languageData.getString("LANGUAGE_ID", "1");
        String lanName = languageData.getString("LANGUAGE_NAME", "Null");
        tvVideoLangName.setText(lanName);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openMethod() {
        DAC = new DataBaseAdapterC(this);
        DAC.open();
        Log.i(TAG, TAG + " open method Sucessfull");
    }

    public void videoclassesButnClick(View v){
        Intent intent = new Intent(this, VideoSpeakerForClasses.class);
        intent.putExtra("LANGUAGE_ID",lid);
        intent.putExtra("LANGUAGE_NAME",tvVideoLangName.getText().toString());
        startActivity(intent);
    }

    public void videogeneralLecturesButnClick(View v){
        Intent intent = new Intent(this, AudioSpeakersGenaeral.class);
        intent.putExtra("LANGUAGE_ID",lid);
        intent.putExtra("LANGUAGE_NAME",tvVideoLangName.getText().toString());
        startActivity(intent);
    }



}
