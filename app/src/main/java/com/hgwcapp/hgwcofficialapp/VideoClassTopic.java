package com.hgwcapp.hgwcofficialapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Handler;
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
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.recyclerp.all.HiddenVisibleDatatype;
import com.recyclerp.all.RecyclerAdaptertwoItemsHV;
import com.recyclerp.all.RecyclerItemClickListener;
import com.youtubeplaylistallclasses.GetPlaylistTitlesAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VideoClassTopic extends AppCompatActivity {

    private static final String TAG = "VideoClassTopics";
    DataBaseAdapterC DAC;

    String lanIdMain;
    String spkIDMain;
    String spkNameMain;
    ArrayList<String> playListIDs = new ArrayList();
    ArrayList<String> mPlaylistTitles;
    ArrayList<HiddenVisibleDatatype> PlayListNamesList = new ArrayList<HiddenVisibleDatatype>();


    TextView tvTopicSpeakerHeader;
    RecyclerView rvForVideoTopic;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdaptertwoItemsHV adaptertwoItemsHV;


    Context context;

    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();

    ProgressDialog progressDialog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_class_topic);

        try {
            openMethod();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();

        Bundle previousClassData = getIntent().getExtras();
        if (previousClassData == null) {
            return;
        }

        lanIdMain = previousClassData.getString("LANGUAGE_ID", "1");
        spkIDMain = previousClassData.getString("SPEAKER_ID", "3");
        spkNameMain = previousClassData.getString("SPEAKER_NAME", "Null");

        tvTopicSpeakerHeader = (TextView) findViewById(R.id.tvHeaderForVideoTopic);
        tvTopicSpeakerHeader.setText(spkNameMain);

        progressDialog01 = ProgressDialog.show(this, "", "Loading...", true);
        progressDialog01.setCancelable(true);

        if (checkNetwork()) {
            selectQueryMethod(lanIdMain, spkIDMain);
            listEmptyCheck();

            rvForVideoTopic = (RecyclerView) findViewById(R.id.rvForVideoTopic);
            rvForVideoTopic.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, rvForVideoTopic, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            TextView hidspkidtv = (TextView) view.findViewById(R.id.twoItemsHiddentv);
                            TextView vtv = (TextView) view.findViewById(R.id.twoItemsVisibletv);

                            String hiddenPlayListid = hidspkidtv.getText().toString();
                            String visiblePlayListTitle = vtv.getText().toString();

                            listItemClickMethod(hiddenPlayListid, visiblePlayListTitle);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            return;
                        }
                    })
            );
        } else {
            Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
        }
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


    public void selectQueryMethod(String lanID, String spkID) {

        Cursor cursor = DAC.selectPlayListIDsWithSpeakerIds(lanID, spkID);
        for (int i = 0; i < cursor.getCount(); i++) {
            String tempString = cursor.getString(cursor.getColumnIndex(DAC.VIDEO_TOPIC_PLAYLIST_LINK));
            this.playListIDs.add(tempString);
            cursor.moveToNext();
        }
        Log.d(TAG, "The PlayList id Size is " + playListIDs.size() + "\n" +
                "Fisrt item in List is " + playListIDs.get(0));

        gettingPlaylistName();
    }

    //MEthod to GEt PLAYLIST Names Using the GetPlaylistTitleAsyncTask
    public void gettingPlaylistName() {

        //Converting the List of Playlist id to String
        try {
            String[] playListIDsArray = new String[playListIDs.size()];
            playListIDs.toArray(playListIDsArray);

            new GetPlaylistTitlesAsyncTask(mYoutubeDataApi) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog01.show();
                }

                @Override
                protected void onPostExecute(PlaylistListResponse playlistListResponse) {
                    super.onPostExecute(playlistListResponse);
                    mPlaylistTitles = new ArrayList();
                    for (com.google.api.services.youtube.model.Playlist playlist : playlistListResponse.getItems()) {
                        mPlaylistTitles.add(playlist.getSnippet().getTitle());
                    }
                    // update the spinner adapter with the titles of the playlists
                    createRecyclerView(playListIDs, mPlaylistTitles);
                    if (progressDialog01.isShowing()) {
                        progressDialog01.dismiss();
                    }
                }
            }.execute(playListIDsArray);
        } catch (Exception e) {
            Log.e(TAG, "error " + e);
        }
    }

    public void createRecyclerView(List<String> playListIDsHidden, List<String> playListTitlesVisible) {

        for (int i = 0; i <= playListTitlesVisible.size() - 1; i++) {
            Log.d(TAG, "The Title in Visible text is  " + playListTitlesVisible.get(i));
            String hiddenLink = playListIDsHidden.get(i);
            String visibleName = playListTitlesVisible.get(i);
            HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenLink, visibleName);
            this.PlayListNamesList.add(hvDT);
        }

        adaptertwoItemsHV = new RecyclerAdaptertwoItemsHV(PlayListNamesList, this);
        rvForVideoTopic.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvForVideoTopic.setLayoutManager(layoutManager);
        rvForVideoTopic.setAdapter(adaptertwoItemsHV);
    }

    public void listItemClickMethod(String playlistLink, String playlistTitle) {
        if (checkNetwork()) {
            Intent intent = new Intent(this, VideoLister.class);
            intent.putExtra("PLAYLIST_LINK", playlistLink);
            intent.putExtra("PLAYLIST_TITLE", playlistTitle);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
        }
    }

    //Method to check internet connection
    public boolean checkNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    //Method for Checking Empty List View
    /*this method checks whether the Recycler View Contains Items or not
    i.e It checks whether the List is populated with in 10sec else it says Slow Connection
    if yes then no problem else it throws an Exception which is not Handled */
    public void listEmptyCheck() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    if (rvForVideoTopic.getAdapter().getItemCount() == 0) {
                        //Toast.makeText(getBaseContext(), "No items in List", Toast.LENGTH_SHORT).show();
                    } else {
                       // Toast.makeText(getBaseContext(), "the List has Items no Worries", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "The Recycler View has Items No worries");
                    }
                } catch (Exception e) {
                    dialogForSlowInternet();
                    Log.wtf(TAG, "The Recycler View has no items and my Code will Throw " +
                            "Exception to Handle the empty Check" +
                            "\n Exception : " + e);
                }
            }
        }, 10000); // 10000 milliseconds delay
    }

    //Alert box For Slow Connection
    public void dialogForSlowInternet() {
        progressDialog01.dismiss();

        AlertDialog.Builder alertBoxBuilder1 = new AlertDialog.Builder(this);
        alertBoxBuilder1.setMessage("Slow Internet Connection");

        alertBoxBuilder1.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        onBackPressed();
                    }
                });
        AlertDialog alert11 = alertBoxBuilder1.create();
        alert11.show();
    }
}
