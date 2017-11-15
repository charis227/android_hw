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
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            magneticArr = event.values;

        if (gravityArr!=null && magneticArr!=null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean isSuccessful = SensorManager.getRotationMatrix(R, I, gravityArr, magneticArr);
            if (isSuccessful) {
                SensorManager.getRotationMatrix(R, I, gravityArr, magneticArr);
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
//                azimuth = orientation[0];
                azimuth = ((float) Math.toDegrees(orientation[0])+360) % 360;

                String result="";

                if (azimuth>=0 && azimuth<45.0)
                    result = "북 " + azimuth;
                else if (azimuth>=45.0f && azimuth<90.0f)
                    result = "북동 "+azimuth;
                else if (azimuth>=90f && azimuth<135.0f)
                    result = "동 "+azimuth;
                else if (azimuth>=135.0f && azimuth<180.0f)
                    result = "남동 "+azimuth;
                else if (azimuth>=180.0f && azimuth<225.0f)
                    result = "남 "+azimuth;
                else if (azimuth>=225.5f && azimuth<270.0f)
                    result = "남서 "+azimuth;
                else if (azimuth>=270.0f && azimuth<315.0f)
                    result = "서 "+azimuth;
                else if (azimuth>=315.0f && azimuth<360.0f)
                    result = "북서 "+azimuth;

                tvAzimuth.setText(result);

                ObjectAnimator rotator = ObjectAnimator.ofFloat(compass,
                        "rotation", prev, azimuth);
                rotator.start();
                prev = azimuth;
            }
        }
    }
}
