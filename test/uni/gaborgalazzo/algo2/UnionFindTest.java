package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.UnionFind;
import org.junit.Test;
import uni.gaborgalazzo.algo2.my.QuickFind;
import uni.gaborgalazzo.algo2.my.QuickUnion;
import uni.gaborgalazzo.algo2.my.UnionByRank;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UnionFindTest {

    UnionFind unionFind = new UnionByRank(10);

    @Test
    public void test1(){
        for(int i = 0; i<9; i++)
            assertNotEquals(unionFind.find(i), unionFind.find(i+1));
    }

    @Test
    public void test2(){
        unionFind.union(1,2);
        assertEquals(unionFind.find(1),unionFind.find(2));
        assertEquals(9, unionFind.getNumberOfSets());

        unionFind.union(3,4);
        assertEquals(unionFind.find(3),unionFind.find(4));
        assertEquals(8, unionFind.getNumberOfSets());

        assertNotEquals(unionFind.find(3), unionFind.find(1));
        assertNotEquals(unionFind.find(4), unionFind.find(2));

        unionFind.union(1,4);
        assertEquals(unionFind.find(3), unionFind.find(1));
        assertEquals(unionFind.find(1), unionFind.find(2));
        assertEquals(unionFind.find(4), unionFind.find(2));
        assertEquals(unionFind.find(3), unionFind.find(2));
        assertEquals(7, unionFind.getNumberOfSets());
    }
}
