package com.hgwcapp.hgwcofficialapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.databaseall.DataBaseAdapterC;

public class AudioMainPage extends AppCompatActivity {

    private static final String TAG = "Tag AudioMainPage";
    DataBaseAdapterC DACmain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_main_page);

        try {
            openforAudioMain();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }
    }

    private void openforAudioMain() {
        DACmain = new DataBaseAdapterC(this);
        DACmain.open();
        Log.e(TAG, TAG + " open method Sucessfull");
    }

    public void audioEnglishClick(View v) {
        Intent intent = new Intent(this, AudioClassOrGeneral.class);
        String lid = Integer.toString(DACmain.LidEngKey);
        intent.putExtra("LANGUAGE_ID", lid);
        intent.putExtra("LANGUAGE_NAME", "English");
        startActivity(intent);
    }

    public void audioTamilClick(View v) {
        Intent intent = new Intent(this, AudioClassOrGeneral.class);
        String lid = Integer.toString(DACmain.LidTamKey);
        intent.putExtra("LANGUAGE_ID", lid);
        intent.putExtra("LANGUAGE_NAME", "Tamil");
        startActivity(intent);

    }

    public void audioUrduClick(View v) {
        Intent intent = new Intent(this, AudioClassOrGeneral.class);
        String lid = Integer.toString(DACmain.LidUrdKey);
        intent.putExtra("LANGUAGE_ID", lid);
        intent.putExtra("LANGUAGE_NAME", "Urdu");
        startActivity(intent);
    }
}
