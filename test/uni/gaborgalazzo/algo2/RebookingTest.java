package uni.gaborgalazzo.algo2;

import org.junit.Test;

import static org.junit.Assert.*;

public class RebookingTest {

    @Test
    public void maxSaving() {

        Rebooking rebooking = new Rebooking(10);
        assertEquals(12, rebooking.maxSaving(new int[]{5,8,3}, new int[]{3,11,9}));
        assertEquals(-1, rebooking.maxSaving(new int[]{5,8}, new int[]{3,11,9}));

        rebooking = new Rebooking(-4);
        assertEquals(-1, rebooking.maxSaving(new int[]{5,8,3}, new int[]{3,11,9}));

        rebooking = new Rebooking(0);
        assertEquals(0, rebooking.maxSaving(new int[]{5,8,3}, new int[]{3,11,9}));

        rebooking = new Rebooking(20);
        assertEquals(23, rebooking.maxSaving(new int[]{5,8,3}, new int[]{3,11,9}));
    }
}