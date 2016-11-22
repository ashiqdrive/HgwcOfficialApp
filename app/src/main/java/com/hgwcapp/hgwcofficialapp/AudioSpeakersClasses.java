package com.hgwcapp.hgwcofficialapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.databaseall.DataBaseAdapterC;
import com.recyclerp.all.HiddenVisibleDatatype;
import com.recyclerp.all.RecyclerAdaptertwoItemsHV;
import com.recyclerp.all.RecyclerItemClickListener;

import java.util.ArrayList;

//this is the speaker list calss

public class AudioSpeakersClasses extends AppCompatActivity {

    private static final String TAG = "TagAudioSpeakerClasses";
    DataBaseAdapterC DACac;
    String lanIdMain; //Languane id

    TextView tvSpkrListAudName;

    ArrayList<HiddenVisibleDatatype> SpkernameList = new ArrayList<HiddenVisibleDatatype>();
    RecyclerView recyclerViewSpeaker;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdaptertwoItemsHV adaptertwoItemsHV;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_speakers_classes);

        tvSpkrListAudName = (TextView) findViewById(R.id.tvSpkrListAudName);
        recyclerViewSpeaker = (RecyclerView) findViewById(R.id.rvSpkClasses);

        try {
            openforAudio();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }


        Bundle languageData = getIntent().getExtras();
        if (languageData == null) {
            return;
        }

        lanIdMain = languageData.getString("LANGUAGE_ID", "1");
        String lanName = languageData.getString("LANGUAGE_NAME", "Null");
        tvSpkrListAudName.setText(lanName);

        selectingSpeakrs(lanIdMain);

        creatingRecyclerView();

        recyclerViewSpeaker.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerViewSpeaker, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

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

    private void openforAudio() {
        DACac = new DataBaseAdapterC(this);
        DACac.open();
        Log.i(TAG, TAG + " open method Sucessfull");
    }

    public void selectingSpeakrs(String lanid) {

        String rowname=DACac.SPK_IF_CLASS;
        Cursor cursor = DACac.selectSpeakerNamesforClassOrGeneralList(lanid,rowname);

        for (int i = 0; i < cursor.getCount(); i++) {
            String hiddenId = cursor.getString(cursor.getColumnIndex(DACac.SPK_ID));
            String visileName = cursor.getString(cursor.getColumnIndex(DACac.SPK_NAMES));
            //String a=cursor.getString(cursor.getColumnIndex(DACac.SPK_NAMES));
            HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenId, visileName);
            SpkernameList.add(hvDT);
            cursor.moveToNext();
        }
        //Toast.makeText(getBaseContext(), "Sql selection sucessful\n Rows selected =" + cursor.getCount(), Toast.LENGTH_SHORT).show();

    }

    public void creatingRecyclerView() {

        adaptertwoItemsHV = new RecyclerAdaptertwoItemsHV(SpkernameList, this);
        recyclerViewSpeaker.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewSpeaker.setLayoutManager(layoutManager);
        recyclerViewSpeaker.setAdapter(adaptertwoItemsHV);
    }

    public void listItemClickMethod(String lid, String spkid, String spkName) {
        Intent intent = new Intent(this, AudioClassTopics.class);
        intent.putExtra("LANGUAGE_ID", lid);
        intent.putExtra("SPEAKER_ID", spkid);
        intent.putExtra("SPEAKER_NAME", spkName);

        startActivity(intent);
    }


}
