package com.example.android.r1512602_03_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    final String FILENAME = "contact.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        printList();

        findViewById(R.id.goBack_button2).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goBack();
                    }
                }
        );

    }

    private void printList() {
        TextView tvList = (TextView) findViewById(R.id.list_view);
        String result="";
        try {
            File file = getFileStreamPath(FILENAME);
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String listContent = bufferedReader.readLine();
                    if (listContent == null)
                        break;
                    result = result + listContent + "\n";
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            Log.v("읽기", "파일 없음");
        }
        tvList.setText(result);
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
