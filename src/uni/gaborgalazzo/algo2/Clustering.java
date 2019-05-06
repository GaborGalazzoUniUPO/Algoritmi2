package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Clustering {

    private UnionFind cluster;
    private Integer spacing = null;

    public Clustering(UndirectedGraph graph, int numCluster){
        cluster = new UnionByRank(graph.getOrder());
        MinHeap<Edge, Integer> minHeap = new MinHeap<>();
        //POPOLO MinHeap
        for(int n = 0; n< graph.getOrder(); n++){
            for(Edge e: graph.getOutEdges(n))
                minHeap.add(e, e.getWeight());
        }

        while (!minHeap.isEmpty()){
            Edge e = minHeap.extractMin();
            if(cluster.find(e.getHead()) != cluster.find(e.getTail()))
                if(cluster.getNumberOfSets() > numCluster)
                    cluster.union(cluster.find(e.getTail()), cluster.find(e.getHead()));
                else
                {
                    spacing = e.getWeight();
                    break;
                }
        }


    }

    public int spacing(){

        return spacing;
    }

    public boolean sameCluster(int a, int b){
        return cluster.find(a) == cluster.find(b);
    }

}
