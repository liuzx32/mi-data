package mi.data.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {

    public String sayHello(String name) throws RemoteException;

}
