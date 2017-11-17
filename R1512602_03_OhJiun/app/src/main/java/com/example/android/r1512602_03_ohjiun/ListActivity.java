package com.example.android.r1512602_03_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
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

/*        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME));
            while (true) {
                String listContent = bufferedReader.readLine();
                if (listContent == null)
                    break;
                list.add(listContent);
            }
            bufferedReader.close();
        } catch (IOException e) {

        } */
    }

    private void printList() {
        final String FILENAME = "contact.txt";

        TextView tvList = (TextView) findViewById(R.id.list_view);

        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            tvList.setText(new String(buffer));
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            Log.v("읽기", "파일 없음");
        }
        catch (IOException e) {
            Log.v("읽기", "에러");
        }
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
