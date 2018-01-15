package mi.data.rpc;

import mi.data.rmi.IService;
import mi.data.rmi.ServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                try {
                    RPCServer serviceServer = new ServiceCenter(8099);
                    serviceServer.register(IService.class, ServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        IService service = RPCClient.getRemoteProxyObj(IService.class, new InetSocketAddress("localhost", 8099));
        System.out.println(service.sayHello("world"));
        System.out.println(service.sayHello("bean"));
        System.out.println(service.sayHello("test"));
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(service.sayHello("num" + i));
        }
    }
}
