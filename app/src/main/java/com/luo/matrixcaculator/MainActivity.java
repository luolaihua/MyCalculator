package com.luo.matrixcaculator;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
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

import com.luo.matrixcaculator.robo.ChatMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //下拉栏
    private Spinner spinner_row_a, spinner_column_a,spinner_row_b, spinner_column_b;

    private TextView tv_result,tv_equal,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran,tv_clearAll,
            tv_A ,tv_B ,tv_analysisA,tv_analysisB;
    private EditText et_a,et_b,et_inputA,et_inputB;
    private String input_a,input_b;
    private List<String> row_a = new ArrayList<>();
    private List<String> column_a = new ArrayList<>();
    private List<String> row_b = new ArrayList<>();
    private List<String> column_b = new ArrayList<>();
    private static int num_row_a=1,num_row_b=1,num_column_a=1,num_column_b=1;//a b行列的数量
    private  int flag = 0;                                                  //加减乘除的flag
    private double[][] result,m,n,eigD,eigV,inverse,transpose;              // 结果
    private double rank,det;                                                //秩和行列式
    private static int num = 3;                                              //小数点保留位数
    private static boolean which_mode = false;                              //是否手动输入模式
    private static boolean isVibrate = false;                               //是否触摸反馈

    //输入框的id
    private LinearLayout linear_a1,linear_a2,linear_a3,linear_a4,linear_a5,
            linear_b1,linear_b2,linear_b3,linear_b4,linear_b5,linear_a,linear_b;
    static double id_a[][] = new double[5][5];
    static double id_b[][] = new double[5][5];
    static double data_a[][] = new double[5][5];
    static double data_b[][] = new double[5][5];
    Vibrator vibrator;


    private DrawerLayout drawerLayout;

//获取小数点保留位数，以及是否触摸反馈
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);
        which_mode = preferences.getBoolean("isChecked", false);
        isVibrate = preferences.getBoolean("isVibrate", false);


        //判断是哪种模式,并显示
        //which_mode == true ? View.GONE :View.VISIBLE
        linear_a.setVisibility(which_mode == true ? View.GONE : View.VISIBLE);
        linear_b.setVisibility(which_mode == true ? View.GONE : View.VISIBLE);
        et_inputA.setVisibility(which_mode == true ? View.VISIBLE : View.GONE);
        et_inputB.setVisibility(which_mode == true ? View.VISIBLE : View.GONE);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",2);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        //把输入框的地址全放入数组中
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


        linear_a = (LinearLayout) findViewById(R.id.linear_a);
        linear_b = (LinearLayout) findViewById(R.id.linear_b);

        et_inputA = (EditText) findViewById(R.id.input_a);
        et_inputB = (EditText) findViewById(R.id.input_b);





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
        tv_A = (TextView) findViewById(R.id.main_A);
        tv_B = (TextView) findViewById(R.id.main_B);
        tv_analysisA = (TextView) findViewById(R.id.main_analysisA);
        tv_analysisB = (TextView) findViewById(R.id.main_analysisB);

        et_b = (EditText) findViewById(R.id.et_b);
        et_a = (EditText) findViewById(R.id.et_a);


        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);
        tv_sovle.setOnClickListener(this);
        tv_sovleTran.setOnClickListener(this);
        tv_clearAll.setOnClickListener(this);
        tv_analysisA.setOnClickListener(this);
        tv_analysisB.setOnClickListener(this);
        tv_B.setOnClickListener(this);
        tv_A.setOnClickListener(this);



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
                Check_ChangeColor();

//-----------------------------------------------------------------------------


                //控件的出现与消失
                //裁剪矩阵
                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                InputShow_linearlayout(num_column_a,linear_a1,linear_a2,linear_a3,linear_a4,linear_a5);

                //让控件全部GONE---初始化
                     Input_gone(id_a);
                //让需要的控件VISIBLE
                     Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_a = position + 1;
                Check_ChangeColor();

//-----------------------------------------------------------------------------

                //控件的出现与消失

                int[][] m = MyJama.FindId(id_a,num_row_a,num_column_a);
                EditText test;
                InputShow_linearlayout(num_column_a,linear_a1,linear_a2,linear_a3,linear_a4,linear_a5);

                //让控件全部GONE---初始化
                Input_gone(id_a);
                //让需要的控件VISIBLE
                Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_row_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_row_b = position+1;

//-----------------------------------------------------------------------------

                //控件的出现与消失

                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                InputShow_linearlayout(num_column_b,linear_b1,linear_b2,linear_b3,linear_b4,linear_b5);

                //让控件全部GONE---初始化
                Input_gone(id_b);
                //让需要的控件VISIBLE
                Input_visible(m);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_column_b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num_column_b = position+1;
               Check_ChangeColor();

//-----------------------------------------------------------------------------

                //控件的出现与消失
                int[][] m = MyJama.FindId(id_b,num_row_b,num_column_b);
                EditText test;
                InputShow_linearlayout(num_column_b,linear_b1,linear_b2,linear_b3,linear_b4,linear_b5);

                //让控件全部GONE---初始化
               Input_gone(id_b);
                //让需要的控件VISIBLE
                Input_visible(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//-------------------------------------------------------------------------


/**
 *  //得出结果
 */
        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVibrate){
                    vibrator.vibrate(10);
                }

                if (flag == 0) {
                    Toast.makeText(MainActivity.this,"还没有点运算符号呢！", Toast.LENGTH_SHORT).show();
                }else {

                    if(which_mode){
                        input_a = et_inputA.getText().toString();
                        input_b = et_inputB.getText().toString();

                        try {
                            double[] a = MyJama.StrToNum(input_a);
                            double[] b = MyJama.StrToNum(input_b);
                            double[][] aa = MyJama.OneToTwo(a, num_row_a, num_column_a);
                            double[][] bb = MyJama.OneToTwo(b, num_row_b, num_column_b);
                            result = MyJama.getResult(aa, bb, flag);
                            tv_result.setText(MyJama.output(result,num).toString());
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this,"输入错误！", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                        // EditText et_a ,et_b;
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


            }
        });





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_A:
               Vibrate();
                if(which_mode){
                    et_inputA.setText(MyJama.UnitMatrix(num_row_a,num_column_a));
                }else {
                    Unit(et_a,id_a);
                }
                break;
            case R.id.main_B:
                Vibrate();
                if(which_mode){
                    et_inputB.setText(MyJama.UnitMatrix(num_row_b,num_column_b));
                }else {
                    Unit(et_b,id_b);
                }
               break;

            case R.id.main_tv_add:
                Vibrate();
                flag = 1;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);break;
            case R.id.main_tv_sub:
                Vibrate();
                flag = 2;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);break;
            case R.id.main_tv_mult:
                Vibrate();
                flag = 3;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);break;
            case R.id.main_sovle:
                Vibrate();
                flag = 4;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);break;
            case R.id.main_sovleTran:
                Vibrate();
                flag = 5;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);break;
            case R.id.main_analysisA:
                Vibrate();
                if(which_mode){
                    try{
                            input_a = et_inputA.getText().toString();
                            double[] a = MyJama.StrToNum(input_a);
                            double[][] m = MyJama.OneToTwo(a, num_row_a, num_column_a);
                            Analysis(m);
/*
                            Intent intent = new Intent(MainActivity.this, Analysis.class);

                            rank = MyJama.matrixRank(m);
                            det = MyJama.matrixDet(m);
                            transpose = MyJama.matrixTranspose(m);
                            eigD = MyJama.matrixEigD(m);
                            eigV = MyJama.matrixEigV(m);

                            //二维变一维
                            double[] tranOne = MyJama.TwotoOne(transpose);
                            double[] eigDOne = MyJama.TwotoOne(eigD);
                            double[] eigVOne = MyJama.TwotoOne(eigV);


                            intent.putExtra("rank",rank);
                            intent.putExtra("det", det);
                            intent.putExtra("transpose", tranOne);
                            intent.putExtra("eigD", eigDOne);
                            intent.putExtra("eigV", eigVOne);

                            if(det != 0){
                                inverse = MyJama.matrixInverse(m);
                                double[] inverseOne = MyJama.TwotoOne(inverse);
                                intent.putExtra("inverse", inverseOne);
                            }

                            startActivity(intent);*/
                            break;
                        }catch (Exception e) {
                        Toast.makeText(MainActivity.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }

                }else {
                    //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            et_a = (EditText) findViewById((int)id_a[i][j]);
                            data_a[i][j] = Double.parseDouble(et_a.getText().toString());
                        }
                    }
                    try {
                        //先处理数据ProData，切割数据
                        m = MyJama.ProData(data_a,num_row_a,num_column_a);

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"输入错误！1", Toast.LENGTH_SHORT).show();
                    }

                    try{

                       /* Intent intent = new Intent(MainActivity.this, Analysis.class);

                        rank = MyJama.matrixRank(m);
                        det = MyJama.matrixDet(m);
                        transpose = MyJama.matrixTranspose(m);
                        eigD = MyJama.matrixEigD(m);
                        eigV = MyJama.matrixEigV(m);

                        //二维变一维
                        double[] tranOne = MyJama.TwotoOne(transpose);
                        double[] eigDOne = MyJama.TwotoOne(eigD);
                        double[] eigVOne = MyJama.TwotoOne(eigV);


                        intent.putExtra("rank",rank);
                        intent.putExtra("det", det);
                        intent.putExtra("transpose", tranOne);
                        intent.putExtra("eigD", eigDOne);
                        intent.putExtra("eigV", eigVOne);

                        if(det != 0){
                            inverse = MyJama.matrixInverse(m);
                            double[] inverseOne = MyJama.TwotoOne(inverse);
                            intent.putExtra("inverse", inverseOne);
                        }

                        startActivity(intent);*/
                        Analysis(m);
                        break;
                    }catch (Exception e) {
                        Toast.makeText(MainActivity.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }


            case R.id.main_analysisB:
                Vibrate();
                if(which_mode){
                    try{
                        input_b = et_inputB.getText().toString();
                        double[] b = MyJama.StrToNum(input_b);
                        double[][] n = MyJama.OneToTwo(b, num_row_b, num_column_b);

                       /* Intent intent = new Intent(MainActivity.this, Analysis.class);

                        rank = MyJama.matrixRank(n);
                        det = MyJama.matrixDet(n);
                        transpose = MyJama.matrixTranspose(n);
                        eigD = MyJama.matrixEigD(n);
                        eigV = MyJama.matrixEigV(n);

                        //二维变一维
                        double[] tranOne = MyJama.TwotoOne(transpose);
                        double[] eigDOne = MyJama.TwotoOne(eigD);
                        double[] eigVOne = MyJama.TwotoOne(eigV);


                        intent.putExtra("rank",rank);
                        intent.putExtra("det", det);
                        intent.putExtra("transpose", tranOne);
                        intent.putExtra("eigD", eigDOne);
                        intent.putExtra("eigV", eigVOne);

                        if(det != 0){
                            inverse = MyJama.matrixInverse(n);
                            double[] inverseOne = MyJama.TwotoOne(inverse);
                            intent.putExtra("inverse", inverseOne);
                        }

                        startActivity(intent);*/
                        Analysis(n);
                        break;
                    }catch (Exception e) {
                        Toast.makeText(MainActivity.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }

                }else {
                    //把数据都放入data_a，b中,五行五列,此时还有好多0呢！！！需要处理！
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            et_b = (EditText) findViewById((int)id_b[i][j]);
                            data_b[i][j] = Double.parseDouble(et_b.getText().toString());
                        }
                    }
                    try {
                        //先处理数据ProData，切割数据
                        n = MyJama.ProData(data_b,num_row_b,num_column_b);

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"输入错误！1", Toast.LENGTH_SHORT).show();
                    }

                    try{
                        Analysis(n);
/*
                        Intent intent = new Intent(MainActivity.this, Analysis.class);

                        rank = MyJama.matrixRank(n);
                        det = MyJama.matrixDet(n);
                        transpose = MyJama.matrixTranspose(n);
                        eigD = MyJama.matrixEigD(n);
                        eigV = MyJama.matrixEigV(n);

                        //二维变一维
                        double[] tranOne = MyJama.TwotoOne(transpose);
                        double[] eigDOne = MyJama.TwotoOne(eigD);
                        double[] eigVOne = MyJama.TwotoOne(eigV);


                        intent.putExtra("rank",rank);
                        intent.putExtra("det", det);
                        intent.putExtra("transpose", tranOne);
                        intent.putExtra("eigD", eigDOne);
                        intent.putExtra("eigV", eigVOne);

                        if(det != 0){
                            inverse = MyJama.matrixInverse(n);
                            double[] inverseOne = MyJama.TwotoOne(inverse);
                            intent.putExtra("inverse", inverseOne);
                        }

                        startActivity(intent);*/


                        break;
                    }catch (Exception e) {
                        Toast.makeText(MainActivity.this,"输入有误...2",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            case R.id.main_clearAll:
                Vibrate();
                flag = 0;
                ChangeOperatorColor(flag,tv_add,tv_sub,tv_mult,tv_sovle,tv_sovleTran);

                spinner_column_a.setSelection(0);
                spinner_column_b.setSelection(0);
                spinner_row_a.setSelection(0);
                spinner_row_b.setSelection(0);

                EditText et_b;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        et_a = (EditText) findViewById((int)id_a[i][j]);
                        et_b = (EditText) findViewById((int)id_b[i][j]);
                        et_a.setText("0");
                        et_b.setText("0");
                    }
                }


                et_inputA.setText("");
                et_inputB.setText("");
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

   //生成单位矩阵
    public void Unit(EditText et,double[][] id){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    et = (EditText) findViewById((int)id[i][j]);
                    et.setText("1");
                }
            }
        }
    }
    //触摸反馈
    public void Vibrate(){
        if(isVibrate){
            vibrator.vibrate(10);
        }
    }
    //改变操作符颜色
    public void ChangeOperatorColor(int f, TextView add, TextView sub, TextView mult, TextView sovle, TextView sovleTran){

        switch (f){
            case 0:
                 add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                 sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                 mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                 sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                 sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 1:
                add.setBackgroundColor(Color.parseColor("#86C0EE"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 2:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#86C0EE"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 3:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#86C0EE"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 4:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#86C0EE"));
                sovleTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                break;
            case 5:
                add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovle.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                sovleTran.setBackgroundColor(Color.parseColor("#86C0EE"));
                break;
        }

    }
    //屏蔽不可进行的操作
    public void Check_ChangeColor(){
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
    }
    //输入控件的出现与消失
    public void InputShow_linearlayout(int num, LinearLayout l1, LinearLayout l2, LinearLayout l3, LinearLayout l4, LinearLayout l5){
        switch (num){
            case 1:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 2:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 3:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                break;
            case 4:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.GONE);
                break;
            case 5:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.VISIBLE);
                break;


        }
    }
    //初始化控件，使之全部消失
    public void Input_gone(double[][] id){
        EditText test;
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < id[i].length; j++) {
                test = (EditText) findViewById((int)id[i][j]);
                test.setVisibility(View.GONE);
            }
        }
    }
    //显示需要的空间
    public void Input_visible(int [][] id){
        EditText test;
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < id[i].length; j++) {
                test = (EditText) findViewById(id[i][j]);
                test.setVisibility(View.VISIBLE);
            }
        }

    }
    //矩阵分析
    public void Analysis(double [][] m){

        Intent intent = new Intent(MainActivity.this, Analysis.class);

        rank = MyJama.matrixRank(m);
        det = MyJama.matrixDet(m);
        transpose = MyJama.matrixTranspose(m);
        eigD = MyJama.matrixEigD(m);
        eigV = MyJama.matrixEigV(m);

        //二维变一维
        double[] tranOne = MyJama.TwotoOne(transpose);
        double[] eigDOne = MyJama.TwotoOne(eigD);
        double[] eigVOne = MyJama.TwotoOne(eigV);


        intent.putExtra("rank",rank);
        intent.putExtra("det", det);
        intent.putExtra("transpose", tranOne);
        intent.putExtra("eigD", eigDOne);
        intent.putExtra("eigV", eigVOne);

        if(det != 0){
            inverse = MyJama.matrixInverse(m);
            double[] inverseOne = MyJama.TwotoOne(inverse);
            intent.putExtra("inverse", inverseOne);
        }

        startActivity(intent);
    }
}
