package com.luo.matrixcaculator;

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

}
