package com.hgwcapp.hgwcofficialapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.databaseall.DataBaseAdapterC;

public class VideoLister extends AppCompatActivity {

    DataBaseAdapterC DACvl;

    ListView lvVideoLister;
    TextView videoNameinLister;

    private static final String TAG = "Tag videoLister";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lister);
        videoNameinLister =(TextView) findViewById(R.id.videoNameinLister);

        lvVideoLister=(ListView) findViewById(R.id.lvVideoLister);

        try {
            openforVideo();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        Bundle VideoDatata = getIntent().getExtras();
        if (VideoDatata == null) {
            return;
        }

        //BELOW IS THE lid WHICH IS USEd IN THE SELECT STATEMENT QUERY
        String lid = VideoDatata.getString("FOIRGN_ID","1");
        String VidName=VideoDatata.getString("VIDEO_NAME", "Null");
        videoNameinLister.setText(VidName.toString());

        selectQuery(lid);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openforVideo() {
        DACvl = new DataBaseAdapterC(this);
        DACvl.open();
        Log.e(TAG, "open method Sucessfull");
    }

    public void selectQuery(String frid) {
        Cursor cursor = DACvl.videoSelecting(frid);
        String[] fromcursor = new String[]{DACvl.VID_NAME, DACvl.VID_DOWNLOAD_LINK};
        int[] toViewids = new int[]{R.id.tvforVideoName,R.id.tvforVideoLink};
        SimpleCursorAdapter videoCursorAdapter;
        try {
            videoCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.custom_video_list, cursor, fromcursor, toViewids);
            lvVideoLister.setAdapter(videoCursorAdapter);
            Log.i(TAG, "List populated");
        } catch (Exception e) {
            Log.w(TAG, "Error in populating list view", e);
            // Toast.makeText(getBaseContext(), "error in poulating\n" + e, Toast.LENGTH_LONG).show();
        }

        lvVideoLister.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvforVideoName = (TextView) view.findViewById(R.id.tvforVideoName);
                TextView tvforVideoLink = (TextView) view.findViewById(R.id.tvforVideoLink);

                String videoName=tvforVideoName.getText().toString();
                String videoURL = tvforVideoLink.getText().toString();
                dialogbox(videoName,videoURL);
            }
        });
    }

    public void openYouTube(String url) {
        //Toast.makeText(getBaseContext(), "link is \n" + url, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void shareString(String videoname,String urlString){
        String shareBody = videoname+"\n"+urlString;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Watch ");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(sharingIntent);
    }

    public void dialogbox(String name,String url) {
        final String videoName = name;
        final String videoUrl = url;
        final Dialog dialogbox = new Dialog(this);
        dialogbox.setContentView(R.layout.video_list_select_action);
        ListView lvSelectAction = (ListView) dialogbox.findViewById(R.id.lvSelectAction);
        String[] typeslist = {"Play", "Share"};
        ListAdapter addlvadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typeslist);
        lvSelectAction.setAdapter(addlvadapter);
        dialogbox.setCancelable(true);
        dialogbox.setTitle("Choose Action");
        dialogbox.show();
        lvSelectAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedPos = position;
                if (selectedPos == 0) {
                    dialogbox.cancel();
                    Log.w(TAG, "Error in populating list view");
                    //Toast.makeText(getBaseContext(), "Play selected\n" + selectedPos, Toast.LENGTH_LONG).show();
                    openYouTube(videoUrl);
                } else if (selectedPos == 1) {
                    dialogbox.cancel();
                    // Toast.makeText(getBaseContext(), "Share selected\n" + selectedPos, Toast.LENGTH_LONG).show();
                    shareString(videoName, videoUrl);
                }
            }
        });
    }
}
