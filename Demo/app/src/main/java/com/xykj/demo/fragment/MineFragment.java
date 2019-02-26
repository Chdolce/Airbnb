package com.xykj.demo.fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.R;
import com.xykj.demo.activity.LoginActicity;
import com.xykj.demo.activity.MainActivity;
import com.xykj.demo.activity.MyOrderActivity;
import com.xykj.demo.view.IMineView;
import com.xykj.demo.base.BaseFragment;

public class MineFragment extends BaseFragment implements IMineView {

    private AirbnbApp app;
    private ImageView Image;
    private TextView text;
    private String namString;
    private TextView travel;
    private int isUpdate = 0;
    private TextView isupdateText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Image = (ImageView)findViewById(R.id.image);



    }

    public MineFragment() {
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.mine_main_view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_main_view, container, false);
        Image = (ImageView)view.findViewById(R.id.image);
        text = (TextView)view.findViewById(R.id.top_name);
        travel = (TextView) view.findViewById(R.id.My_word_travel);
        isupdateText = (TextView)view.findViewById(R.id.isupdate);
        String test;
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActicity.class);
                //startActivity(intent);
                intent.putExtra("isupdate", isupdateText.getText());
                // isUpdate = 1;
                startActivityForResult(intent,1);

            }
        });

        travel.setOnClickListener(new View.OnClickListener(){
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(i);
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == 0){
                updateUserInfo();
            }
        }
    }

    /**
     * 更新界面上面的用户信息
     */
    @Override
    public void updateUserInfo() {
        app = (AirbnbApp)getActivity().getApplication();
        Glide.with(this).load(app.getLoginUser_img()).into(Image);
        text.setText(app.getLoginUser_name());

    }



}
