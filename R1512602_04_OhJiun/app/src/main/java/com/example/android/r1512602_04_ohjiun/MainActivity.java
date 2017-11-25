package com.example.android.r1512602_04_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton rbNamyoung, rbBoguang, rbYongmoon, rbBingo, rbCheongpa;
    Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbNamyoung = findViewById(R.id.button_namyoung);
        rbBoguang = findViewById(R.id.button_boguang);
        rbYongmoon = findViewById(R.id.button_yongmoon);
        rbBingo = findViewById(R.id.button_bingo);
        rbCheongpa = findViewById(R.id.button_cheongpa);

        selectButton = findViewById(R.id.button_select);

        selectButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        setDistrict();
                    }
                }
        );

    }

    private void setDistrict() {
        String zone = "";
        String name = "지역";
        if (rbNamyoung.isChecked()) {
            zone = "1117053000";
            name = "남영동";
        }
        else if (rbBoguang.isChecked()) {
            zone = "1117070000";
            name = "보광동";
        }
        else if (rbBingo.isChecked()) {
            zone = "1117069000";
            name = "서빙고동";
        }
        else if (rbYongmoon.isChecked()) {
            zone = "1117059000";
            name = "용문동";
        }
        else if (rbCheongpa.isChecked()) {
            zone = "1117055500";
            name = "청파동";
        }
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("zone", zone);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
