package com.xykj.demo.fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.R;
import com.xykj.demo.activity.Datechoose;
import com.xykj.demo.activity.Guestchoose;
import com.xykj.demo.activity.SelectActivity;
import com.xykj.demo.adapter.house_adapter;
import com.xykj.demo.base.BaseFragment;
import com.xykj.demo.Class.house_block;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class BrowseFragment extends BaseFragment {
        private AirbnbApp app;
        private List<house_block> mHouseList = new ArrayList<>();
        private List<house_block> HouseList = new ArrayList<>();
        private house_adapter mhouse_adapter;
        RecyclerView recyclerView1;
        Button date;
        Button guest;
        private int namString;
        private String Startday,Endday;
        public static final int RESULT_FIRST_USER = 1;
        public static final int RESULT_SECOND_USER = 2;
        GridLayoutManager layoutManager11;
        public static final String result = "result";
        public static final String StartDay = "Start";
        public static final String EndDay = "End";
        public static final String web = "http://192.168.43.237:80" +
            "/final/apartment_output.jsp";
        //搜索框
        SearchView sv;
        ListView lv;
        private String[] mStrs = {"上海", "中国", "德国","中山"};


//        private house_block[] houses = {
//                new house_block(R.drawable.one,"阁楼",77, "卓小花【初雪】城西loft公寓步行至银泰商圈，近西湖/西溪湿地/黄龙中心/浙大/运河景区，可开发票",1),
//                new house_block(R.drawable.two,"阁楼",227,"林生公寓银泰旁榻榻米设计师自住城西Loft公寓西湖步行10分钟",2),
//                new house_block(R.drawable.four,"整套公寓",547,"林生公寓银泰旁榻榻米LOVE MUSIC LOFT极简INS风梦幻有萌猫带小院私人影院景观单间",3),
//                new house_block(R.drawable.five,"整套公寓",745,"LOVE MUSIC LOFT有萌猫带小院私人影院景观单间",4),
//                new house_block(R.drawable.six,"阁楼",537,"极简INS风梦幻有萌猫带小院私人影院景观单间",5),
//                new house_block(R.drawable.seven,"民宅",267,"西湖步行10分钟浙大植物园灵隐寺麻瓜工作室 设计师自住滨江区高层一线江景房",6),
//                new house_block(R.drawable.eight,"特色房源",167,"滨江区高层一线江景房极简INS风梦幻有萌猫带小院私人影院景观单间",7),
//                new house_block(R.drawable.eight,"附属单元",217,"麻瓜工作室 设计师自住滨江区高层一线江景房",8),
//        };
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
          //  initHouse();
        }
        @Override
        protected int attachLayoutRes() {
            return R.layout.browse_main_view;
        }
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.browse_main_view, container, false);
            recyclerView1 = (RecyclerView) view.findViewById(R.id.recycle_view1);

            sv = (SearchView) view.findViewById(R.id.searchView);
            //设置递交按钮
            sv.setSubmitButtonEnabled(true);
            lv = (ListView) view.findViewById(R.id.lv);
            lv.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mStrs));
            lv.setTextFilterEnabled(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Adapter adpter=parent.getAdapter();
                    String item = (String) adpter.getItem(position);//拿到当前数据值并强转   adpter.getItem(i)即为当前数据对象
                    sv.setQuery(item, true);
                }
            });
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                // 当点击搜索按钮时触发该方法
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(getActivity(),SelectActivity.class);
                    // Log.e("sdf","sdf");
                    //Log.e("ads",sv.getQuery().toString()+"///");
                    intent.putExtra("hi",sv.getQuery().toString());
                    startActivity(intent);
                    return false;
                }
                // 当搜索内容改变时触发该方法
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!TextUtils.isEmpty(newText)) {
                        lv.setFilterText(newText);
                    } else {
                        lv.clearTextFilter();
                    }
                    return false;
                }
            });
            //日期按钮事件监听
            date = (Button)view.findViewById(R.id.date);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Datechoose.class);
                    intent.putExtra("Start",date.getText());
                 //   startActivity(intent);
                    startActivityForResult(intent,RESULT_FIRST_USER);
                }
            });
            //人数按钮监听器
            guest = (Button)view.findViewById(R.id.guest);
            guest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  startActivityForResult(new Intent(getActivity(),Guestchoose.class),1);
                    Intent intent = new Intent(getActivity(), Guestchoose.class);
                    intent.putExtra("result",guest.getText());
                    startActivityForResult(intent, RESULT_SECOND_USER);
                }
            });
            jsonObject();
            return view;
            // return super.onCreateView(inflater, container, savedInstanceState);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SECOND_USER:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    namString = bundle.getInt("result");
                    Log.e("onActivity收到了！！！！", String.valueOf(namString));
                    app = (AirbnbApp)getActivity().getApplication();
                    app.setLonginUser_num(namString);
                    if(bundle != null)
                    {
                        guest.setText(namString+"位房客");
                    }
                }
            case RESULT_FIRST_USER:
            {
                if(resultCode == Activity.RESULT_CANCELED){
                    Bundle bundle1 = data.getExtras();
                    Startday = bundle1.getString("Start");
                    if(bundle1 != null)
                    {
                        date.setText(Startday);
                    }
                }else {
                    date.setText("日期");
                }
            }
        }




     //   super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
        protected void initViews() {
        }
        private void initView() {
            GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);//参数：context，列数
            recyclerView1.setLayoutManager(layoutManager);
            house_adapter fruitAdapter = new house_adapter(HouseList);
            recyclerView1.setAdapter(fruitAdapter);
        }
//        private void initHouse() {
//            mHouseList.clear();
//            for (int i = 0; i < 18; ++i) {
//                Random random = new Random();
//                int index = random.nextInt();
//                mHouseList.add(houses[index]);
//            }
//        }

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


