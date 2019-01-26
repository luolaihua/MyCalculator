package com.luo.matrixcaculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Analysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Intent intent = getIntent();

        //String m = intent.getStringExtra("dataA");
        double[] m=intent.getDoubleArrayExtra("dataA");
        TextView textView = (TextView) findViewById(R.id.show);
       // textView.setText(m[1]+"  "+m[2]+"  "+m[3]+"  "+m[4]+"  "+m[5]+"  "+m[6]+"  "+m[7]+"  ");
        textView.setText(m[0]+"");
    }
}
