package io.klvl.descriptive.breakdown;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Some epic name")
@Feature("Some feature name")
@Stories({
        @Story("The first story name"),
        @Story("The second story name"),
})
public class MultipleStoriesTest {

    @Test
    public void testMultipleStories() {
        // your code here
    }

}
