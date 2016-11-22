package com.hgwcapp.hgwcofficialapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.databaseall.DataBaseAdapterC;

public class VideoCategory extends AppCompatActivity {

    DataBaseAdapterC DACvc;

    ListView tvcVidLv;
    TextView tvcVidLanNam;

    private static final String TAG = "Tag videoCategory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_category);
        tvcVidLanNam = (TextView) findViewById(R.id.tvcVidLanNam);
        tvcVidLv = (ListView) findViewById(R.id.tvcVidLv);

        try {
            openforVideo();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        Bundle languageData = getIntent().getExtras();
        if (languageData == null) {
            return;
        }

        //BELOW IS THE lid WHICH IS USEd IN THE SELECT STATEMENT QUERY
        String lid = languageData.getString("LANGUAGE_ID", "2");
        String lanName = languageData.getString("LANGUAGE_NAME", "Null");
        tvcVidLanNam.setText(lanName);

        selectQuery(lid);
    }

    private void openforVideo() {
        DACvc = new DataBaseAdapterC(this);
        DACvc.open();
        Log.e(TAG, "open method Sucessfull");
    }

    public void selectQuery(String lid) {
        Cursor cursor = DACvc.videoCategorySelecting(lid);
        String[] fromcursor = new String[]{DACvc.VNID_NAME, DACvc.VNID_SPK_NAME,DACvc.VNID_ID};
        int[] toViewids = new int[]{R.id.tv2forVideoName,R.id.tv2forSpeakername,R.id.tv2HoldFroignID};
        SimpleCursorAdapter videoCursorAdapter;
        try {
            videoCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.custom_video_list2, cursor, fromcursor, toViewids);
            tvcVidLv.setAdapter(videoCursorAdapter);
            Log.e(TAG, "List populated");
        } catch (Exception e) {
            Log.w(TAG, "Error in populating list view", e);
            Toast.makeText(getBaseContext(), "error in poulating\n" + e, Toast.LENGTH_LONG).show();
        }

        tvcVidLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvforVideoName = (TextView) view.findViewById(R.id.tv2forVideoName);
                TextView tv2HoldFroignID = (TextView) view.findViewById(R.id.tv2HoldFroignID);

                String videoName = tvforVideoName.getText().toString();
                String tvForgnID = tv2HoldFroignID.getText().toString();

                startVideoLister(videoName,tvForgnID);
            }
        });
    }

    public void startVideoLister(String videoN,String frId){
        Intent intent = new Intent(this, VideoLister.class);
        intent.putExtra("VIDEO_NAME",videoN);
        intent.putExtra("FOIRGN_ID",frId);
        startActivity(intent);
    }
}
