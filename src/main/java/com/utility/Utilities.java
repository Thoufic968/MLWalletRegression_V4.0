package com.utility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.DriverInstance;
import com.driverInstance.DriverManager;
import com.global.GlobalData;
import io.appium.java_client.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.google.common.collect.Ordering;
//import com.zee5.ApplicasterPages.AMDGenericObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import java.time.Duration;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Utilities extends ExtentReporter {

    private Utilities() {
    }

    /**
     * Time out
     */
    private static int timeout;

    /**
     * Retry Count
     */
    private static int retryCount;

    //public static ExtentReporter extent = new ExtentReporter();

    @SuppressWarnings("rawtypes")
    public static TouchAction touchAction;

   // private static SoftAssert softAssert = new SoftAssert();

    public static boolean relaunch = true;

    public static String CTUserName;

    public static String CTPWD;

    public static String setPlatform = "";

    /**
     * The Constant logger.
     */
//	final static Logger logger = Logger.getLogger("rootLogger");
    static LoggingUtils logger = new LoggingUtils();

    /**
     * The Android driver.
     */
    public AndroidDriver<AndroidElement> androidDriver;

    /**
     * window handler
     */
    static ArrayList<String> win = new ArrayList<>();

    /**
     * The Android driver.
     */
    public IOSDriver<WebElement> iOSDriver;

    public static int getTimeout() {
        return 30;
    }

    public static WebDriver getWebDriver() {
        return DriverInstance.tlWebDriver.get();
    }
    
    /*public static void setTimeout(int timeout) {
        this.timeout = timeout;
    }*/

    public static int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public static AppiumDriver<WebElement> getDriver() {
        return DriverInstance.tlDriver.get();
    }

    public static String getPlatform() {
        return DriverInstance.getPlatform();
    }

    public void setPlatform(String Platform) {
        DriverInstance.setPlatfrom(Platform);
    }

    public static AppiumDriver<WebElement> getTVDriver() {
        return DriverInstance.driverTV.get();
    }

    static WebDriverWait wait;

    public static JavascriptExecutor js;
    public static Actions action;
    public static String username = "";
    public static String password = "";

    public static void initDriver() {
        if (getPlatform().equals("Web")) {
            //wait = new WebDriverWait(DriverManager.getDriver(), getTimeout());
            js = (JavascriptExecutor) DriverManager.getDriver();
            action = new Actions(DriverManager.getDriver());
            DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("TV")) {
            js = (JavascriptExecutor) DriverManager.getAppiumDriver();
            action = new Actions(DriverManager.getAppiumDriver());
            DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    public static void javaScriptExecutor() {
        if (getPlatform().equals("Web")) {
            JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        } else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("TV")) {
        }
    }

    public static void setScreenshotSource() {
        if (getPlatformFromtools().equalsIgnoreCase("Web")) {
            src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        } else if (getPlatformFromtools().equals("Android") || getPlatformFromtools().equals("PWA") || getPlatformFromtools().equals("TV")) {
            src = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        } else if (getPlatformFromtools().equalsIgnoreCase("MPWA")) {
            src = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        }
    }

    public static boolean JSClick(By byLocator, String text) throws Exception {
        try {
            js = (JavascriptExecutor) DriverManager.getAppiumDriver();
            js.executeScript("arguments[0].click();", DriverManager.getAppiumDriver().findElement(byLocator));
            logger.info("" + text + " " + " is clicked");
            ExtentReporter.extentLoggerPass("checkElementPresent", "" + text + " is clicked");
            return true;
        } catch (Exception e) {
            logger.error(text + " " + " is not clicked");
            ExtentReporter.extentLoggerFail("checkElementNotPresent", "" + text + " is not clicked");
            ExtentReporter.screencapture();
//			e.printStackTrace();
            return false;
        }
    }


    public static WebElement findElement(By byLocator) throws Exception {
        //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        //return element; +
        WebElement element = null;
        if (getPlatform().equals("Web")) {
            element = DriverManager.getDriver().findElement(byLocator);
            return element;
        } else if (getPlatform().equals("MPWA")) {
            element = DriverManager.getAppiumDriver().findElement(byLocator);
            return element;
        }
        return element;
    }


    public void setWifiConnectionToONOFF(String Value) {
        try {
            if (Value.equalsIgnoreCase("On")) {
                System.out.println("Switching On Wifi");
                String cmd = "adb shell svc wifi enable";
                Runtime.getRuntime().exec(cmd);
                waitTime(5000);
                logger.info("Wifi Data toggle is Switched On");
                ExtentReporter.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched On");
            } else if (Value.equalsIgnoreCase("Off")) {
                System.out.println("Switching Off Wifi");
                String cmd = "adb shell svc wifi disable";
                Runtime.getRuntime().exec(cmd);
                waitTime(3000);
                logger.info("Wifi Data toggle is Switched Off");
                ExtentReporter.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched Off");
            }
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    /**
     * wait until element is displayed.
     *
     * @param
     * @return true, if successful
     */
    public static boolean waitForElementDisplayed(By byLocator, int iTimeOut) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Check element not present.
     *
     * @param byLocator the by locator
     * @return true, if successful
     */
    public static boolean verifyElementNotPresent(By byLocator, String ValidationText,int iTimeOut) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), iTimeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
            logger.info(ValidationText+" is not displayed");
            ExtentReporter.extentLoggerPass("checkElementPresent", ValidationText + " is not displayed");
            return false;
        } catch (Exception e) {
            logger.info(ValidationText + " is present");
            ExtentReporter.extentLoggerPass("checkElementPresent", ValidationText + " is displayed");
            return true;
        }

    }

    /*
	For avoiding the stale element expception
	*/
    public static boolean waitforelementtoappear(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(getDriver(), time);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
        return true;
    }

    public static List<WebElement> staleexception_Click(By locator) {
        List<WebElement> outcome = null;
        int repeat = 0;
        while (repeat <= 6) {
            try {
                List<WebElement> ele = DriverManager.getDriver().findElements(locator);

                break;
            } catch (StaleElementReferenceException bhai) {
                bhai.printStackTrace();
            }
            repeat++;
        }
        return outcome;
    }

    /**
     * Check element present.
     *
     * @param byLocator the by locator
     * @return true, if successful
     */
    public static boolean verifyElementPresentOld(By byLocator, String validationtext) throws Exception {
        try {
            isPresentWithWait(byLocator);
            logger.info(validationtext + " is displayed");
            ExtentReporter.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
            return true;
        } catch (Exception e) {
            logger.error(validationtext + " is not displayed");
            ExtentReporter.extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
            return false;
        }
    }

    public static boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {
        if (isPresentWithWait(byLocator)) {
            logger.info(validationtext + " is displayed");
            ExtentReporter.extentLoggerPass("checkElementPresent", validationtext + " is displayed");

            return true;
        } else {
            logger.info(validationtext + " is not displayed");
            ExtentReporter.extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
            return false;
        }
    }

    public static boolean verifyElementExist(By byLocator, String str) throws Exception {
        try {
            WebElement element = DriverManager.getDriver().findElement(byLocator);
            if (element.isDisplayed()) {
                ExtentReporter.extentLoggerPass("checkElementPresent", "" + str + " is displayed");
                logger.info("" + str + " is displayed");
                return true;
            }
        } catch (Exception e) {
            ExtentReporter.extentLoggerFail("checkElementPresent", "" + str + " is not displayed");
            logger.error(str + " is not displayed");
            return false;
        }
        return false;
    }

    /**
     * boolean return type for conditions
     *
     * @param byLocator
     * @return
     * @throws Exception
     */
    public static boolean verifyElementDisplayed(By byLocator) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        if (platform.equalsIgnoreCase("web")) {
            try {
                WebElement element = DriverManager.getDriver().findElement(byLocator);
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
            }
        } else if (platform.equalsIgnoreCase("Android")) {
            try {
                WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }


    public static boolean checkElementExist(By byLocator, String str) throws Exception {

        try {
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            List<WebElement> list = getDriver().findElements(byLocator);
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (list.size() == 0) {
                ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
                logger.info("" + str + " is not displayed");
                return false;
            } else {
                ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
                logger.info("" + str + " is displayed");
                return list.get(0).isDisplayed();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check element present and click.
     *
     * @param byLocator the by locator
     * @return true, if successful
     */
    public static boolean verifyElementPresentAndClick(By byLocator, String validationtext) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        WebElement element = null;
        try {
            if (platform.equalsIgnoreCase("web")) {
                element = DriverManager.getDriver().findElement(byLocator);
            } else if (platform.equalsIgnoreCase("Android")) {
                element = DriverManager.getAppiumDriver().findElement(byLocator);
            }
            MLWalletBusinessLogic.softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
            logger.info("" + validationtext + " " + "is displayed");
            ExtentReporter.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
            if (platform.equalsIgnoreCase("web")) {
                DriverManager.getDriver().findElement(byLocator).click();
            } else if (platform.equalsIgnoreCase("Android")) {
                DriverManager.getAppiumDriver().findElement(byLocator).click();
            }
            logger.info("Clicked on " + validationtext);
            ExtentReporter.extentLoggerPass("click", "Clicked on " + validationtext);
            return true;
        } catch (Exception e) {
            MLWalletBusinessLogic.softAssert.assertEquals(false, true, "Element" + validationtext + " " + " is not visible");
            logger.error("Element " + validationtext + " " + " is not visible");
            ExtentReporter.extentLoggerFail("checkElementPresent", "" + validationtext + " is not displayed");
            ExtentReporter.screencapture();
            return false;
        }
    }

    public static String getAdId() throws IOException {
        String cmd = "adb shell grep adid_key /data/data/com.google.android.gms/shared_prefs/adid_settings.xml";
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String strBuffer = br.readLine().trim();
        String[] getadid = strBuffer.split(">")[1].split("<");
        System.out.println("\nAdID: " + getadid[0]);
        return getadid[0];
    }

    /**
     * @param byLocator
     * @return true or false
     */
    public static boolean checkcondition(By byLocator) throws Exception {
        boolean iselementPresent = false;
        try {
            iselementPresent = getDriver().findElement(byLocator).isDisplayed();
            iselementPresent = true;
        } catch (Exception e) {
            iselementPresent = false;
        }
        return iselementPresent;
    }

    /**
     * Click on a web element.
     *
     * @param byLocator the by locator
     */
    public static void click(By byLocator, String validationtext) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        try {
            if (platform.equalsIgnoreCase("Android")) {
                AndroidElement element = (AndroidElement) DriverManager.getAppiumDriver().findElement(byLocator);
                element.click();
            } else if (platform.equalsIgnoreCase("mpwa")) {
                WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
                element.click();
            }
            logger.info("Clicked on " + validationtext);
            ExtentReporter.extentLoggerPass("click", "Clicked on " + validationtext);
        } catch (Exception e) {
            logger.info("Not clicked on " + validationtext);
            ExtentReporter.extentLoggerFail("click", "Not Clicked on " + validationtext);
        }
    }

    public static void DoubleClick(By locator, String OptionName) {
        try {

            Actions act = new Actions(DriverManager.getAppiumDriver());
            WebElement ele = DriverManager.getAppiumDriver().findElement(locator);
            act.doubleClick(ele).perform();
            logger.info("Clicked on the " + OptionName);
            ExtentReporter.extentLogger("Click", " " + OptionName);


        } catch (NoSuchElementException e) {
            logger.info("Did not click on " + OptionName);
            ExtentReporter.extentLogger("Did not Click on", " " + OptionName);
        }
    }

    public static boolean verifyElementNotPresentForWeb(By byLocator, int iTimeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), iTimeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * clear the text field
     *
     * @param byLocator
     */
    public static void clearField(By byLocator, String text) {
        try {
            DriverManager.getAppiumDriver().findElement(byLocator).clear();
            logger.info("Cleared the text in : " + text);
            ExtentReporter.extentLogger("clear text", "Cleared the text in : " + text);
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    /**
     * Get Text from an object
     *
     * @param byLocator
     * @return
     * @throws Exception
     */
    public static String getText(By byLocator) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        String value = null;
        if (platform.equalsIgnoreCase("web")) {
            WebElement element = DriverManager.getDriver().findElement(byLocator);
            value = element.getText();
        } else if (platform.equalsIgnoreCase("Android")) {
            WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
            value = element.getText();
        }
        return value;
    }

    @SuppressWarnings({"rawtypes"})
    public String OTPNotification() throws Exception {
        ExtentReporter.HeaderChildNode("Fetching Otp From Notification");
        waitTime(2000);
        getDriver().context("NATIVE_APP");
//		touchAction = new TouchAction(getDriver());
//		touchAction.press(PointOption.point(500, 0))
//		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(4000)))
//		.moveTo(PointOption.point(1500, 500)).release().perform();
//		waitTime(8000);
        AndroidDriver driver = (AndroidDriver) getDriver();
        driver.openNotifications();
        waitTime(3000);
        List<WebElement> allnotifications = getDriver()
                .findElements(By.xpath("(//*[@resource-id='android:id/message_text'])[1]"));
        System.out.println("Size : " + allnotifications.size());
        String Otp = null;
        for (WebElement webElement : allnotifications) {
            Otp = webElement.getText();
            System.out.println("Get Text : " + webElement.getText());
            if (webElement.getText().contains("something")) {
                System.out.println("Notification found");
                break;
            }
        }
        Back(1);
        getDriver().context("WEBVIEW_1");
        return Otp;
    }

    public boolean verifyElementIsNotDisplayed(By by) {
        try {
            getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return getDriver().findElements(by).isEmpty();
        } catch (Exception e) {
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }

    public static boolean verifyIsElementDisplayed(By by) {
        List<WebElement> list = null;
        if (getPlatform().equals("Web")) {
            DriverManager.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            list = DriverManager.getDriver().findElements(by);
            DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            list = DriverManager.getAppiumDriver().findElements(by);
            DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    public static boolean verifyIsElementDisplayed(By by, String validationtext) {
        List<WebElement> list = null;
        if (getPlatform().equals("Web")) {
            DriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            list = DriverManager.getDriver().findElements(by);
            DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } else {
            getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            list = getDriver().findElements(by);
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        if (list.size() == 0) {
            logger.info("Element " + validationtext + " " + " is not displayed");
            ExtentReporter.extentLogger("checkElementPresent", "" + validationtext + " is not displayed");
            return false;
        } else {
            logger.info("" + validationtext + " " + "is displayed");
            ExtentReporter.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
            return list.get(0).isDisplayed();
        }
    }

    public static boolean checkElementExist(By byLocator) throws Exception {
        try {
            WebElement element = DriverManager.getDriver().findElement(byLocator);
            if (element.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Kill or start an application using activity
     *
     * @param command  to START or KILL an application
     * @param activity Start an application by passing the activity
     */
    public void adbStartKill(String command, String activity) {
        String cmd;
        try {
            if (command.equalsIgnoreCase("START")) {
                cmd = "adb shell am start -n" + " " + activity;
                Runtime.getRuntime().exec(cmd);
                logger.info("Started the activity" + cmd);
                ExtentReporter.extentLogger("adbStart", "Started the activity" + cmd);
            } else if (command.equalsIgnoreCase("KILL")) {
                cmd = "adb shell am force-stop" + " " + activity;
                Runtime.getRuntime().exec(cmd);
                logger.info("Executed the App switch");
                ExtentReporter.extentLogger("adbKill", "Executed the App switch");
            }
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    /**
     * @return true if keyboard is displayed
     * @throws IOException
     */
    public boolean checkKeyboardDisplayed() throws IOException {
        boolean mInputShown = false;
        try {
            String cmd = "adb shell dumpsys input_method | grep mInputShown";
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String outputText = "";
            while ((outputText = br.readLine()) != null) {
                if (!outputText.trim().equals("")) {
                    String[] output = outputText.split(" ");
                    String[] value = output[output.length - 1].split("=");
                    String keyFlag = value[1];
                    if (keyFlag.equalsIgnoreCase("True")) {
                        mInputShown = true;
                    }
                }
            }
            br.close();
            p.waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mInputShown;
    }

    /**
     * Closes the Keyboard
     */
    public static void hideKeyboard() {
        try {
            DriverManager.getAppiumDriver().hideKeyboard();
            logger.info("Hiding keyboard was Successfull");
            ExtentReporter.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
        } catch (Exception e) {
        }
    }

    /**
     * Type on a web element.
     *
     * @param byLocator the by locator
     * @param text      the text
     */
    public static void type(By byLocator, String input, String FieldName) throws Exception {
        try {
            String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
            if (platform.equalsIgnoreCase("Android")) {
                WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
                ele.sendKeys(input);
            } else if (platform.equalsIgnoreCase("mpwa")) {
                Actions a = new Actions(DriverManager.getAppiumDriver());
                a.sendKeys(input);
                a.perform();
            }
            input = input.split("\n")[0];
            logger.info("Typed the value " + input + " into " + FieldName);
            ExtentReporter.extentLoggerPass("", "Typed the value " + input + " into " + FieldName);
        } catch (Exception e) {
    		 logger.info("Not Typed the value " + input + " into " + FieldName);
             ExtentReporter.extentLoggerFail("", "Not Typed the value " + input + " into " + FieldName);
        }
    }

    public void enter(By byLocator, String input, String FieldName) {
        try {
            waitTime(1000);
            if (!getPlatform().equals("Web")) {
                Actions a = new Actions(getDriver());
                a.sendKeys(input);
                a.perform();
            } else {
                WebElement element = DriverManager.getDriver().findElement(byLocator);
                element.sendKeys(input);
            }
            input = input.split("\n")[0];
            logger.info("Typed the value into " + FieldName);
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    /**
     * Wait
     *
     * @param x seconds to lock
     */
    public static void Wait(int x) {
        try {
            getDriver().manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    public static void refresh() {
        try {
            getDriver().navigate().refresh();
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    public static void waitTime(int x) {
        try {
            Thread.sleep(x);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * @param keyevent pass the android key event value to perform specific action
     */
    public void adbKeyevents(int keyevent) {
        try {
            String cmd = "adb shell input keyevent" + " " + keyevent;
            Runtime.getRuntime().exec(cmd);
            logger.info("Performed the Keyevent" + keyevent);
            ExtentReporter.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
        } catch (Exception e) {
//			logger.error(e);
        }
    }

    /**
     * @param byLocator
     * @returns the list count of the element
     */
    public int getCount(By byLocator) {

        int count = 0;
        try {
            count = getDriver().findElements(byLocator).size();
            logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
            ExtentReporter.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
        } catch (Exception e) {
//			logger.error(e);
        }
        return count;
    }

    public static List<WebElement> findElements(By byLocator) {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        if (platform.equalsIgnoreCase("web")) {
            return DriverManager.getDriver().findElements(byLocator);
        } else if (platform.equalsIgnoreCase("Android")) {
            return DriverManager.getAppiumDriver().findElements(byLocator);
        }
        return null;
    }

    /**
     * @param i
     * @param byLocator
     * @returns the By locator
     */
    public String iterativeXpathtoStringGenerator(int temp, By byLocator, String property) {

        WebElement element = null;
        String drug = null;
        try {

            String xpath = byLocator.toString();
            String var = "'" + temp + "'";
            xpath = xpath.replaceAll("__placeholder", var);
            String[] test = xpath.split(": ");
            xpath = test[1];
            element = getDriver().findElement(By.xpath(xpath));
            drug = element.getAttribute(property);
        } catch (Exception e) {
//			System.out.println(e);
        }
        return drug;
    }

    /**
     * Back
     *
     * @throws Exception
     */
    public static void Back(int x) throws Exception {

        try {
            if (getPlatform().equals("Web")) {
                for (int i = 0; i < x; i++) {
                    DriverManager.getDriver().navigate().back();
                    logger.info("Back button is tapped");
                    ExtentReporter.extentLogger("Back", "Back button is tapped");
                }
            } else if (getPlatform().equals("Android") || getPlatform().equals("MPWA")) {
                for (int i = 0; i < x; i++) {
                    getDriver().navigate().back();
                    logger.info("Back button is tapped");
                    ExtentReporter.extentLogger("Back", "Back button is tapped");
                    waitTime(6000);
                }
            }
        } catch (Exception e) {
//			logger.error(e);
            ExtentReporter.screencapture();
        }
    }

    /**
     * Finding the duplicate elements in the list
     *
     * @param mono
     * @param content
     * @param dosechang
     * @param enteral
     */
    public List<String> findDuplicateElements(List<String> mono) {

        List<String> duplicate = new ArrayList<String>();
        Set<String> s = new HashSet<String>();
        try {
            if (mono.size() > 0) {
                for (String content : mono) {
                    if (s.add(content) == false) {
                        int i = 1;
                        duplicate.add(content);
                        System.out.println("List of duplicate elements is" + i + content);
                        i++;
                    }
                }
            }
        } catch (Exception e) {
//			System.out.println(e);
        }
        return duplicate;
    }

    /**
     * @param contents
     * @return the list without duplicate elements
     */
    public List<String> removeDuplicateElements(List<String> contents) {

        LinkedHashSet<String> set = new LinkedHashSet<String>(contents);
        ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>();
        try {

            if (contents.size() > 0) {
                listWithoutDuplicateElements = new ArrayList<String>(set);
            }

        } catch (Exception e) {
//			System.out.println(e);
        }
        return listWithoutDuplicateElements;
    }

    /**
     * @param i
     * @param byLocator
     */
    public void iteratorClick(int temp, By byLocator) {

        try {
            String xpath = byLocator.toString();
            String var = "'" + temp + "'";
            xpath = xpath.replaceAll("__placeholder", var);
            String[] test = xpath.split(": ");
            xpath = test[1];
            getDriver().findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
//			System.out.println(e);
        }
    }

    /**
     * Get specific property value of a web element and stores to string variable.
     *
     * @param property  the property of the element.
     * @param byLocator the by locator
     * @return value of the property.
     */
    public static String getElementPropertyToString(String property, By byLocator, String object) {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        String propertyValue = null;
        if (platform.equalsIgnoreCase("web")) {
            try {
                WebElement element = DriverManager.getDriver().findElement(byLocator);
                propertyValue = element.getAttribute(property);
            } catch (Exception e) {
                logger.error(e);
            }
        } else if (platform.equalsIgnoreCase("mpwa")) {
            try {
                WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
                propertyValue = element.getAttribute(property);
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return propertyValue;
    }

    /**
     * @param sorted
     * @return true if the list is sorted
     * @return false if the list is not sorted
     */
    public boolean checkListIsSorted(List<String> ListToSort) {

        boolean isSorted = false;

        if (ListToSort.size() > 0) {
            try {
                if (Ordering.natural().isOrdered(ListToSort)) {
                    ExtentReporter.extentLogger("Check sorting", "List is sorted");
                    logger.info("List is sorted");
                    isSorted = true;
                    return isSorted;
                } else {
                    ExtentReporter.extentLogger("Check sorting", ListToSort + " " + "List is not sorted");
                    logger.info(ListToSort + "List is notsorted");
                    isSorted = false;
                }
            } catch (Exception e) {
//				System.out.println(e);
            }
        } else {
            logger.info("The size of the list is zero");
            ExtentReporter.extentLogger("", ListToSort + " " + "There are no elements in the list to check the sort order");
        }
        return isSorted;
    }

    /**
     * @param byLocator
     * @returns the list count of the element
     */
    public int iterativeGetCount(int temp, By byLocator) {

        int count = 0;
        try {

            String xpath = byLocator.toString();
            String var = "'" + temp + "'";
            xpath = xpath.replaceAll("__placeholder", var);
            String[] test = xpath.split(": ");
            xpath = test[1];
            count = getDriver().findElements(By.xpath(xpath)).size();
            logger.info("List count for" + " " + xpath + " " + "is" + " " + count);
            ExtentReporter.extentLogger("getCount", "List count for" + " " + xpath + " " + "is" + " " + count);
        } catch (Exception e) {
//			logger.error(e);
        }
        return count;
    }

    /**
     * @param temp
     * @param byLocator
     * @return
     */
    public By iterativeXpathText(String temp, By byLocator) {

        By searchResultList = null;

        try {

            String xpath = byLocator.toString();
            String var = "'" + temp + "'";
            xpath = xpath.replaceAll("__placeholder", var);
            String[] test = xpath.split(": ");
            xpath = test[1];
            searchResultList = By.xpath(xpath);
        } catch (Exception e) {
//			System.out.println(e);
        }
        return searchResultList;
    }

    /**
     * @param byLocator Checks whether element is not displayed
     * @throws Exception
     */
    public void checkElementNotPresent(By byLocator) throws Exception {
        boolean isElementNotPresent = true;
        try {
            isElementNotPresent = checkcondition(byLocator);
            MLWalletBusinessLogic.softAssert.assertEquals(isElementNotPresent, false);
            logger.info("" + byLocator + " " + "is not displayed");
            ExtentReporter.extentLogger("checkElementNotPresent", "" + byLocator + "is not displayed");
        } catch (Exception e) {
            MLWalletBusinessLogic.softAssert.assertEquals(isElementNotPresent, true, "Element" + byLocator + " " + "is visible");
//			softAssert.assertAll();
            logger.error(byLocator + " " + "is visible");
            ExtentReporter.extentLogger("checkElementNotPresent", "" + byLocator + "is displayed");
            ExtentReporter.screencapture();
        }
    }

    public static void Resize_Browser(int width, int height) {
        try {

            Dimension scale = new Dimension(width, height);
            getDriver().manage().window().setSize(scale);

        } catch (Exception e) {
            logger.info("Unable to set the size of the browser");
        }


    }

    /**
     * Swipes the screen in left or right or Up or Down or direction
     *
     * @param direction to swipe Left or Right or Up or Down
     * @param count     to swipe
     */
//    @SuppressWarnings("rawtypes")
//    public static void Swipe(String direction, int count) {
//    	String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
//    	TouchAction touchAction=null;
//		if(platform.equalsIgnoreCase("web")) {
//			touchAction = new TouchAction(getDriver());
//		}
//		else if(platform.equalsIgnoreCase("Android")) {
//			touchAction = new TouchAction(DriverManager.getAppiumDriver());
//		}
//        String dire = direction;
//        try {
//            if (dire.equalsIgnoreCase("LEFT")) {
//
//                for (int i = 0; i < count; i++) {
//                    Dimension size = getDriver().manage().window().getSize();
//                    int startx = (int) (size.width * 0.5);
//                    int endx = (int) (size.width * 0.1);
//                    int starty = size.height / 2;
//                    touchAction.press(PointOption.point(startx, starty))
//                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                            .moveTo(PointOption.point(endx, starty)).release().perform();
//                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
//                    ExtentReporter.extentLogger("SwipeLeft",
//                            "Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
//                }
//            } else if (dire.equalsIgnoreCase("RIGHT")) {
//
//                for (int j = 0; j < count; j++) {
//                    Dimension size = getDriver().manage().window().getSize();
//                    int endx = (int) (size.width * 0.8);
//                    int startx = (int) (size.width * 0.20);
//                    if (size.height > 2000) {
//                        int starty = (int) (size.height / 2);
//                        touchAction.press(PointOption.point(startx, starty))
//                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                                .moveTo(PointOption.point(endx, starty)).release().perform();
//                    } else {
//                        int starty = (int) (size.height / 1.5);
//                        touchAction.press(PointOption.point(startx, starty))
//                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                                .moveTo(PointOption.point(endx, starty)).release().perform();
//                    }
//
//                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//                    ExtentReporter.extentLogger("SwipeRight",
//                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//                }
//            } else if (dire.equalsIgnoreCase("UP")) {
//
//                for (int j = 0; j < count; j++) {
//                    Dimension size = getDriver().manage().window().getSize();
//                    System.out.println("size : " + size);
//                    int starty = (int) (size.height * 0.70);// 0.8
//                    int endy = (int) (size.height * 0.30);// 0.2
//                    int startx = size.width / 2;
//                    touchAction.press(PointOption.point(startx, starty))
//                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
//                            .moveTo(PointOption.point(startx, endy)).release().perform();
//                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//                    ExtentReporter.extentLogger("SwipeUp",
//                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//
//                }
//            } else if (dire.equalsIgnoreCase("DOWN")) {
//                for (int j = 0; j < count; j++) {
//
//                    Dimension size = getDriver().manage().window().getSize();
//                    int starty = (int) (size.height * 0.70);
//                    int endy = (int) (size.height * 0.30);
//                    int startx = size.width / 2;
//                    touchAction.press(PointOption.point(startx, endy))
//                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                            .moveTo(PointOption.point(startx, starty)).release().perform();
//                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//                    ExtentReporter.extentLogger("SwipeDown",
//                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//                }
//            }
//        } catch (Exception e) {
////			logger.error(e);
//        }
//    }
//
//    @SuppressWarnings("rawtypes")
    public void SwipeRail(By From) throws Exception {

        WebElement element = DriverManager.getDriver().findElement(From);
        Dimension size = getDriver().manage().window().getSize();
        int startx = (int) (size.width * 0.8);
        int endx = (int) (size.width * 0.1);

        String eleY = element.getAttribute("y");
        int currentPosY = Integer.parseInt(eleY);
        System.out.println(currentPosY);
        currentPosY = Integer.parseInt(eleY) + 200;
        System.out.println(currentPosY);
        touchAction = new TouchAction(getDriver());
        touchAction.press(PointOption.point(startx, currentPosY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endx, currentPosY))
                .release().perform();
    }

    /**
     * Swipes the screen in left or right or Up or Down or direction
     *
     * @param direction to swipe Left or Right or Up or Down
     * @param count     to swipe
     */
    @SuppressWarnings("rawtypes")
    public void PartialSwipe(String direction, int count) {
        touchAction = new TouchAction(getDriver());
        String dire = direction;

        try {

            if (dire.equalsIgnoreCase("LEFT")) {

                for (int i = 0; i < count; i++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int startx = (int) (size.width * 0.4);
                    int endx = (int) (size.width * 0.1);
                    int starty = size.height / 2;
                    // getDriver().swipe(startx, starty, endx, starty, 1000);
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(endx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                    ExtentReporter.extentLogger("SwipeLeft",
                            "Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("RIGHT")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int endx = (int) (size.width * 0.4);
                    int startx = (int) (size.width * 0.1);
                    int starty = size.height / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(endx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeRight",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("UP")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int starty = (int) (size.height * 0.40);
                    int endy = (int) (size.height * 0.1);
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(startx, endy)).release().perform();
                    logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeUp",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            } else if (dire.equalsIgnoreCase("DOWN")) {
                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int starty = (int) (size.height * 0.4);
                    int endy = (int) (size.height * 0.1);
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, endy))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(startx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeDown",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            }

        } catch (Exception e) {
//			logger.error(e);

        }
    }

    public static void Resize_the_current_Browser() {


    }

    @SuppressWarnings("rawtypes")
    public void SwipeRailContentCards(By From) throws Exception {

        Dimension size = getDriver().manage().window().getSize();
        int screenWidth = (int) (size.width * 0.8);

        WebElement element = DriverManager.getDriver().findElement(From);
        String eleX = element.getAttribute("x");
        String eleY = element.getAttribute("y");
        int currentPosX = Integer.parseInt(eleX);
        int currentPosY = Integer.parseInt(eleY);

        currentPosX = currentPosX + screenWidth;
        currentPosY = currentPosY + 150;

        touchAction = new TouchAction(getDriver());
        touchAction.press(PointOption.point(currentPosX, currentPosY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(0, currentPosY))
                .release().perform();
    }

    /**
     * Drag window
     *
     * @param byLocator, byTimer
     */
    @SuppressWarnings("rawtypes")
    public void DragWindow(By byLocator, String direction) throws Exception {
        WebElement element = getDriver().findElement(byLocator);
        touchAction = new TouchAction(getDriver());
        if (direction.equalsIgnoreCase("LEFT")) {
            Dimension size = element.getSize();
            int startx = (int) (size.width * 0.4);
            int endx = (int) (size.width * 0.1);
            int starty = size.height / 2;
            touchAction.longPress(PointOption.point(startx, starty))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(endx, starty)).release().perform();
            logger.info("Swiping " + " " + direction + " direction");
            ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
        } else if (direction.equalsIgnoreCase("DOWN")) {
            Dimension size = getDriver().manage().window().getSize();
            int starty = (int) (size.height * 0.80);
            int endy = (int) (size.height * 0.20);
            int startx = size.width / 2;
            touchAction.longPress(PointOption.point(startx, endy))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point((int) startx, (int) starty)).release().perform();
            logger.info("Swiping " + " " + direction + " direction");
            ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
        }
    }

    /**
     * Seek by dimensions
     *
     * @param byLocator
     */

    @SuppressWarnings({"rawtypes", "unused"})
    public void TapToSeekChromecast(By byLocator) throws Exception {
        WebElement element = getDriver().findElement(byLocator);
        Dimension size = element.getSize();
        int startx = (int) (size.width);
        TouchAction action = new TouchAction(getDriver());
        SwipeAnElement(element, startx, 0);
        logger.info("Scrolling the seek bar");
    }

    /**
     * @param bundleID
     */
    public void launchiOSApp(String bundleID) {

        try {
            iOSDriver = (IOSDriver<WebElement>) getDriver();
            logger.info("Started the bundle id" + " " + bundleID);
            ExtentReporter.extentLogger("Started the bundle id" + " " + bundleID, "Started the bundle id" + " " + bundleID);
        } catch (Exception e) {
            logger.info("Unable to Start the bundle id" + " " + bundleID);
            ExtentReporter.extentLogger("Unable to Start the bundle id" + " " + bundleID,
                    "Unable to Start the bundle id" + " " + bundleID);
        }
    }

    /**
     * Get the Mobile Orientation
     *
     * @throws Exception
     */
    public void GetAndVerifyOrientation(String Value) throws Exception {
        ScreenOrientation orientation = getDriver().getOrientation();
        String ScreenOrientation = orientation.toString();
        try {
            MLWalletBusinessLogic.softAssert.assertEquals(ScreenOrientation.equalsIgnoreCase(Value), true,
                    "The screen Orientation is " + ScreenOrientation);
            logger.info("The screen Orientation is " + ScreenOrientation);
            ExtentReporter.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
        } catch (Exception e) {
            MLWalletBusinessLogic.softAssert.assertEquals(false, true, "The screen Orientation is not " + ScreenOrientation);
//			softAssert.assertAll();
            logger.error("The screen Orientation is not " + ScreenOrientation);
            ExtentReporter.extentLogger("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
            ExtentReporter.screencapture();
        }
    }

    /**
     * Closes the iOS keyboard
     */
    public void closeIosKeyboard() {

        try {
            iOSDriver = (IOSDriver<WebElement>) getDriver();
            ExtentReporter.extentLogger("Hiding keyboard successful", "Hiding keyboard successful");
        } catch (Exception e) {
            ExtentReporter.extentLogger("Hiding keyboard not successful", "Hiding keyboard not successful");
        }
    }

    /**
     * closes the application
     */
    public void closeiOSApp() {
        try {
            iOSDriver = (IOSDriver<WebElement>) getDriver();
            iOSDriver.closeApp();
            ExtentReporter.extentLogger("Killed the appliaction successfully", "Killed the appliaction successfully");
        } catch (Exception e) {
            ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");

        }
    }

    /**
     * closes the Android application
     */
    public void closeAndroidApp() {
        try {
            getDriver().resetApp();
            ExtentReporter.extentLogger("Killed the application successfully", "Killed the application successfully");
        } catch (Exception e) {
            ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");

        }
    }

    /**
     * Verifies if a new page or app is open
     */

    public boolean newPageOrNt() {
        boolean app = false;
        try {
            String cmd = "adb shell \"dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'\"";
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String outputText = "";
            while ((outputText = br.readLine()) != null) {
                if (!outputText.trim().contains("com.tv.v18.viola")) {
                    app = true;
                    logger.info("The app is navigated to ad page");
                    ExtentReporter.extentLogger("Navigated to ad page or not", "The app is navigated to ad page");
                } else {
                    logger.error("The app is not navigated to ad page");
                    ExtentReporter.extentLogger("Navigated to ad page or not", "The app is not navigated to ad page");
                }
            }
            br.close();
            p.waitFor();
        } catch (Exception e) {
//			System.out.println(e);
        }
        return app;
    }

    public void IosDragWindow(By byLocator, String direction) throws Exception {
        WebElement element = getDriver().findElement(byLocator);
        @SuppressWarnings({"rawtypes", "unused"})
        TouchAction action = new TouchAction(getDriver());
        if (direction.equalsIgnoreCase("LEFT")) {
            Dimension size = element.getSize();
            int startx = (int) (size.width * 0.4);
            System.out.println(startx);
            int endx = 0;
            System.out.println(endx);
            int starty = 1250;
            System.out.println(starty);
            SwipeAnElement(element, startx, starty);
            logger.info("Swiping " + " " + direction + " direction");
            ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
        }
    }

    public static String getAttributValue(String property, By byLocator) throws Exception {
        String Value = null;
        WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
        Value = element.getAttribute(property);
        return Value;
    }

    /*
     * public void captureScreenshotAndCompare(String SSName) throws
     * InterruptedException { Thread.sleep(10000); File src =
     * getDriver().getScreenshotAs(OutputType.FILE); String dir =
     * System.getProperty("user.dir"); String fileName = dir +
     * "/Applitool/baseLine/" + SSName + ".png"; System.out.println(fileName); try {
     * FileUtils.copyFile(src, new File(fileName)); } catch (IOException e) {
     * System.out.println(e.getMessage()); } BufferedImage img; try { img =
     * ImageIO.read(new File(fileName)); getEye().checkImage(img, SSName);
     * ExtentReporter.extentLogger("UI Validation", "UI for " + SSName + " is validated"); }
     * catch (IOException e) { System.out.println(e.getMessage()); } }
     */

    public void SwipeAnElement(WebElement element, int posx, int posy) {
        AndroidTouchAction touch = new AndroidTouchAction(getDriver());
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
                .release().perform();
    }

    public void longPressContent(By element) throws Exception {
        AndroidTouchAction touch = new AndroidTouchAction(getDriver());
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(getDriver().findElement(element))))
                .release().perform();

        TouchActions action = new TouchActions(getDriver());
        action.singleTap(getDriver().findElement(element));
        action.click();

    }

    public static boolean verifyElementExist(WebElement ele, String str) throws Exception {
        try {
            WebElement element = ele;
            if (element.isDisplayed()) {
                ExtentReporter.extentLogger("checkElementPresent", "<b>" + str + "</b> is displayed");
                logger.info("" + str + " is displayed");
                return true;
            }
        } catch (Exception e) {
            ExtentReporter.extentLogger("checkElementPresent", "<b>" + str + "</b> is not displayed");
            logger.info(str + " is not displayed");
            return false;
        }
        return false;
    }

    public boolean checkcondition(String s) throws Exception {
        boolean iselementPresent = false;
        try {
            String element = "//*[@text='[" + s + "]']";
            iselementPresent = ((WebElement) getDriver().findElementsByXPath(element)).isDisplayed();
        } catch (Exception e) {
            iselementPresent = false;
        }
        return iselementPresent;
    }

    public void switchtoLandscapeMode() throws IOException {
        Runtime.getRuntime().exec(
                "adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:1");
    }

    public void switchtoPortraitMode() throws IOException {
        Runtime.getRuntime().exec(
                "adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:0");
    }

    @SuppressWarnings("rawtypes")
    public void PartialSwipeInConsumptionScreen(String direction, int count) {
        touchAction = new TouchAction(getDriver());
        String dire = direction;

        try {

            if (dire.equalsIgnoreCase("UP")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int starty = (int) (size.height * 0.8);
                    int endy = (int) (size.height * 0.5);
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(startx, endy)).release().perform();
                    logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeUp",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            } else if (dire.equalsIgnoreCase("DOWN")) {
                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int starty = (int) (size.height * 0.8);
                    int endy = (int) (size.height * 0.5);
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, endy))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(startx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeDown",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            }

        } catch (Exception e) {
//			logger.error(e);

        }
    }

//====================================================================================================================================
    /** ::::::::::::::::Web Utilities:::::::::::: */

    /**
     * Function to ExplicitWait Visibility
     *
     * @param element
     * @param time
     * @throws Exception
     */
    public static void explicitWaitVisibility(By element, int time) throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), time);
        wait.until(ExpectedConditions.visibilityOf(DriverManager.getAppiumDriver().findElement(element)));
    }

    public static void explicitWaitVisible(By byLocator, int time) throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    /**
     * Function to ExplicitWait for Clickable
     *
     * @param element
     * @param time
     * @throws Exception
     */
    public void explicitWaitClickable(By element, int time) throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(element)));
    }

    /**
     * Function to ExplicitWait for windows
     *
     * @param noOfWindows
     */
    public static void explicitWaitForWindows(int noOfWindows) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
    }

    /**
     * Function for ExplicitWait of Element Refresh
     *
     * @throws Exception
     */
    public void explicitWaitForElementRefresh(By element) throws Exception {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(getDriver().findElement(element))));
    }

    /**
     * Function for implicitWait
     */
    public static void implicitWait(int time) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * Function to select by visible text from drop down
     *
     * @param element
     * @param value
     * @throws Exception
     */
    public void selectByVisibleTextFromDD(By element, String value) throws Exception {
        explicitWaitVisibility(element, 20);
        Select select = new Select(getDriver().findElement(element));
        select.selectByVisibleText(value);
    }

    /**
     * Function to select by value from drop down
     *
     * @param element
     * @param value
     * @throws Exception
     */
    public void selectByValueFromDD(By element, String value) throws Exception {
        explicitWaitVisibility(element, 20);
        Select select = new Select(getDriver().findElement(element));
        select.selectByValue(value);
    }

    /**
     * Function to select By index From Drop down
     *
     * @param element
     * @param value
     * @throws Exception
     */
    public void selectByIndexFromDD(By element, String value) throws Exception {
        explicitWaitVisibility(element, 20);
        Select select = new Select(getDriver().findElement(element));
        select.selectByValue(value);
    }

    /**
     * Function to get First Element from Drop down
     *
     * @param element
     * @return
     * @throws Exception
     */
    public String getFirstElementFromDD(By element) throws Exception {
        Select select = new Select(getDriver().findElement(element));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Function to scroll down
     */
    public static void scrollDownWEB() {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,250)", "");
    }

    /**
     * Function to Scroll By
     */
    public static void scrollByWEB() {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,250)", "");
    }

    /**
     * Function to scroll bottom of page
     */
    public static void scrollToBottomOfPageWEB() {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToBottomOfPage() {
        js = (JavascriptExecutor) DriverManager.getAppiumDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Function to scroll to top of the page
     */
    public static void scrollToTopOfPageWEB() {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-250)", "");
    }

    public static void scrollToTopOfPage() {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-250)", "");
    }

    /**
     * Function Scroll to Element
     *
     * @param element
     * @throws Exception
     */
    public static void ScrollToTheElement(By element) throws Exception {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
        js.executeScript("window.scrollBy(0,-50)", "");
    }

    /**
     * Function to switch to window
     *
     * @param noOfWindows
     */
	/*public void switchToWindow(int noOfWindows) {
		try {
			wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
			for (String winHandle : DriverManager.getDriver().getWindowHandles()) {
				win.add(winHandle);
				DriverManager.getDriver().switchTo().window(winHandle);
				DriverManager.getDriver().manage().window().maximize();
			}
		} catch (Exception e) {
			System.out.println("\n No window is displayed!");
		}
	}*/

    /**
     * Function to switch to parent Window
     */
	/*public void switchToParentWindow() {
		try {
			DriverManager.getDriver().switchTo().window(win.get(0));
		} catch (Exception e) {
			System.out.println("\n No window is displayed!");
		}
	}*/


    /**
     * Function for hard sleep
     *
     * @param seconds
     */
    public void sleep(int seconds) {
        try {
            int ms = 1000 * seconds;
            Thread.sleep(ms);
        } catch (InterruptedException e) {
//			e.printStackTrace();
        }
    }

    /**
     * Function to switch the tab
     *
     * @param tab
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void switchTab(int tab) {
        ArrayList<String> window = new ArrayList(DriverManager.getDriver().getWindowHandles());
        DriverManager.getDriver().switchTo().window(window.get(tab));
    }

    /**
     * Function to generate random integer of specified maxValue
     *
     * @param maxValue
     * @return
     */
    public String generateRandomInt(int maxValue) {
        Random rand = new Random();
        int x = rand.nextInt(maxValue);
        String randomInt = Integer.toString(x);
        return randomInt;
    }


    public String RandomIntegerGenerator(int n) {
        String number = "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (number.length() * Math.random());

            sb.append(number.charAt(index));
        }
        return sb.toString();
    }


    /**
     * Function to generate Random String of length 4
     *
     * @return
     */
    public String generateRandomString(int size) {
        String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
        Random rnd = new Random();
        StringBuilder strRandomNumber = new StringBuilder(9);
        strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
        String s1 = strRandomNumber.toString().toUpperCase();
        for (int i = 1; i < size; i++) {
            strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
        }
        return s1 + strRandomNumber.toString();
    }

    /**
     * Function to generate Random characters of specified length
     *
     * @param candidateChars
     * @param length
     * @return
     */
    public String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }

    /**
     * Function to generate Random Integer between range
     *
     * @param maxValue
     * @param minValue
     * @return
     * @throws InterruptedException
     */
    public String generateRandomIntwithrange(int maxValue, int minValue) throws InterruptedException {
        Thread.sleep(2000);
        Random rand = new Random();
        int x = rand.nextInt(maxValue - minValue) + 1;
        String randomInt = Integer.toString(x);
        System.out.println("Auto generate of number from generic method : " + randomInt);
        return randomInt;
    }

    /**
     * Function to drag and drop an object
     *
     * @param From
     * @param To
     * @throws Exception
     */
    public void dragnddrop(By From, By To) throws Exception {
        WebElement Drag = getDriver().findElement(From);
        WebElement Drop = getDriver().findElement(To);
        Thread.sleep(1000);
        Actions builder = new Actions(getDriver());
        builder.clickAndHold(Drag).moveToElement(Drop).release(Drop).build().perform();
    }

    /**
     * Function Convert from String to Integer @param(String)
     */
    public int convertToInt(String string) {
        int i = Integer.parseInt(string);
        return i;
    }

    /**
     * Function Convert from Integer to String @param(integer)
     */
    public String convertToString(int i) {
        String string = Integer.toString(i);
        return string;
    }

    /**
     * Click On element without waiting or verifying
     *
     * @param byLocator the by locator
     */
    public static void clickDirectly(By byLocator, String validationtext) throws Exception {
        try {
            getDriver().findElement(byLocator).click();
            logger.info("Clicked on the " + validationtext);
            ExtentReporter.extentLogger("click", "Clicked on the <b> " + validationtext);
        } catch (Exception e) {
//			logger.error(e);
            ExtentReporter.screencapture();
        }
    }

    public static void verifyAlert() {
        try {
            DriverManager.getDriver().switchTo().alert().dismiss();
            logger.info("Dismissed the alert Pop Up");
            ExtentReporter.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
        } catch (Exception e) {

        }
    }

    public void acceptAlert() {
        try {
            DriverManager.getDriver().switchTo().alert().accept();
            logger.info("Dismissed the alert Pop Up");
            ExtentReporter.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
        } catch (Exception e) {

        }
    }

    public static boolean clickElementWithLocator(By locator) throws Exception {
        String url = Utilities.getParameterFromXML("url");
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        if (platform.equalsIgnoreCase("web")) {
            try {
                DriverManager.getDriver().findElement(locator).click();
                return true;
            } catch (Exception e) {
            }
        } else if (platform.equalsIgnoreCase("mpwa")) {
            try {
                DriverManager.getAppiumDriver().findElement(locator).click();
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static boolean clickElementWithWebElement(WebElement element) throws Exception {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int timeToSec(String s) {
        String[] t = s.split(":");
        int num = 0;
        System.out.println(t.length);

        if (t.length == 2) {
            num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
        }
        if (t.length == 3) {
            num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
        }

        return num;
    }

    public static void partialScrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
        jse.executeScript("window.scrollBy(0,500)", "");
    }

    public static void clickByElement(WebElement ele, String validationtext) throws Exception {
        try {
            WebElement element = ele;
            element.click();
            logger.info("Clicked on the " + validationtext);
            ExtentReporter.extentLogger("click", "Clicked on the <b> " + validationtext);
        } catch (Exception e) {
            logger.error(e);
            ExtentReporter.screencapture();
        }
    }

    public static boolean verifyElementEnabled(By byLocator, String str) throws Exception {

        try {
            WebElement element = DriverManager.getDriver().findElement(byLocator);
            if (element.isEnabled()) {
                ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
                logger.info("" + str + " is displayed");
                return true;
            }
        } catch (Exception e) {
            ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
            logger.info(str + " is not displayed");
            return false;
        }
        return false;
    }

    public static int getCountweb(By byLocator) {
        int count = 0;
        try {
            count = DriverManager.getDriver().findElements(byLocator).size();
            logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
            ExtentReporter.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
        } catch (Exception e) {
//			logger.error(e);
        }
        return count;
    }

    public static boolean waitForElementAndClickIfPresent(By locator, int seconds, String message)
            throws Exception {
        try {
            if (getPlatform().equals("Web")) {
                for (int time = 0; time <= seconds; time++) {
                    try {
                        DriverManager.getDriver().findElement(locator).click();
                        logger.info("Clicked element " + message);
                        ExtentReporter.extentLogger("clickedElement", "Clicked element " + message);
                        return true;
                    } catch (Exception e) {
                        Thread.sleep(1000);
                    }
                }
            } else if (getPlatform().equals("Android") || getPlatform().equals("MPWA")) {
                for (int time = 0; time <= seconds; time++) {
                    try {
                        getDriver().findElement(locator).click();
                        logger.info("Clicked on " + message);
                        ExtentReporter.extentLogger("clickedElement", "Clicked on " + message);
                        return true;
                    } catch (Exception e) {
                        Thread.sleep(1000);
                    }
                }
            }
        } catch (Exception e) {
//			logger.error(e);
            ExtentReporter.screencapture();
        }
        return false;
    }

    public static String RandomStringGenerator(int n) {
        {

            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int index = (int) (AlphaNumericString.length() * Math.random());

                sb.append(AlphaNumericString.charAt(index));
            }
            return sb.toString();
        }
    }

    /**
     * Method to swipe bottom of page
     *
     * @throws Exception
     */
    public static void swipeToBottomOfPage() throws Exception {
        for (int i = 0; i < 5; i++) {
            scrollToBottomOfPage();
            waitTime(4000);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void androidSwitchTab() {
        ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
        getDriver().switchTo().window(window.get(window.size() - 1));
    }

    /**
     * Function to switch to parent Window
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void AndroidSwitchToParentWindow() {
        try {
            ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
            getDriver().switchTo().window(window.get(0));
        } catch (Exception e) {
            System.out.println("\n No window is displayed!");
        }
    }

    public static String getTheOSVersion() {
        String version = null;
        try {
            String cmd1 = "adb shell getprop ro.build.version.release";
            Process p1 = Runtime.getRuntime().exec(cmd1);
            BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            // outputText1 ="";
            while ((version = br.readLine()) != null) {
                logger.info("Version :: " + version.toString());
                Thread.sleep(3000);
                break;
            }

        } catch (Exception e) {
            // logger.error(e);
        }
        return version;
    }

    public void TurnOFFWifi() throws Exception {
        String Deviceversion = getTheOSVersion();
        System.out.println("Turn off wifi");
        if (Deviceversion.contains("6")) {
            Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
            logger.info("Turning off wifi");
            ExtentReporter.extentLoggerPass("Turning off wifi", "Turning off wifi");
        } else {
            Runtime.getRuntime().exec("adb shell svc wifi disable");
            logger.info("Turning off wifi");
            ExtentReporter.extentLoggerPass("Turning off wifi", "Turning off wifi");
        }
    }

    public void TurnONWifi() throws Exception {
        String Deviceversion = getTheOSVersion();
        System.out.println("Turn on wifi");
        if (Deviceversion.contains("6")) {
            Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
            logger.info("Turning ON wifi");
            ExtentReporter.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
        } else {
            Runtime.getRuntime().exec("adb shell svc wifi enable");
            logger.info("Turning ON wifi");
            ExtentReporter.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
        }
    }

    @SuppressWarnings("rawtypes")
    public void carouselSwipe(String direction, int count) {
        touchAction = new TouchAction(getDriver());
        String dire = direction;
        try {
            if (dire.equalsIgnoreCase("LEFT")) {

                for (int i = 0; i < count; i++) {
                    Dimension size = getDriver().manage().window().getSize();

                    int startx = (int) (size.width * 0.9);
                    int endx = (int) (size.width * 0.20);
                    int starty = size.height / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(endx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                    ExtentReporter.extentLogger("SwipeLeft",
                            "Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("RIGHT")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int endx = (int) (size.width * 0.9);
                    int startx = (int) (size.width * 0.20);
                    if (size.height > 2000) {
                        int starty = (int) (size.height / 2);
                        touchAction.press(PointOption.point(startx, starty))
                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(endx, starty)).release().perform();
                    } else {
                        int starty = (int) (size.height / 1.5);
                        touchAction.press(PointOption.point(startx, starty))
                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(endx, starty)).release().perform();
                    }

                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeRight",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                }
            }
        } catch (Exception e) {
//			logger.error(e);

        }
    }

    public static void ScrollToTheElementWEB(By element) throws Exception {
        js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
        js.executeScript("window.scrollBy(0,-250)", "");
    }

    /**
     * Function to Initialize mandatoryRegistrationPopUp count to one
     *
     * @param userType
     */
    public static void mandatoryRegistrationPopUp(String userType) {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        if (userType.contains("Guest")) {
            if (platform.equalsIgnoreCase("web")) {
                JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
                jse.executeScript("window.localStorage.setItem('mandatoryRegistrationVideoCount','1')");
            } else if (platform.equalsIgnoreCase("mpwa")) {
                JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getAppiumDriver();
                jse.executeScript("window.localStorage.setItem('mandatoryRegistrationVideoCount','1')");
            }
        }
    }

    public static boolean checkElementDisplayed(By byLocator, String str) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        if (platform.equalsIgnoreCase("web")) {
            try {
                WebElement element = DriverManager.getDriver().findElement(byLocator);
                if (element.isDisplayed()) {
                    ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
                    logger.info("" + str + " is displayed");
                    return true;
                }
            } catch (Exception e) {
                ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
                logger.info("" + str + " is not displayed");
                return false;
            }
        } else if (platform.equalsIgnoreCase("mpwa")) {
            try {
                WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
                if (element.isDisplayed()) {
                    ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
                    logger.info("" + str + " is displayed");
                    return true;
                }
            } catch (Exception e) {
                ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
                logger.info("" + str + " is not displayed");
                return false;
            }
        }
        return false;
    }

    public static String getParameterFromXML(String param) {
        return ExtentReporter.testContext.getCurrentXmlTest().getParameter(param);
    }

    /**
     * @param expectedtitle
     */
    public static void getTitle(String expectedtitle) {
        String title = DriverManager.getDriver().getTitle();
        Assert.assertEquals(title, expectedtitle, "Actual and expected titles are matching");
        logger.info("Expected Title " + title + " is matching");
        logger.info("Home Page is displayed");
        extentLogger("Expected Title ", title + " is matching");
    }

    @SuppressWarnings("rawtypes")
    public void SwipeInLandscapeMode(String direction, int count) {
        touchAction = new TouchAction(getDriver());
        String dire = direction;
        try {
            if (dire.equalsIgnoreCase("DOWN") | dire.equalsIgnoreCase("LEFT")) {

                for (int i = 0; i < count; i++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int xCor = (int) (size.height / 2);
                    int startY = (int) (size.width * 0.20);
                    int endY = (int) (size.width * 0.85);
                    System.out.println(startY + " X " + endY);
                    touchAction.press(PointOption.point(xCor, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(xCor, endY)).release().perform();
                    logger.info("Swiping screen in " + dire + " direction" + (i + 1) + " times");
                    ExtentReporter.extentLogger("SwipeLeft",
                            "Swiping screen in " + dire + " direction" + (i + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("UP") | dire.equalsIgnoreCase("RIGHT")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = getDriver().manage().window().getSize();
                    int xCor = (int) (size.height / 2);
                    int startY = (int) (size.width * 0.85);
                    int endY = (int) (size.width * 0.20);
                    System.out.println(startY + " X " + endY);
                    System.out.println(xCor);
                    touchAction.press(PointOption.point(xCor, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(xCor, endY)).release().perform();

                    logger.info("Swiping screen in " + dire + " direction " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeRight",
                            "Swiping screen in " + dire + " direction " + (j + 1) + " times");
                }
            }

        } catch (Exception e) {
//			logger.error(e);
        }
    }

    public void clearBackgroundApps() throws IOException {
        String adbRecentApp = "adb shell input keyevent KEYCODE_APP_SWITCH";
        String adbSelectApp = "adb shell input keyevent KEYCODE_DPAD_DOWN";
        String adbClearApp = "adb shell input keyevent KEYCODE_DEL";
        String adbHomeScreen = "adb shell input keyevent KEYCODE_HOME";

        Runtime.getRuntime().exec(adbRecentApp);

        for (int iterator = 1; iterator <= 7; iterator++) {
            waitTime(1000);
            Runtime.getRuntime().exec(adbClearApp);
            Runtime.getRuntime().exec(adbSelectApp);
        }

        waitTime(1000);
        Runtime.getRuntime().exec(adbHomeScreen);
        System.out.println("Cleared all background Apps");
    }

    public boolean findElementInRefreshingConvivaPage(WebDriver webdriver, By locator, String displayText) throws Exception {
        js = (JavascriptExecutor) DriverManager.getDriver();
        for (int i = 1; i <= 500; i++) {
            DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            try {
                DriverManager.getDriver().findElement(locator);
                logger.info(displayText + " is displayed");
                ExtentReporter.extentLogger("", displayText + " is displayed");
                return true;
            } catch (Exception e) {
                try {
                    js.executeScript("window.scrollBy(0,100)", "");
                    waitTime(2000);
                    System.out.println("Waiting ..");
                } catch (Exception e1) {
                }
            }
        }
        return false;
    }

    //	====================================================TV=================================================
    public boolean verifyElementExistTv(By byLocator, String str) throws Exception {

        try {

            if (getDriver().findElement(byLocator).isDisplayed()) {
                ExtentReporter.extentLoggerPass("checkElementPresent", str + " is displayed");
                logger.info("" + str + " is displayed");
                return true;
            }
        } catch (Exception e) {
            ExtentReporter.extentLogger("checkElementPresent", str + " is not displayed");
            logger.info(str + " is not displayed");
            return false;
        }
        return false;
    }

    public void TVclick(By byLocator, String validationtext) throws Exception {

        try {
            getDriver().findElement(byLocator).click();
            logger.info("Clicked on " + validationtext);
            ExtentReporter.extentLogger("click", "Clicked on " + validationtext);
        } catch (Exception e) {
//			logger.error(e);
        }
    }


    public String TVgetText(By byLocator) throws Exception {
        String Value = null;
        Value = getDriver().findElement(byLocator).getText();
        return Value;
    }


    public void TVRemoteEvent(int value) throws Exception {

        String cmd = "adb shell input keyevent " + value + "";
        Runtime.getRuntime().exec(cmd);

    }

    public boolean TVVerifyElementNotPresent(By byLocator, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
            return false;
        } catch (Exception e) {
            return true;
        }
    }


    public String TVgetAttributValue(String property, By byLocator) throws Exception {
        String Value = null;
        Value = getDriver().findElement(byLocator).getAttribute(property);
        return Value;
    }

    public static void BrowsertearDown() {
        DriverManager.getDriver().quit();
    }

    public void decode() {
        CTUserName = new String(Base64.getDecoder().decode(getParameterFromXML("CTUser")));
        CTPWD = new String(Base64.getDecoder().decode(getParameterFromXML("CTPwd")));
    }


    /**
     * The method will wait for the element to not be located for a maximum of given
     * seconds. The method terminates immediately once the element is located and
     * throws error.
     */
    public static void waitForElementAbsence(By locator, int seconds, String message) throws InterruptedException {
        main:
        for (int time = 0; time <= seconds; time++) {
            try {
                Utilities.findElement(locator);
                logger.error("Located element " + message);
                ExtentReporter.extentLoggerFail("locatedElement", "Located element " + message);
                break main;
            } catch (Exception e) {
                Thread.sleep(1000);
                if (time == seconds) {
                    logger.info("Expected behavior: " + message + " is not displayed");
                    ExtentReporter.extentLogger("failedLocateElement", "Expected behavior: " + message + " is not displayed");
                }
            }
        }
    }

    /**
     * This method will wait for element presence till the given time
     *
     * @param locator
     * @param seconds
     * @param message
     * @return
     * @throws Exception
     */
    public static boolean waitForElementPresence(By locator, int seconds, String message) throws Exception {
        try {
            String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
            if (platform.equalsIgnoreCase("web")) {
                WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
                w.until(ExpectedConditions.presenceOfElementLocated(locator));
                logger.info(message + " is displayed");
                ExtentReporter.extentLogger("element is displayed", message + " is displayed");
                return true;
            } else if (platform.equalsIgnoreCase("mpwa")) {
                WebDriverWait w = new WebDriverWait(DriverManager.getAppiumDriver(), seconds);
                w.until(ExpectedConditions.presenceOfElementLocated(locator));
                logger.info(message + " is displayed");
                ExtentReporter.extentLogger("element is displayed", message + " is displayed");
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * This method will wait for element presence till the given time
     *
     * @param locator
     * @param seconds
     * @param message
     * @return
     * @throws Exception
     */
    public static boolean waitForElementVisible(By locator, int seconds, String message) throws Exception {
        try {
            WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
            w.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info(message + " is displayed");
            ExtentReporter.extentLogger("element is displayed", message + " is displayed");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Generic method to return browser current url
     *
     * @return
     * @throws Exception
     */
    public static String getBrowserCurrentUrl() throws Exception {
        try {
            return (DriverManager.getDriver().getCurrentUrl());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Function to switch to window
     *
     * @param noOfWindows
     */
    public static void switchToWindow(int noOfWindows) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
            for (String winHandle : DriverManager.getDriver().getWindowHandles()) {
                win.add(winHandle);
                DriverManager.getDriver().switchTo().window(winHandle);
                DriverManager.getDriver().manage().window().maximize();
            }
        } catch (Exception e) {
            System.out.println("\n No window is displayed!");
        }
    }

    public static void fullScreen() {
        try {
            DriverManager.getDriver().manage().window().fullscreen();
        } catch (Exception e) {

        }
    }

    /**
     * Method to Move to Element using Actions
     *
     * @param title
     * @throws Exception
     */
    public static void moveToElementAction(WebElement element, String message) throws Exception {
        try {
            Actions a = new Actions(DriverManager.getDriver());
            a.moveToElement(element).build().perform();
            logger.info("Moved to element " + message);
            ExtentReporter.extentLogger("", "Moved to element " + message);
        } catch (Exception e) {
            logger.error("Failed to move to element " + message);
            ExtentReporter.extentLoggerFail("", "Failed to move to element " + message);
        }
    }

    public static void waitUntilElementVisible_NoCustomMessage(By by) {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        try {
            if (platform.equalsIgnoreCase("web")) {
                WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
            if (platform.equalsIgnoreCase("Android")) {
                WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), 20);
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }

        } catch (Exception e) {
            throw new ElementNotVisibleException(getCustomErrorMessageForReport(by), e.getCause());
        } finally {
        }
    }

    public static boolean isPresentWithWait(By by) {
        boolean flag = true;
        try {
            waitUntilElementVisible_NoCustomMessage(by);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String getCustomErrorMessageForReport(By by) {
        String className = by.getClass().getSimpleName();
        String value = "";
        try {
            Field field = by.getClass().getDeclaredFields()[1];
            field.setAccessible(true);
            value = field.get(by).toString();
        } catch (Exception ignored) {
        }
        return "Timed out waiting for visibility of element located = " + className + "(" + value + ")";

    }

    public static void waitForElementAndClick(By locator, int seconds, String message) throws Exception {
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName();
        main:
        for (int time = 0; time <= seconds; time++) {
            try {
                if (platform.equalsIgnoreCase("web")) {
                    DriverManager.getDriver().findElement(locator).click();
                } else if (platform.equalsIgnoreCase("mpwa")) {
                    DriverManager.getAppiumDriver().findElement(locator).click();
                }
                logger.info("Clicked element " + message);
                ExtentReporter.extentLogger("clickedElement", "Clicked element " + message);
                break main;
            } catch (Exception e) {
                Thread.sleep(1000);
                if (time == seconds) {
                    logger.error("Failed to click element " + message);
                    ExtentReporter.extentLoggerFail("failedClickElement", "Failed to click element " + message);
                }
            }
        }
    }

    /**
     * Function to select by Locator text from drop down
     *
     * @param byLocator
     * @param value
     * @throws Exception
     */
    public static void selectByVisibleTextByLocator(By byLocator, String value) throws Exception {
        explicitWaitVisibility(byLocator, 20);
        Select select = new Select(findElement(byLocator));
        select.selectByVisibleText(value);
    }

    public static void selectByValue(By byLocator, String value) throws Exception {
        explicitWaitVisibility(byLocator, 20);
        Select select = new Select(findElement(byLocator));
        select.selectByValue(value);
    }

    /**
     * Robot Function to press key
     *
     * @throws Exception
     */
    public static void robotClassUp() throws Exception {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

    /**
     * Robot Function to press key
     *
     * @throws Exception
     */
    public static void robotClassDown() throws Exception {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    /**
     * Function to switch to window
     *
     * @param noOfWindows
     */
    public static void switchFrame_id(String frame_id) {
        DriverManager.getDriver().switchTo().frame(frame_id);
    }

    public static void switchFrame_parent() {
        DriverManager.getDriver().switchTo().parentFrame();
    }

    /**
     * Method to Move to Element using Actions and click
     *
     * @param title
     * @throws Exception
     */
    public static void moveToElementActionAndClick(By byLocator, String message) throws Exception {
        try {

            WebElement element = DriverManager.getDriver().findElement(byLocator);
            Actions a = new Actions(DriverManager.getDriver());
            a.moveToElement(element).click().build().perform();
            logger.info("Moved to element " + message);
            ExtentReporter.extentLogger("", "Moved to element and Click " + message);
        } catch (Exception e) {
            logger.error("Failed to move to element and click " + message);
            ExtentReporter.extentLoggerFail("", "Failed to move to element " + message);
        }
    }

    /**
     * This method will wait for element absence till the given time
     *
     * @param locator
     * @param seconds
     * @param message
     * @return
     * @throws Exception
     */
    public static boolean waitForElementInVisible(By locator, int seconds, String message) throws Exception {
        try {
            WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
            w.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info(message + " is displayed");
            ExtentReporter.extentLogger("element is displayed", message + " is displayed");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getTextVal(By byLocator, String concatValue) throws Exception {
        String Value = null;
        WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
        Value = element.getText();
        String finalValue = Value + " " + concatValue;
        return finalValue;
    }


    public static void Swipe(String direction, int count) {
        touchAction = new TouchAction(DriverManager.getAppiumDriver());
        String dire = direction;
        try {
            if (dire.equalsIgnoreCase("LEFT")) {

                for (int i = 0; i < count; i++) {
                    Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
                    int startx = (int) (size.width * 0.5);
                    int endx = (int) (size.width * 0.1);
                    int starty = size.height / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(endx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                    ExtentReporter.extentLogger("SwipeLeft",
                            "Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("RIGHT")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
                    int endx = (int) (size.width * 0.8);
                    int startx = (int) (size.width * 0.20);
                    if (size.height > 2000) {
                        int starty = (int) (size.height / 2);
                        touchAction.press(PointOption.point(startx, starty))
                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(endx, starty)).release().perform();
                    } else {
                        int starty = (int) (size.height / 1.5);
                        touchAction.press(PointOption.point(startx, starty))
                                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                .moveTo(PointOption.point(endx, starty)).release().perform();
                    }

                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeRight",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                }
            } else if (dire.equalsIgnoreCase("UP")) {

                for (int j = 0; j < count; j++) {
                    Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
                    System.out.println("size : " + size);
                    int starty = (int) (size.height * 0.70);// 0.8
                    int endy = (int) (size.height * 0.30);// 0.2
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, starty))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                            .moveTo(PointOption.point(startx, endy)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeUp",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            } else if (dire.equalsIgnoreCase("DOWN")) {
                for (int j = 0; j < count; j++) {

                    Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
                    int starty = (int) (size.height * 0.70);
                    int endy = (int) (size.height * 0.30);
                    int startx = size.width / 2;
                    touchAction.press(PointOption.point(startx, endy))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                            .moveTo(PointOption.point(startx, starty)).release().perform();
                    logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
                    ExtentReporter.extentLogger("SwipeDown",
                            "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

                }
            }

        } catch (Exception e) {
            logger.error(e);

        }


    }

    public static void assertionValidation(String actual, String expected) throws Exception {
        MLWalletBusinessLogic.softAssert.assertEquals(actual,expected);
        if(actual.equals(expected))
        {
            logger.info(actual+" and "+expected+" are matched");
            ExtentReporter.extentLoggerPass("Assertion",actual+" and "+expected+" are matched");
        }else {
            logger.info(actual+" and "+expected+" are not matched");
            ExtentReporter.extentLoggerFail("Assertion",actual+" and "+expected+" are not matched");
        }
    }

    public static void assertNotEquals(String actual, String expected) throws Exception {
        MLWalletBusinessLogic.softAssert.assertNotEquals(actual,expected);
        if(actual!=expected)
        {
            logger.info(actual+" and "+expected+" are not matched");
            ExtentReporter.extentLoggerPass("Assertion",actual+" and "+expected+" are not matched");
        }else {
            logger.info(actual+" and "+expected+" are matched");
            ExtentReporter.extentLoggerFail("Assertion",actual+" and "+expected+" are matched");
        }
    }





    public static void scrollToFirstHorizontalScrollableElement(String textToSearch) {
        DriverManager.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollIntoView(new UiSelector().text(\""+textToSearch+"\"))"));
    }

    public static void scrollToVertical(String text)
    {
        ((FindsByAndroidUIAutomator<MobileElement>) DriverManager.getAppiumDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
        logger.info("Swiped until the element "+text+" displayed");
    }

    public static void handleOtp(String otp) throws IOException, InterruptedException
    {
        for(int i=0; i<otp.length(); i++)
        {
            char ch=otp.charAt(i);
            Thread.sleep(1000);
            String cmd = "adb shell input text "+ch+"";
            Thread.sleep(2000);
            Runtime.getRuntime().exec(cmd);
        }
        logger.info("Entered OTP "+otp+" Successfully");
        ExtentReporter.extentLogger("Enter OTP", "Entered OTP "+otp+" Successfully");
    }


    public static void swipeDownUntilElementVisible(String  text){
        ((FindsByAndroidUIAutomator<MobileElement>) DriverManager.getAppiumDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
        logger.info("Swiped until the element "+text+" displayed");
    }



    public static void horizontalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);

        new TouchAction(DriverManager.getAppiumDriver())
                .press(PointOption.point(startPoint, anchor))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
        logger.info("Swiped Horizontally");
    }





//    public static void waitTime(int x) {
//        try {
//            Thread.sleep(x);
//        } catch (Exception e) {
//            logger.error(e);
//        }
//    }








}
		
	    

