package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class BFS {

    private final GraphInterface graph;

    public BFS(GraphInterface graphInterface){
        this.graph = graphInterface;
    }

    public ArrayList<Integer> getNodesInOrderOfVisit(int source){

        ArrayList<Integer> visit = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] found = new boolean[graph.getOrder()];
        queue.add(source);
        while (!queue.isEmpty()){
            Integer u = queue.remove(0);
            if(!found[u]){
                visit.add(u);
                found[u]=true;
                for(Edge v: graph.getOutEdges(u))
                    queue.add(v.getHead());

            }
        }
        return visit;
    }

    public int[] getDistance(int source){
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] found = new boolean[graph.getOrder()];
        int[] distances = new int[graph.getOrder()];
        Arrays.fill(distances, -1);
        distances[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()){
            Integer u = queue.remove(0);
            if(!found[u]){
                found[u]=true;
                for(Edge v: graph.getOutEdges(u)) {
                    queue.add(v.getHead());

                    if(!found[v.getHead()])
                        distances[v.getHead()] = distances[u] +1;
                }

            }
        }
        return distances;
    }

    public GraphInterface bfsTree(int source){
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] found = new boolean[graph.getOrder()];
        queue.add(source);
        GraphInterface tree = graph.create();
        boolean[] inTree = new boolean[graph.getOrder()];
        while (!queue.isEmpty()){
            Integer u = queue.remove(0);
            if(!found[u]){
                found[u]=true;
                for(Edge v: graph.getOutEdges(u)) {
                    queue.add(v.getHead());

                    if(!inTree[v.getHead()]) {
                        tree.addEdge(u,v.getHead());
                        inTree[v.getHead()] = true;
                    }
                }

            }
        }
        return tree;
    }


    public int[] getOrderOfVisit(int source){
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] found = new boolean[graph.getOrder()];
        queue.add(source);
        int[] order = new int[graph.getOrder()];
        int i = 0;
        while (!queue.isEmpty()){
            Integer u = queue.remove(0);
            if(!found[u]){
                found[u]=true;
                order[u] = i++;
                for(Edge v: graph.getOutEdges(u)) {
                    queue.add(v.getHead());
                }

            }
        }
        return order;
    }

    public ArrayList<Edge> getShortestPath(int sorg, int dest){
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Edge> shortestPath = new ArrayList<>();
        int[] p = new int[graph.getOrder()];
        Arrays.fill(p, -1);
        boolean[] found = new boolean[graph.getOrder()];
        queue.add(sorg);
        while (!queue.isEmpty()){
            Integer u = queue.remove(0);
            if(!found[u]){
                found[u] = true;
                for(Integer v: graph.getNeighbors(u)) {
                    if(!found[v]){
                        p[v] = u;
                        if(v == dest){
                            int i = dest;
                            while (i!=sorg){
                                shortestPath.add(new Edge(p[i], i));
                                i=p[i];
                            }
                            return shortestPath;
                        }
                    }
                    queue.add(v);

                }

            }
        }
        return null;

    }
}
