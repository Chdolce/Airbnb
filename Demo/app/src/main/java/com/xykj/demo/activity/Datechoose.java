package com.xykj.demo.activity;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.R;
import com.xykj.demo.fragment.BrowseFragment;
import com.xykj.demo.view.AirCalendarView;

import java.util.Calendar;

public class Datechoose extends AppCompatActivity {

    private Button comfirm;
    private AirbnbApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datechoose);
        Toolbar toolbar = (Toolbar)findViewById(R.id.backto);
        comfirm = (Button)findViewById(R.id.sure);
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Datechoose.this,BrowseFragment.class);

                Bundle bundle = new Bundle();
                app = (AirbnbApp)getApplication();
                app.setStartDay(22);
                app.setEndDay(24);
                bundle.putString("Start","12/22-12/24");
                intent.putExtras(bundle);
                setResult(Activity.RESULT_CANCELED,intent);
                //intent.putExtras(bundle);
               // Log.e("Button!!!!!!!!!!!!!",String.valueOf(result));
                finish();
            }
        });
        AirCalendarView calendarView = (AirCalendarView) findViewById(R.id.calendarView);
        calendarView.setOnSelectedDayListener(new AirCalendarView.OnSelectedDayListener() {
            @Override
            public void onDaySelected(Calendar startDay, Calendar endDay) {
                Log.e("start:", startDay + ",end:" + endDay) ;

            }
        });
        setSupportActionBar(toolbar);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
//                Toast.makeText(this,"you clicked backup",Toast.LENGTH_SHORT)
//                        .show();
                Intent intent = new Intent(Datechoose.this,BrowseFragment.class);

                Bundle bundle = new Bundle();
                bundle.putString("Start","日期");
                intent.putExtras(bundle);
                setResult(Activity.RESULT_CANCELED,intent);
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
