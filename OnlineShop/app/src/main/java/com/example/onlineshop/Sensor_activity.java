package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Sensor_activity extends AppCompatActivity implements SensorEventListener {

    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    SensorManager sManager;
    Sensor light_sensor;
    Sensor proximity_sensor;
    Sensor pressure_sensor;
    Sensor humidity_sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        Bundle bundle = getIntent().getExtras();

        sManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        light_sensor = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximity_sensor = sManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pressure_sensor = sManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        humidity_sensor = sManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sManager.unregisterListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sManager.registerListener(this,light_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this,proximity_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this,pressure_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this,humidity_sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            textView.setText(" " + event.values[0]);
        }
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            textView1.setText(" " + event.values[0]);
        }
        if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
            textView2.setText(" " + event.values[0]);
        }
        if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            textView3.setText(" " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}


}
