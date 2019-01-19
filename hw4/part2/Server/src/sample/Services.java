package sample;

import graph.Edge;
import graph.Graph;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Services extends Remote {
    /**
     *
     * @param graph that will be evaluated
     * @param start  is vertex that we begin
     * @param username is id of current user
     * @return minimum spanning tree
     * @throws RemoteException
     */
    ArrayList<Edge> minSpanningTree(Graph graph, int start, String username) throws RemoteException;

    /**
     *
     * @param graph that will be evaluated
     * @param username is id of current user
     * @return incidence matrix
     * @throws RemoteException
     */
    int[][] getIncidenceMatrix(Graph graph, String username) throws RemoteException;

    /**
     *
     * @param username is id of current user
     * @return credit amount of current user
     * @throws RemoteException
     */
    int getCredit(String username) throws RemoteException;

    /**
     *
     * @param username is id of current user
     * @param cardNo credit card no of current user
     * @param amount that will be loaded
     * @throws RemoteException
     */
    void loadBalance(String username, Integer cardNo, Integer amount) throws RemoteException;

    /**
     *
     * @param username is id of current user
     * @param password is password of current user
     * @return client that is logged in
     * @throws RemoteException
     */
    Client login(String username, String password) throws RemoteException;

    /**
     *
     * @param username is id of current user
     * @param password is password of current user
     * @param name of user that will be registered
     * @param surname of user that will be registered
     * @param cardNo of user that will be registered
     * @return  true if process is success, or else false
     * @throws RemoteException
     */
    boolean register(String username, String password, String name, String surname, Integer cardNo) throws RemoteException;
}
