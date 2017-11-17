package com.example.android.r1512602_03_ohjiun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteActivity extends AppCompatActivity {
    static final String FILENAME = "contact.txt";
    EditText etName, etPhone, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        etName = (EditText) findViewById(R.id.edit_name);
        etPhone = (EditText) findViewById(R.id.edit_phone);
        etAddress = (EditText) findViewById(R.id.edit_address);

        findViewById(R.id.write_button).setOnClickListener(
                new Button.OnClickListener() {
                        public void onClick(View v) {
                            writeInfo();
                            clearText();
                        }
                }
        );

        findViewById(R.id.goBack_button).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

    }

    private void writeInfo () {

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            String name, phone, address, line;
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            address = etAddress.getText().toString();
            line = name+" "+phone+" "+address;
            fos.write(line.getBytes());
            fos.close();
        } catch (IOException e) {
        }

        /*
        try {
            String name, phone, address;
            PrintWriter printWriter = new PrintWriter(new FileWriter(FILENAME, true));
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            address = etAddress.getText().toString();
            printWriter.println(name+" "+phone+" "+address);
            printWriter.close();
        } catch (IOException e) {
            Log.v("쓰기", "에러");
        } */
    }

    private void clearText() {
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
    }
}
