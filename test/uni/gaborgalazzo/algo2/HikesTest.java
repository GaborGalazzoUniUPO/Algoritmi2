package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HikesTest {

    @Test
    public void minDistanceOK() {
        Hikes hikes =
                new Hikes(
                        new UndirectedGraph(
                                "5;0 1 13;0 2 16;0 3 19;0 4 9;1 2 7;1 3 14;1 4 22;2 3 12;2 4 15;3 4 26"));

        assertEquals(12, hikes.minDistance(3));
        assertEquals(13, hikes.minDistance(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void minDistanceNeg() {
        Hikes hikes =
                new Hikes(
                        new UndirectedGraph(
                                "5;0 1 13;0 2 16;0 3 19;0 4 9;1 2 7;1 3 14;1 4 22;2 3 12;2 4 15;3 4 26"));

        hikes.minDistance(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minDistanceNegEdge() {
        Hikes hikes =
                new Hikes(
                        new UndirectedGraph(
                                "5;0 1 13;0 2 -1;0 3 19;0 4 9;1 2 7;1 3 14;1 4 22;2 3 12;2 4 15;3 4 26"));

        assertEquals(12, hikes.minDistance(3));
    }
}