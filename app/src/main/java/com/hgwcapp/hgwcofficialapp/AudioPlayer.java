package com.hgwcapp.hgwcofficialapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AudioPlayer extends AppCompatActivity implements Runnable, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;
    private MediaPlayer mp;
    private TextView tvAudioNameAudPlay;

    String urlToBePrepared;

    private ImageView ivstartMedia;
    private ImageView ivstopMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        tvAudioNameAudPlay = (TextView) findViewById(R.id.tvAudioNameAudPlay);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setEnabled(false);

        ivstartMedia = (ImageView) findViewById(R.id.ivPlayAudPlay);
        ivstopMedia = (ImageView) findViewById(R.id.ivStopAudPlay);
        ivstartMedia.setOnClickListener(this);
        ivstopMedia.setOnClickListener(this);

        //receiving bundle to get url and name
        Bundle recieveAudio = getIntent().getExtras();
        if (recieveAudio == null) {
            return;
        }
        String audioUrl = recieveAudio.getString("audioUrl");
        String audioName = recieveAudio.getString("audioName");
        tvAudioNameAudPlay.setText(audioName);
        urlToBePrepared = audioUrl;

        /*try{
            startingPlay();
        }catch (Exception e){
        }*/
    }

    public void run() {
        int currentPosition = mp.getCurrentPosition();
        int total = mp.getDuration();

        while (mp != null && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mp.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }

    public void onClick(View v) {
        if (v.equals(ivstartMedia)) {
            if (mp == null) {
                mp = MediaPlayer.create(getApplicationContext(), Uri.parse(urlToBePrepared));
                seekBar.setEnabled(true);
            }
            if (mp.isPlaying()) {
                mp.pause();
                ivstartMedia.setImageResource(R.drawable.playiconblack72);
            } else {
                mp.start();
                ivstartMedia.setImageResource(R.drawable.pauseiconblack72);
                seekBar.setMax(mp.getDuration());
                new Thread(this).start();
            }
        }

        if (v.equals(ivstopMedia) && mp != null) {
            if (mp.isPlaying() || mp.getDuration() > 0) {
                mp.stop();
                mp = null;
                ivstartMedia.setImageResource(R.drawable.playiconblack72);
                seekBar.setProgress(0);
            }
        }

    }

    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        try {
            if (mp.isPlaying() || mp != null) {
                if (fromUser)
                    mp.seekTo(progress);
            } else if (mp == null) {
                Toast.makeText(getApplicationContext(), "Media is not running",
                        Toast.LENGTH_SHORT).show();
                seekBar.setProgress(0);
            }
        } catch (Exception e) {
            Log.e("seek bar", "" + e);
            seekBar.setEnabled(false);

        }
    }

    @Override
    public void onBackPressed() {
        try {
            mp.stop();
            super.onBackPressed();
        } catch (NullPointerException n) {
            super.onBackPressed();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

}
