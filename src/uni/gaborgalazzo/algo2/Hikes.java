package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Hikes {

    private UndirectedGraph shelters;

    public Hikes(UndirectedGraph shelters){
        this.shelters = shelters;
    }

    public int minDistance(int numZones){
        if(numZones < 1)
            throw new IllegalArgumentException("Invalid numZones: "+numZones);

        UnionFind unionFind = new UnionByRank(shelters.getOrder());
        MinHeap<Edge, Integer> mHeap = new MinHeap<>();

        for(int i = 0; i<shelters.getOrder(); i++)
            for(Edge e: shelters.getOutEdges(i))
                mHeap.add(e, e.getWeight());

        while (!mHeap.isEmpty()){
            Edge e = mHeap.extractMin();
            if(e.getWeight()<0)
                throw new IllegalArgumentException("Negative Edge found: "+e);
            if(unionFind.find(e.getHead()) != unionFind.find(e.getTail()))
                if(unionFind.getNumberOfSets() > numZones)
                    unionFind.union(unionFind.find(e.getHead()), unionFind.find(e.getTail()));
                else {
                    return e.getWeight();
                }
        }
        throw new RuntimeException();
    }
}
