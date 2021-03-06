package com.luo.matrixcaculator;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import Jama.Matrix;

public class MyJama {



    public static void dispMatrix(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%-15.2f",m[i][j]);
            }
            System.out.println();
        }
    }
    public static void dispMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%-15d",m[i][j]);
            }
            System.out.println();
        }
    }
    public static double[][] matrixAdd(double[][] m,double[][] n) {

        Matrix A = new Matrix(m);
        Matrix B = new Matrix(n);
        A.plusEquals(B);
        return A.getArray();

    }
    public static double[][] matrixSub(double[][] m,double[][] n) {
        Matrix A = new Matrix(m);
        Matrix B = new Matrix(n);
        A.minusEquals(B);
        return A.getArray();
    }
    public static double[][] matrixMult(double[][] m,double[][] n) {
        Matrix A = new Matrix(m);
        Matrix B = new Matrix(n);
        A = A.times(B);
        return A.getArray();
    }
    public static double[][] matrixInverse(double[][] m) {
        Matrix A = new Matrix(m);
        return A.inverse().getArray();
    }
    public static double[][] matrixTranspose(double[][] m) {

        Matrix A = new Matrix(m);
        return A.transpose().getArray();

    }
    public static double[][] matrixEigD(double[][] m) {

        Matrix A = new Matrix(m);
        return A.eig().getD().getArray();

    }
    public static double[][] matrixEigV(double[][] m) {

        Matrix A = new Matrix(m);
        return A.eig().getV().getArray();

    }
    public static double matrixDet(double[][] m) {

        Matrix A = new Matrix(m);
        return A.det();

    }
    public static double matrixRank(double[][] m) {

        Matrix A = new Matrix(m);
        return A.rank();

    }
    public static double[][] matrixSolve(double[][] m,double[][] n) {
        Matrix A = new Matrix(m);
        Matrix B = new Matrix(n);
        A = A.solve(B);
        return A.getArray();
    }
    public static double[][] matrixSolveTran(double[][] m,double[][] n) {
        Matrix A = new Matrix(m);
        Matrix B = new Matrix(n);
        A = A.solveTranspose(B);
        return A.getArray();
    }
    public static double[] TwotoOne(double[][] two) {
        double [] one;
        int len = two.length*two.length;
        one=new double[len];
        int index=0;
        for(int i=0;i<two.length;i++){
            for(int j=0;j<two[i].length;j++){
                one[index++]=two[i][j];
            }
        }
        return one;
    }
    public static double[][] OneToTwo(double[] one) {

        return new Matrix(one,(int)Math.sqrt(one.length)).transpose().getArray();
    }
    public static double[][] getResult(double[][] m,double[][] n,int flag){

        double[][] result = m;

        switch (flag){
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
        }
        return result;
    }
    public static double[][] OneToTwo(double[] one,int row,int column) {
        double[][] m = new double[row][column];
        int index=0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                m[i][j] = one[index];
                index++;
            }
        }
        return m;
    }
    public static double[] StrToNum(String a) {

        String[] b = a.replaceAll("\n",",").split(",| ");
        double [] num = new double[b.length];
        for (int i = 0; i < b.length; i++) {
            num[i] = Double.parseDouble(b[i]);
        }
        return num;
    }
    public static StringBuilder output(double[] c) {
        StringBuilder sb = new StringBuilder();
        int row = (int) Math.sqrt(c.length);
        for(int i = 0; i < c.length; i++){
            if((i+1) % row == 0){
                sb.append(String.format("%.3f", c[i])+"\n");
            }else{
                sb.append(String.format("%.3f", c[i])+",  ");
            }
        }
        return sb;
    }
    public static StringBuilder output(double[][] cc) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cc.length;i++){
            for(int j=0;j<cc[i].length;j++){
                sb.append(String.format("%.2f", cc[i][j])+"   ");
            }
            sb.append("\n");
        }
        return sb;
    }
    public static StringBuilder output(double[] c,int n) {
        StringBuilder sb = new StringBuilder();
        int row = (int) Math.sqrt(c.length);
        for(int i = 0; i < c.length; i++){
            if((i+1) % row == 0){
                sb.append(String.format("%."+n+"f", c[i])+"\n");
            }else{
                sb.append(String.format("%."+n+"f", c[i])+",  ");
            }
        }
        return sb;
    }
    public static StringBuilder output(double[][] cc,int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cc.length;i++){
            for(int j=0;j<cc[i].length;j++){
                sb.append(String.format("%."+n+"f", cc[i][j])+"   ");
            }
            sb.append("\n");
        }
        return sb;
    }
    public static int[][] FindId(double[][] id,int row,int column) {
        //获取行列的索引
        int id_r[] = new int[row];
        int id_c[] = new int[column];
        for (int i = 0; i < row; i++) {
            id_r[i] = i;
        }
        for (int i = 0; i < column; i++) {
            id_c[i] = i;
        }


        Matrix m1 = new Matrix(id);
       // m1.print(4,2);
        Matrix test = m1.getMatrix(id_r, id_c);

       // test.print(4,2);

        double [][] m = test.getArray();
        int[][] n = new int[row][column];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                n[i][j] = (int) m[i][j];
            }
        }
       // MyJama.dispMatrix(n);
        return n;
    }
    //功能与FindId差不多，裁剪数据。
    public static double[][] ProData(double[][] data,int row,int column) {
        //获取行列的索引
        int id_r[] = new int[row];
        int id_c[] = new int[column];
        for (int i = 0; i < row; i++) {
            id_r[i] = i;
        }
        for (int i = 0; i < column; i++) {
            id_c[i] = i;
        }


        Matrix m1 = new Matrix(data);
        // m1.print(4,2);

        return  m1.getMatrix(id_r, id_c).getArray();
    }


    public static StringBuilder UnitMatrix(int row, int column) {
        StringBuilder m = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == j ) {
                    if (j != column-1){
                        m.append("1,");
                    }else {
                        m.append("1");
                    }
                }else {
                    if (j != column-1){
                        m.append("0,");
                    }else {
                        m.append("0");
                    }
                }
            }
            m.append("\n");
        }

        return m;
    }
}
