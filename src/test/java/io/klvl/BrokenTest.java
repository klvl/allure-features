package io.klvl;

import org.testng.annotations.Test;

public class BrokenTest {

    @Test
    public void testBroken() {
        String nullable = null;
        boolean isEmpty = nullable.isEmpty();
    }

}
