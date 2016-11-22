package com.databaseall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DataBaseAdapterC {
    private static final String TAG = "DataBaseAdapterTag"; //used for logging database version changes

    public static final String DATABASE_NAME = "hgwcappdbbasesses.db";
    public static final int DATABASE_VERSION = 4;//should be updated every time when application is updated
    //Last version i.e version 2 has DataBase vr =1 (26-07-2016)

    public static final String TABLE_LANGUAGE = "languagetable";
    public static final String TABLE_SPEAKER = "speakertable";
    public static final String TABLE_AUDIO_TOPIC = "audiotopictable";
    public static final String TABLE_AUDIO_LIST = "audiolisttable";
    public static final String TABLE_AUDIO_GENERAL = "audiogeneraltable";
    public static final String TABLE_PDF = "pdftable";
    public static final String TABLE_VIDEO_NAME = "videonametable";
    public static final String TABLE_VIDEO = "videotable";


    //==Constructors below
    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public DataBaseAdapterC(Context context) {
        this.context = context;
        myDBHelper = new DatabaseHelper(context);
    }

    public DataBaseAdapterC open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        myDBHelper.close();
    }

    //=== Row NAMES ====== Row NAMES ====== ROW NAMES ===========================

    //Language Table Row Names
    public static final String LAN_ID = "_id";
    public static final String LAN_LANGUAGE = "language";
    //Speaker Table Row Names
    public static final String SPK_ID = "_id";
    public static final String SPK_NAMES = "speakernames";
    public static final String SPK_IF_CLASS = "speakerifclass";
    public static final String SPK_IF_GENERAL = "speakerifgeneral";
    public static final String SPK_IF_ENG = "speakerifeng";
    public static final String SPK_IF_TAMIL = "speakeriftamil";
    public static final String SPK_IF_URDU = "speakerifurdu";

    //Audio Topics ClassTopics Table Row Names
    public static final String AUD_Topic_ID = "_id";
    public static final String AUD_Topic_NAME = "_aidname";
    public static final String AUD_Topic_LAN_ID = "_lanid";
    public static final String AUD_Topic_SPK_ID = "_spkid";

    //Audio List Table Row Names
    public static final String AUD_ID = "_id";
    public static final String AUD_FRID = "_frid";//foreign key to Table_AudioClassTopics
    public static final String AUD_DOWNLOAD_LINK = "link";
    public static final String AUD_FILE_NAME = "filename";
    public static final String AUD_LAN_ID = "_lanid";
    public static final String AUD_SPK_ID = "_spkid";

    //Audio General Table row Names
    public static final String AUD_GEN_ID = "_id";
    public static final String AUD_GEN_NAME = "_audgname";
    public static final String AUD_GEN_LINK = "_audglink";
    public static final String AUD_GEN_LAN_ID = "_lanid";
    public static final String AUD_GEN_SPK_ID = "_spkid";


    //VideoName Table Row Names
    public static final String VNID_ID = "_id";
    public static final String VNID_NAME = "_vidname";
    public static final String VNID_LAN_ID = "_lanid";
    public static final String VNID_SPK_ID = "_spkid";
    public static final String VNID_SPK_NAME = "spkname";
    //Video Table Row Names
    public static final String VID_ID = "_id";
    public static final String VID_FRID = "_frid";
    public static final String VID_DOWNLOAD_LINK = "link";
    public static final String VID_NAME = "vidname";
    public static final String VID_LAN_ID = "_lanid";
    public static final String VID_SPK_ID = "_spkid";

    //Pdf Table Row Names
    public static final String PDF_ID = "_id";
    public static final String PDF_NAME = "name";
    public static final String PDF_LAN_ID = "_lanid";
    public static final String PDF_LINK = "links";

    //Image Table Row Names
    public static final String IMG_ID = "_id";
    public static final String IMG_RAW_ID = "rawid";
    public static final String IMG_LAN_ID = "_lanid";

    // ALL_KEYS__ALL_KEYS__ALL_KEYS__ALL_KEYS __ALL_KEYS __ALL_KEYS __ALL_KEYS __ALL_KEYS
    public static final String ALL_LANGUAGE_KEYS[] = {LAN_ID, LAN_LANGUAGE};
    public static final String ALL_SPEAKER_KEYS[] = {SPK_ID, SPK_NAMES, SPK_IF_CLASS, SPK_IF_GENERAL, SPK_IF_ENG, SPK_IF_TAMIL, SPK_IF_URDU};
    public static final String ALL_AUDIO_TOPIC_KEYS[] = {AUD_Topic_ID, AUD_Topic_NAME, AUD_Topic_LAN_ID, AUD_Topic_SPK_ID};
    public static final String ALL_AUDIO_LIST_KEYS[] = {AUD_ID, AUD_FRID, AUD_DOWNLOAD_LINK, AUD_FILE_NAME, AUD_LAN_ID, AUD_SPK_ID};
    public static final String ALL_AUDIO_GENERAL_KEYS[] = {AUD_GEN_ID, AUD_GEN_NAME, AUD_GEN_LINK, AUD_GEN_LAN_ID, AUD_GEN_SPK_ID};
    public static final String ALL_PDF_KEYS[] = {PDF_ID, PDF_NAME, PDF_LAN_ID, PDF_LINK};
    public static final String ALL_VIDEONAMES_KEYS[] = {VNID_ID, VNID_NAME, VNID_LAN_ID, VNID_SPK_ID, VNID_SPK_NAME};
    public static final String ALL_VIDEO_KEYS[] = {VID_ID, VID_FRID, VID_DOWNLOAD_LINK, VID_NAME, VID_LAN_ID, VID_SPK_ID};


    //*************CREATE STATEMENTS*******************

    //Create SQL statement for Language table

    private static final String CREATE_LANGUAGE_SQL =
            "CREATE TABLE " + TABLE_LANGUAGE
                    + " ("
                    + LAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LAN_LANGUAGE + " TEXT "
                    + ");";

    //Create SQL statement for SPEAKERs table
    private static final String CREATE_SPEAKER_SQL =
            "CREATE TABLE " + TABLE_SPEAKER
                    + " ("
                    + SPK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SPK_NAMES + " TEXT, "
                    + SPK_IF_CLASS + " INTEGER, "
                    + SPK_IF_GENERAL + " INTEGER, "
                    + SPK_IF_ENG + " INTEGER, "
                    + SPK_IF_TAMIL + " INTEGER, "
                    + SPK_IF_URDU + " INTEGER "
                    + ");";

    //Create SQL statement for AudioNAME Table
    private static final String CREATE_AUDIO_TOPIC_SQL =
            "CREATE TABLE " + TABLE_AUDIO_TOPIC
                    + " ("
                    + AUD_Topic_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AUD_Topic_NAME + " TEXT, "
                    + AUD_Topic_LAN_ID + " INTEGER, "
                    + AUD_Topic_SPK_ID + " INTEGER "
                    + ");";
    //Create SQL statement for AUDIO Table
    private static final String CREATE_AUDIO_LIST_SQL =
            "CREATE TABLE " + TABLE_AUDIO_LIST
                    + " ("
                    + AUD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AUD_FRID + " INTEGER, "
                    + AUD_DOWNLOAD_LINK + " TEXT, "
                    + AUD_FILE_NAME + " TEXT, "
                    + AUD_LAN_ID + " INTEGER, "
                    + AUD_SPK_ID + " INTEGER "
                    + ");";

    //Create SQL statement for Audio General Table
    private static final String CREATE_AUDIO_GENERAL_SQL =
            "CREATE TABLE " + TABLE_AUDIO_GENERAL
                    + " ("
                    + AUD_GEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AUD_GEN_NAME + " TEXT, "
                    + AUD_GEN_LINK + " TEXT, "
                    + AUD_GEN_LAN_ID + " INTEGER, "
                    + AUD_GEN_SPK_ID + " INTEGER "
                    + ");";


    //Create SQL statement for VIDEO NAME Table
    private static final String CREATE_VIDEO_NAME_SQL =
            "CREATE TABLE " + TABLE_VIDEO_NAME
                    + " (" + VNID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + VNID_NAME + " TEXT, "
                    + VNID_LAN_ID + " INTEGER, "
                    + VNID_SPK_ID + " INTEGER, "
                    + VNID_SPK_NAME + " TEXT "
                    + ");";
    //Create SQL statement for VIDEO Table
    private static final String CREATE_VIDEO_SQL =
            "CREATE TABLE " + TABLE_VIDEO
                    + " (" + VID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + VID_FRID + " INTEGER, "
                    + VID_DOWNLOAD_LINK + " TEXT, "
                    + VID_NAME + " TEXT, "
                    + VID_LAN_ID + " INTEGER, "
                    + VID_SPK_ID + " INTEGER "
                    + ");";

    //Create SQL statement for PDF Table
    private static final String CREATE_PDF_SQL =
            "CREATE TABLE " + TABLE_PDF
                    + " (" + PDF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PDF_NAME + " TEXT, "
                    + PDF_LAN_ID + " INTEGER, "
                    + PDF_LINK + " TEXT "
                    + ");";


    //_________CREATE STATEMENTS______ENDS_______ENDS__________________


    //********** INSERT STATEMENTS **** STARTS **** STARTS **** STARTS ****
    //************************ I N S E R T **************************************************************************

    String allLanguagesArray[] = { //add new languages in this Array and update the DB version
            "english",
            "tamil",
            "urdu",
            "nonmuslims"
    };

    public void insertLanguageTable() {
        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < allLanguagesArray.length; i++) {
                cv.put(LAN_LANGUAGE, allLanguagesArray[i]);
                db.insert(TABLE_LANGUAGE, null, cv);
            }
            Log.e(TAG, "Language table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "Sql Exception Language Insertion\n" + e);
        }
    }

    //=====Lanid Languages which are used in insert statements
    public static final int LidEngKey = 1;
    public static final int LidTamKey = 2;
    public static final int LidUrdKey = 3;
    public static final int LidNonMKey = 4;

    public static final String LidStringEnglish = Integer.toString(LidEngKey);
    public static final String LidStringTamil = Integer.toString(LidTamKey);
    public static final String LidStringUrdu = Integer.toString(LidUrdKey);


//___________________________________________________________________________________________________

    //* * * * * * * * Speaker Table SQL Insert statement * * * * * * * * * *
    public void insertSpeakerTable() {
        String allSpeakersArray[] = {
                "Sh Abdul Majeed Mahzari",//1
                "Sh Abdus Salaam Madani",//2
                "Sh Aneesur Rahman Madani",//3
                "Sh Ansarudeen Makki",//4
                "Br M.F Ali",//5
                "Sh Dr R.K.Noor Madani",//6
                "Multiple",//7
                "Sh Abdullah Hydrabadi"//8
        };
        String ifClassarray[] = {"1", "1", "1", "1", "1", "1", "1", "0"};
        String ifGenerarray[] = {"0", "0", "0", "0", "0", "0", "0", "1"};
        String ifEngliarray[] = {"0", "1", "0", "0", "0", "0", "0", "0"};
        String ifTamilarray[] = {"1", "0", "0", "0", "1", "0", "1", "0"};
        String ifUrduuarray[] = {"0", "0", "1", "1", "0", "1", "0", "1"};

        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < allSpeakersArray.length; i++) {
                cv.put(SPK_NAMES, allSpeakersArray[i]);
                cv.put(SPK_IF_CLASS, ifClassarray[i]);
                cv.put(SPK_IF_GENERAL, ifGenerarray[i]);
                cv.put(SPK_IF_ENG, ifEngliarray[i]);
                cv.put(SPK_IF_TAMIL, ifTamilarray[i]);
                cv.put(SPK_IF_URDU, ifUrduuarray[i]);
                db.insert(TABLE_SPEAKER, null, cv);
            }
            Log.e(TAG, "SPEAKER table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "Sql Exception Speaker Insertion\n" + e);
        }
    }

    //below Are Final Integers based on the Speaker NAmes inserted in the Speaker table table
    /*these ID are based on the names inserted in the table and Array */
    public static final int SpkIntAbdulMajeed = 1;
    public static final int SpkIntAbdusSalaamMadani = 2;
    public static final int SpkIntAneesRahman = 3;
    public static final int SpkIntAnsarudinMakki = 4;
    public static final int SpkIntMFAli = 5;
    public static final int SpkIntRKNoorMadani = 6;
    public static final int SpkIntMultiple = 7;
    public static final int SpkIntAbdullahHydrabadi = 8;
//__________________________________________________________________________________________________________________________

    //Audio Topics classes Topics table SQL Insert statement

    public void insertAudioTopicTable() {
        String TopicNameArray[] = {
                "Aqeeda Al Wasitiya",//1
                "Gumrah firqh aur unki haqeeqatein",//2
                "Hujjiyat e azmath e sahaba",//3
                "Dawah Training Program",//4
                "Mundru Adipadaigal",//5
                "Sihr Sooniyam",//6
                "Taqwiyathul Eeman",//7
                "Ramadan Duroos 1435H(2014)",//8
                "Ramadan Duroos 2016",//9
                "Ahkmaat e Quran",//10
                "Dars e Quran",//11
                //"Dars e Hadeeth",//12
                //"Usool us Salasa",//13
                //"Kitab at Tawheed"//14
        };

        int TopicLanidArray[] = {
                LidUrdKey,//1
                LidUrdKey,//2
                LidUrdKey,//3
                LidTamKey,//4
                LidTamKey,//5
                LidTamKey,//6
                LidUrdKey,//7
                LidUrdKey,//8
                LidUrdKey,//9
                LidUrdKey,//10
                LidUrdKey,//11
                //LidUrdKey,//12
                //LidUrdKey,//13
                //LidEngKey//14
        };

        int TopicSpkidArray[] = {
                SpkIntRKNoorMadani,//1
                SpkIntAnsarudinMakki,//2
                SpkIntRKNoorMadani,//3
                SpkIntMFAli,//4
                SpkIntAbdulMajeed,//5
                SpkIntMultiple,//6
                SpkIntAneesRahman,//7
                SpkIntAneesRahman,//8
                SpkIntAneesRahman,//9
                SpkIntAneesRahman,//10
                SpkIntAneesRahman,//11
                // SpkIntAneesRahman,//12
                // SpkIntAnsarudinMakki,//13
                //SpkIntAbdusSalaamMadani//14
        };
        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < TopicNameArray.length; i++) {
                cv.put(AUD_Topic_NAME, TopicNameArray[i]);
                cv.put(AUD_Topic_LAN_ID, TopicLanidArray[i]);
                cv.put(AUD_Topic_SPK_ID, TopicSpkidArray[i]);
                db.insert(TABLE_AUDIO_TOPIC, null, cv);
            }
            Log.e(TAG, "Audio Topic table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "Sql Exception Audio Topic Insertion\n" + e);
        }
    }
//__________________________________________________________________________________________________________________________________________________________________________________________

    //* * * * * * * Audio List Table Insert * * * * *
    public void insertAudioList() {

        /*how to add new audios in the list table
        * Hashmap Key format is = the foreign an hyphen then zero then fileNo
          //Example
          * hmFRID.put(FrKey-0FileNo,FrKey);
          * hmLink.put(FrKey-0FileNo,Link);
          * hmFileName.put(FrKey-0FileNo,FileName);
          * hmLanID.put(FrKey-0FileNo,Lanid);
          * hmSpkID.put(FrKey-0FileNo,Spkid);
          * */

        HashMap<String, String> hmFRID = new HashMap<String, String>();
        HashMap<String, String> hmLink = new HashMap<String, String>();
        HashMap<String, String> hmFileName = new HashMap<String, String>();
        HashMap<String, Integer> hmLanID = new HashMap<String, Integer>();
        HashMap<String, Integer> hmSpkID = new HashMap<String, Integer>();

        // Hash Map Foreign ID (i.e 2nd row of the Audio List Table After _id)---------------------
        hmFRID.put("1-01", "1");
        hmFRID.put("1-02", "1");
        hmFRID.put("1-03", "1");
        hmFRID.put("1-04", "1");
        hmFRID.put("1-05", "1");
        hmFRID.put("1-06", "1");
        hmFRID.put("1-07", "1");
        hmFRID.put("1-08", "1");
        hmFRID.put("1-09", "1");
        hmFRID.put("1-010", "1");
        hmFRID.put("1-011", "1");
        hmFRID.put("1-012", "1");
        hmFRID.put("1-013", "1");
        hmFRID.put("1-014", "1");
        hmFRID.put("1-015", "1");
        hmFRID.put("1-016", "1");
        hmFRID.put("1-017", "1");
        hmFRID.put("1-018", "1");
        hmFRID.put("2-01", "2");
        hmFRID.put("2-02", "2");
        hmFRID.put("2-03", "2");
        hmFRID.put("2-04", "2");
        hmFRID.put("2-05", "2");
        hmFRID.put("2-06", "2");
        hmFRID.put("2-07", "2");
        hmFRID.put("2-08", "2");
        hmFRID.put("2-09", "2");
        hmFRID.put("2-010", "2");
        hmFRID.put("2-011", "2");
        hmFRID.put("2-012", "2");
        hmFRID.put("2-013", "2");
        hmFRID.put("3-01", "3");
        hmFRID.put("3-02", "3");
        hmFRID.put("3-03", "3");
        hmFRID.put("3-04", "3");
        hmFRID.put("4-01", "4");
        hmFRID.put("4-02", "4");
        hmFRID.put("4-03", "4");
        hmFRID.put("5-01", "5");
        hmFRID.put("5-02", "5");
        hmFRID.put("5-03", "5");
        hmFRID.put("5-04", "5");
        hmFRID.put("5-05", "5");
        hmFRID.put("5-06", "5");
        hmFRID.put("5-07", "5");
        hmFRID.put("5-08", "5");
        hmFRID.put("5-09", "5");
        hmFRID.put("6-01", "6");
        hmFRID.put("6-02", "6");
        hmFRID.put("6-03", "6");
        hmFRID.put("6-04", "6");
        hmFRID.put("7-01", "7");
        hmFRID.put("7-02", "7");
        hmFRID.put("7-03", "7");
        hmFRID.put("7-04", "7");
        hmFRID.put("7-05", "7");
        hmFRID.put("7-06", "7");
        hmFRID.put("8-01", "8");
        hmFRID.put("8-02", "8");
        hmFRID.put("8-03", "8");
        hmFRID.put("8-04", "8");
        hmFRID.put("8-05", "8");
        hmFRID.put("8-06", "8");
        hmFRID.put("8-07", "8");
        hmFRID.put("8-08", "8");
        hmFRID.put("8-09", "8");
        hmFRID.put("8-010", "8");
        hmFRID.put("8-011", "8");
        hmFRID.put("8-012", "8");
        hmFRID.put("8-013", "8");
        hmFRID.put("8-014", "8");
        hmFRID.put("8-015", "8");
        hmFRID.put("8-016", "8");
        hmFRID.put("8-017", "8");
        hmFRID.put("8-018", "8");
        hmFRID.put("8-019", "8");
        hmFRID.put("8-020", "8");
        hmFRID.put("8-021", "8");
        hmFRID.put("8-022", "8");
        hmFRID.put("8-023", "8");
        hmFRID.put("9-01", "9");
        hmFRID.put("9-02", "9");
        hmFRID.put("9-03", "9");
        hmFRID.put("9-04", "9");
        hmFRID.put("9-05", "9");
        hmFRID.put("9-06", "9");
        hmFRID.put("9-07", "9");
        hmFRID.put("9-08", "9");
        hmFRID.put("9-09", "9");
        hmFRID.put("9-010", "9");
        hmFRID.put("9-011", "9");
        hmFRID.put("9-012", "9");
        hmFRID.put("9-013", "9");
        hmFRID.put("10-01", "10");
        hmFRID.put("10-02", "10");
        hmFRID.put("10-03", "10");
        hmFRID.put("10-04", "10");
        hmFRID.put("10-05", "10");
        hmFRID.put("10-06", "10");
        hmFRID.put("10-07", "10");
        hmFRID.put("10-08", "10");
        hmFRID.put("10-09", "10");
        hmFRID.put("10-010", "10");
        hmFRID.put("10-011", "10");
        hmFRID.put("10-012", "10");
        hmFRID.put("10-013", "10");
        hmFRID.put("10-014", "10");
        hmFRID.put("10-015", "10");
        hmFRID.put("10-016", "10");
        hmFRID.put("10-017", "10");
        hmFRID.put("10-018", "10");
        hmFRID.put("10-019", "10");
        hmFRID.put("10-020", "10");
        hmFRID.put("10-021", "10");
        hmFRID.put("10-022", "10");
        hmFRID.put("10-023", "10");
        hmFRID.put("10-024", "10");
        hmFRID.put("10-025", "10");
        hmFRID.put("10-026", "10");
        hmFRID.put("10-027", "10");
        hmFRID.put("11-01", "11");
        hmFRID.put("11-02", "11");
        hmFRID.put("11-03", "11");
        hmFRID.put("11-04", "11");
        hmFRID.put("11-05", "11");
        hmFRID.put("11-06", "11");
        hmFRID.put("11-07", "11");
        hmFRID.put("11-08", "11");
        hmFRID.put("11-09", "11");
        hmFRID.put("11-010", "11");
        hmFRID.put("11-011", "11");
        hmFRID.put("11-012", "11");
        hmFRID.put("11-013", "11");
        hmFRID.put("11-014", "11");
        hmFRID.put("11-015", "11");
        hmFRID.put("11-016", "11");
        hmFRID.put("11-017", "11");
        hmFRID.put("11-018", "11");
        hmFRID.put("11-019", "11");

        //Hash Map for Links------------------------------------------------------------------------------
        hmLink.put("1-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RWlXUkQxTmJDcXc");
        hmLink.put("1-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NWplWV9VR0xUR0U");
        hmLink.put("1-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ejBNdlZ3Q2hlRTg");
        hmLink.put("1-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cENwZ3pZT3lKVVU");
        hmLink.put("1-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z3JKUWc2VkpqQjg");
        hmLink.put("1-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T25QTnZXUE96ems");
        hmLink.put("1-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R0N2U3lZQWRRQnM");
        hmLink.put("1-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZU8zS3VTdDZ3YXc");
        hmLink.put("1-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TUttdWpFOWFRaGc");
        hmLink.put("1-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eDBjcC1PSHhDZ2s");
        hmLink.put("1-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aUxMM0xiX0ZtMTg");
        hmLink.put("1-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OU1hYjlENEtuVUE");
        hmLink.put("1-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aEcyeTBfZ1gxV0U");
        hmLink.put("1-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzFmNW9UUF9WSG8");
        hmLink.put("1-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZGpHS2pGT2xJOVE");
        hmLink.put("1-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1puS0I2VWNKUVk");
        hmLink.put("1-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R29rcDF4dGJZR1U");
        hmLink.put("1-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NURjVVlldW5YTXM");
        hmLink.put("2-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aFlrUXZ0aTNPNWc");
        hmLink.put("2-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aHQ1RjJvN1dFOGs");
        hmLink.put("2-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YUh2cFR4TExXR1U");
        hmLink.put("2-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aVVoaThIQ3lLSWc");
        hmLink.put("2-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aDQ2elRXTkhyM1E");
        hmLink.put("2-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RHV5SW1abXRqc3c");
        hmLink.put("2-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YXFBTlhqZWo4Slk");
        hmLink.put("2-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cExNRGJWakd6TGM");
        hmLink.put("2-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UEtTX0J3dGEtSkE");
        hmLink.put("2-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MFB6MDV0RnR5b0E");
        hmLink.put("2-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4d05fcTZCLV9BalE");
        hmLink.put("2-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZGFqaFlwbnd0Vnc");
        hmLink.put("2-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZXQ1bHFIOW0wR0k");
        hmLink.put("3-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4XzhhcUI0a3otd1E");
        hmLink.put("3-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4U2FFTy12b2h2eE0");
        hmLink.put("3-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SkJpanQwZ3pJUE0");
        hmLink.put("3-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZFZ6UDhOQ2dBQzg");
        hmLink.put("4-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NTVaZ3dNZGJGR1k");
        hmLink.put("4-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4clROQzdHcEtLQ0k");
        hmLink.put("4-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZzlGNlVyQ3h4Vmc");
        hmLink.put("5-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YnRXUGdyYzJFSUE");
        hmLink.put("5-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T3lKTkV5OEs2NXc");
        hmLink.put("5-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bGVnWGY0UDlnakU");
        hmLink.put("5-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cFVja3VqRVVNWUk");
        hmLink.put("5-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bmlIMnRvcGVtcXM");
        hmLink.put("5-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ekNSNzBfSTJuZkk");
        hmLink.put("5-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzhiODllVmkyTkU");
        hmLink.put("5-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4LTBTUXZjdnBLcG8");
        hmLink.put("5-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Q3JRM2Q4c1dVbGc");
        hmLink.put("6-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dnV3TE9IRmQxdjg");
        hmLink.put("6-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VjVIZEN3c1pFV00");
        hmLink.put("6-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UlFwUHhuai1oMGs");
        hmLink.put("6-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MXRkeFQ4X3plLTQ");
        hmLink.put("7-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TlNZcGx1dWxIU00");
        hmLink.put("7-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VUpZWllJU0lZaGs");
        hmLink.put("7-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RFhtSm9odXIxclU");
        hmLink.put("7-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QlN5R2s3SGJuWFk");
        hmLink.put("7-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NkM4MGF3SVRqcnc");
        hmLink.put("7-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Nlp1UDVIRjZjSUk");
        hmLink.put("8-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VEFZRVhtREdZdVE");
        hmLink.put("8-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aXJ0X2JkN0NBbEk");
        hmLink.put("8-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Vk95SE5pV21CT2s");
        hmLink.put("8-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QjNYTXhZSXd2WHM");
        hmLink.put("8-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4elBoN3R2bjZrT0U");
        hmLink.put("8-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WkswNnNvb0R2eDA");
        hmLink.put("8-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UFYtMjJkVzVmaDQ");
        hmLink.put("8-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y0stVi1FTlc0bnc");
        hmLink.put("8-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4X0NZQzdsaWpwWUk");
        hmLink.put("8-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4STU3QWZ3UERKSkk");
        hmLink.put("8-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VVNyV3pkazA4TWM");
        hmLink.put("8-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4b1lTX0V0NXBGdDA");
        hmLink.put("8-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OVdsWFNKWUtjNUk");
        hmLink.put("8-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4XzNlN1JJRXVBdkU");
        hmLink.put("8-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dFVfMFdPQWNpMkk");
        hmLink.put("8-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VnpNbW9od3JtOW8");
        hmLink.put("8-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OUR6aUQ2RG83VUk");
        hmLink.put("8-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MDNVZDRIMy12U3c");
        hmLink.put("8-019", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4elpnTlNzRGZCVjg");
        hmLink.put("8-020", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OS1lZVdBX2xQZFE");
        hmLink.put("8-021", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NFRxUzBlZmtVa2M");
        hmLink.put("8-022", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MGRkdFI1WEVDWnc");
        hmLink.put("8-023", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4V2NsSldLMTRCMTQ");
        hmLink.put("9-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MHFoeXBISjE1Qjg");
        hmLink.put("9-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RDFodWJWN0tUUlk");
        hmLink.put("9-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ajlFb090MzZWVUU");
        hmLink.put("9-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VHJFcHhOcjViT0U");
        hmLink.put("9-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4S0FCazJDaUpmQ3M");
        hmLink.put("9-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dWRVSmE4a0tkRWM");
        hmLink.put("9-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1diSjVqNFhoY1k");
        hmLink.put("9-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aE5ZdzZJUWhWUWM");
        hmLink.put("9-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TUxreTlINWVpYms");
        hmLink.put("9-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Zm9PMjVpZGd2NlE");
        hmLink.put("9-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZGl4aHFiS0lJWVk");
        hmLink.put("9-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UXd5eURyUmxjajg");
        hmLink.put("9-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MlN0TkYtSUFYeFE");
        hmLink.put("10-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzRjYWd4bVk0VFk");
        hmLink.put("10-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VVZTTDlrd05IRzA");
        hmLink.put("10-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dTUzczRTclZEekE");
        hmLink.put("10-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1lXeXhUNGhGRm8");
        hmLink.put("10-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QTREOVZzeUYzMFk");
        hmLink.put("10-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Si1TUXhqRUFmNjA");
        hmLink.put("10-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z0tDckZCRnN5V0k");
        hmLink.put("10-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bTBWdnZtb25jVUE");
        hmLink.put("10-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TFg3SEI5TDMzbmM");
        hmLink.put("10-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dk5Qc2ZuNDk0cjQ");
        hmLink.put("10-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4azN0T3ByZTRjXzA");
        hmLink.put("10-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4REFOdnRqS2Z4REE");
        hmLink.put("10-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WVJLVDlrTDhiUGs");
        hmLink.put("10-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NjRIN3gyc2VuNFk");
        hmLink.put("10-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YmZiM0RodnRnUGM");
        hmLink.put("10-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4b1FkejYwNl9vSHM");
        hmLink.put("10-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TlplaEVtRHJ4Rm8");
        hmLink.put("10-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QXVObGUyamRlNWs");
        hmLink.put("10-019", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MGRtZ1ZjYmYwNUE");
        hmLink.put("10-020", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TUNDZU1acHpteXc");
        hmLink.put("10-021", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VDN0TGpQNm1XV2s");
        hmLink.put("10-022", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Qy1ydUgyRzF0MkE");
        hmLink.put("10-023", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UTYtU3MzX0hTOFU");
        hmLink.put("10-024", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4XzRBX0FqR1dObWs");
        hmLink.put("10-025", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Rk02VUlWY0FWZW8");
        hmLink.put("10-026", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UEVRREc2b3FWclU");
        hmLink.put("10-027", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Yzd0eVh0d01pVUU");
        hmLink.put("11-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RzJPRC15cnJITmc");
        hmLink.put("11-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bWZxbjZJUnpjRm8");
        hmLink.put("11-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YW1kMmM1b1FWQkE");
        hmLink.put("11-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OUtCa0pITUtCSE0");
        hmLink.put("11-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R2ZyZDR0SFdBS2s");
        hmLink.put("11-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4emVWUFMwaThQY1U");
        hmLink.put("11-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VGE2U05rVkN1cUE");
        hmLink.put("11-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bGktMXZqSHI5ZkU");
        hmLink.put("11-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cVBkM0lvNVRHUkE");
        hmLink.put("11-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4M0NrcWpGOEFJdTQ");
        hmLink.put("11-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TFVLZ3JxYWNiTms");
        hmLink.put("11-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z3REazVOdFp0Qzg");
        hmLink.put("11-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TTJzVUIyTWR3WVU");
        hmLink.put("11-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SHhLX1NURE9MTFU");
        hmLink.put("11-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dnplMjZXZlpEVEk");
        hmLink.put("11-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UjdKaTJxbWpoeEU");
        hmLink.put("11-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y3ZNLW9jal9FMjg");
        hmLink.put("11-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4c1RZQ1ZSZU1ObE0");
        hmLink.put("11-019", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VS1nTHI5aEJDWW8");


        // Hash Map for Audio List Names------------------------------------------
        /*NOte: Importnt file names should never be the same
        * failing to maintain the discipline will not download the new file
        * Because all the files exists in the same folder*/
        hmFileName.put("1-01", "Aqeeda Al Wasitiya 01");
        hmFileName.put("1-02", "Aqeeda Al Wasitiya 02");
        hmFileName.put("1-03", "Aqeeda Al Wasitiya 03");
        hmFileName.put("1-04", "Aqeeda Al Wasitiya 04");
        hmFileName.put("1-05", "Aqeeda Al Wasitiya 05");
        hmFileName.put("1-06", "Aqeeda Al Wasitiya 06");
        hmFileName.put("1-07", "Aqeeda Al Wasitiya 07");
        hmFileName.put("1-08", "Aqeeda Al Wasitiya 08");
        hmFileName.put("1-09", "Aqeeda Al Wasitiya 09");
        hmFileName.put("1-010", "Aqeeda Al Wasitiya 10");
        hmFileName.put("1-011", "Aqeeda Al Wasitiya 11");
        hmFileName.put("1-012", "Aqeeda Al Wasitiya 12");
        hmFileName.put("1-013", "Aqeeda Al Wasitiya 13");
        hmFileName.put("1-014", "Aqeeda Al Wasitiya 14");
        hmFileName.put("1-015", "Aqeeda Al Wasitiya 15");
        hmFileName.put("1-016", "Aqeeda Al Wasitiya 16");
        hmFileName.put("1-017", "Aqeeda Al Wasitiya 17");
        hmFileName.put("1-018", "Aqeeda Al Wasitiya 18");
        hmFileName.put("2-01", "Gumrah firqh aur unki haqeeqatein 01");
        hmFileName.put("2-02", "Gumrah firqh aur unki haqeeqatein 02");
        hmFileName.put("2-03", "Gumrah firqh aur unki haqeeqatein 03");
        hmFileName.put("2-04", "Gumrah firqh aur unki haqeeqatein 04");
        hmFileName.put("2-05", "Gumrah firqh aur unki haqeeqatein 05");
        hmFileName.put("2-06", "Gumrah firqh aur unki haqeeqatein 06");
        hmFileName.put("2-07", "Gumrah firqh aur unki haqeeqatein 07");
        hmFileName.put("2-08", "Gumrah firqh aur unki haqeeqatein 08");
        hmFileName.put("2-09", "Gumrah firqh aur unki haqeeqatein 09");
        hmFileName.put("2-010", "Gumrah firqh aur unki haqeeqatein 10");
        hmFileName.put("2-011", "Gumrah firqh aur unki haqeeqatein 11");
        hmFileName.put("2-012", "Gumrah firqh aur unki haqeeqatein 12");
        hmFileName.put("2-013", "Gumrah firqh aur unki haqeeqatein 13");
        hmFileName.put("3-01", "hujjiyat e azmath e sahaba 01");
        hmFileName.put("3-02", "hujjiyat e azmath e sahaba 02");
        hmFileName.put("3-03", "hujjiyat e azmath e sahaba 03");
        hmFileName.put("3-04", "hujjiyat e azmath e sahaba 04");
        hmFileName.put("4-01", "Dawah Training 01");
        hmFileName.put("4-02", "Dawah Training 02");
        hmFileName.put("4-03", "Dawah Training 03");
        hmFileName.put("5-01", "Mundru Adipadaigal 01");
        hmFileName.put("5-02", "Mundru Adipadaigal 02");
        hmFileName.put("5-03", "Mundru Adipadaigal 03");
        hmFileName.put("5-04", "Mundru Adipadaigal 04");
        hmFileName.put("5-05", "Mundru Adipadaigal 05");
        hmFileName.put("5-06", "Mundru Adipadaigal 06");
        hmFileName.put("5-07", "Mundru Adipadaigal 07");
        hmFileName.put("5-08", "Mundru Adipadaigal 08");
        hmFileName.put("5-09", "Mundru Adipadaigal 09");
        hmFileName.put("6-01", "Sooniyam Unmai nilai");
        hmFileName.put("6-02", "Sihr Aqeeda thodarbu ulla vishiyam");
        hmFileName.put("6-03", "Sihr Hadeeth Evidenc");
        hmFileName.put("6-04", "Sihr Quran Evidence");
        hmFileName.put("7-01", "Taqwiyathul Eeman 01");
        hmFileName.put("7-02", "Taqwiyathul Eeman 02");
        hmFileName.put("7-03", "Taqwiyathul Eeman 03");
        hmFileName.put("7-04", "Taqwiyathul Eeman 04");
        hmFileName.put("7-05", "Taqwiyathul Eeman 05");
        hmFileName.put("7-06", "Taqwiyathul Eeman 06");
        hmFileName.put("8-01", "Ramadhan lecture,Day23 - Taubah");
        hmFileName.put("8-02", "Ramadhan lecture,Day21 - Shab e Qadr & DUA");
        hmFileName.put("8-03", "Ramadhan Duroos, Day29- A glance of Prophets in Islam - Part 3a");
        hmFileName.put("8-04", "Ramadhan Duroos, Day29- A glance of Prophets in Islam - Part 3b");
        hmFileName.put("8-05", "Ramadhan Duroos, Day27- Few Advice");
        hmFileName.put("8-06", "Ramadhan Duroos, Day28- A glance of Prophets in Islam - Part 2");
        hmFileName.put("8-07", "Ramadhan Duroos, Day25- Few Advices for the Women");
        hmFileName.put("8-08", "Ramadhan Duroos, Day24- A glance of Prophets in Islam");
        hmFileName.put("8-09", "Dont-s in the Qur-an, Day22- Badgumaani");
        hmFileName.put("8-010", "Dont-s in the Qur-an, Day20- Aabaparasti");
        hmFileName.put("8-011", "Dont-s in the Qur-an, Day19- Walidain k saath Badsulook");
        hmFileName.put("8-012", "Dont-s in the Qur-an, Day18- Fuzool Kharchi aur Israaf");
        hmFileName.put("8-013", "Dont-s in the Qur-an, Day10-RIBA(INTEREST)");
        hmFileName.put("8-014", "Dont-s in the Qur-an, Day17- Musalmaan mey aapasi Dushmani");
        hmFileName.put("8-015", "Dont-s in the Qur-an, Day12- SHARAAB & JUWWA");
        hmFileName.put("8-016", "Dont-s in the Qur-an, Day9-Qatal");
        hmFileName.put("8-017", "Dont-s in the Qur-an, Day7 - Biwi k Saath badsulooki");
        hmFileName.put("8-018", "Dont-s in the Qur-an, Day6 - Takabbur");
        hmFileName.put("8-019", "Dont-s in the Qur-an, Day5 - Haraam ka maal");
        hmFileName.put("8-020", "Dont-s in the Qur-an, Day3 - Shaitaan ki Ittiba");
        hmFileName.put("8-021", "Dont-s in the Qur-an, Day2 - SHIRK");
        hmFileName.put("8-022", "Dont-s in the Qur-an, Day4 - Disobedience to Allah and his Messenger");
        hmFileName.put("8-023", "Dont-s in the Qur-an, Day11-Bakheeli");
        hmFileName.put("9-01", "Ramadhan 1437 - Dars 17");
        hmFileName.put("9-02", "Ramadhan 1437 - Dars 14");
        hmFileName.put("9-03", "Ramadhan 1437 - Dars 13");
        hmFileName.put("9-04", "Ramadhan 1437 - Dars 15");
        hmFileName.put("9-05", "Ramadhan 1437 - Dars 16");
        hmFileName.put("9-06", "Ramadhan 1437 - Dars 8");
        hmFileName.put("9-07", "Ramadhan 1437 - Dars 6");
        hmFileName.put("9-08", "Ramadhan 1437 - Dars 5");
        hmFileName.put("9-09", "Ramadhan 1437 - Dars 7");
        hmFileName.put("9-010", "Ramadhan 1437 - Dars 4");
        hmFileName.put("9-011", "Ramadhan 1437 - Dars 3");
        hmFileName.put("9-012", "Ramadhan 1437 - Dars 1");
        hmFileName.put("9-013", "Ramadhan 1437 - Dars 2");
        hmFileName.put("10-01", "Ahkaamaat e Quran  Dars28  Juz 29");
        hmFileName.put("10-02", "Ahkaamaat e Quran  Dars27  Powerful Reminders");
        hmFileName.put("10-03", "Ahkaamaat e Quran  Dars25  Juz 25");
        hmFileName.put("10-04", "Ahkaamaat e Quran  Dars26  Surah Hujraat");
        hmFileName.put("10-05", "Ahkaamaat e Quran  Dars23  Juz 23");
        hmFileName.put("10-06", "Ahkaamaat e Quran  Dars24  Juz 24");
        hmFileName.put("10-07", "Ahkaamaat e Quran  Dars22  Juz 22");
        hmFileName.put("10-08", "Ahkaamaat e Quran  Dars21  Juz 21");
        hmFileName.put("10-09", "Ahkaamaat e Quran  Dars20  Juz 20");
        hmFileName.put("10-010", "Ahkaamaat e Quran  Dars19  Juz 19");
        hmFileName.put("10-011", "Ahkaamaat e Quran  Dars18  Juz 18");
        hmFileName.put("10-012", "Ahkaamaat e Quran  Dars16  Juz 16");
        hmFileName.put("10-013", "Ahkaamaat e Quran  Dars17  Juz 17");
        hmFileName.put("10-014", "Ahkaamaat e Quran  Dars14  Juz 14");
        hmFileName.put("10-015", "Ahkaamaat e Quran  Dars13  Juz 13");
        hmFileName.put("10-016", "Ahkaamaat e Quran  Dars11  Juz 11");
        hmFileName.put("10-017", "Ahkaamaat e Quran  Dars12  Juz 12");
        hmFileName.put("10-018", "Ahkaamaat e Quran  Dars10  Juz 10");
        hmFileName.put("10-019", "Ahkaamaat e Quran  Dars9  Juz 8");
        hmFileName.put("10-020", "Ahkaamaat e Quran  Dars8  Juz 8");
        hmFileName.put("10-021", "Ahkaamaat e Quran  Dars6  Juz 6");
        hmFileName.put("10-022", "Ahkaamaat e Quran  Dars7  Juz 7");
        hmFileName.put("10-023", "Ahkaamaat e Quran  Dars5  Juz 5");
        hmFileName.put("10-024", "Ahkaamaat e Quran  Dars4  Juz 4");
        hmFileName.put("10-025", "Ahkaamaat e Quran  Dars3  Juz 3");
        hmFileName.put("10-026", "Ahkaamaat e Quran  Dars1  Juz 1");
        hmFileName.put("10-027", "Ahkaamaat e Quran  Dars2  Juz 2");
        hmFileName.put("11-01", "Dars e Quran-Al Baqarah Vr 30-39 -Sh Anees ur Rahman");
        hmFileName.put("11-02", "Dars e Quran-Al Baqarah Vr 1-8 -Sh Anees ur Rahman");
        hmFileName.put("11-03", "Dars e Quran-Al Baqarah-Vr 97 to 102 -Sh Anees ur Rahman");
        hmFileName.put("11-04", "Dars e Quran-Al Baqarah-Vr 83 to 86 -Sh Anees ur Rahman");
        hmFileName.put("11-05", "Dars e Quran-Al Baqarah-Vr 75 to 82 -Sh Anees ur Rahman");
        hmFileName.put("11-06", "Dars e Quran-Al Baqarah-Vr 67 to 74 -Sh Anees ur Rahman");
        hmFileName.put("11-07", "Dars e Quran-Al Baqarah-Vr 47 to 54 -Sh Anees ur Rahman");
        hmFileName.put("11-08", "Dars e Quran-Al Baqarah-Vr 40 to 46 -Sh Anees ur Rahman");
        hmFileName.put("11-09", "Dars e Quran-4 -Sh Anees ur Rahman");
        hmFileName.put("11-010", "Dars e Quran-3 -Sh Anees ur Rahman");
        hmFileName.put("11-011", "Dars e Quran-2 -Sh Anees ur Rahman");
        hmFileName.put("11-012", "Dars e Quran-1 -Sh Anees ur Rahman");
        hmFileName.put("11-013", "dars e Quran -5 -Sh Anees ur Rahman");
        hmFileName.put("11-014", "Dars e Quran -7 -Sh Anees ur Rahman");
        hmFileName.put("11-015", "Dars e Quran -6 -Sh Anees ur Rahman");
        hmFileName.put("11-016", "Dar e Quran -8 -Sh Anees ur Rahman");
        hmFileName.put("11-017", "Dar e Quran -10 -Sh Anees ur Rahman");
        hmFileName.put("11-018", "Dars e Quran -11 -Sh Anees ur Rahman");
        hmFileName.put("11-019", "Dars e Quran-12 -Sh Anees ur Rahman");


        //Hash Map for Lan Id----------------------------------
        hmLanID.put("1-01", LidUrdKey);
        hmLanID.put("1-02", LidUrdKey);
        hmLanID.put("1-03", LidUrdKey);
        hmLanID.put("1-04", LidUrdKey);
        hmLanID.put("1-05", LidUrdKey);
        hmLanID.put("1-06", LidUrdKey);
        hmLanID.put("1-07", LidUrdKey);
        hmLanID.put("1-08", LidUrdKey);
        hmLanID.put("1-09", LidUrdKey);
        hmLanID.put("1-010", LidUrdKey);
        hmLanID.put("1-011", LidUrdKey);
        hmLanID.put("1-012", LidUrdKey);
        hmLanID.put("1-013", LidUrdKey);
        hmLanID.put("1-014", LidUrdKey);
        hmLanID.put("1-015", LidUrdKey);
        hmLanID.put("1-016", LidUrdKey);
        hmLanID.put("1-017", LidUrdKey);
        hmLanID.put("1-018", LidUrdKey);
        hmLanID.put("2-01", LidUrdKey);
        hmLanID.put("2-02", LidUrdKey);
        hmLanID.put("2-03", LidUrdKey);
        hmLanID.put("2-04", LidUrdKey);
        hmLanID.put("2-05", LidUrdKey);
        hmLanID.put("2-06", LidUrdKey);
        hmLanID.put("2-07", LidUrdKey);
        hmLanID.put("2-08", LidUrdKey);
        hmLanID.put("2-09", LidUrdKey);
        hmLanID.put("2-010", LidUrdKey);
        hmLanID.put("2-011", LidUrdKey);
        hmLanID.put("2-012", LidUrdKey);
        hmLanID.put("2-013", LidUrdKey);
        hmLanID.put("3-01", LidUrdKey);
        hmLanID.put("3-02", LidUrdKey);
        hmLanID.put("3-03", LidUrdKey);
        hmLanID.put("3-04", LidUrdKey);
        hmLanID.put("4-01", LidTamKey);
        hmLanID.put("4-02", LidTamKey);
        hmLanID.put("4-03", LidTamKey);
        hmLanID.put("5-01", LidTamKey);
        hmLanID.put("5-02", LidTamKey);
        hmLanID.put("5-03", LidTamKey);
        hmLanID.put("5-04", LidTamKey);
        hmLanID.put("5-05", LidTamKey);
        hmLanID.put("5-06", LidTamKey);
        hmLanID.put("5-07", LidTamKey);
        hmLanID.put("5-08", LidTamKey);
        hmLanID.put("5-09", LidTamKey);
        hmLanID.put("6-01", LidTamKey);
        hmLanID.put("6-02", LidTamKey);
        hmLanID.put("6-03", LidTamKey);
        hmLanID.put("6-04", LidTamKey);
        hmLanID.put("7-01", LidUrdKey);
        hmLanID.put("7-02", LidUrdKey);
        hmLanID.put("7-03", LidUrdKey);
        hmLanID.put("7-04", LidUrdKey);
        hmLanID.put("7-05", LidUrdKey);
        hmLanID.put("7-06", LidUrdKey);
        hmLanID.put("8-01", LidUrdKey);
        hmLanID.put("8-02", LidUrdKey);
        hmLanID.put("8-03", LidUrdKey);
        hmLanID.put("8-04", LidUrdKey);
        hmLanID.put("8-05", LidUrdKey);
        hmLanID.put("8-06", LidUrdKey);
        hmLanID.put("8-07", LidUrdKey);
        hmLanID.put("8-08", LidUrdKey);
        hmLanID.put("8-09", LidUrdKey);
        hmLanID.put("8-010", LidUrdKey);
        hmLanID.put("8-011", LidUrdKey);
        hmLanID.put("8-012", LidUrdKey);
        hmLanID.put("8-013", LidUrdKey);
        hmLanID.put("8-014", LidUrdKey);
        hmLanID.put("8-015", LidUrdKey);
        hmLanID.put("8-016", LidUrdKey);
        hmLanID.put("8-017", LidUrdKey);
        hmLanID.put("8-018", LidUrdKey);
        hmLanID.put("8-019", LidUrdKey);
        hmLanID.put("8-020", LidUrdKey);
        hmLanID.put("8-021", LidUrdKey);
        hmLanID.put("8-022", LidUrdKey);
        hmLanID.put("8-023", LidUrdKey);
        hmLanID.put("9-01", LidUrdKey);
        hmLanID.put("9-02", LidUrdKey);
        hmLanID.put("9-03", LidUrdKey);
        hmLanID.put("9-04", LidUrdKey);
        hmLanID.put("9-05", LidUrdKey);
        hmLanID.put("9-06", LidUrdKey);
        hmLanID.put("9-07", LidUrdKey);
        hmLanID.put("9-08", LidUrdKey);
        hmLanID.put("9-09", LidUrdKey);
        hmLanID.put("9-010", LidUrdKey);
        hmLanID.put("9-011", LidUrdKey);
        hmLanID.put("9-012", LidUrdKey);
        hmLanID.put("9-013", LidUrdKey);
        hmLanID.put("10-01", LidUrdKey);
        hmLanID.put("10-02", LidUrdKey);
        hmLanID.put("10-03", LidUrdKey);
        hmLanID.put("10-04", LidUrdKey);
        hmLanID.put("10-05", LidUrdKey);
        hmLanID.put("10-06", LidUrdKey);
        hmLanID.put("10-07", LidUrdKey);
        hmLanID.put("10-08", LidUrdKey);
        hmLanID.put("10-09", LidUrdKey);
        hmLanID.put("10-010", LidUrdKey);
        hmLanID.put("10-011", LidUrdKey);
        hmLanID.put("10-012", LidUrdKey);
        hmLanID.put("10-013", LidUrdKey);
        hmLanID.put("10-014", LidUrdKey);
        hmLanID.put("10-015", LidUrdKey);
        hmLanID.put("10-016", LidUrdKey);
        hmLanID.put("10-017", LidUrdKey);
        hmLanID.put("10-018", LidUrdKey);
        hmLanID.put("10-019", LidUrdKey);
        hmLanID.put("10-020", LidUrdKey);
        hmLanID.put("10-021", LidUrdKey);
        hmLanID.put("10-022", LidUrdKey);
        hmLanID.put("10-023", LidUrdKey);
        hmLanID.put("10-024", LidUrdKey);
        hmLanID.put("10-025", LidUrdKey);
        hmLanID.put("10-026", LidUrdKey);
        hmLanID.put("10-027", LidUrdKey);
        hmLanID.put("11-01", LidUrdKey);
        hmLanID.put("11-02", LidUrdKey);
        hmLanID.put("11-03", LidUrdKey);
        hmLanID.put("11-04", LidUrdKey);
        hmLanID.put("11-05", LidUrdKey);
        hmLanID.put("11-06", LidUrdKey);
        hmLanID.put("11-07", LidUrdKey);
        hmLanID.put("11-08", LidUrdKey);
        hmLanID.put("11-09", LidUrdKey);
        hmLanID.put("11-010", LidUrdKey);
        hmLanID.put("11-011", LidUrdKey);
        hmLanID.put("11-012", LidUrdKey);
        hmLanID.put("11-013", LidUrdKey);
        hmLanID.put("11-014", LidUrdKey);
        hmLanID.put("11-015", LidUrdKey);
        hmLanID.put("11-016", LidUrdKey);
        hmLanID.put("11-017", LidUrdKey);
        hmLanID.put("11-018", LidUrdKey);
        hmLanID.put("11-019", LidUrdKey);


        //Hash MAp for Spk Id--------------------------------------------------------
        hmSpkID.put("1-01", SpkIntRKNoorMadani);
        hmSpkID.put("1-02", SpkIntRKNoorMadani);
        hmSpkID.put("1-03", SpkIntRKNoorMadani);
        hmSpkID.put("1-04", SpkIntRKNoorMadani);
        hmSpkID.put("1-05", SpkIntRKNoorMadani);
        hmSpkID.put("1-06", SpkIntRKNoorMadani);
        hmSpkID.put("1-07", SpkIntRKNoorMadani);
        hmSpkID.put("1-08", SpkIntRKNoorMadani);
        hmSpkID.put("1-09", SpkIntRKNoorMadani);
        hmSpkID.put("1-010", SpkIntRKNoorMadani);
        hmSpkID.put("1-011", SpkIntRKNoorMadani);
        hmSpkID.put("1-012", SpkIntRKNoorMadani);
        hmSpkID.put("1-013", SpkIntRKNoorMadani);
        hmSpkID.put("1-014", SpkIntRKNoorMadani);
        hmSpkID.put("1-015", SpkIntRKNoorMadani);
        hmSpkID.put("1-016", SpkIntRKNoorMadani);
        hmSpkID.put("1-017", SpkIntRKNoorMadani);
        hmSpkID.put("1-018", SpkIntRKNoorMadani);
        hmSpkID.put("2-01", SpkIntAnsarudinMakki);
        hmSpkID.put("2-02", SpkIntAnsarudinMakki);
        hmSpkID.put("2-03", SpkIntAnsarudinMakki);
        hmSpkID.put("2-04", SpkIntAnsarudinMakki);
        hmSpkID.put("2-05", SpkIntAnsarudinMakki);
        hmSpkID.put("2-06", SpkIntAnsarudinMakki);
        hmSpkID.put("2-07", SpkIntAnsarudinMakki);
        hmSpkID.put("2-08", SpkIntAnsarudinMakki);
        hmSpkID.put("2-09", SpkIntAnsarudinMakki);
        hmSpkID.put("2-010", SpkIntAnsarudinMakki);
        hmSpkID.put("2-011", SpkIntAnsarudinMakki);
        hmSpkID.put("2-012", SpkIntAnsarudinMakki);
        hmSpkID.put("2-013", SpkIntAnsarudinMakki);
        hmSpkID.put("3-01", SpkIntRKNoorMadani);
        hmSpkID.put("3-02", SpkIntRKNoorMadani);
        hmSpkID.put("3-03", SpkIntRKNoorMadani);
        hmSpkID.put("3-04", SpkIntRKNoorMadani);
        hmSpkID.put("4-01", SpkIntMFAli);
        hmSpkID.put("4-02", SpkIntMFAli);
        hmSpkID.put("4-03", SpkIntMFAli);
        hmSpkID.put("5-01", SpkIntAbdulMajeed);
        hmSpkID.put("5-02", SpkIntAbdulMajeed);
        hmSpkID.put("5-03", SpkIntAbdulMajeed);
        hmSpkID.put("5-04", SpkIntAbdulMajeed);
        hmSpkID.put("5-05", SpkIntAbdulMajeed);
        hmSpkID.put("5-06", SpkIntAbdulMajeed);
        hmSpkID.put("5-07", SpkIntAbdulMajeed);
        hmSpkID.put("5-08", SpkIntAbdulMajeed);
        hmSpkID.put("5-09", SpkIntAbdulMajeed);
        hmSpkID.put("6-01", SpkIntMultiple);
        hmSpkID.put("6-02", SpkIntMultiple);
        hmSpkID.put("6-03", SpkIntMultiple);
        hmSpkID.put("6-04", SpkIntMultiple);
        hmSpkID.put("7-01", SpkIntAneesRahman);
        hmSpkID.put("7-02", SpkIntAneesRahman);
        hmSpkID.put("7-03", SpkIntAneesRahman);
        hmSpkID.put("7-04", SpkIntAneesRahman);
        hmSpkID.put("7-05", SpkIntAneesRahman);
        hmSpkID.put("7-06", SpkIntAneesRahman);
        hmSpkID.put("8-01", SpkIntAneesRahman);
        hmSpkID.put("8-02", SpkIntAneesRahman);
        hmSpkID.put("8-03", SpkIntAneesRahman);
        hmSpkID.put("8-04", SpkIntAneesRahman);
        hmSpkID.put("8-05", SpkIntAneesRahman);
        hmSpkID.put("8-06", SpkIntAneesRahman);
        hmSpkID.put("8-07", SpkIntAneesRahman);
        hmSpkID.put("8-08", SpkIntAneesRahman);
        hmSpkID.put("8-09", SpkIntAneesRahman);
        hmSpkID.put("8-010", SpkIntAneesRahman);
        hmSpkID.put("8-011", SpkIntAneesRahman);
        hmSpkID.put("8-012", SpkIntAneesRahman);
        hmSpkID.put("8-013", SpkIntAneesRahman);
        hmSpkID.put("8-014", SpkIntAneesRahman);
        hmSpkID.put("8-015", SpkIntAneesRahman);
        hmSpkID.put("8-016", SpkIntAneesRahman);
        hmSpkID.put("8-017", SpkIntAneesRahman);
        hmSpkID.put("8-018", SpkIntAneesRahman);
        hmSpkID.put("8-019", SpkIntAneesRahman);
        hmSpkID.put("8-020", SpkIntAneesRahman);
        hmSpkID.put("8-021", SpkIntAneesRahman);
        hmSpkID.put("8-022", SpkIntAneesRahman);
        hmSpkID.put("8-023", SpkIntAneesRahman);
        hmSpkID.put("9-01", SpkIntAneesRahman);
        hmSpkID.put("9-02", SpkIntAneesRahman);
        hmSpkID.put("9-03", SpkIntAneesRahman);
        hmSpkID.put("9-04", SpkIntAneesRahman);
        hmSpkID.put("9-05", SpkIntAneesRahman);
        hmSpkID.put("9-06", SpkIntAneesRahman);
        hmSpkID.put("9-07", SpkIntAneesRahman);
        hmSpkID.put("9-08", SpkIntAneesRahman);
        hmSpkID.put("9-09", SpkIntAneesRahman);
        hmSpkID.put("9-010", SpkIntAneesRahman);
        hmSpkID.put("9-011", SpkIntAneesRahman);
        hmSpkID.put("9-012", SpkIntAneesRahman);
        hmSpkID.put("9-013", SpkIntAneesRahman);
        hmSpkID.put("10-01", SpkIntAneesRahman);
        hmSpkID.put("10-02", SpkIntAneesRahman);
        hmSpkID.put("10-03", SpkIntAneesRahman);
        hmSpkID.put("10-04", SpkIntAneesRahman);
        hmSpkID.put("10-05", SpkIntAneesRahman);
        hmSpkID.put("10-06", SpkIntAneesRahman);
        hmSpkID.put("10-07", SpkIntAneesRahman);
        hmSpkID.put("10-08", SpkIntAneesRahman);
        hmSpkID.put("10-09", SpkIntAneesRahman);
        hmSpkID.put("10-010", SpkIntAneesRahman);
        hmSpkID.put("10-011", SpkIntAneesRahman);
        hmSpkID.put("10-012", SpkIntAneesRahman);
        hmSpkID.put("10-013", SpkIntAneesRahman);
        hmSpkID.put("10-014", SpkIntAneesRahman);
        hmSpkID.put("10-015", SpkIntAneesRahman);
        hmSpkID.put("10-016", SpkIntAneesRahman);
        hmSpkID.put("10-017", SpkIntAneesRahman);
        hmSpkID.put("10-018", SpkIntAneesRahman);
        hmSpkID.put("10-019", SpkIntAneesRahman);
        hmSpkID.put("10-020", SpkIntAneesRahman);
        hmSpkID.put("10-021", SpkIntAneesRahman);
        hmSpkID.put("10-022", SpkIntAneesRahman);
        hmSpkID.put("10-023", SpkIntAneesRahman);
        hmSpkID.put("10-024", SpkIntAneesRahman);
        hmSpkID.put("10-025", SpkIntAneesRahman);
        hmSpkID.put("10-026", SpkIntAneesRahman);
        hmSpkID.put("10-027", SpkIntAneesRahman);
        hmSpkID.put("11-01", SpkIntAneesRahman);
        hmSpkID.put("11-02", SpkIntAneesRahman);
        hmSpkID.put("11-03", SpkIntAneesRahman);
        hmSpkID.put("11-04", SpkIntAneesRahman);
        hmSpkID.put("11-05", SpkIntAneesRahman);
        hmSpkID.put("11-06", SpkIntAneesRahman);
        hmSpkID.put("11-07", SpkIntAneesRahman);
        hmSpkID.put("11-08", SpkIntAneesRahman);
        hmSpkID.put("11-09", SpkIntAneesRahman);
        hmSpkID.put("11-010", SpkIntAneesRahman);
        hmSpkID.put("11-011", SpkIntAneesRahman);
        hmSpkID.put("11-012", SpkIntAneesRahman);
        hmSpkID.put("11-013", SpkIntAneesRahman);
        hmSpkID.put("11-014", SpkIntAneesRahman);
        hmSpkID.put("11-015", SpkIntAneesRahman);
        hmSpkID.put("11-016", SpkIntAneesRahman);
        hmSpkID.put("11-017", SpkIntAneesRahman);
        hmSpkID.put("11-018", SpkIntAneesRahman);
        hmSpkID.put("11-019", SpkIntAneesRahman);


        //Main Insertion
        try {
            for (int i = 1; i < hmFRID.size(); i++) {
                String istring = Integer.toString(i);

                for (int j = 1; j <= hmFRID.size(); j++) {
                    String jstring = Integer.toString(j);
                    String keytolocate = istring + "-0" + jstring;
                    if (hmFRID.containsKey(keytolocate)) {
                        ContentValues cv = new ContentValues();
                        cv.put(AUD_FRID, hmFRID.get(keytolocate));
                        cv.put(AUD_DOWNLOAD_LINK, hmLink.get(keytolocate));
                        cv.put(AUD_FILE_NAME, hmFileName.get(keytolocate));
                        cv.put(AUD_LAN_ID, hmLanID.get(keytolocate));
                        cv.put(AUD_SPK_ID, hmSpkID.get(keytolocate));
                        db.insert(TABLE_AUDIO_LIST, null, cv);
                    }
                }
            }
            Log.e(TAG, "AUDIO List table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "SQLException error AUDIO Lister Insertion", e);
        }

        /*String query = "INSERT INTO " + TABLE_AUDIO_LIST + "(" + AUD_FRID + "," + AUD_DOWNLOAD_LINK + "," + AUD_FILE_NAME + "," + AUD_LAN_ID + "," + AUD_SPK_ID + ") VALUES " +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RWlXUkQxTmJDcXc','Aqeeda Al Wasitiya 01'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NWplWV9VR0xUR0U','Aqeeda Al Wasitiya 02'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ejBNdlZ3Q2hlRTg','Aqeeda Al Wasitiya 03'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cENwZ3pZT3lKVVU','Aqeeda Al Wasitiya 04'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z3JKUWc2VkpqQjg','Aqeeda Al Wasitiya 05'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T25QTnZXUE96ems','Aqeeda Al Wasitiya 06'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1puS0I2VWNKUVk','Aqeeda Al Wasitiya 16'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R29rcDF4dGJZR1U','Aqeeda Al Wasitiya 17'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NURjVVlldW5YTXM','Aqeeda Al Wasitiya 18'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")"; */
    }

//__________________________________________________________________________________________________________________________________________________________

    //Audio General table SQL Insert statement
    public void insertAudioGeneral() {
        String ArrayGeneralName[] = {
                "Role of Youths in Nurturing the Soceity -Sh Abdullah Hyderabadi",//1
                "Khudkhushi Ka Anjam -Sh Abdullah Hyderabadi",//2
                "12 Rabi al awal ki haqeqath-Sh Abdullah Hyderabadi",//3
                "Muqaam e Qatoon-Sh Abdullah Hyderabadi",//4
                "Neyk Aurath-Sh Abdullah Hyderabadi"//5
        };

        String ArrayGeneralLinks[] = {
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VlI4bmptWGhINlk",//1
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UExHU0h5cUIyNjQ",//2
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4N3YtdzMwa0w4VG8",//3
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eG4tZF8yZ1dKOXc",//4
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4akxhanRoY2pPZlk"//5
        };

        int ArrayGeneralLanID[] = {
                LidUrdKey,//1
                LidUrdKey,//2
                LidUrdKey,//3
                LidUrdKey,//4
                LidUrdKey//5
        };

        int ArrayGeneralSpkID[] = {
                SpkIntAbdullahHydrabadi,//1
                SpkIntAbdullahHydrabadi,//2
                SpkIntAbdullahHydrabadi,//3
                SpkIntAbdullahHydrabadi,//4
                SpkIntAbdullahHydrabadi//5
        };

        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < ArrayGeneralName.length; i++) {
                cv.put(AUD_GEN_NAME, ArrayGeneralName[i]);
                cv.put(AUD_GEN_LINK, ArrayGeneralLinks[i]);
                cv.put(AUD_GEN_LAN_ID, ArrayGeneralLanID[i]);
                cv.put(AUD_GEN_SPK_ID, ArrayGeneralSpkID[i]);
                db.insert(TABLE_AUDIO_GENERAL, null, cv);
            }
            Log.e(TAG, "Audio General Lectures inserted");
        } catch (SQLException e) {
            Log.e(TAG, "Sql Exception General Lectures Insertion\n" + e);
        }

    }

//__________________________________________________________________________________________________________________________________________________________

    //PDF table SQL Insert statement

    // please note: The file name inserted in the table should exactly be
    // same as the file name in the Cloud drive to avoid confusion
    //I dont know why I wrote the above comment I have to test changing names
    public void insertPdfTable() {
        String query = "INSERT INTO " + TABLE_PDF + "(" + PDF_NAME + ", " + PDF_LAN_ID + "," + PDF_LINK + ") VALUES " +
                "('33 Lessons -Shayk ibn Baz'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cXlmREw3Q1JobjQ')," +
                "('40 Hadith Nawawi'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bngwVVRTclVHNG8')," +
                "('Al Fawaid -ibn Qayyim'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZUFtTzVkLTFiTGM')," +
                "('Authentic Remembrance After Salah'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VGoxNWFyY1hYa28')," +
                "('Devils Deception'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TVphdVE3RU1aR0k')," +
                "('Fortress Of Muslim'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OGtWVWZzU2gzc1U')," +
                "('How to Perform the Rituals of Hajj -ibn Uthaymeen'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dHhaLTJWT1p3QjQ')," +
                "('Kitab ut Tawheed'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RTlWaHJMdVp1MTQ')," +
                "('Methodlogy of prophet call to Allah'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WVAyanQ0bnpyWkk')," +
                "('Prophet Prayer -al Albani'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ME96RDhnU3hrTkk')," +
                "('Riyad us Saliheen'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SnpGWnlobFkzZ3c')," +
                "('Sharh Al Aqeeda al Wasitiya -ibn Taymiyyah'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aDlhQVd1NmFXVDQ')," +
                "('Stories of Prophet'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OXBPOTZBeXg5T1k')," +
                "('The Condition and Pillars of Salat -Imam Abdul Wahab'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4V3RxUlI4d21Lcms')," +
                "('The Etiquettes of Marriage'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4LVE5WjU0YUwtTjA')," +
                "('The Ideal Muslim'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4U3dXSlpOeExwWXM')," +
                "('The Ill Effects of Sin -ibn Uthaymeen'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4U0prdm94OW1POVU')," +
                "('The Islamic Awakening -ibn Uthaymeen'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4XzlhYVNmdGg5bGM')," +
                "('The Sealed Nectar'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NVJJTUx4eVo1TFU')," +
                "('The Three Fundamental Principle -Imam Abdul Wahab'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WFE4d1JXcEl2X1E')," +
                "('Youth Problems -ibn Uthaymeen'," + LidEngKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZkJCSFRIVmtFbFU')," +
                "('Answering those who altered the religion of jesus christ -ibn Taymiyyah'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bElSbjN6V1Awam8')," +
                "('Quran and Moder Science Compatible or incompatible'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bGw2WTIyVU1jVm8')," +
                "('Scientific Truth in the Quran'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4enZJRVBjX2FtMWM')," +
                "('The Islamic View of Jesus -ibn Kathir'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bUVacnNyVEVuVmc')," +
                "('True Message of Jesus -Dr Bilal Philips'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NUhlT1JUVkYwcEU')";

       /*EXAMPLE on how last two lines should be for the above SQL Query
"('The Islamic View of Jesus -ibn Kathir'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bUVacnNyVEVuVmc')," +
                "('True Message of Jesus -Dr Bilal Philips'," + LidNonMKey + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NUhlT1JUVkYwcEU')";
        */

        //main insertion is below
        try {
            db.execSQL(query);
            Log.e(TAG, "PDF table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "SQLException error pdf Insertion", e);
        }
    }

    //VIDEO Name table SQL Insert statement
    public void insertVideoNameTable() {
        //this query is the video Titles
        String query = "INSERT INTO " + TABLE_VIDEO_NAME + "(" + VNID_NAME + ", " + VNID_LAN_ID + ", " + VNID_SPK_ID + ", " + VNID_SPK_NAME + ") VALUES " +
            /*1*/ "('Taqwiyyatul Eemaan'," + LidUrdKey + "," + SpkIntAneesRahman + "," + "(SELECT " + SPK_NAMES + " FROM " + TABLE_SPEAKER + " WHERE _id=" + SpkIntAneesRahman + "))," +
            /*2*/ "('Explanation of Kitab ut Tauheed'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + "," + "(SELECT " + SPK_NAMES + " FROM " + TABLE_SPEAKER + " WHERE _id=" + SpkIntAbdusSalaamMadani + "))," +
            /*3*/ "('Mundru adipadaigal'," + LidTamKey + "," + SpkIntAbdulMajeed + "," + "(SELECT " + SPK_NAMES + " FROM " + TABLE_SPEAKER + " WHERE _id=" + SpkIntAbdulMajeed + "))," +
            /*4*/ "('Aqeedah al Wasitiyyah'," + LidUrdKey + "," + SpkIntRKNoorMadani + "," + "(SELECT " + SPK_NAMES + " FROM " + TABLE_SPEAKER + " WHERE _id=" + SpkIntRKNoorMadani + "))";
        //main insertion is below
        try {
            db.execSQL(query);
            Log.e(TAG, "Video NAme table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "SQLException error pdf Insertion", e);
        }
    }


    //VIDEO table SQL Insert statement
    public void insertVideoTable() {
        //Before entering the video links insert the video topic in the VIDEONAME table which is above this and
        //then call the _id of the videoNameTable as a foreign key in this below table
        String query = "INSERT INTO " + TABLE_VIDEO + "(" + VID_FRID + "," + VID_DOWNLOAD_LINK + "," + VID_NAME + "," + VID_LAN_ID + "," + VID_SPK_ID + ") VALUES " +
                "(1,'https://youtu.be/UPYWJZusYtE','Taqwiyyatul Eemaan 01'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/tDKeKTaHDoc','Taqwiyyatul Eemaan 02'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/bhomsoWjauE','Taqwiyyatul Eemaan 03'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/5-iOVyvO17Q','Taqwiyyatul Eemaan 04'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/pUHtCpa9qgM','Taqwiyyatul Eemaan 05'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/YDsom9yHe_Y','Taqwiyyatul Eemaan 07'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(1,'https://youtu.be/dO_U7buFnhM','Taqwiyyatul Eemaan 08'," + LidUrdKey + "," + SpkIntAneesRahman + ")," +
                "(2,'https://www.youtube.com/watch?v=CjzOSIKJA-E&index=2&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 01'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=D4ckSnQu3sQ&index=1&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 02'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=E9v4X6UUU5A&index=3&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 03'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=IJERgcpAQTE&index=4&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 04'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=SbXX9HYtFBY&index=5&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 05'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=6eTdHL_ksSo&index=6&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 06'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=FwGXfAMIQlI&index=7&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 07'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=DfXuxs59n74&index=8&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 08'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=hGksaKM3BE4&index=9&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 09'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=F2xKActHgyA&index=11&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 10'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=_tOJ_rTTORA&index=10&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 11'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=XgGPAWITLIY&index=12&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 12'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=2vA6wS-2hMs&index=13&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 13'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=pHs2nUUKODA&index=14&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 14'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=MqDKwHNZgAc&index=15&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 15'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=KdhffHNs_w8&index=16&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 16'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=xPMuNhQCDmU&index=17&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 17'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(2,'https://www.youtube.com/watch?v=Icyxyyn5CI8&index=18&list=PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n','Kitaab ut Tauheed 18'," + LidEngKey + "," + SpkIntAbdusSalaamMadani + ")," +
                "(3,'https://youtu.be/ir3fNZbwU0I?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 01'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/YZihOTOe1BU?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 02'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/KSbc8sSH5K8?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 03'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/UpM1p-MJ-z4?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 04'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/Iq1L04EqWqo?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 05'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/tH7z8f__9DA?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 06'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/axhrDFLvUpA?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 07'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/rAnd0Bh1kRo?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 08'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(3,'https://youtu.be/li9NMwCje1Y?list=PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G','Mundru adipadaigal 09'," + LidTamKey + "," + SpkIntAbdulMajeed + ")," +
                "(4,'https://www.youtube.com/watch?v=26Plj2fDv30&index=17&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 01'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=wI03ufAsRSc&index=16&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 02'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=uEHPY-x1B2A&index=15&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 03'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=zi2FnH7oYEw&index=14&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 04'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=zplKjHIxjFo&index=13&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 05'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=srGuGfSNcLM&index=12&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 06'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=E0lx5myDwqM&index=11&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 07'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=epK4LZc-vho&index=10&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 08'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=1483UzjtGl0&index=9&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 09'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=P6WrZkivrRs&index=8&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 10'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=jVL3X5UzeSE&index=7&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 11'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=qPjZEPIElaU&index=6&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 12'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=muGoix8ap48&index=5&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 13'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=_7MXusjC6t8&index=4&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 14'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=fQq2XaxjsxU&index=3&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 15'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=ZRvLX4pKiPw&index=2&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 16'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=i3W6-tN0Crc&index=1&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 17'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(4,'https://www.youtube.com/watch?v=LRyP6cu23Kk&index=18&list=PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg','Aqeeda al Wasitiyyah 18'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")";
        //main insertion is below
        try {
            db.execSQL(query);
            Log.e(TAG, "VIDEO table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "SQLException error Video Insertion", e);
        }
    }

    //_______INSERT ENDS_________INSERT ENDS_________INSERT ENDS_________INSERT ENDS_____

    /*the below method is used to check whether the values are inserted in the table @ FirstTime and Update
    this can be used for all tables
    simply pass the tableName as 1st argument and All_KEYS (of appropirate table) array as 2nd argument*/
    public int getRowCount(String tableName, String[] Rowkeys) {
        Cursor c = db.query(true, tableName, Rowkeys, null, null, null, null, null, null);
        int count = c.getCount();
        return count;
    }

    public Cursor getAllRows(String tableName, String[] allKeysName) {
        String where = null;
        Cursor c = db.query(true, TABLE_LANGUAGE, ALL_LANGUAGE_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //This method is to get the row count of a table with some selected specfic row with value
    public int getSpecficRowCount(String tableName, String rowName, String rowValue) {
        String countQuery = "SELECT * FROM " + tableName + " WHERE " + rowName + " = '" + rowValue + "';";
        Cursor c = db.rawQuery(countQuery, null);
        int count = c.getCount();
        return count;
    }

    //This Method is used to get a value for a given value
    public Cursor getSpecficRow(String tablename, String rowTobeSelctd, String whereRowName, String rowValue) {
        String query = "SELECT " + rowTobeSelctd + " FROM " + tablename + " WHERE " + whereRowName + " = '" + rowValue + "';";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getAllRowsbyLang(String tablename, String[] allKeys, int lanid) {
        String where = VID_LAN_ID + " = '" + lanid + "'";
        Cursor c = db.query(true, tablename, allKeys, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor videoCategorySelecting(String lanid) {
        String query = "SELECT * FROM " + TABLE_VIDEO_NAME + " WHERE _lanid=" + lanid;
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public Cursor videoSelecting(String frid) {//frid means foreign id as a parameter
        String query = "SELECT * FROM " + TABLE_VIDEO + " WHERE _frid=" + frid;
        Cursor c = db.rawQuery(query, null);
        return c;
    }


    // Get a specific row (by rowId)
    public Cursor getRowofPdf(int rowId) {
        String where = PDF_ID + "=" + rowId;
        Cursor c = db.query(true, TABLE_PDF, ALL_PDF_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getPdfAssetsNames(int lanid) {
        String where = PDF_LAN_ID + "=" + lanid;
        Cursor c = db.query(true, TABLE_PDF, ALL_PDF_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectSpeakerNamesforClassOrGeneralList(String lanid, String ifClassorIfGeneral) {

        String tableName = TABLE_SPEAKER;

        String[] selection = {SPK_ID, SPK_NAMES};

        String lanrowName;
        switch (lanid) {
            case "1":
                lanrowName = SPK_IF_ENG;
                break;
            case "2":
                lanrowName = SPK_IF_TAMIL;
                break;
            case "3":
                lanrowName = SPK_IF_URDU;
                break;
            default:
                lanrowName = SPK_IF_URDU; //default
                break;
        }
        String where = lanrowName + "=1 AND " + ifClassorIfGeneral + "=1";

        Cursor c = db.query(true, tableName, selection, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectTopicNamesforClassTopics(String lanid, String spkid) {

        String tableName = TABLE_AUDIO_TOPIC;

        String[] selection = {AUD_Topic_ID, AUD_Topic_NAME};

        String where = AUD_Topic_LAN_ID + "=" + lanid + " AND " + AUD_Topic_SPK_ID + "=" + spkid;

        Cursor c = db.query(true, tableName, selection, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectAudioListforClassTopics(String classtopicsFrKey) {

        String tablename = TABLE_AUDIO_LIST;
        String[] selection = {AUD_DOWNLOAD_LINK, AUD_FILE_NAME};

        String where = AUD_FRID + "=" + classtopicsFrKey;

        Cursor c = db.query(true, tablename, selection, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectAudioListGeneralLectures(String spkid) {

        String tablename = TABLE_AUDIO_GENERAL;
        String[] selection = {AUD_GEN_LINK, AUD_GEN_NAME};

        String where = AUD_GEN_SPK_ID + "=" + spkid;

        Cursor c = db.query(true, tablename, selection, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    //_______Seperate Class but Main for DataBase Adapter class____________________________________
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DataBaseAdapterC DAC; //object for DataBaseAdapterC class

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(CREATE_LANGUAGE_SQL);
            _db.execSQL(CREATE_SPEAKER_SQL);
            _db.execSQL(CREATE_AUDIO_TOPIC_SQL);
            _db.execSQL(CREATE_AUDIO_LIST_SQL);
            _db.execSQL(CREATE_AUDIO_GENERAL_SQL);
            _db.execSQL(CREATE_VIDEO_NAME_SQL);
            _db.execSQL(CREATE_VIDEO_SQL);
            _db.execSQL(CREATE_PDF_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data !");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_LANGUAGE);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKER);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIO_TOPIC);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIO_LIST);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIO_GENERAL);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO_NAME);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_PDF);


            // Recreate new database:
            onCreate(_db);
        }
    }
}

