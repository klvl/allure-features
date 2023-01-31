package io.klvl.annotations;

import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class TmsLinkTest {

    @Test
    @TmsLink("https://atlassian.jira.com/KLVL_123")
    public void testTmsLink() {
        // your code here
    }

    @Test
    @TmsLinks({
            @TmsLink("https://atlassian.jira.com/KLVL_123"),
            @TmsLink("https://atlassian.jira.com/KLVL_124")

    })
    public void testMultipleTmsLinks() {
        // your code here
    }

    @Test
    @TmsLink("KLVL_1234")
    public void testTmsLinkPattern() {
        // your code here
    }

}
