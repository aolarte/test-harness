package com.andresolarte.harness.lang.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {


    public static class Vertex {

        private final ArrayList<Edge> neighborhood = new ArrayList<>();
        private final String label;


        public Vertex(String label) {
            this.label = label;
        }


        public void addNeighbor(Edge edge) {
            if (this.neighborhood.contains(edge)) {
                return;
            }
            this.neighborhood.add(edge);
        }

        public boolean containsNeighbor(Edge other) {
            return this.neighborhood.contains(other);
        }


        public Edge getNeighbor(int index) {
            return this.neighborhood.get(index);
        }

        Edge removeNeighbor(int index) {
            return this.neighborhood.remove(index);
        }

        public void removeNeighbor(Edge e) {
            this.neighborhood.remove(e);
        }

        public int getNeighborCount() {
            return this.neighborhood.size();
        }


        public String getLabel() {
            return this.label;
        }

        public String toString() {
            return "Vertex " + label;
        }

        public int hashCode() {
            return this.label.hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof Vertex)) {
                return false;
            }
            Vertex v = (Vertex) other;
            return this.label.equals(v.label);
        }


        public ArrayList<Edge> getNeighbors() {
            return new ArrayList<>(this.neighborhood);
        }

    }

    public static class Edge implements Comparable<Edge> {

        private final Vertex one;
        private final Vertex two;
        private final int weight;


        public Edge(Vertex one, Vertex two) {
            this(one, two, 1);
        }


        public Edge(Vertex one, Vertex two, int weight) {
            this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
            this.two = (this.one == one) ? two : one;
            this.weight = weight;
        }

        public Vertex getNeighbor(Vertex current) {
            if (!(current.equals(one) || current.equals(two))) {
                return null;
            }

            return (current.equals(one)) ? two : one;
        }

        public Vertex getOne() {
            return this.one;
        }

        public Vertex getTwo() {
            return this.two;
        }

        public int getWeight() {
            return this.weight;
        }


        /**
         * Note that the compareTo() method deviates from
         * the specifications in the Comparable interface. A
         * return value of 0 does not indicate that this.equals(other).
         * The equals() method checks the Vertex endpoints, while the
         * compareTo() is used to compare Edge weights
         *
         * @param other The Edge to compare against this
         * @return int this.weight - other.weight
         */
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }

        public String toString() {
            return "({" + one + ", " + two + "}, " + weight + ")";
        }

        public int hashCode() {
            return (one.getLabel() + two.getLabel()).hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof Edge)) {
                return false;
            }

            Edge e = (Edge) other;

            return e.one.equals(this.one) && e.two.equals(this.two);
        }
    }

    private Map<String, Vertex> vertices = new HashMap<>();
    private Map<Integer, Edge> edges = new HashMap<>();

    public Graph() {

    }


    public Graph(ArrayList<Vertex> vertices) {
        for (Vertex v : vertices) {
            this.vertices.put(v.getLabel(), v);
        }

    }


    public boolean addEdge(Vertex one, Vertex two) {
        return addEdge(one, two, 1);
    }


    public boolean addEdge(Vertex one, Vertex two, int weight) {
        if (one.equals(two)) {
            return false;
        }

        //ensures the Edge is not in the Graph
        Edge e = new Edge(one, two, weight);
        if (edges.containsKey(e.hashCode())) {
            return false;
        }

        //and that the Edge isn't already incident to one of the vertices
        else if (one.containsNeighbor(e) || two.containsNeighbor(e)) {
            return false;
        }

        edges.put(e.hashCode(), e);
        one.addNeighbor(e);
        two.addNeighbor(e);
        return true;
    }

    public boolean containsEdge(Edge e) {
        if (e.getOne() == null || e.getTwo() == null) {
            return false;
        }

        return this.edges.containsKey(e.hashCode());
    }

    public Edge removeEdge(Edge e) {
        e.getOne().removeNeighbor(e);
        e.getTwo().removeNeighbor(e);
        return this.edges.remove(e.hashCode());
    }


    public boolean containsVertex(Vertex vertex) {
        return this.vertices.get(vertex.getLabel()) != null;
    }


    public Vertex getVertex(String label) {
        return vertices.get(label);
    }


    public boolean addVertex(Vertex vertex, boolean overwriteExisting) {
        Vertex current = this.vertices.get(vertex.getLabel());
        if (current != null) {
            if (!overwriteExisting) {
                return false;
            }

            while (current.getNeighborCount() > 0) {
                this.removeEdge(current.getNeighbor(0));
            }
        }

        vertices.put(vertex.getLabel(), vertex);
        return true;
    }

    public Vertex removeVertex(String label) {
        Vertex v = vertices.remove(label);

        while (v.getNeighborCount() > 0) {
            this.removeEdge(v.getNeighbor((0)));
        }

        return v;
    }

    public Set<String> vertexKeys() {
        return this.vertices.keySet();
    }

    public Set<Edge> getEdges() {
        return new HashSet<>(this.edges.values());
    }

    public Collection<Vertex> getVertices() {
        return new HashSet<>(vertices.values());
    }

    public static class DijkstraAlgorithm {

        private final List<Vertex> nodes;
        private final List<Edge> edges;
        private Set<Vertex> settledNodes;
        private Set<Vertex> unSettledNodes;
        private Map<Vertex, Vertex> predecessors;
        private Map<Vertex, Integer> distance;

        public DijkstraAlgorithm(Graph graph) {
            // create a copy of the array so that we can operate on this array
            this.nodes = new ArrayList<>(graph.getVertices());
            this.edges = new ArrayList<>(graph.getEdges());
        }

        public void execute(Vertex source) {
            settledNodes = new HashSet<>();
            unSettledNodes = new HashSet<>();
            distance = new HashMap<>();
            predecessors = new HashMap<>();
            distance.put(source, 0);
            unSettledNodes.add(source);
            while (unSettledNodes.size() > 0) {
                Vertex node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            }
        }

        private void findMinimalDistances(Vertex node) {
            List<Vertex> adjacentNodes = getNeighbors(node);
            for (Vertex target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }

        }

        private int getDistance(Vertex node, Vertex target) {
            for (Edge edge : edges) {
                if (edge.getOne().equals(node)
                        && edge.getTwo().equals(target)) {
                    return edge.getWeight();
                }
            }
            throw new RuntimeException("Should not happen");
        }

        private List<Vertex> getNeighbors(Vertex node) {
            List<Vertex> neighbors = new ArrayList<Vertex>();
            for (Edge edge : edges) {
                if (edge.getOne().equals(node)
                        && !isSettled(edge.getTwo())) {
                    neighbors.add(edge.getTwo());
                }
            }
            return neighbors;
        }

        private Vertex getMinimum(Set<Vertex> vertexes) {
            Vertex minimum = null;
            for (Vertex vertex : vertexes) {
                if (minimum == null) {
                    minimum = vertex;
                } else {
                    if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                        minimum = vertex;
                    }
                }
            }
            return minimum;
        }

        private boolean isSettled(Vertex vertex) {
            return settledNodes.contains(vertex);
        }

        private int getShortestDistance(Vertex destination) {
            Integer d = distance.get(destination);
            if (d == null) {
                return Integer.MAX_VALUE;
            } else {
                return d;
            }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public List<Vertex> getPath(Vertex target) {
            LinkedList<Vertex> path = new LinkedList<>();
            Vertex step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            return path;
        }

    }


    public static void main(String[] args) {
        Graph graph = new Graph();


        //initialize some vertices and add them to the graph
        Vertex[] vertices = new Vertex[5];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex("" + i);
            graph.addVertex(vertices[i], true);
        }
        display(vertices);


        graph.addEdge(graph.getVertex("0"), graph.getVertex("1"), 10);
        graph.addEdge(graph.getVertex("1"), graph.getVertex("2"), 10);
        graph.addEdge(graph.getVertex("2"), graph.getVertex("3"), 10);
        graph.addEdge(graph.getVertex("3"), graph.getVertex("4"), 10);

        graph.addEdge(graph.getVertex("0"), graph.getVertex("3"), 15);

        DijkstraAlgorithm algo = new DijkstraAlgorithm(graph);
        algo.execute(graph.getVertex("0"));

        List<Vertex> path = algo.getPath(graph.getVertex("4"));


        for (Vertex vertex : path) {
            System.out.println(vertex);
        }


    }

    private static void display(Vertex[] vertices) {
        System.out.println("Displaying vertices");
        Arrays.asList(vertices).forEach(vertex -> {
            System.out.println(vertex);
            vertex.getNeighbors().forEach(System.out::println);
            System.out.println();

        });
        System.out.println("Done displaying vertices");
    }

}
