package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlightsTest {

    @Test
    public void time() {
        DirectedGraph directedGraph = new DirectedGraph("8;1 0 9;1 2 5;1 3 5;1 6 9;3 5 7;4 2 9;5 3 7;5 1 8;5 6 7;2 5 1;6 5 8;5 7 6");
        Flights flights = new Flights(directedGraph);
        assertEquals(-1, flights.time(0,1));
        assertEquals(12,flights.time(1,7));
    }

    @Test
    public void numAirports() {
        DirectedGraph directedGraph = new DirectedGraph("8;1 0 9;1 2 5;1 3 5;1 6 9;3 5 7;4 2 9;5 3 7;5 1 8;5 6 7;2 5 1;6 5 8;5 7 6");
        Flights flights = new Flights(directedGraph);
        assertEquals(-1 , flights.numAirports(0,4));
        assertEquals(3, flights.numAirports(1,7));
    }

    @Test
    public void minimumTime() {
        DirectedGraph directedGraph = new DirectedGraph("8;1 0 9;1 2 5;1 3 5;1 6 9;3 5 7;4 2 9;5 3 7;5 1 8;5 6 7;2 5 1;6 5 8;5 7 6");
        Flights flights = new Flights(directedGraph);
        assertEquals(12,flights.minimumTime(1,7));
    }

    @Test
    public void fastestPath() {
        DirectedGraph directedGraph = new DirectedGraph("8;1 0 9;1 2 5;1 3 5;1 6 9;3 5 7;4 2 9;5 3 7;5 1 8;5 6 7;2 5 1;6 5 8;5 7 6");
        Flights flights = new Flights(directedGraph);
        ArrayList<Edge> path = flights.fastestPath(6,0);
        assertEquals(3, path.size());
        assertTrue(path.contains(new Edge(6,5)));
        assertTrue(path.contains(new Edge(5,1)));
        assertTrue(path.contains(new Edge(1,0)));
    }

    @Test
    public void airports() {
        DirectedGraph directedGraph = new DirectedGraph("8;1 0 9;1 2 5;1 3 5;1 6 9;3 5 7;4 2 9;5 3 7;5 1 8;5 6 7;2 5 1;6 5 8;5 7 6");
        Flights flights = new Flights(directedGraph);
        ArrayList<Integer> path = flights.airports(6,0);
        assertEquals(4, path.size());
        assertTrue(path.contains(0));
        assertTrue(path.contains(5));
        assertTrue(path.contains(1));
        assertTrue(path.contains(6));
    }
}