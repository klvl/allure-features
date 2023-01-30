package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AdditionalTestInformationTest {

    @Test
    public void testIssue() {
        Allure.issue("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }

    @Test
    public void testLink() {
        Allure.link("https://google.com");
        Allure.link("google", "https://google.com");
    }

}
