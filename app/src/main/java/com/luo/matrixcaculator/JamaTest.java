package com.luo.matrixcaculator;
import Jama.Matrix;
public class JamaTest {
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
        double [][] array = {{-1,1,0},{-4,3,0}, {1 ,0,2}};
        double[][] array2 = {{4,5,6},{4,5,6},{7,8,10}};
        double[][] array3;
        Matrix m1 = new Matrix(array);
        Matrix m2 = new Matrix(array2);
        Matrix m3 = Matrix.random(3,1);
        m1 = m1.times(m2);
        array3 = m1.getArray();
        MatrixTest.dispMatrix(array3);




/*
        System.out.println(m1.det());
        System.out.println(m1.rank());

System.out.println("--------------");
        System.out.println(m2.det());
        System.out.println(m2.rank());*/






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

    }

}
