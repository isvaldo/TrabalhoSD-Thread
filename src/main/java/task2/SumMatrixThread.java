package task2;

import java.util.Arrays;

/**
 * Created by isvaldo on 28/02/16.
 * SEE Junit Testing for this example in
 *
 * Main/test/task2
 */
public class  SumMatrixThread {

    public  Integer  [][] matrix1;
    public  Integer  [][] matrix2;
    public static Integer  [][] result;

    public SumMatrixThread(final Integer [][] matrix1, final Integer [][] matrix2) throws Exception {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        result = new Integer[matrix1.length][matrix1[0].length];
        validMatrix();
    }


    public static void main(String[] args) throws Exception {

        Integer[][] a = { {1,1},{1,1}};
        Integer[][] b = { {1,1},{1,1}};


        SumMatrixThread test = new SumMatrixThread(a,b);

        test.sum();

        for (int i=0; i<result.length;i++){
            for(int j=0; j < result[0].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
    }


    public Integer[][] sum() {
        Thread controlth[] = new Thread[matrix1.length];
        for(int i = 0; i<matrix1.length;i++){
            controlth[i] = new Thread(new threadCalc(matrix1[i], matrix2[i], i));
        }
        Arrays.stream(controlth).forEach(Thread::start);

        Arrays.stream(controlth).forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public Integer[][] getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(Integer[][] matrix1) {
        this.matrix1 = matrix1;
    }

    public Integer[][] getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(Integer[][] matrix2) {
        this.matrix2 = matrix2;
    }

    public static Integer[][] getResult() {
        return result;
    }

    public static void setResult(Integer[][] result) {
        SumMatrixThread.result = result;
    }

    private void validMatrix() throws Exception {
        if (matrix1.length == matrix2.length){
            for (int i = 0; i<matrix1.length; i++) {
                if (! (matrix1[i].length == matrix1[i].length)){
                    throw new Exception("Matrix invalid number of cells");
                }
            }
        }else{
            throw new Exception("Matrix invalid number of columns");
        }




    }
    class threadCalc implements Runnable{

        public final Integer[] a;
        public final Integer[] b;
        public final Integer[] r;
        public final int c;
        threadCalc(final Integer[] a, final Integer[] b, final int c){
            this.a = a;
            this.b = b;
            this.c = c;
            this.r = new Integer[a.length];
        }

        @Override
        public void run() {
            for(int i =0;i<a.length;i++){
                r[i] = a[i] +b[i];
            }
            result[c] = r;
        }
    }
}
