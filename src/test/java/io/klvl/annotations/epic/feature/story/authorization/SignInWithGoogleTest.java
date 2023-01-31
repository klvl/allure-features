package io.klvl.annotations.epic.feature.story.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Authorization")
@Feature("Sign in")
@Story("Sign in with google")
public class SignInWithGoogleTest {

    @Test
    public void testValidSignInWithGoogle() {
        // your code here
    }

    @Test
    public void testSignInWithNonExistingGoogleAccount() {
        // your code here
    }

}
