package io.klvl.annotations;

import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import org.testng.annotations.Test;

public class IssueTest {

    @Test(enabled = false)
    @Issue("https://atlassian.jira.com/issues/KLVL_123")
    public void testIssue() {
        // your code here
    }

    @Test(enabled = false)
    @Issues({
            @Issue("https://atlassian.jira.com/issues/KLVL_123"),
            @Issue("https://atlassian.jira.com/issues/KLVL_124")

    })
    public void testMultipleIssues() {
        // your code here
    }

    @Test(enabled = false)
    @Issue("KLVL_1234")
    public void testIssuePattern() {
        // your code here
    }

}
