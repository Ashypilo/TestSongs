package com.ua.ashypilov.testsongs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bartbergmans.gradientbutton.views.GradientButton;
import com.captaindroid.tvg.Tvg;
import com.jackandphantom.paletteshadowview.PaletteShadowView;
import com.prush.bndrsntchtimer.BndrsntchTimer;
import com.ua.ashypilov.testsongs.MainActivity;
import com.ua.ashypilov.testsongs.PlaySong;
import com.ua.ashypilov.testsongs.R;
import com.ua.ashypilov.testsongs.Song;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>{
 
    private final LayoutInflater inflater;
    private final ArrayList<Song> songs;
    private MainActivity activity;
    private BndrsntchTimer progress;
 
    public SongsAdapter(MainActivity activity, ArrayList<Song> songs) {
        this.songs = songs;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
    }
    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 
        View view = inflater.inflate(R.layout.card_song, parent, false);
        progress = activity.findViewById(R.id.timer);
        return new ViewHolder(view);
    }
 
    @Override
    public void onBindViewHolder(SongsAdapter.ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewSong.setText(song.getTextSong());
        holder.textViewGroup.setText(song.getTextGroup());
        holder.imageViewSong.setImageResource(song.getImage());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickPlay(position);
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return songs.size();
    }
 
    public static class ViewHolder extends RecyclerView.ViewHolder {
        PaletteShadowView imageViewSong;
        TextView textViewGroup, textViewSong;
        GradientButton play;
        ViewHolder(View view){
            super(view);
            imageViewSong = view.findViewById(R.id.imageSong);
            textViewGroup = view.findViewById(R.id.textGroup);
            textViewSong = view.findViewById(R.id.textSong);
            play = view.findViewById(R.id.buttonPlay);
            Tvg.change(textViewSong, Color.parseColor("#FFFFFFFF"),  Color.parseColor("#FF000000"));
            Tvg.change(textViewGroup, Color.parseColor("#FFFFFFFF"),  Color.parseColor("#FF000000"));
        }
    }

    private void clickPlay(int position) {
        PlaySong playSong = new PlaySong(progress);
        String numberString = activity.getSharedPreferences("Number_Song", Context.MODE_PRIVATE).getString("Number_Song","0");
        int number = Integer.parseInt(numberString);
        if (number != position) {
            Log.i("checkSong", number + "-" + position);
            songs.get(number).getMediaPlayer().stop();
            songs.get(number).setMediaPlayer(null);
            songs.get(number).setPlay(false);
        }
        if (!songs.get(position).isPlay()) {
            songs.get(position).createMediaPlayer();
            songs.get(position).getMediaPlayer().start();
            songs.get(position).setPlay(true);
            playSong.show(activity, songs.get(position));
            activity.getSharedPreferences("Number_Song", 0).edit().putString("Number_Song", String.valueOf(position)).apply();
        }
    }
}