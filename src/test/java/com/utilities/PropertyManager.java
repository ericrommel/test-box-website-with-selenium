package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/Configuration.properties";
    private static String driverPath;
    private static String testFileToUpload;
    private static String url;
    private static String wrongEmail;
    private static String wrongPassword;
    private static String rightEmail;
    private static String rightPassword;

    public static PropertyManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    private void loadData() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("Configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }

        url = prop.getProperty("url");
        driverPath = prop.getProperty("driverPath");
        testFileToUpload = prop.getProperty("testFileToUpload");
        wrongEmail = prop.getProperty("wrongEmail");
        wrongPassword = prop.getProperty("wrongPassword");
        rightEmail = prop.getProperty("rightEmail");
        rightPassword = prop.getProperty("rightPassword");
    }

    public String getURL () {
        return url;
    }

    public String getDriverPath() {
        if (driverPath!= null)
            return driverPath;
        else
            throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public String getTestFileToUpload() {
        if (testFileToUpload!= null)
            return testFileToUpload;
        else
            throw new RuntimeException("testFileToUpload not specified in the Configuration.properties file.");
    }

    public String getWrongEmail() {
        if (wrongEmail!= null)
            return wrongEmail;
        else
            throw new RuntimeException("wrongEmail not specified in the Configuration.properties file.");
    }

    public String getWrongPassword () {
        if (wrongPassword!= null)
            return wrongPassword;
        else
            throw new RuntimeException("wrongPassword not specified in the Configuration.properties file.");
    }

    public String getRightEmail() {
        if (rightEmail!= null)
            return rightEmail;
        else
            throw new RuntimeException("rightEmail not specified in the Configuration.properties file.");
    }

    public String getRightPassword () {
        if (rightPassword!= null)
            return rightPassword;
        else
            throw new RuntimeException("rightPassword not specified in the Configuration.properties file.");
    }
}
