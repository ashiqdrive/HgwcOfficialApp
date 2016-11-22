package com.hgwcapp.hgwcofficialapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.databaseall.DataBaseAdapterC;

public class AudioClassOrGeneral extends AppCompatActivity {

    private static final String TAG = "Tag AudioClassorGeneral";
    DataBaseAdapterC DACac;

    TextView tvAudCat;
    String lid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_class_or_general);

        tvAudCat = (TextView) findViewById(R.id.tvAudCatLanNam);

        try {
            openforAudioCat();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }


        Bundle languageData = getIntent().getExtras();
        if (languageData == null) {
            return;
        }

        lid = languageData.getString("LANGUAGE_ID", "1");
        String lanName = languageData.getString("LANGUAGE_NAME", "Null");
        tvAudCat.setText(lanName);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openforAudioCat() {
        DACac = new DataBaseAdapterC(this);
        DACac.open();
        Log.i(TAG, TAG + " open method Sucessfull");
    }

    public void classesButnClick(View v){
        Intent intent = new Intent(this, AudioSpeakersClasses.class);
        intent.putExtra("LANGUAGE_ID",lid);
        intent.putExtra("LANGUAGE_NAME",tvAudCat.getText().toString());
        startActivity(intent);
    }

    public void generalLecturesButnClick(View v){
        Intent intent = new Intent(this, AudioSpeakersGenaeral.class);
        intent.putExtra("LANGUAGE_ID",lid);
        intent.putExtra("LANGUAGE_NAME",tvAudCat.getText().toString());
        startActivity(intent);
    }


}
