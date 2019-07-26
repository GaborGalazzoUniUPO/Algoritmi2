package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class BellmanFordTest {

    @Test
    public void getDist() {
        DirectedGraph g = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");

        BellmanFord bellmanFord = new BellmanFord(g, 0);
        assertEquals(1, bellmanFord.getDist(2), 0.001);
    }
}