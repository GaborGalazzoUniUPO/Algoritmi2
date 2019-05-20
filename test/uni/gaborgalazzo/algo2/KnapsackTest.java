package uni.gaborgalazzo.algo2;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnapsackTest {

    @Test
    public void getMaxVal() {
        Knapsack knapsack = new Knapsack( new int[]{3,11,9},new int[]{5,8,3}, 10);
        assertEquals(12, knapsack.getMaxVal());
    }
}