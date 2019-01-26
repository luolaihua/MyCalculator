package com.luo.matrixcaculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TwoCompute extends BaseActivity implements View.OnClickListener{
    private TextView tv_result,tv_equal,tv_add,tv_sub,tv_mult ;
    private EditText edt_0,edt_1,edt_2,edt_3,
            edt2_0,edt2_1,edt2_2,edt2_3;

    private  int flag = 1;

    private double[][] m = new double[2][2];
    private double[][] n = new double[2][2];
    private double[][] result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_compute);

        tv_result = (TextView) findViewById(R.id.two_tv_result);
        tv_add =(TextView) findViewById(R.id.two_tv_add);
        tv_sub = (TextView) findViewById(R.id.two_tv_sub);
        tv_mult = (TextView) findViewById(R.id.two_tv_mult);
        tv_equal = (TextView) findViewById(R.id.two_tv_equal);

        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
        tv_mult.setOnClickListener(this);

        edt2_0 = (EditText) findViewById(R.id.two_edtInput2_0);
        edt2_1 = (EditText) findViewById(R.id.two_edtInput2_1);
        edt2_2 = (EditText) findViewById(R.id.two_edtInput2_2);
        edt2_3 = (EditText) findViewById(R.id.two_edtInput2_3);


        edt_0 = (EditText) findViewById(R.id.two_edtInput_0);
        edt_1 = (EditText) findViewById(R.id.two_edtInput_1);
        edt_2 = (EditText) findViewById(R.id.two_edtInput_2);
        edt_3 = (EditText) findViewById(R.id.two_edtInput_3);




        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n[0][0] = Double.parseDouble(edt2_0.getText().toString());
                n[0][1] = Double.parseDouble(edt2_1.getText().toString());
                n[1][0] = Double.parseDouble(edt2_2.getText().toString());
                n[1][1] = Double.parseDouble(edt2_3.getText().toString());


                m[0][0] = Double.parseDouble(edt_0.getText().toString());
                m[0][1] = Double.parseDouble(edt_1.getText().toString());
                m[1][0] = Double.parseDouble(edt_2.getText().toString());
                m[1][1] = Double.parseDouble(edt_3.getText().toString());

                switch (flag){
                    case 1:
                        result = MyJama.matrixAdd(m,n);break;
                    case 2:
                        result = MyJama.matrixSub(m,n);break;
                    case 3:
                        result = MyJama.matrixMult(m,n);break;
                }



                tv_result.setText(

                    String.format("%.2f", result[0][0])+"    "+
                    String.format("%.2f", result[0][1])+"    \n"+
                    String.format("%.2f", result[1][0])+"    "+
                    String.format("%.2f", result[1][1])+"    "
                );



            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.two_tv_add:
                tv_add.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 1;break;
            case R.id.two_tv_sub:
                tv_sub.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_mult.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 2;break;
            case R.id.two_tv_mult:
                tv_mult.setBackgroundColor(Color.parseColor("#B0D6F5"));
                tv_sub.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                tv_add.setBackgroundColor(Color.parseColor("#6FE2EDF5"));
                flag = 3;break;

        }
    }
}
