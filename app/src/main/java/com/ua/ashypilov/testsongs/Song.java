package com.ua.ashypilov.testsongs;

import android.media.MediaPlayer;

public class Song {

    private String textGroup;
    private String textSong;
    private MediaPlayer mediaPlayer;
    private int image;
    private int songMedia;
    private boolean play = false;
    private long lengthSong = 0;
    private MainActivity activity;

    public Song(String group, String song, MainActivity mainActivity, int songMedia, int image) {
        this.activity = mainActivity;
        this.textGroup = group;
        this.textSong = song;
        this.image = image;
        this.songMedia = songMedia;
        createMediaPlayer();
    }

    public void createMediaPlayer() {
        mediaPlayer = MediaPlayer.create(activity, songMedia);
        lengthSong = mediaPlayer.getDuration();
    }


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getImage() {
        return image;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public long getLengthSong() {
        return lengthSong;
    }

    public String getTextGroup() {
        return textGroup;
    }

    public String getTextSong() {
        return textSong;
    }

    public int getSongMedia() {
        return songMedia;
    }

    public MainActivity getActivity() {
        return activity;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
