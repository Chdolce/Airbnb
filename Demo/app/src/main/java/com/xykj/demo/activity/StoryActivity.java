package com.xykj.demo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xykj.demo.Class.AirbnbApp;
import com.xykj.demo.Class.Comment;
import com.xykj.demo.R;
import com.xykj.demo.adapter.CommentAdapter;
import com.xykj.demo.base.HttpUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {
    //传入数据
    /*
    *private String story_name;
    private String story_title;
    private int story_imgId;
     */

    //全局变量
    private AirbnbApp app;
    public static final String Story_name = "story_name";
    public static final String Story_title = "story_title";
    public static final String Story_imgID = "story_imgId";
    public static final String Author_id = "author_id";
    public static final String Author_name = "author_name";
    public static final String Author_img = "author_img";
    public static final String Story_content = "story_content";
    public static final String Story_img = "story_img";

    private ImageView bingPicImg;

    private TextView storytitleText;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView storyView;
    private TextView storyTitle;
    private ImageView comment;
    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;
    private TextView comment_count;
    private TextView comment_time;
    TextView authornameText;
    ImageView authorimgView;
    TextView storycontentText;
    private LinearLayout rl_enroll;
    private RelativeLayout rl_comment;

    private ListView comment_list;
    private CommentAdapter adapterComment;
    private List<Comment> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        //绑定控件
        initView();
        //加载网络图片
        loadBingPic();
        //加载顶部导航栏
        setSupportActionBar(toolbar);
        //获取Fragment数据
        Intent intent = getIntent();
        String storyname = intent.getStringExtra(Story_name);
        String storytitle = intent.getStringExtra(Story_title);
        String authorimg = intent.getStringExtra(Author_img);
        String authorname = intent.getStringExtra(Author_name);
        String storycontent = intent.getStringExtra(Story_content);
        String storyimg = intent.getStringExtra(Story_img);
        int storyID = intent.getIntExtra(Story_imgID,0);

        //ActionBar actionBar = getSupportActionBar();
        collapsingToolbarLayout.setTitle(storyname);
        Glide.with(this).load(storyimg).into(storyView);
       // Glide.with(this).load(storyID).into(storyView);
       // String storyContent = generateStoryContent(storytitle,storyname);
        storytitleText.setText(storycontent);
        storyTitle.setText(storytitle);
        authornameText.setText(authorname);
        Glide.with(this).load(authorimg).into(authorimgView);
    }

    @SuppressLint("WrongViewCast")
    private void initView() {

        //绑定控件
        bingPicImg = (ImageView)findViewById(R.id.bing_pic_img);

        storytitleText = (TextView)findViewById(R.id.story_content);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        storyView = (ImageView)findViewById(R.id.story_image_view);
        storyTitle = (TextView)findViewById(R.id.house_title);
        authorimgView = (ImageView)findViewById(R.id.story_author_photo);
        authornameText = (TextView)findViewById(R.id.story_author_name);
        storycontentText = (TextView)findViewById(R.id.story_content);
        app = (AirbnbApp) getApplication();

        // 初始化评论列表
        comment_list = (ListView) findViewById(R.id.comment_list);
        // 初始化数据
        data = new ArrayList<>();
        // 初始化适配器
        adapterComment = new CommentAdapter(getApplicationContext(), data);
        // 为评论列表设置适配器
        comment_list.setAdapter(adapterComment);

        comment = (ImageView) findViewById(R.id.comment);
        hide_down = (TextView) findViewById(R.id.hide_down);
        comment_content = (EditText) findViewById(R.id.comment_content);
        comment_send = (Button) findViewById(R.id.comment_send);
        comment_count = (TextView)findViewById(R.id.comment_count);
        comment_time = (TextView)findViewById(R.id.comment_time);

        rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);

        setListener();
    }

    /**
     * 设置监听
     */
    public void setListener(){
        comment.setOnClickListener(this);

        hide_down.setOnClickListener(this);
        comment_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down:
                // 隐藏评论框
                rl_enroll.setVisibility(View.VISIBLE);
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                if(app.getLoginUser() == 1){
                    sendComment();
                }
                else {
                    Toast.makeText(getApplicationContext(),"未登录不能评论！！",Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    /**
     * 发送评论
     */
    @SuppressLint("SetTextI18n")
    public void sendComment(){
        if(comment_content.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            // 生成评论数据
            Comment comment = new Comment();
            //修改成当前用户的ID
            //comment.setName("我");

            comment.setName( app.getLoginUser_name());
            comment.setContent(comment_content.getText().toString());
            comment_count.setText(data.size()+1+"条评论");
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setTime(df.format(day));
            adapterComment.addComment(comment);
            // 发送完，清空输入框
            comment_content.setText("");

            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    //填充故事内容
    private String generateStoryContent(String storytitle,String storyname) {
        StringBuilder storyContent = new StringBuilder();
        for(int i = 0; i < 25;i++){
            storyContent.append(storytitle);
            storyContent.append(storyname);
        }
        return storyContent.toString();
    }

    /*
     *加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
//                SharedPreferences.Editor editor = PreferenceManager.
//                        getDefaultSharedPreferences(StoryActivity.this).edit();
//                editor.putString("bing_pic",bingPic);
//                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(StoryActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

        });
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
