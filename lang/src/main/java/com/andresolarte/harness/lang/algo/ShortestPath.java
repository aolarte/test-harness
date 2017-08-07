package com.andresolarte.harness.lang.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPath {
    public static class Vertex {
        final private String id;
        final private String name;


        public Vertex(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Edge {
        private final String id;
        private final Vertex source;
        private final Vertex destination;
        private final int weight;

        public Edge(String id, Vertex source, Vertex destination, int weight) {
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }

        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " " + destination;
        }
    }

    public class Graph {
        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
        }

        public List<Vertex> getVertexes() {
            return vertexes;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    Map<Vertex, Integer> distance = new HashMap<>();
    List<Vertex> settledNodes = new ArrayList<>();
    List<Vertex> unSettledNodes = new ArrayList<>();
    Map<Vertex, Vertex> predecessors = new HashMap<>();


    public void run(Graph graph, Vertex sourceNode) {
        //Foreach node set distance[node] = HIGH

        graph.vertexes.stream().forEach(v -> distance.put(v, Integer.MAX_VALUE));

        //Add sourceNode to UnSettledNodes
        unSettledNodes.add(sourceNode);
        //distance[sourceNode]= 0
        distance.put(sourceNode, 0);

        while (unSettledNodes.size() > 0) {
            //evaluationNode = getNodeWithLowestDistance(UnSettledNodes)
            Vertex evaluationVertex = findVertexWithLowestDistance();
            //remove evaluationNode from UnSettledNodes
            unSettledNodes.remove(evaluationVertex);
            //add evaluationNode to SettledNodes
            settledNodes.add(evaluationVertex);
            //evaluatedNeighbors(evaluationNode)
            evaluatedNeighbors(graph, evaluationVertex);
        }
    }


    Vertex findVertexWithLowestDistance() {
        return unSettledNodes.stream().sorted(Comparator.comparingInt(distance::get)).findFirst().get();
    }

    void evaluatedNeighbors(Graph graph, Vertex evaluationVertex) {
        //Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes {
        graph.getEdges().stream()
                .filter(e -> e.source == evaluationVertex)
                .filter(e -> !settledNodes.contains(e.destination)).forEach(e -> {
            //edgeDistance = getDistance(edge(evaluationNode, destinationNode))
            //newDistance = distance[evaluationNode] + edgeDistance
            int edgeDistance = e.getWeight();
            int newDistance = distance.get(evaluationVertex) + edgeDistance;
            if (distance.get(e.destination) > newDistance) {
                distance.put(e.destination, newDistance);
                predecessors.put(e.destination, evaluationVertex);
                unSettledNodes.add(e.destination);
            }
        });
    }

    public List<Vertex> getPath(Vertex target) {
        List<Vertex> path = new ArrayList<>();
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

    //////////////////// TEST

    public static void main(String... args) {
        new ShortestPath().testExecute();
    }

    public void testExecute() {
        List<Vertex> nodes;
        List<Edge> edges;

        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85, nodes, edges);
        addLane("Edge_1", 0, 2, 217, nodes, edges);
        addLane("Edge_2", 0, 4, 173, nodes, edges);
        addLane("Edge_3", 2, 6, 186, nodes, edges);
        addLane("Edge_4", 2, 7, 103, nodes, edges);
        addLane("Edge_5", 3, 7, 183, nodes, edges);
        addLane("Edge_6", 5, 8, 250, nodes, edges);
        addLane("Edge_7", 8, 9, 84, nodes, edges);
        addLane("Edge_8", 7, 9, 167, nodes, edges);
        addLane("Edge_9", 4, 9, 502, nodes, edges);
        addLane("Edge_10", 9, 10, 40, nodes, edges);
        addLane("Edge_11", 1, 10, 600, nodes, edges);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        this.run(graph, nodes.get(0));
        List<Vertex> path = this.getPath(nodes.get(10));

        assert path != null;
        assert path.size() > 0;

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration, List<Vertex> nodes, List<Edge> edges) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }

}
