package com.driverInstance;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.SkipException;

import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;
//import com.zee5.ApplicasterPages.AMDOnboardingScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverInstance extends Drivertools {
	
	public static ThreadLocal<RemoteWebDriver> tlWebDriver = new ThreadLocal<>();

	@SuppressWarnings("static-access")
	public DriverInstance(String Application,String deviceName, String portno) {
		super(Application,deviceName,portno);
		try {
			switch (getPlatform()) {
			case "Android":
//				if (getTestName().equals("Android_ChromeCast")) {
//					chromeCastInitDriver();
//				}
//				else {
					DriverManager.setAppiumDriver((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),this.generateAndroidCapabilities(Application,deviceName,portno)));
					//Utilities.waitForElementDisplayed(AMDOnboardingScreen.objWaitForSplashScreenDisapear, 240);				
					Instant endTime = Instant.now();
					timeElapsed = Duration.between(startTime, endTime);
					logger.info("Time taken to launch the App (millisec)" + timeElapsed.toMillis());
			//	}
				break;

//			case "MPWA":
//				DriverManager.setAppiumDriver((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),this.generateAndroidCapabilities(Application)));
//				DriverManager.getAppiumDriver().get(getURL());
//				break;
				
			case "MPWAiOSSafari":
				DriverManager.setAppiumDriver((AppiumDriver<WebElement>) new IOSDriver<WebElement>(new URL(getremoteUrl()),this.generateiOSCapabilities(Application)));
				DriverManager.getAppiumDriver().get(getURL());
				break;	

			case "Web":
				LaunchBrowser(getBrowserType());
				break;
				
//			case "TV":
//				tlDriver.set((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),
//						this.generateAndroidCapabilities(Application)));
//				break;

			default:
				throw new SkipException("Incorrect Platform...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("Device not connected OR Appium Studio service is down...");
		}
		Utilities.initDriver();
	}










	/**
	 * @param application
	 * @return Android capabilities
	 * @throws Exception
	 */
	protected DesiredCapabilities generateAndroidCapabilities(String application,String deviceName, String portno) {
		capabilities.setCapability("appium-version", "1.22.3");
		capabilities.setCapability(MobileCapabilityType.UDID, deviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		//capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("unlockType", "pin");
		capabilities.setCapability("unlockKey", "1111");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300 * 60);



//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
//		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
//		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//		capabilities.setCapability(MobileCapabilityType.UDID, deviceName);
//		capabilities.setCapability("fullReset", false);
//		capabilities.setCapability("autoAcceptAlerts", true);
		if (getPlatform().equals("MPWA")) {
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			return capabilities;
		}
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getAppPackage());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getappActivity());
		if (Utilities.relaunch) {
			removePermisson(getAppPackage());
		}
		startTime = Instant.now();
		return capabilities;
	}
	
	/**
	 * @param application
	 * @return Android capabilities
	 * @throws Exception
	 */
	protected DesiredCapabilities generateiOSCapabilities(String application) {
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.mobilesafari");
		capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		return capabilities;
	}
	
	@SuppressWarnings("unused")
/*	private void installAPK(String build) {
		if(build.contains("Latest") || build.contains("BuildVersion")) {
		DownloadApp(build);
		System.out.println("Finished download");
		System.out.println(dir);
		File file = new File(dir);
		file.mkdir();
		File filesList[] = file.listFiles();
		 for(File fileName : filesList) {
			 apkName = fileName.getName();
		 }
		 capabilities.setCapability(MobileCapabilityType.APP, dir+apkName);
		 System.out.println("Install APK");
		switch(getApk()) {
		case "CleverTap":
			capabilities.setCapability(MobileCapabilityType.APP, dir+apkName);
			break;
		case "AppsFlyer":
//			capabilities.setCapability(MobileCapabilityType.APP, dir+"");
			break;
		case "Conviva":
//			capabilities.setCapability(MobileCapabilityType.APP, dir+"");
			break;
		case "DFP":
			capabilities.setCapability(MobileCapabilityType.APP, dir+"DFP.apk");
			break;
		case "Mixpanel":
			capabilities.setCapability(MobileCapabilityType.APP, dir+"mixpanel.apk");
			break;
		}
	  }
	}
*/
	/**
	 * Function to Launch Web Browsers
	 * 	
	 * @param browserName
	 * @throws MalformedURLException 
	 */
	public synchronized void LaunchBrowserGrid(String browserName) throws MalformedURLException {		
		setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
		if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().browserVersion("0.27.0").setup();
			tlWebDriver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-notifications");
			try {
				//DriverManager.setDriver(new RemoteWebDriver(new URL("http://3.7.152.250:4444/"), options));
				DriverManager.setDriver(new RemoteWebDriver(new URL("http://192.168.0.191:4444/"), options));
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (browserName.equalsIgnoreCase("IE")) {
			tlWebDriver.set(new InternetExplorerDriver());
		}
		else if (browserName.equalsIgnoreCase("MSEdge")) {
			tlWebDriver.set(new EdgeDriver());
		}
		//DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DriverManager.getDriver().get(getURL());
		//DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public synchronized void LaunchBrowser(String browserName) {
		setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
		if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().browserVersion("0.27.0").setup();
			tlWebDriver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().cachePath("Drivers").setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-notifications");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			try {
				DriverManager.setDriver(new ChromeDriver(options));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (browserName.equalsIgnoreCase("ChromeNormal")) {
			WebDriverManager.chromedriver().browserVersion(getDriverVersion()).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			tlWebDriver.set(new ChromeDriver(options));
		} 
		else if (browserName.equalsIgnoreCase("IE")) {
			tlWebDriver.set(new InternetExplorerDriver());
		}
		else if (browserName.equalsIgnoreCase("MSEdge")) {
			tlWebDriver.set(new EdgeDriver());
		}	
		//DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DriverManager.getDriver().get(getURL());
		//DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * To Remove the permission of an application
	 * 
	 * @param packagename
	 */
	public static void removePermisson(String packagename)
	{
		logger.info("****Clearing the App Data****");
		String cmd2 = "adb -s "+getDeviceList()+" shell pm clear " + packagename;
		try {
			Runtime.getRuntime().exec(cmd2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*	public static void DownloadApp(String build) {
		DriverInstance.setPlatfrom("Web");
		File file = new File(System.getProperty("user.dir") + File.separator + "Apk");
		file.mkdir();
		WebDriverManager.chromedriver().browserVersion(getDriverVersion()).setup();
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "Apk");
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("prefs", prefs);
	    tlWebDriver.set(new ChromeDriver(options));
	    tlWebDriver.get().get("https://install.appcenter.ms/sign-in?original_url=install:/%2Forgs%2FZee5-Mobile%2Fapps%2FZee5-Android");
		try {
		DownloadAPPFromAPPCenter DAFAC = new DownloadAPPFromAPPCenter();
		String buildversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BuildVersion");
		DAFAC.AppCenter(build,buildversion);
		tlWebDriver.get().quit();
		DriverInstance.setPlatfrom("Android");
		}catch(Exception e) {
			
		}
	}
*/
	public void chromeCastInitDriver() throws MalformedURLException, ParseException {
		tlDriver.set((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),generateAndroidChromeCastCapabilities("Zee")));		
		driverTV.set((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),generateAndroidTvChromeCastCapabilities("zeeTV")));
	}
	
	public DesiredCapabilities generateAndroidChromeCastCapabilities(String application) {
		System.out.println("Capability");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability("udid", getDeviceList());
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getAppPackage());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getappActivity());
		return capabilities;
	}
	
	public DesiredCapabilities generateAndroidTvChromeCastCapabilities(String application) {
		System.out.println("Capability");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability("udid", getTVDeviceList());
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.graymatrix.did");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.zee5.player.activities.SplashActivity");
		return capabilities;
	}
}