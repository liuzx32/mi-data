package mi.data.join;

import java.util.concurrent.RecursiveTask;

public class MyForkJoinTask extends RecursiveTask<Integer> {

    private final int spilSize = 2;
    private int start, end;

    public MyForkJoinTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {
        int sum = 0;
        if ((end - start) < 2) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            MyForkJoinTask firstTask = new MyForkJoinTask(start, middle);
            MyForkJoinTask secondTask = new MyForkJoinTask(middle + 1, end);
            // 提交任务
            firstTask.fork();
            secondTask.fork();
            // 阻塞线程等待任务结果
            Integer firstResult = firstTask.join();
            Integer secondResult = secondTask.join();
            sum = firstResult.intValue() + secondResult.intValue();
        }
        return sum;
    }
}