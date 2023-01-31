package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class TmsLinkTest {

    @Test
    public void testTmsLink() {
        Allure.tms("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }

}
