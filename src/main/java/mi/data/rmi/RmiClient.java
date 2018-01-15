package mi.data.rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// RMI客户端
public class RmiClient {

    public static void main(String[] args) {
        // 注册管理器
        Registry registry = null;
        try {
            // 获取服务注册管理器
            registry = LocateRegistry.getRegistry("127.0.0.1", 8089);
            // 列出所有注册的服务
            String[] list = registry.list();
            for (String s : list) {
                System.out.println(s);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            // 根据命名获取服务
            IService server = (IService) registry.lookup("hello");
            // 调用远程方法
            String result = server.sayHello("world");
            // 输出调用结果
            System.out.println("result from remote : " + result);
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}