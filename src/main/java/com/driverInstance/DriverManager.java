package com.driverInstance;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public final class DriverManager {

    private DriverManager(){}

    private static final ThreadLocal<WebDriver> threadSafeDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadSafeDriver.get();
    }
    public static void setDriver(WebDriver driver){
        threadSafeDriver.set(driver);
    }
    public static void unload(){
        getDriver().quit();
        threadSafeDriver.remove();
    }
    
    //////////////////// android ////////////////////////
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
    
    public static void setAppiumDriver (AppiumDriver appiumdriver) {
    	appiumDriver.set(appiumdriver);
    }
    
    public static AppiumDriver getAppiumDriver(){
        return appiumDriver.get();
    }

}
