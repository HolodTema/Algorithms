import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");


        {
            nodeA.addDestination(nodeB, 10);
            nodeA.addDestination(nodeC, 15);

            nodeB.addDestination(nodeD, 12);
            nodeB.addDestination(nodeF, 15);

            nodeC.addDestination(nodeE, 10);

            nodeD.addDestination(nodeE, 2);
            nodeD.addDestination(nodeF, 1);

            nodeF.addDestination(nodeE, 5);
        }


        Graph graph = new Graph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        Graph graphResult = dijkstra(graph, nodeA);

        for(Node node : graphResult.getNodes()) {
            System.out.println(node.getName() + ": " + node.getDistance());
        }



    }

    private static Graph dijkstra(Graph graph, Node source) {
        //the distance from start to start is 0
        source.setDistance(0);

        //visited nodes
        Set<Node> settledNodes = new HashSet<>();
        //unvisited nodes
        Set<Node> unsettledNodes = new HashSet<>();

        //we have not visited start node yet
        unsettledNodes.add(source);

        while(!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);

            for(Map.Entry<Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if(!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            //mark the node as visited
            unsettledNodes.remove(currentNode);
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumDistance(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        int sourceDistance = sourceNode.getDistance();
        if(sourceDistance + edgeWeight < adjacentNode.getDistance()) {
            adjacentNode.setDistance(sourceDistance+edgeWeight);

            //pass the previous route to the node which was calculated a moment ago.
            //and add sourceNode to it
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            adjacentNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for(Node node: unsettledNodes) {
            if(node.getDistance()<lowestDistance) {
                lowestDistanceNode = node;
                lowestDistance = node.getDistance();
            }
        }
        return lowestDistanceNode;
    }
}
