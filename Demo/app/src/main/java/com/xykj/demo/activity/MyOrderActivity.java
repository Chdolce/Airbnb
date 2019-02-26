package com.xykj.demo.activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xykj.demo.Class.Order_block;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.R;
import com.xykj.demo.adapter.OrderAdapter;
import com.xykj.demo.fragment.MineFragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyOrderActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private OrderAdapter mOrder__adapter;
    private RecyclerView recyclerView1;
    private Button backtoMy;
    Date date=new Date(2018,12,6);
    Date date2=new Date(2018,12,6);;
    private Order_block[] orders = {
            new Order_block("杭州的独立房间",date,date2,78.0,R.drawable.two,2),
    };
    private List<Order_block> mOrder_List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initOrder();
        recyclerView1 = (RecyclerView) findViewById(R.id.order_recycle);

        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 1);
        recyclerView1.setLayoutManager(layoutManager1);
        mOrder__adapter = new OrderAdapter(mOrder_List);
        recyclerView1.setAdapter(mOrder__adapter);
      //  System.out.println("hehwd");
        backtoMy=(Button)findViewById(R.id.backtomine) ;
        backtoMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyOrderActivity.this, MainActivity.class);
                //i.putExtra("id",5);
                startActivity(i);
                //finish();
            }
        });


    }



    private void initOrder(){
        mOrder_List.clear();
        for(int i = 0 ; i<18;++i){
            Random random = new Random();
            int index = random.nextInt(orders.length);
            mOrder_List.add(orders[index]);
        }
    }
}
