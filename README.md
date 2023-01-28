# The most of allure

This module contains examples of [allure-report](https://github.com/allure-framework/allure2) features. The current
file describes how to use this features separately from this project. Follow 
[official documentation](https://docs.qameta.io/allure/) for more details.

**Outline:**
* [Prerequisites](#prerequisites)
* [Quick start](#quick-start)
* [Allure CLI commands](#allure-cli-commands)
* [Specify output directory for allure-results](#specify-output-directory-for-allure-results)
* [Remove allure-report folder](#remove-allure-report-folder)


## Prerequisites
* Java 17  
* Maven 3.+  
* Allure commandline
```shell
brew install allure
```
or
```shell
npm install -g allure-commandline --save-dev
```

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
    public void simpleTest() {
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


## Allure CLI commands

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


## Specify output directory for allure-results

By default, the `allure-results` directory is generated to the project root directory. There are cases when you may want
to change the default location of the `allure-results`. In the examples below, the project build directory is used to 
demonstrate how to change the default location.

**Option #1: via maven configuration property**  
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

**Option #2: via maven command line option**
```shell
mvn test -Dtest="SimpleTest#simpleTest" -Dallure.report.directory="target/allure-results/"
```


## Remove allure-report folder  

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
