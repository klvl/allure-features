package io.klvl.descriptive;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestActionsNamesTest {

    @BeforeSuite(description = "Some before suite action")
    public void beforeSuiteAction() {
        // your code here
    }

    @BeforeGroups(description = "Some before groups action")
    public void beforeGroupsAction() {
        // your code here
    }

    @BeforeTest(description = "Prepare test accounts")
    public void createAccounts() {
        // your code here
    }

    @BeforeClass(description = "Set up global data")
    public void setUpGlobalData() {
        // you code here
    }

    @BeforeMethod(description = "Authorize as default user")
    public void loginUser() {
        // your code here
    }

    @Test(description = "As allure user I can set descriptive name for my test")
    public void testDescriptiveName() {
        // your code here
    }

    @AfterMethod(description = "Close session")
    public void closeSession() {
        // your code here
    }

    @AfterClass(description = "Clean up global data")
    public void cleanUpGlobalData() {
        // your code here
    }

    @AfterTest(description = "Delete test accounts")
    public void deleteTestAccounts() {
        // your code here
    }

    @AfterGroups(description = "Some after groups action")
    public void afterGroupsAction() {
        // your code here
    }

    @AfterSuite(description = "Some after suite action")
    public void afterSuiteAction() {
        // your code here
    }

}
