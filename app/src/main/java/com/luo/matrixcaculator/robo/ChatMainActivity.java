package com.luo.matrixcaculator.robo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.luo.matrixcaculator.BaseActivity;
import com.luo.matrixcaculator.FourCompute;
import com.luo.matrixcaculator.MyApplication;
import com.luo.matrixcaculator.R;
import com.luo.matrixcaculator.ThreeCompute;
import com.luo.matrixcaculator.TwoCompute;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatMainActivity extends BaseActivity implements View.OnClickListener {

    //  聊天消息列表
    private RecyclerView recyclerView;

    //  输入框
    private EditText editText;

    //  发送按钮
    private Button mButton;

    //    对话信息集合
    private List<Chat> list = new ArrayList<>();



    //    适配器
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_two:
                        Intent intent = new Intent(MyApplication.getContext(), TwoCompute.class);
                        startActivity(intent);finish();break;
                    case R.id.nav_three:
                        Intent intent1 = new Intent(MyApplication.getContext(), ThreeCompute.class);
                        startActivity(intent1);finish();break;
                    case R.id.nav_four:
                        Intent intent2 = new Intent(MyApplication.getContext(), FourCompute.class);
                        startActivity(intent2);finish();break;
                    case R.id.nav_help:
                        Intent intent3 = new Intent(MyApplication.getContext(), ChatMainActivity.class);
                        startActivity(intent3);finish();break;
                    case R.id.nav_home:
                        finish();break;
                }


                return true;
            }
        });






//      初始化数据
        initView();
//       加载数据
        initData();
//      设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * 加载列表布局数据
     */
    private void initData() {
        Chat c1 = new Chat("你好，我是罗小炮", Chat.TYPE_RECEIVED);
        Chat c2 = new Chat("谈恋爱选我，我超甜", Chat.TYPE_RECEIVED);
        list.add(c1);
        list.add(c2);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        recyclerView = findViewById(R.id.recycler);
        editText = findViewById(R.id.et_text);
        mButton = findViewById(R.id.btn_send);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                /**
                 * 1，获取输入框的内容
                 * 2，判断是否为空
                 * 3，发送后清空当前的输入框
                 */
//              1,获取输入框的内容
                String text = editText.getText().toString();
//              2,判断是否为空
                if (!TextUtils.isEmpty(text)) {
//                  把要发送的数据添加到addData方法中，并把数据类型也填入，这里我们的类型是TYPE_SENT，发送数据类型
                    addData(text, Chat.TYPE_SENT);
//                  清空输入框
                    editText.setText("");
//                  把发送的文本数据传递到request方法中，请求数据
                    request(text);
                }
                break;
        }
    }

    /**
     * 通过传递进来的test和type创建数据实体类，添加到聊天数据集合list中
     * @param text
     * @param type
     */
    private void addData(String text, int type) {
        Chat c = new Chat(text, type);
        list.add(c);
        //当有新消息时，刷新显示
        recyclerViewAdapter.notifyItemInserted(list.size() - 1);
        //定位的最后一行
        recyclerView.scrollToPosition(list.size() - 1);
    }

    /**
     * 请求数据
     *
     * @param text 输入框的发送数据
     */
    private void request(String text) {
//      把输入的文本数据存储在请求实体类中
        Ask ask = new Ask();
        Ask.UserInfoBean info = new Ask.UserInfoBean();
        info.setApiKey("4fd1b0d820bd46f09790f8615dbd460a");//将机器人的key值填入
        info.setUserId("381611");//将用户id填入
        ask.setUserInfo(info);
        Ask.PerceptionBean.InputTextBean pre = new Ask.PerceptionBean.InputTextBean(text);//将要发送给机器人书文本天趣
        ask.setPerception(new Ask.PerceptionBean(pre));

//       创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.tuling123.com/")//设置网络请求url，后面一段写在网络请求接口里面
                .addConverterFactory(GsonConverterFactory.create())//Gson解析
                .build();
//       创建网络请求接口的实例
        Api api = retrofit.create(Api.class);
//      Take为响应实体类，用来接受机器人返回的回复数据
        Call<Take> call = api.request(ask);
//
        call.enqueue(new Callback<Take>() {
            //          请求成功
            @Override
            public void onResponse(Call<Take> call, Response<Take> response) {
//              接受到的机器人回复的数据
                String mText= response.body().getResults().get(0).getValues().getText();
//              把接受到的数据传入addData方法中，类型是TYPE_RECEIVED接受数据
                addData(mText, Chat.TYPE_RECEIVED);
                L.d("接受到的机器人回复的数据： "+mText);
            }
            //            请求失败
            @Override
            public void onFailure(Call<Take> call, Throwable t) {
                L.d("请求失败： "+t.toString());
            }
        });
    }
}