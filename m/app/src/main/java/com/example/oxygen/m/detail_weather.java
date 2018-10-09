package com.example.oxygen.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class detail_weather extends AppCompatActivity {

    //Tạo danh sách chứa dữ liệu.
    private List<detail> danhsach = new ArrayList<detail>();

    //Tạo listView hiển thị dữ liệu trong sanh sách đã lấy
    ListView listView = (ListView) findViewById(R.id.listView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);

        dataInput();
        show_data();
    }

    //Chương trình con đổ dữ liệu từ danh sách vào listView
    private void show_data()
    {
        ArrayAdapter<detail> arrayAdapter = new ArrayAdapter<detail>(detail_weather.this,
                R.layout.item_list_view, danhsach);
        listView.setAdapter(new customListAdapter(this, danhsach));
        //Khi nhấn vào một đối tượng trong danh sách
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                detail item = (detail) o;
                Intent intentItem = new Intent(detail_weather.this, MainActivity.class);
                Gson gson = new Gson();
                String sItem = gson.toJson(item);
                intentItem.putExtra("dataItem", sItem);
                startActivity(intentItem);
            }
        });
    }

    //Chương trình con lấy dữ liệu từ mainActivity
    private void dataInput()
    {
        String data = getIntent().getStringExtra("data");

        Gson gson = new Gson();

        Type type = new TypeToken<List<detail>>(){}.getType();
        List<detail> danhsach = gson.fromJson(data, type);
        for(detail i: danhsach)
        {
            detail item_detail = new detail(i.getTenThanhPho(),i.getGio(),i.getMay(),i.getDoAm(),
                    i.getDoAm(),i.getLuongMua(),i.getTime_update());
            danhsach.add(item_detail);
        }
    }
}
