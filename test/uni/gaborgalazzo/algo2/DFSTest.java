package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DFSTest {

    @Test
    public void testCreate() {
        GraphInterface graph = new DirectedGraph(3);
        DFS dfsTest = new DFS(graph);
        assertNotNull(dfsTest);
    }

    @Test
    public void testFound() throws NotAllNodesReachedException {
        GraphInterface g = new DirectedGraph("3;0 1;1 2;2 0");
        DFS dfs = new DFS(g);
        GraphInterface dfsTree = dfs.getTree(0);
        assertNotNull(dfsTree);
    }

    @Test
    public void testNumEdges() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        GraphInterface dfsTree = dfs.getTree(0);
        assertEquals(g.getEdgeNum() - 1, dfsTree.getEdgeNum());
    }


    @Test
    public void testEdges() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        GraphInterface dfsTree = dfs.getTree(2);
        assertTrue(
                dfsTree.hasEdge(2, 0)
                        && dfsTree.hasEdge(0, 1)
                        && dfsTree.hasEdge(1, 3)
                        || dfsTree.hasEdge(2, 3)
                        && dfsTree.hasEdge(3, 1)
                        && dfsTree.hasEdge(1, 0)
        );
        assertFalse(
                dfsTree.hasEdge(2, 3)
                        && dfsTree.hasEdge(2, 0)
                        && dfsTree.hasEdge(0, 1)
                        || dfsTree.hasEdge(2, 0)
                        && dfsTree.hasEdge(3, 2)
                        && dfsTree.hasEdge(1, 3)
        );
    }

    @Test
    public void testInitTree() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        GraphInterface dfsTree1 = dfs.getTree(2);
        GraphInterface dfsTree2 = dfs.getTree(3);
        assertEquals(dfsTree1.getOrder(), dfsTree2.getOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException1() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        dfs.getTree(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException2() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        dfs.getTree(100);
    }


    @Test(expected = NotAllNodesReachedException.class)
    public void testNotAllNodesReachedException() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("5;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        dfs.getTree(0);
    }

    @Test
    public void testOrderPostVisit() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        ArrayList<Integer> orderPostVisit = dfs.getNodesInOrderPostVisit(0);
        assertEquals(1, (int) orderPostVisit.get(0));
        assertEquals(3, (int) orderPostVisit.get(1));
        assertEquals(2, (int) orderPostVisit.get(2));
        assertEquals(0, (int) orderPostVisit.get(3));
    }

    @Test()
    public void testGetForest() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("5;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        GraphInterface forest = dfs.getForest();
        assertEquals(g.getOrder(), forest.getOrder());
    }

    @Test()
    public void testGetForestDir() throws NotAllNodesReachedException {
        GraphInterface g = new DirectedGraph("5;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        GraphInterface forest = dfs.getForest();
        assertEquals(g.getOrder(), forest.getOrder());
    }


    @Test
    public void testOrderVisit() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        ArrayList<Integer> orderVisit = dfs.getNodesInOrderOfVisit(0);
        assertEquals(0, (int) orderVisit.get(0));
        assertEquals(1, (int) orderVisit.get(3));
        assertEquals(2, (int) orderVisit.get(1));
        assertEquals(3, (int) orderVisit.get(2));
    }

    @Test
    public void testOrderLength() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        assertEquals(dfs.getNodesInOrderOfVisit(0).size(), dfs.getNodesInOrderOfVisit(3).size());
    }

    @Test
    public void testOrderContents() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(2).get(1),1);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(2).get(0),0);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(2).get(2),2);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(1).get(1),1);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(1).get(0),0);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(0).get(3), 3);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(0).get(2), 2);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(3).get(3), 3);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(3).get(2), 2);
        assertNotEquals((int)dfs.getNodesInOrderOfVisit(3).get(0), 0);
    }


    @Test
    public void testOrderPostVisitArray() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        int[] orderPostVisit = dfs.getOrderPostVisit(0);
        assertEquals(0, orderPostVisit[1]);
        assertEquals(1, orderPostVisit[3]);
        assertEquals(2, orderPostVisit[2]);
        assertEquals(3, orderPostVisit[0]);
    }

    @Test
    public void testOrderVisitArray() throws NotAllNodesReachedException {
        GraphInterface g = new UndirectedGraph("4;0 2;0 1;1 3;2 3");
        DFS dfs = new DFS(g);
        int[] orderPostVisit = dfs.getOrderOfVisit(0);
        assertEquals(0, orderPostVisit[0]);
        assertEquals(3, orderPostVisit[1]);
        assertEquals(1, orderPostVisit[2]);
        assertEquals(2, orderPostVisit[3]);

    }
}