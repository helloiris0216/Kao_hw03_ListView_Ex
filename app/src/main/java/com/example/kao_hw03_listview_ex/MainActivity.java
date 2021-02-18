package com.example.kao_hw03_listview_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Context context;
    private ListView listViewId;
    private String[] cityName;
    private int[] cityPic= {R.drawable.tainan, R.drawable.taipei, R.drawable.taitung,
                            R.drawable.taoyuan, R.drawable.yilan};

    private List<Map<String, Object>> itemList;
    private String[] cityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.NightTheme);
        context = this;
       // setTitle("ListView_Ex");

        //初始化
        listViewId = (ListView) findViewById(R.id.listView_id);
        setList();
    }

    //將cityName & cityPic放入Map後，再放入List內
    private void setList() {
        //get data
        itemList = new ArrayList<Map<String, Object>>();
        cityName = getResources().getStringArray(R.array.city);
        cityInfo = getResources().getStringArray(R.array.info);

        //put data
        for (int i=0; i<cityName.length; i++){
            HashMap<String, Object> data = new HashMap<>();
            data.put("CITYNAME", cityName[i]);
            data.put("CITYPIC", cityPic[i]);
            data.put("CITYINFO", cityInfo[i]);

            Log.d(TAG, "setList: CITYNAME = " +cityName);
            Log.d(TAG, "setList: CITYPIC = " +cityPic);

            itemList.add(data);
        }
        setAdaptor();
    }

    //將資料(itemList)放入Adaptor內
    //再將adaptor給listView(listViewId)
    private void setAdaptor() {
        Log.d(TAG, "setAdaptor: ");
        //SimpleAdaptor
        SimpleAdapter adaptor = new SimpleAdapter(context, itemList, R.layout.city_layout,
                new String[]{"CITYNAME", "CITYPIC", "CITYINFO"},
                new int[]{R.id.textView_cityLayout, R.id.imageView_picLayout});

        listViewId.setAdapter(adaptor);

        setListener();
    }

    //監聽adaptor，取出放在Map內的資料
    private void setListener() {
        listViewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);

                Intent intent = new Intent(context, CityActivity.class);
                intent.putExtra("CITYNAME", (String)(item.get("CITYNAME")));
                intent.putExtra("CITYPIC", (int) item.get("CITYPIC"));
                intent.putExtra("CITYINFO", (String) item.get("CITYINFO"));

                Log.d(TAG, "onItemClick: CITYNAME = " +(String)(item.get("CITYNAME")));
                Log.d(TAG, "onItemClick: CITYINFO = "+(String) item.get("CITYINFO"));

                startActivity(intent);
                Toast.makeText(context, "You selected : " + (String)(item.get("CITYNAME")), Toast.LENGTH_SHORT).show();
            }
        });
    }


}