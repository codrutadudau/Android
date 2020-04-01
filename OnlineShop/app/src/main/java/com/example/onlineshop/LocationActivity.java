package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity{
    Button button_start;
    Button button_stop;
    TextView cordText;
    BroadcastReceiver brdReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        button_start = (Button)findViewById(R.id.buttonStart);
        button_stop = (Button)findViewById(R.id.buttonStop);
        cordText = (TextView)findViewById(R.id.cordText);

        if(!runtime_permission())
            enable_buttons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(brdReceiver == null){
            brdReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    cordText.append("\n" + intent.getExtras().get("coordinates"));
                }
            };
        }
        registerReceiver(brdReceiver,new IntentFilter("update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(brdReceiver!=null){
            unregisterReceiver(brdReceiver);
        }
    }

    private void enable_buttons(){
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), GPS_service.class);
                startService(intent);
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), GPS_service.class);
                stopService(intent);
            }
        });

    }
    private boolean runtime_permission(){
        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission
        .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest
        .permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission
            .ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager
            .PERMISSION_GRANTED){
                enable_buttons();
            }else{
                runtime_permission();
            }
        }
    }
}
