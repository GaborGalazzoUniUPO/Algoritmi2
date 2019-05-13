package uni.gaborgalazzo.algo2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MSITest {

    @Test
    public void test1(){
        // MSI msi = new MSI(new int[]{9,2,7,4,1,8,5,6,3});
        MSI msi = new MSI(new int[]{9,8,7,6,5,4,3,2,1});

        assertEquals(25,msi.getMaxVal());
        assertTrue(msi.getOptSol().containsAll(Arrays.asList(1,3,5,7,9)));

    }

}