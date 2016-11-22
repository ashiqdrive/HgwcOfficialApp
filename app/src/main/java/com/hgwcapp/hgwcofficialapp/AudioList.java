package com.hgwcapp.hgwcofficialapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.databaseall.DataBaseAdapterC;
import java.io.File;
import java.util.ArrayList;
import com.recyclerp.all.*;


public class AudioList extends AppCompatActivity {

    MainActivity MA;

    private static final String TAG = "Tag AudioList";
    DataBaseAdapterC DACal;

    String previousClassName;
    String classIDMain;
    String classNameMain;
    String spkIdMain;
    TextView classTopicHeader;

    ArrayList<HiddenVisibleDatatype> ListAudioList = new ArrayList<HiddenVisibleDatatype>();
    RecyclerView recyclerViewAudioList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdaptertwoItemsHV adaptertwoItemsHV;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);

        isStoragePermissionGranted();

        recyclerViewAudioList = (RecyclerView) findViewById(R.id.rvAudioList);
        classTopicHeader = (TextView) findViewById(R.id.tvClassTopicNameHeader);

        try {
            openMethod();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        Bundle previousClassData = getIntent().getExtras();
        if (previousClassData == null) {
            return;
        }

        previousClassName = previousClassData.getString("PREVIOUS_CLASS_NAME", "generalLectures");

        if (previousClassName.equals("generalLectures")) {
            spkIdMain = previousClassData.getString("SPEAKER_ID", "Null");
            classNameMain = previousClassData.getString("SPEAKER_NAME", "Null");
            classTopicHeader.setText(classNameMain);
            selectingGeneralLectureQueryMethod(spkIdMain);
            creatingClassTopicsRecyclerView();

        } else {
            classIDMain = previousClassData.getString("CLASS_ID", "Null");
            classNameMain = previousClassData.getString("CLASS_NAME", "Null");
            classTopicHeader.setText(classNameMain);
            selectingClassTopicsQueryMethod(classIDMain);
            creatingClassTopicsRecyclerView();

        }


        recyclerViewAudioList.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerViewAudioList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        TextView hidtv = (TextView) view.findViewById(R.id.twoItemsHiddentv);
                        TextView vtv = (TextView) view.findViewById(R.id.twoItemsVisibletv);

                        String hstLink = hidtv.getText().toString();
                        String vstFileNAme = vtv.getText().toString();

                        listItemClickMethod(vstFileNAme, hstLink);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        return;
                    }
                })
        );

    }

    private void openMethod() {
        DACal = new DataBaseAdapterC(this);
        DACal.open();
        Log.i(TAG, TAG + " open method Sucessfull");
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectingClassTopicsQueryMethod(String classid) {

        Cursor cursor = DACal.selectAudioListforClassTopics(classid);

        for (int i = 0; i < cursor.getCount(); i++) {
            String hiddenLink = cursor.getString(cursor.getColumnIndex(DACal.AUD_DOWNLOAD_LINK));
            String visileName = cursor.getString(cursor.getColumnIndex(DACal.AUD_FILE_NAME));
            HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenLink, visileName);
            ListAudioList.add(hvDT);
            cursor.moveToNext();
        }
    }

    public void creatingClassTopicsRecyclerView() {

        adaptertwoItemsHV = new RecyclerAdaptertwoItemsHV(ListAudioList, this);
        recyclerViewAudioList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewAudioList.setLayoutManager(layoutManager);
        recyclerViewAudioList.setAdapter(adaptertwoItemsHV);
    }

    public void selectingGeneralLectureQueryMethod(String spkid) {

        Cursor cursor = DACal.selectAudioListGeneralLectures(spkid);

        for (int i = 0; i < cursor.getCount(); i++) {
            String hiddenLink = cursor.getString(cursor.getColumnIndex(DACal.AUD_GEN_LINK));
            String visileName = cursor.getString(cursor.getColumnIndex(DACal.AUD_GEN_NAME));
            HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenLink, visileName);
            ListAudioList.add(hvDT);
            cursor.moveToNext();
        }
    }

    public void listItemClickMethod(String audioName, String audioURL) {
        dialogBoxList(audioName, audioURL);
    }

    public void dialogBoxList(String name, String url) {
        final String audioName = name;
        final String audioUrl = url;
        final Dialog dialogbox = new Dialog(this);
        dialogbox.setContentView(R.layout.audio_list_select_action);
        ListView lvSelectAction = (ListView) dialogbox.findViewById(R.id.lvSelectionAudioDialogBox);
        String[] selectioList = {"Download", "Play", "Share"};
        ListAdapter lvadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectioList);
        lvSelectAction.setAdapter(lvadapter);
        dialogbox.setCancelable(true);
        dialogbox.setTitle("Choose Action");
        dialogbox.show();
        lvSelectAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedPos = position;
                if (selectedPos == 0) {
                    dialogbox.dismiss();
                    downloadAudio(audioName, audioUrl);
                } else if (selectedPos == 1) {
                    dialogbox.dismiss();
                    fileToPlayCheck(audioName, audioUrl);
                } else if (selectedPos == 2) {
                    dialogbox.dismiss();
                    shareAudio(audioName, audioUrl);
                }
            }
        });
    }

    //Method to Download Audio
    public void downloadAudio(String name, String url) {

        final String audioName = name + ".mp3";
        final String dwnldUrl = url;
        final String dwnldDirectory = MA.DirectoryAudioDownload.toString();//"/sdcard/HGWCappsFiles/Audios_downloaded/"
        final String dwnldPATH = "/" + MA.FolderMainName + "/" + MA.FolderAudioDownload;

        //--checking whether file selected to download already exists
        File checkFile = new File(dwnldDirectory + "/" + audioName).getAbsoluteFile();
        if (checkFile.exists()) {
            audioOpener(dwnldDirectory + "/" + audioName);
            //Toast.makeText(getBaseContext(), "file " + audioName + " is already downloaded\n check All downloaded section." , Toast.LENGTH_LONG).show();
        } else {
            if (checkNetwork() == true) {
                Toast.makeText(getBaseContext(), "Audio downloading", Toast.LENGTH_SHORT).show();

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(dwnldUrl));
                request.setDescription("Hgwc Audio");
                request.setTitle("Hgwc Audio download");
                // in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(dwnldPATH, audioName);

                // get download service and enqueue file
                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            } else {
                Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Method to check file existence(so that it would play the file instead of asking user to download again)
    public void fileToPlayCheck(String name, String url) {
        final String audioName = name + ".mp3";
        final String dwnldDirectory = MA.DirectoryAudioDownload.toString();//"/sdcard/HGWCappsFiles/Audios_downloaded/"
        File checkFile = new File(dwnldDirectory + "/" + audioName).getAbsoluteFile();

        if (checkFile.exists()) {
            audioOpener(dwnldDirectory + "/" + audioName);
        } else {
            alertboxbuilt(name, url);
        }
    }

    //alertbox to be shown to download instead of playing direcly
    public void alertboxbuilt(String name, String url) {

        final String audioName = name;
        final String audioUrl = url;

        AlertDialog.Builder alertBoxBuilder1 = new AlertDialog.Builder(this);
        alertBoxBuilder1.setMessage("The Audio needs to be Downloaded");
        alertBoxBuilder1.setCancelable(true);
        alertBoxBuilder1.setPositiveButton("Download",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        downloadAudio(audioName, audioUrl);
                        dialog.cancel();
                    }
                });
        alertBoxBuilder1.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = alertBoxBuilder1.create();
        alert11.show();
    }

    //Method to open audio in a player
    private void audioOpener(String fileAbsolutePath) {

        File file = new File(fileAbsolutePath).getAbsoluteFile();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(file), "audio/*");
        startActivity(intent);
    }
    /*---- Plays Directly
    public void playDirectly(String name, String url) {

        if (checkNetwork() == true) {
            Intent i = new Intent(this, AudioPlayer.class);
            i.putExtra("audioName", name);
            i.putExtra("audioUrl", url);
            startActivity(i);
        } else {
            Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
        }
    }*/

    //method to be used for sharing audio url
    public void shareAudio(String name, String url) {
        String shareBody = name + "\n" + url;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Listen ");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(sharingIntent);
    }

    //Method to check internet connection
    public boolean checkNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    //Marshmellow Permission Checking Code
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Permission is granted");
                return true;
            } else {
                Log.i(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }
}
