package com.luo.matrixcaculator;
import android.os.Bundle;

import Jama.Matrix;
public class JamaTest {
    public static final String ARRAYS_COUNT = "com.yourname.ARRAYS_COUNT";

    public static final String ARRAY_INDEX = "com.yourname.ARRAY_INDEX";
    public static void main(String[] args) {
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
        double [][] array = {{10,9.6},{5.9,3.4}};
        double[][] array2 = {{1,2,3},{4,5,6},{7,8,9}};
        double[][] array3;
        Matrix m1 = new Matrix(array);
        Matrix m2 = new Matrix(array2);
        Matrix m3 = Matrix.random(3,1);
        /*m1 = m1.times(m2);
        array3 = m1.getArray();
        MatrixTest.dispMatrix(array3);*/

        System.out.println(m1.det());
        System.out.println(m1.rank());

        System.out.println("--------------");
        System.out.println(m2.det());
        if(m2.det()==0){
            System.out.println("*********************");
        }
        System.out.println(m2.rank());




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

