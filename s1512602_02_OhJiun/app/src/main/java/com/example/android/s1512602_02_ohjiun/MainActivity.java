package com.example.android.s1512602_02_ohjiun;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("여행사진 목록");

        findViewById(R.id.jeonju).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displayPicture(v);
                    }
                }
        );

        findViewById(R.id.jeju).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displayPicture(v);
                    }
                }
        );

        findViewById(R.id.busan).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        displayPicture(v);
                    }
                }
        );
   }

  public void displayPicture(View v) {
        int id;

      id = v.getId();
      TextView tv = (TextView) v.findViewById(id);
//        LinearLayout layout = (LinearLayout) v.findViewById(id);
        String tag = (String) tv.getTag();

        Intent intent = new Intent(this, PictureActivity.class);
        intent.putExtra("intent_tag", tag);
        startActivity(intent);


    }

}
