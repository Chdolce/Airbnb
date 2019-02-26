package com.xykj.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.Class.CheckDay;
import com.xykj.demo.R;
import com.xykj.demo.view.CircleImageView;

import org.w3c.dom.Text;

import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookActivity extends AppCompatActivity {

    private AirbnbApp app;
    private CheckDay checkDay;
    private int houseid;
    Button book;
    TextView price;
    ImageView housephotoView;
    TextView allprice;
    TextView peoplenum;
    TextView user_name;
    CircleImageView user_img;
    EditText checkinday;
    EditText checkoutday;
    TextView checkBox;
    private int sday,eday,num;
    int houseprice;
    private static final String reserve = "http://192.168.43.237:80/final/reserve.jsp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        app = (AirbnbApp)getApplication();
        checkDay = new CheckDay();
        book = (Button) findViewById(R.id.book);
        price = (TextView) findViewById(R.id.price);
        housephotoView = (ImageView) findViewById(R.id.house_show);
        allprice = (TextView)findViewById(R.id.allprice);
        peoplenum = (TextView)findViewById(R.id.people_count);
        user_img = (CircleImageView)findViewById(R.id.user_pic);
        user_name = (TextView)findViewById(R.id.uesr_name);
        checkinday = (EditText) findViewById(R.id.checkin_day);
        checkoutday = (EditText) findViewById(R.id.checkout_day);
        checkBox = (TextView)findViewById(R.id.checkbox);
        Intent intent = getIntent();
        //获取Intent中的数据：
        // price.setText("¥"+intent.getStringExtra("houseprice")+"/晚");
        houseprice = Integer.parseInt(intent.getStringExtra("houseprice"));
        String housepicture = intent.getStringExtra("housepicture");
        houseid = Integer.parseInt(intent.getStringExtra("houseid"));
        onClick(checkBox);
        Log.e("BookActivity!!!!! 天天天",String.valueOf(checkDay.getEndDay()));
        price.setText("¥" + houseprice + "/晚");

    //    checkinday.setText("12月"+app.getStartDay()+"日");
    //    checkoutday.setText("12月"+app.getEndDay()+"日");
   //     int day = app.getEndDay() - app.getStartDay();
        checkinday.setText(checkDay.getStartMouth()+"-"+checkDay.getStartDay());
        checkoutday.setText(checkDay.getEndMouth()+"-"+checkDay.getEndDay());
        peoplenum.setText(app.getLonginUser_num()+"位");

        user_name.setText(app.getLoginUser_name());
        Glide.with(this).load(app.getLoginUser_img()).into(user_img);
        Log.e("BookAcyivity!!!",housepicture);
        Glide.with(this).load(housepicture).into(housephotoView);
        Log.e("BookActivity id 快出来",String.valueOf(app.getLoginUser_id()));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve();
            }
        });
    }

    public void onClick(View view) {
        if (isJoin()){
            String _time_in =  checkinday.getText().toString().trim();

//           checkDay.ReceveDays(_time_in);
            eday = checkDay.getEndDay();
            String _time_out = checkoutday.getText().toString().trim();
   //        checkDay.ReceveDays(_time_out);
            sday  = checkDay.getStartDay();
            num = eday - sday;
            allprice.setText("¥" + (num*(houseprice) - 20));
        }

    }
    private void reserve(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    final String guest_id = String.valueOf(app.getLoginUser_id());
                    final String house_name = String.valueOf(houseid);
                    final String start_time = "2018-"+checkinday.getText().toString();;
                    final String finish_time = "2018-"+checkoutday.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("guest_id", guest_id)
                            .add("house_name", house_name)
                            .add("start_time", start_time)
                            .add("finish_time", finish_time)
                            .build();
                    Request request = new Request.Builder()
                            .url(reserve)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String data = response.body().string();
                    final String result = data;
                    if(data.trim().equals("1")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BookActivity.this, "预订成功，即将跳转到支付界面", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BookActivity.this,LoadingActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
//                    else if(data.trim().equals("2")){
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(BookActivity.this,"该id已经预订了",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BookActivity.this,"预订失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public boolean isJoin() {
         if (checkinday.getText().toString().trim().equals("")) {
            Toast.makeText(this, "入住时间为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkoutday.getText().toString().trim().equals("")) {
            Toast.makeText(this, "退房时间为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
