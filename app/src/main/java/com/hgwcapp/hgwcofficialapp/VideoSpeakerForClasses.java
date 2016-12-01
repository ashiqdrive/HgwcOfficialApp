package com.hgwcapp.hgwcofficialapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.databaseall.DataBaseAdapterC;
import com.recyclerp.all.HiddenVisibleDatatype;
import com.recyclerp.all.RecyclerAdaptertwoItemsHV;
import com.recyclerp.all.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class VideoSpeakerForClasses extends AppCompatActivity {

    private static final String TAG = "VideoSpeakerClasses";

    DataBaseAdapterC DAC;


    TextView tvVideoSpkClasses;
    RecyclerView rvVideoSpkClasses;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdaptertwoItemsHV adaptertwoItemsHV;

    public String lanIdMain; //Languane id
    ArrayList<String> SpeakerIDsList = new ArrayList<String>();
    ArrayList<HiddenVisibleDatatype> SpeakerNamesList = new ArrayList<HiddenVisibleDatatype>();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_speaker_for_classes);

        try {
            openMethod();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }


        Bundle languageData = getIntent().getExtras();
        if (languageData == null) {
            return;
        }

        lanIdMain = languageData.getString("LANGUAGE_ID", "1");
        String lanName = languageData.getString("LANGUAGE_NAME", "Null");

        tvVideoSpkClasses = (TextView) findViewById(R.id.tvVideoSpkClasses);
        tvVideoSpkClasses.setText(lanName);

        rvVideoSpkClasses = (RecyclerView) findViewById(R.id.rvVideoSpkClasses);
        selectingSpeakrs(lanIdMain);

        creatingRecyclerView();

        rvVideoSpkClasses.addOnItemTouchListener(
                new RecyclerItemClickListener(context, rvVideoSpkClasses, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        TextView hidspkidtv = (TextView) view.findViewById(R.id.twoItemsHiddentv);
                        TextView vtv = (TextView) view.findViewById(R.id.twoItemsVisibletv);

                        String hidspkidst = hidspkidtv.getText().toString();
                        String vst = vtv.getText().toString();

                        listItemClickMethod(lanIdMain, hidspkidst, vst);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        return;
                    }
                })
        );
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

    //Selecting Speaker IDs
    public void selectingSpeakrs(String lanid) {

        try {
            Cursor cursor = DAC.selectVideoSpeaker(lanid);

            for (int i = 0; i < cursor.getCount(); i++) {
                String tempString = cursor.getString(cursor.getColumnIndex(DAC.VIDEO_TOPIC_SPK_ID));
                this.SpeakerIDsList.add(tempString);
                cursor.moveToNext();
            }
            Log.d(TAG, "Speaker ID Selection Sucessful \n"
                    + "Size of the list is " + SpeakerIDsList.size() + "\n"
                    + SpeakerIDsList.get(0));

        } catch (SQLException se) {
            Log.e(TAG, "Error in Speaker Ids Selection" + se.toString());
        }

        selectSpeakerNames(SpeakerIDsList);

    }

    public void selectSpeakerNames(List<String> spkIDs) {

        Log.d(TAG, "Select speaker names using SkpIDs MEthod started");

        for (int c = 0; c <= spkIDs.size() - 1; c++) {

            Cursor cursor = DAC.selectSpeakerNameWithSpkIDs(spkIDs.get(c));

            Log.d(TAG, "Speaker id in the Speaker id list is " + spkIDs.get(c));

            for (int i = 0; i < cursor.getCount(); i++) {

                String hiddenId = cursor.getString(cursor.getColumnIndex(DAC.SPK_ID));
                String visileName = cursor.getString(cursor.getColumnIndex(DAC.SPK_NAMES));
                HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenId, visileName);
                this.SpeakerNamesList.add(hvDT);

                Log.d(TAG, "Name added is  " + cursor.getString(cursor.getColumnIndex(DAC.SPK_NAMES)));
                cursor.moveToNext();
            }
            Log.d(TAG, "Speaker NAme Selection Sucessful \n"
                    + "Size of the list is " + SpeakerNamesList.size()
                    + "_________________________________________________________");
        }
    }

    public void creatingRecyclerView() {

        adaptertwoItemsHV = new RecyclerAdaptertwoItemsHV(SpeakerNamesList, this);
        rvVideoSpkClasses.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvVideoSpkClasses.setLayoutManager(layoutManager);
        rvVideoSpkClasses.setAdapter(adaptertwoItemsHV);
    }

    public void listItemClickMethod(String lid, String spkid, String spkName) {

        if (checkNetwork()) {
            Intent intent = new Intent(this, VideoClassTopic.class);
            intent.putExtra("LANGUAGE_ID", lid);
            intent.putExtra("SPEAKER_ID", spkid);
            intent.putExtra("SPEAKER_NAME", spkName);
            startActivity(intent);
        }
        else {
            Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
        }

    }

    //Method to check internet connection
    public boolean checkNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}