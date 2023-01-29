package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AdditionalTestInformationTest {

    @Test
    public void issueTest() {
        Allure.issue("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }

}
