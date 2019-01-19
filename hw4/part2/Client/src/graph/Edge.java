package graph;

import java.io.Serializable;

/**
 * An Edge represents a relationship between two
 * vertices.
 *
 * @author Koffman and Wolfgang
 */

public class Edge implements Serializable {
    private static final long serialVersionUID = 123456L;

    // Data Fields

    /**
     * The source vertix
     */
    private String source;


    /**
     * The destination vertix
     */
    private String dest;

    /**
     * The weight
     */
    private double weight;

    // Constructor

    /**
     * Construct a weighted edge with a source
     * of from and a destination of to. Set the
     * weight to w.
     *
     * @param source - The source vertix
     * @param dest   - The destination vertix
     * @param w      - The weight
     */
    public Edge(String source, String dest, double w) {
        this.source = source;
        this.dest = dest;
        weight = w;
    }

    // Methods

    /**
     * Get the source
     *
     * @return The value of source
     */
    public String getSource() {
        return source;
    }

    /**
     * Get the destination
     *
     * @return The value of dest
     */
    public String getDest() {
        return dest;
    }

    /**
     * Get the weight
     *
     * @return the value of weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Return a String representation of the edge
     *
     * @return A String representation of the edge
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("[(");
        sb.append(source);
        sb.append(", ");
        sb.append(dest);
        sb.append("): ");
        sb.append(Double.toString(weight));
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return true if two edges are equal. Edges
     * are equal if the source and destination
     * are equal. Weight is not conidered.
     *
     * @param obj The object to compare to
     * @return true if the edges have the same source
     * and destination
     */
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            return (source == edge.source
                    && dest == edge.dest);
        } else {
            return false;
        }
    }

    /**
     * Return a hash code for an edge.  The hash
     * code is the source shifted left 16 bits
     * exclusive or with the dest
     *
     * @return a hash code for an edge
     */
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < source.length(); i++) {
            hash = hash*31 + source.charAt(i);
        }

        int hash2 = 7;
        for (int i = 0; i < dest.length(); i++) {
            hash2 = hash*31 + dest.charAt(i);
        }
        return (hash << 16) ^ hash2;
    }

}
