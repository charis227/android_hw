package com.example.android.s1512602_07_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private RadioButton univ, aladin, naver;
    private EditText manualInput;
    private static final String UNIV_URL = "http://www.sookmyung.ac.kr";
    private static final String ALADIN_URL = "http://www.aladin.co.kr";
    private static final String NAVER_URL = "http://www.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        univ = (RadioButton) findViewById(R.id.button_univ);
        aladin = (RadioButton) findViewById(R.id.button_aladin);
        naver = (RadioButton) findViewById(R.id.button_naver);

        findViewById(R.id.selectButton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToURL();
                    }
                }
        );

        findViewById(R.id.manualButton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToManualURL();
                    }
                }
        );
    }

    protected void goToURL() {
        Intent intent = new Intent(this, WebActivity.class);
        if (univ.isChecked())
            intent.putExtra("url", UNIV_URL);
        else if (aladin.isChecked())
            intent.putExtra("url", ALADIN_URL);
        else if (naver.isChecked())
            intent.putExtra("url", NAVER_URL);

        startActivity(intent);
    }

    protected void goToManualURL() {
        manualInput = (EditText) findViewById(R.id.inputText);
        String url = manualInput.getText().toString();
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
