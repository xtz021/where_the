package com.example.oxygen.m;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class detail_weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);
        ListView list = (ListView) findViewById(R.id.listView);

        detail test1 = new detail("Hà Nội", "100:100:100", null);
        detail test2 = new detail("Hà Nội", "100:100:100", null);

        detail[] ds = new detail[]{test1, test2};

        ArrayAdapter<detail> danhsach = new ArrayAdapter<detail>(
                detail_weather.this, android.R.layout.activity_list_item,ds
        );

        list.setAdapter((ListAdapter) danhsach);

    }
}
