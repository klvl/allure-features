package io.klvl.annotations.epic.feature.story;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Some epic name")
@Features({
        @Feature("The first feature name"),
        @Feature("The second feature name"),
})
@Story("Some story name")
public class MultipleFeaturesTest {

    @Test
    public void testMultipleFeatures() {
        // your code here
    }

}
