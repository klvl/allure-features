package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class IssueTest {

    @Test
    public void testIssue() {
        Allure.issue("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }

}
