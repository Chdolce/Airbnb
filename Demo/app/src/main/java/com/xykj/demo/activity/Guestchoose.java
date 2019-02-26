package com.xykj.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.NumberPicker.Formatter;
import android.widget.Toast;
import com.xykj.demo.R;
import com.xykj.demo.base.BaseActivity;
import com.xykj.demo.fragment.BrowseFragment;

public class Guestchoose extends BaseActivity implements NumberPicker.OnValueChangeListener, Formatter, NumberPicker.OnScrollListener {
    NumberPicker numberPicker=null;
    Button button=null;
    private int result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //返回布局文件ID
        setContentView(attachLayoutRes());
        //找到控件ID
        initFindViewById();
        //初始化控件
        initViews();
       // setContentView(R.layout.activity_guestchoose);
        numberPicker=(NumberPicker)this.findViewById(R.id.countpicker);
        numberPicker.setFormatter(this); //格式化数字，重写format方法
        numberPicker.setOnValueChangedListener(this); //值变化监听事件
        numberPicker.setOnScrollListener(this); //滑动监听事件
        numberPicker.setMinValue(0);//最小值
        numberPicker.setMaxValue(20);//最大值
        numberPicker.setValue(1);//设置初始选定值
        button=(Button)this.findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Guestchoose.this,BrowseFragment.class);

                Bundle bundle = new Bundle();
                bundle.putInt("result",result);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                //intent.putExtras(bundle);
                Log.e("Button!!!!!!!!!!!!!",String.valueOf(result));
                finish();

                //discoverFragment.setArguments(bundle);
                //startActivity(intent);

            }
        });
    }


    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING://用户按下去然后滑动
                Toast.makeText(this, "scroll state fling", Toast.LENGTH_LONG)
                        .show();
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE://正在滑动中的状态
                Toast.makeText(this, "scroll state idle", Toast.LENGTH_LONG).show();
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://NumberPicker停止滑动
                Toast.makeText(this, "scroll state touch scroll", Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }
    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        tmpStr = value+"人";
        return tmpStr;
    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {//值变化监听事件
        Toast.makeText(this, "值发生变化 ，原值： " + oldVal + " ， 新值:"+ newVal, Toast.LENGTH_SHORT).show();
        result = newVal;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件的ID
     */
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guestchoose;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }

    /**
     * 找到控件ID
     */
    @Override
    protected void initFindViewById() {

    }
}