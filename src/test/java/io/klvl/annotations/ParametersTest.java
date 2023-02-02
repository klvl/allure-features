package io.klvl.annotations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTest {

    @DataProvider
    public Object[][] provideTestData() {
        return new Object[][] {
                { 1, "klvl" },
                { 2, "vznd" }
        };
    }

    @Test(dataProvider = "provideTestData")
    @Parameters({"id", "userName"})
    public void testParameters(int id, String userName) {
        // your code here
    }

}
