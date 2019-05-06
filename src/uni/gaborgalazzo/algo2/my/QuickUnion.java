package uni.gaborgalazzo.algo2.my;

import it.uniupo.algoTools.UnionFind;

public class QuickUnion implements UnionFind {

    private int[] clusters;
    private int setsCount;

    public QuickUnion(int numElements) {
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
        if(clusters[find(i)] != clusters[find(i1)]) {
            clusters[find(i)] = clusters[find(i1)];
            setsCount--;
        }

    }

    @Override
    public int find(int i) {
        while (clusters[i] != i){
            i = clusters[i];
        }
        return i;
    }

    @Override
    public int getNumberOfSets() {
        return setsCount;
    }
}
