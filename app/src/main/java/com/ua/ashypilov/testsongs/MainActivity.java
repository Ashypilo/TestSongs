package com.ua.ashypilov.testsongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

import com.ua.ashypilov.testsongs.Adapter.SongsAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> listSongs = new ArrayList<>();
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSongs();
        initRecycleView();
        initVolume();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        SongsAdapter adapter = new SongsAdapter(this, listSongs);
        recyclerView.setAdapter(adapter);
    }

    private void initSongs() {
        listSongs.add(new Song("AC DC", "Highway To Hell", this, R.raw.ac_dc_highway_to_hell, R.drawable.ac_dc));
        listSongs.add(new Song("AC DC", "Thunderstruck", this, R.raw.ac_dc_thunderstruck, R.drawable.ac_dc));
        listSongs.add(new Song("Imagine Dragons", "Believer", this, R.raw.imagine_dragons_believer, R.drawable.imagine_dragons));
        listSongs.add(new Song("Imagine Dragons", "Natural", this, R.raw.imagine_dragons_natural, R.drawable.imagine_dragons));
        listSongs.add(new Song("Imagine Dragons", "Thunder", this, R.raw.imagine_dragons_thunder, R.drawable.imagine_dragons));
        listSongs.add(new Song("Sabaton", "Into The Fire", this, R.raw.sabaton_into_the_fire, R.drawable.sabaton));
        listSongs.add(new Song("One Republic", "Apologize", this, R.raw.timbaland_feat_one_republic_apologize, R.drawable.one_republic));
        listSongs.add(new Song("One Republic", "I Lived", this, R.raw.one_republic_i_lived, R.drawable.one_republic));
        listSongs.add(new Song("One Republic", "Counting Stars", this, R.raw.one_republic_counting_stars, R.drawable.one_republic));
        listSongs.add(new Song("Metallica", "Nothing Else Matters", this, R.raw.metallica_nothing_else_matters, R.drawable.metallica));
        Collections.shuffle(listSongs);
    }
    private void initVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void stopSong() {
        for (int i = 0; i < listSongs.size(); i++) {
            if (listSongs.get(i).isPlay()) {
                listSongs.get(i).getMediaPlayer().pause();
            }
        }
    }

    private void restartSong() {
        for (int i = 0; i < listSongs.size(); i++) {
            if (listSongs.get(i).isPlay()) {
                listSongs.get(i).getMediaPlayer().start();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        restartSong();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSong();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSong();
    }
}