package mi.data.join;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.RecursiveTask;

public class MainTask extends RecursiveTask<int[]> {

    private int[] m, n;

    public MainTask(int[] m, int[] n) {
        this.m = m;
        this.n = n;
    }

    @Override
    protected int[] compute() {
        if (m == null) {
            return n;
        }
        if (n == null) {
            return m;
        }
        return getArray(m, n);
    }

    public int[] getArray(int[] m,int[] n) {
        int im = 0;
        int jn = 0;
        int[] mn = new int[m.length + n.length];
        while(im < m.length && jn <n.length){
            if(m[im]<=n[jn]){
                mn[im + jn] = m[im];
                im++;
            }else{
                mn[im + jn] = n[jn];
                jn++;
            }
        }
        while(im < m.length){
            mn[im + jn] = m[im];
            im++;
        }
        while(jn < n.length){
            mn[im + jn] = n[jn];
            jn++;
        }
        return mn;
    }

    public static void main(String[] args) {
        int[] m = {1, 2, 3, 4, 5, 6, 14, 18, 30 };
        int[] n = {0, 2, 10, 11,  12, 13, 14, 15};
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(m);
        queue.add(n);
        queue.add(m);
        queue.add(n);
        //
        while(queue.size() > 1) {
            List<MainTask> list = new ArrayList<MainTask>();
            while (!queue.isEmpty()) {
                int[] a = queue.isEmpty() ? null : queue.poll();
                int[] b = queue.isEmpty() ? null : queue.poll();
                MainTask task = new MainTask(a, b);
                list.add(task);
                task.fork();
            }
            for (MainTask task : list) {
                int[] c = task.join();
                queue.add(c);
            }
        }
        int[] mn = queue.poll();
        for(int i = 0; i < mn.length; i++) {
            System.out.println(mn[i]);
        }
    }
}
