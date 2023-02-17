package io.klvl;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class SkippedTest {

    @Test
    public void testSkippedFirst() {
        throw new SkipException("The first skipped!");
    }

    @Test
    public void testSkippedSecond() {
        throw new SkipException("The second skipped!");
    }

}
