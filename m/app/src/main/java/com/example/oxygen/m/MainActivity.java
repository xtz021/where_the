package com.example.oxygen.m;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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
    ArrayList<NextDaysObject> listNextDays;
    private TextView tvCity, tvTemp, tvHumid, tvWind, tvCloud, tvDate;
    private TextView tvDate1, tvDate2, tvDate3, tvDate4, tvDate5;
    private TextView tvDay1, tvDay2, tvDay3, tvDay4, tvDay5, tvNight1, tvNight2, tvNight3, tvNight4, tvNight5;
    private ArrayList<WeatherObject> listThoiTiet = new ArrayList<>();
    private String FileName = "note.txt";
    private TextView findClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listThoiTiet = new ArrayList<>();
        getControl();
        //Chạy chương trình lấy dữ liệu từ file.
        getDataUrl("35", "139");
        write_file_data(listThoiTiet);
        read_file_data();
        showWeather("35", "139");
//        if (listThoiTiet.size() == 0) {
//            Toast.makeText(this, "Chưa có dữ liệu\n" +
//                    "Bạn vui lòng thêm thành phố muốn theo dỗi.\n" +
//                    "Cảm ơn.", Toast.LENGTH_SHORT).show();
//        } else {
//            //Nếu có dữ liệu sẽ lấy đối tượng đầu tiên mang ra để hiện thị màn hình chính.
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        findClick = (TextView) findViewById(R.id.btn_find);
        findClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void getControl() {
        tvDate = findViewById(R.id.DataTimeUpdate);
        tvCity = findViewById(R.id.City);
        tvTemp = findViewById(R.id.temp);
        tvHumid = findViewById(R.id.humidity);
        tvWind = findViewById(R.id.wind);
        tvCloud = findViewById(R.id.clouds);

        tvDate1 = findViewById(R.id.date1);
        tvDate2 = findViewById(R.id.date2);
        tvDate3 = findViewById(R.id.date3);
        tvDate4 = findViewById(R.id.date4);
        tvDate5 = findViewById(R.id.date5);

        tvDay1 = findViewById(R.id.tempday1);
        tvDay2 = findViewById(R.id.tempday2);
        tvDay3 = findViewById(R.id.tempday3);
        tvDay4 = findViewById(R.id.tempday4);
        tvDay5 = findViewById(R.id.tempday5);

        tvNight1 = findViewById(R.id.tempnight1);
        tvNight2 = findViewById(R.id.tempnight2);
        tvNight3 = findViewById(R.id.tempnight3);
        tvNight4 = findViewById(R.id.tempnight4);
        tvNight5 = findViewById(R.id.tempnight5);
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
    private void write_file_data(ArrayList<WeatherObject> data) {
        //Ghi file.
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(FileName, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            for (WeatherObject i : data) {
                oos.writeObject(i);
            }
            Toast.makeText(this, "Lưu file thành công.", Toast.LENGTH_SHORT).show();
        } catch (Exception err) {
            Toast.makeText(this, "Lỗi ghi file: " + err, Toast.LENGTH_LONG).show();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
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
                listThoiTiet.add((WeatherObject) ois.readObject());
            }
        } catch (IOException e) {
            Toast.makeText(this, "Lỗi đọc file: " + e, Toast.LENGTH_LONG).show();
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

    private int checkList(String lat, String lon)
    {
        if (listThoiTiet.size() > 0)
        {
            int count = 0;
            for(WeatherObject wo : listThoiTiet)
            {
                if (wo.getLat().equals(lat) && wo.getLon().equals(lon))
                {
                    return count;
                }
                count++;
            }
        }
        return -1;
    }


    public void showWeather(String lat, String lon)
    {
        for (WeatherObject wo: listThoiTiet)
        {
            if(wo.getLat().equals(lat) && wo.getLon().equals(lon))
            {
                tvCity.setText("Thành phố: "+wo.getCity());
                tvDate.setText(wo.getDate());
                tvTemp.setText("Nhiệt độ: " + wo.getTemp()+"°C");
                tvHumid.setText("Độ ẩm: " + wo.getHumid() + "%");
                tvWind.setText("Độ gió: " + wo.getWind() + "m/s");
                tvCloud.setText("Mây: " + wo.getCloud() + "%");

            }

        }
    }

    //============================================================================================//
    // Lấy thông tin từ OpenWeatherMap
    // Sử dụng kinh độ và vĩ độ để lấy dữ liệu vị trí thời tiết
    public void getDataUrl(String lat, String lon) {
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&units=" +
                "metric&appid=be8d3e323de722ff78208a7dbb2dcd6f";
        final String lt = lat, ln = lon;
        //Viết nốt check lat lon trong dãy

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Toast.makeText(MainActivity.this, "Tải nội dung thành công\n"
                        //       + response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //lấy tên thành phố
                            String name = jsonObject.getString("name");

                            //lấy ngày tháng update
                            String day = jsonObject.getString("dt");
                            //xử lý format ngày tháng từ JSON
                            long l = Long.valueOf(day);
                            Date date;
                            date = new Date(l * 1000L);
                            SimpleDateFormat simpleDateFormat;
                            simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String Day = simpleDateFormat.format(date);

                            //lấy nhiệt độ, độ ẩm
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietDo = jsonObjectMain.getString("temp");
                            String doAm = jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietDo);
                            String NhietDo = String.valueOf(a.intValue());

                            //lấy độ gió, mây
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");

                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectCloud.getString("all");

                            ArrayList<NextDaysObject> list5Ngay = getNextDaysDataUrl(lt,ln);

                            //Thêm vào list
                            if(!name.equals(""))
                            {
                                int i = checkList(lt,ln);
                                WeatherObject wo = new WeatherObject(lt,ln,Day,name,nhietDo,doAm,gio,may,list5Ngay);
                                if(i<0)
                                {
                                    listThoiTiet.add(wo);
                                }
                                else {
                                    listThoiTiet.set(i,wo);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi lấy dữ liệu rồi thằng đần.",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    public ArrayList<NextDaysObject> getNextDaysDataUrl(String lat, String lon)
    {

        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?lat="+lat+"&lon="+
                lon+"&units=metric&cnt=5&appid=be8d3e323de722ff78208a7dbb2dcd6f";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listNextDays = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    for (int i=0; i< jsonArray.length();i++) {
                        JSONObject jsonObjectList = jsonArray.getJSONObject(i);

                        //Lấy date và format ngày
                        String ngay = jsonObjectList.getString("dt");
                        long l = Long.valueOf(ngay);
                        Date date = new Date(l * 1000L);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String Day = simpleDateFormat.format(date);

                        //Nhiệt độ
                        JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("temp");
                        String tempDay = jsonObjectTemp.getString("day");
                        String tempNight = jsonObjectTemp.getString("night");

                        //Trạn thái thời tiết
                        JSONObject jsonObjectWeather = jsonObjectList.getJSONObject("weather");
                        String status = jsonObjectWeather.getString("main");
                        String icon = jsonObjectWeather.getString("icon");

                        //Thêm vào List
                        if(!Day.equals(""))
                        {
                            NextDaysObject ndo = new NextDaysObject(Day,tempDay,tempNight,status,icon);
                            listNextDays.add(ndo);
                        }
                        else {
                            break;
                        }
                    }
                } catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Lỗi khi đang lấy dữ liệu: \n" + e.toString(), Toast.LENGTH_LONG);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Xảy ra sự cố lấy dữ liệu!!!", Toast.LENGTH_LONG);
            }
        });
        return listNextDays;
    }
}
