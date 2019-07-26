package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;
import java.util.Arrays;

public class FloydWarshall {

    private final double[][] dist;
    private final double[][] path;

    public FloydWarshall(DirectedGraph g) {
        this.dist = new double[g.getOrder()][g.getOrder()];

        this.path = new double[g.getOrder()][g.getOrder()];
        for(int i = 0; i<g.getOrder(); i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);

            path[i][i] = i;
            dist[i][i] = 0;

            for(Edge e: g.getOutEdges(i)) {
                dist[i][e.getHead()] = e.getWeight();
                path[i][e.getHead()] = i;

            }
        }
        for(int k = 0; k<g.getOrder(); k++){
            for(int u = 0; u<g.getOrder(); u++)
                for(int v = 0; v<g.getOrder(); v++){
                    double nd = Math.min(dist[u][v], dist[u][k]+dist[k][v]);
                    if(dist[u][v]> dist[u][k]+dist[k][v]){
                        path[u][v] = k;
                    }
                    dist[u][v] = nd;
                }

        }
        return;

    }

    public double getDist(int u, int v){
        return dist[u][v];
    }

    public ArrayList<Integer> getMinimumPath(int u, int v){
        ArrayList<Integer> path = new ArrayList<>();
        int last = (int)this.path[u][v];
        do {
            path.add(last);
            last = (int) this.path[u][last];
        }while (last!=u);
        return path;
    }
}
