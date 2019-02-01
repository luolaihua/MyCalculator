package com.luo.matrixcaculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.matrixcaculator.robo.ChatMainActivity;

public class FourCompute extends BaseActivity implements View.OnClickListener{
    private TextView tv_result,tv_equal,tv_add,tv_sub,tv_mult,tv_solve,tv_solveTran,
            tv_analysisA,tv_analysisB,tv_clearAll;
    private EditText edt_0,edt_1,edt_2,edt_3,edt_4,edt_5,edt_6,edt_7,edt_8,
            edt_9,edt_10,edt_11,edt_12,edt_13,edt_14,edt_15,
            edt2_0,edt2_1,edt2_2,edt2_3,edt2_4,edt2_5,edt2_6,edt2_7,edt2_8,
            edt2_9,edt2_10,edt2_11,edt2_12,edt2_13,edt2_14,edt2_15;

    private int flag = 1;

    private double[][] m = new double[4][4];
    private double[][] n = new double[4][4];
    private double[][] result,eigD,eigV,inverse,transpose;
    private double rank,det;
    private static int num = 3;
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("data_num", MODE_PRIVATE);
        num = preferences.getInt("num",0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_compute);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_two:
                        Intent intent = new Intent(MyApplication.getContext(),TwoCompute.class);
                        startActivity(intent);finish();break;
                    case R.id.nav_three:
                        Intent intent1 = new Intent(MyApplication.getContext(),ThreeCompute.class);
                        startActivity(intent1);finish();break;
                    case R.id.nav_four:
                        Intent intent2 = new Intent(MyApplication.getContext(),FourCompute.class);
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



        tv_result = (TextView) findViewById(R.id.four_tv_result);
        tv_add =(TextView) findViewById(R.id.four_tv_add);
        tv_sub = (TextView) findViewById(R.id.four_tv_sub);
        tv_mult = (TextView) findViewById(R.id.four_tv_mult);
        tv_equal = (TextView) findViewById(R.id.four_tv_equal);
        tv_solve = (TextView) findViewById(R.id.four_sovle);
        tv_solveTran = (TextView) findViewById(R.id.four_sovleTran);
        tv_analysisA = (TextView) findViewById(R.id.four_analysisA);
        tv_analysisB = (TextView) findViewById(R.id.four_analysisB);
        tv_clearAll = (TextView) findViewById(R.id.four_clearAll);


        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);
        tv_solve.setOnClickListener(this);
        tv_solveTran.setOnClickListener(this);
        tv_analysisA.setOnClickListener(this);
        tv_analysisB.setOnClickListener(this);
        tv_clearAll.setOnClickListener(this);

        edt2_0 = (EditText) findViewById(R.id.four_edtInput2_0);
        edt2_1 = (EditText) findViewById(R.id.four_edtInput2_1);
        edt2_2 = (EditText) findViewById(R.id.four_edtInput2_2);
        edt2_3 = (EditText) findViewById(R.id.four_edtInput2_3);
        edt2_4 = (EditText) findViewById(R.id.four_edtInput2_4);
        edt2_5 = (EditText) findViewById(R.id.four_edtInput2_5);
        edt2_6 = (EditText) findViewById(R.id.four_edtInput2_6);
        edt2_7 = (EditText) findViewById(R.id.four_edtInput2_7);
        edt2_8 = (EditText) findViewById(R.id.four_edtInput2_8);
        edt2_9 = (EditText) findViewById(R.id.four_edtInput2_9);
        edt2_10 = (EditText) findViewById(R.id.four_edtInput2_10);
        edt2_11 = (EditText) findViewById(R.id.four_edtInput2_11);
        edt2_12 = (EditText) findViewById(R.id.four_edtInput2_12);
        edt2_13 = (EditText) findViewById(R.id.four_edtInput2_13);
        edt2_14 = (EditText) findViewById(R.id.four_edtInput2_14);
        edt2_15 = (EditText) findViewById(R.id.four_edtInput2_15);



        edt_0 = (EditText) findViewById(R.id.four_edtInput_0);
        edt_1 = (EditText) findViewById(R.id.four_edtInput_1);
        edt_2 = (EditText) findViewById(R.id.four_edtInput_2);
        edt_3 = (EditText) findViewById(R.id.four_edtInput_3);
        edt_4 = (EditText) findViewById(R.id.four_edtInput_4);
        edt_5 = (EditText) findViewById(R.id.four_edtInput_5);
        edt_6 = (EditText) findViewById(R.id.four_edtInput_6);
        edt_7 = (EditText) findViewById(R.id.four_edtInput_7);
        edt_8 = (EditText) findViewById(R.id.four_edtInput_8);
        edt_9 = (EditText) findViewById(R.id.four_edtInput_9);
        edt_10 = (EditText) findViewById(R.id.four_edtInput_10);
        edt_11 = (EditText) findViewById(R.id.four_edtInput_11);
        edt_12 = (EditText) findViewById(R.id.four_edtInput_12);
        edt_13 = (EditText) findViewById(R.id.four_edtInput_13);
        edt_14 = (EditText) findViewById(R.id.four_edtInput_14);
        edt_15 = (EditText) findViewById(R.id.four_edtInput_15);




        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    n[0][0] = Double.parseDouble(edt2_0.getText().toString());
                    n[0][1] = Double.parseDouble(edt2_1.getText().toString());
                    n[0][2] = Double.parseDouble(edt2_2.getText().toString());
                    n[0][3] = Double.parseDouble(edt2_3.getText().toString());
                    n[1][0] = Double.parseDouble(edt2_4.getText().toString());
                    n[1][1] = Double.parseDouble(edt2_5.getText().toString());
                    n[1][2] = Double.parseDouble(edt2_6.getText().toString());
                    n[1][3] = Double.parseDouble(edt2_7.getText().toString());
                    n[2][0] = Double.parseDouble(edt2_8.getText().toString());
                    n[2][1] = Double.parseDouble(edt2_9.getText().toString());
                    n[2][2] = Double.parseDouble(edt2_10.getText().toString());
                    n[2][3] = Double.parseDouble(edt2_11.getText().toString());
                    n[3][0] = Double.parseDouble(edt2_12.getText().toString());
                    n[3][1] = Double.parseDouble(edt2_13.getText().toString());
                    n[3][2] = Double.parseDouble(edt2_14.getText().toString());
                    n[3][3] = Double.parseDouble(edt2_15.getText().toString());



                    m[0][0] = Double.parseDouble(edt_0.getText().toString());
                    m[0][1] = Double.parseDouble(edt_1.getText().toString());
                    m[0][2] = Double.parseDouble(edt_2.getText().toString());
                    m[0][3] = Double.parseDouble(edt_3.getText().toString());
                    m[1][0] = Double.parseDouble(edt_4.getText().toString());
                    m[1][1] = Double.parseDouble(edt_5.getText().toString());
                    m[1][2] = Double.parseDouble(edt_6.getText().toString());
                    m[1][3] = Double.parseDouble(edt_7.getText().toString());
                    m[2][0] = Double.parseDouble(edt_8.getText().toString());
                    m[2][1] = Double.parseDouble(edt_9.getText().toString());
                    m[2][2] = Double.parseDouble(edt_10.getText().toString());
                    m[2][3] = Double.parseDouble(edt_11.getText().toString());
                    m[3][0] = Double.parseDouble(edt_12.getText().toString());
                    m[3][1] = Double.parseDouble(edt_13.getText().toString());
                    m[3][2] = Double.parseDouble(edt_14.getText().toString());
                    m[3][3] = Double.parseDouble(edt_15.getText().toString());


                    //计算结果；
                    result = MyJama.getResult(m, n, flag);
                    tv_result.setText(MyJama.output(result,num).toString());

                }catch (Exception e) {
                    Toast.makeText(FourCompute.this,"输入有误...",Toast.LENGTH_SHORT).show();
                }





                    //计算结果；
                /*    result = MyJama.getResult(m, n, flag);
                tv_result.setText(MyJama.output(result).toString());*/

               /* switch (flag){
                    case 1:
                        result = MyJama.matrixAdd(m,n);break;
                    case 2:
                        result = MyJama.matrixSub(m,n);break;
                    case 3:
                        result = MyJama.matrixMult(m,n);break;
                    case 4:
                        result = MyJama.matrixSolve(m,n);break;
                    case 5:
                        result = MyJama.matrixSolveTran(m,n);break;
                }*/




                /*tv_result.setText(

                                String.format("%.2f", result[0][0])+"    "+
                                String.format("%.2f", result[0][1])+"    "+
                                String.format("%.2f", result[0][2])+"    "+
                                String.format("%.2f", result[0][3])+"    \n"+
                                String.format("%.2f", result[1][0])+"    "+
                                String.format("%.2f", result[1][1])+"    "+
                                String.format("%.2f", result[1][2])+"    "+
                                String.format("%.2f", result[1][3])+"    \n"+
                                String.format("%.2f", result[2][0])+"    "+
                                String.format("%.2f", result[2][1])+"    "+
                                String.format("%.2f", result[2][2])+"    "+
                                String.format("%.2f", result[2][3])+"    \n"+
                                String.format("%.2f", result[3][0])+"    "+
                                String.format("%.2f", result[3][1])+"    "+
                                String.format("%.2f", result[3][2])+"    "+
                                String.format("%.2f", result[3][3])+"    ");*/





            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.four_tv_add:
                tv_add.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solve.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solveTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 1;break;
            case R.id.four_tv_sub:
                tv_sub.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solve.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solveTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 2;break;
            case R.id.four_tv_mult:
                tv_mult.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solve.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solveTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 3;break;
            case R.id.four_sovle:
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solve.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_solveTran.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 4;break;
            case R.id.four_sovleTran:
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solve.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_solveTran.setBackgroundColor(Color.parseColor("#B0D6F5"));
                flag = 5;break;
            case R.id.four_analysisA:


                try{
                    m[0][0] = Double.parseDouble(edt_0.getText().toString());
                    m[0][1] = Double.parseDouble(edt_1.getText().toString());
                    m[0][2] = Double.parseDouble(edt_2.getText().toString());
                    m[0][3] = Double.parseDouble(edt_3.getText().toString());
                    m[1][0] = Double.parseDouble(edt_4.getText().toString());
                    m[1][1] = Double.parseDouble(edt_5.getText().toString());
                    m[1][2] = Double.parseDouble(edt_6.getText().toString());
                    m[1][3] = Double.parseDouble(edt_7.getText().toString());
                    m[2][0] = Double.parseDouble(edt_8.getText().toString());
                    m[2][1] = Double.parseDouble(edt_9.getText().toString());
                    m[2][2] = Double.parseDouble(edt_10.getText().toString());
                    m[2][3] = Double.parseDouble(edt_11.getText().toString());
                    m[3][0] = Double.parseDouble(edt_12.getText().toString());
                    m[3][1] = Double.parseDouble(edt_13.getText().toString());
                    m[3][2] = Double.parseDouble(edt_14.getText().toString());
                    m[3][3] = Double.parseDouble(edt_15.getText().toString());

                    Intent intent = new Intent(FourCompute.this, Analysis.class);

                    rank = MyJama.matrixRank(m);
                    det = MyJama.matrixDet(m);
                    transpose = MyJama.matrixTranspose(m);
                    eigD = MyJama.matrixEigD(m);
                    eigV = MyJama.matrixEigV(m);

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
                    break;
                }catch (Exception e) {
                    Toast.makeText(FourCompute.this,"输入有误...",Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.four_analysisB:

                try{
                    n[0][0] = Double.parseDouble(edt2_0.getText().toString());
                    n[0][1] = Double.parseDouble(edt2_1.getText().toString());
                    n[0][2] = Double.parseDouble(edt2_2.getText().toString());
                    n[0][3] = Double.parseDouble(edt2_3.getText().toString());
                    n[1][0] = Double.parseDouble(edt2_4.getText().toString());
                    n[1][1] = Double.parseDouble(edt2_5.getText().toString());
                    n[1][2] = Double.parseDouble(edt2_6.getText().toString());
                    n[1][3] = Double.parseDouble(edt2_7.getText().toString());
                    n[2][0] = Double.parseDouble(edt2_8.getText().toString());
                    n[2][1] = Double.parseDouble(edt2_9.getText().toString());
                    n[2][2] = Double.parseDouble(edt2_10.getText().toString());
                    n[2][3] = Double.parseDouble(edt2_11.getText().toString());
                    n[3][0] = Double.parseDouble(edt2_12.getText().toString());
                    n[3][1] = Double.parseDouble(edt2_13.getText().toString());
                    n[3][2] = Double.parseDouble(edt2_14.getText().toString());
                    n[3][3] = Double.parseDouble(edt2_15.getText().toString());

                    Intent intent1 = new Intent(FourCompute.this, Analysis.class);

                    rank = MyJama.matrixRank(n);
                    det = MyJama.matrixDet(n);
                    transpose = MyJama.matrixTranspose(n);
                    eigD = MyJama.matrixEigD(n);
                    eigV = MyJama.matrixEigV(n);

                    double[] tranOne1 = MyJama.TwotoOne(transpose);
                    double[] eigDOne1 = MyJama.TwotoOne(eigD);
                    double[] eigVOne1 = MyJama.TwotoOne(eigV);


                    intent1.putExtra("rank",rank);
                    intent1.putExtra("det", det);
                    intent1.putExtra("transpose", tranOne1);
                    intent1.putExtra("eigD", eigDOne1);
                    intent1.putExtra("eigV", eigVOne1);

                    if(det != 0){
                        inverse = MyJama.matrixInverse(n);
                        double[] inverseOne = MyJama.TwotoOne(inverse);
                        intent1.putExtra("inverse", inverseOne);
                    }

                    startActivity(intent1);
                    break;
                }catch (Exception e) {
                    Toast.makeText(FourCompute.this,"输入有误...",Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.four_clearAll:
                edt_0.setText("0");
                edt_1.setText("0");
                edt_2.setText("0");
                edt_3.setText("0");
                edt_4.setText("0");
                edt_5.setText("0");
                edt_6.setText("0");
                edt_7.setText("0");
                edt_8.setText("0");
                edt_9.setText("0");
                edt_10.setText("0");
                edt_11.setText("0");
                edt_12.setText("0");
                edt_13.setText("0");
                edt_14.setText("0");
                edt_15.setText("0");

                edt2_0.setText("0");
                edt2_1.setText("0");
                edt2_2.setText("0");
                edt2_3.setText("0");
                edt2_4.setText("0");
                edt2_5.setText("0");
                edt2_6.setText("0");
                edt2_7.setText("0");
                edt2_8.setText("0");
                edt2_9.setText("0");
                edt2_10.setText("0");
                edt2_11.setText("0");
                edt2_12.setText("0");
                edt2_13.setText("0");
                edt2_14.setText("0");
                edt2_15.setText("0");
                tv_result.setText("0  0  0  0\n0  0  0  0\n0  0  0  0\n0  0  0  0\n");


        }
    }
}
