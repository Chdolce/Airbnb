package com.xykj.demo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xykj.demo.Class.Story_block;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.R;
import com.xykj.demo.adapter.CrosswiseAdapter;
import com.xykj.demo.adapter.StoryAdapter;
import com.xykj.demo.adapter.house_adapter;
import com.xykj.demo.base.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StoryAllStoryFragment extends BaseFragment {

    private DrawerLayout mDraweLayout;
    private  List<Story_block> mstoryblocks1= new ArrayList<>();
    private  List<Story_block> mstory = new ArrayList<>();
    RecyclerView recyclerView1,recyclerView2;
    public static final String web = "http://192.168.43.237:80" +
            "/final/story_output.jsp";



    private Story_block[] storyBlocks1 = {
            new Story_block("厦门","在闹中取静的家感受厦门的美好",R.drawable.one),
            new Story_block("旧金山","在旧金山找到了一个家",R.mipmap.a),
            new Story_block("厦门","局口版面：拌面只是个幌子",R.mipmap.b),
            new Story_block("伦敦","雨中伦敦才是正伦敦",R.mipmap.c),
    };

    private List<Story_block> mstoryblocks2 = new ArrayList<>();

    private Story_block[] storyBlocks2 = {
            new Story_block("在闹中取静的家感受厦门的美好",R.drawable.five),
            new Story_block("在旧金山找到了一个家",R.drawable.four),
            new Story_block("局口版面：拌面只是个幌子",R.drawable.one),
            new Story_block("雨中伦敦才是正伦敦",R.drawable.two),
            new Story_block("甜点咖啡",R.drawable.eight)
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStory();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.story_fragment_allstory, container, false);
         recyclerView1 = (RecyclerView) view.findViewById(R.id.recycle_storyview1);
         recyclerView2 = (RecyclerView)view.findViewById(R.id.recycle_widview);

       // initView();
        jsonObject();
        return view;
    }

    private void initView() {
        //纵向
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);//参数：context，列数
        recyclerView1.setLayoutManager(layoutManager);
        //1.修改绑定适配器内容
        StoryAdapter storyAdapter = new StoryAdapter(mstory);
        recyclerView1.setAdapter(storyAdapter);
       //横向
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(manager);
        CrosswiseAdapter crosswiseAdapter = new CrosswiseAdapter(mstoryblocks2,getContext());
        recyclerView2.setAdapter(crosswiseAdapter);


    }
    public void initStory(){
//        mstoryblocks1.clear();
//        for(int i = 0;i < 18;i++ ){
//            Random random = new Random();
//            int index = random.nextInt(storyBlocks1.length);
//            mstoryblocks1.add(storyBlocks1[index]);
//        }

        mstoryblocks2.clear();
            for (int j = 0;j < 7;j++){
                Random random = new Random();
                int index2 = random.nextInt(storyBlocks2.length);
                mstoryblocks2.add(storyBlocks2[index2]);
            }
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
                    mstory = getNews(data);
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
    public static List<Story_block> getNews(String path) {
        List<Story_block> List = new ArrayList<Story_block>();
        try {
            // 解析Json数组
            JSONArray arr = new JSONArray(path);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj2 = arr.getJSONObject(i);
                Story_block news = new Story_block();
                // 根据解析到的对象得到对应的值并设置到News对象的对应属性中
                news.setAuthor_id(obj2.getInt("authorid"));
                int authorid = obj2.getInt("authorid");
                news.setAuthor_img(obj2.getString("authorimg"));
                news.setAuthor_name(obj2.getString("authorname"));
                news.setStory_imgId(obj2.getInt("storyid"));
                news.setStory_title(obj2.getString("storytitle"));
                news.setStory_content(obj2.getString("storycontent"));
                news.setStory_img(obj2.getString("storyimg"));
                news.setStory_place(obj2.getString("storyplace"));
                String pic = obj2.getString("storyimg");
                // 把News对象添加到list集合中
                List.add(news);
                Log.e("数据库story",String.valueOf(authorid));
                Log.e("数据库story",pic);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return List;
    }
    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.story_fragment_allstory;
    }
}
