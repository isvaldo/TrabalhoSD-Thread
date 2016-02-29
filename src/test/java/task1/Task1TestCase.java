package task1;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by isvaldo on 28/02/16.
 */
public class Task1TestCase {

    @Test
    public void testCase() throws InterruptedException {

        SyncronizedPrint.main(null);

        // String Expected
        final String matchString = "AAAAABBBBBCCCCCDDDDD";

        // test Output
        final boolean  assertTest = SyncronizedPrint.toPrint.toString().equals(matchString);

        // Assert Test
        Assert.assertTrue( assertTest);
    }
}
