package com.xykj.demo.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xykj.demo.R;
import com.xykj.demo.adapter.TabFragmentPagerAdapter;
import com.xykj.demo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class StoryFragment extends BaseFragment {

    TabLayout mViewpagerTab;
    ViewPager mNewsViewpager;

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        //找到控件
        mViewpagerTab = findViewById(R.id.home_viewpager_tab);
        mNewsViewpager = findViewById(R.id.home_viewpager);
        //fragment列表  
        List<Fragment> list_fragment = new ArrayList<>();
        //tab名的列表
        List<String> list_Title = new ArrayList<>();

        list_fragment.add(new StoryAllStoryFragment());
        list_fragment.add(new StoryHomestayFragment());
        list_fragment.add(new StoryFoodFragment());
        list_fragment.add(new StorySceneFragment());
        list_fragment.add(new StoryArtistFragment());
        list_fragment.add(new StoryAllStoryFragment());
        list_fragment.add(new StoryHomestayFragment());

        list_Title.add("全部故事");
        list_Title.add("民宿");
        list_Title.add("美食");
        list_Title.add("景点");
        list_Title.add("艺术");
        list_Title.add("全部故事");
        list_Title.add("民宿");

        //设置名称
        for (int i = 0; i < list_Title.size(); i++) {
            mViewpagerTab.addTab(mViewpagerTab.newTab().setText(list_Title.get(i)));
        }
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(
                getActivity().getSupportFragmentManager(), list_fragment, list_Title
        );
        //viewpager 加载adapter
        mNewsViewpager.setAdapter(adapter);
        //TableLayout加载viewpager
        mViewpagerTab.setupWithViewPager(mNewsViewpager);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.story_main_view;
    }
}
