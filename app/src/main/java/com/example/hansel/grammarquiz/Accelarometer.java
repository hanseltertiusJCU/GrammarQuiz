package com.example.hansel.grammarquiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.SoundPool;

public class Accelarometer extends Activity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;
    private SoundManager sounds;
    private int menuPop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelarometer);

        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        sounds = new SoundManager(this);
        menuPop = sounds.addSound(R.raw.pop);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        xText.setText("X: " + Float.toString(x));
        yText.setText("Y: " + Float.toString(y));
        zText.setText("Z: " + Float.toString(z));
        if (x > y && x > z) {
            xText.setTextColor(Color.GREEN);
        } else {
            xText.setTextColor(Color.RED);
        }
        if (y > x && y > z) {
            yText.setTextColor(Color.GREEN);
        } else {
            yText.setTextColor(Color.RED);
        }
        if (z > x && z > y) {
            zText.setTextColor(Color.GREEN);
        } else {
            zText.setTextColor(Color.RED);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }

    public void back (View view) {
        sounds.play(menuPop);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
