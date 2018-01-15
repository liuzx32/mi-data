package mi.data.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceCenter implements RPCServer {

    protected static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    protected static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();
    private static boolean isRunning = false;
    private static int port;

    public ServiceCenter(int port) {
        this.port = port;
    }

    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while (true) {
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                System.out.println(1);
                executor.execute(new ServiceTask(server.accept()));
                System.out.println(2);
            }
        } finally {
            server.close();
        }
    }

    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

}