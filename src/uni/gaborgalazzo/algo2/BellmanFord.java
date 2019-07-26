package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;
import uni.gaborgalazzo.algo2.my.Utils;

public class BellmanFord {

    private DirectedGraph g;
    private int source;

    public BellmanFord(DirectedGraph g, int source) {
        this.g = GraphUtils.reverseGraph(g);
        this.source = source;
    }

    public double getDist(int d){

        int n = g.getOrder();
        double[][] L = new double[n+1][n+1];
        for(int i = 0; i<n+1; i++)
            for(int j = 0; j<n+1; j++){
                L[i][j] = Double.POSITIVE_INFINITY;}
        L[0][source] = 0;
        for(int i = 1; i<=n; i++){
            for(int v = 0; v<n; v++ ){
                double min = Double.POSITIVE_INFINITY;
                for(Edge e: g.getOutEdges(v)){
                    double l = L[i-1][e.getHead()] + e.getWeight();
                    if(l<min)
                        min = l;
                }
                L[i][v] = Math.min(L[i-1][v], min);
            }
        }

        if(L[n][n-1]<L[n-1][n-1])
            return Double.NEGATIVE_INFINITY;
        return L[n][n-1];
    }

    public boolean hasNegCycle(){
        return false;
    }
}
