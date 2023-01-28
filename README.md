# The most of allure

This module contains examples of [allure-report](https://github.com/allure-framework/allure2) features. The current
file describes how to use this features separately from this project. Follow 
[official documentation](https://docs.qameta.io/allure/) for more details.

**Outline:**
* [Prerequisites](#prerequisites)
* [Quick start](#quick-start)
* [Allure CLI](#allure-cli)
  * [Installation](#installation)
  * [Commands](#commands)
* [Manage allure folders](#manage-allure-folders)
  * [Specify the output directory for allure-results](#specify-the-output-directory-for-allure-results)
  * [Cleanup allure folders](#cleanup-allure-folders)
* [Descriptive names](#descriptive-names)
  * [Test actions names](#test-actions-names)
  * [Step names](#step-names)
  * [Tests breakdown](#tests-breakdown)




## Prerequisites
* Java 17  
* Maven 3.+  
* Allure commandline(CLI) 

Follow [Allure CLI/Installation](#installation) for installation instructions.




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
import io.qameta.allure.Step;
import org.testng.annotations.Test;

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




## Manage allure folders

The Allure report generates to folders `allure-results` and `allure-report`. Generally, we don't want to commit these
folders, and we put it to a `.gitignore` file. Therefore, we might also want to remove these folders using `mvn clean`.
The sections below, describe how to do it gently.


### Specify the output directory for allure-results

By default, the `allure-results` directory is generated to the project root directory. There are cases when you may want
to change the default location of the `allure-results`. In the examples below, the project build directory is used to 
demonstrate how to change the default location.

**Maven configuration property**  
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>${maven-surefire-plugin.version}</version>
      <configuration>
        <systemPropertyVariables>
          <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
        </systemPropertyVariables>
      </configuration>
    </plugin>
  </plugins>
</build>
```

**Maven command line option**
```shell
mvn test -Dtest="SimpleTest#simpleTest" -Dallure.report.directory="target/allure-results/"
```


### Cleanup allure folders  

There is an option to remove `allure-report` folder by running `mvn clean` command along with other build files:
* Add [mvn-clean-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-clean-plugin) to pom.xml
```xml
<build>
    <plugins>
      <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>${maven-clean-plugin.version}</version>
        <configuration>
          <filesets>
            <fileset>
              <directory>allure-report</directory>
            </fileset>
          </filesets>
        </configuration>
      </plugin>
  </plugins>
</build>
```
* Generate allure report  
```shell
mvn allure generate target/allure-results/
```
* Clean up working directory  
```shell
mvn clean
```

The same approach can be applied to `allure-results` folder. 
But if you [specified](#specify-the-output-directory-for-allure-results) the `allure-results` output directory as 
project build one, it is not required, because project build directory is cleaned up after `mvn clean` by default.




## Descriptive names

By default, the Allure report displays step names or test names as a method name for where annotation is applied to.
There is a possibility to set more descriptive names in Allure report.


### Test actions names   

To make name descriptive in Allure report, add a `description` parameter to annotation: 
```java
import org.testng.annotations.Test;

public class DescriptiveNameTest {
    
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

See `io.klvl.DescriptiveNamesTest` test class for examples.


### Step names 

**Basic usage**

To add descriptive name for a step, just pass string to a Step annotation:
```java
import io.qameta.allure.Step;

public class DescriptiveNamesTest {

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


**Parametrized step**

It is possible to display parameter, passed to step method, in step name:
```java
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class ParametrizedStepTest {
    
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

See `io.klvl.ParametrizedStepTest` for examples.


**Step as lambda function:**

Sometimes we want to group test steps into a single step, but we don't want put it in a separate method:
```java
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
    }
}
```

See `io.klvl.StepAsLambdaTest` for examples.


### Tests breakdown

Sometimes we want to split our test by different epics, features and stories. The allure report includes functionality
to achieve it, but first, let's review an example.

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

See `io.klvl.breakdown.authorization` package for examples.


**Multiple features and stories**

Sometimes it is required to map your test class to multiple features and/or stories. It is possible to achieve it, by
adding `@Features` and/or `@Stories` annotation to a test class. For example:
```java
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Story;

@Epic("Some epic name")
@Features({
        @Feature("The first feature name"),
        @Feature("The second feature name"),
})
@Story("Some story name")
public class MultipleFeaturesTest {
}
```

See `io.klvl.MultipleFeaturesTest` and `io.klvl.MultipleStoriesTest` for examples.


Please, note that all annotations(`@Epic`, `@Feature`/`@Features`, `@Story`/`@Stories`) can be applied on a test method
level, not only on a test class.
