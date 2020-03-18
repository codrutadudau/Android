package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView text = (TextView)findViewById(R.id.textview);

        Bundle bundle = getIntent().getExtras();
        String key = getResources().getString(R.string.text);
        String msg = bundle.getString(key);
        text.setText(msg);



    }
}
