package com.example.oxygen.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    Hàm này lấy sự kiện của nút btn_find
//    Nhiệm vụ của hàm này là lấy sự kiện sau đó chuyển tới form map
    public Intent intent;
    public void btn_find_click(View view)
    {
        intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

//    Chương trình con lấy ngày của tuần
//    Nhiệm vụ của nó là lấy ngày tiếp theo trong tuần từ ngày hiện tại
//    Cho các ô hiển thị ngày tiếp theo trong danh sach theo dõi thời tiết
    public void getday()
    {
        TextView myDay1,myDay2,myDay3, myDay5, myDay4;

        String[] dayOfWeek = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật"};
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int  day = calendar.get(Calendar.DAY_OF_WEEK);
        myDay1 = findViewById(R.id.day1);
        myDay1.setText(dayOfWeek[day + 1]);
        myDay2 =findViewById(R.id.day2);
        myDay2.setText(dayOfWeek[day + 2]);
        myDay3 =findViewById(R.id.day3);
        myDay3.setText(dayOfWeek[day + 3]);
        myDay4 =findViewById(R.id.day4);
        myDay4.setText(dayOfWeek[day + 4]);
        myDay5 =findViewById(R.id.day5);
        myDay5.setText(dayOfWeek[day + 5]);
    }
}
