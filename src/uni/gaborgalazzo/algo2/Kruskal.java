package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MST;
import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Kruskal implements MST {

    private UndirectedGraph mst;
    private int cost;


    @Override
    public MST create(UndirectedGraph undirectedGraph) {
        mst = (UndirectedGraph) undirectedGraph.create();
        MinHeap<Edge, Integer> minHeap = new MinHeap<>();
        //POPOLO MinHeap
        for(int n = 0; n< undirectedGraph.getOrder(); n++){
            for(Edge e: undirectedGraph.getOutEdges(n))
                minHeap.add(e, e.getWeight());
        }
        UnionFind unionFind = new UnionByRank(undirectedGraph.getOrder());
        while (!minHeap.isEmpty()){
            Edge e = minHeap.extractMin();
            if(unionFind.find(e.getHead()) != unionFind.find(e.getTail())) {
                unionFind.union(unionFind.find(e.getHead()), unionFind.find(e.getTail()));
                mst.addEdge(e);
                cost += e.getWeight();
            }
        }
        return this;
    }

    @Override
    public UndirectedGraph getMST() {
        return mst;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
