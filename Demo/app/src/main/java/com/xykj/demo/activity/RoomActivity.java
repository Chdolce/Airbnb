package com.xykj.demo.activity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.R;
import com.xykj.demo.view.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //传入数据
    public static final String House_name = "house_name";//详细名称
    public static final String House_type = "house_type";
    public static final String House_price = "price";
    public static final String House_show = "show_house";
    public static final String House_ID = "house_id";
    public static final String House_title = "house_title";
    public static final String House_country = "house_country";
    public static final String House_city = "house_city";
    public static final String House_province = "house_province";
    public static final String House_longitude = "house_longitude";
    public static final String House_latitude = "house_latitude";
    public static final String House_capacity = "house_capacity";
    public static final String House_picture = "house_picture";
    private AirbnbApp app;
    //图片轮播
    private ViewPager vp;
    private LinearLayout ll_point;
    private int[] imageResIds; //存放图片资源id的数组
    private ArrayList<ImageView> imageViews; //存放图片的集合
    private int lastPosition;
    private boolean isRunning = false; //viewpager是否在自动轮播
    //地图 加入“移动到我的位置”功能
    private BaiduMap baiduMap;
    private boolean isFirstLocation = true;// 是否首次定位
    //地图显示
    private MapView mMapView ;
    public LocationClient mLocationClient;
    private TextView positionText;
    private Button Booknow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化客户端
        mLocationClient = new LocationClient(getApplicationContext());
       //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_room);

        Booknow = (Button)findViewById(R.id.reserve);

        final Intent intent = getIntent();
        final String housename = intent.getStringExtra(House_name);
        final String housetype = intent.getStringExtra(House_type);
        final int houseprice = intent.getIntExtra(House_price,0);
       // final int houseshow = intent.getIntExtra(House_show,0);
        //final int houseshow = intent.getIntExtra(House_show,0);
        final String housepicture = intent.getStringExtra(House_picture);
        final String housetitle = intent.getStringExtra(House_title);
        double latitude = intent.getDoubleExtra(House_latitude,0);
        double longitude = intent.getDoubleExtra(House_longitude,0);
        //String housecapacuty = intent.getStringExtra(House_capacity);
        int housecapacuty = intent.getIntExtra(House_capacity,0);
        final int houseid = intent.getIntExtra(House_ID,0);
        String housecity = intent.getStringExtra(House_city);
        String housecountry = intent.getStringExtra(House_country);
        String houseprovince = intent.getStringExtra(House_province);
        //绑定控件
        TextView housecapacityText = (TextView)findViewById(R.id.house_capacity);
        TextView housenameText = (TextView)findViewById(R.id.house_name);
        TextView housetitleText = (TextView)findViewById(R.id.house_title);
        TextView housetypeText = (TextView)findViewById(R.id.house_type);
        TextView housepriceText = (TextView)findViewById(R.id.house_price);
        CircleImageView housephotoView = (CircleImageView)findViewById(R.id.house_photo);
       // CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        //Text显示位置
        positionText = (TextView)findViewById(R.id.position_text_view);
        Glide.with(this).load(housepicture).into(housephotoView);
        //collapsingToolbarLayout.setTitle(housename);
        housecapacityText.setText(housecapacuty+"位房客");
        housetitleText.setText(housetitle);
        housenameText.setText(housename);
        housetypeText.setText(housetype);
        housepriceText.setText("¥" + houseprice + "/晚");
       positionText.setText(housecity + ", " + houseprovince + ", " + housecountry);
        Booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // intent.putExtra("housename",housename);
                // intent.putExtra("housetitle",housetitle);
                //intent.putExtra("houseprice",houseprice+"");
                app = (AirbnbApp)getApplication();
                if(app.getLoginUser() == 1){
                    Intent intent = new Intent(RoomActivity.this, BookActivity.class);
                    intent.putExtra("housepicture",housepicture);
                    Log.e("RoomActivity!!",housepicture);
                    intent.putExtra("houseprice",String.valueOf(houseprice));
                    intent.putExtra("houseid",String.valueOf(houseid));
                    //intent.putExtra("housepicture",String.valueOf(housepicture));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(RoomActivity.this,LoginActicity.class);
                    startActivity(intent1);
                }

            }
        });
        //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        // 运行时权限
        // 有一个权限不处理，则无法定位
        // 处理权限
        List<String> permissionList = new ArrayList<>();
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        baiduMap = mMapView.getMap();

        if(ContextCompat.checkSelfPermission(this,Manifest
                .permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            // 未授权: 精确定位
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.
                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            // 未授权: 读取手机状态权限
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            // 未授权: 存储卡写入权限
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            // 有未授权权限
            String[] permissions  =permissionList.toArray(new String[permissionList.
                    size()]);
            ActivityCompat.requestPermissions(this, permissions,1);
        }else {
            requestLocation();
    }

        //V--view视图
        initViews();
        //M--model数据
        initData();
        //C--control控制器(即适配器)
        initAdapter();
        //开启图片的自动轮询
        new Thread(){
            @Override
            public void run() {
                isRunning = true;
                while(isRunning){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() { //在子线程中开启子线程
                            //往下翻一页（setCurrentItem方法用来设置ViewPager的当前页）
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();
    //调用地图
        navigateToo(latitude,longitude);
    }

    //定位结果回调到之前注册的监听器中
    private void requestLocation() {
        //启动定位客户端
        initLocation();
        mLocationClient.start();
    }
    //实时更新当前位置
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        //每5秒更新一次
        option.setIsNeedAddress(true);
        //获取当前位置详细的地址信息
       // option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        //将定位模式置顶成传感器
        mLocationClient.setLocOption(option);
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    //对权限申请结果的逻辑处理
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int result : grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序"
                                    ,Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        isRunning = false;
        mLocationClient.stop();
        mMapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    //指定地点显示
    private void navigateToo(double house_latitude, double house_longitude){
        if (isFirstLocation){
            LatLng target = new LatLng(house_latitude,house_longitude);
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(target);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocation = false;
        }
        //事先调用让设备当前位置显示在地图上
        baiduMap.setMyLocationEnabled(true);
        //显示当前设备所在位置，光标显示120.114199,30.305983
        //封装设备当前所在位置
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(house_latitude);//纬度
        locationBuilder.longitude(house_longitude);//经度
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);

        StringBuilder currentPosition = new StringBuilder();

    }

    private void initViews() {
        //初始化放小圆点的控件
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        //初始化ViewPager控件
        vp = (ViewPager) findViewById(R.id.vp);
        //设置ViewPager的滚动监听
        vp.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);
    }

    private void initData() {
        //初始化填充ViewPager的图片资源
        imageResIds = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
        //保存图片资源的集合
        imageViews = new ArrayList<>();
        ImageView imageView;
        View pointView;
        //循环遍历图片资源，然后保存到集合中
        for (int i = 0; i < imageResIds.length; i++) {
            //添加图片到集合中
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViews.add(imageView);

            //加小白点，指示器
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_selector); //使用选择器设置背景
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {
                //如果不是第一个点，则设置点的左边距
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false); //默认都是暗色的
            ll_point.addView(pointView, layoutParams);
        }
    }

    private void initAdapter() {
        ll_point.getChildAt(0).setEnabled(true); //初始化控件时，设置第一个小圆点为亮色
        lastPosition = 0; //设置之前的位置为第一个
        vp.setAdapter(new MyPagerAdapter());
        //设置默认显示中间的某个位置（这样可以左右滑动），这个数只有在整数范围内，可以随便设置
        vp.setCurrentItem(5000000); //显示5000000这个位置的图片
    }

    //界面销毁时，停止viewpager的轮询

    class MyPagerAdapter extends PagerAdapter {

        //返回显示数据的总条数，为了实现无限循环，把返回的值设置为最大整数
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        //指定复用的判断逻
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //当创建新的条目，又反回来，判断view是否可以被复用(即是否存在)
            return view == object;
        }

        //返回要显示的条目内容
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container  容器  相当于用来存放imageView
            //从集合中获得图片
            int newPosition = position % 5; //数组中总共有5张图片，超过数组长度时，取模，防止下标越界
            ImageView imageView = imageViews.get(newPosition);
            //把图片添加到container中
            container.addView(imageView);
            //把图片返回给框架，用来缓存
            return imageView;
        }

        //销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //object:刚才创建的对象，即要销毁的对象
            container.removeView((View) object);
        }
    }
    //页面滑动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //新的页面被选中
    @Override
    public void onPageSelected(int position) {
        //当前的位置可能很大，为了防止下标越界，对要显示的图片的总数进行取余
        int newPosition = position % 5;
        //设置小圆点为高亮或暗色
        ll_point.getChildAt(lastPosition).setEnabled(false);
        ll_point.getChildAt(newPosition).setEnabled(true);
        lastPosition = newPosition; //记录之前的点
    }

    //页面滑动状态发生改变
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //Toolbar 实现
    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
//                Toast.makeText(this,"you clicked backup",Toast.LENGTH_SHORT)
//                        .show();
//
                finish();
                return true;
            case R.id.share:
                Toast.makeText(this,"you clicked share",Toast.LENGTH_SHORT)
                        .show();
            case R.id.like:
                Toast.makeText(this,"you clicked like",Toast.LENGTH_SHORT)
                        .show();
            default:
        }
        return true;
    }

}
