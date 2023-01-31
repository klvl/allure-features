package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class EpicFeatureStorySuiteTest {

    @Test
    public void testEpicFeatureStorySuite() {
        Allure.epic("Epic name");
        Allure.feature("Feature name");
        Allure.story("Story name");
        Allure.suite("Suite name");
    }

}
