package uni.gaborgalazzo.algo2.my;

import it.uniupo.algoTools.UnionFind;

public class UnionByRank implements UnionFind {

    private int[] clusters;
    private int[] ranks;
    private int setsCount;

    public UnionByRank(int numElements) {
        create(numElements);
    }

    @Override
    public UnionFind create(int i) {
        clusters = new int[i];
        for(int j = 0; j<i; j++)
            clusters[j] = j;
        setsCount = i;
        ranks = new int[i];
        return this;
    }

    @Override
    public void union(int i, int i1) {
        i = find(i);
        i1 = find(i1);
        if(i!=i1){
            if(ranks[i] > ranks[i1]){
                clusters[i1] = i;
            }else {
                clusters[i] = i1;
                if(ranks[i] == ranks[i1])
                    ranks[i] = Math.max(ranks[i], ranks[i1])+1;
            }
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
        if(setsCount<0)
            setsCount = 0;
        return setsCount;
    }
}
