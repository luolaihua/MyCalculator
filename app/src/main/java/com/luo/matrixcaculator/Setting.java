package com.luo.matrixcaculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Setting extends AppCompatActivity {
private Spinner spinner;
private static int num = 3;
    private List<String> list_num = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);



        spinner = (Spinner) findViewById(R.id.num);

        list_num.add("取整");
        list_num.add("1");
        list_num.add("2");
        list_num.add("3");
        list_num.add("4");
        list_num.add("5");
        list_num.add("6");
        list_num.add("7");


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list_num);
        spinner.setAdapter(adapter);
        spinner.setSelection(num,true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences.Editor editor = getSharedPreferences("data_num",MODE_PRIVATE).edit();
                editor.putInt("num", position);
                editor.apply();
                //Toast.makeText(Setting.this,"小数点保留："+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
