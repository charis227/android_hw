package com.example.android.r1512602_03_ohjiun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        try {
            String name, phone, address;
            PrintWriter printWriter = new PrintWriter(new FileWriter(FILENAME, true));
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            address = etAddress.getText().toString();
            printWriter.println(name+" "+phone+" "+address);
            printWriter.close();
        } catch (IOException e) {

        }

/*        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            /////////
            fileInputStream.close();
        }
        catch (IOException e) {

        }*/
    }
}
