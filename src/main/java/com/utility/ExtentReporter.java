package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.stream.Stream;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.business.mlwallet.MLWalletBusinessLogic;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.DriverInstance;
import com.driverInstance.DriverManager;
import com.emailReport.SendEmail;
import com.excel.ExcelUpdate;
//import com.mlwallet.regression.MLWalletRegressionScripts;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Json;
import com.utility.LoggingUtils;
import io.appium.java_client.AppiumDriver;

public class ExtentReporter implements ITestListener {

	private static String report;
	public static String platform;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentReporter reporter = new ExtentReporter();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentHtmlReporter> htmlReport = new ThreadLocal<>();
	public static File src;
	private static String currentDate;
	private boolean runmode = true;
	private static String BrowserType;
	public static String filePath;
	public static String fileName;
	private static String AppVersion;
	public static String ReportName;
	public static String userType;
	public static ArrayList<String> mailBodyPart = new ArrayList<String>();
	public static int totalTests = 0;
	private static int totalPassedTest = 0;
	private static int totalFailedTest = 0;
	private static ArrayList<String> moduleFail = new ArrayList<String>();
	private static int moduleFailCount = 0;
	private static int logfail = 0;
	public static String version;
	public static String jiraID = "TC";
	public static String buildVersion;
	public static String CTCurrentTime;
	public static ArrayList<String> performaceDetails = new ArrayList<String>();
	public static Dictionary<String, String> performaceMatrics = new Hashtable<String, String>();
	static int passed = 0;
	static int failed = 0;
	public static boolean installAPK = false;
	private PropertyFileReader handler;
	boolean startTest=false;
	public static ITestContext testContext;
	
	/** The Constant logger. */
	static LoggingUtils logger = new LoggingUtils();

	@SuppressWarnings("static-access")
	public synchronized void setReport(String report) {
		this.report = report;
	}

	@SuppressWarnings("static-access")
	public static synchronized String getReport() {
		return report;
	}

	@SuppressWarnings("static-access")
	public synchronized static String getPlatform() {
		return platform;
	}

	@SuppressWarnings("static-access")
	public synchronized void setPlatform(String platform) {
		this.platform = platform; 
	}

	public static synchronized String getPlatformFromtools() {
		return DriverInstance.getPlatform();
	}
	
	@SuppressWarnings("static-access")
	public synchronized String getAppVersion() {
		return this.AppVersion;
	}

	@SuppressWarnings("static-access")
	public synchronized void setAppVersion(String versionName) {
		this.AppVersion = versionName;
	}

	public static synchronized AppiumDriver<WebElement> getDriver() {
		return DriverInstance.tlDriver.get();
	}

//	public synchronized static WebDriver getWebDriver() {
//		return DriverInstance.tlWebDriver.get();
//	}

	public static synchronized void initExtentDriver() throws Exception {
		if (getPlatformFromtools().equals("Web")) {
			src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equals("Android") || getPlatformFromtools().equals("PWA") || getPlatformFromtools().equals("TV")) {
			//src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
			src = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equals("MPWA")) {
			src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		}
	}
	
	public synchronized ExtentReports ExtentReportGenerator(ITestContext context) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		currentDate = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":","_");
		setReport(context.getName());
		setPlatform(context.getSuite().getName());
	//	userType = context.getCurrentXmlTest().getParameter("userType");
		appVersion();
		//DeviceDetails.deviceDetails();
		DeviceDetails.getTheDeviceManufacturer();
		filePath = System.getProperty("user.dir") + "/Reports" + "/" + currentDate + "/" + getPlatform()+"_"
		//+"/"
			//	+ context.getCurrentXmlTest().getParameter("userType") + "/" + context.getName() + "/"
			//	+ context.getCurrentXmlTest().getParameter("userType") +"_" 
				+ context.getName() + "_" + getAppVersion()
				+ "_" + getDate() + ".html";

		fileName = //context.getCurrentXmlTest().getParameter("userType") + "_" + 
				context.getName() + "_"
				+ getAppVersion() + "_" + getDate() + ".html";
		System.out.println("fileName "+fileName);
		//to create html file
		//ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\index.html");
		htmlReport.set(new ExtentHtmlReporter(filePath));
		htmlReport.get().loadXMLConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"), true);
		//to configure the html file
		//reporter.config().setReportName("Automation Report");
		//reporter.config().setDocumentTitle("Test Execution Results");
		//to report during execution
		extent = new ExtentReports();		
		extent.attachReporter(htmlReport.get());
		//extent.setSystemInfo("Tester", "Tani");		
		return extent;
	}

	@Override
	public synchronized void onStart(ITestContext context) {			
		//System.out.println("ON START");
		extent=ExtentReportGenerator(context);
		ExcelUpdate.UserType = context.getCurrentXmlTest().getParameter("userType");
		testContext=context;
		//ExcelUpdate.creatExcel();
		
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("ON TEST START");
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		handler=new PropertyFileReader("properties/ExecutionControl.properties");
		String testName=result.getTestContext().getName();
		if(handler.getproperty(testName).equals("Y")) {
//			logger.info("Running Test :: " + testName);
//			logger.info("Run Mode :: YES");
			DriverInstance.methodName = result.getName();
			ExcelUpdate.ModuleName = result.getName();
			logger.info(":::::::::Test " + result.getName() + " Started::::::::");
			totalTests++;
			ExcelUpdate.passCounter = ExcelUpdate.failCounter = ExcelUpdate.warningCounter = moduleFailCount = 0;
		}
		else {
			logger.info("RunMode is :: No : "+ testName +" Test is Skipped");
			startTest = false;
			throw new SkipException(testName + " : Test Skipped ");
		}
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		try {
			screencapture();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		childTest.get().log(Status.PASS, result.getName() + " is PASSED");
		logger.info("::::::::::Test " + result.getName() + " PASSED::::::::::");
		if(moduleFailCount == 0) {
		moduleFail.add(result.getName()+","+"Pass");
		}else {
		moduleFail.add(result.getName()+","+"Fail");
		}
		if(logfail != 0) {
			totalFailedTest++;
		}else {
			totalPassedTest++;
		}
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		try {
		if ((getDriver() != null) || (DriverManager.getAppiumDriver() != null)) {
			//System.out.println("result.getName():"+result.getName());
			try {
				screencapture();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			childTest.get().log(Status.FAIL, result.getName() + " is FAILED");
			logger.info("::::::::::Test " + result.getName() + " FAILED::::::::::");
			moduleFail.add(result.getName()+","+"Fail");
			totalFailedTest++;
		}

	}finally {
			logger.info("::::::::::Relaunching The App::::::::::");
			Utilities.relaunch=true;
		}

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		if (runmode) {
			HeaderChildNode(result.getTestName());
			childTest.get().log(Status.SKIP, result.getName() + " is SKIPPED");
			logger.info("::::::::::Test " + result.getName() + " SKIPPED::::::::::");
		}
	}

	public static synchronized void HeaderChildNode(String header) {
		if (extentTest.get() != null)
			childTest.set(extentTest.get().createNode(header));
			//ExcelUpdate.Node(header);
	}

	public static synchronized void extentLogger(String stepName, String details) {
		childTest.get().log(Status.INFO, details);
		//ExcelUpdate.writeData(details, "Pass", "");
	}
	
	public static synchronized void extentLoggerPass(String stepName, String details) throws Exception {

		childTest.get().log(Status.PASS, details);
//		Utilities.screencapture();
		//ExcelUpdate.writeData(details, "Pass", "");
	}

	public static synchronized void extentLoggerFail(String stepName, String details) throws Exception {
		childTest.get().log(Status.FAIL, details);
		screencapture();
// 		DriverManager.getAppiumDriver().resetApp();
		moduleFailCount = 1;
		logfail++;
//		Utilities.relaunch=true;
		//ExcelUpdate.writeData("", "Fail", details);
	}

	public static synchronized void extentLoggerWarning(String stepName, String details) {
		childTest.get().log(Status.WARNING, details);
		//ExcelUpdate.writeData("", "Warning", details);
	}


	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("ON FINISH");
		extent.flush();
		
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult context) {
	}

	public static synchronized String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String name = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":","_");
		return name;
	}

	public static synchronized void screencapture() throws Exception {
		try {
			setScreenshotSource();
			org.apache.commons.io.FileUtils.copyFile(src,
					new File(System.getProperty("user.dir") + "/Reports" + "/" + currentDate + "/" + getPlatform() + "/"
//							+ Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
//									.getParameter("userType")
							+ "/" + getReport() + "/Screenshots/" + getReport() + "_" + getDate() + ".jpg"));
			childTest.get().addScreenCaptureFromBase64String(base64Encode(src));
			logger.log(src, "Attachment");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void screencapture(WebDriver webdriver) {
		try {
			src = ((TakesScreenshot) webdriver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(src,
					new File(System.getProperty("user.dir") + "/Reports" + "/" + currentDate + "/" + getPlatform() + "/"
							+ Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
									.getParameter("userType")
							+ "/" + getReport() + "/Screenshots/" + getReport() + "_" + getDate() + ".jpg"));
			childTest.get().addScreenCaptureFromBase64String(base64Encode(src));
			logger.log(src, "Attachment");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized String base64Encode(File file) {
		if (file == null || !file.isFile()) {
			return null;
		}
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			if (fileInputStreamReader.read(bytes) != -1) {
				return "data:image/png;base64," + new String(Base64.getEncoder().encode(bytes), "UTF-8");
			}
			return null;
		} catch (Throwable e) {
			return null;
		}
	}

	public synchronized void appVersion() {
		if (getPlatform().equals("Android") || getPlatform().equals("TV")) {
			PropertyFileReader handler = new PropertyFileReader("properties/AppPackageActivity.properties");
			setAppVersion("Build " + DeviceDetails.getAppVersion(handler.getproperty("MLWalletPackage")).trim()
					.replace("versionName=", ""));
			logger.info(getAppVersion());
		} else {
			setAppVersion("");
		}
	}

	public synchronized static StringBuilder updateTVResult() {
		StringBuilder builder = new StringBuilder();
		if (mailBodyPart.size() > 0) {
			for (int i = 0; i < mailBodyPart.size(); i++) {
				String result[] = mailBodyPart.get(i).toString().split(",");
//				System.out.println(result[0]+result[1]+result[2]);
				builder.append("        <tr>\r\n" + "          <td> " + result[0] + " </td>\r\n" + "          <td> "
						+ result[1] + " </td>\r\n" + "          <td> " + result[2] + " </td>\r\n"
						+ "        </tr>\r\n");
			}
			return builder;
		}else {
			return null;
		}
	}
	
	public synchronized static StringBuilder updateResult() {
		int totalTest = moduleFail.size();
		passedCount();
		StringBuilder builder = new StringBuilder();
				builder.append("        <tr>\r\n" + "          <td> " + (totalTest) + " </td>\r\n" + "          <td> "
						+ passed + " </td>\r\n" + "          <td> " + failed + " </td>\r\n"
						+ "        </tr>\r\n");
			return builder;
	}
	
	public synchronized static void passedCount(){
		for(int i=0;i<moduleFail.size();i++){
			String result[] = moduleFail.get(i).toString().split(",");
			if(result[1].equals("Pass")){
				passed ++;
			}else {
				failed++;
			}
		}
	}
	
	static double pass = 0;
	static double fail = 0;
	public synchronized static StringBuilder updateModuleResult() {
		StringBuilder builder = new StringBuilder();
		if (moduleFail.size() > 0) {
			for (int i = 0; i < moduleFail.size(); i++) {
				String result[] = moduleFail.get(i).toString().split(",");
				if(moduleFail.get(i).toString().contains("Pass")) {
					builder.append("<tr>\r\n" + "<td> " + result[0] + " </td>\r\n" + "<td> <span style=\"font-weight:bold;color:green\">"+ result[1] + " </td>\r\n"+ "</tr>\r\n");
					pass++;
				}else {
					builder.append("<tr>\r\n" + "<td> " + result[0] + " </td>\r\n" + "<td> <span style=\"font-weight:bold;color:red\">"+ result[1] + " </td>\r\n"+ "</tr>\r\n");
					fail++;
				}
			}
			return builder;
		}else {
			return null;
		}
	}
	
	public synchronized static StringBuilder performanceDetails() {
		StringBuilder builder = new StringBuilder();
		System.out.println(performaceDetails);
		if (performaceDetails.size() > 0) {
			for (int i = 0; i < performaceDetails.size(); i++) {
				String result[] = performaceDetails.get(i).toString().split(",");
					builder.append("<tr>\r\n" + "<td> " + result[0] + " </td>\r\n" + "<td>"+ result[1] + " </td>\r\n" + "<td>"+ result[2] + " </td>\r\n"
							+"<td>"+ result[3] + " </td>\r\n"+"<td>"+ result[4] + " </td>\r\n"+"<td>"+ result[5] + " </td>\r\n"
							+"<td>"+ result[6] + " </td>\r\n"+"<td>"+ result[7] + " </td>\r\n"+"</tr>\r\n");
			}
			return builder;
		}else {
			return null;
		}
	}
	
	
	public synchronized static StringBuilder DeviceDetails() {
		String deviceDetails = "Device Name - MarQ 2K Android TV Version - 9";
		StringBuilder builder = new StringBuilder();
				builder.append("        <tr>\r\n" + "          <td> " + deviceDetails.split("Version")[0]+ " </td>\r\n" + "          <td> "
						+  deviceDetails.split("Version - ")[1] + " </td>\r\n" + "          <td> " + buildVersion + " </td>\r\n"
						+ "        </tr>\r\n");
			return builder;
	}
	
	public synchronized void CleverTapTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		CTCurrentTime = dtf.format(now);
		System.out.println(CTCurrentTime);
	}
	
	public synchronized static StringBuilder updatePercentageOffailure() {
		StringBuilder builder = new StringBuilder();
		double total = (pass+fail);
	     double percentage;
	     percentage = (fail * 100/ total);
	     String percent = String.format("%.2f", percentage);
	     builder.append("<tr>\r\n" + "<td>"+total+"</td>\r\n" + "<td>"+pass+"</td>\r\n"+ "<td>"+fail+"</td>\r\n"+"<td>"+percent+"%</td>\r\n"+"</tr>\r\n");
	     return builder;
	}
	
	public synchronized static void setScreenshotSource() {
		if (getPlatformFromtools().equals("Web")) {
			src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equals("Android") || getPlatformFromtools().equals("PWA") || getPlatformFromtools().equals("TV")) {
			src = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equals("MPWA")) {
			src = ((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		}
	}
}
