package com.luo.matrixcaculator;
import android.os.Bundle;

import Jama.Matrix;
public class JamaTest {

    public static void main(String[] args) {
        int row = 3,column = 2;
        int id_r[] = new int[row];
        int id_c[] = new int[column];

        for (int i = 0; i < row; i++) {
            id_r[i] = i;
        }
        for (int i = 0; i < column; i++) {
            id_c[i] = i;
        }

        double id[][] = new double[5][5];
        id[0][0] = R.id.a_00;
        id[0][1] = R.id.a_01;
        id[0][2] = R.id.a_02;
        id[0][3] = R.id.a_03;
        id[0][4] = R.id.a_04;
        id[1][0] = R.id.a_10;
        id[1][1] = R.id.a_11;
        id[1][2] = R.id.a_12;
        id[1][3] = R.id.a_13;
        id[1][4] = R.id.a_14;
        id[2][0] = R.id.a_20;
        id[2][1] = R.id.a_21;
        id[2][2] = R.id.a_22;
        id[2][3] = R.id.a_23;
        id[2][4] = R.id.a_24;
        id[3][0] = R.id.a_30;
        id[3][1] = R.id.a_31;
        id[3][2] = R.id.a_32;
        id[3][3] = R.id.a_33;
        id[3][4] = R.id.a_34;
        id[4][0] = R.id.a_40;
        id[4][1] = R.id.a_41;
        id[4][2] = R.id.a_42;
        id[4][3] = R.id.a_43;
        id[4][4] = R.id.a_44;

        MyJama.dispMatrix(MyJama.FindId(id,row,column));

/*
        Matrix m1 = new Matrix(id);
        m1.print(4,2);

        Matrix test = m1.getMatrix(id_r, id_c);

        test.print(4,2);

        double [][] m = test.getArray();
        int[][] n = new int[row][column];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                n[i][j] = (int) m[i][j];
            }

        }
        MyJama.dispMatrix(n);*/
        //int idInt[][] = (int[][])test.getArray();
        double id2[][] = new double[5][5];
        id2[0][0] = R.id.b_00;
        id2[0][1] = R.id.b_01;
        id2[0][2] = R.id.b_02;
        id2[0][3] = R.id.b_03;
        id2[0][4] = R.id.b_04;
        id2[1][0] = R.id.b_10;
        id2[1][1] = R.id.b_11;
        id2[1][2] = R.id.b_12;
        id2[1][3] = R.id.b_13;
        id2[1][4] = R.id.b_14;
        id2[2][0] = R.id.b_20;
        id2[2][1] = R.id.b_21;
        id2[2][2] = R.id.b_22;
        id2[2][3] = R.id.b_23;
        id2[2][4] = R.id.b_24;
        id2[3][0] = R.id.b_30;
        id2[3][1] = R.id.b_31;
        id2[3][2] = R.id.b_32;
        id2[3][3] = R.id.b_33;
        id2[3][4] = R.id.b_34;
        id2[4][0] = R.id.b_40;
        id2[4][1] = R.id.b_41;
        id2[4][2] = R.id.b_42;
        id2[4][3] = R.id.b_43;
        id2[4][4] = R.id.b_44;
        //Matrix m2 = new Matrix(id2);
       // m2.print(4,2);
/*

        double [][] array = {{10,9.6},{5.9,3.4},{5.9,3.4}};//32
        double[][] array2 = {{1,2,3,4,5}
                            , {4,5,6,7,8}
                            , {4,5,6,9,10}
                            , {4,5,6,11,12}
                            , {4,5,6,13,14}};//23
        double[][] array3;
        int[] aa = {0,1,2};
        int[] bb = {0,1,2};
        Matrix m1 = new Matrix(array);
        Matrix m2 = new Matrix(array2);
        Matrix m3 = m2.getMatrix(aa, bb);
        m3.print(4,2);
*/



       /* String a= "1,2,3,4\n5,6,7,8\n9,10,11,12";//34
        String b= "1,2,3\n5,6,7\n9,10,11\n9,10,13";
        double[] aa = MyJama.StrToNum(a);
        double[] bb = MyJama.StrToNum(b);
        double[][] m = MyJama.OneToTwo(aa, 3, 4);
        double[][] n = MyJama.OneToTwo(bb, 4, 3);
        double[][] result = MyJama.getResult(m, n, 3);

        Matrix m1 = new Matrix(result);
        m1.print(4,2);*/
       // tv_result.setText(MyJama.output(result,num).toString());

       /* String a= "1,2,3,4\n5,6,7,8\n9,10,11,12";
        String c=a.replaceAll("\n",",");
        String[] b = c.split(",");
        double [] num = new double[b.length];
        for (int i = 0; i < b.length; i++) {
            num[i] = Double.parseDouble(b[i]);
        }
        System.out.println("--------------"+num.length);
        for (int i = 0; i < num.length; i++) {
            System.out.println( num[i] );
        }


        //初始数据 row，column，num[]
        int row = 3,column = 4;

        double[][] m = new double[row][column];
            int index=0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <column; j++) {
                    m[i][j] = num[index];
                    index++;
            }
        }


        MatrixTest.dispMatrix(m);

        String array2 = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
        double[] num1 = MyJama.StrToNum(array2);
        MyJama.dispMatrix(MyJama.OneToTwo(num1,5,3));
       */ //MyJama.dispMatrix(MyJama.OneToTwo(array2,3,5));











        /*

     set 方法    set(int i, int j, double s)
     get 方法  get(int i, int j)  getArray()
     拷贝方法 copy()
     矩阵加法 plus(Matrix B)
     矩阵减法  minus(Matrix B)
     矩阵乘法 times(Matrix B)
          倒置   transpose()
     范式 norm()
          对称矩阵的特征值  eig()
     行列式 det()
     矩阵秩  rank()
     求逆 inverse()
            解方程
                solve(Matrix B)
                          Solve A*X = B
                solveTranspose(Matrix B)
                          Solve X*A = B, which is also A'*X' = B'
        * */
     /*double [][] array = {{10,9.6},{5.9,3.4},{5.9,3.4}};//32
        double[][] array2 = {{1,2,3},{4,5,6}};//23
        double[][] array3;
        Matrix m1 = new Matrix(array);
        Matrix m2 = new Matrix(array2);

        Matrix m3 ;
        m3 = m1.times(m2);
        m3.print(4,2);*/
       // array3 = m1.getArray();
        //MatrixTest.dispMatrix(array3);
        /*

       System.out.println(m1.det());
        System.out.println(m1.rank());

        System.out.println("--------------");
        System.out.println(m2.det());
        if(m2.det()==0){
            System.out.println("*********************");
        }
        System.out.println(m2.rank());
*/


/*

            double num[][]=new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        double[] aaa = MyJama.TwotoOne(num);
        StringBuilder stringBuilder = MyJama.output(aaa);
        System.out.println(stringBuilder.toString());
*/

          //  System.out.println(len);
           // one=new int[len];
          /*  int index=0;
            for(int i=0;i<num.length;i++){
                for(int j=0;j<num[i].length;j++){
                    stringBuilder.append(num[i][j]+"   ");
                }
                stringBuilder.append("\n");
            }*/
            //System.out.println(stringBuilder.toString());
//创建一个一维数组 0,1,2,3...,10
       /* double [] c= MyJama.TwotoOne(array2);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
        int row = (int) Math.sqrt(c.length);
        System.out.println(c.length);
        System.out.println(row);
        System.out.println("*********************"+0%3);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < c.length; i++){
           if((i+1) % row == 0){
               sb.append(c[i]+"\n");
           }else{
               sb.append(c[i]+", ");
           }
        }
        System.out.println(sb.toString());*/
        //MatrixTest.dispMatrix(MyJama.OneToTwo(c));

        //System.out.println(index);
    }

}













      /*
        Matrix x = m1.solve(m3);
        Matrix B =m1.transpose();
        B.print(4,2);
        double [][] array1 = B.getArray();
        MatrixTest.dispMatrix(array1);*/






        //由特征值组成的对角矩阵
      //  A.eig().getD()----返回由特征值组成的矩阵
      //  A.eig().getD().print(4,2);


        //每一列对应的是一个特征向量
      //  A.eig().getV()----返回特征向量组成的矩阵
      //  A.eig().getV().print(4,2);

