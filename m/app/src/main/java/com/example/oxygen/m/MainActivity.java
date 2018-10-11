package com.example.oxygen.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvCity, tvTemp,tvHumid, tvWind, tvCloud, tvDate;
    ArrayList<WeatherObject> listThoiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataUrl("1","1");
    }

    protected void getControl()
    {
        tvDate = (TextView) findViewById(R.id.DataTimeUpdate);
        tvCity = (TextView) findViewById(R.id.City);
        tvTemp = (TextView) findViewById(R.id.temp);
        tvHumid = (TextView) findViewById(R.id.humidity);
        tvWind = (TextView) findViewById(R.id.wind);
        tvCloud = (TextView) findViewById(R.id.clouds);
        listThoiTiet = new ArrayList<WeatherObject>();
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataUrl();
    }

    // Lấy thông tin từ OpenWeatherMap
    // Sử dụng kinh độ và vĩ độ để lấy dữ liệu vị trí thời tiết
    public void getDataUrl(String lat, String lon) {
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&units=metric&appid=be8d3e323de722ff78208a7dbb2dcd6f";
        //Viết nốt check lat lon trong dãy



        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this, "Tải nội dung thành công\n" + response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //lấy tên thành phố
                            String name = jsonObject.getString("name");
                            tvCity.setText("Thành phố: " + name);

                            //lấy ngày tháng update
                            String day = jsonObject.getString("dt");
                            //xử lý format ngày tháng từ JSON
                            long l = Long.valueOf(day);
                            Date date = new Date(l * 1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String Day = simpleDateFormat.format(date);
                            tvDate.setText(Day);

                            //lấy nhiệt độ, độ ẩm
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietDo = jsonObjectMain.getString("temp");
                            String doAm = jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietDo);
                            String NhietDo = String.valueOf(a.intValue());
                            tvTemp.setText("Nhiệt độ: " + NhietDo + "°C");
                            tvHumid.append("Độ ẩm: " + doAm + "%");

                            //lấy độ gió, mây
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            tvWind.setText("Độ gió: " + gio + "m/s");

                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectCloud.getString("all");
                            tvCloud.setText("Mây: " + may + "%");

                            for (WeatherObject wo:listThoiTiet)
                            {
                                if(wo.getLat().equals(lat)==1 && wo.getLon().equals(lon)==1)
                                {
                                    WeatherObject wObject = new WeatherObject(Day,name,NhietDo,doAm,gio,may);
                                    listThoiTiet.add(wObject);
                                }
                            }
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi rồi thằng đần.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

}
