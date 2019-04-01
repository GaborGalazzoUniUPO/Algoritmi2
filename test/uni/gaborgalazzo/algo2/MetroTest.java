package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MetroTest {

    @Test
    public void getStops() {
        UndirectedGraph map = new UndirectedGraph("7;0 1;1 2;2 3;3 4;2 5;2 6");
        Metro m = new Metro(map);
        ArrayList<Integer> stops = m.getStops(6,3);
        assertEquals(2, stops.size());
        assertEquals(2, (int)stops.get(0));
        assertEquals(3, (int)stops.get(1));
    }

    @Test
    public void getStopsNull() {
        UndirectedGraph map = new UndirectedGraph("7;0 1;1 2;2 3;2 5;2 6");
        Metro m = new Metro(map);
        ArrayList<Integer> stops = m.getStops(6,4);
        assertNull(stops);
    }
}