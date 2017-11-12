package com.example.android.s1512602_06_ohjiun;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView tvAzimuth;
    SensorManager sensorManager;
    Sensor sensorMagnetic, sensorAccel;
    ImageView compass;
    float azimuth, pitch, roll;
    float prev;
    float[] gravityArr;
    float[] magneticArr;


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
//        compass.setX(x);
//        compass.setY(y);

        tvAzimuth = (TextView) findViewById(R.id.azimuth);
        prev = 0.0f;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

//        sensor_orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccel,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
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
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravityArr = event.values;
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            magneticArr = event.values;

        float R[] = new float[9];
        float I[] = new float[9];
        SensorManager.getRotationMatrix(R, I, gravityArr, magneticArr);
        float orientation[] = new float[3];
        SensorManager.getOrientation(R, orientation);
        azimuth = (float) Math.toDegrees(orientation[0]);
        pitch = (float) Math.toDegrees(orientation[1]);
        roll = (float) Math.toDegrees(orientation[2]);

        String result;
        result = "Azimuth:" + azimuth;
        tvAzimuth.setText(result);

        float newAngle = event.values[0];
        String text = Float.toString(newAngle);
       tvAzimuth.setText(text);
        ObjectAnimator rotator = ObjectAnimator.ofFloat(compass,
                "rotation", prev, newAngle);
        rotator.start();
        prev = newAngle;
    }
}
