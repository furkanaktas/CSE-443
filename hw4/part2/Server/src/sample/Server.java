package sample;

import graph.Edge;
import graph.Graph;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends UnicastRemoteObject implements Services {

    private static final long serialVersionUID = 1L;
    private List<Client> users;

    protected Server() throws RemoteException {
        super();
        users = new ArrayList<>();
        users.add(new Client("admin","admin","furkan","aktas", 123, 100));
        users.add(new Client("admin2","admin2","ali","veli",  1234,100));
    }


    /**
     *
     * @param graph that will be evaluated
     * @param start  is vertex that we begin
     * @param username is id of current user
     * @return minimum spanning tree
     */
    @Override
    public synchronized ArrayList<Edge> minSpanningTree(Graph graph, int start, String username) {
        long startTime = System.nanoTime();

        Client client = getCurrUser(username);
        System.out.println("Request came at   "+ new Date() +"    from user : " + client.getId());

        int credit = client.getCredit();
        if (credit < 10)
        {
            System.out.println("User: "+ client.getId() +" don't have enough credit for minSpanningTree service !");
            return null;
        }

        ArrayList<Edge> result = new ArrayList<>();
        int numV = graph.getNumV();

        Set<Integer> vMinusS = new HashSet<>();

        Queue<Edge> pQ = new PriorityQueue<>(numV, (e1, e2) -> Double.compare(e1.getWeight(), e2.getWeight()));

        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }

        int current = start;
        while (vMinusS.size() != 0) {
            Iterator<Edge> iter = graph.edgeIterator(current);

            while (iter.hasNext()) {
                Edge edge = iter.next();
                int dest = graph.getVertexes().get(edge.getDest());
                if (vMinusS.contains(dest)) {
                    pQ.add(edge);
                }
            }

            int dest = -1;
            Edge edge = null;

            do {
                edge = pQ.remove();
                dest = graph.getVertexes().get(edge.getDest());
            } while (!vMinusS.contains(dest));

            vMinusS.remove(dest);
            result.add(edge);
            current = dest;
        }

        client.setCredit(credit-10);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Spanning tree calculate time : "+ totalTime/1000 + " ms");
        return result;
    }

    /**
     *
     * @param graph that will be evaluated
     * @param username is id of current user
     * @return incidence matrix
     * @throws RemoteException
     */
    @Override
    public synchronized int[][] getIncidenceMatrix(Graph graph, String username) throws RemoteException {
        long startTime = System.nanoTime();
        Client client = getCurrUser(username);
        System.out.println("Request came at   "+ new Date() +"    from user : " + client.getId());

        int credit = client.getCredit();
        if (credit < 5)
        {
            System.out.println("User: "+ client.getId() +" don't have enough credit for getIncidenceMatrix service !");
            return null;
        }

        int numV = graph.getNumV();
        int numEdge = 0;

        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                if (graph.isEdge(i, j)) {
                    Edge temp = graph.getEdge(i, j);
                    Edge temp2 = graph.getEdge(j, i);
                    if ((!edges.contains(temp)) && !edges.contains(temp2)) {
                        edges.add(temp);
                        ++numEdge;
                    }
                }
            }
        }

        int[][] incidenceMatrix = new int[numV][];
        for (int i = 0; i < numV; i++) {
            incidenceMatrix[i] = new int[numEdge];
            for (int j = 0; j < numEdge; j++) {
                incidenceMatrix[i][j] = 0;
            }
        }

        Iterator<Edge> iter = edges.iterator();
        for (int i = 0; i < numEdge; i++) {
            Edge edge = iter.next();
            int source = graph.getVertexes().get(edge.getSource());
            int dest = graph.getVertexes().get(edge.getDest());

            incidenceMatrix[dest][i] = 1;
            if (graph.isEdge(dest, source))
                incidenceMatrix[source][i] = 1;
            else
                incidenceMatrix[source][i] = -1;
        }

        client.setCredit(credit-5);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Incidence Matrix Calculate Time : "+ totalTime/1000 + " ms");
        return incidenceMatrix;
    }

    /**
     *
     * @param username is id of current user
     * @return credit amount of current user
     */
    public synchronized int getCredit(String username){
        Client client = getCurrUser(username);
        return   client == null ? -1 : client.getCredit() ;
    }

    /**
     *
     * @param username is id of current user
     * @param cardNo credit card no of current user
     * @param amount that will be loaded
     */
    @Override
    public synchronized void loadBalance(String username, Integer cardNo, Integer amount){
        Client client = getCurrUser(username);
        client.setCredit(client.getCredit()+ amount);
    }

    /**
     *
     * @param username is id of current user
     * @param password is password of current user
     * @return client that is logged in
     */
    @Override
    public synchronized Client login(String username, String password) {
        Client client = getCurrUser(username);
        if (client == null)
            return null;
        else
            return client.getPassword().equals(password) ? client : null;

    }

    /**
     *
     * @param username is id of current user
     * @param password is password of current user
     * @param name of user that will be registered
     * @param surname of user that will be registered
     * @param cardNo of user that will be registered
     * @return  true if process is success, or else false
     */
    @Override
    public synchronized boolean register(String username, String password, String name, String surname, Integer cardNo) {
        Client client = getCurrUser(username);
        if (client != null)
            return false;

        users.add(new Client(username, password, name, surname, cardNo,100));
        return true;
    }

    private Client getCurrUser(String username){
        for (Client client:
                users) {
            if (client.getId().equals(username)){
                return client;
            }
        }
        return null;
    }


}
