package mi.data.join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class MyForkJoinTest {

    public static void main(String[] args) throws Exception {
        MyForkJoinTask task = new MyForkJoinTask(1, 4);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> result = pool.submit(task);
        System.out.println("result is " + result.get());
    }
}