package com.example.android.r1512602_03_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.select_button).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        selectActivity();
                    }
                }
        );
    }

    protected void selectActivity() {
        RadioButton rbWrite = (RadioButton) findViewById(R.id.radio_write);
        RadioButton rbList = (RadioButton) findViewById(R.id.radio_list);

        Intent intent;

        if (rbWrite.isChecked()) {
            intent = new Intent(this, WriteActivity.class);
            startActivity(intent);
        }
        else if (rbList.isChecked()) {
            intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        }
    }
}
