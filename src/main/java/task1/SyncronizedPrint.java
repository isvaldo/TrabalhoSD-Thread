package task1;

/**
 * Created by isvaldo on 28/02/16.
 *
 * SEE Junit Testing for this example in
 *
 * Main/test/task1
 */
public class SyncronizedPrint {

    public final static StringBuilder toPrint = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {

        final Thread aTh = new Thread(new A());
        final Thread bTh = new Thread(new B());

        //Start Threads
        aTh.start();
        bTh.start();

        //Wait All Finish
        aTh.join();
        bTh.join();

        //for tests purposes
        System.out.println(toPrint);
    }

    /**
     * Try Print A and C
     */
    static class A extends PrintSync implements Runnable{
        public void run() {
            try {
                printChar(0, 'A');
                printChar(10, 'C');
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Try Print B and D
     */
    static class B extends PrintSync implements Runnable {
        public void run() {
            try {
                printChar(5, 'B');
                printChar(15, 'D');
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sync prints
     */
    static class PrintSync {

        /**
         *
         * @param trigger Condition for start
         * @param c char to print 5 times
         * @throws InterruptedException
         */
        public synchronized void printChar(final int trigger, final char c) throws InterruptedException {
            synchronized (toPrint) {
                while (!(toPrint.length() == trigger))
                    toPrint.wait();

                final int times = 5;
                for (int i = 0; i < times; i++)
                    toPrint.append(c);

                toPrint.notify();
            }
        }
    }
}
