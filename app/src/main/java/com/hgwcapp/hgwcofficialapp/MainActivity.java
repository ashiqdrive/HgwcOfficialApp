package com.hgwcapp.hgwcofficialapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.databaseall.DataBaseAdapterC;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    DataBaseAdapterC DACmain;
    private static final String TAG = "MainActivity tag";

    //_Folder Names
    public static final String FolderMainName = "HGWC_App_Files";//Main folder name

    public static final String FolderAudioDownload = "Audios_downloaded";
    public static final String FolderPdfEnglish = "pdf_English";
    public static final String FolderPdfTamil = "pdf_Tamil";
    public static final String FolderPdfUrdu = "pdf_Urdu";
    public static final String FolderPdfNonMuslims = "pdf_Others";
    public static final String FolderTEMP = "temp";

    //_Directory Path Variables
    public final String PathMain = Environment.getExternalStorageDirectory() + "/" + FolderMainName;//Main path name

    public static final String DirectoryAudioDownload = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderAudioDownload;
    public static final String DirectoryPdfEnglish = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderPdfEnglish;
    public static final String DirectoryPdfTamil = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderPdfTamil;
    public static final String DirectoryPdfUrdu = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderPdfUrdu;
    public static final String DirectoryPdfNonMuslims = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderPdfNonMuslims;
    public static final String DirectoryTEMP = Environment.getExternalStorageDirectory() + "/" + FolderMainName + "/" + FolderTEMP;

    String folderNames[] = {DirectoryAudioDownload, DirectoryPdfEnglish, DirectoryPdfTamil, DirectoryPdfUrdu, DirectoryPdfNonMuslims, DirectoryTEMP};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isStoragePermissionGranted();// Marshmallow Storage Write permission
        folderCheck();
        subFolderCheck();
        try {
            openformain();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }
        //the below is done to check whether the insert statement has been done or not
        tableInsertCheck();
    }

    private void openformain() {
        DACmain = new DataBaseAdapterC(this);
        DACmain.open();
        Log.i(TAG, "open method Sucessfull");
    }

    // Main folder existence check
    public void folderCheck() {
        File mainFile = new File(PathMain);
        try {
            if (mainFile.isDirectory()) {
                Log.d(TAG, "Main Folder already exists");
            } else {
                mainFile.mkdirs();
                Log.d(TAG, "No Main Folder so created");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error: No Folder\n" + e);
        }
    }

    //Sub Folder existence check
    public void subFolderCheck() {

        for (int i = 0; i < folderNames.length; i++) {
            File file = new File(folderNames[i]);
            try {
                if (file.isDirectory()) {
                    Log.d(TAG, "Folder " + folderNames[i] + " already exists");
                } else {
                    file.mkdirs();
                    Log.w(TAG, "No Folder " + folderNames[i] + " so created");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error: No Folder\n" + e);
            }
        }
    }

    public void tableInsertCheck() {
        /* this method is to check whether all tables are inserted
        * if the result is 0 means there is no values in the table so it inserts the values by calling the respective insert method*/

        //CHECKING INSERT FOR LANGUAGE TABLE
        if (DACmain.getRowCount(DACmain.TABLE_LANGUAGE, DACmain.ALL_LANGUAGE_KEYS) == 0) {
            DACmain.insertLanguageTable();
        } else {
            Log.d(TAG, "Language table already inserted its more than 0");
        }
        //CHECKING INSERT FOR SPEAKER TABLE
        if (DACmain.getRowCount(DACmain.TABLE_SPEAKER, DACmain.ALL_SPEAKER_KEYS) == 0) {
            DACmain.insertSpeakerTable();
        } else {
            Log.d(TAG, "SPEAKER table already inserted its more than 0");
        }
        //CHECKING INSERT FOR Audio Class Topic TABLE
        if (DACmain.getRowCount(DACmain.TABLE_AUDIO_TOPIC, DACmain.ALL_AUDIO_TOPIC_KEYS) == 0) {
            DACmain.insertAudioTopicTable();
        } else {
            Log.d(TAG, " Audio Class Topic TABLE already inserted its more than 0");
        }
        //CHECKING INSERT FOR Audio List TABLE
        if (DACmain.getRowCount(DACmain.TABLE_AUDIO_LIST, DACmain.ALL_AUDIO_LIST_KEYS) == 0) {
            DACmain.insertAudioList();
        } else {
            Log.d(TAG, "Audio List TABLE already inserted its more than 0");
        }
        //CHECKING INSERT FOR GENERAL AUDIO TABLE
        if (DACmain.getRowCount(DACmain.TABLE_AUDIO_GENERAL, DACmain.ALL_AUDIO_GENERAL_KEYS) == 0) {
            DACmain.insertAudioGeneral();
        } else {
            Log.d(TAG, "Audio General Lectures TABLE already inserted its more than 0");
        }
        //CHECKING INSERT FOR PDF TABLE
        if (DACmain.getRowCount(DACmain.TABLE_PDF, DACmain.ALL_PDF_KEYS) == 0) {
            DACmain.insertPdfTable();
        } else {
            Log.d(TAG, "PDF table already inserted its more than 0");
        }
        //CHECKING INSERT FOR VIDEONAME TABLE
        if (DACmain.getRowCount(DACmain.TABLE_VIDEO_NAME, DACmain.ALL_VIDEONAMES_KEYS) == 0) {
            DACmain.insertVideoNameTable();
        } else {
            Log.d(TAG, "VideoName table already inserted\nits more than 0");
        }
        //CHECKING INSERT FOR VIDEO TABLE
        if (DACmain.getRowCount(DACmain.TABLE_VIDEO, DACmain.ALL_VIDEO_KEYS) == 0) {
            DACmain.insertVideoTable();
        } else {
            Log.d(TAG, "VIDEO table already inserted\nits more than 0");
        }

    }

    //Button clicks methods
    public void ivAudioClickMethod(View v) {
        Intent intent = new Intent(this, AudioMainPage.class);
        startActivity(intent);
    }

    public void ivBooksClickMethod(View v) {
        Intent intent = new Intent(this, BookMainPage.class);
        startActivity(intent);
    }

    public void ivVideoClickMethod(View v) {
        Intent intent = new Intent(this, VideoMainPage.class);
        startActivity(intent);
    }

    public void ivWebsiteClickMethod(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setData(Uri.parse("http://hgwc.in/"));
        startActivity(intent);
    }

    public void ivFacebookClickMethod(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setData(Uri.parse("https://www.facebook.com/hgwcin/"));
        startActivity(intent);
    }

    public void ivTwitterClickMethod(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setData(Uri.parse("https://www.twitter.com/hgwcin/"));
        startActivity(intent);
    }

    public void ivYoutubeClickMethod(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setData(Uri.parse("https://www.youtube.com/channel/UCnaa1bkxCtRzhVlZ60PuY4A"));
        startActivity(intent);
    }

    public void logoClickMethod(View v) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("About");
        builder1.setMessage("HGWC (Human Guidance and Welfare Centre) has been affirmed and Certified by the Renowned Scholars of India which makes it a Completely Trusted Organization");
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton("Visit Website", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setData(Uri.parse("http://hgwc.in/"));
                startActivity(intent);
                dialog.cancel();
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //_____Button clicks methods__Ends______ENDS________ENDS_________


    //Below code is used to check Marshmallow Storage Write permission
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainpagemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share_app_menu:
                shareApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareApp() {
        String shareBody = "Download the HGWC Android app\n https://play.google.com/store/apps/details?id=com.hgwcapp.hgwcofficialapp ";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(sharingIntent);
    }

    // Initialize the view
    private static String navigateFrom;//String to get Intent Value

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Set Back Icon on Activity
        navigateFrom = getIntent().getStringExtra("navigateFrom");//Get Intent Value in String

    }

}
