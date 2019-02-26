package com.xykj.demo.activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.R;
import com.xykj.demo.adapter.house_adapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class SelectActivity extends AppCompatActivity {
    private List<house_block> HouseList = new ArrayList<>();
    RecyclerView recyclerView1;
    TextView showwhattoselect;
    public static final String web = "http://192.168.43.237:80" +
            "/final/apartment_output.jsp";
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
       // View view = inflater.inflate(R.layout.browse_main_view, container, false);
        recyclerView1 = (RecyclerView)findViewById(R.id.recycle_view1);
        showwhattoselect = (TextView)findViewById(R.id.showselectwhat);
        Intent intent = getIntent();
        final String placename = intent.getStringExtra("hi");
        showwhattoselect.setText("以下是关于"+placename+"的搜索结果");
        jsonObject(placename.toString());
        Log.d("Browse","HouseList");

        //  initHouse();
    }
//    @Override
//    protected int attachLayoutRes() {
//        return R.layout.browse_main_view;
//    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.browse_main_view, container, false);
//        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycle_view1);
//        jsonObject();
//        Log.d("Browse","HouseList");
//
//        return view;
//        // return super.onCreateView(inflater, container, savedInstanceState);
//    }
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);//参数：context，列数
        recyclerView1.setLayoutManager(layoutManager);
        house_adapter fruitAdapter = new house_adapter(HouseList);
        recyclerView1.setAdapter(fruitAdapter);
    }

    private void jsonObject(final String placename){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(web)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String data = response.body().string();
                    HouseList = getNews(data,placename);
//                    // 鏄剧ず
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView();
                        }
                    });
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    // 得到我们需要的存放News对象的集合
    public static List<house_block> getNews(String path,String placename) {
        List<house_block> List = new ArrayList<house_block>();
        int a;
        int b;
        int c;
        int kk;
        int k;
        try {
            // 解析Json数组
            JSONArray arr = new JSONArray(path);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj2 = arr.getJSONObject(i);
                house_block news = new house_block();
                //List<house_block> news  = new ArrayList<>();
                // 根据解析到的对象得到对应的值并设置到News对象的对应属性中
                news.setHouse_id(obj2.getInt("hid"));
                news.setHouse_title(obj2.getString("title"));
                news.setHouse_province(obj2.getString("province"));
                news.setHouse_city(obj2.getString("city"));
                news.setHouse_country(obj2.getString("country"));
                news.setHouse_latitude(obj2.getDouble("latitude"));
                news.setHouse_longitude(obj2.getDouble("longtitude"));
                news.setHouse_capacity(obj2.getInt("guests_count"));
                news.setHouse_type(obj2.getString("type"));
                news.setPrice(obj2.getInt("refer_price"));
                news.setHouse_name(obj2.getString("info"));
                news.setHouse_picture(obj2.getString("picture"));
                String pic = obj2.getString("picture");
                // 把News对象添加到list集合中
                if(news.getHouse_country().contains(placename)||
                        placename.contains(news.getHouse_country())||
                        news.getHouse_province().contains(placename)||
                        placename.contains(news.getHouse_province())||
                        news.getHouse_city().contains(placename)||
                        placename.contains(news.getHouse_city())||
                        news.getHouse_country() == placename ||
                        news.getHouse_province() == placename ||
                        news.getHouse_city() == placename)
                {
                    List.add(news);
                }
                Log.e("数据库",pic);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return List;
    }
}
