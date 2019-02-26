package com.xykj.demo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.base.HttpUtil;
import com.xykj.demo.fragment.BrowseFragment;
import com.xykj.demo.fragment.InboxFragment;
import com.xykj.demo.fragment.MineFragment;
import com.xykj.demo.R;
import com.xykj.demo.fragment.StoryFragment;
import com.xykj.demo.fragment.StorySceneFragment;
import com.xykj.demo.fragment.WishFragment;
import com.xykj.demo.base.BaseActivity;
import com.xykj.demo.view.util.HouseHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity {

    AirbnbApp app;

    RadioGroup mRgBottomMenu;
    //数组 存储Fragment
    Fragment[] mFragments;
    //当前Fragent的下标
    private int currentIndex;


    List<house_block> HouseList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }
    @Override
    protected void initFindViewById() {
        mRgBottomMenu = (RadioGroup) findViewById(R.id.rg_bottom_menu);
    }
    @Override
    protected void initViews() {
       
        //将Fragment加入数组
        mFragments = new Fragment[] {
                //浏览、心愿单、故事、收件箱、我的
                new BrowseFragment(),
                new WishFragment(),
                new StoryFragment(),
                new InboxFragment(),
                new MineFragment()
        };
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 BrowseFragment
        ft.add(R.id.main_content,mFragments[0]).commit();
        //RadioGroup选中事件监听 改变fragment
        mRgBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_browse:
                        setIndexSelected(0);

                        break;
                    case R.id.rb_wish:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_story:
                        setIndexSelected(2);
                        break;
                    case R.id.rb_receive:
                        setIndexSelected(3);
                        break;
                    case R.id.rb_mine:
                        setIndexSelected(4);
                        break;
                }
            }
        });


    }
    //设置Fragment页面
    public void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(mFragments[currentIndex]);
        //判断Fragment是否已经添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.main_content,mFragments[index]).show(mFragments[index]);
        }else {
            //显示新的Fragment
            ft.show(mFragments[index]);
        }
        ft.commit();
        currentIndex = index;
    }

    public void setBrowseSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(mFragments[currentIndex]);
        //判断Fragment是否已经添加
        ft.replace(R.id.main_content,mFragments[index]);

        ft.commit();
        currentIndex = index;
    }
    protected void onResume() {
        int id = getIntent().getIntExtra("id", 0);
        if (id == 5) {
            //ft.setCurrentTab(3);
            gotoDownloadFragment();
        }
        super.onResume();
    }
    public void gotoDownloadFragment() {    //去下载页面
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction = fmanager.beginTransaction();
        MineFragment  mDownloadFragment = new MineFragment();
        ftransaction.replace(R.id.activity_main, mDownloadFragment);
        ftransaction.commit();
    }

}
