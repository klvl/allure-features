package io.klvl.annotations;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class SeverityTest {

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void testTrivialSeverity() {
        // your code here
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void testMinorSeverity() {
        // your code here
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testNormalSeverity() {
        // your code here
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void testCriticalSeverity() {
        // your code here
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testBlockerSeverity() {
        // your code here
    }

}
