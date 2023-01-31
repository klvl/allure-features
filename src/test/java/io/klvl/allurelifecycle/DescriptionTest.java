package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class DescriptionTest {

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
