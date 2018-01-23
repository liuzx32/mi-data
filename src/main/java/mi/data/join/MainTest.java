package mi.data.join;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MainTest {

    public static void main(String[] args) {
        String intput = "k1=v1,k2=v2,k3=[k4=v4,k5=v5],k6=v6";
        HashMap<String, Object> map = parse(intput);
        System.out.println(map);
    }

    public static HashMap<String, Object> parse(String input) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (input == null || input.length() == 0) {
            return map;
        }
        Queue<String> queue = new LinkedList<String>();
        toQueue(queue, input);
        while(!queue.isEmpty()) {
            String str = queue.poll();
            String[] kv = str.split("=");
            if (kv[1].startsWith("[")) {
                map.put(kv[0], getMap(kv[1]));
            } else {
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }

    public static HashMap<String, Object> getMap(String input) {
        Stack<String> stack = new Stack<String>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> tmp = map;
        String line = getKvStr(input);
        System.out.println("@" + line);
        String[] kvs = line.split(",");
        for (String str : kvs) {
            System.out.println("#" + str);
        }
        for (String str : kvs) {
            String[] kv = str.split("=");
            String key = kv[0];
            stack.push(kv[1]);
            while (!stack.isEmpty()) {
                String st = stack.pop();
                if (st.startsWith("[")) {
                    HashMap mp = new HashMap<String, Object>();
                    tmp.put(key, mp);
                    String[] ks = st.split("=");
                    tmp = mp;
                    key = ks[0];
                    stack.push(getKvStr(ks[1]));
                } else if (st.contains("=")) {
                    HashMap mp = new HashMap<String, Object>();
                    String[] ks = st.split("=");
                    mp.put(ks[0], ks[1]);
                    tmp.put(key, mp);
                } else {
                    tmp.put(key, st);
                }
            }
        }
        return map;
    }

    public static String getKvStr(String value) {
        System.out.println("!" + value);
        if (value.startsWith("[") && value.endsWith("]")) {
            return value.substring(1, value.length() - 2);
        }
        if (value.startsWith("[")) {
            return value.substring(1);
        }
        return value;
    }

    public static void toQueue(Queue<String> queue, String input) {
        String[] kvs = input.split(",");
        for (String kv : kvs) {
            queue.add(kv);
        }
    }
}
