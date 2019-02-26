package com.xykj.demo.fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.R;
import com.xykj.demo.adapter.WishHouseAdapter;
import com.xykj.demo.base.BaseFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WishFragment extends BaseFragment {
    // private DrawerLayout mDrawerLayout;
    // private List<house_block> mHouseList = new ArrayList<>();
    private List<house_block> HouseList = new ArrayList<>();
    //  private house_adapter mhouse_adapter;
    RecyclerView recyclerView1;
    //GridLayoutManager layoutManager11;
    public static final String web = "http://192.168.43.237:80" +
            "/final/apartment_output.jsp";

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.wish_main_view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wish_main_view, container, false);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.wish_recyclerview1);
        //日期按钮事件监听
        jsonObject();
        Log.d("Browse","HouseList");
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    protected void initViews() {
    }
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 1);//参数：context，列数
        recyclerView1.setLayoutManager(layoutManager);
        WishHouseAdapter fruitAdapter = new WishHouseAdapter (HouseList);
        recyclerView1.setAdapter(fruitAdapter);
    }
    private void jsonObject(){
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
                    HouseList = getNews(data);
//                    // 鏄剧ず
                    getActivity().runOnUiThread(new Runnable() {
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
    public static List<house_block> getNews(String path) {
        List<house_block> List = new ArrayList<house_block>();
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
                List.add(news);
                Log.e("数据库",pic);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return List;
    }
}
