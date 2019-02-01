package com.luo.matrixcaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {
private TextView tv_num,tv_jian,tv_add;
private static int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        tv_add = (TextView) findViewById(R.id.count_jia);
        tv_num = (TextView) findViewById(R.id.count_num);
        tv_jian = (TextView) findViewById(R.id.count_jian);

        tv_jian.setOnClickListener(this);
        tv_num.setOnClickListener(this);
        tv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count_jia:
                num++;
                tv_num.setText(""+num);
                break;
            case R.id.count_jian:
                num--;
                tv_num.setText(""+num);
                break;
            case R.id.count_num:
                break;

        }
    }
}
