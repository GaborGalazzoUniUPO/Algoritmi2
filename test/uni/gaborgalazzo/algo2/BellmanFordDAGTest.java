package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class BellmanFordDAGTest {

    @Test
    public void getDist() {
        DirectedGraph g = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");

        BellmanFordDAG bellmanFord = new BellmanFordDAG(g, 0);
        assertEquals(1, bellmanFord.getDist(2), 0.001);
    }
}