package com.example.android.s1512602_04_ohjiun;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView seaIV = (ImageView) findViewById(R.id.sea);
        ImageView ballIV = (ImageView) findViewById(R.id.ball);
        ImageView fireIV = (ImageView) findViewById(R.id.fire);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        seaIV.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int seaHeight = seaIV.getMeasuredHeight();
        int seaYPos = height - seaHeight;

        ObjectAnimator seaY = ObjectAnimator.ofFloat(seaIV, "translationY", seaYPos, seaYPos);
        seaY.setDuration(2000);
        seaY.setRepeatCount(0);
        ObjectAnimator seaAlpha = ObjectAnimator.ofFloat(seaIV, "alpha", 0.2f, 1.0f);
        seaAlpha.setDuration(2000);
        seaAlpha.setRepeatCount(0);

        ObjectAnimator ballX = ObjectAnimator.ofFloat(ballIV, "translationX", 10.0f, 450.0f);
        ObjectAnimator ballY = (ObjectAnimator.ofFloat(ballIV, "translationY", height, 450.0f));
        ballX.setDuration(2000);
        ballX.setRepeatCount(0);
        ballY.setDuration(2000);
        ballY.setRepeatCount(0);

        ObjectAnimator ballScaleX = ObjectAnimator.ofFloat(ballIV, "scaleX", 1.0f, 0.0f);
        ObjectAnimator ballScaleY = ObjectAnimator.ofFloat(ballIV, "scaleY", 1.0f, 0.0f);
        ballScaleX.setDuration(2000);
        ballScaleX.setRepeatCount(0);
        ballScaleY.setDuration(2000);
        ballScaleY.setRepeatCount(0);

        ObjectAnimator fireX = ObjectAnimator.ofFloat(fireIV, "translationX", 100.0f, 100.0f);
        ObjectAnimator fireY = ObjectAnimator.ofFloat(fireIV, "translationY", 20.0f, 20.0f);
        fireX.setDuration(2000);
        fireX.setRepeatCount(0);
        fireY.setDuration(2000);
        fireY.setRepeatCount(0);

        ObjectAnimator fireScale = ObjectAnimator.ofPropertyValuesHolder(fireIV,
                PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f));
        fireScale.setDuration(2000);
        fireScale.setRepeatCount(0);

        ObjectAnimator fireAlpha1 = ObjectAnimator.ofFloat(fireIV, "alpha", 0.0f, 0.0f);
        ObjectAnimator fireAlpha2 = ObjectAnimator.ofFloat(fireIV, "alpha", 1.0f, 1.0f);

        ObjectAnimator seaAlpha2 = ObjectAnimator.ofFloat(seaIV, "alpha", 0.2f, 0.2f);

        AnimatorSet setBall = new AnimatorSet();
        setBall.play(seaAlpha2);
        setBall.play(seaY).with(ballScaleX);
        setBall.play(ballScaleX).with(ballScaleY);
        setBall.play(ballScaleY).with(ballX);
        setBall.play(ballX).with(ballY);
        setBall.play(ballY).with(fireAlpha1);

        AnimatorSet setFire = new AnimatorSet();
        setFire.play(seaY).with(fireScale);
        setFire.play(fireScale).with(fireX);
        setFire.play(fireX).with(fireY);
        setFire.play(fireY).with(fireAlpha2);
        setFire.play(fireAlpha2).with(seaAlpha);

        AnimatorSet setInitial = new AnimatorSet();
        setInitial.play(seaY).with(fireAlpha1);
        setInitial.play(seaAlpha2);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(setBall, setFire, setInitial);
        set.start();


    }
}
