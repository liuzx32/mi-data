package mi.data.join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class MyForkJoinTest {

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        //
        MyForkJoinTask task = new MyForkJoinTask(1, 4);
        Future<Integer> result = pool.submit(task);
        System.out.println("result is " + result.get());
    }
}