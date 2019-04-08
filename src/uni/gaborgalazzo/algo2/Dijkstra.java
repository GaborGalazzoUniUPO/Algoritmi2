package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {


    private GraphInterface graph;

    public Dijkstra(GraphInterface graph) {
        for(int i = 0; i<graph.getOrder(); i++)
        {
            for(Edge e: graph.getOutEdges(i))
                if(e.getWeight()<0)
                    throw new IllegalArgumentException();
        }
        this.graph = graph;
    }

    public int[] getDistances(int source){
        if(source>=graph.getOrder() || source<0)
            throw new IllegalArgumentException();
        boolean[] found = new boolean[graph.getOrder()];
        int[] distances = new int[graph.getOrder()];
        Arrays.fill(distances, -1);
        found[source] = true;
        distances[source] = 0;
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        for(Edge e: graph.getOutEdges(source)){
            heap.add(e, distances[source] + e.getWeight());
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                distances[e.getHead()] = distances[e.getTail()] + e.getWeight();
                for(Edge n: graph.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                    heap.add(n, distances[n.getTail()] + n.getWeight());
            }
        }
        return distances;
    }

    public GraphInterface getMinimumPathsTree(int source){
        if(source>=graph.getOrder() || source<0)
            throw new IllegalArgumentException();
        GraphInterface tree = new UndirectedGraph(graph.getOrder());
        boolean[] found = new boolean[graph.getOrder()];
        int[] distances = new int[graph.getOrder()];
        Arrays.fill(distances, -1);
        found[source] = true;
        distances[source] = 0;
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        for(Edge e: graph.getOutEdges(source)){
            heap.add(e, distances[source] + e.getWeight());
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                distances[e.getHead()] = distances[e.getTail()] + e.getWeight();
                tree.addEdge(e);
                for(Edge n: graph.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n, distances[n.getTail()] + n.getWeight());
            }
        }
        return tree;
    }

    public ArrayList<Edge> getMinimumPath(int source, int dest){
        if(source>=graph.getOrder() || source<0)
            throw new IllegalArgumentException();
        if(dest>=graph.getOrder() || dest<0)
            throw new IllegalArgumentException();
        BFS bfs = new BFS(getMinimumPathsTree(source));
        return bfs.getShortestPath(source, dest);
    }
}
