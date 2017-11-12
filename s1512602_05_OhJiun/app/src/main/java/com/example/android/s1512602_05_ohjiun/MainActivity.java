package com.example.android.s1512602_05_ohjiun;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

import static android.R.attr.x;
import static android.R.attr.y;

public class MainActivity extends AppCompatActivity {
    ImageView IVSmile;
    Vibrator vibrator;
    int screenWidth, screenHeight;
    int IVHeight, IVWidth;
    int x, y;
    int prevX, prevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        IVSmile = (ImageView) findViewById(R.id.smile);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        IVSmile.measure(IVSmile.getMeasuredWidth(), IVSmile.getMeasuredHeight());
        IVHeight = IVSmile.getMeasuredHeight();
        IVWidth = IVSmile.getMeasuredWidth();
        screenWidth = size.x;
        screenHeight = size.y;

        x = screenWidth/2 - IVWidth/2;
        y = screenHeight/2 - IVHeight/2;
        IVSmile.setX(x);
        IVSmile.setY(y);

        screenHeight = size.y -IVHeight;

        prevX = x;
        prevY = y;

        IVSmile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Random rand = new Random();
                int xBound, yBound;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        int rightBorder = screenWidth - (int) (IVWidth * 1.3);
                        int bottomBorder = screenHeight - (int) (IVHeight * 1.5);
                        int rightMostX = x + IVWidth;
                        int bottomY = y + IVHeight;

                        if (rightMostX >= rightBorder) {
                            xBound = x;
                            x = x - rand.nextInt(xBound);
                        } else {
                            xBound = screenWidth - rightMostX;
                            x = x + rand.nextInt(xBound);
                        }

                        if (bottomY >= bottomBorder) {
                            yBound = y;
                            y = y - rand.nextInt(yBound);
                        } else {
                            yBound = screenHeight - bottomY;
                            y = y + rand.nextInt(yBound);
                        }

                        ObjectAnimator xTranslator = ObjectAnimator.ofFloat(IVSmile,
                                "translationX", prevX, x);
                        ObjectAnimator yTranslator = ObjectAnimator.ofFloat(IVSmile,
                                "translationY", prevY, y);
                        xTranslator.start();
                        yTranslator.start();

                        if (Build.VERSION.SDK_INT >= 26)
                            vibrator.vibrate(VibrationEffect.createOneShot(50, 10));
                        else
                            vibrator.vibrate(50);

                        prevX = x;
                        prevY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
}