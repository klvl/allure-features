# The most of allure

This module contains examples of [allure-report](https://github.com/allure-framework/allure2) features. The current
file describes how to use this features separately from this project. Follow 
[official documentation](https://docs.qameta.io/allure/) for more details.

## Prerequisites
* Java 17  
* Maven 3.+  
* Allure commandline    
  * Using brew
    ```shell
    brew install allure
    ```
  * Using npm
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
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>${maven-surefire-plugin.version}</version>
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
