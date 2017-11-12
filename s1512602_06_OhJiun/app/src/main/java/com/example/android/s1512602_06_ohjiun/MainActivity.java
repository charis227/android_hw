package com.example.android.s1512602_06_ohjiun;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView azimuth;
    SensorManager sensorManager;
    Sensor sensor_orientation;
    Sensor magnetic, accel;
    ImageView compass;
    float prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        compass = (ImageView) findViewById(R.id.compass);

        compass.measure(compass.getMeasuredWidth(), compass.getMeasuredHeight());
        int compassHeight = compass.getMeasuredHeight();
        int compassWidth = compass.getMeasuredWidth();
        int screenWidth = size.x;
        int screenHeight = size.y;

        int x = screenWidth/2 - compassWidth/2;
        int y = screenHeight/2 - compassHeight/2;
        compass.setX(x);
        compass.setY(y);

        azimuth = (TextView) findViewById(R.id.azimuth);
        prev = 0.0f;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

//        sensor_orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    protected void onResum() {
        super.onResume();
        sensorManager.registerListener(this, accel,SensorManager.SENSOR_DELAY_NORMAL);
/*        sensorManager.registerListener((SensorEventListener) this, sensor_orientation,
                SensorManager.SENSOR_DELAY_NORMAL); */
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        float newAngle = event.values[0];
        String text = Float.toString(newAngle);
       azimuth.setText(text);
        ObjectAnimator rotator = ObjectAnimator.ofFloat(compass,
                "rotation", prev, newAngle);
        rotator.start();
        prev = newAngle;
    }
}
