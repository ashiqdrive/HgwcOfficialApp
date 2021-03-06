package com.hgwcapp.hgwcofficialapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.databaseall.DataBaseAdapterC;

import java.io.File;

public class BookMainPage extends AppCompatActivity {

    MainActivity MAbooks;
    DataBaseAdapterC DAC;
    TabHost th;
    private static final String TAG = "Books Main Tag";

    ListView lvMain;
    ListView lvNonMuslimBookLister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_main_page);
        lvNonMuslimBookLister = (ListView) findViewById(R.id.lvNonMuslimBookLister);
        th = (TabHost) findViewById(R.id.BooksTabHost);
        th.setup();

        isStoragePermissionGranted();

        try {
            openForbooks();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        //1st tab added here
        TabHost.TabSpec tabSpec1 = th.newTabSpec("tag1");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("Books");
        th.addTab(tabSpec1);
        //2nd tab added here
        TabHost.TabSpec tabSpec2 = th.newTabSpec("tag1");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("New-Muslims");
        th.addTab(tabSpec2);

        populateBooksCategory();
        NMselectquery();
    }

    private void openForbooks() {//code to open the SQL DB
        DAC = new DataBaseAdapterC(this);
        DAC.open();
        Log.d(TAG, "open method Sucessfull");
    }

    public void populateBooksCategory() {


        String[] ListItems = { //New Book CATEGORYs should be Added here and in the Switch *listItemClick() Method Below
                DAC.BookCat_CREED,
                DAC.BookCat_DUA,
                DAC.BookCat_PRAYER,
                DAC.BookCat_GENERAL,
                DAC.BookCat_QURAN,
                DAC.BookCat_SISTERS
        };
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListItems);
        lvMain = (ListView) findViewById(R.id.lvBooksCategoryList);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItemClick(position);
            }
        });
    }

    public void listItemClick(int itemSelected){

        String rowName;
        switch (itemSelected) {
            case 0:
                rowName = DAC.BookCat_CREED;
                break;
            case 1:
                rowName =  DAC.BookCat_DUA;
                break;
            case 2:
                rowName = DAC.BookCat_PRAYER;
                break;
            case 3:
                rowName =DAC.BookCat_GENERAL;
                break;
            case 4:
                rowName =  DAC.BookCat_QURAN;
                break;
            case 5:
                rowName =  DAC.BookCat_SISTERS;
                break;
            default:
                rowName = DAC.BookCat_CREED; //default
                break;
        }

        openBookLIster(rowName);
    }

    //Button methods
    public void openBookLIster(String rowname) {
        Intent intent = new Intent(this, BookLister.class);
        intent.putExtra("BOOK_CATEGORY_NAME", rowname);
        startActivity(intent);
    }


    //method of the query to populate Non-Muslim books
    public void NMselectquery() {
        try {

            String bookCat = DAC.BookCat_NEW_MUSLIM;
            Cursor cursor = DAC.getAllRowsbyLang(DAC.TABLE_PDF, DAC.ALL_PDF_KEYS, bookCat);
            String[] fromcursor = new String[]{DAC.PDF_NAME, DAC.PDF_LINK};
            int[] toViewids = new int[]{R.id.tvBokBooksName, R.id.tvBookLinks};
            SimpleCursorAdapter bookCursorAdapter;

            bookCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.custom_book_list1, cursor, fromcursor, toViewids);
            lvNonMuslimBookLister.setAdapter(bookCursorAdapter);
            Log.e(TAG, "List populated");
        } catch (Exception e) {
            Log.w(TAG, "Error in populating list view", e);
        }

        lvNonMuslimBookLister.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvpdfbookName = (TextView) view.findViewById(R.id.tvBokBooksName);
                TextView tvpdfLink = (TextView) view.findViewById(R.id.tvBookLinks);
                String pdfBookName = tvpdfbookName.getText().toString();
                String pdfBookURL = tvpdfLink.getText().toString();
                clickDecision(pdfBookName, pdfBookURL);
            }
        });
    }

    private void clickDecision(String name, String url) {

        final String dwnldDirectory = MAbooks.DirectoryPdfEnglish.toString();
        final String folderName = MAbooks.FolderPdfEnglish;
        final String dwnldUrl = url;
        final String pdfBookName = name + ".pdf";

        try {
            File checkFile = new File(dwnldDirectory + "/" + pdfBookName).getAbsoluteFile();
            if (checkFile.exists()) {
                nonMreaderOpen(dwnldDirectory + "/" + pdfBookName);
            } else {
                //Toast.makeText(getBaseContext(), "the file " + pdfBookName + " doesnt exist" + " in the directory\n" + dwnldDirectory, Toast.LENGTH_SHORT).show();
                alertboxbuilt(name, dwnldUrl, dwnldDirectory, folderName);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in click decision method ", e);
        }
    }

    //method to open the Non-Muslim Pdf file in a reader
    private void nonMreaderOpen(String fileAbsolutePath) {

        File file = new File(fileAbsolutePath).getAbsoluteFile();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        startActivity(intent);
    }

    //alertbox to inform tht the file would be dwnloaded only once
    public void alertboxbuilt(String name, String url, String directory, String folderName) {

        final String bookName = name;
        final String bookUrl = url;
        final String bookDirectory = directory;
        final String bookFolderName = folderName;


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("This specific  book needs to be downloaded. \n This will happen only once.");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Download",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        downloadTheBook(bookName, bookUrl, bookDirectory, bookFolderName);
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //Method to Download Book
    public void downloadTheBook(String name, String url, String directory, String folderName) {

        final String pdfBookName = name + ".pdf";
        final String dwnldUrl = url;
        final String dwnldDirectory = directory;
        final String dwnldPATH = "/" + MAbooks.FolderMainName + "/" + folderName;

        //--checking whether file selected to download already exists
        File checkFile = new File(dwnldDirectory + "/" + pdfBookName).getAbsoluteFile();
        if (checkFile.exists()) {
            //Toast.makeText(getBaseContext(), "file " + pdfBookName + "already downloaded\n check All downloaded section ", Toast.LENGTH_LONG).show();
        } else {
            if (checkNetwork() == true) {
                Toast.makeText(getBaseContext(), "Book downloading", Toast.LENGTH_SHORT).show();

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(dwnldUrl));
                request.setDescription("Hgwc Audio");
                request.setTitle("Hgwc download");
                // in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(dwnldPATH, pdfBookName);

                // get download service and enqueue file
                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            } else {
                Toast.makeText(getBaseContext(), "Check your connection and try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Method to check internet connection
    public boolean checkNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    //Marshmellow Permission Checking Code
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG,"Permission is granted");
                return true;
            } else {
                Log.i(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.i(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
