package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MST;
import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;
import it.uniupo.graphLib.UndirectedGraph;

public class Prim implements MST {

    private UndirectedGraph mst;
    private int cost;

    @Override
    public MST create(UndirectedGraph undirectedGraph) {
        mst = (UndirectedGraph) undirectedGraph.create();
        boolean[] found = new boolean[undirectedGraph.getOrder()];
        found[0] = true;
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        for(Edge e: undirectedGraph.getOutEdges(0)){
            heap.add(e, e.getWeight());
        }
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!found[e.getHead()]){
                found[e.getHead()] = true;
                mst.addEdge(e);
                cost+=e.getWeight();
                for(Edge n: undirectedGraph.getOutEdges(e.getHead()))
                    if(!found[n.getHead()])
                        heap.add(n, n.getWeight());
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
