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
    public static final int DATABASE_VERSION = 4;
    //should be updated every time when application is updated
    //Last version i.e version 3 has DataBase vr = 3(13-01-2017)

    public static final String TABLE_LANGUAGE = "languagetable";
    public static final String TABLE_SPEAKER = "speakertable";
    public static final String TABLE_AUDIO_TOPIC = "audiotopictable";
    public static final String TABLE_AUDIO_LIST = "audiolisttable";
    public static final String TABLE_AUDIO_GENERAL = "audiogeneraltable";
    public static final String TABLE_PDF = "pdftable";

    public static final String TABLE_VIDEO_TOPIC = "videotopictable";


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


    //Video Topics ClassTopics Table Row Names
    public static final String VIDEO_TOPIC_ID = "_id";
    public static final String VIDEO_TOPIC_PLAYLIST_LINK = "link";
    public static final String VIDEO_TOPIC_LAN_ID = "_lanid";
    public static final String VIDEO_TOPIC_SPK_ID = "_spkid";

    /*VideoName Table Row Names
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
    public static final String VID_SPK_ID = "_spkid";*/

    //Pdf Table Row Names
    public static final String PDF_ID = "_id";
    public static final String PDF_NAME = "name";
    public static final String PDF_CATEGORY = "category";
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
    public static final String ALL_PDF_KEYS[] = {PDF_ID, PDF_NAME, PDF_CATEGORY, PDF_LINK};
    public static final String ALL_VIDEO_TOPIC_KEYS[] = {VIDEO_TOPIC_ID, VIDEO_TOPIC_PLAYLIST_LINK, VIDEO_TOPIC_LAN_ID, VIDEO_TOPIC_SPK_ID};

    //*************CREATE STATEMENTS**********************************************************

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
    private static final String CREATE_VIDEO_TOPIC_NAME_SQL =
            "CREATE TABLE " + TABLE_VIDEO_TOPIC
                    + " (" + VIDEO_TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + VIDEO_TOPIC_PLAYLIST_LINK + " TEXT, "
                    + VIDEO_TOPIC_LAN_ID + " INTEGER, "
                    + VIDEO_TOPIC_SPK_ID + " INTEGER "
                    + ");";

    //Create SQL statement for PDF Table
    private static final String CREATE_PDF_SQL =
            "CREATE TABLE " + TABLE_PDF
                    + " (" + PDF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PDF_NAME + " TEXT, "
                    + PDF_CATEGORY + " TEXT, "
                    + PDF_LINK + " TEXT "
                    + ");";


    //_________CREATE STATEMENTS______ENDS_______ENDS__________________


    //********** INSERT STATEMENTS **** STARTS **** STARTS **** STARTS ****
    //************************ I N S E R T **************************************************************************

    String allLanguagesArray[] = { //add new languages in this Array and update the DB version
            "english",
            "tamil",
            "urdu",
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

    public static final String LidStringEnglish = Integer.toString(LidEngKey);
    public static final String LidStringTamil = Integer.toString(LidTamKey);
    public static final String LidStringUrdu = Integer.toString(LidUrdKey);


//___________________________________________________________________________________________________

    //* * * * * * * * Speaker Table SQL Insert statement * * * * * * * * * *
    //How to Add New Speakers
    /*
    // 1) Add the New spkaker name in the allSpeakerArray[]
    //
    // 2) Based on the Language and Type of Audios
          give true and false value to the Arrays respectively
    */
    public void insertSpeakerTable() {
        String allSpeakersArray[] = {
                "Sh Abdul Majeed Mahzari",//1
                "Sh Abdus Salaam Madani",//2
                "Sh Aneesur Rahman Madani",//3
                "Sh Ansarudeen Makki",//4
                "Br M.F Ali",//5
                "Sh Dr R.K.Noor Madani",//6
                "Multiple Speakers",//7
                "Sh Abdullah Hydrabadi",//8
                "Mufti Umar Sherif",//9
                "Sh Owais Omeri",//10
                "Sh Mujeeb ur Rahman"//11
        };
        String ifClassarray[] = {"1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0"};
        String ifGenerarray[] = {"0", "0", "1", "0", "0", "1", "0", "1", "1", "0", "0"};
        String ifEngliarray[] = {"0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
        String ifTamilarray[] = {"1", "0", "0", "0", "1", "0", "1", "0", "1", "0", "0"};
        String ifUrduuarray[] = {"0", "0", "1", "1", "0", "1", "0", "1", "0", "0", "0"};
        //Spk Integer           //1   /2   /3   /4   /5   /6   /7   /8   /9  //10 //11
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
    public static final int SpkIntMuftiUmar = 9;
    public static final int SpkIntShOwaisOmeri = 10;
    public static final int SpkIntShMujeeburRahman = 11;
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
                "Dars e Hadeeth",//12
                "Usool us Salasa",//13
                "Explanation of Kitab at Tawheed",//14
                "Eemaniyath",//15
                "Jumma Kuthba Sh Anees Rahman"//16
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
                LidUrdKey,//12
                LidUrdKey,//13
                LidEngKey,//14
                LidUrdKey,//15
                LidUrdKey//16
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
                SpkIntAneesRahman,//12
                SpkIntAnsarudinMakki,//13
                SpkIntAbdusSalaamMadani,//14
                SpkIntRKNoorMadani,//15
                SpkIntAneesRahman//16
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
        hmFRID.put("12-01", "12");
        hmFRID.put("12-02", "12");
        hmFRID.put("12-03", "12");
        hmFRID.put("12-04", "12");
        hmFRID.put("12-05", "12");
        hmFRID.put("12-06", "12");
        hmFRID.put("12-07", "12");
        hmFRID.put("12-08", "12");
        hmFRID.put("12-09", "12");
        hmFRID.put("12-010", "12");
        hmFRID.put("12-011", "12");
        hmFRID.put("12-012", "12");
        hmFRID.put("12-013", "12");
        hmFRID.put("12-014", "12");
        hmFRID.put("12-015", "12");
        hmFRID.put("12-016", "12");
        hmFRID.put("12-017", "12");
        hmFRID.put("12-018", "12");
        hmFRID.put("12-019", "12");
        hmFRID.put("12-020", "12");
        hmFRID.put("12-021", "12");
        hmFRID.put("12-022", "12");
        hmFRID.put("12-023", "12");
        hmFRID.put("12-024", "12");
        hmFRID.put("12-025", "12");
        hmFRID.put("12-026", "12");
        hmFRID.put("12-027", "12");
        hmFRID.put("12-028", "12");
        hmFRID.put("12-029", "12");
        hmFRID.put("12-030", "12");
        hmFRID.put("12-031", "12");
        hmFRID.put("12-032", "12");
        //13
        hmFRID.put("13-01", "13");
        hmFRID.put("13-02", "13");
        hmFRID.put("13-03", "13");
        hmFRID.put("13-04", "13");
        hmFRID.put("13-05", "13");
        hmFRID.put("13-06", "13");
        hmFRID.put("13-07", "13");
        hmFRID.put("13-08", "13");
        //14
        hmFRID.put("14-01", "14");
        hmFRID.put("14-02", "14");
        hmFRID.put("14-03", "14");
        hmFRID.put("14-04", "14");
        hmFRID.put("14-05", "14");
        hmFRID.put("14-06", "14");
        hmFRID.put("14-07", "14");
        hmFRID.put("14-08", "14");
        hmFRID.put("14-09", "14");
        hmFRID.put("14-010", "14");
        hmFRID.put("14-011", "14");
        hmFRID.put("14-012", "14");
        hmFRID.put("14-013", "14");
        hmFRID.put("14-014", "14");
        hmFRID.put("14-015", "14");
        hmFRID.put("14-016", "14");
        hmFRID.put("14-017", "14");
        hmFRID.put("14-018", "14");
        //15
        hmFRID.put("15-01", "15");
        hmFRID.put("15-02", "15");
        hmFRID.put("15-03", "15");
        hmFRID.put("15-04", "15");
        hmFRID.put("15-05", "15");
        hmFRID.put("15-06", "15");
        hmFRID.put("15-07", "15");
        hmFRID.put("15-08", "15");
        hmFRID.put("15-09", "15");
        hmFRID.put("15-010", "15");
        //16
        hmFRID.put("16-01", "16");
        hmFRID.put("16-02", "16");
        hmFRID.put("16-03", "16");
        hmFRID.put("16-04", "16");
        hmFRID.put("16-05", "16");
        hmFRID.put("16-06", "16");
        hmFRID.put("16-07", "16");
        hmFRID.put("16-08", "16");
        hmFRID.put("16-09", "16");
        hmFRID.put("16-010", "16");
        hmFRID.put("16-011", "16");
        hmFRID.put("16-012", "16");
        hmFRID.put("16-013", "16");
        hmFRID.put("16-014", "16");
        hmFRID.put("16-015", "16");
        hmFRID.put("16-016", "16");
        hmFRID.put("16-017", "16");
        hmFRID.put("16-018", "16");
        hmFRID.put("16-019", "16");
        hmFRID.put("16-020", "16");
        hmFRID.put("16-021", "16");
        hmFRID.put("16-022", "16");
        hmFRID.put("16-023", "16");
        hmFRID.put("16-024", "16");
        hmFRID.put("16-025", "16");
        hmFRID.put("16-026", "16");
        hmFRID.put("16-027", "16");
        hmFRID.put("16-028", "16");
        hmFRID.put("16-029", "16");
        hmFRID.put("16-030", "16");
        hmFRID.put("16-031", "16");
        hmFRID.put("16-032", "16");
        hmFRID.put("16-033", "16");
        hmFRID.put("16-034", "16");
        hmFRID.put("16-035", "16");
        hmFRID.put("16-036", "16");
        hmFRID.put("16-037", "16");
        hmFRID.put("16-038", "16");
        hmFRID.put("16-039", "16");
        hmFRID.put("16-040", "16");
        hmFRID.put("16-041", "16");
        hmFRID.put("16-042", "16");
        hmFRID.put("16-043", "16");
        hmFRID.put("16-044", "16");
        hmFRID.put("16-045", "16");
        hmFRID.put("16-046", "16");
        hmFRID.put("16-047", "16");
        hmFRID.put("16-048", "16");
        hmFRID.put("16-049", "16");
        hmFRID.put("16-050", "16");
        hmFRID.put("16-051", "16");
        hmFRID.put("16-052", "16");
        hmFRID.put("16-053", "16");
        hmFRID.put("16-054", "16");
        hmFRID.put("16-055", "16");
        hmFRID.put("16-056", "16");


        //------------------------------------------------------------------------------------------------------------
        //--------------------------/Hash Map for Links-------------------------------------------------------------------------------------------------------------
        //Hash Map for Links------------------------------------------------------------------------------
        //1
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
        //2
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
        //3
        hmLink.put("3-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4XzhhcUI0a3otd1E");
        hmLink.put("3-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4U2FFTy12b2h2eE0");
        hmLink.put("3-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SkJpanQwZ3pJUE0");
        hmLink.put("3-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZFZ6UDhOQ2dBQzg");
        //4
        hmLink.put("4-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NTVaZ3dNZGJGR1k");
        hmLink.put("4-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4clROQzdHcEtLQ0k");
        hmLink.put("4-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZzlGNlVyQ3h4Vmc");
        //5
        hmLink.put("5-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YnRXUGdyYzJFSUE");
        hmLink.put("5-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T3lKTkV5OEs2NXc");
        hmLink.put("5-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bGVnWGY0UDlnakU");
        hmLink.put("5-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cFVja3VqRVVNWUk");
        hmLink.put("5-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bmlIMnRvcGVtcXM");
        hmLink.put("5-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ekNSNzBfSTJuZkk");
        hmLink.put("5-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzhiODllVmkyTkU");
        hmLink.put("5-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4LTBTUXZjdnBLcG8");
        hmLink.put("5-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Q3JRM2Q4c1dVbGc");
        //6
        hmLink.put("6-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dnV3TE9IRmQxdjg");
        hmLink.put("6-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VjVIZEN3c1pFV00");
        hmLink.put("6-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UlFwUHhuai1oMGs");
        hmLink.put("6-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MXRkeFQ4X3plLTQ");
        //7
        hmLink.put("7-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TlNZcGx1dWxIU00");
        hmLink.put("7-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VUpZWllJU0lZaGs");
        hmLink.put("7-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RFhtSm9odXIxclU");
        hmLink.put("7-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QlN5R2s3SGJuWFk");
        hmLink.put("7-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NkM4MGF3SVRqcnc");
        hmLink.put("7-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Nlp1UDVIRjZjSUk");
        //8
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
        //9
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
        //10
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
        //11
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
        //12
        hmLink.put("12-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZmJzUkFxYXU5TWc");
        hmLink.put("12-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WGJCa2JGRTQzWlk");
        hmLink.put("12-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZEVCVjVYSkVtZGs");
        hmLink.put("12-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eEFfRGhoRWxuTU0");
        hmLink.put("12-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4d1p5VjJvU2k0MDA");
        hmLink.put("12-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4N0ZjaWt3ZWd4MFE");
        hmLink.put("12-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SlZuX1NQMUFFREk");
        hmLink.put("12-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NGxyM1B3WFQ2XzA");
        hmLink.put("12-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dG1xWXhwSnExSUk");
        hmLink.put("12-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dWJMSmZReXdzejA");
        hmLink.put("12-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4STc1YjZ4RFpvdU0");
        hmLink.put("12-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WlUxY1ByTmtlOFk");
        hmLink.put("12-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4blNZemh2VVhwNDA");
        hmLink.put("12-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WUpuamVRLWZCdGs");
        hmLink.put("12-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NUtoV0Z0U1JyYnc");
        hmLink.put("12-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QVVVcGtqN2tUR2M");
        hmLink.put("12-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Rkk1TlF0ZHVuaVE");
        hmLink.put("12-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4b3pweWI0Rk14VFE");
        hmLink.put("12-019", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eFRPQ2owOENlV0U");
        hmLink.put("12-020", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MHlPX29uZF9Ham8");
        hmLink.put("12-021", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4c0V2U21DQ01SYWs");
        hmLink.put("12-022", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dmExYlBLbzUwOU0");
        hmLink.put("12-023", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TGFXczdwbHA0Nm8");
        hmLink.put("12-024", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4blNaQmpVeXN0VzQ");
        hmLink.put("12-025", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bEQwclc2b0lLekE");
        hmLink.put("12-026", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MDc2czlvUkZuVnM");
        hmLink.put("12-027", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NU5CTzJLdkRTUDQ");
        hmLink.put("12-028", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YlN3ZlRmYnRfYzQ");
        hmLink.put("12-029", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4d2hwTm04Q2h6d1E");
        hmLink.put("12-030", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MEVma3J5ZDVxbVU");
        hmLink.put("12-031", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4X2NIUHZCTUlIMlE");
        hmLink.put("12-032", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dGp1NmlLSzRhSFE");
        //13
        hmLink.put("13-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dnNEd2dYNE5DalE");
        hmLink.put("13-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZnA0djg5VmlCX28");
        hmLink.put("13-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WWtMQzlEb2NfbVU");
        hmLink.put("13-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RDM0WndEWFJ0WlE");
        hmLink.put("13-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WFpPckU4aHZ6NkE");
        hmLink.put("13-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cmdsUktQVXprLUE");
        hmLink.put("13-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SXlkVGFjYW9UTUU");
        hmLink.put("13-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SkI5Qzkwc3NRdHc");
        //14
        hmLink.put("14-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VnUxUVBaY3pSNUU");
        hmLink.put("14-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TFlmT09IcS1sdjA");
        hmLink.put("14-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Q3A2UzBxdG10eWM");
        hmLink.put("14-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NlRvRFQ0UDZzRUU");
        hmLink.put("14-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aDFhLUlfNWlZU00");
        hmLink.put("14-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YS1YZjNBVnRhcGc");
        hmLink.put("14-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R3VNbVFLUVpYVUU");
        hmLink.put("14-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WVNrWS12U2FUYTg");
        hmLink.put("14-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OEIxZTlXY0hZc3M");
        hmLink.put("14-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YnF6dEQ3OEJ3dmM");
        hmLink.put("14-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UEF5OXctLWxpRTg");
        hmLink.put("14-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZTdxMlY2Z1gtQjQ");
        hmLink.put("14-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YngyaEV5dUFKVzA");
        hmLink.put("14-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QlpRTHVWWVhibU0");
        hmLink.put("14-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y3VPR1FFSkh2SXM");
        hmLink.put("14-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bjJwWXJ6a2s5ZmM");
        hmLink.put("14-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Njh1YlJuYWZUdk0");
        hmLink.put("14-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eW9MY1pSclFHcDQ");
        //15
        hmLink.put("15-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4azh5UThSSmJkTUU");
        hmLink.put("15-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UEZGV25neXFrTDg");
        hmLink.put("15-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4X0c3T1g0a2xWQTA");
        hmLink.put("15-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ODVjakZ2RE9tQXc");
        hmLink.put("15-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WDEyTm5rVnphbWs");
        hmLink.put("15-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UVdySEtTLVJ5X1E");
        hmLink.put("15-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YUdycXVYUEFkbE0");
        hmLink.put("15-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bUtTYW5HcUl2cUE");
        hmLink.put("15-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UGFwVUM5QjBxUWM");
        hmLink.put("15-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4LUJvMjhRM1UwMDg");
        //16
        hmLink.put("16-01", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WkNvM0V0Z1kxUTA");
        hmLink.put("16-02", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Q1Z6WFpCelQzeXM");
        hmLink.put("16-03", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QXkxV0hZYVZGalk");
        hmLink.put("16-04", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NWFtb0NEWlgtV3c");
        hmLink.put("16-05", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aU4xTzdqYy1CUjA");
        hmLink.put("16-06", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RS1fSmRNMG0xSnc");
        hmLink.put("16-07", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4clNBb2NmUTFaMzQ");
        hmLink.put("16-08", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T2s4cm5MLTNJX2M");
        hmLink.put("16-09", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RUY4SFdNLV9tZ3c");
        hmLink.put("16-010", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dUZfUjFoSGpUWFE");
        hmLink.put("16-011", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aklNeUJDV2F5MDA");
        hmLink.put("16-012", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZkFsRV9lZHY1QzA");
        hmLink.put("16-013", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bXNLZzF0eE5nWDA");
        hmLink.put("16-014", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cnNvY3VNTS1yTG8");
        hmLink.put("16-015", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4c0RUeGxuWnMyems");
        hmLink.put("16-016", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TFJqRFE2dVZLb2s");
        hmLink.put("16-017", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4THFxdHVGWDR6UVU");
        hmLink.put("16-018", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T0pDSGUtRlg4N0U");
        hmLink.put("16-019", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Zk16ZC10T2lGdXM");
        hmLink.put("16-020", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eWtKSk9XWUh5NE0");
        hmLink.put("16-021", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RUl2T3ZSSUhYbWM");
        hmLink.put("16-022", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MFVyeFJaeDJjTms");
        hmLink.put("16-023", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R0hIYXlqWkZKVW8");
        hmLink.put("16-024", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4X0lsMVZ0ZzgtR3c");
        hmLink.put("16-025", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cEtwZURSVm5aUDg");
        hmLink.put("16-026", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZExSY2NHVEx1N0E");
        hmLink.put("16-027", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4LS1rTHZSN0NKTXM");
        hmLink.put("16-028", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aVFlV2VfMEtyTUE");
        hmLink.put("16-029", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TnkwenF2Mkw5d1U");
        hmLink.put("16-030", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OWhsRWtNTVVkMTA");
        hmLink.put("16-031", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NjlIdEtUWktmdjg");
        hmLink.put("16-032", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QkNhWVBzRDhOUDQ");
        hmLink.put("16-033", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4elZfWGptaFhzTms");
        hmLink.put("16-034", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MVZFODNrcU9JRlU");
        hmLink.put("16-035", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YkxubTVON3ZNQXM");
        hmLink.put("16-036", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SUFTSUFENlVwRHc");
        hmLink.put("16-037", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OVQzemVnR0xISFk");
        hmLink.put("16-038", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Sk1RX1AydFVMakU");
        hmLink.put("16-039", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NGJEWEVDUUMtODg");
        hmLink.put("16-040", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4c0NJZmxPazJVOVE");
        hmLink.put("16-041", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZlBOZW5Zc1hSM0E");
        hmLink.put("16-042", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WWx3ODNmZG9heXc");
        hmLink.put("16-043", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UEhJNFBtZ244SWs");
        hmLink.put("16-044", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TEJwbW1LOWdFYk0");
        hmLink.put("16-045", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RTE2NE5zc3JDdE0");
        hmLink.put("16-046", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SXFkazBLd3ZJbVE");
        hmLink.put("16-047", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RVNFX3lQVHQ4c0k");
        hmLink.put("16-048", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OHVHT2tFUnRtY1k");
        hmLink.put("16-049", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SThtckUxVkhQOFE");
        hmLink.put("16-050", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YjAwS29DNm5qd2s");
        hmLink.put("16-051", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T3hSZkVNYm1aVFk");
        hmLink.put("16-052", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OHBtdkNsZU9LaW8");
        hmLink.put("16-053", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UGpXNENKNzR2VDQ");
        hmLink.put("16-054", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4OEFBekRMeDhmY2M");
        hmLink.put("16-055", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QXNqcXpWTHBpRlk");
        hmLink.put("16-056", "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T1dzVGFDRE5tUzA");


        //-----------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------
        // Hash Map for Audio List Names------------------------------------------
        /*NOte: < Important > file names should never be the same

        * failing to maintain the discipline will not download the new file
        * Because all the files exists in the same folder*/

        //Even though if you fail to check all names At least make sure that the names dont end with ' (1) '


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
        //9
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
        //10
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
        //11
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
        //12
        hmFileName.put("12-01", "Dars e Hadeeth-Buloogh ul Maraam-Business Part 7--Sh Anees ur Rahman");
        hmFileName.put("12-02", "Dars e Hadeeth Buloogh ul Maraam Business Part 6 -Sh Anees ur Rahman");
        hmFileName.put("12-03", "Dars e Hadeeth Buloogh ul Maraam-Issues pretaining Marriage-2-Sh Anees ur Rahman");
        hmFileName.put("12-04", "Dars e Hadeeth Buloogh ul Maraam Business Part 5 -Sh Anees ur Rahman");
        hmFileName.put("12-05", "dars e hadeeth-Buloogh ul Maraam-z -Sh Anees ur Rahman");
        hmFileName.put("12-06", "Dars e Hadeeth-Buloogh ul Maraam-Kitaqab ul Hajj--Sh Anees ur Rahman");
        hmFileName.put("12-07", "Dars e Hadeeth-Buloogh ul Maraam-Fasting Part 2--Sh Anees ur Rahman");
        hmFileName.put("12-08", "Dars e Hadeeth-Buloogh ul Maraam-Zakaah 2--Sh Anees ur Rahman");
        hmFileName.put("12-09", "Dars e Hadeeth-Buloogh ul Maraam-Zakaah--Sh Anees ur Rahman");
        hmFileName.put("12-010", "Dars e Hadeeth-Buloogh ul Maraam-Tashahhud-Sh Anees ur Rahman");
        hmFileName.put("12-011", "Dars e hadeeth-Buloogh ul Maraam-sunnath & nafeel salaah -(2)-Sh Anees ur Rahman");
        hmFileName.put("12-012", "Dars e hadeeth-Buloogh ul Maraam-tahajjud & taraweeh -Sh Anees ur Rahman");
        hmFileName.put("12-013", "Dars e Hadeeth-Buloogh ul Maraam-Salaah-  -Sh Anees ur Rahman");
        hmFileName.put("12-014", "Dars e Hadeeth-Buloogh ul Maraam-Saum (Fasting)--Sh Anees ur Rahman");
        hmFileName.put("12-015", "Dars e Hadeeth-Buloogh ul Maraam-place of  prayer-  -Sh Anees ur Rahman");
        hmFileName.put("12-016", "Dars e hadeeth-Buloogh ul Maraam-musafir ki namaz-    -Sh Anees ur Rahman");
        hmFileName.put("12-017", "Dars e hadeeth-Buloogh ul Maraam-Menstating women--Sh Anees ur Rahman");
        hmFileName.put("12-018", "Dars e Hadeeth-Buloogh ul Maraam-Mahr a Valima--Sh Anees ur Rahman");
        hmFileName.put("12-019", "Dars e Hadeeth-Buloogh ul Maraam-Libaas & Janaza Rituals -Sh Anees ur Rahman");
        hmFileName.put("12-020", "Dars e Hadeeth-Buloogh ul Maraam-Kitaab ul Hajj-part3-Sh Anees ur Rahman");
        hmFileName.put("12-021", "Dars e Hadeeth-Buloogh ul Maraam-Jumu-ah-    -Sh Anees ur Rahman");
        hmFileName.put("12-022", "Dars e Hadeeth-Buloogh ul Maraam-Kitaab ul Hajj-part2 -Sh Anees ur Rahman");
        hmFileName.put("12-023", "Dars e Hadeeth-Buloogh ul Maraam-Janaza Rituals2--Sh Anees ur Rahman");
        hmFileName.put("12-024", "Dars e Hadeeth-Buloogh ul Maraam-Janaza Rituals--Sh Anees ur Rahman");
        hmFileName.put("12-025", "Dars e hadeeth-Buloogh ul Maraam-dress code for prayers- 3-Sh Anees ur Rahman");
        hmFileName.put("12-026", "Dars e Hadeeth-Buloogh ul Maraam-Hiba and Wiraasath--Sh Anees ur Rahman");
        hmFileName.put("12-027", "Dars e Hadeeth-Buloogh ul Maraam-eid n fear prayers--Sh Anees ur Rahman");
        hmFileName.put("12-028", "Dars e Hadeeth-Buloogh ul Maraam-Eclipse Prayer and Libaas --Sh Anees ur Rahman");
        hmFileName.put("12-029", "Dars e Hadeeth-Buloogh ul Maraam-Libaas --Sh Anees ur Rahman");
        hmFileName.put("12-030", "Dars e Hadeeth-Buloogh ul Maraam-Business Part 3--Sh Anees ur Rahman");
        hmFileName.put("12-031", "Dars e Hadeeth-Buloogh ul Maraam-Business Part 1--Sh Anees ur Rahman");
        hmFileName.put("12-032", "Dars e Hadeeth-Buloogh ul Maraam-Business Part 2--Sh Anees ur Rahman");
        //13
        hmFileName.put("13-01", "Dars usool uthalatha 9 shaik Ansar ud deen Makki");
        hmFileName.put("13-02", "Dars usool uthalatha 8 shaik Ansar ud deen Makki");
        hmFileName.put("13-03", "Dars usool uthalatha 7 shaik Ansar ud deen Makki");
        hmFileName.put("13-04", "Dars usool uthalatha 6 shaik Ansar ud deen Makki");
        hmFileName.put("13-05", "Dars usool uthalatha 4 shaik Ansar ud deen Makki");
        hmFileName.put("13-06", "Dars usool uthalatha 3 shaik Ansar ud deen Makki");
        hmFileName.put("13-07", "Dars usool uthalatha 2 shaik Ansar ud deen Makki");
        hmFileName.put("13-08", "Dars usool uthalatha 1 shaik Ansar ud deen Makki");
        //14
        hmFileName.put("14-01", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 1");
        hmFileName.put("14-02", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 2");
        hmFileName.put("14-03", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 3");
        hmFileName.put("14-04", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 4");
        hmFileName.put("14-05", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 5");
        hmFileName.put("14-06", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 6");
        hmFileName.put("14-07", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 7");
        hmFileName.put("14-08", "Explanation of Kitaab ut Tauheed- Part 1 - Shaikh Abdus Salaam omeri Madani - Class 8");
        hmFileName.put("14-09", "Explanation of Kitaab ut Tauheed- Part 2 - Shaikh Abdus Salaam omeri Madani - Class 10");
        hmFileName.put("14-010", "Explanation of Kitaab ut Tauheed- Part 2 - Shaikh Abdus Salaam omeri Madani - Class 11");
        hmFileName.put("14-011", "Explanation of Kitaab ut Tauheed- Part 2 - Shaikh Abdus Salaam omeri Madani - Class 9");
        hmFileName.put("14-012", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 12");
        hmFileName.put("14-013", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 13");
        hmFileName.put("14-014", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 14");
        hmFileName.put("14-015", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 15");
        hmFileName.put("14-016", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 16");
        hmFileName.put("14-017", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 17");
        hmFileName.put("14-018", "Explanation of Kitaab ut Tauheed- Part 3 - Shaikh Abdus Salaam omeri Madani - Class 18");
        //15
        hmFileName.put("15-01", "shaan e jalaalah -Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-02", "Quran sey pehley eeman -Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-03", "fawaa-id e asma wa sifaat 2 -Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-04", "fawa-id e asma wa sifaat - Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-05", "eeman bil malaa-ikah - Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-06", "Qur-an aur Ramadhan k ta-alluqat -Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-07", "eemaan bin nabi -Eemaniyath -Dr R K Noor Madani");
        hmFileName.put("15-08", "eemaan bil qadr -Eemaniyath -Dr R K Noor Madani");
        hmFileName.put("15-09", "eemaan bil kittab -Eemaniyath Dr R K Noor Madani");
        hmFileName.put("15-010", "eemaan bil aakhira -Eemaniyath - Dr R K Noor Madani");
        //16
        hmFileName.put("16-01", "Ahlul ilm Ka Ehteraam - Shaikh Anees ur Rahman");
        hmFileName.put("16-02", "Ahlul ilm sey doori - Shaikh Anees ur Rehman");
        hmFileName.put("16-03", "Auraton kay Liye Naseehath - Shaikh Anees ur Rahman");
        hmFileName.put("16-04", "Auraton kay Liye Naseehath-Part2 - Shaikh Anees ur Rahman");
        hmFileName.put("16-05", "Bid-ath - Shaikh Anees ur Rahman");
        hmFileName.put("16-06", "Dhulhijja aur dhikr - shaikh anees ur rahman");
        hmFileName.put("16-07", "eemaan billah- shk anees");
        hmFileName.put("16-08", "eeman walon - purey musalman banjao - shk anees ur rahman");
        hmFileName.put("16-09", "Ehteraam - Shaikh Anees ur Rahman");
        hmFileName.put("16-010", "Eid ul Adh-Ha - Masa-il Aur Ahkaam - Part 1 - Shaikh Anees ur Rahman");
        hmFileName.put("16-011", "Eid ul Fitr - Ahkaam - Shaikh Anees ur Rahman");
        hmFileName.put("16-012", "Ek Aham Hukum - Anmbiya kay Ta-alluq say - Shaikh Aneesur Rahman");
        hmFileName.put("16-013", "farishton par eemaan 2 - shk anees ur rahman");
        hmFileName.put("16-014", "Fawaa-id e Zikrullah- part2 - Shaikh Anees ur Rahman");
        hmFileName.put("16-015", "Fawaa-id e Zikrullah- part3 - Shaikh Anees ur Rahman");
        hmFileName.put("16-016", "Faza-il e hijrath A critical analysis of New Year in the view of Islam-Sh AneesurRahman");
        hmFileName.put("16-017", "Fitney - Shaikh Aneesur Rahman");
        hmFileName.put("16-018", "Fitnon k chund Aqsaam - Shaikh Anees ur Rahman");
        hmFileName.put("16-019", "Gheebath - Shaikh Anees ur Rahman");
        hmFileName.put("16-020", "Gheebath – Waqt ki Barbaadi ka ek Sabab – Shaikh Aneesur Rahman");
        hmFileName.put("16-021", "gumraahiyaan - shaikh Anees ur Rahman");
        hmFileName.put("16-022", "Hukmraan - ek Saazish - Shaikh Anees ur Rahman");
        hmFileName.put("16-023", "insaan aur eemaan - shaikh Anees ur Rehman");
        hmFileName.put("16-024", "insaan aur eemaan 2 - shaikh Anees ur Rahman");
        hmFileName.put("16-025", "janaza rights - shaikh anees ur rahman");
        hmFileName.put("16-026", "Jhoot - Shaikh Anees ur Rahman");
        hmFileName.put("16-027", "jumah 06.02.2015");
        hmFileName.put("16-028", "Jum-ah Khutba - 28.3.14 - Shaikh Anees ur Rahman");
        hmFileName.put("16-029", "Jum-ah Khutba - Nikaah - Shaikh Anees ur Rahman");
        hmFileName.put("16-030", "Jum-ah Khutbah - 12 december- Shaikh Anees ur Rahman");
        hmFileName.put("16-031", "Jumah Khutbah - 14.03.14 Shaikh Anees ur Rahman");
        hmFileName.put("16-032", "Jum-ah Khutbah - 18.7.14 - Shaikh Anees ur Rahman");
        hmFileName.put("16-033", "Jum-ah Khutbah - 4.07.14 - Shaikh Anees ur Rahman");
        hmFileName.put("16-034", "Jum-ah Quthba- farishton par eemaan & qurbani k Masa-il -Shaikh aneesur Rahman");
        hmFileName.put("16-035", "Kuffaar ki Mushabihath - Shaikh Anees ur Rahman");
        hmFileName.put("16-036", "Libas and israaf jumma 7.6.13");
        hmFileName.put("16-037", "Maahey Muharram k Ta-aaluq - Shaikh Anees ur Rahman");
        hmFileName.put("16-038", "Marey Huvey Bhai ka Gosht Khana - Shaikh Anees ur Rahman");
        hmFileName.put("16-039", "Masaajid ki abaadi - Shaikh Anees ur Rehman");
        hmFileName.put("16-040", "masa-il e dhul hijja - shk anees ur rahman");
        hmFileName.put("16-041", "masa-il e eid ul adh-ha - shk anees ur rahman");
        hmFileName.put("16-042", "Masjid walon k dil veeran - Shaikh Anees ur Rahman 	");
        hmFileName.put("16-043", "Mu-Min ki Namaz - - Shaikh Anees ur Rahman");
        hmFileName.put("16-044", "Mushabahath - Shaikh Anees ur Rahman");
        hmFileName.put("16-045", "Protests - An Analysis - Shaikh Anees ur Rahman");
        hmFileName.put("16-046", "Protests - An Analysis - Shaikh Anees ur Rahman_001");
        hmFileName.put("16-047", "Qiyaamath kay suwaalaat - Shaikh Anees ur Rahman");
        hmFileName.put("16-048", "Qiyaamath kay suwaalaat 2 - Shaikh Anees ur Rahman");
        hmFileName.put("16-049", "Talaaq kay Masaa-il - Part2 - Shaikh AneesurRahman");
        hmFileName.put("16-050", "Talaaq Kay Masaa-il aur Ahkaam - Shaikh AneesurRahman   	");
        hmFileName.put("16-051", "Tazkiyyathun nafs, Ilm, Qayamath k Alaamat - Shaikh Anees ur Rahman");
        hmFileName.put("16-052", "Voting - A Muslims Duty - Shaikh Anees ur Rahman");
        hmFileName.put("16-053", "Yaum e Aashura k Mutalliq baaz Tareeqi cheezen - Shaikh Anees ur Rahman");
        hmFileName.put("16-054", "Yaum e Aashurah ki Haqeeqath - Shaikh Aneesur Rahman");
        hmFileName.put("16-055", "Yaum e Ashura ka roza - Shaikh Anees ur Rahman");
        hmFileName.put("16-056", "Zabaan Ki Museebatein - Shaikh Anees ur Rahman");


        //------------------------------------------------------------------------------------------------------------
        //----Hash Map for Lan Id----------------------------------
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
        //12
        hmLanID.put("12-01", LidUrdKey);
        hmLanID.put("12-02", LidUrdKey);
        hmLanID.put("12-03", LidUrdKey);
        hmLanID.put("12-04", LidUrdKey);
        hmLanID.put("12-05", LidUrdKey);
        hmLanID.put("12-06", LidUrdKey);
        hmLanID.put("12-07", LidUrdKey);
        hmLanID.put("12-08", LidUrdKey);
        hmLanID.put("12-09", LidUrdKey);
        hmLanID.put("12-010", LidUrdKey);
        hmLanID.put("12-011", LidUrdKey);
        hmLanID.put("12-012", LidUrdKey);
        hmLanID.put("12-013", LidUrdKey);
        hmLanID.put("12-014", LidUrdKey);
        hmLanID.put("12-015", LidUrdKey);
        hmLanID.put("12-016", LidUrdKey);
        hmLanID.put("12-017", LidUrdKey);
        hmLanID.put("12-018", LidUrdKey);
        hmLanID.put("12-019", LidUrdKey);
        hmLanID.put("12-020", LidUrdKey);
        hmLanID.put("12-021", LidUrdKey);
        hmLanID.put("12-022", LidUrdKey);
        hmLanID.put("12-023", LidUrdKey);
        hmLanID.put("12-024", LidUrdKey);
        hmLanID.put("12-025", LidUrdKey);
        hmLanID.put("12-026", LidUrdKey);
        hmLanID.put("12-027", LidUrdKey);
        hmLanID.put("12-028", LidUrdKey);
        hmLanID.put("12-029", LidUrdKey);
        hmLanID.put("12-030", LidUrdKey);
        hmLanID.put("12-031", LidUrdKey);
        hmLanID.put("12-032", LidUrdKey);
        //13
        hmLanID.put("13-01", LidUrdKey);
        hmLanID.put("13-02", LidUrdKey);
        hmLanID.put("13-03", LidUrdKey);
        hmLanID.put("13-04", LidUrdKey);
        hmLanID.put("13-05", LidUrdKey);
        hmLanID.put("13-06", LidUrdKey);
        hmLanID.put("13-07", LidUrdKey);
        hmLanID.put("13-08", LidUrdKey);
        //14
        hmLanID.put("14-01", LidEngKey);
        hmLanID.put("14-02", LidEngKey);
        hmLanID.put("14-03", LidEngKey);
        hmLanID.put("14-04", LidEngKey);
        hmLanID.put("14-05", LidEngKey);
        hmLanID.put("14-06", LidEngKey);
        hmLanID.put("14-07", LidEngKey);
        hmLanID.put("14-08", LidEngKey);
        hmLanID.put("14-09", LidEngKey);
        hmLanID.put("14-010", LidEngKey);
        hmLanID.put("14-011", LidEngKey);
        hmLanID.put("14-012", LidEngKey);
        hmLanID.put("14-013", LidEngKey);
        hmLanID.put("14-014", LidEngKey);
        hmLanID.put("14-015", LidEngKey);
        hmLanID.put("14-016", LidEngKey);
        hmLanID.put("14-017", LidEngKey);
        hmLanID.put("14-018", LidEngKey);
        //15
        hmLanID.put("15-01", LidUrdKey);
        hmLanID.put("15-02", LidUrdKey);
        hmLanID.put("15-03", LidUrdKey);
        hmLanID.put("15-04", LidUrdKey);
        hmLanID.put("15-05", LidUrdKey);
        hmLanID.put("15-06", LidUrdKey);
        hmLanID.put("15-07", LidUrdKey);
        hmLanID.put("15-08", LidUrdKey);
        hmLanID.put("15-09", LidUrdKey);
        hmLanID.put("15-010", LidUrdKey);
        //16
        hmLanID.put("16-01", LidUrdKey);
        hmLanID.put("16-02", LidUrdKey);
        hmLanID.put("16-03", LidUrdKey);
        hmLanID.put("16-04", LidUrdKey);
        hmLanID.put("16-05", LidUrdKey);
        hmLanID.put("16-06", LidUrdKey);
        hmLanID.put("16-07", LidUrdKey);
        hmLanID.put("16-08", LidUrdKey);
        hmLanID.put("16-09", LidUrdKey);
        hmLanID.put("16-010", LidUrdKey);
        hmLanID.put("16-011", LidUrdKey);
        hmLanID.put("16-012", LidUrdKey);
        hmLanID.put("16-013", LidUrdKey);
        hmLanID.put("16-014", LidUrdKey);
        hmLanID.put("16-015", LidUrdKey);
        hmLanID.put("16-016", LidUrdKey);
        hmLanID.put("16-017", LidUrdKey);
        hmLanID.put("16-018", LidUrdKey);
        hmLanID.put("16-019", LidUrdKey);
        hmLanID.put("16-020", LidUrdKey);
        hmLanID.put("16-021", LidUrdKey);
        hmLanID.put("16-022", LidUrdKey);
        hmLanID.put("16-023", LidUrdKey);
        hmLanID.put("16-024", LidUrdKey);
        hmLanID.put("16-025", LidUrdKey);
        hmLanID.put("16-026", LidUrdKey);
        hmLanID.put("16-027", LidUrdKey);
        hmLanID.put("16-028", LidUrdKey);
        hmLanID.put("16-029", LidUrdKey);
        hmLanID.put("16-030", LidUrdKey);
        hmLanID.put("16-031", LidUrdKey);
        hmLanID.put("16-032", LidUrdKey);
        hmLanID.put("16-033", LidUrdKey);
        hmLanID.put("16-034", LidUrdKey);
        hmLanID.put("16-035", LidUrdKey);
        hmLanID.put("16-036", LidUrdKey);
        hmLanID.put("16-037", LidUrdKey);
        hmLanID.put("16-038", LidUrdKey);
        hmLanID.put("16-039", LidUrdKey);
        hmLanID.put("16-040", LidUrdKey);
        hmLanID.put("16-041", LidUrdKey);
        hmLanID.put("16-042", LidUrdKey);
        hmLanID.put("16-043", LidUrdKey);
        hmLanID.put("16-044", LidUrdKey);
        hmLanID.put("16-045", LidUrdKey);
        hmLanID.put("16-046", LidUrdKey);
        hmLanID.put("16-047", LidUrdKey);
        hmLanID.put("16-048", LidUrdKey);
        hmLanID.put("16-049", LidUrdKey);
        hmLanID.put("16-050", LidUrdKey);
        hmLanID.put("16-051", LidUrdKey);
        hmLanID.put("16-052", LidUrdKey);
        hmLanID.put("16-053", LidUrdKey);
        hmLanID.put("16-054", LidUrdKey);
        hmLanID.put("16-055", LidUrdKey);
        hmLanID.put("16-056", LidUrdKey);


        //-----------------------------------------------------------------------------
        //-----------------------------------------------------------------------------
        // Hash MAp for Spk Id--------------------------------------------------------
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
        //12
        hmSpkID.put("12-01", SpkIntAneesRahman);
        hmSpkID.put("12-02", SpkIntAneesRahman);
        hmSpkID.put("12-03", SpkIntAneesRahman);
        hmSpkID.put("12-04", SpkIntAneesRahman);
        hmSpkID.put("12-05", SpkIntAneesRahman);
        hmSpkID.put("12-06", SpkIntAneesRahman);
        hmSpkID.put("12-07", SpkIntAneesRahman);
        hmSpkID.put("12-08", SpkIntAneesRahman);
        hmSpkID.put("12-09", SpkIntAneesRahman);
        hmSpkID.put("12-010", SpkIntAneesRahman);
        hmSpkID.put("12-011", SpkIntAneesRahman);
        hmSpkID.put("12-012", SpkIntAneesRahman);
        hmSpkID.put("12-013", SpkIntAneesRahman);
        hmSpkID.put("12-014", SpkIntAneesRahman);
        hmSpkID.put("12-015", SpkIntAneesRahman);
        hmSpkID.put("12-016", SpkIntAneesRahman);
        hmSpkID.put("12-017", SpkIntAneesRahman);
        hmSpkID.put("12-018", SpkIntAneesRahman);
        hmSpkID.put("12-019", SpkIntAneesRahman);
        hmSpkID.put("12-020", SpkIntAneesRahman);
        hmSpkID.put("12-021", SpkIntAneesRahman);
        hmSpkID.put("12-022", SpkIntAneesRahman);
        hmSpkID.put("12-023", SpkIntAneesRahman);
        hmSpkID.put("12-024", SpkIntAneesRahman);
        hmSpkID.put("12-025", SpkIntAneesRahman);
        hmSpkID.put("12-026", SpkIntAneesRahman);
        hmSpkID.put("12-027", SpkIntAneesRahman);
        hmSpkID.put("12-028", SpkIntAneesRahman);
        hmSpkID.put("12-029", SpkIntAneesRahman);
        hmSpkID.put("12-030", SpkIntAneesRahman);
        hmSpkID.put("12-031", SpkIntAneesRahman);
        hmSpkID.put("12-032", SpkIntAneesRahman);
        //13
        hmSpkID.put("13-01", SpkIntAnsarudinMakki);
        hmSpkID.put("13-02", SpkIntAnsarudinMakki);
        hmSpkID.put("13-03", SpkIntAnsarudinMakki);
        hmSpkID.put("13-04", SpkIntAnsarudinMakki);
        hmSpkID.put("13-05", SpkIntAnsarudinMakki);
        hmSpkID.put("13-06", SpkIntAnsarudinMakki);
        hmSpkID.put("13-07", SpkIntAnsarudinMakki);
        hmSpkID.put("13-08", SpkIntAnsarudinMakki);
        //14
        hmSpkID.put("14-01", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-02", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-03", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-04", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-05", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-06", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-07", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-08", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-09", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-010", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-011", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-012", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-013", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-014", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-015", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-016", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-017", SpkIntAbdusSalaamMadani);
        hmSpkID.put("14-018", SpkIntAbdusSalaamMadani);
        //15
        hmSpkID.put("15-01", SpkIntRKNoorMadani);
        hmSpkID.put("15-02", SpkIntRKNoorMadani);
        hmSpkID.put("15-03", SpkIntRKNoorMadani);
        hmSpkID.put("15-04", SpkIntRKNoorMadani);
        hmSpkID.put("15-05", SpkIntRKNoorMadani);
        hmSpkID.put("15-06", SpkIntRKNoorMadani);
        hmSpkID.put("15-07", SpkIntRKNoorMadani);
        hmSpkID.put("15-08", SpkIntRKNoorMadani);
        hmSpkID.put("15-09", SpkIntRKNoorMadani);
        hmSpkID.put("15-010", SpkIntRKNoorMadani);
        //16
        hmSpkID.put("16-01", SpkIntAneesRahman);
        hmSpkID.put("16-02", SpkIntAneesRahman);
        hmSpkID.put("16-03", SpkIntAneesRahman);
        hmSpkID.put("16-04", SpkIntAneesRahman);
        hmSpkID.put("16-05", SpkIntAneesRahman);
        hmSpkID.put("16-06", SpkIntAneesRahman);
        hmSpkID.put("16-07", SpkIntAneesRahman);
        hmSpkID.put("16-08", SpkIntAneesRahman);
        hmSpkID.put("16-09", SpkIntAneesRahman);
        hmSpkID.put("16-010", SpkIntAneesRahman);
        hmSpkID.put("16-011", SpkIntAneesRahman);
        hmSpkID.put("16-012", SpkIntAneesRahman);
        hmSpkID.put("16-013", SpkIntAneesRahman);
        hmSpkID.put("16-014", SpkIntAneesRahman);
        hmSpkID.put("16-015", SpkIntAneesRahman);
        hmSpkID.put("16-016", SpkIntAneesRahman);
        hmSpkID.put("16-017", SpkIntAneesRahman);
        hmSpkID.put("16-018", SpkIntAneesRahman);
        hmSpkID.put("16-019", SpkIntAneesRahman);
        hmSpkID.put("16-020", SpkIntAneesRahman);
        hmSpkID.put("16-021", SpkIntAneesRahman);
        hmSpkID.put("16-022", SpkIntAneesRahman);
        hmSpkID.put("16-023", SpkIntAneesRahman);
        hmSpkID.put("16-024", SpkIntAneesRahman);
        hmSpkID.put("16-025", SpkIntAneesRahman);
        hmSpkID.put("16-026", SpkIntAneesRahman);
        hmSpkID.put("16-027", SpkIntAneesRahman);
        hmSpkID.put("16-028", SpkIntAneesRahman);
        hmSpkID.put("16-029", SpkIntAneesRahman);
        hmSpkID.put("16-030", SpkIntAneesRahman);
        hmSpkID.put("16-031", SpkIntAneesRahman);
        hmSpkID.put("16-032", SpkIntAneesRahman);
        hmSpkID.put("16-033", SpkIntAneesRahman);
        hmSpkID.put("16-034", SpkIntAneesRahman);
        hmSpkID.put("16-035", SpkIntAneesRahman);
        hmSpkID.put("16-036", SpkIntAneesRahman);
        hmSpkID.put("16-037", SpkIntAneesRahman);
        hmSpkID.put("16-038", SpkIntAneesRahman);
        hmSpkID.put("16-039", SpkIntAneesRahman);
        hmSpkID.put("16-040", SpkIntAneesRahman);
        hmSpkID.put("16-041", SpkIntAneesRahman);
        hmSpkID.put("16-042", SpkIntAneesRahman);
        hmSpkID.put("16-043", SpkIntAneesRahman);
        hmSpkID.put("16-044", SpkIntAneesRahman);
        hmSpkID.put("16-045", SpkIntAneesRahman);
        hmSpkID.put("16-046", SpkIntAneesRahman);
        hmSpkID.put("16-047", SpkIntAneesRahman);
        hmSpkID.put("16-048", SpkIntAneesRahman);
        hmSpkID.put("16-049", SpkIntAneesRahman);
        hmSpkID.put("16-050", SpkIntAneesRahman);
        hmSpkID.put("16-051", SpkIntAneesRahman);
        hmSpkID.put("16-052", SpkIntAneesRahman);
        hmSpkID.put("16-053", SpkIntAneesRahman);
        hmSpkID.put("16-054", SpkIntAneesRahman);
        hmSpkID.put("16-055", SpkIntAneesRahman);
        hmSpkID.put("16-056", SpkIntAneesRahman);


//---MAin Insertion------------------------------------------------------------------------
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
                        if (CheckFileNameAlreadyExistinginAudiList(TABLE_AUDIO_LIST, AUD_FILE_NAME, hmFileName.get(keytolocate))) {
                            Log.d(TAG, "trying to rename file" + hmFileName.get(keytolocate));
                            cv.put(AUD_FILE_NAME, changeFileNameAudioList(hmFileName.get(keytolocate)));
                        } else {
                            cv.put(AUD_FILE_NAME, hmFileName.get(keytolocate));
                        }
                        //cv.put(AUD_FILE_NAME, hmFileName.get(keytolocate));
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

        //old code below
        /*String query = "INSERT INTO " + TABLE_AUDIO_LIST + "(" + AUD_FRID + "," + AUD_DOWNLOAD_LINK + "," + AUD_FILE_NAME + "," + AUD_LAN_ID + "," + AUD_SPK_ID + ") VALUES " +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RWlXUkQxTmJDcXc','Aqeeda Al Wasitiya 01'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NWplWV9VR0xUR0U','Aqeeda Al Wasitiya 02'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1puS0I2VWNKUVk','Aqeeda Al Wasitiya 16'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R29rcDF4dGJZR1U','Aqeeda Al Wasitiya 17'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")," +
                "(1,'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4NURjVVlldW5YTXM','Aqeeda Al Wasitiya 18'," + LidUrdKey + "," + SpkIntRKNoorMadani + ")"; */
    }

    // Method If the name of the file already exist
    // is the name already exist then in concadenates (+1) with the name
    public boolean CheckFileNameAlreadyExistinginAudiList(String TableName,
                                                          String dbfield,
                                                          String fieldValue) {

        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //Method to Rename the file Name if NAme Already existing
    public String changeFileNameAudioList(String fileName) {

        String newName = fileName;
        //String newName = fileName + ("(" + 1 + ")");
        /*if (CheckFileNameAlreadyExistinginAudiList(TABLE_AUDIO_LIST, AUD_FILE_NAME, newName)) {
            newName = newName + "(" + 2 + ")";
            Log.e(TAG, "Renamed inside if " + newName);
        }*/
        do newName = newName + "(" + 1 + ")";
        while (CheckFileNameAlreadyExistinginAudiList(TABLE_AUDIO_LIST, AUD_FILE_NAME, newName));
        Log.e(TAG, "Renamed to " + newName);
        return newName;

    }


//__________________________________________________________________________________________________________________________________________________________

    //Audio General table SQL Insert statement
    public void insertAudioGeneral() {
        String ArrayGeneralName[] = {
                "Role of Youths in Nurturing the Soceity -Sh Abdullah Hyderabadi",//1
                "Khudkhushi Ka Anjam -Sh Abdullah Hyderabadi",//2
                "12 Rabi al awal ki haqeqath-Sh Abdullah Hyderabadi",//3
                "Muqaam e Qatoon-Sh Abdullah Hyderabadi",//4
                "Neyk Aurath-Sh Abdullah Hyderabadi",//5
                "Think Befor you Talk about Saudi Arab 01-Sh Aneesur Rahman Madani",//6
                "Warning Against Deviators -Sh Aneesur Rahman Madani",//7
                "The Role of Salaf in Understanding the Qur-an - Sh Aneesur Rahman Madani",//8
                "Think Befor you Talk about Saudi Arab - ~1_001Sh Aneesur Rahman Madani",//9
                "The Social Life - Sh Aneesur Rahman Madani",//10
                "Think Befor you Talk about Saudi Arab 02- Sh Aneesur Rahman Madani",//11
                "Think Befor you Talk about Saudi Arab 03- Sh Aneesur Rahman Madani",//12
                "Shab e Qadr and E-tikaaf - Sh Aneesur Rahman Madani",//13
                "Ramadhan Kay Masa-il - Sh Aneesur Rahman Madani",//14
                "Naseehat - Sh Aneesur Rahman Madani",//15
                "Powerful Reminder- Sh Aneesur Rahman Madani",//16
                "Maahe Ramadhan mey Ibaadath kay Aqsaam Sh Aneesur Rahman Madani",//17
                "Haraam aur Halaal - Sh Aneesur Rahman Madani",//18
                "E-tikaaf and Zakaath - Sh Aneesur Rahman Madani",//19
                "Bismillah ki Barkath - Sh Aneesur Rahman Madani",//20
                "Aadaab Sh Aneesur Rahman Madani",//21
                "Aqeedey ki Islaah - Sh Aneesur Rahman Madani",//22
                "Barelvism and Sufism ka Fitnah Sh Aneesur Rahman Madani",//23
                "Ad khilnal jannah wa najaata minannaar Dr R K Noor ",//24
                "Towbah Istigfaar - Dr RK Noor Madani",//25
                "zakaath - Dr Rk Noor Madani",//26
                "Umm Sulaim - DrRKNoor Madani",//27
                "virtues of fasting - Dr R k Noor",//28
                "shab e qadr ka ehteram sh R K Noor Madani",//29
                "Ramadhan Lecture - Lailatul Qadr - DrRKNoor Madani ",//30
                "Ramadhan Lecture - Istighfaar - Dr R K Noor Madani ",//31
                "tazkiya Sanad Ijaza - drRKNoor madani",//32
                "Reply to those who blame the Scholars",//33
                "maqasid e ramadhan ka tazkiyyah - Dr Rk Noor Madani",//34
                "Qur-an Shaitaan - Dr Rk Noor Madani",//35
                "Moujzaat e Nabwi Sal Allahu alaihi wa sallam - Sh RK Noor Madani",//36
                "lessons from Ramadhan - Shaikh RK Noor Madani",//37
                "masa-il e etikaaf - Dr Rk Noor Madani",//38
                "Lailatul Qadr - Dr Rk Noor Madani",//39
                "Jaago Aye Musalmaan - DrRkNoor Madani",//40
                "Islam ek Ni-amath - usey pehchano - Sh RKNoor Madani",//41
                "Husn Al Khaatimah - Dr Rk Noor madani",//42
                "Ghazwa e badr Dr R K Noor Madani",//43
                "Huqooq - Dr Rk Noor Madani",//44
                "Fasting - Result Analysis - Dr Rk Noor madani",//45
                "common mistakes in salaah - Dr Rk Noor Madani",//46
                "Abdullah Zulbijadain - Mufti Umar Sheriff Qasimi",//47
                "Know Thy Creator - Mufti Umar Sheriff Qasimi"//48
        };

        String ArrayGeneralLinks[] = {
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VlI4bmptWGhINlk",//1
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UExHU0h5cUIyNjQ",//2
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4N3YtdzMwa0w4VG8",//3
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eG4tZF8yZ1dKOXc",//4
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4akxhanRoY2pPZlk",//5
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzZPOTA2eE1RVGM",//6
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZW1NR2Jqd2oxekk",//7
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4N2tMeXRUcjRPT1U",//8
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cUR0ZS1YLUNfOUE",//9
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eU1DY1RLVlBMclE",//10
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VjJFRWdULWJGdjg",//11
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QndDMFFncWQ5emc",//12
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SDR2elFiS3FsU0k",//13
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TkZxTEtmbWo0V28",//14
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4czFvbnRXXy1ER00",//15
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZnlBVnNSemFxWEU",//16
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ajhQWGZKTjVZSVE",//17
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R2xIYlBjbGJaeHc",//18
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QzM4WkN0X1FhV3c",//19
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WDdNcFRJY2lMQ1E",//20
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R04tOW4tNG9DdmM",//21
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4UFNydTdGZEJjUGM",//22
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZzFlZkk5MzBLRlU",//23
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TTl3eERqY0xhUzg",//24
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bndEdnJjbGhJZkU",//25
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4elVjNndkb2ZDME0",//26
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4V0xDSlhGTExhb1E",//27
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4elM5RFRYelh0c2s",//28
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4REpvRG5kYndUem8",//29
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Znh3X2tqT0diTW8",//30
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4My04ZUY5eXd4TUk",//31
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cFNGNTFfNUhiLUU",//32
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Z1dKaFJtVDI5Rms",//33
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MDk2T0hyRVgtQzQ",//34
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SUpqVm1SSmxkeUk",//35
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cnhITUpJSmYxTFU",//36
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TktrZWNRbVRhV00",//37
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R3U4RWRrblJkazA",//38
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4QmhiY0ZiZE0zcWs",//39
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MWVSYjFiRXNvd00",//40
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dzUySVhxeTNuTnM",//41
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dC1Ua092Nm1MUVE",//42
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4cWUzY241UnZEam8",//43
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Vm5yU0pXNjRKMnc",//44
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4T2dVcXo2UFZVcHM",//45
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4RWZPNTkxa1RGOHc", //46
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VDRQMzA1eU9aR3c",//47
                "https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eXl0MHpoOElNNWc"//48
        };

        int ArrayGeneralLanID[] = {
                LidUrdKey,//1
                LidUrdKey,//2
                LidUrdKey,//3
                LidUrdKey,//4
                LidUrdKey,//5
                LidUrdKey,//6
                LidUrdKey,//7
                LidUrdKey,//8
                LidUrdKey,//9
                LidUrdKey,//10
                LidUrdKey,//11
                LidUrdKey,//12
                LidUrdKey,//13
                LidUrdKey,//14
                LidUrdKey,//15
                LidUrdKey,//16
                LidUrdKey,//17
                LidUrdKey,//18
                LidUrdKey,//19
                LidUrdKey,//20
                LidUrdKey,//21
                LidUrdKey,//22
                LidUrdKey,//23
                LidUrdKey,//24
                LidUrdKey,//25
                LidUrdKey,//26
                LidUrdKey,//27
                LidUrdKey,//28
                LidUrdKey,//29
                LidUrdKey,//30
                LidUrdKey,//31
                LidUrdKey,//32
                LidUrdKey,//33
                LidUrdKey,//34
                LidUrdKey,//35
                LidUrdKey,//36
                LidUrdKey,//37
                LidUrdKey,//38
                LidUrdKey,//39
                LidUrdKey,//40
                LidUrdKey,//41
                LidUrdKey,//42
                LidUrdKey,//43
                LidUrdKey,//44
                LidUrdKey,//45
                LidUrdKey,//46
                LidTamKey,//47
                LidTamKey//48
        };

        int ArrayGeneralSpkID[] = {
                SpkIntAbdullahHydrabadi,//1
                SpkIntAbdullahHydrabadi,//2
                SpkIntAbdullahHydrabadi,//3
                SpkIntAbdullahHydrabadi,//4
                SpkIntAbdullahHydrabadi,//5
                SpkIntAneesRahman,//6
                SpkIntAneesRahman,//7
                SpkIntAneesRahman,//8
                SpkIntAneesRahman,//9
                SpkIntAneesRahman,//10
                SpkIntAneesRahman,//11
                SpkIntAneesRahman,//12
                SpkIntAneesRahman,//13
                SpkIntAneesRahman,//14
                SpkIntAneesRahman,//15
                SpkIntAneesRahman,//16
                SpkIntAneesRahman,//17
                SpkIntAneesRahman,//18
                SpkIntAneesRahman,//19
                SpkIntAneesRahman,//20
                SpkIntAneesRahman,//21
                SpkIntAneesRahman,//22
                SpkIntAneesRahman,//23
                SpkIntRKNoorMadani,//24
                SpkIntRKNoorMadani,//25
                SpkIntRKNoorMadani,//26
                SpkIntRKNoorMadani,//27
                SpkIntRKNoorMadani,//28
                SpkIntRKNoorMadani,//29
                SpkIntRKNoorMadani,//30
                SpkIntRKNoorMadani,//31
                SpkIntRKNoorMadani,//32
                SpkIntRKNoorMadani,//33
                SpkIntRKNoorMadani,//34
                SpkIntRKNoorMadani,//35
                SpkIntRKNoorMadani,//36
                SpkIntRKNoorMadani,//37
                SpkIntRKNoorMadani,//38
                SpkIntRKNoorMadani,//39
                SpkIntRKNoorMadani,//40
                SpkIntRKNoorMadani,//41
                SpkIntRKNoorMadani,//42
                SpkIntRKNoorMadani,//43
                SpkIntRKNoorMadani,//44
                SpkIntRKNoorMadani,//45
                SpkIntRKNoorMadani,//46
                SpkIntMuftiUmar,//47
                SpkIntMuftiUmar//48
        };

        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < ArrayGeneralName.length; i++) {
                if (CheckFileNameExistenceAudioGeneral(TABLE_AUDIO_GENERAL, AUD_GEN_NAME, ArrayGeneralName[i])) {
                    Log.d(TAG, "trying to rename file" + ArrayGeneralName[i]);
                    cv.put(AUD_GEN_NAME, changeFileNameAudioGeneral(ArrayGeneralName[i]));
                } else {
                    cv.put(AUD_GEN_NAME, ArrayGeneralName[i]);
                }
                //cv.put(AUD_GEN_NAME, ArrayGeneralName[i]);
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

    // Method If the name of the file already exist
    // is the name already exist then in concadenates (+1) with the name
    public boolean CheckFileNameExistenceAudioGeneral(String TableName,
                                                      String dbfield,
                                                      String fieldValue) {

        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //Method to Rename the file Name if NAme Already existing
    public String changeFileNameAudioGeneral(String fileName) {

        String newName = fileName;

        do newName = newName + "(" + 1 + ")";
        while (CheckFileNameAlreadyExistinginAudiList(TABLE_AUDIO_GENERAL, AUD_GEN_NAME, newName));
        Log.e(TAG, "Renamed to " + newName);
        return newName;
    }

//____________________/PDF table SQL Insert statement__________/PDF table SQL Insert statement____________________________________________________________________________________________________________________________

    //PDF table SQL Insert statement

    //NOTE * The Names of Book Cannot be same
    //don't add copyrighted books

    // please note: The file name inserted in the table should exactly be
    // same as the file name in the Cloud drive to avoid confusion
    //I don't know why I wrote the above comment I have to test changing names

    public static final String BookCat_CREED = "Creed";
    public static final String BookCat_DUA = "Dua";
    public static final String BookCat_PRAYER = "Prayer";
    public static final String BookCat_GENERAL = "General";
    public static final String BookCat_QURAN = "Quran";
    public static final String BookCat_SISTERS = "Sisters";
    public static final String BookCat_NEW_MUSLIM = "New-Muslim";

    public void insertPdfTable() {
        String query = "INSERT INTO " + TABLE_PDF + "(" + PDF_NAME + ", " + PDF_CATEGORY + "," + PDF_LINK + ") VALUES " +
                "('The Three Fundamental Principle -Imam Abdul Wahab'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WFE4d1JXcEl2X1E')," +
                "('The Correct Belief -Shayk ibn Baaz'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TUFVZklVOUVoNlE')," +
                "('Aqeeda-at-Tahaawiyyah'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4V2w4dzNwYzBUUkU')," +
                "('Knowing Allah Quotes from Quran -Dr Saleh as Saleh'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4em0za0pxNTZUSmc')," +
                "('The Testimony of Faith Shahaada -Dr Saleh as Saleh'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Yl9kVmlPYlQwZDA')," +
                "('The Messengers -Dr Saleh as Saleh'," + "'" + BookCat_CREED + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y1N1cVo5eThaNWs')," +
                //
                "('Authentic Remembrance After Salah -Abu Talhah Dawud Burbank'," + "'" + BookCat_DUA + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4VGoxNWFyY1hYa28')," +
                "('Authentic Supplication for Morning and Evening -Abu Talhah Dawud Burbank'," + "'" + BookCat_DUA + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bmJDdUFtSjJ1cnc')," +
                //
                "('Prophet Prayer -al Albani'," + "'" + BookCat_PRAYER + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4SGpBV2dCV2RzUFU')," +
                "('The Condition and Pillars of Salat -Imam Abdul Wahab'," + "'" + BookCat_PRAYER + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4V3RxUlI4d21Lcms')," +
                "('How to Make Wudu Ablution -Dr Saleh as Saleh'," + "'" + BookCat_PRAYER + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4aE5VcjRVWk1pQTg')," +
                //
                "('Explanation of Four Ahaadeeth from Imam Bukhari Adabul-Mufrad -Dawud Burbank'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WnU5LU1LVHdQVmM')," +
                "('Laying The Foundation for Seeking Knowledge -Shaykh ibn Bazmool'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4c0xLRnkzN3FTRHc')," +
                "('Guarding the Tongue -Imaam An-Nawawee'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WV9rVjJNT0V2bDg')," +
                "('Sabr Patience Perseverance'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4X2dFblY0VEprbDA')," +
                "('Belief in the Angels -Shaykh Salih Al Fawzan'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y2VBR3JuckNlYk0')," +
                "('How to Perform the Rituals of Hajj -ibn Uthaymeen'," + "'" + BookCat_GENERAL + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dHhaLTJWT1p3QjQ')," +
                //
                "('Concise Interpretation of Surah Fatiha -Dr Saleh'," + "'" + BookCat_QURAN + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WjZ4ZC1iNzhLMEk')," +
                "('Allah is An Nuur -Dr Saleh'," + "'" + BookCat_QURAN + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4dDZrbVo1Uzh1dG8')," +
                "('Tafseer Sura Naba -Based on ibn Uthaymeen'," + "'" + BookCat_QURAN + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Vi16SWxhTXNMZ28')," +
                "('Tafseer Ayathul Kursi -Dr Saleh as Saleh'," + "'" + BookCat_QURAN + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4eFBVenZxQ0ZzLVU')," +
                "('Quran English Muhsin khan and Hilali'," + "'" + BookCat_QURAN + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4R3dycG11ZkY2SkU')," +
                //
                "('Advice to Sisters -Shayk Salih Al Fawzan'," + "'" + BookCat_SISTERS + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Y1hKTk9lQnFSanc')," +
                "('The Awrah of Women -Dr Saleh as Saleh'," + "'" + BookCat_SISTERS + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4ZFBEY3JXaDUzazg')," +
                //
                "('The Testimony of Faith Shahaada -Dr Saleh as Saleh'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Yl9kVmlPYlQwZDA')," +
                "('The Three Fundamental Principle -Imam Abdul Wahab'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4WFE4d1JXcEl2X1E')," +
                "('The Correct Belief -Shayk ibn Baaz'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TUFVZklVOUVoNlE')," +
                "('The Beleif in Allah -ibn Uthaymeen (en-Dr Saleh as Saleh)'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4MkxXUVNseGlOQmc')," +
                "('Purpose of Creation -Dr Saleh as Saleh'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4Uy1mS1UyR0pSMG8')," +
                "('Sincere Advise to every Christian -Dr Saleh as Saleh'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4bFlqY29vc2NYcmc')," +
                "('The Search for Truth -Dr Saleh as Saleh'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4TzNncXM3cjBHN0k')," +
                "('Creation vs Evolution -Dr Saleh as Saleh'," + "'" + BookCat_NEW_MUSLIM + "'" + ",'https://drive.google.com/uc?export=download&id=0B-qLTPYff2I4YlJzNHJGUmk3dUU')";

        //main insertion is below
        try {
            db.execSQL(query);
            Log.e(TAG, "PDF table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "SQLException error pdf Insertion", e);
        }
    }


    //_____________________________________________________________________________________________________________________
    //_____________________________________________________________________________________________________________________
//***********************  Video Table Insert  ************  Video Table Insert  ******************************************************************************************************************************
    //VIDEO Name table SQL Insert statement
    public void insertVideoTopicTable() {

        String VideoPlaylistIDLinkArray[] = {
                "PLPqbjyewIywP2Ti-m2SAAswBgWBI8GH5n",//1 Kitab ut tawheed
                "PLPqbjyewIywNhxwUqDksh2fmw9CqIuBRz",//2 Jumma Kuthba
                "PLPqbjyewIywPHBVHJKxqcANexl_epO0PN",//3 Ramadan Duroos 2014 Anees ur Rahman
                "PLPqbjyewIywPDhCZUUQ968GqYFQbMjsbF",//4 Ramadan Duroos 2015 Anees ur Rahman
                "PLPqbjyewIywMpsT22P2PHzPFxGDTkTz5G",//5 Mundru Adipadaigal
                "PLPqbjyewIywMZUoak3WKud37PwiMNaDxR",//6 HGWC Conference 2014 English
                "PLPqbjyewIywMZUoak3WKud37PwiMNaDxR",//7 HGWC Conference 2014 Tamil
                "PLPqbjyewIywMZUoak3WKud37PwiMNaDxR",//8 HGWC Conference 2014 Urdu
                "PLPqbjyewIywM_F37hwYtsnfHvB-vO1LBg",//9 Aqeeda R K Noor Madani
                "PLPqbjyewIywN5xiHD6dMnq9SPBvU5YPGD",//10 Weekly Lectures (URdu) Multiple
                "PLPqbjyewIywN8UlX0eEHRBSWa5Op8BFp3",// 11 Weekly Aqeeda Class (URDU) Sheik Owais Omeri
                "PLPqbjyewIywN8UlX0eEHRBSWa5Op8BFp3",// 12 Weekly Aqeeda Class (URDU) Sh Mujeeb Rahman Makki
                "PLPqbjyewIywN1-2kX8Hl-jdFabZFCtSlh",// 13 Seven Under The Shade of Arsh (Urdu) Sheik Owais Omeri
                "PLPqbjyewIywP7u0iqFbVpXnII_PULw2VI",// 14 Da'wah Training Program - Level 2 (Tamil) Mufti Umar
                "PLPqbjyewIywP7u0iqFbVpXnII_PULw2VI",// 15 Da'wah Training Program - Level 2 (Tamil) Multiple
                "PLPqbjyewIywNieK1vrd7Lh_sUg69GKaEU",// 16 Taqwiyyatul Eemaan (Urdu) Shaikh Anees Rahman
                "PLPqbjyewIywOFTNjOnJPupuqlKWy6F9Ue",// 17 jum'ah khutuba - masjid charminar chennai (Urdu) Sh Anees Rahman
                "PLPqbjyewIywMJKGnZbS7IwRMfxzuHJpek",// 18 Dars e Hadeeth - Buloogh ul Maraam -(Urdu) Sh Anees Rahman
                "PLPqbjyewIywO3NyppA1RhvHcdxo_Gq4NV",// 19 Humanity Conference (Tamil) Multiple
                "PLPqbjyewIywO3NyppA1RhvHcdxo_Gq4NV",// 20 Humanity Conference (Urdu) Multiple
                "PLPqbjyewIywO48Ql-bLwd0wO3X6OUoeLW" // 21 masa'il e tijarath - (Urdu) Shk anis
        };

        int VideoPlaylistLanidArray[] = {
                LidEngKey, //1
                LidUrdKey, //2
                LidUrdKey, //3
                LidUrdKey, //4
                LidTamKey, //5
                LidEngKey, //6
                LidTamKey, //7
                LidUrdKey, //8
                LidUrdKey, //9
                LidUrdKey, //10
                LidUrdKey, //11
                LidUrdKey, //12
                LidUrdKey, //13
                LidTamKey, //14
                LidTamKey, //15
                LidUrdKey, //16
                LidUrdKey, //17
                LidUrdKey, //18
                LidTamKey, //19
                LidUrdKey, //20
                LidUrdKey  //21
        };

        int VideoPlaylistSpkidArray[] = {
                SpkIntAbdusSalaamMadani,//1
                SpkIntAneesRahman,//2
                SpkIntAneesRahman,//3
                SpkIntAneesRahman,//4
                SpkIntAbdulMajeed,//5
                SpkIntMultiple,//6
                SpkIntMultiple,//7
                SpkIntMultiple,//8
                SpkIntRKNoorMadani,//9
                SpkIntMultiple,//10
                SpkIntShOwaisOmeri,//11
                SpkIntShMujeeburRahman,//12
                SpkIntShOwaisOmeri,//13
                SpkIntMuftiUmar,//14
                SpkIntMultiple,//15
                SpkIntAneesRahman,//16
                SpkIntAneesRahman,//17
                SpkIntAneesRahman,//18
                SpkIntMultiple,//19
                SpkIntMultiple,//20
                SpkIntAneesRahman//21
        };

        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < VideoPlaylistIDLinkArray.length; i++) {
                cv.put(VIDEO_TOPIC_PLAYLIST_LINK, VideoPlaylistIDLinkArray[i]);
                cv.put(VIDEO_TOPIC_LAN_ID, VideoPlaylistLanidArray[i]);
                cv.put(VIDEO_TOPIC_SPK_ID, VideoPlaylistSpkidArray[i]);
                db.insert(TABLE_VIDEO_TOPIC, null, cv);
            }
            Log.e(TAG, "VIDEO Topic table inserted");
        } catch (SQLException e) {
            Log.e(TAG, "Sql Exception Video Topic Insertion\n" + e);
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


    //OLD Video SQL QUERIES
    public Cursor getAllRowsbyLang(String tablename, String[] allKeys, String bookCat) {
        String where = PDF_CATEGORY + " = '" + bookCat + "'";
        Cursor c = db.query(true, tablename, allKeys, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
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

    public Cursor selectSpeakerNameWithSpkIDs(String spkid) {

        String tableName = TABLE_SPEAKER;
        String[] selection = {SPK_ID, SPK_NAMES};
        String where = SPK_ID + "=" + spkid;

        Cursor c = db.query(true, tableName, selection, where, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectVideoSpeaker(String lanid) {
        String tableName = TABLE_VIDEO_TOPIC;
        String[] selection = {VIDEO_TOPIC_SPK_ID};
        String where = VIDEO_TOPIC_LAN_ID + "=" + lanid;

        Cursor c = db.query(true, tableName, selection, where, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectPlayListIDsWithSpeakerIds(String lanId, String spkId) {

        String tableName = TABLE_VIDEO_TOPIC;
        String selection[] = {VIDEO_TOPIC_PLAYLIST_LINK};
        String where = VIDEO_TOPIC_LAN_ID + "=" + lanId + " AND " + VIDEO_TOPIC_SPK_ID + "=" + spkId;

        Cursor c = db.query(true, tableName, selection, where, null, null, null, null, null);
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
            _db.execSQL(CREATE_VIDEO_TOPIC_NAME_SQL);
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
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO_TOPIC);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_PDF);


            // Recreate new database:
            onCreate(_db);
        }
    }
}

