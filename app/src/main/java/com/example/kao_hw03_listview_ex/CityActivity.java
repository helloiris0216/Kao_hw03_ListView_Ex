package com.example.kao_hw03_listview_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CityActivity extends AppCompatActivity {

    private ImageView imageViewCity;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent intent = getIntent();
        String name = intent.getStringExtra("CITYNAME");
        setTitle(name);
        Log.d("CityActivity", "onCreate: name = " +name);

        imageViewCity = (ImageView) findViewById(R.id.imageView_city);
        textView = (TextView) findViewById(R.id.textView_city);

        int pic = intent.getIntExtra("CITYPIC", 0);
        int info = intent.getIntExtra("CITYINFO", 0);
        String msg = getResources().getString(info);
        
        imageViewCity.setImageResource(pic);
        textView.setText(msg);

    }
}