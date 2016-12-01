package com.hgwcapp.hgwcofficialapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        if (checkNetwork()) {
            rvForVideoTopic = (RecyclerView) findViewById(R.id.rvForVideoTopic);

            selectQueryMethod(lanIdMain, spkIDMain);

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
                protected void onPostExecute(PlaylistListResponse playlistListResponse) {
                    super.onPostExecute(playlistListResponse);
                    mPlaylistTitles = new ArrayList();
                    for (com.google.api.services.youtube.model.Playlist playlist : playlistListResponse.getItems()) {
                        mPlaylistTitles.add(playlist.getSnippet().getTitle());
                    }
                    // update the spinner adapter with the titles of the playlists
                    createRecyclerView(playListIDs, mPlaylistTitles);
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
        }
        else{
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
