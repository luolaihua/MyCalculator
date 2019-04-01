package com.luo.matrixcaculator;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.matrixcaculator.robo.ChatMainActivity;

import java.util.ArrayList;
import java.util.List;

public class Main2 extends AppCompatActivity implements View.OnClickListener{
    private Spinner spinner_row_a, spinner_column_a,spinner_row_b, spinner_column_b;
    private TextView tv_result,tv_equal,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran,tv_clearAll,testA ;
    private EditText et_a,et_b,b0,b1;
    private String input_a,input_b;
    private List<String> row_a = new ArrayList<>();
    private List<String> column_a = new ArrayList<>();
    private List<String> row_b = new ArrayList<>();
    private List<String> column_b = new ArrayList<>();
    private int num_row_a=1,num_row_b=1,num_column_a=1,num_column_b=1;
    private  int flag = 0;
    private double[][] result;
    private static int num = 3;




    private DrawerLayout drawerLayout;

    //获取小数点保留位数
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);




/*        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu33);
        }*/


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
                 Toast.makeText(Main2.this,"当前小数点保留 "+num+" 位",Toast.LENGTH_SHORT).show();

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
        row_a.add("6行");
        row_a.add("7行");
        row_a.add("8行");
        row_a.add("9行");
        row_a.add("10行");
        column_a.add("1列");
        column_a.add("2列");
        column_a.add("3列");
        column_a.add("4列");
        column_a.add("5列");
        column_a.add("6列");
        column_a.add("7列");
        column_a.add("8列");
        column_a.add("9列");
        column_a.add("10列");
        row_b.add("1行");
        row_b.add("2行");
        row_b.add("3行");
        row_b.add("4行");
        row_b.add("5行");
        row_b.add("6行");
        row_b.add("7行");
        row_b.add("8行");
        row_b.add("9行");
        row_b.add("10行");
        column_b.add("1列");
        column_b.add("2列");
        column_b.add("3列");
        column_b.add("4列");
        column_b.add("5列");
        column_b.add("6列");
        column_b.add("7列");
        column_b.add("8列");
        column_b.add("9列");
        column_b.add("10列");

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


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //得出结果
        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    Toast.makeText(Main2.this,"还没有点运算符号呢！", Toast.LENGTH_SHORT).show();
                }else {
                    input_a = et_a.getText().toString();
                    input_b = et_b.getText().toString();
                    try {
                        double[] a = MyJama.StrToNum(input_a);
                        double[] b = MyJama.StrToNum(input_b);
                        double[][] m = MyJama.OneToTwo(a, num_row_a, num_column_a);
                        double[][] n = MyJama.OneToTwo(b, num_row_b, num_column_b);
                        result = MyJama.getResult(m, n, flag);
                        tv_result.setText(MyJama.output(result,num).toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Main2.this,"输入错误！", Toast.LENGTH_SHORT).show();
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

                et_a.setText("");
                et_b.setText("");
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
           /* case R.id.robo:
                Intent intent3 = new Intent(MyApplication.getContext(), ChatMainActivity.class);
                startActivity(intent3);break;*/
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);break;

        }
        return super.onOptionsItemSelected(item);
    }

}
