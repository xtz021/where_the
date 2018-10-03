package com.example.oxygen.m;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class process_json {
private String url_link = "";
    public process_json() throws IOException {
        read_JSON();
        getJsonUrl(url_link);
    }

    //Lấy file json từ web về sau đó ném cho read_json nghịch
    public JSONObject getJsonUrl(String urlString) throws IOException {
        HttpURLConnection urlConnection;
        URL url = new URL(urlString);
        BufferedReader br;
        char[] buffer = null;
        String jsonString = new String();
        StringBuilder sb;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            br = new BufferedReader(new InputStreamReader(url.openStream()));
            buffer = new char[1024];
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            jsonString = sb.toString();
            System.out.println("JSON" + jsonString);
        } catch (Exception e) {
            System.out.println("\nERROR" + e);
        }
        return new JSONObject();
    }

    //Đọc dữ liệu từ file json
    public void read_JSON() {
        JSONObject obj;
        //String pageString;
        JSONArray array;
        try {
            obj = new JSONObject();
            //pageString = obj.getJSONObject("pageinfo").getString("pageName");
            array = obj.getJSONArray("posts");
            for (int i = 0; i < array.length(); ++i) {
                String post_id = array.getJSONObject(i).getString("posts");
                System.out.println(post_id);
            }
        } catch (JSONException je) {

        }
    }
}
