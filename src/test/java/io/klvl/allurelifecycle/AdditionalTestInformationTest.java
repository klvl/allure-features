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

    @Test
    public void testDescription() {
        Allure.description("The purpose of this test is to demonstrate how to add description to a report!");
    }

    @Test
    public void testDescriptionHtml() {
        Allure.descriptionHtml("<div style=\"color: blue\">" +
                "The purpose of this test is to demonstrate how to add HTML description to a report!" +
                "</div>");
    }

}
