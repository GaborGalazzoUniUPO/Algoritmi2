package uni.gaborgalazzo.algo2;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class KnapsackTest {

    @Test
    public void getMaxVal() {
        Knapsack knapsack = new Knapsack( new int[]{3,11,9},new int[]{5,8,3}, 10);
        assertEquals(12, knapsack.getMaxVal());
        ArrayList<Integer> sol = knapsack.getOptSol();


        assertTrue(sol.contains(0));
        assertFalse(sol.contains(1));
        assertTrue(sol.contains(2));
    }
}