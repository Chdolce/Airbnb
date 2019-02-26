package com.xykj.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.R;
import com.xykj.demo.fragment.MineFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActicity extends AppCompatActivity{
    private AirbnbApp app;
    private EditText mEmail;
    private EditText mPassWord;
    private TextView mRegist;
    private String account_name;
    private String account_img;
    private int account_id;
    String email = null;
    String password = null;
    private ImageView imageView;
    public static final String Login="http://192.168.43.237:80/final/login.jsp";
    public static final String Web = "http://192.168.43.237:80/final/air.jsp";

//    //将构造函数私有化
//    private LoginActicity(){}
//    //创建私有实例对象
//    private static final LoginActicity mLoginActivity = new LoginActicity();
//    //对外提供方法，返回实例对象
//    public static LoginActicity getInstance()

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mButton = (Button)findViewById(R.id.btnLogin);
        mEmail = (EditText)findViewById(R.id.etLoginname);
        mPassWord = (EditText)findViewById(R.id.etPwd);
        mRegist = (TextView)findViewById(R.id.tvNewAccount);
        imageView = (ImageView)findViewById(R.id.ivBack);




        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                jsonObject();
            }

        });
        mRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActicity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActicity.this, MainActivity.class);
                //intent.putExtra("id", 4);
                startActivity(intent);
            }
        });

    }

    private void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    final String email = mEmail.getText().toString();
                    final String pass = mPassWord.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("account_email", email)
                            .add("account_password", pass)
                            .build();
                    Request request = new Request.Builder()
                            .url(Login)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String data = response.body().string();
                    final String result = data;
                    if(data.trim().equals("1")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActicity.this,"登录成功",Toast.LENGTH_SHORT).show();
                               app = (AirbnbApp)getApplication();
                               app.setLoginUser_name(account_name);
                               app.setLoginUser_id(account_id);
                               app.setLoginUser_img(account_img);
                              // app.isLogin();
                                app.setLoginUser(1);
                                Log.e("姓名载入成功",app.getLoginUser_name());
                                Log.e("图片载入成功",app.getLoginUser_img());
                                Intent intent =  new Intent(LoginActicity.this, MineFragment.class);
                                //startActivity(intent);
                                Bundle bundle = new Bundle();
                                bundle.putString("isupdate", "yes");
                                intent.putExtras(bundle);
                                setResult(0,intent);
                                finish();
                            }
                        });
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActicity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();;
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jsonObject(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(Web)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String data = response.body().string();
                    parseJsonObject(data);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private String parseJsonObject(String jsonStr){
        StringBuilder builder = new StringBuilder();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for( int i = 0; i < jsonArray.length(); ++i ){
                JSONObject object = jsonArray.getJSONObject(i);
                account_name = object.getString("account_name");
                account_id = object.getInt("account_id");
                account_img = object.getString("account_image");
                Log.e("LoaginActivity 我叫什么@@@@", account_name);
                Log.e("我也有图片啊啊啊",account_img);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return builder.toString();
    }

}
