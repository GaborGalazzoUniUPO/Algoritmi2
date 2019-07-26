package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.GraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BellmanFordDAG {


    private double[] L;

    public BellmanFordDAG(DirectedGraph g, int source) {

        this.L = new double[g.getOrder()+1];
        for(int i = 0; i<L.length; i++)
                L[i] = Double.POSITIVE_INFINITY;
        L[source] = 0;


        boolean[] found = new boolean[g.getOrder()];
        int[] order = new int[g.getOrder()];

        tOrder(g, found, order, new int[]{0}, source);

        g = GraphUtils.reverseGraph(g);
        for(int v = order.length-2; v>=0; v--){
            double min = Double.POSITIVE_INFINITY;
            for(Edge e: g.getOutEdges(order[v])){
                double m = L[e.getHead()] + e.getWeight();
                if(m<min)
                    min = m;
            }
            L[order[v]] = min;
        }


    }

    private void tOrder(
            GraphInterface g,
            boolean[] found,
            int[] order,
            int[] level,
            int source) {
        if (!found[source]) {
            found[source] = true;
            for (Edge e : g.getOutEdges(source))
                if (!found[e.getHead()])
                    tOrder(g, found, order, level, e.getHead());
            order[source] = level[0]++;
        }


    }

    public double getDist(int u){
     return L[u];
    }

    public ArrayList<Integer> getPath (int u){
        return null;
    }
}
