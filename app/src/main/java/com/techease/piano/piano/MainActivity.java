package com.techease.piano.piano;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNext, tvPrevious, tvPrache;
    private SeekBar sbVolume;
    private MediaPlayer mediaPlayer;
    private int mp_id, current_volume;
    private int[] piano_sound = {R.raw.track1, R.raw.track2, R.raw.track3, R.raw.track4, R.raw.track5, R.raw.track6, R.raw.track7, R.raw.track8, R.raw.track9, R.raw.track10};
    private AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNext = findViewById(R.id.tv_next);
        tvPrevious = findViewById(R.id.tv_previous);
        tvPrache = findViewById(R.id.tv_prache);
        sbVolume = findViewById(R.id.seekBar);

        tvNext.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);


        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        this.setVolumeControlStream(audio.STREAM_MUSIC);

        current_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbVolume.setProgress(current_volume);


        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                int i = 0;
                i = Utilities.getSharedPreferences(this).getInt("inc", 0);
                int incrementValue = i + 1;
                Utilities.putValueInEditor(this).putInt("inc", incrementValue).commit();

                if (incrementValue > 9) {
                    Utilities.putValueInEditor(this).putInt("inc", 0).commit();
                    incrementValue = 0;
                }
                mp_id = piano_sound[incrementValue];
                MediaPlayerClass(mp_id);
                break;
            case R.id.tv_previous:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                int iPrevious = 0;
                iPrevious = Utilities.getSharedPreferences(this).getInt("inc", 0);
                int incrementValuePrevious = iPrevious - 1;
                Utilities.putValueInEditor(this).putInt("inc", incrementValuePrevious).commit();

                if (incrementValuePrevious < 0) {
                    Utilities.putValueInEditor(this).putInt("inc", 9).commit();
                    incrementValuePrevious = 9;
                }
                mp_id = piano_sound[incrementValuePrevious];
                MediaPlayerClass(mp_id);
                break;
        }

    }

    private void MediaPlayerClass(int mp_id) {
        mediaPlayer = MediaPlayer.create(MainActivity.this, mp_id);
        mediaPlayer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FX_KEY_CLICK);

                current_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                int i = current_volume + 1;
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                sbVolume.setProgress(i);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FX_KEY_CLICK);

                current_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                int ii = current_volume - 1;
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, ii, 0);
                sbVolume.setProgress(ii);
                return true;
            default:
                // return false;
                // Update based on @Rene comment below:
                return super.onKeyDown(keyCode, event);
        }
    }
}
