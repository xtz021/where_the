package com.example.oxygen.m;
//================================================================================================//
//  decription
//  - Đọc dữ liệu tại đây.
//  - Hiện danh sách tên các thành phố và địa điểm đã lưu.
//  - Tạo sự kiện listItemClikcListener for each item of list
//  - Create the event for back to mainActivity and info of item clicked.
//================================================================================================//
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class detail_weather extends AppCompatActivity {
    protected String FileName = "data.txt";
    private static final String TAG = "Detail Weather";
    //Tạo danh sách chứa dữ liệu.
    private List<detail> danhsach = new ArrayList<detail>();

    //Tạo listView hiển thị dữ liệu trong sanh sách đã lấy
    ListView listView = (ListView) findViewById(R.id.listView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);
        Log.d(TAG, "onCreate: create the app started");
        read_file_data();
    }
    private void read_file_data() {
        //Đọc dữ liệu từ file.
        //Trả về một danh sách các phần đối tượng kiểu detail đã tại sẵn.
        Log.d(TAG, "read_file_data: begining process read dât from file local");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            Scanner s = new Scanner(new File(FileName));
            while (s.hasNextLine()){
            }
            fis = this.openFileInput(FileName);
            ois = new ObjectInputStream(fis);
//            listThoiTiet = (ArrayList<WeatherObject>) ois.readObject();
        } catch (IOException e) {
            Toast.makeText(this, "Lỗi đọc file: " + e, Toast.LENGTH_LONG).show();
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

        Type type = new TypeToken<List<WeatherObject>>(){}.getType();
        List<WeatherObject> danhsach = gson.fromJson(data, type);
        for(WeatherObject i: danhsach)
        {

        }
    }
}
