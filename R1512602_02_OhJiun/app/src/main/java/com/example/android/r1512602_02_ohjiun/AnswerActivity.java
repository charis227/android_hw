package com.example.android.r1512602_02_ohjiun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_answer);
        setTitle("연예인은 누구?");

        TextView tvConfirm = (TextView) findViewById(R.id.confirm);
        TextView tvCount = (TextView) findViewById(R.id.count);

        Intent intent = getIntent();
        String answer = intent.getStringExtra("answer");
        String rightAnswer;
        boolean isRight = intent.getBooleanExtra("isRight", false);
        int count = intent.getIntExtra("count", 0);

        if (isRight) {
            tvConfirm.setText(answer+"입니다.\n잘 맞혔어요!");
        }
        else {
            Log.v("검사", "틀림 안으로 들어옴");
            rightAnswer = intent.getStringExtra("rightAnswer");
            tvConfirm.setText(answer+" 아닙니다.\n"+rightAnswer+"입니다.");
        }
        tvCount.setText("맞힌 횟수: "+count);

        findViewById(R.id.back).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    protected void goBack() {
        finish();
    }
}
