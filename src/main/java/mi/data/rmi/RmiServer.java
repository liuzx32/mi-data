package mi.data.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// RMI服务端
public class RmiServer {

    public static void main(String[] args) {
        // 注册管理器
        Registry registry = null;
        try {
            // 创建一个服务注册管理器
            registry = LocateRegistry.createRegistry(8089);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            // 创建一个服务
            ServiceImpl server = new ServiceImpl();
            // 将服务绑定命名
            registry.rebind("hello", server);
            System.out.println("#bind server hello");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}