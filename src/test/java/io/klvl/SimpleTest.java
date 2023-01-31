package io.klvl;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void testSimple() {
        doFirstTestStep();
        doSecondTestStep();
    }

    @Step
    public void doFirstTestStep() {
        // your code here
    }

    @Step
    public void doSecondTestStep() {
        // your code here
    }

    @Test(description = "As a user I see descriptive test name in a report")
    public void testDescriptiveTestName() {
        // your code here
    }

}
