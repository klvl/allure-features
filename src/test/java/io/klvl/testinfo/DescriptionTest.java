package io.klvl.testinfo;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DescriptionTest {

    @Test
    @Description("The purpose of this test is to demonstrate how to add description to a report")
    public void testDescriptionDefaultUsage() {
        // your code here
    }

    @Test
    @Description("""
            The purpose of this test is to demonstrate how to add
            multi-paragraph description to
            allure report
            """)
    public void testDescriptionMultiParagraph() {
        // your code here
    }

}
