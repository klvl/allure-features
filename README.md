# The most of the Allure report

This module contains examples of [allure-report](https://github.com/allure-framework/allure2) features. The current
file describes how to use this features separately from this project. Follow 
[official documentation](https://docs.qameta.io/allure/) for more details.

**Outline:**
* [Prerequisites](#prerequisites)
* [Quick start](#quick-start)
* [Allure CLI](#allure-cli)
  * [Installation](#installation)
  * [Commands](#commands)
* [Allure properties](#allure-properties)
  * [Allure properties file](#allure-properties-file)
  * [Configuration in pom.xml](#configuration-in-pomxml)
  * [Command-line option](#command-line-option)
* [Descriptive names](#descriptive-names)
  * [Breakdown](#breakdown)
  * [Actions](#actions)
  * [Steps](#steps)
* [Additional test information](#additional-test-information)
  * [Severity](#severity)
  * [Link](#link)
  * [Issue](#issue)
* [Allure lifecycle](#allure-lifecycle)
  * [Breakdown](#breakdown-1)
  * [Steps](#steps-1)
  * [Additional test information](#additional-test-information-1)




## Prerequisites
* Java 17  
* Maven 3.+  
* Allure commandline(CLI) 

Follow the [Allure CLI/Installation](#installation) section for the installation instructions.




## Quick start

* Create maven project  
* Add 
[allure-testng](https://mvnrepository.com/artifact/io.qameta.allure/allure-testng), 
[testng](https://mvnrepository.com/artifact/org.testng/testng) and 
[maven-surefire-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin) 
dependencies to pom.xml  
```xml
<dependencies>
  <dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>${testng.version}</version>
  </dependency>
  <dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>${allure-testng.version}</version>
  </dependency>
</dependencies>
```
* Configure aspectj in build section, maven-surefire-plugin, so steps will be displayed in report 
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>${maven-surefire-plugin.version}</version>
      <configuration>
        <argLine>
          -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
        </argLine>
      </configuration>
      <dependencies>
        <dependency>
          <groupId>org.aspectj</groupId>
          <artifactId>aspectjweaver</artifactId>
          <version>${aspectj.version}</version>
        </dependency>
      </dependencies>
    </plugin>
  </plugins>
</build>    
```
* Add a simple test  
```java
package io.klvl;

public class SimpleTest {

    @Test
    public void testSimple() {
        doFirstTestStep();
        doSecondTestStep();
    }

    @Step
    public void doFirstTestStep() {
        // your code here
    }

    @Step
    public void doSecondTestStep() {
        // your code here
    }

}
```
* Run test  
```shell
mvn test -Dtest="SimpleTest#simpleTest"
```
* Run allure command to generate and open allure report  
```shell
allure serve
```




## Allure CLI

The Allure report has an allure-command line tool for allure report generation.


### Installation

* Using brew on macOS  
```shell
brew install allure
```
* Using npm  
```shell
npm install -g allure-commandline --save-dev
```


### Commands

* Describe available commands  
```shell
allure help
```
* Generate allure-report directory, with HTML report  
```shell
allure generate
```
* Generate allure-report directory, with HTML report, passing path to allure-results directory  
```shell
allure generate path/to/allure-results/
```
* Generate allure-report directory, with HTML report, passing path to allure-results directory and name of output folder  
```shell
allure generate path/to/allure-results/ -o allure-report
```
* Open allure report in web browser  
```shell
allure open
```
* Generate allure-report in temp directory and open allure report  
```shell
allure serve
```




## Allure properties

The allure allows to configure the following properties:
* The `allure-results` directory
* The Issue link pattern
* The Tms link pattern

It is possible to configure it using `allure.properties` file or configuration property in maven-surefire-plugin, or
passing configuration property as command line option.


### Allure properties file

To use Allure properties:
* Create a file with the `allure.properties` name, in the `src/test/resources` directory
```properties
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://atlassian.jira.com/issue/{}
allure.link.tms.pattern=https://atlassian.jira.com/{}
```
* Run test
```shell
mvn clean test
```
* Generate report
```shell
allure serve target/allure-results
```


### Configuration in pom.xml

To use configuration in pom.xml:
* Add property to pom.xml
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
     __ <version>${maven-surefire-plugin.version}</version>
      <configuration>
        <systemPropertyVariables>
          <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
        </systemPropertyVariables>
      </configuration>
    </plugin>
  </plugins>
</build>
```
* Run test
```shell
mvn clean test
```
* Generate report
```shell
allure serve target/allure-results
```


### Command line option

To use property via command line:
* Run test
```shell
mvn test -Dtest="SimpleTest#simpleTest" -Dallure.report.directory="target/allure-results/"
```
* Generate report
```shell
allure serve target/allure-results
```


## Descriptive names

By default, the Allure report displays step names or test names as a method name for where annotation is applied to.
There is a possibility to set more descriptive names in Allure report.


### Breakdown

Sometimes we want to split our test by different epics, features and stories.

#### Basic test breakdown

Imagine you test authorization in your system. It is possible to sign up and sign in. There is a possibility to 
authorize using regular login and password or, using your Google account. Finally, there are different cases to test, 
e.g. sign up/sign in with valid credentials, sign up/sign in with invalid credentials and so on and so forth. 
So, let's pick up a `Sign in` flow and try to split it by different levels:
1. Authorization — it is a top level, and it is called `Epic`  
2. Sign in — it is a `Feature`  
3. Sign in with login and password(credentials) — it is a `Story`  
4. Sign in with valid/invalid credentials — `Test` cases

You can mark your test class with corresponding annotations(`@Epic`, `@Feature`, `@Story`) and it will be grouped in 
allure report correspondingly. You will be able to find it on the first `Owerview` page in the `Features by stories` 
section. It might help to find necessary tests faster and will simplify reading of your report for a stakeholders.

```java
package io.klvl.descriptive.breakdown.auhthorization;

@Epic("Authorization")
@Feature("Sign in")
@Story("Sign in with credentials")
public class SignInWithCredentialsTest {

  @Test
  public void testSignInWithValidCredentials() {
    // your code here
  }

}
```

#### Multiple features and stories

Sometimes it is required to map your test class to multiple features and/or stories. It is possible to achieve it, by
adding `@Features` and/or `@Stories` annotation to a test class. For example:
```java
package io.klvl.descriptive.breakdown;
        
@Epic("Some epic name")
@Features({
        @Feature("The first feature name"),
        @Feature("The second feature name"),
})
@Story("Some story name")
public class MultipleFeaturesTest {
}
```

Please, note that all annotations(`@Epic`, `@Feature`/`@Features`, `@Story`/`@Stories`) can be applied on a test method
level, not only on a test class.

#### Suite name

By default, all tests are displayed under `Default suite`. The TestNG suites should be used to change it.

* Create testng.xml file and add suite name
```xml
<suite name="My test suite name" verbose="1" >
  ...
</suite>
```
* Add tests to a suite(see `testng.xml` file in a root directory of the project)
* Run this suite
```shell
mvn test -DsuiteXmlFile=testng.xml
```
* Enjoy report with suite
```shell
allure serve target/allure-results
```

#### Dynamic tests breakdown

Follow [Allure lifecycle/Tests breakdown](#tests-breakdown-1) section for details.


### Actions

To make name descriptive in Allure report, add a `description` parameter to annotation:
```java
package io.klvl.descriptive;

public class TestActionsNamesTest {
    
    @Test(description = "As allure user I can set descriptive name for my test")
    public void testDescriptiveName() {
        
    }
}
```

The approach is the same for the following annotations:
* AfterClass
* AfterGroups
* AfterMethod
* AfterSuite
* AfterTest
* BeforeClass
* BeforeGroups
* BeforeMethod
* BeforeSuite
* BeforeTest
* Test


### Step names

#### Basic usage

To add descriptive name for a step, just pass string to a Step annotation:
```java
package io.klvl.descriptive;

public class StepNamesTest {

    @Step("Perform the first step")
    public void firstStep() {
        // you code here
    }

    @Step("Perform the second step")
    public void secondStep() {
        // you code here
    }
}
```

#### Parametrized step

It is possible to display parameter, passed to step method, in step name:
```java
package io.klvl.descriptive;

public class StepNamesTest {
    
    @Test
    public void testParametrizedSepName() {
        stepParametrizedByName("email@example.com", "qwerty123456");
    }
    
    @Step("This is email: {email} parameter, and this is password: {password}")
    public void stepParametrizedByName(String email, String password) {
        // your code here
    }
    
}
```

#### Step as lambda function

Follow [Allure lifecycle/Tests breakdown](#steps) section for details.




## Additional test information

This section describes possibilities for adding additional information about a test to Allure report.


### Severity

The test can have is severity and can be displayed in a report:
```java
package io.klvl;

public class SeverityTest {
    
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void testTrivialSeverity() {
        // your code here
    }
    
}
```

The Allure report has the following list of severities:
* TRIVIAL 
* MINOR 
* NORMAL 
* CRITICAL 
* BLOCKER


### Link

Sometimes we may want to add a general link to a test in allure report.

#### Default usage

To add link to a test in report:
```java
package io.klvl.testinfo;

import io.qameta.allure.Link;
import org.testng.annotations.Test;

public class LinkTest {

  @Test
  @Link("https://www.google.com")
  public void testDefaultLinkUsage() {
    // your code here
  }

}
```

#### Link with name

If we want to display a URL as text, instead of full URL:
```java
package io.klvl.testinfo;

import io.qameta.allure.Link;
import org.testng.annotations.Test;

public class LinkTest {

  @Test
  @Link(name = "my-link", url = "https://www.googgle.com")
  public void testLinkAsText() {
    // your code here
  }

}
```


#### Multiple links

To display multiple links in a report:
```java
package io.klvl.testinfo;

import io.qameta.allure.Link;
import io.qameta.allure.Links;
import org.testng.annotations.Test;

public class LinkTest {

    @Test
    @Links({
            @Link("https://www.google.com"),
            @Link("https://www.yahoo.com")
    })
    public void testMultipleLinks() {

    }

}
```

#### Add link dynamically

Follow [Allure lifecycle/Additional Report Information/Link](#link-1) section for information.


### Issue

When automated test detected a defect, we create an issue in a bug-tracking system, disable this regression test(until 
is fixed), and add link with issue to this test, to display it in a report.

#### Single issue

To display issue link for a test in Allure report, mark test method with the `@Issue` annotation:
```java
package io.klvl.testinfo;

public class IssueTest {
    
    @Test
    @Issue("https://atlassian.jira.com/issues/KLVL_123")
    public void testIssue() {
        // your code here
    }
    
}
```

#### Multiple issues

If test fails not because of one issue, the test can be marked with the `@Issues` annotation:
```java
package io.klvl.testinfo;

public class IssueTest {

    @Test(enabled = false)
    @Issues({
            @Issue("https://atlassian.jira.com/issues/KLVL_123"),
            @Issue("https://atlassian.jira.com/issues/KLVL_124")
            
    })
    public void testMultipleIssues() {
        // your code here
    }
    
}
```


#### Issue pattern

To use issue link pattern:
* Follow [Allure properties](#allure-properties) section to configure link pattern  
* Make test methods with the `@Issue` annotation
```java
package io.klvl.testinfo;

public class IssueTest {
    
  @Test(enabled = false)
  @Issue("KLVL_1234")
  public void testIssuePattern() {
    // your code here
  }

}
```

#### Add issue dynamically

Follow [Allure lifecycle/Additional Report Information/Issue](#issue-1) section for information.


## Allure lifecycle

The Allure allows to its features dynamically at a runtime, instead of using annotations. This section describes how to
achieve it.


### Breakdown

To mark test with specific `Epic`, `Feature`, `Story` and `Suite` dynamically:
```java
package io.klvl.allurelifecycle;

public class TestBreakdownTest {

    @Test
    public void testAllureLifecycleBreakdown() {
        Allure.epic("Epic name");
        Allure.feature("Feature name");
        Allure.story("Story name");
        Allure.suite("Suite name");
    }

}
```


### Steps

Sometimes we want to group test steps into a single step, but we don't want put it in a separate method:
```java
package io.klvl.allurelifecycle;

public class StepAsLambdaTest {

    @Test
    public void testStepAsLambda() {
        step("Sign in", () -> {
            openLoginPage();
            typeEmail();
            typePassword();
            clickLoginButton();
        });
    }
}
```


### Additional test information

#### Link

Add link to Allure report dynamically:
```java
package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AdditionalTestInformationTest {

  @Test
  public void testLink() {
    Allure.link("https://google.com");
    Allure.link("google", "https://google.com");
  }

}
```

#### Issue

Add issue to Allure report dynamically:
```java
package io.klvl.allurelifecycle;

public class AdditionalTestInformationTest {
    
    @Test
    public void testIssue() {
        Allure.issue("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }
    
}
```
