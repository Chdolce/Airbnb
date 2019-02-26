package com.xykj.demo.view.util;

import com.xykj.demo.Class.house_block;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HouseHttpUtils {

    public static final String web = "http://192.168.43.237:80" +
            "/final/apartment_output.jsp";
    // 得到解析出来的Json数据
    public static String getJsonContent(String path) {
            String result = "";
        BufferedReader bufr = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bufr = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = "";
            while ((line = bufr.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                bufr.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return result;
    }

    // 得到我们需要的存放News对象的集合
    public static List<house_block> getNews(String path) {
        List<house_block> HouseList = new ArrayList<house_block>();
         String json = getJsonContent(path);
        try {
            // 解析Json数组
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj2 = arr.getJSONObject(i);
                house_block news = new house_block();
                //List<house_block> news  = new ArrayList<>();
                // 根据解析到的对象得到对应的值并设置到News对象的对应属性中
                news.setHouse_id(obj2.getInt("hid"));
                news.setHouse_title(obj2.getString("title"));
                news.setHouse_province(obj2.getString("province"));
                news.setHouse_city(obj2.getString("city"));
                news.setHouse_latitude(obj2.getDouble("latitude"));
                news.setHouse_longitude(obj2.getDouble("longtitude"));
                news.setHouse_capacity(obj2.getInt("guests_count"));
                news.setHouse_type(obj2.getString("type"));
                news.setPrice(obj2.getInt("refer_price"));
                news.setHouse_name(obj2.getString("info"));
                // 把News对象添加到list集合中
                HouseList.add(news);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return HouseList;
    }

}
