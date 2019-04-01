package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DFS {

    private GraphInterface graph;

    public DFS(GraphInterface graph) {
        this.graph = graph;
    }

    public GraphInterface getTree(int source) throws NotAllNodesReachedException {
        GraphInterface dfsTree = graph.create();
        boolean[] s = new boolean[graph.getOrder()];
        buildTree(source, s, dfsTree);
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return dfsTree;
    }

    public void buildTree(int source, boolean[] found, GraphInterface dfsTree) {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                dfsTree.addEdge(source, neighbor);
                buildTree(neighbor, found, dfsTree);
            }
        }
    }

    public ArrayList<Integer> getNodesInOrderPostVisit(int source) throws NotAllNodesReachedException {
        ArrayList<Integer> orderPostVisit = new ArrayList<>();
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderPostTree(source, s, orderPostVisit);
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return orderPostVisit;
    }

    private void buildOrderPostTree(int source, boolean[] found, ArrayList<Integer> orderPostVisit) {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                buildOrderPostTree(neighbor, found, orderPostVisit);
            }
        }
        orderPostVisit.add(source);
    }

    public GraphInterface getForest() throws NotAllNodesReachedException {
        GraphInterface dfsForest = graph.create();
        boolean[] s = new boolean[graph.getOrder()];
        for (int i = 0; i < graph.getOrder(); i++) {
            if (!s[i])
                buildTree(i, s, dfsForest);
        }
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return dfsForest;
    }


    public ArrayList<Integer> getNodesInOrderOfVisit(int source) throws NotAllNodesReachedException {
        ArrayList<Integer> orderVisit = new ArrayList<>();
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderTree(source, s, orderVisit);
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return orderVisit;
    }

    private void buildOrderTree(int source, boolean[] found, ArrayList<Integer> orderVisit) {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        orderVisit.add(source);
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                buildOrderTree(neighbor, found, orderVisit);
            }
        }
    }

    public int[] getOrderPostVisit(int source) throws NotAllNodesReachedException {
        int[] orderPostVisit = new int[graph.getOrder()];
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderPostTree(source, s, orderPostVisit, new int[]{0});
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return orderPostVisit;
    }

    private void buildOrderPostTree(int source, boolean[] found, int[] orderPostVisit, int[] level) {
        if (source < 0 || source >= found.length)
            throw new IllegalArgumentException();
        found[source] = true;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                buildOrderPostTree(neighbor, found, orderPostVisit, level);
            }
        }
        orderPostVisit[source] = level[0]++;
    }

    public int[] getOrderOfVisit(int source) throws NotAllNodesReachedException {
        int[] orderPostVisit = new int[graph.getOrder()];
        boolean[] s = new boolean[graph.getOrder()];
        buildOrderTree(source, s, orderPostVisit, new int[]{0});
        for (int i = 0; i < s.length; i++)
            if (!s[i])
                throw new NotAllNodesReachedException(i + " not reached");
        return orderPostVisit;
    }

    private void buildOrderTree(int source, boolean[] found, int[] orderPostVisit, int[] level) {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        orderPostVisit[source] = level[0]++;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                buildOrderTree(neighbor, found, orderPostVisit, level);
            }
        }
    }

    public boolean hasDirCycle() {
        return getDirCycle() != null;

    }

    public ArrayList<Integer> getDirCycle() {
        if (!(graph instanceof DirectedGraph))
            return null;
        boolean[] s = new boolean[graph.getOrder()];
        boolean[] e = new boolean[graph.getOrder()];
        int[] p = new int[graph.getOrder()];
        Arrays.fill(p, -1);
        try {
            for (int i = 0; i < graph.getOrder(); i++)
                if (!s[i])
                    buildDirCycleTree(i, s, e, p);
            return null;
        } catch (CycleFoundException e1) {
            ArrayList<Integer> cycle = new ArrayList<>();
            for (int i = 0; i < p.length; i++)
                cycle.add(p[i]);
            return cycle;
        }

    }

    private void buildDirCycleTree(int source, boolean[] found, boolean[] ended, int[] parent) throws CycleFoundException {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for (Integer neighbor : graph.getNeighbors(source)) {
            parent[neighbor] = source;
            if (!found[neighbor]) {
                buildDirCycleTree(neighbor, found, ended, parent);
            } else if (!ended[neighbor]) {
                throw new CycleFoundException();
            }
        }
        ended[source] = true;

    }

    public boolean isConnected() {
        try {
            getTree(0);
            return true;
        } catch (NotAllNodesReachedException e) {
            return false;
        }
    }

    public int[] connectedComponents() {

        boolean[] s = new boolean[graph.getOrder()];
        int[] p = new int[graph.getOrder()];
        int[] ccA = new int[graph.getOrder()];
        Arrays.fill(ccA, -1);
        int cc = 0;
        for (int i = 0; i < graph.getOrder(); i++)
            if (!s[i]) {
                buildCCArray(i, s, p, cc++, ccA);
            }

        return ccA;
    }

    private void buildCCArray(int source, boolean[] found, int[] parent, int cc, int[] ccA) {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        ccA[source] = cc;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                parent[neighbor] = source;
                buildCCArray(neighbor, found, parent, cc, ccA);
            }
        }
    }

    public boolean hasUndirectedCycle() {
        return getUndirCycle() != null;

    }

    public ArrayList<Integer> getUndirCycle() {
        if (!(graph instanceof UndirectedGraph))
            return null;
        boolean[] s = new boolean[graph.getOrder()];
        boolean[] e = new boolean[graph.getOrder()];
        int[] p = new int[graph.getOrder()];
        Arrays.fill(p, -1);
        try {
            for (int i = 0; i < graph.getOrder(); i++)
                if (!s[i])
                    buildUndirCycleTree(i, s, e, p);
            return null;
        } catch (CycleFoundException e1) {
            ArrayList<Integer> cycle = new ArrayList<>();
            for (int i = 0; i < p.length; i++)
                cycle.add(p[i]);
            return cycle;
        }

    }

    private void buildUndirCycleTree(int source, boolean[] found, boolean[] ended, int[] parent) throws CycleFoundException {
        if (source < 0 || source >= found.length || found[source])
            throw new IllegalArgumentException();
        found[source] = true;
        for (Integer neighbor : graph.getNeighbors(source)) {
            if (!found[neighbor]) {
                parent[neighbor] = source;
                buildUndirCycleTree(neighbor, found, ended, parent);
            } else if (!ended[neighbor] && parent[source] != neighbor) {
                parent[neighbor] = source;
                throw new CycleFoundException();
            }
        }
        ended[source] = true;

    }


    public int[] getTopologicalOrder() {

        if (!isDag())
            throw new IllegalArgumentException();

        int[] orderPostVisit = new int[graph.getOrder()];
        boolean[] s = new boolean[graph.getOrder()];
        int[] order = new int[]{0};
        for (int i = 0; i < graph.getOrder(); i++)
            if (!s[i])
                buildOrderPostTree(i, s, orderPostVisit, order);
        for (int i = 0; i < orderPostVisit.length / 2; i++) {
            int temp = orderPostVisit[i];
            orderPostVisit[i] = orderPostVisit[orderPostVisit.length - 1 - i];
            orderPostVisit[orderPostVisit.length - 1 - i] = temp;
        }
        return orderPostVisit;

    }

    public ArrayList<Integer> postVisitList() {
        int[] orderPostVisit = new int[graph.getOrder()];
        Arrays.fill(orderPostVisit, -1);
        boolean[] s = new boolean[graph.getOrder()];
        int[] order = new int[]{0};
        for (int i = 0; i < graph.getOrder(); i++)
            if (!s[i])
                buildOrderPostTree(i, s, orderPostVisit, order);
        ArrayList<Integer> result = new ArrayList<>();
        int[] r = new int[graph.getOrder()];
        for(int i = 0; i < orderPostVisit.length; i++){
            //result.add(orderPostVisit[i], i);
            r[orderPostVisit[i]] = i;
        }
        for(int i = 0; i < r.length; i++){
            result.add(r[i]);
        }
        return result;
    }

    private boolean isDag() {
        return !hasDirCycle();
    }

}
