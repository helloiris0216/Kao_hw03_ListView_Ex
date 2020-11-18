package com.example.kao_hw03_listview_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

//    private int[] cityIcon={R.drawable.icon_tainan, R.drawable.icon_taipei, R.drawable.icon_taitung,
//                            R.drawable.icon_taoyuan, R.drawable.icon_yilan};
    private List<Map<String, Object>> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        setTitle("ListView_Ex");

        //初始化
        listViewId = (ListView) findViewById(R.id.listView_id);
        setList();

    }

    //將cityName & cityPic放入Map後，再放入List內
    private void setList() {
        //get data
        itemList = new ArrayList<Map<String, Object>>();
        cityName = getResources().getStringArray(R.array.city);
        Log.d(TAG, "setList: cityName = " +cityName);

        //put data
        for (int i=0; i<cityName.length; i++){
            HashMap<String, Object> data = new HashMap<>();
            data.put("CITYNAME", cityName[i]);
            data.put("CITYPIC", cityPic[i]);

            Log.d(TAG, "setList: CITYNAME = " +cityName);
            Log.d(TAG, "setList: CITYPIC = " +cityPic);

            itemList.add(data);
        }
        setAdaptor();
    }

    //將資料(itemList)放入Adaptor內
    //再將adaptor給listView(listViewFood)
    private void setAdaptor() {
        Log.d(TAG, "setAdaptor: ");
        //SimpleAdaptor
        SimpleAdapter adaptor = new SimpleAdapter(context, itemList, R.layout.city_layout,
                new String[]{"CITYNAME", "CITYPIC"},
                new int[]{R.id.textView_city, R.id.imageView_city});

        listViewId.setAdapter(adaptor);

        setListener();
    }

    //監聽adaptor，取出放在Map內的資料
    private void setListener() {
        listViewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String name = (String) item.get("CITYNAME");
                Log.d(TAG, "onItemClick: CITYNAME = " +name);
                Toast.makeText(context, "You selected : " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }


}