package io.klvl.breakdown;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

public class StepAsLambdaTest {

    @Test
    public void testStepAsLambda() {
        step("Sign in", () -> {
            openLoginPage();
            typeEmail();
            typePassword();
            clickLoginButton();
        });

        step("Buy product", () -> {
            addProductToBasket();
            purchaseProduct();
        });
    }

    @Step
    public void openLoginPage() {
        // your code here
    }

    @Step
    public void typeEmail() {
        // your code here
    }

    @Step
    public void typePassword() {
        // your code here
    }

    @Step
    public void clickLoginButton() {
        // your code here
    }

    @Step
    public void addProductToBasket() {
        // your code here
    }

    @Step
    public void purchaseProduct() {
        // your code here
    }

}
