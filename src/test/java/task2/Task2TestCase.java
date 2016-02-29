package task2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by isvaldo on 28/02/16.
 */
public class Task2TestCase {


    @Test
    public void testCase1() throws Exception {
        Integer[][] a = { {1, 1}, {1, 1}};
        Integer[][] b = { {1, 1}, {1, 1}};

        Integer[][] r = { {2, 2}, {2, 2}};
        SumMatrixThread thSum = new SumMatrixThread(a,b);

        thSum.sum();

        Assert.assertArrayEquals(SumMatrixThread.getResult(), r);

    }

    @Test
    public void testCase2() throws Exception {
        Integer[][] a = { {2, 10}, {5, 20}};
        Integer[][] b = { {4, 0}, {10, 98}};

        Integer[][] r = { {6, 10}, {15, 118}};
        SumMatrixThread thSum = new SumMatrixThread(a,b);

        thSum.sum();

        Assert.assertArrayEquals(SumMatrixThread.getResult(), r);

    }

    @Test
    public void testCase3() throws Exception {
        Integer[][] a = { {0, 0}, {0, 0}};
        Integer[][] b = { {0, 0}, {0, 0}};

        Integer[][] r = { {0, 0}, {0, 0}};
        SumMatrixThread thSum = new SumMatrixThread(a,b);

        thSum.sum();

        Assert.assertArrayEquals(SumMatrixThread.getResult(), r);

    }

}
