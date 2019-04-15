package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class HydraulicConnectionTest {
    HydraulicConnection allId;

    @Test
    public void testCreate() {
        UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
        int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
        int costoTubo = 3;
        int puntoAll = 1;
        allId = new HydraulicConnection(mappa, costoScavo, costoTubo,puntoAll);
        assertNotNull(allId);
    }

    @Test
    public void test01() {
        UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
        int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
        int costoTubo = 3;
        int puntoAll = 1;
        allId = new HydraulicConnection(mappa, costoScavo, costoTubo,puntoAll);
        assertTrue(allId.getMuniProject().hasEdge(0,3));
        assertTrue(!allId.getMuniProject().hasEdge(2,3));
        assertTrue(allId.propProject().hasEdge(2,3));
        assertTrue(!allId.propProject().hasEdge(0,3));
        assertEquals(2,allId.extraMuni());
        assertEquals(3,allId.extraProp(3));
    }

}