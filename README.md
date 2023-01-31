# The most of the Allure report

This repository contains usages examples of [allure-report](https://github.com/allure-framework/allure2) features.

**Outline:**
* [Prerequisites](#prerequisites)  
* [Quick start](#quick-start)  
* [Setup from scratch](#setup-from-scratch)  
* [Allure CLI](#allure-cli)
  * [Installation](#installation)
  * [Commands](#commands)
* [Allure properties](#allure-properties)
  * [Allure properties file](#allure-properties-file)
  * [Configuration in pom.xml](#configuration-in-pomxml)
  * [Command-line option](#command-line-option)
* [Descriptive names](#descriptive-names)
  * [Test actions](#test-actions)
  * [Test breakdown](#test-breakdown)
  * [Suite name](#suite-name)
  * [Steps](#steps)
* [Annotations](#annotations)
  * [Step](#step)
  * [Epic, Feature and Story](#epic-feature-and-story)
  * [Description](#description)
  * [Severity](#severity)
  * [Link](#link)
  * [Issue](#issue)
  * [TMS](#tms)
* [Allure lifecycle](#allure-lifecycle)
  * [Step](#step-1)
  * [Epic, Feature and story](#epic-feature-and-story-1)
  * [Description](#description-1)
  * [Link](#link-1)
  * [Issue](#issue-1)
  * [TMS](#tms-1)





## Prerequisites
* Java 17  
* Maven 3.+  
* Allure commandline(CLI) 

Follow the [Allure CLI/Installation](#installation) section for the installation instructions.





## Quick start

* Clone the project  
```shell
git clone git@github.com:klvl/most-of-allure.git
```
* Run test  
```shell
mvn test -Dtest="SimpleTest#simpleTest"
```
* Open report  
```shell
allure serve
```





## Setup from scratch

* Add [allure-testng](https://mvnrepository.com/artifact/io.qameta.allure/allure-testng), 
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
* Open report
```shell
allure serve
```





## Allure CLI

This section describes how to install and use allure-commandline.

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

* Generate `allure-report` directory, with HTML report  
```shell
cd /path/to/allure-results
allure generate
```
* Generate `allure-report` directory, with HTML report, passing path to `allure-results` directory  
```shell
allure generate path/to/allure-results/
```
* Generate `allure-report` directory, with HTML report, passing path to `allure-results` directory and name of output folder  
```shell
allure generate path/to/allure-results/ -o allure-report
```
* Open report in a web browser  
```shell
allure open
```
* Generate `allure-report` directory in /temp folder and open a report  
```shell
allure serve
```





## Allure properties

This section describes how to set and use Allure properties.

### Allure properties file

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

* Run test
```shell
mvn test -Dtest="SimpleTest#simpleTest" -Dallure.report.directory="target/allure-results/"
```
* Generate report
```shell
allure serve target/allure-results
```





## Descriptive names

The test name, steps and suites are displayed as packages/classes/method names. This section describes how to set 
custom names for these attributes.

### Test actions

```java
package io.klvl.descriptive;

import org.testng.annotations.Test;

public class TestActionsNamesTest {

  @Test(description = "As allure user I can set descriptive name for my test")
  public void testDescriptiveName() {
    // your code here
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



### Test breakdown

Follow the [Annotations/Epic, Feature and Story](#epic-feature-and-story-1) section for information.


### Suite name

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


### Steps

Follow the [Annotations/Step](#step) section for information.





## Annotations

This section describes usages of Allure annotations.

### Step

**Basic usage:**
```java
package io.klvl.descriptive;

public class StepTest {

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

**Parametrized step:**
```java
package io.klvl.descriptive;

public class StepTest {
    
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

**Step as lambda function:** follow [Allure lifecycle/Tests breakdown](#steps) section for details.



### Epic, Feature and Story

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

Please, note that all annotations(`@Epic`, `@Feature`/`@Features`, `@Story`/`@Stories`) can be applied on a test method
level, not only on a test class.

**Basic usage:**
```java
package io.klvl.annotations.epic.feature.story.auhthorization;

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

**Multiple Features and Stories:**
```java
package io.klvl.annotations.epic.feature.story;
        
@Epic("Some epic name")
@Features({
        @Feature("The first feature name"),
        @Feature("The second feature name"),
})
@Story("Some story name")
public class MultipleFeaturesTest {
}
```

**Multiple Stories:**
```java
package io.klvl.annotations.epic.feature.story;

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
```


### Description

**Default usage:**
```java
package io.klvl.annotations;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DescriptionTest {

  @Test
  @Description("The purpose of this test is to demonstrate how to add description to a report")
  public void testDescriptionDefaultUsage() {
    // your code here
  }

}
```

**Multi-paragraph:**
```java
package io.klvl.annotations;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DescriptionTest {

  @Test
  @Description("""
          The purpose of this test is to demonstrate how to add
          multi-paragraph description to
          allure report
          """)
  public void testDescriptionMultiParagraph() {
    // your code here
  }

}
```

**Add HTML description:** follow [Allure lifecycle/Additional Report Information/Description](#description-1) section 
for information.

**Add description dynamically:** follow [Allure lifecycle/Additional Report Information/Description](#description-1) 
section for information.



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

This section describes how to add link to a report.

**Default usage:**
```java
package io.klvl.annotations;

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

**Link with name:**
```java
package io.klvl.annotations;

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

**Multiple links:**
```java
package io.klvl.annotations;

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

**Add link dynamically:** follow [Allure lifecycle/Additional Report Information/Link](#link-1) section for information.



### Issue

This section describes how to add link with a bug symbol to a report.  

**Single issue:**
```java
package io.klvl.annotations;

public class IssueTest {

  @Test
  @Issue("https://atlassian.jira.com/issues/KLVL_123")
  public void testIssue() {
    // your code here
  }

}
```

**Multiple issues:**
```java
package io.klvl.annotations;

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

**Issue pattern:**
* Follow [Allure properties](#allure-properties) section to configure link pattern  
* Mark test methods with the `@Issue` annotation

```java
package io.klvl.annotations;

public class IssueTest {

  @Test(enabled = false)
  @Issue("KLVL_1234")
  public void testIssuePattern() {
    // your code here
  }

}
```

**Add issue dynamically:** follow [Allure lifecycle/Additional Report Information/Issue](#issue-1) section for 
information.



### TMS Link

This section describes how to add link with a test management system symbol.

**Single TMS link:**
```java
package io.klvl.annotations;

import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TmsLinkTest {

  @Test
  @TmsLink("https://atlassian.jira.com/KLVL_123")
  public void testTmsLink() {
    // your code here
  }

}
```

**Multiple TMS Links:**
```java
package io.klvl.annotations;

import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;

public class TmsLinkTest {

  @Test
  @TmsLinks({
          @TmsLink("https://atlassian.jira.com/KLVL_123"),
          @TmsLink("https://atlassian.jira.com/KLVL_124")

  })
  public void testMultipleTmsLinks() {
    // your code here
  }

}
```

**TMS Link pattern:**
* Follow [Allure properties](#allure-properties) section to configure TMS link pattern  
* Mark test methods with the `@TmsLink` annotation

```java
package io.klvl.annotations;

import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TmsLinkTest {

  @Test
  @TmsLink("KLVL_1234")
  public void testTmsLinkPattern() {
    // your code here
  }

}
```





## Allure lifecycle

This section describes how to use allure features dynamically at a runtime.

### Step

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



### Epic, Feature and story

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



### Description

**Add simple description:**
```java
package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AdditionalTestInformationTest {

    @Test
    public void testDescription() {
        Allure.description("The purpose of this test is to demonstrate how to add description to a report!");
    }

}
```

**Add HTML description:**
```java
package io.klvl.allurelifecycle;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class AdditionalTestInformationTest {

    @Test
    public void testDescriptionHtml() {
        Allure.descriptionHtml("<div style=\"color: blue\">" +
                "The purpose of this test is to demonstrate how to add HTML description to a report!" +
                "</div>");
    }

}
```



### Link

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



### Issue

```java
package io.klvl.allurelifecycle;

public class AdditionalTestInformationTest {
    
    @Test
    public void testIssue() {
        Allure.issue("KLVL-123", "https://atlassian.jira.com/KLVL-123");
    }
    
}
```
