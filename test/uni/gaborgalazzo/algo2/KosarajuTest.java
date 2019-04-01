package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class KosarajuTest {

    @Test
    public void getSccNode() {
        DirectedGraph graph = new DirectedGraph("1");
        Kosaraju kosaraju = new Kosaraju(graph);
        int[] scc = kosaraju.getScc();
        assertEquals(1, scc.length);
        assertEquals(0, scc[0]);
    }

    @Test
    public void getSccDAG() {
        DirectedGraph graph = new DirectedGraph("4;0 1;0 2;1 3;2 3");
        Kosaraju kosaraju = new Kosaraju(graph);
        int[] scc = kosaraju.getScc();
        assertEquals(4, scc.length);
        assertTrue(scc[0]<scc[1]);
        assertTrue(scc[0]<scc[2]);
        assertTrue(scc[1]<scc[3]);
        assertTrue(scc[2]<scc[3]);
    }

    @Test
    public void getScc() {
        DirectedGraph graph = new DirectedGraph("4;0 1;1 2;2 3;3 0");
        Kosaraju kosaraju = new Kosaraju(graph);
        int[] scc = kosaraju.getScc();
        assertEquals(4, scc.length);
        assertEquals(0,scc[0]);
        assertEquals(0,scc[1]);
        assertEquals(0,scc[2]);
        assertEquals(0,scc[3]);
    }

    @Test
    public void testLemma() {
        DirectedGraph graph = new DirectedGraph("4;0 1;1 2;2 3;3 0");
        DirectedGraph graphT = GraphUtils.reverseGraph(graph);
        Kosaraju kosaraju = new Kosaraju(graph);
        Kosaraju kosarajuT = new Kosaraju(graphT);
        assertTrue(Arrays.equals(kosaraju.getScc(), kosarajuT.getScc()));
    }

    @Test
    public void getScc2() {
        DirectedGraph graph = new DirectedGraph("5;0 2;1 4;1 2;2 0;3 1;4 0;4 3");
        Kosaraju kosaraju = new Kosaraju(graph);
        int[] scc = kosaraju.getScc();
        assertEquals(5, scc.length);
        assertEquals(scc[2],scc[0]);
        assertEquals(scc[3],scc[1]);
        assertEquals(scc[3],scc[4]);
        assertNotEquals(scc[0],scc[4]);
    }

    @Test
    public void getMaxScc() {
        DirectedGraph graph = new DirectedGraph("5;0 2;1 4;1 2;2 0;3 1;4 0;4 3");
        Kosaraju kosaraju = new Kosaraju(graph);
        assertNotEquals(0,kosaraju.getMaxSCC());

    }
}