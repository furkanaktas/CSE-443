package graph;

import java.util.*;

/**
 * A MatrixGraph is an implementation of the Graph
 * abstract class that uses an array to represent the
 * edges.
 *
 * @author Koffman and Wolfgang
 */

public class MatrixGraph
        extends AbstractGraph {

    // Data field
    /**
     * The two dimensional array to represen an edge
     */
    private double[][] edges;

    public Map<String, Integer> getVertexes() {
        return vertexes;
    }

    private Map<String, Integer> vertexes;

    // Consructors

    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     * @param numV - The number of vertices
     */
    public MatrixGraph(int numV) {
        super(numV, 0);

        vertexes = new HashMap<>();
        edges = new double[numV][];

        for (int i = 0; i != numV; ++i) {
            edges[i] = new double[numV];
            for (int j = 0; j != numV; ++j) {
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    // Implementation of abstract methods

    /**
     * Insert a new edge into the graph
     *
     * @param edge - The new edge
     */
    @Override
    public void insert(Edge edge) {
        String src = edge.getSource();
        String dst = edge.getDest();

        if (!vertexes.containsKey(src)){
            vertexes.put(src, vertexes.size());
        }
        if (!vertexes.containsKey(dst)){
            vertexes.put(dst, vertexes.size());
        }

        setEdgeValue(vertexes.get(src), vertexes.get(dst),
                edge.getWeight());
    }

    /**
     * Determine if an edge exists
     *
     * @param source - The source vertix
     * @param dest   - The destination vertix
     * @return true if there is an edge from u to v
     */
    @Override
    public boolean isEdge(int source, int dest) {
        int numV = getNumV();
        if (source < 0 || source >= numV || dest < 0 || dest >= numV)
            return false;
        return Double.POSITIVE_INFINITY != getEdgeValue(source, dest);
    }

    /**
     * Get the edge between two vertices. If an
     * edge does not exist, an Edge with a weight
     * of POSITIVE_INFINITY is returned.
     *
     * @param source - The source
     * @param dest   - The destination
     * @return the edge between these two vertices
     */
    @Override
    public Edge getEdge(int source, int dest) {
        return new Edge(getKeyByValue(source), getKeyByValue(dest),
                getEdgeValue(source, dest));
    }

    /**
     * Return an iterator to the edges connected
     * to a given vertix.
     *
     * @param source - The source vertix
     * @return an EdgeIterator to the vertices
     * contcted to source
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new Iter(source);
    }

    /**
     * Method to set the value of an edge
     *
     * @param source - The source vertix
     * @param dest   - The destination vertix
     * @param wt     - The weight
     */
    private void setEdgeValue(int source, int dest, double wt) {
        edges[source][dest] = wt;
    }

    /**
     * Method to get the value of an edge
     *
     * @param source - The source vertix
     * @param dest   - The destination vertix
     * @return The weight of this edge or
     * POSITIVE_INFINITY if no edge exists
     */
    private double getEdgeValue(int source, int dest) {
        return edges[source][dest];
    }

    // Iter class

    /**
     * An iterator to the edges.  An Edge iterator is
     * similar to an Iterator except that its
     * next method will always return an edge
     */
    private class Iter implements Iterator<Edge> {
        // Data fields

        /**
         * The source vertix for this iterator
         */
        private int source;
        /**
         * The current index for this iterator
         */
        private int index;

        // Constructor

        /**
         * Construct an EdgeIterator for a given vertix
         *
         * @param source - The source vertix
         */
        public Iter(int source) {
            this.source = source;
            index = -1;
            advanceIndex();
        }

        /**
         * Return true if there are more edges
         *
         * @return true if there are more edges
         */
        @Override
        public boolean hasNext() {
            return index != getNumV();
        }

        /**
         * Return the next edge if there is one
         *
         * @return the next Edge in the iteration
         * @throws NoSuchElementException - there are no
         *                                more edges
         */
        @Override
        public Edge next() {
            if (index == getNumV()) {
                throw new java.util.NoSuchElementException();
            }
            Edge returnValue = new Edge(getKeyByValue(source), getKeyByValue(index),
                    getEdgeValue(source, index));
            advanceIndex();
            return returnValue;
        }

        /**
         * Remove is not implememted
         *
         * @throws UnsupportedOperationException if called
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Advance the index to the next edge
         */
        private void advanceIndex() {
            do {
                index++;
            } while (index != getNumV() && Double.POSITIVE_INFINITY == getEdgeValue(source, index));
        }
    }

    private String getKeyByValue(Integer value) {
        for (Map.Entry<String, Integer> entry : vertexes.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * @param start ağacı istenilen vertex
     * @return istenilen vertex'in ağacı
     */
    public int[] breadthFirstSearch(int start) {
        Queue<Integer> theQueue = new LinkedList<Integer>();
        // Declare array parent and initialize its elements to –1.
        int[] parent = new int[this.getNumV()];
        for (int i = 0; i < this.getNumV(); i++) {
            parent[i] = -1;
        }
        // Declare array identified and
        // initialize its elements to false.
        boolean[] identified = new boolean[this.getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
        identified[start] = true;
        theQueue.offer(start);
        /* While the queue is not empty */
        while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
            int current = theQueue.remove();
            /* Examine each vertex, neighbor, adjacent to current. */
            Iterator<Edge> itr = this.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = vertexes.get(edge.getDest());
                // If neighbor has not been identified
                if (!identified[neighbor]) {
                    // Mark it identified.
                    identified[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor)
             into the tree. */
                    parent[neighbor] = current;
                }
            }
            // Finished visiting current.
        }
        return parent;

    }

    /**** END EXERCISE ****/
}
