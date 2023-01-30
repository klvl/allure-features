package io.klvl.testinfo;

import io.qameta.allure.Link;
import io.qameta.allure.Links;
import org.testng.annotations.Test;

public class LinkTest {

    @Test
    @Link("https://www.google.com")
    public void testDefaultLinkUsage() {
        // your code here
    }

    @Test
    @Link(name = "my-link", url = "https://www.googgle.com")
    public void testLinkAsText() {
        // your code here
    }

    @Test
    @Links({
            @Link("https://www.google.com"),
            @Link("https://www.yahoo.com")
    })
    public void testMultipleLinks() {

    }

}
