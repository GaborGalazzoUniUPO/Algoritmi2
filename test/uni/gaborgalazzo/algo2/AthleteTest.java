package uni.gaborgalazzo.algo2;

import org.junit.Test;

import static org.junit.Assert.*;

public class AthleteTest {

    @Test
    public void choice() {

        Athlete athlete = new Athlete(5);

        assertEquals(2,athlete.choice(new int[]{8,4,2,6,3}, new int[]{3,10,7,7,4}));
        assertEquals(0,athlete.choice(new int[]{8,4,2,6,3}, new int[]{3,10,7,4,4}));

    }
}