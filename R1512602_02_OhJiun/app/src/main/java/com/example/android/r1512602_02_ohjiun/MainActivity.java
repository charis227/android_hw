package com.example.android.r1512602_02_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RadioButton rbJimin, rbLeo, rbGong, rbOliv;
    ImageView image;
    int photoNumber;
    int count;
    String rightAnswer;
    int before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("연예인은 누구?");

        rbGong = (RadioButton) findViewById(R.id.button_gongyou);
        rbJimin = (RadioButton) findViewById(R.id.button_jimin);
        rbLeo = (RadioButton) findViewById(R.id.button_leo);
        rbOliv = (RadioButton) findViewById(R.id.button_olivia);

        image = (ImageView) findViewById(R.id.image);
        count = 0;
        before = 0;

        setPhoto();

        findViewById(R.id.confirm).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        confirmAnswer();
                    }
                }
        );
    }

    protected void onResume() {
        super.onResume();
        setPhoto();
    }

    protected void confirmAnswer() {
        Intent intent;
        boolean isRight = false;
        String answer="";

        if (rbGong.isChecked()) {
            answer = "공유";
            if (photoNumber==0) {
                count++;
                isRight = true;
            }
        }
        else if (rbJimin.isChecked()) {
            answer = "한지민";
            if (photoNumber==1) {
                count++;
                isRight = true;
            }
        }
        else if (rbLeo.isChecked()) {
            answer = "레오나르도 디카프리오";
            if (photoNumber==2) {
                count++;
                isRight = true;
            }
        }
        else if (rbOliv.isChecked()) {
            answer = "올리비아 핫세";
            if (photoNumber==3) {
                count++;
                isRight = true;
            }
        }

        intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("count", count);
        intent.putExtra("answer", answer);
        intent.putExtra("isRight", isRight);
        if (!isRight)
            intent.putExtra("rightAnswer", rightAnswer);

        startActivity(intent);
    }

    private void setPhoto() {
        Random random = new Random();
        do {
            photoNumber = random.nextInt(4);
        } while (photoNumber==before);
        before = photoNumber;

        switch (photoNumber) {
            case 0:
                image.setImageResource(R.drawable.gongyou);
                rightAnswer = "공유";
                break;
            case 1:
                image.setImageResource(R.drawable.jimin);
                rightAnswer = "한지민";
                break;
            case 2:
                image.setImageResource(R.drawable.leo);
                rightAnswer = "레오나르도 디카프리오";
                break;
            case 3:
                image.setImageResource(R.drawable.olivia);
                rightAnswer = "올리비아 핫세";
                break;
        }
    }
}
