package com.example.android.s1512602_03_ohjiun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.label;

public class GenreActivity extends AppCompatActivity {
    private String genreName;
    private String songTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        TextView song1 = (TextView) findViewById(R.id.song1);
        TextView song2 = (TextView) findViewById(R.id.song2);
        TextView song3 = (TextView) findViewById(R.id.song3);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("intent_tag");
        Resources res = getResources();

        int id_label = res.getIdentifier("genre"+tag, "string", getPackageName());
        genreName = res.getString(id_label);
        setTitle(genreName);

        songTag = "genre"+tag;

        int id_songstring1 = res.getIdentifier("genre"+tag+"01title", "string", getPackageName());
        String songstring1 = res.getString(id_songstring1);
        song1.setText(songstring1);

        int id_songstring2 = res.getIdentifier("genre"+tag+"02title", "string", getPackageName());
        String songstring2 = res.getString(id_songstring2);
        song2.setText(songstring2);

        int id_songstring3 = res.getIdentifier("genre"+tag+"03title", "string", getPackageName());
        String songstring3 = res.getString(id_songstring3);
        song3.setText(songstring3);
    }

    public void closeSongs(View v) {
        finish();
    }

    public void displaySongDetail(View v) {
        int id;

        id = v.getId();
        TextView tv = (TextView) v.findViewById(id);
        String tag = (String) tv.getTag();

        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("songTag", songTag+tag);
        intent.putExtra("genre", genreName);
        startActivity(intent);
    }

}