package com.example.oxygen.m;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView tvCity, tvTemp, tvHumid, tvWind, tvCloud, tvDate;
    private ArrayList<detail> listThoiTiet = new ArrayList<>();
    private String FileName = "note.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
        //Chạy chương trình lấy dữ liệu từ file.
        read_file_data();
        detail a = new detail("Ha Noi", "21", "no clouds", 125,21,12121212);
        listThoiTiet.add(a);
        if (listThoiTiet.size() == 0) {
            Toast.makeText(this, "Chưa có dữ liệu\n" +
                    "Bạn vui lòng thêm thành phố muốn theo dỗi.\n" +
                    "Cảm ơn.", Toast.LENGTH_SHORT).show();
        } else {
            //Nếu có dữ liệu sẽ lấy đối tượng đầu tiên mang ra để hiện thị màn hình chính.
            String lat = Float.toString(listThoiTiet.get(0).getLat()) ;
            String lon = Float.toString(listThoiTiet.get(0).getLon());
            getDataUrl(lat, lon);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void getControl() {
        tvDate = (TextView) findViewById(R.id.DataTimeUpdate);
        tvCity = (TextView) findViewById(R.id.City);
        tvTemp = (TextView) findViewById(R.id.temp);
        tvHumid = (TextView) findViewById(R.id.humidity);
        tvWind = (TextView) findViewById(R.id.wind);
        tvCloud = (TextView) findViewById(R.id.clouds);
        listThoiTiet = new ArrayList<detail>();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //Chạy chương trình lưu dữ liệu
        write_file_data(listThoiTiet);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataUrl("21", "21");
    }

    //============================================================================================//
    // Chương trình con lưu và lấy dữ liệu từ file
    private void write_file_data(ArrayList<detail> data) {
        //Ghi file.
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(FileName, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            for (detail i : data) {
                oos.writeObject(i);
            }
            Toast.makeText(this, "Lưu file thành công.", Toast.LENGTH_SHORT).show();
        } catch (Exception err) {
            Toast.makeText(this, "Lỗi: " + err, Toast.LENGTH_SHORT).show();
        } finally {
            if(oos != null)
            {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void read_file_data() {
        //Đọc dữ liệu từ file.
        //Trả về một danh sách các phần đối tượng kiểu detail đã tại sẵn.
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = this.openFileInput(FileName);
            ois = new ObjectInputStream(fis);
            while (ois.readObject() == null) {
                listThoiTiet.add((detail) ois.readObject());
            }
        } catch (IOException e) {
            Toast.makeText(this, "Lỗi: " + e, Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //============================================================================================//
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

//                            for (WeatherObject wo:listThoiTiet)
//                            {
//                                if(wo.getLat().equals(lat)==1 && wo.getLon().equals(lon)==1)
//                                {
//                                    WeatherObject wObject = new WeatherObject(Day,name,NhietDo,doAm,gio,may);
//                                    listThoiTiet.add(wObject);
//                                }
//                            }
                        } catch (Exception e) {
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
