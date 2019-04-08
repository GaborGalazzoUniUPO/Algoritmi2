package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DijkstraTest {


    @Test
    public void getDistances() {
        GraphInterface g = new UndirectedGraph("8;0 2 1;0 3 8;1 3 4;1 5 9;2 6 3;3 7 1;4 7 3;4 6 5;5 7 1;6 7 4");

        Dijkstra testDijkstra = new Dijkstra(g);
        int[] distances = testDijkstra.getDistances(0);
        assertTrue(Arrays.equals(distances, new int[]{0,12,1,8,9,9,4,8}));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDijkstraNegSorg()
    {
        GraphInterface g = new UndirectedGraph("8;0 2 1;0 3 8;1 3 4;1 5 9;2 6 3;3 7 1;4 7 3;4 6 5;5 7 1;6 7 4");

        Dijkstra testDijkstra = new Dijkstra(g);
        testDijkstra.getDistances(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegW()
    {
        GraphInterface g = new UndirectedGraph("8;0 2 1;0 3 8;1 3 4;1 5 9;2 6 3;3 7 1;4 7 3;4 6 5;5 7 1;6 7 -1");

        Dijkstra testDijkstra = new Dijkstra(g);
    }

    @Test
    public void getMinimumPathsTree() {
        GraphInterface g = new UndirectedGraph("8;0 2 1;0 3 8;1 3 4;1 5 9;2 6 3;3 7 1;4 7 3;4 6 5;5 7 1;6 7 4");

        Dijkstra testDijkstra = new Dijkstra(g);
        GraphInterface tree = testDijkstra.getMinimumPathsTree(0);
        assertTrue(tree.hasEdge(0,2));
        assertTrue(tree.hasEdge(2,6));
        assertTrue(tree.hasEdge(6,7));
        assertTrue(tree.hasEdge(6,4));
        assertTrue(tree.hasEdge(7,5));
        assertTrue(tree.hasEdge(0,3));
        assertTrue(tree.hasEdge(3,1));
        assertFalse(tree.hasEdge(1,5));
        assertFalse(tree.hasEdge(3,7));
        assertFalse(tree.hasEdge(4,7));
    }

    @Test
    public void getMinimumPath() {
        GraphInterface g = new UndirectedGraph("8;0 2 1;0 3 8;1 3 4;1 5 9;2 6 3;3 7 1;4 7 3;4 6 5;5 7 1;6 7 4");
        Dijkstra testDijkstra = new Dijkstra(g);
        ArrayList<Edge> minimumPath = testDijkstra.getMinimumPath(0, 5);
        assertEquals(4, minimumPath.size());
        assertTrue(minimumPath.contains(new Edge(0,2)));
        assertTrue(minimumPath.contains(new Edge(2,6)));
        assertTrue(minimumPath.contains(new Edge(6,7)));
        assertTrue(minimumPath.contains(new Edge(7,5)));
    }
}