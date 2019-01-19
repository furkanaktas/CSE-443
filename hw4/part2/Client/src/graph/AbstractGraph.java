package graph;

/**
 * Abstract base class for graphs. A graph is a set
 * of vertices and a set of edges. Vertices are
 * represented by integers from 0 to n - 1. Edges
 * are ordered pairs of vertices.
 *
 * @author Koffman and Wolfgang
 */

public abstract class AbstractGraph
        implements Graph {

    // Data Fields
    /**
     * The number of vertices
     */
    private int numV;

    // Constructor

    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV The number of vertices
     */
    public AbstractGraph(int numV, int numEdge) {
        this.numV = numV;
    }

    // Accessor Methods

    /**
     * Return the number of vertices.
     *
     * @return The number of vertices
     */
    public int getNumV() {
        return numV;
    }
}
