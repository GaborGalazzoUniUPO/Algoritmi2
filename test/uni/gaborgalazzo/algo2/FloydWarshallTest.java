package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class FloydWarshallTest {

    @Test
    public void getDist() {
        FloydWarshall floydWarshall = new FloydWarshall(new DirectedGraph("3;0 1 3;1 2 -2;0 2 2"));

        assertEquals(1, floydWarshall.getDist(0,2), 0.01);
        assertEquals(3, floydWarshall.getDist(0,1), 0.01);
        assertEquals(-2, floydWarshall.getDist(1,2), 0.01);
        assertEquals(Double.POSITIVE_INFINITY, floydWarshall.getDist(2,1), 0.01);

    }

    @Test
    public void getMinimumPath() {

        FloydWarshall floydWarshall = new FloydWarshall(new DirectedGraph("3;0 1 3;1 2 -2;0 2 2"));

        assertTrue(floydWarshall.getMinimumPath(0,2).contains(1));
    }
}