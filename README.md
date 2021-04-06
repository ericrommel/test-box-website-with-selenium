# AUT: www.box.com
###### source: Payonee

### The Goal
The goal of this exercise is to check coding skills, knowledge of relevant test frameworks, testing style and techniques, quality of
code that an applicant can demonstrate. Also, we want to give an impression of what kind of work to expect. The job itself will not
be related to Box.com, and we will not use submitted code in any way other than for the candidate’s application.

### Task description
Develop a Test automation project with functional tests for https://www.box.com functionality - allows to access user's account
and perform some actions. Please write automated tests for at least 3 Scenarios/Test Cases. The tests should be written using
Selenium WebDriver, Maven, Java or JavaScript, TestNG or JUnit. Feel free to use any already registered user, however user
credentials should be parametrized (easy to change). Please describe shortly how to run/configure your project.

##### Mandatory Tests Scenarios to cover
Login/Logout functionality
Create folder functionality
Upload file functionality

## Technical Requirements

These are the main tech requirement. The complete list is in requirements.txt.
- [Windows 10](https://www.microsoft.com/hu-hu/software-download/windows10)
- [Java (JDK16)](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
- [Maven (v3.8.1)](https://maven.apache.org/download.cgi)
- [Selenium (v3.141.59)](https://www.selenium.dev/downloads/)
- [ChromeDriver (v89.0.4389.23)](https://sites.google.com/a/chromium.org/chromedriver/)
- [JUnit (v5.7.0)](https://junit.org/junit5/)
- [IntelliJ (v2020.3)](https://www.jetbrains.com/idea/download/#section=windows)

### Installing

The default Git version is the master branch:

    # clone the repository
    $ cd desired/path/
    $ git clone https://github.com/ericrommel/test-box-website-with-selenium.git

### Configuration
You have to change the Configuration.properties files in order to add your own settings, for example, a correct email address and password for access the AUT

    # configure properties
    - Go to /src/test/resources
    - Open and edit the Configuration.properties file
    - Change the properties 'rightEmail' and 'rightPassword' and use one that can access the http://www.box.com

### Run
This project has been done using IntelliJ as the IDE. From there, it is easy to configure and run the project.

    # Using JUnit
    - Open the project itself from IntelliJ
    - Sync project using Maven reload project in IntelliJ
    - Right click on /src/test/java/com/box package
    - Click on Run 'Tests in 'com.box''

    # Using Maven
    - Open the project itself from IntelliJ
    - Open a terminal instance in IntelliJ
    - Type the mvn command below
    $ mvn test

## About

This project is part of [Payonee](https://www.payoneer.com) challenge for their Senior Software Test Automation Engineer
process sent in April 2021.

## Author

- [Eric Dantas](https://github.com/ericrommel)

## License

This project is licensed under the GNU License - see the [License](./LICENSE) file for details.
