package sample;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerConnection {
    private Services services;

    private Client currClient;

    public ServerConnection() {}

    /**
     *
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws NotBoundException
     */
    public void connectServer() throws RemoteException, MalformedURLException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 8888);
        services = (Services) registry.lookup("RMI_EXAMPLE");
    }

    /**
     *
     * @param client that will be set
     */
    public void setCurrClient(Client client){
        currClient = client;
    }

    /**
     *
     * @return member client
     */
    public Client getCurrClient() {
        return currClient;
    }

    /**
     *
     * @return services that we can use
     */
    public Services getServices() {
        return services;
    }

}