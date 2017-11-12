package com.example.android.s1512602_03_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("음악 장르");

        findViewById(R.id.pop).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displaySongs(v);
                    }
                }
        );

        findViewById(R.id.classic).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displaySongs(v);
                    }
                }
        );

        findViewById(R.id.newage).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displaySongs(v);
                    }
                }
        );

    }

    public void displaySongs(View v) {
        int id;

        id = v.getId();
        TextView tv = (TextView) v.findViewById(id);
        String tag = (String) tv.getTag();

        Intent intent = new Intent(this, GenreActivity.class);
        intent.putExtra("intent_tag", tag);
        startActivity(intent);
    }
}



