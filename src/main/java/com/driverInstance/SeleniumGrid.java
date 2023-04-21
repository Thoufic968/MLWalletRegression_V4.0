package com.driverInstance;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SeleniumGrid  {

  
    public static final String URL = "http://13.233.103.136:4444";
            //"https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    //"http://13.233.103.136:4444";
   
    public static WebDriver getDriver() throws MalformedURLException{
        WebDriver driver;
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    
       /* if (ConfigFactory.getConfig().browser() == DriverType.EDGE) {
            capabilities.setBrowserName(BrowserType.EDGE);
        } else {

        }*/
        driver = new RemoteWebDriver(new URL(URL),capabilities);
        return driver;
    }
}
