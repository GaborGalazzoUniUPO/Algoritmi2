package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MST;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MSTTest {

    MST mst = new Kruskal();

    @Test
    public void createTest(){
        UndirectedGraph g = new UndirectedGraph("2\n1 0 2");
        MST testMst = mst.create(g);
        assertNotNull(testMst);
    }

    @Test
    public void test(){
        UndirectedGraph g = new UndirectedGraph("2\n1 0 2");
        MST testMst = mst.create(g);
        UndirectedGraph t = testMst.getMST();
        assertTrue(t.hasEdge(1,0));
        assertEquals(2, testMst.getCost());
    }
}
