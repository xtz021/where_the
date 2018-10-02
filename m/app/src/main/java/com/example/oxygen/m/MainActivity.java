package com.example.oxygen.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readData();
        getday();
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
//    Chương trình lấy dữ liệu từ web về dạng JSON
//    Dữ liệu sẽ được sử lý và định dạng lại để sử dụng
    public void getData()
    {
    }
//    Chương trình lưu dữ liệu
    private String fileName = "weather.txt";
//    Sử dụng sau khi chương trình đóng để lưu lại dữ liệu người dùng
    public void saveData(){
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            for (detail i: read_file_list)
            {
                oos.writeObject(i);
            }
        }
        catch (Exception exception)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    Chương trình đọc dữ liệu từ file đã lưu
    //Tạo mảng để sau khi đọc dữ liệu sẽ được lưu lại vào mảng
    List<detail> read_file_list = new ArrayList<detail>();
    public void readData()
    {
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            read_file_list.add((detail) ois.readObject());
        }
        catch (Exception exception)
        {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }
}
