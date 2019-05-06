package uni.gaborgalazzo.algo2.my;

import it.uniupo.algoTools.UnionFind;

public class QuickFind implements UnionFind {

    private int[] clusters;
    private int setsCount;

    public QuickFind(int numElements) {
        create(numElements);
    }

    @Override
    public UnionFind create(int i) {
        clusters = new int[i];
        for(int j = 0; j<i; j++)
            clusters[j] = j;
        setsCount = i;
        return this;
    }

    @Override
    public void union(int i, int i1) {
        int b = clusters[i1];
        int a = clusters[i];
        for(int j = 0; j<clusters.length; j++){
            if(clusters[j] == b)
                clusters[j] = a;
        }
        if(b!=a)
            setsCount--;

    }

    @Override
    public int find(int i) {
        return clusters[i];
    }

    @Override
    public int getNumberOfSets() {
        return setsCount;
    }
}
