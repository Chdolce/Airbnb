package com.xykj.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xykj.demo.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText etLoginname;
    private EditText etPwd;
    private EditText etRealname;
    private EditText etPhone;
    private EditText etId;
    private Button btnLogin;
    private EditText etImage;
    public static final String Register = "http://192.168.43.237:80/final/register.jsp";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        imageView = (ImageView) findViewById(R.id.ivBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, LoginActicity.class);
                startActivity(intent);
            }
        });

        etLoginname = (EditText) findViewById(R.id.etLoginname);
        etPwd = (EditText) findViewById(R.id.etPwd);
        etRealname = (EditText) findViewById(R.id.etRealname);
        etId = (EditText) findViewById(R.id.etId);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etImage = (EditText)findViewById(R.id.etImage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUser()) {
                    String userEmail = etLoginname.getText().toString().trim();
                    String userPwd = etPwd.getText().toString().trim();
                    String userName = etRealname.getText().toString().trim();
                    String userId = etId.getText().toString().trim();
                    String userPhone = etPhone.getText().toString().trim();
                    String userImage = etImage.getText().toString().trim();

                    register();
                }
            }
        });

    }

    private void register(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    final String account_id = etId.getText().toString();
                    final String account_name = etRealname.getText().toString();
                    final String account_email = etLoginname.getText().toString();
                    final String account_phone = etPhone.getText().toString();
                    final String account_password = etPwd.getText().toString();
                    final String account_iamge = etImage.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("account_id", account_id)
                            .add("account_name", account_name)
                            .add("account_email", account_email)
                            .add("account_phone", account_phone)
                            .add("account_password", account_password)
                            .add("account_image",account_iamge)
                            .build();
                    Request request = new Request.Builder()
                            .url(Register)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String data = response.body().string();
                    final String result = data;
                    if(data.trim().equals("1")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this, "注册成功，即将跳转到登录界面", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistActivity.this,LoginActicity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    else if(data.trim().equals("2")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this,"该用户名已被使用",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public boolean isUser() {
        if (etLoginname.getText().toString().trim().equals("")) {
            Toast.makeText(this, "邮箱为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (etRealname.getText().toString().trim().equals("")) {
            Toast.makeText(this, "名字为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (etId.getText().toString().trim().equals("")) {
            Toast.makeText(this, "ID为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPhone.getText().toString().trim().equals("")) {
            Toast.makeText(this, "手机号为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




}

