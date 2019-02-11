package com.luo.matrixcaculator;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.luo.matrixcaculator.robo.ChatMainActivity;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spinner_row_a, spinner_column_a,spinner_row_b, spinner_column_b;
    private TextView tv_result,tv_equal,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran,tv_clearAll,testA ;
    private EditText et_a,et_b,b0,b1;
    private String input_a,input_b;
    private List<String> row_a = new ArrayList<>();
    private List<String> column_a = new ArrayList<>();
    private List<String> row_b = new ArrayList<>();
    private List<String> column_b = new ArrayList<>();
    private static int num_row_a=1,num_row_b=1,num_column_a=1,num_column_b=1;//a b行列的数量
    private  int flag = 0;//加减乘除的flag
    private double[][] result;// 结果
    private static int num = 3;//小数点保留位数
    private LinearLayout linear_a1,linear_a2,linear_a3,linear_a4,linear_a5;
    private LinearLayout linear_b1,linear_b2,linear_b3,linear_b4,linear_b5;
    static double id_a[][] = new double[5][5];
    static double id_b[][] = new double[5][5];
    static double data_a[][] = new double[5][5];
    static double data_b[][] = new double[5][5];



    private DrawerLayout drawerLayout;

//获取小数点保留位数
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_a[0][0] = R.id.a_00;
        id_a[0][1] = R.id.a_01;
        id_a[0][2] = R.id.a_02;
        id_a[0][3] = R.id.a_03;
        id_a[0][4] = R.id.a_04;
        id_a[1][0] = R.id.a_10;
        id_a[1][1] = R.id.a_11;
        id_a[1][2] = R.id.a_12;
        id_a[1][3] = R.id.a_13;
        id_a[1][4] = R.id.a_14;
        id_a[2][0] = R.id.a_20;
        id_a[2][1] = R.id.a_21;
        id_a[2][2] = R.id.a_22;
        id_a[2][3] = R.id.a_23;
        id_a[2][4] = R.id.a_24;
        id_a[3][0] = R.id.a_30;
        id_a[3][1] = R.id.a_31;
        id_a[3][2] = R.id.a_32;
        id_a[3][3] = R.id.a_33;
        id_a[3][4] = R.id.a_34;
        id_a[4][0] = R.id.a_40;
        id_a[4][1] = R.id.a_41;
        id_a[4][2] = R.id.a_42;
        id_a[4][3] = R.id.a_43;
        id_a[4][4] = R.id.a_44;



        id_b[0][0] = R.id.b_00;
        id_b[0][1] = R.id.b_01;
        id_b[0][2] = R.id.b_02;
        id_b[0][3] = R.id.b_03;
        id_b[0][4] = R.id.b_04;
        id_b[1][0] = R.id.b_10;
        id_b[1][1] = R.id.b_11;
        id_b[1][2] = R.id.b_12;
        id_b[1][3] = R.id.b_13;
        id_b[1][4] = R.id.b_14;
        id_b[2][0] = R.id.b_20;
        id_b[2][1] = R.id.b_21;
        id_b[2][2] = R.id.b_22;
        id_b[2][3] = R.id.b_23;
        id_b[2][4] = R.id.b_24;
        id_b[3][0] = R.id.b_30;
        id_b[3][1] = R.id.b_31;
        id_b[3][2] = R.id.b_32;
        id_b[3][3] = R.id.b_33;
        id_b[3][4] = R.id.b_34;
        id_b[4][0] = R.id.b_40;
        id_b[4][1] = R.id.b_41;
        id_b[4][2] = R.id.b_42;
        id_b[4][3] = R.id.b_43;
        id_b[4][4] = R.id.b_44;

        linear_a1 = (LinearLayout) findViewById(R.id.linear_a_1);
        linear_a2 = (LinearLayout) findViewById(R.id.linear_a_2);
        linear_a3 = (LinearLayout) findViewById(R.id.linear_a_3);
        linear_a4 = (LinearLayout) findViewById(R.id.linear_a_4);
        linear_a5 = (LinearLayout) findViewById(R.id.linear_a_5);

        linear_b1 = (LinearLayout) findViewById(R.id.linear_b_1);
        linear_b2 = (LinearLayout) findViewById(R.id.linear_b_2);
        linear_b3 = (LinearLayout) findViewById(R.id.linear_b_3);
        linear_b4 = (LinearLayout) findViewById(R.id.linear_b_4);
        linear_b5 = (LinearLayout) findViewById(R.id.linear_b_5);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu33);
        }
       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_two:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(MyApplication.getContext(),TwoCompute.class);
                        startActivity(intent);break;
                    case R.id.nav_three:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(MyApplication.getContext(),ThreeCompute.class);
                        startActivity(intent1);break;
                    case R.id.nav_four:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(MyApplication.getContext(),FourCompute.class);
                        startActivity(intent2);break;
                    case R.id.nav_help:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent3 = new Intent(MyApplication.getContext(), ChatMainActivity.class);
                        startActivity(intent3);break;
                    case R.id.nav_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });




        tv_result = (TextView) findViewById(R.id.main_tv_result);
        tv_add =(TextView) findViewById(R.id.main_tv_add);
        tv_sub = (TextView) findViewById(R.id.main_tv_sub);
        tv_mult = (TextView) findViewById(R.id.main_tv_mult);
        tv_equal = (TextView) findViewById(R.id.main_tv_equal);
        tv_sovle = (TextView) findViewById(R.id.main_sovle);
        tv_sovleTran = (TextView) findViewById(R.id.main_sovleTran);
        tv_clearAll = (TextView) findViewById(R.id.main_clearAll);
        testA = (TextView) findViewById(R.id.testA);


testA.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       Toast.makeText(MainActivity.this,"当前小数点保留 "+num+" 位",Toast.LENGTH_SHORT).show();
    }
});




        et_b = (EditText) findViewById(R.id.et_b);
        et_a = (EditText) findViewById(R.id.et_a);


        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);
        tv_sovle.setOnClickListener(this);
        tv_sovleTran.setOnClickListener(this);
        tv_clearAll.setOnClickListener(this);



        //设置下拉栏属性
        spinner_row_a = (Spinner) findViewById(R.id.row_a);
        spinner_column_a = (Spinner) findViewById(R.id.column_a);
        spinner_row_b = (Spinner) findViewById(R.id.row_b);
        spinner_column_b = (Spinner) findViewById(R.id.column_b);
        row_a.add("1行");
        row_a.add("2行");
        row_a.add("3行");
        row_a.add("4行");
        row_a.add("5行");
        column_a.add("1列");
        column_a.add("2列");
        column_a.add("3列");
        column_a.add("4列");
        column_a.add("5列");
        row_b.add("1行");
        row_b.add("2行");
        row_b.add("3行");
        row_b.add("4行");
        row_b.add("5行");
        column_b.add("1列");
        column_b.add("2列");
        column_b.add("3列");
        column_b.add("4列");
        column_b.add("5列");

        ArrayAdapter<String> adapter_row_a =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,row_a);
        ArrayAdapter<String> adapter_column_a =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,column_a);

        ArrayAdapter<String> adapter_row_b =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,row_b);
        ArrayAdapter<String> adapter_column_b =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,column_b);


        adapter_row_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row_a.setAdapter(adapter_row_a);
        adapter_column_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_column_a.setAdapter(adapter_column_a);

        adapter_row_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row_b.setAdapter(adapter_row_b);
        adapter_column_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_column_b.setAdapter(adapter_column_b);

        //下拉栏点击事件
        spinner_row_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_row_a = position + 1;
                if (num_row_a != num_row_b || num_column_a != num_column_b) {
                    tv_add.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sub.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_add.setClickable(false);
                    tv_sub.setClickable(false);
                }else {
                    tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_add.setClickable(true);
                    tv_sub.setClickable(true);
                }
                if (num_row_a != num_column_b || num_column_a != num_row_b ) {
                    tv_mult.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_mult.setClickable(false);
                }else {
                    tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_mult.setClickable(true);
                }

                //----------A*X=B----ac*cb=ab
                if (num_row_a  != num_row_b  ) {
                    tv_sovle.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovle.setClickable(false);
                }else {
                    tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovle.setClickable(true);
                }
                //----------X*A=B----ac*cb=ab
                if (num_column_a  != num_column_b  ) {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovleTran.setClickable(false);
                }else {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovleTran.setClickable(true);
                }


//-----------------------------------------------------------------------------


                //控件的出现与消失

                //int row = (int) Double.parseDouble(b0.getText().toString());
               // int column = (int) Double.parseDouble(b1.getText().toString());
                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                switch (num_column_a){
                    case 1:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.GONE);
                        linear_a3.setVisibility(View.GONE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 2:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.GONE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 3:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 4:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.VISIBLE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 5:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.VISIBLE);
                        linear_a5.setVisibility(View.VISIBLE);
                        break;


                }
                //让控件全部GONE---初始化
                for (int i = 0; i < id_a.length; i++) {
                    for (int j = 0; j < id_a[i].length; j++) {
                        test = (EditText) findViewById((int)id_a[i][j]);
                        test.setVisibility(View.GONE);
                    }
                }

                //让需要的控件VISIBLE
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        test = (EditText) findViewById(m[i][j]);
                        test.setVisibility(View.VISIBLE);
                    }
                }


               /* if (num_row_a * num_column_a != num_row_b * num_column_b ) {
                    tv_sovle.setBackgroundColor(Color.parseColor("#001D20"));
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#001D20"));
                    tv_sovle.setClickable(false);
                    tv_sovleTran.setClickable(false);
                }else {
                    tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovle.setClickable(true);
                    tv_sovleTran.setClickable(true);
                }*/
                //Toast.makeText(MainActivity.this, num_row_a+"", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_a = position + 1;
                if (num_row_a != num_row_b || num_column_a != num_column_b) {
                    tv_add.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sub.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_add.setClickable(false);
                    tv_sub.setClickable(false);
                }else {
                    tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_add.setClickable(true);
                    tv_sub.setClickable(true);
                }
                if (num_row_a != num_column_b || num_column_a != num_row_b ) {
                    tv_mult.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_mult.setClickable(false);
                }else {
                    tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_mult.setClickable(true);
                }
                //----------A*X=B----ac*cb=ab
                if (num_row_a  != num_row_b  ) {
                    tv_sovle.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovle.setClickable(false);
                }else {
                    tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovle.setClickable(true);
                }
                //----------X*A=B----ac*cb=ab
                if (num_column_a  != num_column_b  ) {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovleTran.setClickable(false);
                }else {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovleTran.setClickable(true);
                }
               // Toast.makeText(MainActivity.this, num_column_a+"", Toast.LENGTH_SHORT).show();



//-----------------------------------------------------------------------------


                //控件的出现与消失

                //int row = (int) Double.parseDouble(b0.getText().toString());
                // int column = (int) Double.parseDouble(b1.getText().toString());
                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                switch (num_column_a){
                    case 1:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.GONE);
                        linear_a3.setVisibility(View.GONE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 2:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.GONE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 3:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.GONE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 4:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.VISIBLE);
                        linear_a5.setVisibility(View.GONE);
                        break;
                    case 5:
                        linear_a1.setVisibility(View.VISIBLE);
                        linear_a2.setVisibility(View.VISIBLE);
                        linear_a3.setVisibility(View.VISIBLE);
                        linear_a4.setVisibility(View.VISIBLE);
                        linear_a5.setVisibility(View.VISIBLE);
                        break;


                }
                //让控件全部GONE---初始化
                for (int i = 0; i < id_a.length; i++) {
                    for (int j = 0; j < id_a[i].length; j++) {
                        test = (EditText) findViewById((int)id_a[i][j]);
                        test.setVisibility(View.GONE);
                    }
                }

                //让需要的控件VISIBLE
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        test = (EditText) findViewById(m[i][j]);
                        test.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_row_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_row_b = position+1;
                if (num_row_a != num_row_b || num_column_a != num_column_b) {
                    tv_add.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sub.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_add.setClickable(false);
                    tv_sub.setClickable(false);
                }else {
                    tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_add.setClickable(true);
                    tv_sub.setClickable(true);
                }
                if (num_row_a != num_column_b || num_column_a != num_row_b ) {
                    tv_mult.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_mult.setClickable(false);
                }else {
                    tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_mult.setClickable(true);
                }
                //----------A*X=B----ac*cb=ab
                if (num_row_a  != num_row_b  ) {
                    tv_sovle.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovle.setClickable(false);
                }else {
                    tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovle.setClickable(true);
                }
                //----------X*A=B----ac*cb=ab
                if (num_column_a  != num_column_b  ) {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovleTran.setClickable(false);
                }else {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovleTran.setClickable(true);
                }
                //Toast.makeText(MainActivity.this, num_row_b+"", Toast.LENGTH_SHORT).show();



//-----------------------------------------------------------------------------


                //控件的出现与消失

                //int row = (int) Double.parseDouble(b0.getText().toString());
                // int column = (int) Double.parseDouble(b1.getText().toString());
                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                switch (num_column_b){
                    case 1:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.GONE);
                        linear_b3.setVisibility(View.GONE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 2:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.GONE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 3:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 4:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.VISIBLE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 5:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.VISIBLE);
                        linear_b5.setVisibility(View.VISIBLE);
                        break;


                }
                //让控件全部GONE---初始化
                for (int i = 0; i < id_b.length; i++) {
                    for (int j = 0; j < id_b[i].length; j++) {
                        test = (EditText) findViewById((int)id_b[i][j]);
                        test.setVisibility(View.GONE);
                    }
                }

                //让需要的控件VISIBLE
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        test = (EditText) findViewById(m[i][j]);
                        test.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_b = position+1;
                if (num_row_a != num_row_b || num_column_a != num_column_b) {
                    tv_add.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sub.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_add.setClickable(false);
                    tv_sub.setClickable(false);
                }else {
                    tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_add.setClickable(true);
                    tv_sub.setClickable(true);
                }
                if (num_row_a != num_column_b || num_column_a != num_row_b ) {
                    tv_mult.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_mult.setClickable(false);
                }else {
                    tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_mult.setClickable(true);
                }
                //----------A*X=B----ac*cb=ab
                if (num_row_a  != num_row_b  ) {
                    tv_sovle.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovle.setClickable(false);
                }else {
                    tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovle.setClickable(true);
                }
                //----------X*A=B----ac*cb=ab
                if (num_column_a  != num_column_b  ) {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#F3978F"));
                    tv_sovleTran.setClickable(false);
                }else {
                    tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                    tv_sovleTran.setClickable(true);
                }
                //  Toast.makeText(MainActivity.this,num_column_b+"", Toast.LENGTH_SHORT).show();


//-----------------------------------------------------------------------------


                //控件的出现与消失

                //int row = (int) Double.parseDouble(b0.getText().toString());
                // int column = (int) Double.parseDouble(b1.getText().toString());
                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                switch (num_column_b){
                    case 1:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.GONE);
                        linear_b3.setVisibility(View.GONE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 2:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.GONE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 3:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.GONE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 4:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.VISIBLE);
                        linear_b5.setVisibility(View.GONE);
                        break;
                    case 5:
                        linear_b1.setVisibility(View.VISIBLE);
                        linear_b2.setVisibility(View.VISIBLE);
                        linear_b3.setVisibility(View.VISIBLE);
                        linear_b4.setVisibility(View.VISIBLE);
                        linear_b5.setVisibility(View.VISIBLE);
                        break;


                }
                //让控件全部GONE---初始化
                for (int i = 0; i < id_b.length; i++) {
                    for (int j = 0; j < id_b[i].length; j++) {
                        test = (EditText) findViewById((int)id_b[i][j]);
                        test.setVisibility(View.GONE);
                    }
                }

                //让需要的控件VISIBLE
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        test = (EditText) findViewById(m[i][j]);
                        test.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//-------------------------------------------------------------------------


        //得出结果
        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    Toast.makeText(MainActivity.this,"还没有点运算符号呢！", Toast.LENGTH_SHORT).show();
                }else {
                    //input_a = et_a.getText().toString();
                    //input_b = et_b.getText().toString();



                    //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                    EditText et_a ,et_b;
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            et_a = (EditText) findViewById((int)id_a[i][j]);
                            et_b = (EditText) findViewById((int)id_b[i][j]);
                            data_a[i][j] = Double.parseDouble(et_a.getText().toString());
                            data_b[i][j] = Double.parseDouble(et_b.getText().toString());
                        }
                    }

                    try {
                       //先处理数据ProData，切割数据
                        //计算结果；
                        result = MyJama.getResult(MyJama.ProData(data_a,num_row_a,num_column_a), MyJama.ProData(data_b,num_row_b,num_column_b), flag);
                        tv_result.setText(MyJama.output(result,num).toString());


                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"输入错误！", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_tv_add:
                tv_add.setBackgroundColor(Color.parseColor("#86C0EE"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 1;break;
            case R.id.main_tv_sub:
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#86C0EE"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 2;break;
            case R.id.main_tv_mult:
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#86C0EE"));
                tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 3;break;
            case R.id.main_sovle:
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovle.setBackgroundColor(Color.parseColor("#86C0EE"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 4;break;
            case R.id.main_sovleTran:
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#86C0EE"));
                flag = 5;break;
            case R.id.main_clearAll:
               // Toast.makeText(MainActivity.this," 99999",Toast.LENGTH_SHORT).show();
                flag = 0;
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));

                spinner_column_a.setSelection(0);
                spinner_column_b.setSelection(0);
                spinner_row_a.setSelection(0);
                spinner_row_b.setSelection(0);

                EditText et_a ,et_b;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        et_a = (EditText) findViewById((int)id_a[i][j]);
                        et_b = (EditText) findViewById((int)id_b[i][j]);
                        et_a.setText("0");
                        et_b.setText("0");
                    }
                }
                tv_result.setText("0  0\n0  0");
        }
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                //Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.getContext(),Setting.class);
                startActivity(intent);break;
           /* case R.id.author:
                Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                *//*Intent intent1 = new Intent(MyApplication.getContext(),ThreeCompute.class);
                startActivity(intent1);finish();*//*break;
            case R.id.more:
                Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                *//*Intent intent2 = new Intent(MyApplication.getContext(),FourCompute.class);
                startActivity(intent2);finish();*//*break;*/
            case R.id.robo:
                Intent intent3 = new Intent(MyApplication.getContext(), ChatMainActivity.class);
                startActivity(intent3);break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);break;

        }
        return super.onOptionsItemSelected(item);
    }

}
