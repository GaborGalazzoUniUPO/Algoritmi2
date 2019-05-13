package uni.gaborgalazzo.algo2;

import org.junit.Test;

import static org.junit.Assert.*;

public class FracKnapsackTest {

    @Test
    public void test1(){
        FracKnapsack fracKnapsack = new FracKnapsack(5,
                new double[]{3,2,4},
                new double[]{2,5,1});
        assertEquals(7,fracKnapsack.maxVal(), 0.001);
        fracKnapsack = new FracKnapsack(1,
                new double[]{3,2,4},
                new double[]{2,5,1});
        assertEquals(2.5,fracKnapsack.maxVal(), 0.001);
    }

    @Test
    public void test2(){
        FracKnapsack fracKnapsack = new FracKnapsack(10,
                new double[]{3,2,4},
                new double[]{2,5,1});
        assertEquals(3,fracKnapsack.dose(0), 0.001);
        assertEquals(2,fracKnapsack.dose(1), 0.001);
        assertEquals(4,fracKnapsack.dose(2), 0.001);
        assertEquals(8, fracKnapsack.maxVal(), 0.001);

        fracKnapsack = new FracKnapsack(3,
                new double[]{70,12,3},
                new double[]{10,2,0.3});
        assertEquals(0,fracKnapsack.dose(0), 0.001);
        assertEquals(3,fracKnapsack.dose(1), 0.001);
        assertEquals(0,fracKnapsack.dose(2), 0.001);
        assertEquals(0.5, fracKnapsack.maxVal(), 0.001);

        fracKnapsack = new FracKnapsack(10,
                new double[]{4,3,4},
                new double[]{5,3,1});
        assertEquals(4,fracKnapsack.dose(0), 0.001);
        assertEquals(3,fracKnapsack.dose(1), 0.001);
        assertEquals(3,fracKnapsack.dose(2), 0.001);
        assertEquals(8.75, fracKnapsack.maxVal(), 0.001);

    }

}