package com.xykj.demo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xykj.demo.R;
import com.xykj.demo.base.BaseFragment;


public class StoryFoodFragment extends BaseFragment {
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.story_fragment_food;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }
}
