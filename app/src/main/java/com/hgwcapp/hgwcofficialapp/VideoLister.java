package com.hgwcapp.hgwcofficialapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.databaseall.DataBaseAdapterC;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoContentDetails;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import com.recyclerp.all.RecyclerItemClickListener;
import com.youtubeplaylistallclasses.GetPlaylistAsyncTask;
import com.youtubeplaylistallclasses.LastItemReachedListener;
import com.youtubeplaylistallclasses.PlaylistVideos;
import com.youtubeplaylistallclasses.RecyclerYoutubePlaylistAdapter;

import java.util.List;

public class VideoLister extends AppCompatActivity {

    private static final String TAG = "Tag videoLister";
    DataBaseAdapterC DACvl;

    TextView tvHeaderVideoLister;
    RecyclerView rvVideoLister;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerYoutubePlaylistAdapter mRecyclerPlaylistAdapter;


    private PlaylistVideos mPlaylistVideos;
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();

    public ProgressDialog progressDialog02;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lister);


        tvHeaderVideoLister = (TextView) findViewById(R.id.tvHeaderVideoLister);

        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();

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
        String playListLink = VideoDatata.getString("PLAYLIST_LINK", "1");
        String PlayListTitle = VideoDatata.getString("PLAYLIST_TITLE", "Null");
        tvHeaderVideoLister.setText(PlayListTitle.toString());

        progressDialog02 = ProgressDialog.show(this, "", "Loading...", true);
        if (checkNetwork()) {

            listEmptyCheck();
            /*final ProgressDialog dialog = ProgressDialog.show(this, "", "Loading...", true);
            dialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }}, 2000); // 2000 milliseconds delay*/

            rvVideoLister = (RecyclerView) findViewById(R.id.rvVideoLister);
            rvVideoLister.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            rvVideoLister.setLayoutManager(new LinearLayoutManager(this));

            gettingVideoNamesForPlaylist(playListLink);

            rvVideoLister.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, rvVideoLister, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            final Video video = mPlaylistVideos.get(position);
                            final VideoSnippet videoSnippet = video.getSnippet();
                            final VideoContentDetails videoContentDetails = video.getContentDetails();
                            final VideoStatistics videoStatistics = video.getStatistics();

                            TextView videoTitle = (TextView) view.findViewById(R.id.tvTitle);

                            //String videoTitleString = videoTitle.getText().toString();

                            String link = "http://www.youtube.com/watch?v=" + video.getId();

                            dialogbox(videoTitle.getText().toString(), link);
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

    private void openforVideo() {
        DACvl = new DataBaseAdapterC(this);
        DACvl.open();
        Log.e(TAG, TAG + " Open method Successful");
    }

    public void gettingVideoNamesForPlaylist(String playlistID) {

        if (mPlaylistVideos != null) {
            // reload the UI with the existing playlist.  No need to fetch it again
            reloadUi(mPlaylistVideos, false);
        } else {
            // otherwise create an empty playlist using the first item in the playlist id's array
            mPlaylistVideos = new PlaylistVideos(playlistID);
            // and reload the UI with the selected playlist and kick off fetching the playlist content
            reloadUi(mPlaylistVideos, true);
        }
    }

    private void reloadUi(final PlaylistVideos playlistVideos, boolean fetchPlaylist) {
        // initialize the cards adapter
        initCardAdapter(playlistVideos);

        if (fetchPlaylist) {
            //dialog.show();
            // start fetching the selected playlistVideos contents
            new GetPlaylistAsyncTask(mYoutubeDataApi) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog02.show();
                }

                @Override
                public void onPostExecute(Pair<String, List<Video>> result) {
                    handleGetPlaylistResult(playlistVideos, result);
                    if (progressDialog02.isShowing()) {
                        progressDialog02.dismiss();
                    }
                }
            }.execute(playlistVideos.playlistId, playlistVideos.getNextPageToken());

        }

    }

    private void initCardAdapter(final PlaylistVideos playlistVideos) {
        // create the adapter with our playlistVideos and a callback to handle when we reached the last item


        mRecyclerPlaylistAdapter = new RecyclerYoutubePlaylistAdapter(playlistVideos, new LastItemReachedListener() {
            @Override
            public void onLastItem(int position, String nextPageToken) {
                new GetPlaylistAsyncTask(mYoutubeDataApi) {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog02.show();
                    }

                    @Override
                    public void onPostExecute(Pair<String, List<Video>> result) {
                        handleGetPlaylistResult(playlistVideos, result);
                        if (progressDialog02.isShowing()) {
                            progressDialog02.dismiss();
                        }
                    }
                }.execute(playlistVideos.playlistId, playlistVideos.getNextPageToken());
            }
        });
        rvVideoLister.setAdapter(mRecyclerPlaylistAdapter);
    }

    private void handleGetPlaylistResult(PlaylistVideos playlistVideos, Pair<String, List<Video>> result) {
        if (result == null) return;
        final int positionStart = playlistVideos.size();
        playlistVideos.setNextPageToken(result.first);
        playlistVideos.addAll(result.second);
        mRecyclerPlaylistAdapter.notifyItemRangeInserted(positionStart, result.second.size());

    }


    public void dialogbox(String name, String url) {
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

    public void openYouTube(String url) {
        //Toast.makeText(getBaseContext(), "link is \n" + url, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void shareString(String videoname, String urlString) {
        String shareBody = videoname + "\n" + urlString;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Watch ");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(sharingIntent);
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
                    if (rvVideoLister.getAdapter().getItemCount() == 0) {
                        //Toast.makeText(getBaseContext(), "No items in List", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getBaseContext(), "the List has Items no Worries", Toast.LENGTH_SHORT).show();
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
        progressDialog02.dismiss();

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
