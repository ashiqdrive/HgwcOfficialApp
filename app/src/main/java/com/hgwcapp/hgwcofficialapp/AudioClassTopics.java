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
import com.recyclerp.all.*;

import java.util.ArrayList;

public class AudioClassTopics extends AppCompatActivity {

    private static final String TAG = "Tag AudioClassTopics";
    DataBaseAdapterC DACct;

    String lanIdMain;
    String spkIDMain;
    String spkNameMain;
    TextView speakerHeaderName;

    ArrayList<HiddenVisibleDatatype> ListclassTopics = new ArrayList<HiddenVisibleDatatype>();
    RecyclerView recyclerViewClassTopics;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdaptertwoItemsHV adaptertwoItemsHV;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_class_topics);

        recyclerViewClassTopics = (RecyclerView) findViewById(R.id.rvClassTopics);
        speakerHeaderName=(TextView) findViewById(R.id.tvTopicSpeakerHeader);

        try {
            openMethod();
        } catch (Exception e) {
            Log.e(TAG, "Error in open DB\n" + e);
        }

        Bundle previousClassData = getIntent().getExtras();
        if (previousClassData == null) {
            return;
        }

        lanIdMain = previousClassData.getString("LANGUAGE_ID", "1");
        spkIDMain=previousClassData.getString("SPEAKER_ID", "Null");
        spkNameMain=previousClassData.getString("SPEAKER_NAME", "Null");

        speakerHeaderName.setText(spkNameMain);

        selectingQueryMethod(lanIdMain,spkIDMain);
        creatingRecyclerView();

        recyclerViewClassTopics.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerViewClassTopics, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        TextView hidtv=(TextView) view.findViewById(R.id.twoItemsHiddentv);
                        TextView vtv=(TextView) view.findViewById(R.id.twoItemsVisibletv);

                        String hstClassId=hidtv.getText().toString();
                        String vstClassName=vtv.getText().toString();

                        listItemClickMethod(hstClassId,vstClassName);
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

    private void openMethod() {
        DACct = new DataBaseAdapterC(this);
        DACct.open();
        Log.i(TAG, TAG + " open method Sucessfull");
    }


    public void selectingQueryMethod(String lanid,String spkid) {

        Cursor cursor = DACct.selectTopicNamesforClassTopics(lanid,spkid);

        for (int i = 0; i < cursor.getCount(); i++) {
            String hiddenId = cursor.getString(cursor.getColumnIndex(DACct.AUD_Topic_ID));
            String visileName = cursor.getString(cursor.getColumnIndex(DACct.AUD_Topic_NAME));
            HiddenVisibleDatatype hvDT = new HiddenVisibleDatatype(hiddenId, visileName);
            ListclassTopics.add(hvDT);
            cursor.moveToNext();
        }
    }

    public void creatingRecyclerView() {

        adaptertwoItemsHV = new RecyclerAdaptertwoItemsHV(ListclassTopics, this);
        recyclerViewClassTopics.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewClassTopics.setLayoutManager(layoutManager);
        recyclerViewClassTopics.setAdapter(adaptertwoItemsHV);
    }

    public void listItemClickMethod(String classid,String className) {
        Intent intent = new Intent(this, AudioList.class);
        intent.putExtra("CLASS_ID", classid);
        intent.putExtra("CLASS_NAME", className);
        intent.putExtra("PREVIOUS_CLASS_NAME", "classtopics");
        startActivity(intent);
    }
}
