package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class TestBreakdownTest {

    @Test
    public void testAllureLifecycleBreakdown() {
        Allure.epic("Epic name");
        Allure.feature("Feature name");
        Allure.story("Story name");
        Allure.suite("Suite name");
    }

}
