package com.example.android.s1512602_03_ohjiun;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static android.R.attr.id;
import static android.R.attr.label;
import static android.R.attr.tag;

public class SongActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        String songSrc = intent.getStringExtra("songTag");
        String genre = intent.getStringExtra("genre");
        Resources res = getResources();
        int id_title = res.getIdentifier(songSrc+"title", "string", getPackageName());
        String title = res.getString(id_title);
        setTitle(genre+"-"+title);

        TextView songTitle = (TextView) findViewById(R.id.title);
        TextView songSinger = (TextView) findViewById(R.id.singer);

        songTitle.setText("제목: "+title);

        int id_singer = res.getIdentifier(songSrc+"singer", "string", getPackageName());
        String singer = res.getString(id_singer);
        songSinger.setText("가수: "+singer);

        int songSrcId = res.getIdentifier(songSrc, "string", getPackageName());
        songSrc = res.getString(songSrcId);

        int id_audio = res.getIdentifier(songSrc, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, id_audio);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

    }

    public void closeSongDetail (View v) {
        mediaPlayer.stop();
        mediaPlayer.release();
        finish();
    }
}
