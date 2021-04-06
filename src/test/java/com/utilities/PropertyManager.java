package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    private static String driverPath;
    private static String testFileToUpload;
    private static String url;
    private static String wrongEmail;
    private static String wrongPassword;
    private static String rightEmail;
    private static String rightPassword;

    // Create a Singleton instance. We need only one instance of Property Manager.
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
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
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
        return driverPath;
    }

    public String getTestFileToUpload() {
        return testFileToUpload;
    }

    public String getWrongEmail() {
        return wrongEmail;
    }

    public String getWrongPassword () {
        return wrongPassword;
    }

    public String getRightEmail() {
        return rightEmail;
    }

    public String getRightPassword () {
        return rightPassword;
    }
}
