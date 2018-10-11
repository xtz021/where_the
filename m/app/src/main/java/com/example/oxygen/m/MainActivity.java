package com.example.oxygen.m;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<detail> danhsach;
    private JsonArray sdanhsach = new JsonArray();
    private String danhSachTam;
    private Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataUrl();
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
    public void getDataUrl() {
        String url = "https://samples.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10&appid=b1b15e88fa797225412429c1c50c122a1";
        float lat, lon;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        danhSachTam = response.toString();
                        Toast.makeText(MainActivity.this, "Tải nội dung thành công\n" + response.substring(0, 500), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi rồi thằng đần.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
        listContext();
    }

    //đưa dữ liệu vào danh sách lưu trữ.
    public void listContext() {
        Type type = new TypeToken<detail>() {
        }.getType();
        Gson gson = new Gson();
        List<detail> danh_sach = gson.fromJson(danhSachTam, type);
        for (detail i : danh_sach) {
            detail item_detail = new detail(i.getTenThanhPho(), i.getGio(), i.getMay(), i.getDoAm(),
                    i.getDoAm(), i.getLuongMua(), i.getTime_update());
            danhsach.add(item_detail);
        }
    }

}
