package io.klvl.annotations;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class StepTest {

    @Test(description = "As allure user I can set descriptive name for my test")
    public void testDescriptiveName() {
        firstStep();
        secondStep();
    }

    @Step("Perform the first step")
    public void firstStep() {
        // you code here
    }

    @Step("Perform the second step")
    public void secondStep() {
        // you code here
    }

    @Test
    public void testParametrizedSepName() {
        stepParametrizedByName("email@example.com", "qwerty123456");
        stepParametrizedByIndex("email@example.com", "qwerty123456");
    }

    @Step("This is email: {email} parameter, and this is password: {password}")
    public void stepParametrizedByName(String email, String password) {
        // your code here
    }


    @Step("This is email: {0}, and this is password: {1}")
    public void stepParametrizedByIndex(String email, String password) {
        // your code here
    }

}
