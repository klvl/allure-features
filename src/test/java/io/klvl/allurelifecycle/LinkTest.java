package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class LinkTest {

    @Test
    public void testLink() {
        Allure.link("https://google.com");
        Allure.link("google", "https://google.com");
    }

}
