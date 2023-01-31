package io.klvl.annotations.epic.feature.story.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Authorization")
@Feature("Sign in")
@Story("Sign in with credentials")
public class SignInWithCredentialsTest {

    @Test
    public void testSignInWithValidCredentials() {
        // your code here
    }

    @Test
    public void testSignInWithAlreadyExistingCredentials() {
        // your code here
    }

    @Test
    public void testSignInWithInvalidLogin() {
        // your code here
    }

    @Test
    public void testSignInWithInvalidPassword() {
        // your code here
    }

}
