package uni.gaborgalazzo.algo2;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClusteringTest {

    @Test
    public void spacing() {

        Clustering clustering = new Clustering(new UndirectedGraph("3;0 1 2;1 2 5;2 0 7"),2);
        assertEquals(5, clustering.spacing());
    }

    @Test
    public void sameCluster() {
        Clustering clustering = new Clustering(new UndirectedGraph("3;0 1 2;1 2 5;2 0 7"),2);
        assertTrue(clustering.sameCluster(1,0));
        assertTrue(clustering.sameCluster(0,1));
        assertFalse(clustering.sameCluster(1,2));
        assertFalse(clustering.sameCluster(2,1));
        assertFalse(clustering.sameCluster(2,0));
        assertFalse(clustering.sameCluster(0,2));
    }
}