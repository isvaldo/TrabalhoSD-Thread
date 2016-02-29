package task3;

import java.util.*;

import static task3.SearchWithThread.*;

/**
 * Created by isvaldo on 28/02/16.
 */
public class SearchWithThread <T> {

    public static final int AMOUNT_THREAD = 4;
    public List<T> target;
    public T keyword;
    public static int[] result;

    SearchWithThread(List<T> target, T keyword){

        this.target = target;
        this.keyword = keyword;
        result = new int[AMOUNT_THREAD];
    }

    public int[] find () {
        int[] map = new int[5];
        int j = 0;
        for (int i = 0; i <= target.size(); i = i+(target.size()/ AMOUNT_THREAD)) {
            map[j] = i;
            j++;
        }

        List<Thread> thControl = new ArrayList<>();

        for (int i = 0; i < AMOUNT_THREAD; i++) {
            thControl.add(new Thread(new DoSearchInterval<T>(target, keyword, i ,map[i], map[i+1])));
        }

        thControl.stream().forEach(Thread::start);


        thControl.stream().forEach(m -> {
            try {
                m.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return result;

    }

    public static void main(String[] args){
     final List<Integer> interval = new ArrayList<>();

        for (int i = 0; i <=100; i++)
            interval.add(new Random().nextInt(100));

        final SearchWithThread<Integer> search = new SearchWithThread<>(interval, 21);

        final int[] result = search.find();

        for (int i = 0; i <result.length ; i++) {
            System.out.println(result[i]);
        }

    }
}

class DoSearchInterval <T> implements Runnable {

    private final int end;
    private final int start;
    private List<T> targets;
    private T keyword;
    private int threadNumber;

    public DoSearchInterval(List<T> targets,
                            T keyword,
                            int threadNumber,
                            int start,
                            int end) {
        this.targets = targets;
        this.keyword = keyword;
        this.threadNumber = threadNumber;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        result[threadNumber] = go();
    }

    public synchronized int go() {
        for (int i = start; i < end; i++) {
            if (targets.get(i).equals(keyword)){
                return i;
            }
        }
        return -1;
    }
}