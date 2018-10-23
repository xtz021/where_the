package com.example.oxygen.weather;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //  Widget
    private TextView btn_find;

    //  Const
    protected static final String FILE_NAME = "data.txt";
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    //  Variable
    private List<weatherObject> list = new ArrayList<>();

    // Read and write data from file local
    //============================================================================================//
    private boolean checkList(List<weatherObject> mList) {
        int iSize = mList.size();
        if (iSize == 0) {
            Log.d(TAG, "checkList: List is empty, do not to write to file");
            Toast.makeText(this, "Dữ liệu lỗi, không thể tiến hành ghi file", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void demo() {
        weatherObject weatherObject = new weatherObject();
        weatherObject.setCity("Hà nội");
        weatherObject.setLat("21");
        weatherObject.setLon("105");
        list.add(weatherObject);
    }

    private ArrayList<weatherObject> readFile() {
        //  variable
        ArrayList<weatherObject> array;
        Log.d(TAG, "read_file: Open thread read the data from file");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = this.openFileInput(FILE_NAME);
            ois = new ObjectInputStream(fis);
            array = (ArrayList) ois.readObject();
            Log.d(TAG, "readFile: Reading is ok");
            Toast.makeText(this, "Đọc file thành công: " + list, Toast.LENGTH_SHORT).show();
            return array;
        } catch (IOException e) {
            Log.e(TAG, "readFile: Error :" + e.getMessage());
            Toast.makeText(this, "Lỗi đọc file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "readFile: Error :" + e.getMessage());
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
        return null;
    }

    private void writeFile(ArrayList<weatherObject> array){
        Log.d(TAG, "writeFile: Open thread write data to the file");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(FILE_NAME, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(array);
            oos.flush();
            Toast.makeText(this, "Lưu file thành công.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "writeFile: Writing is successfully");
        } catch (IOException err) {
            Toast.makeText(this, "Lỗi ghi file: " + err.getMessage(), Toast.LENGTH_LONG).show();
            Log.d(TAG, "writeFile: Error :" + err.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    Log.e(TAG, "writeFile: Error: " + e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, "writeFile: Error: " + e.getMessage());
                }
            }
        }
    }

    // Check google sevices
    //============================================================================================//

    private boolean isServiceOk() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void init() {
        btn_find = (TextView) findViewById(R.id.findLocation);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    //============================================================================================//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo();
        if (isServiceOk()) {
            init();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkList(list)) {
            writeFile((ArrayList<weatherObject>) list);
        }
        readFile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeFile((ArrayList<weatherObject>) list);
    }
}
