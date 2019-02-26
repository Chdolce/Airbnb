package com.xykj.demo.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xykj.demo.R;
import com.xykj.demo.activity.MainActivity;
import com.xykj.demo.activity.StoryActivity;
import com.xykj.demo.base.BaseFragment;
import com.xykj.demo.base.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class StorySceneFragment extends BaseFragment {

    ImageView imageView;
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.story_fragment_scene,container,false);
        imageView = (ImageView)findViewById(R.id.bing_pic_img);
        SharedPreferences preferences = getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        String pic = preferences.getString("bing_pic","");
        Log.d("Story","pic"+ pic);
        try {
            int a = Integer.valueOf(pic);
            imageView.setImageResource(a);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
/*
*必应图片显示不了  原因用GLide报空指针 以及 返回String类型不能使用
 */
        return view;
    }
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.story_fragment_scene;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }
}
