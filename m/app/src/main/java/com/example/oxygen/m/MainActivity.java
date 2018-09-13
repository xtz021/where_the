package com.example.oxygen.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.time.Instant;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn_Them;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list_view);

        detail test1 = new detail("Hà Nội", "100:100:100", null);
        detail test2 = new detail("Hà Nội", "100:100:100", null);

        detail[] ds = new detail[]{test1, test2};

        ArrayAdapter<detail> danhsach = new ArrayAdapter<detail>(
                MainActivity.this, android.R.layout.activity_list_item,ds
                );

        list.setAdapter((ListAdapter) danhsach);
    }
}
