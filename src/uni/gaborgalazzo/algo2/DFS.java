package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.GraphInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DFS {

    private GraphInterface graph;

    public DFS(GraphInterface graph) {
        this.graph = graph;
    }

    public GraphInterface getTree(int source) throws NotAllNodesReachedException {
        GraphInterface dfsTree = graph.create();
        boolean[] s = new boolean[graph.getOrder()];
        buildTree(source, s, dfsTree);
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return dfsTree;
    }

    private void buildTree(int source, boolean[] found, GraphInterface dfsTree){
        if(source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for(Integer neighbor: graph.getNeighbors(source)){
            if(!found[neighbor]){
                dfsTree.addEdge(source, neighbor);
                buildTree(neighbor, found, dfsTree);
            }
        }
    }

    public ArrayList<Integer> getNodesInOrderPostVisit(int source) throws NotAllNodesReachedException {
        ArrayList<Integer> orderPostVisit = new ArrayList<>();
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderPostTree(source, s, orderPostVisit);
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return orderPostVisit;
    }

    private void buildOrderPostTree(int source, boolean[] found, ArrayList<Integer> orderPostVisit){
        if(source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for(Integer neighbor: graph.getNeighbors(source)){
            if(!found[neighbor]){
                buildOrderPostTree(neighbor, found, orderPostVisit);
            }
        }
        orderPostVisit.add(source);
    }

    public GraphInterface getForest() throws NotAllNodesReachedException{
        GraphInterface dfsForest = graph.create();
        boolean[] s = new boolean[graph.getOrder()];
        for(int i = 0; i<graph.getOrder(); i++){
            if(!s[i])
                buildTree(i, s, dfsForest);
        }
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return dfsForest;
    }


    public ArrayList<Integer> getNodesInOrderOfVisit(int source) throws NotAllNodesReachedException {
        ArrayList<Integer> orderVisit = new ArrayList<>();
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderTree(source, s, orderVisit);
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return orderVisit;
    }

    private void buildOrderTree(int source, boolean[] found, ArrayList<Integer> orderVisit) {
        if(source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        orderVisit.add(source);
        for(Integer neighbor: graph.getNeighbors(source)){
            if(!found[neighbor]){
                buildOrderTree(neighbor, found, orderVisit);
            }
        }
    }

    public int[] getOrderPostVisit(int source) throws NotAllNodesReachedException {
        int[] orderPostVisit = new int[graph.getOrder()];
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderPostTree(source, s, orderPostVisit, new int[]{0});
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return orderPostVisit;
    }

    private void buildOrderPostTree(int source, boolean[] found, int[] orderPostVisit, int[] level){
        if(source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for(Integer neighbor: graph.getNeighbors(source)){
            if(!found[neighbor]){
                buildOrderPostTree(neighbor, found, orderPostVisit,level);
            }
        }
        orderPostVisit[source] = level[0]++;
    }

    public int[] getOrderOfVisit(int source) throws NotAllNodesReachedException {
        int[] orderPostVisit = new int[graph.getOrder()];
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderTree(source, s, orderPostVisit, new int[]{0});
        for(int i =0; i<s.length; i++)
            if(!s[i])
                throw new NotAllNodesReachedException(i+" not reached");
        return orderPostVisit;
    }

    private void buildOrderTree(int source, boolean[] found, int[] orderPostVisit, int[] level){
        if(source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        orderPostVisit[source] = level[0]++;
        for(Integer neighbor: graph.getNeighbors(source)){
            if(!found[neighbor]){
                buildOrderTree(neighbor, found, orderPostVisit,level);
            }
        }
    }

}
