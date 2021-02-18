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
    private String TAG = "CityActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent intent = getIntent();
        String name = intent.getStringExtra("CITYNAME");
        setTitle(name);
        Log.d("CityActivity", "onCreate: name = " +name);

        imageViewCity = (ImageView) findViewById(R.id.imageView_city);
        //imageViewCity.getBackground().setAlpha(100);

        textView = (TextView) findViewById(R.id.textView_city);
        textView.getBackground().setAlpha(200);

        int pic = intent.getIntExtra("CITYPIC", 0);
        String info = intent.getStringExtra("CITYINFO");
        Log.d(TAG, "onCreate: info = "+info);

       // String msg = getResources().getString(Integer.parseInt(info));


        imageViewCity.setImageResource(pic);
        textView.setText(info);

    }
}