package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MST;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimTest {

    @Test
    public void create() {
        UndirectedGraph undirectedGraph = new UndirectedGraph("4;0 1 1; 0 2 3; 0 3 4; 1 2 2; 2 3 5");
        MST mst = new Prim();
        mst.create(undirectedGraph);
    }

    @Test
    public void getMST() {
        UndirectedGraph undirectedGraph = new UndirectedGraph("4;0 1 1; 0 2 3; 0 3 4; 1 2 2; 2 3 5");
        MST mst = new Prim();
        mst.create(undirectedGraph);
        UndirectedGraph tree = mst.getMST();
        assertEquals(undirectedGraph.getOrder(), tree.getOrder());
        assertEquals(undirectedGraph.getOrder()-1, tree.getEdgeNum());
        assertTrue(tree.hasEdge(0, 1));
        assertTrue(tree.hasEdge(0, 3));
        assertTrue(tree.hasEdge(1, 2));
    }

    @Test
    public void getCost() {
        UndirectedGraph undirectedGraph = new UndirectedGraph("4;0 1 1; 0 2 3; 0 3 4; 1 2 2; 2 3 5");
        MST mst = new Prim();
        mst.create(undirectedGraph);
        assertEquals(7, mst.getCost());
    }
}