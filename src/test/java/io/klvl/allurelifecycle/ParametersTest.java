package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class ParametersTest {

    @Test
    public void testParameters() {
        Allure.parameter("name", "klvl");
        Allure.parameter("age", 25);
        // your code here
    }

}
