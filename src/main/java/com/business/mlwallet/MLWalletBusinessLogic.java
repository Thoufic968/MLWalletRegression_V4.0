package com.business.mlwallet;


import com.driverInstance.CommandBase;
import com.driverInstance.DriverManager;
import com.mlwallet.pages.*;
import com.propertyfilereader.PropertyFileReader;
import com.utility.ExtentReporter;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static com.utility.Utilities.*;
// Sprint demo 5


public class MLWalletBusinessLogic {

	static LoggingUtils logger = new LoggingUtils();
	private int timeout;
	private int retryCount;

	public static SoftAssert softAssert = new SoftAssert();


	public static PropertyFileReader prop = new PropertyFileReader(".\\properties\\testdata.properties");

	public MLWalletBusinessLogic(String Application, String deviceName, String portno) throws InterruptedException {
		new CommandBase(Application, deviceName, portno);
		init();
	}

	public void init() {
		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public void tearDown() {
		softAssert.assertAll();
		logger.info("Session ID: " + ((RemoteWebDriver) DriverManager.getAppiumDriver()).getSessionId());
		ExtentReporter.extentLogger("", "Session ID: " + ((RemoteWebDriver) DriverManager.getAppiumDriver()).getSessionId());
		logger.info("Session is quit");
		ExtentReporter.extentLogger("", "Session is quit");

		setScreenshotSource();
		DriverManager.getAppiumDriver().quit();
	}

	public void beforeSuite(){

	}
	//================================ LOG IN==============================================//
	public void mlWalletLogin(String sTier) throws Exception {
		explicitWaitVisible(MLWalletLoginPage.objMobileNumberTextField, 10);
		click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
		type(MLWalletLoginPage.objMobileNumberTextField, sTier, "Mobile Number Text Field");
		click(MLWalletLoginPage.objLoginBtn, "Login Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("Application Logged In Successfully");
		} else {
			logger.info("Application not get Logged In Successfully");
		}
	}

	//===================================LOG OUT=============================================================//
	public void mlWalletLogout() throws Exception {
		if (verifyElementPresent(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu")) {
			click(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
			click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
			Thread.sleep(2000);
			click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
		}
		if (verifyElementPresent(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"))) {
			logger.info("Application Logged Out Successfully");
		} else {
			logger.info("Application not get Logged Out Successfully");
		}

	}
//================================== Enter OTP ===================================================//

	public void enterOTP(String OTP) throws Exception {
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
//		verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
//		Thread.sleep(3000);
//		for(int i=1;i<=6;i++) {
//			type(MLWalletLoginPage.objOtpTextField(i), OTP, "OTP Text Field");
//		}

		waitTime(5000);
		if(verifyElementPresent(MLWalletLoginPage.objContinueBtn, "Continue Button Pop Up")) {
			click(MLWalletLoginPage.objContinueBtn, "Clicked On OTP Continue Button");
		}else {
			explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 20);
			waitTime(2000);
			verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));

			for(int i=1;i<=6;i++) {
				type(MLWalletLoginPage.objOtpTextField(i), OTP, "OTP Text Field");
			}
			waitTime(3000);
		}
	}

	public void backArrowBtn(int nNumber) throws Exception {
		for (int i = 1; i <= nNumber; i++) {
			click(SendTransferPage.objBackArrow, "Back Arrow Button");
			Thread.sleep(2000);
		}
	}


	public void enterMLPin() throws Exception {
		click(MLWalletLoginPage.objOneBtn, getTextVal(MLWalletLoginPage.objOneBtn, "Button"));
		click(MLWalletLoginPage.objOneBtn, getTextVal(MLWalletLoginPage.objOneBtn, "Button"));
		click(MLWalletLoginPage.objOneBtn, getTextVal(MLWalletLoginPage.objOneBtn, "Button"));
		click(MLWalletLoginPage.objOneBtn, getTextVal(MLWalletLoginPage.objOneBtn, "Button"));
	}


	public void enableLocation_PopUp() throws Exception {
		String loc = getText(MLWalletLoginPage.objLocationPopup);
		if (loc.contains("Allow")) {
			logger.info(loc + " Pop Up is Displayed");
			ExtentReporter.extentLoggerPass("pop up", loc + " Pop Up is Displayed");
			click(MLWalletCashOutPage.objLocationPermission, "Allow Button");
		} else {
			logger.info(" Location Pop Up is not Displayed");
			ExtentReporter.extentLoggerPass("pop up", "Location Pop Up is not Displayed");
		}
	}

	public void enableCameraPopup() throws Exception {
		String loc = getText(MLWalletLoginPage.objCameraPopup);
		if (loc.contains("Allow")) {
			logger.info(loc + " Pop Up is Displayed");
			ExtentReporter.extentLoggerPass("pop up", loc + " Pop Up is Displayed");
			click(MLWalletUseQR.objWhileUsingApp, "Allow Button");
		} else {
			logger.info(" Location Pop Up is not Displayed");
			ExtentReporter.extentLoggerPass("pop up", "Location Pop Up is not Displayed");
		}
	}


//========================================= LOGIN SCENARIOS======================================//

	public void logInScenarioWithValidMobNumber_Lgn_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Login Scenarios With Valid Mobile Number");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("Lgn_TC_01, Logged In Successfully and redirected to Dashboard");
			ExtentReporter.extentLoggerPass("Lgn_TC_01", "Lgn_TC_01, Logged In Successfully and redirected to Dashboard");
			System.out.println("-----------------------------------------------------------");

		}
	}

	public void logInScenarioWithInvalidMobNumber_Lgn_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Login Scenarios With Invalid Mobile Number");
		explicitWaitVisibility(MLWalletLoginPage.objMobileNumberTextField, 10);
		click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
		type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
		click(MLWalletLoginPage.objLoginBtn, "Login Button");
		if (verifyElementPresent(MLWalletLoginPage.objInvalidMobNumberTxt, getTextVal(MLWalletLoginPage.objInvalidMobNumberTxt, "Error Message"))) {
			logger.info("Lgn_TC_02, Mobile number is Invalid Error Message is Displayed");
			ExtentReporter.extentLoggerPass("Lgn_TC_02", "Lgn_TC_02, Mobile number is Invalid Error Message is Displayed");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logInScenarioWithValidOTP_Lgn_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Login Scenarios With Valid OTP");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("Lgn_TC_03, Logged In Successfully and redirected to Dashboard");
			ExtentReporter.extentLoggerPass("Lgn_TC_03", "Lgn_TC_03, Logged In Successfully and redirected to Dashboard");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void LogInScenarioWithInValidOTP() throws Exception {
		ExtentReporter.HeaderChildNode("Login Scenarios With InValid OTP");
		explicitWaitVisibility(MLWalletLoginPage.objMobileNumberTextField, 10);
		click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
		type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(MLWalletLoginPage.objLoginBtn, "Login Button");
//		type(MLWalletLoginPage.objOtpTextField, prop.getproperty("InValid_OTP"), "OTP Text Field");
	}


//========================================== Phase 2==========================================//

	public void appLaunch_Lgn_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("App Launch");
		if (verifyElementPresent(MLWalletLoginPage.objLoginBtn, "Login Button")) {
			logger.info("Lgn_TC_05, Application Launched Successfully");
			ExtentReporter.extentLoggerPass("Lgn_TC_05", "Lgn_TC_05, Application Launched Successfully");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginPageUIValidation_Lgn_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Login Page UI Validation");
		if (verifyElementPresent(MLWalletLoginPage.objLoginBtn, "Login Button")) {
			verifyElementPresent(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn, getTextVal(MLWalletLoginPage.objTroubleSigningIn, "Button"));
			verifyElementPresent(MLWalletLoginPage.objHaveAnAccountMsg, getTextVal(MLWalletLoginPage.objHaveAnAccountMsg, "Message"));
			verifyElementPresent(MLWalletLoginPage.objCreateOneBtn, getTextVal(MLWalletLoginPage.objCreateOneBtn, "Button"));
			verifyElementPresent(MLWalletLoginPage.objBranchLocator, getTextVal(MLWalletLoginPage.objBranchLocator, "Button"));
			verifyElementPresent(MLWalletLoginPage.objAppVersion, getTextVal(MLWalletLoginPage.objAppVersion, "App Version"));
			logger.info("Lgn_TC_06, Login Page UI Validated");
			ExtentReporter.extentLoggerPass("Lgn_TC_06", "Lgn_TC_06,  Login Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginTroubleSigningIn_Lgn_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("LogIn Trouble Signing In Validation");
		if (verifyElementPresentAndClick(MLWalletLoginPage.objTroubleSigningIn, getTextVal(MLWalletLoginPage.objTroubleSigningIn, "Button"))) {
			verifyElementPresent(MLWalletLoginPage.objTroubleSigningPage, getTextVal(MLWalletLoginPage.objTroubleSigningPage, "Page"));
			verifyElementPresent(MLWalletLoginPage.objMLWalletSupport, getTextVal(MLWalletLoginPage.objMLWalletSupport, "Header"));
			logger.info("Lgn_TC_07, Navigated to Trouble Signing In Page");
			ExtentReporter.extentLoggerPass("Lgn_TC_07", "Lgn_TC_07,  Navigated to Trouble Signing In Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginCreateOne_Lgn_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("LogIn Create One");
		if (verifyElementPresentAndClick(MLWalletLoginPage.objCreateOneBtn, getTextVal(MLWalletLoginPage.objCreateOneBtn, "Button"))) {
			verifyElementPresent(MLWalletLoginPage.objRegistrationNumber, getTextVal(MLWalletLoginPage.objRegistrationNumber, "Page"));
			logger.info("Lgn_TC_08, Navigated to Create One Page");
			ExtentReporter.extentLoggerPass("Lgn_TC_08", "Lgn_TC_08, Navigated to Create One Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginBranchLocator_Lgn_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("LogIn Branch Locator");
		if (verifyElementPresentAndClick(MLWalletLoginPage.objBranchLocator, getTextVal(MLWalletLoginPage.objBranchLocator, "Button"))) {
			enableLocation_PopUp();
			verifyElementPresent(MLWalletLoginPage.objBranchLocator, getTextVal(MLWalletLoginPage.objBranchLocator, "Page"));
			logger.info("Lgn_TC_09, Navigated to Branch Locator Page");
			ExtentReporter.extentLoggerPass("Lgn_TC_09", "Lgn_TC_09, Navigated to Branch Locator Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginOTPPageUIValidation_Lgn_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("LogIn OTP Page UI Validation");
		click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
		type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(MLWalletLoginPage.objLoginBtn, "Login Button");
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
//			verifyElementPresent(MLWalletLoginPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("Lgn_TC_10, LogIn OTP Page UI Validated");
			ExtentReporter.extentLoggerPass("Lgn_TC_10", "Lgn_TC_10, LogIn OTP Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginWithExistingMobileNumber_Lgn_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Login With Existing Mobile Number");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		mlWalletLogout();
		verifyElementPresent(MLWalletLogOutPage.objChangeNumber,"Mpin Page");
		enterMLPin();
		explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("Lgn_TC_17, Application Logged In Successfully");
			ExtentReporter.extentLoggerPass("Lgn_TC_17", "Lgn_TC_17, Application Logged In Successfully");
			System.out.println("-----------------------------------------------------------");
		} else {
			logger.info("Application not get Logged In Successfully");
		}
	}

	public void loginMPinPageUIValidation_Lgn_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Login Mpin Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		mlWalletLogout();
		if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,"Mpin Page")){
			verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"button"));
			verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Button"));
			verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"));
			verifyElementPresent(MLWalletLoginPage.objAppVersionChangeNumber,getTextVal(MLWalletLoginPage.objAppVersionChangeNumber,"App Version"));
			logger.info("Lgn_TC_18, Application Logged In Successfully");
			ExtentReporter.extentLoggerPass("Lgn_TC_18", "Lgn_TC_18, Application Logged In Successfully");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void loginNetworkInterruptionWhileLoggingInValidation_Lgn_TC_19(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Login Network Interruption Validation");
		explicitWaitVisible(MLWalletLoginPage.objMobileNumberTextField, 10);
		click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
		type(MLWalletLoginPage.objMobileNumberTextField, sTier, "Mobile Number Text Field");
		click(MLWalletLoginPage.objLoginBtn, "Login Button");
		waitTime(5000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("Lgn_TC_19, Login Network Interruption Validation");
			ExtentReporter.extentLoggerPass("Lgn_TC_19", "Lgn_TC_19, Login Network Interruption Validation");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}

	public void loginInternetInterruptionWhileLaunchingApp_Lgn_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Login Internet Interruption While Launching App");
		setWifiConnectionToONOFF("OFF");
		if(verifyElementPresent(MLWalletLoginPage.objErrorPopup,getTextVal(MLWalletLoginPage.objErrorPopup,"pop up"))){
			verifyElementPresentAndClick(MLWalletLoginPage.objOkBtn,getTextVal(MLWalletLoginPage.objOkBtn,"Button"));
			verifyElementPresent(MLWalletLoginPage.objNoConnection,getTextVal(MLWalletLoginPage.objNoConnection,"Header"));
			verifyElementPresent(MLWalletLoginPage.objNoInternetConnectionMsg,getTextVal(MLWalletLoginPage.objNoInternetConnectionMsg,"Msg"));
			verifyElementPresent(MLWalletLoginPage.objNoConnectionAppVersion,getTextVal(MLWalletLoginPage.objNoConnectionAppVersion,"App version"));
			logger.info("Lgn_TC_20, Login Internet Interruption While Launching App");
			ExtentReporter.extentLoggerPass("Lgn_TC_20", "Lgn_TC_20, Login Internet Interruption While Launching App");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}






//========================================CASH OUT / WITHDRAW===============================================//
//======================================= Generalized methods =============================================//


	public void cashOutSelectBank(String sBank) throws Exception {
		if (verifyElementPresent(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button")) {
			click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
			if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
				click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
				type(MLWalletCashOutPage.objSearchBank, sBank, "Search Bank Text Field");
				click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
			}
		}
	}

	public void enterBankDetails(String sAccountNumber) throws Exception {
		if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
			type(MLWalletCashOutPage.objAccountNumberField, sAccountNumber, "Account Number Field");
			type(MLWalletCashOutPage.objFirstname, prop.getproperty("First_Name"), "Account Holder First Name");
			type(MLWalletCashOutPage.objMiddleName, prop.getproperty("Middle_Name"), "Account Holder Middle Name");
			click(MLWalletCashOutPage.objCheckBox, "Check Box");
			type(MLWalletCashOutPage.objLastName, prop.getproperty("Last_Name"), "Account Holder Last Name");
			Swipe("UP", 1);
			type(MLWalletCashOutPage.objEmailAddress, prop.getproperty("Email"), "Account Holder Email Address");
			click(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		}

	}

	public void enterAmountMLBranch(String nAmount) throws Exception {
		if (verifyElementPresent(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"))) {
			click(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"));
			verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"));
			type(MLWalletCashOutPage.objAmountField, nAmount, "Amount to Send");
			click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
			click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
			Thread.sleep(3000);
		}
	}

	public void enterAmountBank(String sAmount) throws Exception {
		if (verifyElementPresent(MLWalletCashOutPage.objAmountField, "Bank Cash Out Amount Field")) {
			type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
			click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
			explicitWaitVisible(MLWalletCashOutPage.objDragonPayPopUpMsg,10);
			waitTime(10000);
			String sDragonPopUpMsg = getText(MLWalletCashOutPage.objDragonPayPopUpMsg);
			String sExpectedMsg = "Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?";
			assertionValidation(sDragonPopUpMsg, sExpectedMsg);
			click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
			swipeDownUntilElementVisible("Next");
			click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
			Thread.sleep(5000);
		}
	}

	//===================================================================================================================//
	public void cashOutWithdrawBank_WM_TC_01(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(Amount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			String sTransactionSuccess = getText(MLWalletCashOutPage.objTransactionSuccessMessage);
			assertionValidation(sTransactionSuccess, "Transaction Successful");
			verifyElementPresent(MLWalletCashOutPage.objTransactionNo, getTextVal(MLWalletCashOutPage.objTransactionNo, "Transaction Number"));
			String sTransactionNumber = getText(MLWalletCashOutPage.objTransactionNo);
			System.out.println(sTransactionNumber);
			scrollToVertical("Back To Home");
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sTransactionNumber);
				logger.info("WM_TC_01, Successfully Withdraw Money to Bank");
				ExtentReporter.extentLoggerPass("WM_TC_01", "WM_TC_01, Successfully Withdraw Money to Bank");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutWithInvalidAccNumber_WM_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("cashOut With Invalid Account Number");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("Invalid_AccountNumber"));
		Thread.sleep(3000);
		if (verifyElementPresent(MLWalletCashOutPage.objAccInvalidErrorMsg, getTextVal(MLWalletCashOutPage.objAccInvalidErrorMsg, "Text Message"))) {
			String sInvalidAccTxt = getText(MLWalletCashOutPage.objAccInvalidErrorMsg);
			String ExpectedTxt = "Bank Account provided not valid. Please check the account details and try again.";
			assertionValidation(sInvalidAccTxt, ExpectedTxt);
			logger.info("WM_TC_02, Bank Account provided not valid. Error Message is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_02", "WM_TC_02, Bank Account provided not valid. Error Message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutWithdrawBankMaxAmount_WM_TC_03(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch Max Amount");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(Amount);
		if (verifyElementPresent(MLWalletCashOutPage.objBankMaxLimitTxt, getTextVal(MLWalletCashOutPage.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Bank Cash-out per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("WM_TC_03, The Maximum Bank Cash-out per transaction Msg is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_03", "WM_TC_03, The Maximum Bank Cash-out per transaction Msg is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutWithdrawMinTransactionLimit_WM_TC_04(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch Max Amount");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		explicitWaitVisible(MLWalletCashOutPage.objAmountField, 5);
		type(MLWalletCashOutPage.objAmountField, Amount, "Amount to Send");
		click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
		Thread.sleep(5000);
		if (verifyElementPresent(MLWalletCashOutPage.objMinimumTransactionErrorMsg, getTextVal(MLWalletCashOutPage.objMinimumTransactionErrorMsg, "Error message"))) {
			String sMinimumTransactionErrorMsg = getText(MLWalletCashOutPage.objMinimumTransactionErrorMsg);
			String sExpectedMsg = "The supplied amount is less than the required minimum transaction limit";
			assertionValidation(sMinimumTransactionErrorMsg, sExpectedMsg);
			logger.info("WM_TC_04, The supplied amount is less than the required minimum transaction limit Error Msg is validated");
			ExtentReporter.extentLoggerPass("WM_TC_04", "WM_TC_04, The supplied amount is less than the required minimum transaction limit Error Msg is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutWithdrawBranch_WM_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
			String nReference = getText(MLWalletCashOutPage.objReferenceNumber);
			System.out.println(nReference);
			String sReferenceNumber = nReference.substring(5, 16);
			System.out.println(sReferenceNumber);
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
				logger.info("Reference Number is matching with recent Transaction");
				logger.info("WM_TC_05, Successfully Withdraw Money to ML Branch");
				ExtentReporter.extentLoggerPass("WM_TC_05", "WM_TC_05, Successfully Withdraw Money to ML Branch");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutMaxLimit_WM_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("100000");
		if (verifyElementPresent(MLWalletCashOutPage.objMaxLimitTxt, getTextVal(MLWalletCashOutPage.objMaxLimitTxt, "Text Message"))) {
			String sMaxLimitTxt = getText(MLWalletCashOutPage.objMaxLimitTxt);
			String ExpectedTxt = "The maximum Branch Cash-out per transaction set for your verification level is P40,000.00. Please try again. ";
			assertionValidation(sMaxLimitTxt, ExpectedTxt);
			logger.info("WM_TC_06, The supplied amount us less than the required minimum transaction limit. Error Message is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_06", "WM_TC_06, The supplied amount us less than the required minimum transaction limit. Error Message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutInsufficientBalance_WM_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Insufficient Balance");
		mlWalletLogin(prop.getproperty("Branch_Verified_ELoad_LowBalance"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("35000");
		if (verifyElementPresent(MLWalletCashOutPage.objInsufficientBalance, getTextVal(MLWalletCashOutPage.objInsufficientBalance, "Text Message"))) {
			String sInsufficientBalancePopupTxt = getText(MLWalletCashOutPage.objInsufficientBalance);
			String ExpectedTxt = "There is insufficient balance to proceed with this transaction. Please try again.";
			assertionValidation(sInsufficientBalancePopupTxt, ExpectedTxt);
			logger.info("WM_TC_07, Insufficient Balance pop up validated");
			ExtentReporter.extentLoggerPass("WM_TC_07", "WM_TC_07, Insufficient Balance pop up validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBuyerTierLevelAcc_WM_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Withdraw Branch");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("100");
		if (verifyElementPresent(MLWalletCashOutPage.objMaxLimitTxt, getTextVal(MLWalletCashOutPage.objMaxLimitTxt, "Text Message"))) {
			String sErrorMessage = getText(MLWalletCashOutPage.objMaxLimitTxt);
			String ExpectedTxt = "Branch Cash-out is not allowed for customers at this verification level. Please upgrade your account to use this service.";
			assertionValidation(sErrorMessage, ExpectedTxt);
			logger.info("WM_TC_09, Branch Cash-out is not allowed for customers at this verification level. Error Message is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_09", "WM_TC_08, Branch Cash-out is not allowed for customers at this verification level. Error Message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


//=================================== Cash Out Phase2==================================================//

	public void cashOutInvalidBank_WM_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Invalid Bank Details");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button")) {
			click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
			if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
				click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
				type(MLWalletCashOutPage.objSearchBank, prop.getproperty("Invalid_BankName"), "Search Bank Text Field");
				if (verifyElementPresent(MLWalletCashOutPage.objNoRecentTransactionTxt, getTextVal(MLWalletCashOutPage.objNoRecentTransactionTxt, "Text"))) {
					logger.info("WM_TC_10, No Recent Transaction message Validated after entering invalid Bank Name");
					ExtentReporter.extentLoggerPass("WM_TC_10", "WM_TC_10, No Recent Transaction message Validated after entering invalid Bank Name");
					System.out.println("-----------------------------------------------------------");
				}
			}
		}
	}

	public void searchAndSelectBank_WM_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Search And Select Bank");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank Name"));
			logger.info("WM_TC_11, Bank Name auto-displayed after searching and selecting the particular Bank");
			ExtentReporter.extentLoggerPass("WM_TC_11", "WM_TC_11, Bank Name auto-displayed after searching and selecting the particular Bank");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutInvalidAmount_WM_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Search And Select Bank");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		type(MLWalletCashOutPage.objAmountField, "", "Amount to Send");
		click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objAmountRequiredErrorMsg, getTextVal(MLWalletCashOutPage.objAmountRequiredErrorMsg, "Error Message"))) {
			String sAmountRequiredErrorMsg = getText(MLWalletCashOutPage.objAmountRequiredErrorMsg);
			String sExceptedErrorMsg = "Amount is required";
			assertionValidation(sAmountRequiredErrorMsg, sExceptedErrorMsg);
			logger.info("WM_TC_12, Amount is required - Error message is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_12", "WM_TC_12, Amount is required - Error message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutSaveRecipient_WM_TC_13(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash out Save Recipient");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		scrollToVertical("Back To Home");
		type(MLWalletCashOutPage.objNickName, prop.getproperty("Nick_Name"), "Nick Name Input Text Field");
		click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
		explicitWaitVisible(MLWalletCashOutPage.objToAnyBank, 5);
		verifyElementPresentAndClick(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
		click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
		verifyElementPresent(MLWalletCashOutPage.objSavedBankAccount, getTextVal(MLWalletCashOutPage.objSavedBankAccount, "Page"));
		if (verifyElementPresent(MLWalletCashOutPage.objNickNameInSavedBankAcc(prop.getproperty("Nick_Name")), getTextVal(MLWalletCashOutPage.objNickNameInSavedBankAcc(prop.getproperty("Nick_Name")), "Nick Name for Saved Bank Account"))) {
			logger.info("WM_TC_13, Saved Recipient is displayed under Saved Bank Account");
			ExtentReporter.extentLoggerPass("WM_TC_13", "WM_TC_13, Saved Recipient is displayed under Saved Bank Account");
			System.out.println("-----------------------------------------------------------");

		}
	}

	public void cashOutRecipientDuplicate_WM_TC_14(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash out Recipient Duplicate");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		scrollToVertical("Back To Home");
		type(MLWalletCashOutPage.objNickName, prop.getproperty("Nick_Name"), "Nick Name Input Text Field");
		click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objRecipientExistMsg, getTextVal(MLWalletCashOutPage.objRecipientExistMsg, "Popup Message"))) {
			String sRecipientExistMsg = getText(MLWalletCashOutPage.objRecipientExistMsg);
			String sExpectedMsg = "Recipient already exists.";
			assertionValidation(sRecipientExistMsg, sExpectedMsg);
			logger.info("WM_TC_14, Recipient already exists pop up message Validated");
			ExtentReporter.extentLoggerPass("WM_TC_14", "WM_TC_14, Recipient already exists pop up message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutUIValidation_WM_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCashOutOptions, getTextVal(MLWalletCashOutPage.objCashOutOptions, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Option"));
			verifyElementPresent(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Option"));
			verifyElementPresent(MLWalletCashOutPage.objCashOutOngoingTransaction, getTextVal(MLWalletCashOutPage.objCashOutOngoingTransaction, "Header"));
			logger.info("WM_TC_16, Cash Out Page UI Validation");
			ExtentReporter.extentLoggerPass("WM_TC_16", "WM_TC_16, Cash Out Page UI Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutWithdrawBackBtnValidation_WM_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Page back arrow Button Validation");
		cashOutUIValidation_WM_TC_16();
		click(MLWalletCashOutPage.cashOutBackArrowBtn, "Cash Out Page Back Arrow Button");
		explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("WM_TC_17, Cash Out Page back arrow Button Validation");
			ExtentReporter.extentLoggerPass("WM_TC_17", "WM_TC_17, Cash Out Page back arrow Button Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutToBranchUIValidation_WM_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out To Branch UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		verifyElementPresentAndClick(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutPage, getTextVal(MLWalletCashOutPage.objCashOutPage, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.ObjCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objUserName, getTextVal(MLWalletCashOutPage.objUserName, "User Name"));
			verifyElementPresent(MLWalletCashOutPage.objUserMobileNumber, getTextVal(MLWalletCashOutPage.objUserMobileNumber, "User Mobile Number"));
			verifyElementPresent(MLWalletCashOutPage.objMLWalletBalance, getTextVal(MLWalletCashOutPage.objMLWalletBalance, "Balance"));
			verifyElementPresent(MLWalletCashOutPage.objCashOutInstructions, "Instructions Icon");
			logger.info("WM_TC_18, Cash Out to Branch Page Validation");
			ExtentReporter.extentLoggerPass("WM_TC_18", "WM_TC_18, Cash Out to Branch Page Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutToBranchBackBtnValidation_WM_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out To Branch Page back arrow Button Validation");
		cashOutToBranchUIValidation_WM_TC_18();
		click(MLWalletCashOutPage.objCashOutToBranchBackBtn, "Cash Out Page Back Arrow Button");
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
			logger.info("WM_TC_19, Cash Out To Branch Page back arrow Button Validation");
			ExtentReporter.extentLoggerPass("WM_TC_19", "WM_TC_19, Cash Out To Branch Page back arrow Button Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutOTPPageUIValidation_WM_TC_20(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out OTP Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("WM_TC_20, One Time Pin page UI Validation");
			ExtentReporter.extentLoggerPass("WM_TC_20", "WM_TC_20, One Time Pin page UI Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutOTPPageBackBtnValidation_WM_TC_21(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out OTP Page Back Button Validation");
		cashOutOTPPageUIValidation_WM_TC_20(sAmount);
		click(MLWalletCashOutPage.objOneTimePinBackBtn, "OTP Back Arrow Button");
		if (verifyElementPresent(MLWalletCashOutPage.objReviewTransaction, getTextVal(MLWalletCashOutPage.objReviewTransaction, "Page"))) {
			logger.info("WM_TC_21, OTP page back arrow Button Validation");
			ExtentReporter.extentLoggerPass("WM_TC_21", "WM_TC_21, OTP page back arrow Button Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutMlBranchQRPageUIValidation_WM_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Branch QR Code Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
			verifyElementPresent(MLWalletCashOutPage.objPHP, getTextVal(MLWalletCashOutPage.objPHP, "Amount"));
			verifyElementPresent(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			verifyElementPresent(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
			logger.info("WM_TC_22, CashOut ML Branch QR Code Page UI Validation");
			ExtentReporter.extentLoggerPass("WM_TC_22", "WM_TC_22, CashOut ML Branch QR Code Page UI Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutCancelIconValidation_WM_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Cancel Icon Validation");
		cashOutMlBranchQRPageUIValidation_WM_TC_22();
		verifyElementPresentAndClick(MLWalletCashOutPage.objCancelIcon, "Cancel Icon");
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("WM_TC_23, Cash Out Cancel Icon Validated");
			ExtentReporter.extentLoggerPass("WM_TC_23", "WM_TC_23, Cash Out Cancel Icon Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutPendingTransactionValidation_WM_TC_24(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Pending Transaction Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			scrollToVertical("Back To Home");
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletHomePage.objPendingStatusForCashOut, getTextVal(MLWalletHomePage.objPendingStatusForCashOut, "Status"))) {
				String sActualStatus = getText(MLWalletHomePage.objPendingStatusForCashOut);
				String sExceptedStatus = "Pending";
				assertionValidation(sActualStatus, sExceptedStatus);
				logger.info("WM_TC_24, Cash Out Pending Transaction Validated");
				ExtentReporter.extentLoggerPass("WM_TC_24", "WM_TC_24, Cash Out Pending Transaction Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutMLBankBuyerTier_WM_TC_27(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Bank Buyer Tier");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletCashOutPage.objMaxLimitTxt, getTextVal(MLWalletCashOutPage.objMaxLimitTxt, "Text Message"))) {
			String sErrorMessage = getText(MLWalletCashOutPage.objMaxLimitTxt);
			String ExpectedTxt = "Bank Cash-out is not allowed for customers at this verification level. Please upgrade your account to use this service.";
			assertionValidation(sErrorMessage, ExpectedTxt);
			verifyElementPresent(MLWalletCashOutPage.objUpgradeNowBtn, getTextVal(MLWalletCashOutPage.objUpgradeNowBtn, "Button"));
			logger.info("WM_TC_27, CashOut ML Bank Buyer Tier Validated");
			ExtentReporter.extentLoggerPass("WM_TC_27", "WM_TC_27, CashOut ML Bank Buyer Tier Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutSemiVerifiedTier_WM_TC_28(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Withdraw Semi-Verified Tier Account");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumberCashOutBranch, getTextVal(MLWalletCashOutPage.objReferenceNumberCashOutBranch, "Reference NUmber"));
			verifyElementPresent(MLWalletCashOutPage.objTransactionNo, getTextVal(MLWalletCashOutPage.objTransactionNo, "Transaction Number"));
			logger.info("WM_TC_28, CashOut Withdraw Semi-Verified Tier Account Validated");
			ExtentReporter.extentLoggerPass("WM_TC_28", "WM_TC_28, CashOut Withdraw Semi-Verified Tier Account Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutFullyVerifiedTier_WM_TC_29(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Withdraw Semi-Verified Tier Account");
		mlWalletLogin(prop.getproperty("Fully_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumberCashOutBranch, getTextVal(MLWalletCashOutPage.objReferenceNumberCashOutBranch, "Reference NUmber"));
			verifyElementPresent(MLWalletCashOutPage.objTransactionNo, getTextVal(MLWalletCashOutPage.objTransactionNo, "Transaction Number"));
			logger.info("WM_TC_29, CashOut Withdraw Fully_Verified Tier Account Validated");
			ExtentReporter.extentLoggerPass("WM_TC_29", "WM_TC_29, CashOut Withdraw Fully_Verified Tier Account Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutMLBranchSemiVerifiedTier_WM_TC_31() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Branch Semi-Verified Tier");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
			String nReference = getText(MLWalletCashOutPage.objReferenceNumber);
			System.out.println(nReference);
			String sReferenceNumber = nReference.substring(5, 16);
			System.out.println(sReferenceNumber);
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
				logger.info("Reference Number is matching with recent Transaction");
				logger.info("WM_TC_31, Successfully Withdraw Money to ML Branch for Semi-verified Tier Account");
				ExtentReporter.extentLoggerPass("WM_TC_31", "WM_TC_31, Successfully Withdraw Money to ML Branch for Semi-verified Tier Account");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashOutMLBranchFullyVerifiedTier_WM_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Branch Fully-Verified Tier");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
			String nReference = getText(MLWalletCashOutPage.objReferenceNumber);
			System.out.println(nReference);
			String sReferenceNumber = nReference.substring(5, 16);
			System.out.println(sReferenceNumber);
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
				logger.info("Reference Number is matching with recent Transaction");
				logger.info("WM_TC_32, Successfully Withdraw Money to ML Branch for Fully-Verified Tier Account");
				ExtentReporter.extentLoggerPass("WM_TC_32", "WM_TC_32, Successfully Withdraw Money to ML Branch for Fully-Verified Tier Account");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutMaxLimitSemiVerifiedTier_WM_TC_33() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Maximum Limit For Semi-verified Tier");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("30000");
		if (verifyElementPresent(MLWalletCashOutPage.objBankMaxLimitTxt, getTextVal(MLWalletCashOutPage.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Bank Cash-out per transaction set for your verification level is P5,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(MLWalletCashOutPage.objUpgradeNowBtn, getTextVal(MLWalletCashOutPage.objUpgradeNowBtn, "Button"));
			logger.info("WM_TC_33, The Maximum Bank Cash-out per transaction Msg for Semi-verified tier Account is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_33", "WM_TC_33, The Maximum Bank Cash-out per transaction Msg for Semi-verified tier Account is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutMaxLimitFullyVerifiedTier_WM_TC_36() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Maximum Limit For Fully-verified Tier");
		mlWalletLogin(prop.getproperty("Fully_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("60000");
		if (verifyElementPresent(MLWalletCashOutPage.objBankMaxLimitTxt, getTextVal(MLWalletCashOutPage.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Bank Cash-out per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(MLWalletCashOutPage.objUpgradeNowBtn, getTextVal(MLWalletCashOutPage.objUpgradeNowBtn, "Button"));
			logger.info("WM_TC_36, The Maximum Bank Cash-out per transaction Msg for Fully-verified tier Account is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_36", "WM_TC_36, The Maximum Bank Cash-out per transaction Msg for Fully-verified tier Account is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutMLBranchMaxLimitSemiVerifiedTier_WM_TC_39() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Branch Maximum Limit For Semi-verified Tier");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("20000");
		if (verifyElementPresent(MLWalletCashOutPage.objBankMaxLimitTxt, getTextVal(MLWalletCashOutPage.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-out per transaction set for your verification level is P5,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(MLWalletCashOutPage.objUpgradeNowBtn, getTextVal(MLWalletCashOutPage.objUpgradeNowBtn, "Button"));
			logger.info("WM_TC_39, The Maximum Branch Cash-out per transaction Msg for Semi-verified tier Account is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_39", "WM_TC_39, The Maximum Bank Cash-out per transaction Msg for Semi-verified tier Account is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutMLBranchMaxLimitFullyVerifiedTier_WM_TC_42() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Branch Maximum Limit For Fully-verified Tier");
		mlWalletLogin(prop.getproperty("Fully_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("60000");
		if (verifyElementPresent(MLWalletCashOutPage.objBankMaxLimitTxt, getTextVal(MLWalletCashOutPage.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-out per transaction set for your verification level is P40,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(MLWalletCashOutPage.objUpgradeNowBtn, getTextVal(MLWalletCashOutPage.objUpgradeNowBtn, "Button"));
			logger.info("WM_TC_42, The Maximum Branch Cash-out per transaction Msg for Fully-verified tier Account is Validated");
			ExtentReporter.extentLoggerPass("WM_TC_42", "WM_TC_42, The Maximum Bank Cash-out per transaction Msg for Fully-verified tier Account is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutWithDrawBankRequiredDetails_WM_TC_47() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Bank Required Details Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"));
		Swipe("UP", 1);
		verifyElementPresentAndClick(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objAccountNumberRequiredMsg, getTextVal(MLWalletCashOutPage.objAccountNumberRequiredMsg, "Error Message"))) {
			String sAccountNumberErrorMsg = getText(MLWalletCashOutPage.objAccountNumberRequiredMsg);
			String sExpectedMsg = "Account Number is required";
			assertionValidation(sAccountNumberErrorMsg, sExpectedMsg);
		}
		type(MLWalletCashOutPage.objAccountNumberField, prop.getproperty("AccountNumber"), "Account Number Text Field");
		Swipe("UP", 1);
		verifyElementPresentAndClick(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objFirstNameRequiredMsg, getTextVal(MLWalletCashOutPage.objFirstNameRequiredMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(MLWalletCashOutPage.objFirstNameRequiredMsg);
			String sExpectedMsg = "First name is required";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		type(MLWalletCashOutPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
		Swipe("UP", 1);
		verifyElementPresentAndClick(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objMiddleNameRequiredMsg, getTextVal(MLWalletCashOutPage.objMiddleNameRequiredMsg, "Error Message"))) {
			String sMiddleNameRequiredMsg = getText(MLWalletCashOutPage.objMiddleNameRequiredMsg);
			String sExpectedMsg = "Middle name is required";
			assertionValidation(sMiddleNameRequiredMsg, sExpectedMsg);
		}
		type(MLWalletCashOutPage.objMiddleName, prop.getproperty("Middle_Name"), "Account Holder Middle Name");
		verifyElementPresentAndClick(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objLastNameRequiredMsg, getTextVal(MLWalletCashOutPage.objLastNameRequiredMsg, "Error Message"))) {
			String sLastNameRequiredMsg = getText(MLWalletCashOutPage.objLastNameRequiredMsg);
			String sExpectedMsg = "Last name is required";
			assertionValidation(sLastNameRequiredMsg, sExpectedMsg);
		}
		type(MLWalletCashOutPage.objLastName, prop.getproperty("Last_Name"), "Account Holder Last Name");
		click(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objEmailAddressRequiredMsg, getTextVal(MLWalletCashOutPage.objEmailAddressRequiredMsg, "Error Message"))) {
			String sLastNameRequiredMsg = getText(MLWalletCashOutPage.objEmailAddressRequiredMsg);
			String sExpectedMsg = "Email address is required";
			assertionValidation(sLastNameRequiredMsg, sExpectedMsg);
		}
		type(MLWalletCashOutPage.objEmailAddress, prop.getproperty("Email"), "Account Holder Email Address");
		click(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objDragonPage, getTextVal(MLWalletCashOutPage.objDragonPage, "Page"))) {
			logger.info("WM_TC_47, Prompt msg for Bank Details data required is validated");
			ExtentReporter.extentLoggerPass("WM_TC_47", "WM_TC_47, Prompt msg for Bank Details data required is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutMiddleNameCheckBoxValidation_WM_TC_48() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank details Middle Name Check Box Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objCheckBox, "I legally do not have a middle name Check Box");
		if (verifyElementPresent(MLWalletCashOutPage.objMiddleName, "Middle Name input Field")) {
			String sMiddleNameFieldDisabled = getAttributValue("enabled", MLWalletCashOutPage.objMiddleName);
			assertionValidation("false", sMiddleNameFieldDisabled);
			logger.info("WM_TC_48, CashOut Bank Middle Name field is disabled After clicking on Check Box Validated");
			ExtentReporter.extentLoggerPass("WM_TC_48", "WM_TC_48, CashOut Bank Middle Name field is disabled After clicking on Check Box Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankPageUIValidation_WM_TC_49() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objSavedBankAccount, getTextVal(MLWalletCashOutPage.objSavedBankAccount, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "Button"));
			verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objBank, getTextVal(MLWalletCashOutPage.objBank, "Bank"));
			verifyElementPresent(MLWalletCashOutPage.objAccountNumberField, "Account Number Input Field");
			verifyElementPresent(MLWalletCashOutPage.objFirstname, "First Name Input Field");
			verifyElementPresent(MLWalletCashOutPage.objMiddleName, "Middle Name Input Field");
			verifyElementPresent(MLWalletCashOutPage.objCheckBox, "I legally do not have a middle name check Box");
			verifyElementPresent(MLWalletCashOutPage.objLastName, "Last Name Input field");
			verifyElementPresent(MLWalletCashOutPage.objEmailAddress, "Email Address Input field");
			Swipe("UP", 1);
			verifyElementPresent(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
			logger.info("WM_TC_49, CashOut Bank Page UI Validated");
			ExtentReporter.extentLoggerPass("WM_TC_49", "WM_TC_49, CashOut Bank Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutDragonPayPageUIValidation_WM_TC_50() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Dragon Pay Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		if (verifyElementPresent(MLWalletCashOutPage.objDragonPage, getTextVal(MLWalletCashOutPage.objDragonPage, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objBankCashOut, getTextVal(MLWalletCashOutPage.objBankCashOut, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objAmountField, "Amount Input Field");
			verifyElementPresent(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
			logger.info("WM_TC_50, CashOut Dragon Pay Page UI Validated");
			ExtentReporter.extentLoggerPass("WM_TC_50", "WM_TC_50, CashOut Dragon Pay Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankReviewTransactionUIValidation_WM_TC_51(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Review Transaction UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
		click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
		Thread.sleep(3000);
		String sDragonPopUpMsg = getText(MLWalletCashOutPage.objDragonPayPopUpMsg);
		String sExpectedMsg = "Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?";
		assertionValidation(sDragonPopUpMsg, sExpectedMsg);
		click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objReviewTransaction, getTextVal(MLWalletCashOutPage.objReviewTransaction, "page"))) {
			verifyElementPresent(MLWalletCashOutPage.objReceiverName, getTextVal(MLWalletCashOutPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletCashOutPage.objBankName, getTextVal(MLWalletCashOutPage.objBankName, "Bank Name"));
			verifyElementPresent(MLWalletCashOutPage.objAccountNumber, getTextVal(MLWalletCashOutPage.objAccountNumber, "Account Number"));
			verifyElementPresent(MLWalletCashOutPage.objAmount, getTextVal(MLWalletCashOutPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletCashOutPage.objServiceFee, getTextVal(MLWalletCashOutPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletCashOutPage.objTotalAmountDeduct, getTextVal(MLWalletCashOutPage.objTotalAmountDeduct, "Total Amount Deduct"));
			verifyElementPresent(MLWalletCashOutPage.objEmailAddressUpdated, getTextVal(MLWalletCashOutPage.objEmailAddressUpdated, "Email Address"));
			Swipe("UP", 2);
			verifyElementPresent(MLWalletCashInBank.objBankTransferCutOffTime, getTextVal(MLWalletCashInBank.objBankTransferCutOffTime, "Message"));
			String sBankTransferTime = getText(MLWalletCashInBank.objBankTransferCutOffTime);
			String sExpectedBankTransferTime = "Bank transfers after 1:00PM are posted on the next banking day.";
			assertionValidation(sBankTransferTime, sExpectedBankTransferTime);
			verifyElementPresent(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "button"));
			logger.info("WM_TC_51, CashOut Bank Review Transaction UI Validated");
			ExtentReporter.extentLoggerPass("WM_TC_51", "WM_TC_51, CashOut Bank Review Transaction UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankTransactionReceiptUIValidation_WM_TC_52() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Transaction Receipt UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			verifyElementPresent(MLWalletCashOutPage.objReceiverNameInTransactionReceipt, getTextVal(MLWalletCashOutPage.objReceiverNameInTransactionReceipt, "Receiver's Name"));
			verifyElementPresent(MLWalletCashOutPage.objBankNamInTransactionReceipt, getTextVal(MLWalletCashOutPage.objBankNamInTransactionReceipt, "Bank Name"));
			verifyElementPresent(MLWalletCashOutPage.objAccountNumberInTransactionReceipt, getTextVal(MLWalletCashOutPage.objAccountNumberInTransactionReceipt, "Account Number"));
			verifyElementPresent(MLWalletCashOutPage.objPrincipalAmount, getTextVal(MLWalletCashOutPage.objPrincipalAmount, "Principal Amount"));
			verifyElementPresent(MLWalletCashOutPage.objServiceFeeInTransactionReceipt, getTextVal(MLWalletCashOutPage.objServiceFeeInTransactionReceipt, "Service Fee"));
			verifyElementPresent(MLWalletCashOutPage.objNetAmount, getTextVal(MLWalletCashOutPage.objNetAmount, "Net Amount"));
			verifyElementPresent(MLWalletCashOutPage.objEmailInTransactionReceipt, getTextVal(MLWalletCashOutPage.objEmailInTransactionReceipt, "Email"));
			Swipe("UP", 2);
			verifyElementPresent(MLWalletCashOutPage.objDate, getTextVal(MLWalletCashOutPage.objDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objType, getTextVal(MLWalletCashOutPage.objType, "Type"));
			verifyElementPresent(MLWalletCashOutPage.objAddToSavedRecipients, getTextVal(MLWalletCashOutPage.objAddToSavedRecipients, "Header"));
			verifyElementPresent(MLWalletCashOutPage.objNickName, "Nick Name Input Field");
			verifyElementPresent(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
			verifyElementPresent(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			verifyElementPresent(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
			logger.info("WM_TC_52, CashOut Bank Transaction Receipt UI Validated");
			ExtentReporter.extentLoggerPass("WM_TC_52", "WM_TC_52, CashOut Bank Transaction Receipt UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutRecentTransactionDetailsUIValidation_WM_TC_53() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Recent Transaction Details UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			scrollToVertical("Back To Home");
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objBank, getTextVal(MLWalletTransactionHistoryPage.objBank, "Bank"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashOut, getTextVal(MLWalletTransactionHistoryPage.objTotalCashOut, "Total Cash Out"));
				logger.info("WM_TC_53, CashOut Recent Transaction Details UI Validated");
				ExtentReporter.extentLoggerPass("WM_TC_53", "WM_TC_53, CashOut Recent Transaction Details UI Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutMLBranchBackToHomeBtnValidation_WM_TC_54() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Branch Back To Home Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"));
		verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
		verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("WM_TC_54, CashOut ML Branch, After Clicking On Back To Home Button Application Navigated to Home page Validated");
			ExtentReporter.extentLoggerPass("WM_TC_54", "WM_TC_54, CashOut ML Branch, After Clicking On Back To Home Button Application Navigated to Home page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutMLBranchNewTransactionBtnValidation_WM_TC_55() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut ML Branch New Transaction Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"));
		verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
		verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
			logger.info("WM_TC_55, CashOut ML Branch, After Clicking On New Transaction Button Application Navigated to CashOut/WithDraw Page Validated");
			ExtentReporter.extentLoggerPass("WM_TC_55", "WM_TC_55, CashOut ML Branch, After Clicking On New Transaction Button Application Navigated to CashOut/WithDraw Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cahOutBankViewAllPageBackBtnValidation_WM_TC_56() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank, View All Page Back Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "button"));
		waitTime(3000);
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBackBtn, "Back Button in ViewAll page");
		if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
			logger.info("WM_TC_56, CashOut Bank, View All Page Back Button Validated");
			ExtentReporter.extentLoggerPass("WM_TC_56", "WM_TC_56, CashOut Bank, View All Page Back Button Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankSavedRecipientEditBackButton_WM_TC_57() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut Bank Saved Recipient Edited changes are unsaved upon back navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "button"));
		waitTime(3000);
		verifyElementPresent(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, getTextVal(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, "Account Number in Saved Bank Account"));
		String sAccountNumberInSavedBankAccount = getText(MLWalletCashOutPage.objAccountNumberInSavedBankAccount);
		verifyElementPresentAndClick(MLWalletCashOutPage.objThreeDotMenuButton, "Three Dot menu Button");
		verifyElementPresentAndClick(MLWalletCashOutPage.objEditBtn, getTextVal(MLWalletCashOutPage.objEditBtn, "Button"));
		type(MLWalletCashOutPage.objAccountNumberInEditRecipient, prop.getproperty("EditedAccountNumber"), "Account Number In Edit Recipient");
		click(MLWalletCashOutPage.objEditRecipientBackBtn, "Edit Recipient Back Button");
		verifyElementPresent(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, getTextVal(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, "Account Number in Saved Bank Account"));
		String sAccountNumberAfterNavigatingBack = getText(MLWalletCashOutPage.objAccountNumberInSavedBankAccount);
		assertionValidation(sAccountNumberInSavedBankAccount, sAccountNumberAfterNavigatingBack);
		if (sAccountNumberInSavedBankAccount.equals(sAccountNumberAfterNavigatingBack)) {
			logger.info("WM_TC_57, CashOut Bank Saved Recipient Edited changes are unsaved upon back navigation validated");
			ExtentReporter.extentLoggerPass("WM_TC_57", "WM_TC_57, CashOut Bank Saved Recipient Edited changes are unsaved upon back navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankEditRecipient_WM_TC_58() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Edit Recipient");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "button"));
		waitTime(3000);
		verifyElementPresent(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, getTextVal(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, "Account Number in Saved Bank Account"));
		String sAccountNumberInSavedBankAccount = getText(MLWalletCashOutPage.objAccountNumberInSavedBankAccount);
		verifyElementPresentAndClick(MLWalletCashOutPage.objThreeDotMenuButton, "Three Dot menu Button");
		verifyElementPresentAndClick(MLWalletCashOutPage.objEditBtn, getTextVal(MLWalletCashOutPage.objEditBtn, "Button"));
		type(MLWalletCashOutPage.objAccountNumberInEditRecipient, prop.getproperty("EditedAccountNumber"), "Account Number In Edit Recipient");
		click(MLWalletCashOutPage.objConfirmBtn, getTextVal(MLWalletCashOutPage.objConfirmBtn, "Button"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objCheckBox, "Middle Name Check Box");
		verifyElementPresentAndClick(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "button"));
		waitTime(3000);
		verifyElementPresent(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, getTextVal(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, "Account Number in Saved Bank Account"));
		String sEditedAccountNumberInSavedBankAccount = getText(MLWalletCashOutPage.objAccountNumberInSavedBankAccount);
		assertNotEquals(sAccountNumberInSavedBankAccount, sEditedAccountNumberInSavedBankAccount);
		if (sAccountNumberInSavedBankAccount != sEditedAccountNumberInSavedBankAccount) {
			logger.info("WM_TC_58, CashOut/Withdraw Edit Recipient validated");
			ExtentReporter.extentLoggerPass("WM_TC_58", "WM_TC_58, CashOut/Withdraw Edit Recipient validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankDeleteRecipient_WM_TC_59() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Delete Recipient");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		verifyElementPresentAndClick(MLWalletCashOutPage.objViewAllBtn, getTextVal(MLWalletCashOutPage.objViewAllBtn, "button"));
		waitTime(3000);
		verifyElementPresent(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, getTextVal(MLWalletCashOutPage.objAccountNumberInSavedBankAccount, "Account Number in Saved Bank Account"));
		String sAccountNumberInSavedBankAccount = getText(MLWalletCashOutPage.objAccountNumberInSavedBankAccount);
		verifyElementPresentAndClick(MLWalletCashOutPage.objThreeDotMenuButton, "Three Dot menu Button");
		verifyElementPresentAndClick(MLWalletCashOutPage.objDeleteBtn, getTextVal(MLWalletCashOutPage.objDeleteBtn, "Button"));
		verifyElementNotPresent(MLWalletCashOutPage.objAccountNumber(sAccountNumberInSavedBankAccount), "Account Number", 5);
		logger.info("WM_TC_59, CashOut/Withdraw Delete Recipient validated");
		ExtentReporter.extentLoggerPass("WM_TC_59", "WM_TC_59, CashOut/Withdraw Delete Recipient validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void cashOutMLBranchOTPPageUiValidation_WM_TC_60() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/WithDraw ML Branch OTP Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("WM_TC_60, CashOut/WithDraw ML Branch OTP Page UI Validated");
			ExtentReporter.extentLoggerPass("WM_TC_60", "WM_TC_20, CashOut/WithDraw ML Branch OTP Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutMLBranchTransactionDetailsUIValidation_WM_TC_61() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/WithDraw ML Branch Transaction Details UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
			click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashOut, getTextVal(MLWalletTransactionHistoryPage.objTotalCashOut, "Total Cash Out"));
				logger.info("WM_TC_61, CashOut/WithDraw ML Branch Transaction Details UI Validated");
				ExtentReporter.extentLoggerPass("WM_TC_61", "WM_TC_61, CashOut/WithDraw ML Branch Transaction Details UI Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutBankBackHomeBtnValidation_WM_TC_62() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank, Back to Home Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			scrollToVertical("Back To Home");
			verifyElementPresentAndClick(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
			if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
				logger.info("WM_TC_62, CashOut/Withdraw Bank, After Clicking on Back To Home Button, Application should navigate to Home Page is Validated");
				ExtentReporter.extentLoggerPass("WM_TC_62", "WM_TC_62, CashOut/Withdraw Bank, After Clicking on Back To Home Button, Application should navigate to Home Page is Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutBankNewTransactionBtnValidation_WM_TC_63() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank, New Transaction Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
			verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
			scrollToVertical("New Transaction");
			verifyElementPresentAndClick(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
			if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
				logger.info("WM_TC_63, CashOut/Withdraw Bank, After Clicking on New Transaction Button, Application should navigate to CashOut/WithDraw Page is Validated");
				ExtentReporter.extentLoggerPass("WM_TC_63", "WM_TC_63, CashOut/Withdraw Bank, After Clicking on New Transaction Button, Application should navigate to CashOut/WithDraw Page is Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutBankDragonPayMessageValidation_WM_TC_64(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/WithDraw Bank Dragon Pay Message Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		verifyElementPresent(MLWalletCashOutPage.objAmountField, "Bank Cash Out Amount Field");
		type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
		click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
		Thread.sleep(3000);
		if (verifyElementPresent(MLWalletCashOutPage.objDragonPayPopUpMsg, getTextVal(MLWalletCashOutPage.objDragonPayPopUpMsg, "Dragon Pay Message"))) {
			String sDragonPopUpMsg = getText(MLWalletCashOutPage.objDragonPayPopUpMsg);
			String sExpectedMsg = "Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?";
			assertionValidation(sDragonPopUpMsg, sExpectedMsg);
			logger.info("WM_TC_64, CashOut/WithDraw Bank Dragon Pay Message Validated");
			ExtentReporter.extentLoggerPass("WM_TC_64", "WM_TC_64, CashOut/WithDraw Bank Dragon Pay Message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankReviewTransactionTotalAmountValidation_WM_TC_65(String sAmount, int serviceFee) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank, Review Transaction Total Amount Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		verifyElementPresent(MLWalletCashOutPage.objAmountField, "Bank Cash Out Amount Field");
		type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
		click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
		Thread.sleep(3000);
		String sDragonPopUpMsg = getText(MLWalletCashOutPage.objDragonPayPopUpMsg);
		String sExpectedMsg = "Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?";
		assertionValidation(sDragonPopUpMsg, sExpectedMsg);
		click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
		verifyElementPresent(MLWalletCashOutPage.objReviewTransaction, getTextVal(MLWalletCashOutPage.objReviewTransaction, "page"));
		verifyElementPresent(MLWalletCashOutPage.objTotalAmountDeduct, getTextVal(MLWalletCashOutPage.objTotalAmountDeduct, "Total Amount Deduct"));
		String sTotalAmountDeductWithPHP = getText(MLWalletCashOutPage.objTotalAmountDeduct);
		System.out.println(sTotalAmountDeductWithPHP);
		String sActualTotalAmountDeduct = sTotalAmountDeductWithPHP.substring(4, 7);
		logger.info("Actual Total Amount Deduct displayed : " + sActualTotalAmountDeduct);
		int nAmount = Integer.parseInt(sAmount);
		int nExceptedTotalAmountDeduct = nAmount + serviceFee;
		String sExceptedTotalAmount = Integer.toString(nExceptedTotalAmountDeduct);
		logger.info("Expected Total Amount Deduct : " + sExceptedTotalAmount);
		assertionValidation(sActualTotalAmountDeduct, sExceptedTotalAmount);
		if (sActualTotalAmountDeduct.equals(sExceptedTotalAmount)) {
			logger.info("WM_TC_65, CashOut/Withdraw Bank, Review Transaction Total Amount Validated");
			ExtentReporter.extentLoggerPass("WM_TC_65", "WM_TC_65, CashOut/Withdraw Bank, Review Transaction Total Amount Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankLocationPopupValidation_WM_TC_69(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Location Popup Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("WM_TC_69, CashOut/Withdraw Bank Location popup Validated");
			ExtentReporter.extentLoggerPass("WM_TC_69", "WM_TC_69, CashOut/Withdraw Bank Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankLocationDenyFunctionality_WM_TC_70(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Location Deny Functionality");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		waitTime(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("WM_TC_70, CashOut/Withdraw Bank Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("WM_TC_70", "WM_TC_70, CashOut/Withdraw Bank Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBankLocationPermissionDenyCloseBtnFunctionality_WM_TC_71(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Location Permission Deny Close Button Functionality");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		waitTime(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletCashOutPage.objDragonPage,getTextVal(MLWalletCashOutPage.objDragonPage,"Page"))){
				logger.info("WM_TC_71, CashOut/Withdraw Bank Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_71", "WM_TC_71, CashOut/Withdraw Bank Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutBankLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_72(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Location Permission Deny open Settings Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		waitTime(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(MLWalletCashOutPage.objAppInfo, getTextVal(MLWalletCashOutPage.objAppInfo, "Page"))) {
				logger.info("WM_TC_72, CashOut/Withdraw Bank Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_72", "WM_TC_72, CashOut/Withdraw Bank Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashOutBankLocationPopUpAllowFunctionality_WM_TC_73(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Location Bank popup Allow Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		waitTime(3000);
		enterAmountBank(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("WM_TC_73, CashOut/Withdraw Bank Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_73", "WM_TC_73, CashOut/Withdraw Bank Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashOutBranchLocationPopupValidation_WM_TC_74(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Location Popup Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("WM_TC_74, CashOut/Withdraw Branch Location popup Validated");
			ExtentReporter.extentLoggerPass("WM_TC_74", "WM_TC_74, CashOut/Withdraw Branch Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutBranchLocationDenyFunctionality_WM_TC_75(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Location Deny Functionality");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("WM_TC_75, CashOut/Withdraw Branch Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("WM_TC_75", "WM_TC_75, CashOut/Withdraw Branch Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashOutBranchLocationPermissionDenyCloseBtnFunctionality_WM_TC_76(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Location Permission Deny Close Button Functionality");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw,getTextVal(MLWalletCashOutPage.objCashOutWithdraw,"Page"))){
				logger.info("WM_TC_76, CashOut/Withdraw Branch Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_76", "WM_TC_76, CashOut/Withdraw Branch Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashOutBranchLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_77(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Location Permission Deny open Settings Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(MLWalletCashOutPage.objAppInfo, getTextVal(MLWalletCashOutPage.objAppInfo, "Page"))) {
				logger.info("WM_TC_77, CashOut/Withdraw Branch Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_77", "WM_TC_77, CashOut/Withdraw Branch Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashOutBranchLocationPopUpAllowFunctionality_WM_TC_78(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Location popup Allow Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("WM_TC_78, CashOut/Withdraw Branch Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("WM_TC_78", "WM_TC_78, CashOut/Withdraw Branch Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}



	public void cashOutBankInternetInterruptionWhileEnteringOTP_WM_TC_79(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Internet Interruption While Entering OTP Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		waitTime(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin,10);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("WM_TC_79,CashOut/Withdraw Bank Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("WM_TC_78", "WM_TC_78, CashOut/Withdraw Bank Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}



	public void cashOutBranchInternetInterruptionWhileEnteringOTP_WM_TC_80(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Internet Interruption While Entering OTP Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch(sAmount);
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin,10);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("WM_TC_80,CashOut/Withdraw Branch Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("WM_TC_80", "WM_TC_80, CashOut/Withdraw Branch Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}


	public void cashOutBankTransactionValidationAfterMinimizingApp_WM_TC_83(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Bank Transaction Validation After Minimizing App");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		cashOutSelectBank(prop.getproperty("Valid_BankName"));
		enterBankDetails(prop.getproperty("AccountNumber"));
		Thread.sleep(3000);
		enterAmountBank(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if(verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"))){
			logger.info("WM_TC_83, CashOut/Withdraw Bank Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("WM_TC_83", "WM_TC_83, CashOut/Withdraw Bank Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashOutBranchTransactionValidationAfterMinimizingApp_WM_TC_91() throws Exception {
		ExtentReporter.HeaderChildNode("CashOut/Withdraw Branch Transaction Validation After Minimizing App");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
		enterAmountMLBranch("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
			logger.info("WM_TC_91, CashOut/Withdraw Branch Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("WM_TC_91", "WM_TC_91, CashOut/Withdraw Branch Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

















//================================ Send/Transfer To any ML Branch ============================================//
//=============================== General methods For send transfer ============================================//

	public void sendMoneyToAnyMLBranch(String sTier) throws Exception {
		mlWalletLogin(sTier);
		click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
		verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
		if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
			click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
		}
	}

	public void enterMLBranchDetails() throws Exception {
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
			verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
			type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
			type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
			click(SendTransferPage.objCheckBox, "Check Box");
			waitTime(3000);
			type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
			type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		}
	}

	public void enterAmountToKwartaPadala(String nAmount) throws Exception {
		waitTime(5000);

		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
		Thread.sleep(3000);
		click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
		verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
		click(SendTransferPage.objConfirmBtn, getTextVal(SendTransferPage.objConfirmBtn, "Button"));
	}

	public void selectSavedRecipient() throws Exception {
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
			click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
			explicitWaitVisible(SendTransferPage.objSavedRecipients, 5);
			click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Page"));
			type(SendTransferPage.objSearchRecipient, prop.getproperty("Last_Name"), "Search Recipient Text Field");
			verifyElementPresent(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), "Recipient"));
			click(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), "Recipient"));
			Thread.sleep(3000);
		}
	}

	public void addRecipient() throws Exception {
		if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"))) {
			click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
			click(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
			explicitWaitVisible(SendTransferPage.objAddRecipient, 5);
			type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
			type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
			click(SendTransferPage.objCheckBox, "Check Box");
			type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
			type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Last Name Text Field");
			type(SendTransferPage.objNickName, prop.getproperty("Nick_Name"), "Nick Name Text Field");
			click(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
		}
	}

	public void sendMoneyToMLBranchRatesValidation(String sAmount) throws Exception {
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		waitTime(5000);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objAmountTxtField, sAmount, "Amount text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
		Thread.sleep(3000);
		click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
		waitTime(5000);
		verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
	}


	//===============================================================================================================//
	public void sendMoneyToMLBranch_STB_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			String sReference = getText(SendTransferPage.objReferenceNumber);
			System.out.println(sReference);
			String sReferenceNumber = sReference.substring(9, 20);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objKwartaPadala, getTextVal(MLWalletHomePage.objKwartaPadala, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
				logger.info("STB_TC_01, Successfully sent Amount to ML Branch and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("STB_TC_01", "STB_TC_01, Successfully sent Amount to ML Branch and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void sendMoneyRequiredDetails_STB_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Invalid Bank Details");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objFirstNameRequiredMsg, getTextVal(SendTransferPage.objFirstNameRequiredMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objFirstNameRequiredMsg);
			String sExpectedMsg = "First name is required";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		hideKeyboard();
		type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
		type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objMiddleNameRequiredMsg, getTextVal(SendTransferPage.objMiddleNameRequiredMsg, "Error Message"))) {
			String sMiddleNameRequiredMsg = getText(SendTransferPage.objMiddleNameRequiredMsg);
			String sExpectedMsg = "Middle name is required";
			assertionValidation(sMiddleNameRequiredMsg, sExpectedMsg);
		}
		waitTime(3000);
		type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objLastNameRequiredMsg, getTextVal(SendTransferPage.objLastNameRequiredMsg, "Error Message"))) {
			String sLastNameRequiredMsg = getText(SendTransferPage.objLastNameRequiredMsg);
			String sExpectedMsg = "Last name is required";
			assertionValidation(sLastNameRequiredMsg, sExpectedMsg);
		}
		waitTime(3000);
		type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
		type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objMobileNumberRequiredMsg, getTextVal(SendTransferPage.objMobileNumberRequiredMsg, "Error Message"))) {
			String sMobileNumberRequiredMsg = getText(SendTransferPage.objMobileNumberRequiredMsg);
			String sExpectedMsg = "Mobile number is required";
			assertionValidation(sMobileNumberRequiredMsg, sExpectedMsg);
		}
		type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Last Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "page"))) {
			logger.info("STB_TC_08, Prompt msg for Receiver's Details required is validated");
			ExtentReporter.extentLoggerPass("STB_TC_08", "STB_TC_08, Prompt msg for Receiver's Details required is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyInvalidDetails_STB_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Invalid Bank Details");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objFirstname, prop.getproperty("Invalid_First_Name"), "First Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objFirstNameErrorMsg, getTextVal(SendTransferPage.objFirstNameErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objFirstNameErrorMsg);
			String sExpectedMsg = "First name must only contain letters and spaces";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
		type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");

		type(SendTransferPage.objMiddleName, prop.getproperty("Invalid_Middle_Name"), "Middle Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objMiddleNameErrorMsg, getTextVal(SendTransferPage.objMiddleNameErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objMiddleNameErrorMsg);
			String sExpectedMsg = "Middle name must only contain letters and spaces";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		click(SendTransferPage.objCheckBox, "Check Box");
		Swipe("UP", 1);
		type(SendTransferPage.objLastName, prop.getproperty("Invalid_Last_Name"), "Last Name Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objLastNameErrorMsg, getTextVal(SendTransferPage.objLastNameErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objLastNameErrorMsg);
			String sExpectedMsg = "Last name must only contain letters and spaces";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
		type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");


		type(SendTransferPage.objMobileNumber, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objMobileNumberErrorMsg, getTextVal(SendTransferPage.objMobileNumberErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objMobileNumberErrorMsg);
			String sExpectedMsg = "Mobile number is invalid";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
		}
		clearField(SendTransferPage.objMobileNumber, "Mobile Number Text Field");
		type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "page"))) {
			logger.info("STB_TC_07, Prompt msg for Receiver's Details Invalid is validated");
			ExtentReporter.extentLoggerPass("STB_TC_07", "STB_TC_07, Prompt msg for Receiver's Details Invalid is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyAddRecipient_STB_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		addRecipient();
		type(SendTransferPage.objSearchRecipient, prop.getproperty("Last_Name"), "Search Recipient Text Field");
		if (verifyElementPresent(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), "Recipient"))) {
			logger.info("STB_TC_03, The Added Recipient is displayed in Saved Recipient Page");
			ExtentReporter.extentLoggerPass("STB_TC_03", "STB_TC_03, The Added Recipient is displayed in Saved Recipient Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToSavedRecipient_STB_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		selectSavedRecipient();
		waitTime(3000);
		click(SendTransferPage.objSelectRecipient, getTextVal(SendTransferPage.objSelectRecipient, "Button"));
		click(SendTransferPage.objCheckBox, "Check Box");
		Swipe("UP", 1);
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		waitTime(5000);
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			String sReference = getText(SendTransferPage.objReferenceNumber);
			System.out.println(sReference);
			String sReferenceNumber = sReference.substring(9, 20);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			waitTime(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objKwartaPadala, getTextVal(MLWalletHomePage.objKwartaPadala, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
				logger.info("STB_TC_02, Successfully sent Amount to saved Recipient and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("STB_TC_02", "STB_TC_02, Successfully sent Amount to saved Recipient and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyContactDuplicate_STB_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Contact Duplicate");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		addRecipient();
		if (verifyElementPresent(SendTransferPage.objContactAlreadyExistMsg, getTextVal(SendTransferPage.objContactAlreadyExistMsg, "Error Message"))) {
			String sContactDuplicatePopupMsg = getText(SendTransferPage.objContactAlreadyExistMsg);
			String sExpectedPopupMsg = "Contact already exists.";
			assertionValidation(sContactDuplicatePopupMsg, sExpectedPopupMsg);
			logger.info("STB_TC_04, Contact already exists popup message Validated");
			ExtentReporter.extentLoggerPass("STB_TC_04", "STB_TC_04, Contact already exists popup message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyEditRecipient_STB_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		selectSavedRecipient();
		click(SendTransferPage.objEditRecipient, getTextVal(SendTransferPage.objEditRecipient, "Button"));
		type(SendTransferPage.objEditRecipientLastName, prop.getproperty("Edited_Last_name"), "Last Name Text Field");
		click(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
		type(SendTransferPage.objSearchRecipient, prop.getproperty("Edited_Last_name"), "Search Recipient Text Field");
		if (verifyElementPresent(SendTransferPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")), "Recipient"))) {
			logger.info("STB_TC_06, Successfully edited the Saved Recipient");
			ExtentReporter.extentLoggerPass("STB_TC_06", "STB_TC_06, Successfully edited the Saved Recipient");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyDeleteRecipient_STB_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		selectSavedRecipient();
		click(SendTransferPage.objDeleteRecipient, getTextVal(SendTransferPage.objDeleteRecipient, "Button"));
		verifyElementPresent(SendTransferPage.objPopupMsg, getTextVal(SendTransferPage.objPopupMsg, "Pop Up message"));
		String sDeleteConfirmationPopup = getText(SendTransferPage.objPopupMsg);
		String sExceptedMsg = "Are you sure you want to remove this saved recipient?";
		assertionValidation(sDeleteConfirmationPopup, sExceptedMsg);
		click(SendTransferPage.objRemoveBtn, getTextVal(SendTransferPage.objRemoveBtn, "Button"));
		clearField(SendTransferPage.objSearchRecipient, "Search Field");
		Thread.sleep(3000);
		if (verifyElementNotPresent(SendTransferPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")), "Saved Recipient", 5)) {
			logger.info("STB_TC_05, Saved Recipient from Saved Recipients page not got deleted Successfully");
		} else {
			logger.info("STB_TC_05, Saved Recipient from Saved Recipients page deleted Successfully");
			ExtentReporter.extentLoggerPass("STB_TC_05", "STB_TC_05, Saved Recipient from Saved Recipients page deleted Successfully");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyInvalidAmount_STB_TC_09(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objAmountTxtField, Amount, "Amount text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objInvalidAmountMsg, getTextVal(SendTransferPage.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(SendTransferPage.objInvalidAmountMsg);
			String sExpectedErrorMsg = "The amount should not be less than 1";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("STB_TC_09, The amount should not be less than 1 - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_09", "STB_TC_09, The amount should not be less than 1 - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyInsufficientAmount_STB_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified_ELoad_LowBalance"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("35000");
		if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg, getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
			String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
			String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
			assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
			logger.info("STB_TC_10, Insufficient Balance - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_10", "STB_TC_10, Insufficient Balance - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMaximumAmount_STB_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100000");
		if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			logger.info("STB_TC_12, The maximum send money per transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_12", "STB_TC_12, The maximum send money per transaction - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

//================================= Phase 2 ==================================================================//


	public void sendTransferUIValidation_STB_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Send/Transfer page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
			verifyElementPresent(SendTransferPage.objSendWalletOptions, getTextVal(SendTransferPage.objSendWalletOptions, "Header"));
			verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "option"));
			verifyElementPresent(SendTransferPage.objToAMLWalletUser, getTextVal(SendTransferPage.objToAMLWalletUser, "option"));
			logger.info("STB_TC_13, Send/Transfer page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_13", "STB_TC_13, Send/Transfer page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchUIValidation_STB_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to ML Branch page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
			verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
			verifyElementPresent(SendTransferPage.objFirstname, "First Name Input Field");
			verifyElementPresent(SendTransferPage.objMiddleName, "Middle Name Input Field");
			verifyElementPresent(SendTransferPage.objCheckBox, "Check box to Receiver legally does not have middle Name");
			verifyElementPresent(SendTransferPage.objLastName, "Last Name Input Field");
			verifyElementPresent(SendTransferPage.objMobileNumber, "Mobile Number Input Field");
			verifyElementPresent(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			logger.info("STB_TC_14, Send Money to ML Branch page UI Validation");
			ExtentReporter.extentLoggerPass("STB_TC_14", "STB_TC_14, Send Money to ML Branch page UI Validation");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchSaveRecipientPageUIValidation_STB_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Save Recipient Page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresentAndClick(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
		if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Page"))) {
			verifyElementPresent(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
			verifyElementPresent(SendTransferPage.objSelectRecipient, getTextVal(SendTransferPage.objSelectRecipient, "Header"));
			verifyElementPresent(SendTransferPage.objSearchRecipient, "Search Recipient Input Field");
			if (verifyElementDisplayed(SendTransferPage.objSavedRecipientsList)) {
				List<WebElement> values = findElements(SendTransferPage.objSavedRecipientsList);
				for (int i = 0; i < values.size(); i++) {
					String savedRecipientName = values.get(i).getText();
					logger.info("Saved Recipient : " + savedRecipientName + " is displayed");
					ExtentReporter.extentLogger(" ", "Saved Recipient : " + savedRecipientName + " is displayed");
				}
			} else if (verifyElementPresent(SendTransferPage.objNoRecentTransactionTxt, getTextVal(SendTransferPage.objNoRecentTransactionTxt, "Text"))) {
				logger.info("No Saved Recipients are present");
			}
			logger.info("STB_TC_15, Send Money To Branch Save Recipient Page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_15", "STB_TC_15, Send Money To Branch Save Recipient Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToBranchSuccessUIValidation_STB_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Success UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			Swipe("UP", 1);
			verifyElementPresent(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			verifyElementPresent(SendTransferPage.objNewTransaction, getTextVal(SendTransferPage.objNewTransaction, "Button"));
			logger.info("STB_TC_16, Send Money To Branch Success page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_16", "STB_TC_16, Send Money To Branch Success page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchConfirmDetailsPageUIValidation_STB_TC_17(String nAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Confirm Details Page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
		Thread.sleep(3000);
		click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
		if (verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"))) {
			verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			verifyElementPresent(SendTransferPage.objConfirmBtn, getTextVal(SendTransferPage.objConfirmBtn, "Button"));
			logger.info("STB_TC_17, Send Money To Branch Confirm Details Page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_17", "STB_TC_17, Send Money To Branch Confirm Details Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchSelectPaymentMethodPageUIValidation_STB_TC_18(String nAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Select Payment Method Page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
		type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"))) {
			verifyElementPresent(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
			verifyElementPresent(SendTransferPage.objAvailableBalance, getTextVal(SendTransferPage.objAvailableBalance, "Available PHP"));
			logger.info("STB_TC_18, Send Money To Branch Select Payment Method Page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_18", "STB_TC_18, Send Money To Branch Select Payment Method Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchEnterAmountPageUIValidation_STB_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Enter Amount Page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
		if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
			verifyElementPresent(SendTransferPage.objAmountToSend, getTextVal(SendTransferPage.objAmountToSend, "Header"));
			verifyElementPresent(SendTransferPage.objAmountTxtField, "Amount Input Field");
			verifyElementPresent(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			logger.info("STB_TC_19, Send Money To Branch Enter Amount Page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_19", "STB_TC_19, Send Money To Branch Enter Amount Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void kwartaPadalaTransactionDetailsUIValidation_STB_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Kwarta Padala Transaction Details UI Validation");
		sendMoneyToMLBranch_STB_TC_01();
		if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"))) {
			verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Text"));
			verifyElementPresent(SendTransferPage.objKwartaPadalaDate, getTextVal(SendTransferPage.objKwartaPadalaDate, "Kwarta Padala Date"));
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			logger.info("STB_TC_20, Kwarta Padala Transaction Details page UI Validated");
			ExtentReporter.extentLoggerPass("STB_TC_20", "STB_TC_20, Kwarta Padala Transaction Details page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToBranchAddRecipientPageUIValidation_STB_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Branch Add Recipient Page UI Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"))) {
			click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
			click(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
			explicitWaitVisible(SendTransferPage.objAddRecipient, 5);
			if (verifyElementPresent(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Page"))) {
				verifyElementPresent(SendTransferPage.objFirstname, "First Name Text Field");
				verifyElementPresent(SendTransferPage.objMiddleName, "Middle Name Text Field");
				verifyElementPresent(SendTransferPage.objCheckBox, "Check Box");
				verifyElementPresent(SendTransferPage.objLastName, "Last Name Text Field");
				verifyElementPresent(SendTransferPage.objMobileNumber, "Last Name Text Field");
				verifyElementPresent(SendTransferPage.objNickName, "Nick Name Text Field");
				verifyElementPresent(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
				logger.info("STB_TC_21, Send Money To Branch Add Recipient Page UI Validated");
				ExtentReporter.extentLoggerPass("STB_TC_21", "STB_TC_21, Send Money To Branch Add Recipient Page UI Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void sendMoneyToMLBranchBuyerTierAccount_STB_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Buyer Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Buyer_Tier"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "Send Money is not allowed for customers at this verification level. Please upgrade your account to use this service.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STB_TC_22, Send Money is not allowed for customers at this Buyer tier - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_22", "STB_TC_22, Send Money is not allowed for customers at this Buyer tier  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchSemiVerifiedTierAccount_STB_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Semi verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Semi_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			logger.info("STB_TC_23, Send Money To ML Branch, Semi verified Tier Account transaction validated");
			ExtentReporter.extentLoggerPass("STB_TC_23", "STB_TC_23, Send Money To ML Branch, Semi verified Tier Account transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchBranchVerifiedAccount_STB_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			logger.info("STB_TC_24, Send Money To ML Branch, Branch verified Tier Account transaction validated");
			ExtentReporter.extentLoggerPass("STB_TC_24", "STB_TC_24, Send Money To ML Branch, Branch verified Tier Account transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchFullyVerifiedAccount_STB_TC_25() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Fully_verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
			verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
			logger.info("STB_TC_25, Send Money To ML Branch, Branch verified Tier Account transaction validated");
			ExtentReporter.extentLoggerPass("STB_TC_25", "STB_TC_25, Send Money To ML Branch, Branch verified Tier Account transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchSemiVerifiedTierAccountMaxAmount_STB_TC_26() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Semi verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Semi_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("20000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P10,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STB_TC_26, Send Money To ML Branch, Semi verified Tier Account Maximum Transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_26", "STB_TC_26, Send Money To ML Branch, Semi verified Tier Account Maximum Transaction  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchBranchVerifiedTierAccountMaxAmount_STB_TC_29() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("60000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STB_TC_29, Send Money To ML Branch, Branch verified Tier Account Maximum Transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_29", "STB_TC_29, Send Money To ML Branch, Branch verified Tier Account Maximum Transaction  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchFullyVerifiedTierAccountMaxAmount_STB_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch, Fully verified Tier Account");
		sendMoneyToAnyMLBranch(prop.getproperty("Fully_verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("60000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STB_TC_32, Send Money To ML Branch, Fully verified Tier Account Maximum Transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_32", "STB_TC_32, Send Money To ML Branch, Fully verified Tier Account Maximum Transaction  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void kwartaPadalaRates_STB_TC_35() throws Exception {
		ExtentReporter.HeaderChildNode("Kwarta Padala Rates");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresentAndClick(SendTransferPage.objKwartaPadalaRatesBtn, "Kwarta Padala Rates");
		if (verifyElementPresent(SendTransferPage.objKwartaPadalaRates, "Kwarta Padala Rates")) {
			List<WebElement> values = findElements(SendTransferPage.objKwartaPadalaRates);
			for (int i = 0; i < values.size(); i++) {
				if (i % 2 != 0) {
					String sRates = values.get(i).getText();
					logger.info("Rates : " + sRates + " is displayed");
					ExtentReporter.extentLogger(" ", "Rates : " + sRates + " is displayed");
				}
				if (i % 2 == 0) {
					String sAmountRange = values.get(i).getText();
					logger.info("Amount Range : " + sAmountRange + " is displayed");
					ExtentReporter.extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
				}
			}
			logger.info("STB_TC_35, Kwarta Padala Rates validated");
			ExtentReporter.extentLoggerPass("STB_TC_35", "STB_TC_35, Kwarta Padala Rates validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioOne_STB_TC_36(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP0.50 for PHP0.01 to PHP50.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 0.50";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_36, Send Money To ML Branch charged PHP0.50 for 0.01 to PHP50.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_36", "STB_TC_36, Send Money To ML Branch charged PHP0.50 for PHP0.01 to PHP50.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioTwo_STB_TC_37(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 1.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_36, Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_36", "STB_TC_36, Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioThree_STB_TC_38(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 2.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_38, Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_38", "STB_TC_38, Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioFour_STB_TC_39(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 3.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_39, Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_39", "STB_TC_39, Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioFive_STB_TC_40(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 5.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_40, Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_40", "STB_TC_40, Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioSix_STB_TC_41(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP8.00 for PHP500.01 to PHP600.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 8.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_41, Send Money To ML Branch charged PHP5.00 for PHP8.00 to PHP600.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_41", "STB_TC_41, Send Money To ML Branch charged PHP8.00 for PHP500.01 to PHP600.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioSeven_STB_TC_42(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 10.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_42, Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_42", "STB_TC_42, Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioEight_STB_TC_43(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 12.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_43, Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_43", "STB_TC_43 , Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioNine_STB_TC_44(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 15.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_44, Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_44", "STB_TC_44 , Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioTen_STB_TC_45(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 20.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_45, Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_45", "STB_TC_45 , Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioEleven_STB_TC_46(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 30.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_46, Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_46", "STB_TC_46 , Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchRatesValidationScenarioTwelve_STB_TC_47(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 40.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_47, Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_47", "STB_TC_47 , Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchRatesValidationScenarioThirteen_STB_TC_48(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00");
		sendMoneyToMLBranchRatesValidation(sAmount);
		if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
			String sServiceFee = getText(SendTransferPage.objServiceFee);
			String sExceptedServiceFee = "Php 500.00";
			assertionValidation(sServiceFee, sExceptedServiceFee);
			logger.info("STB_TC_48, Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00 validated");
			ExtentReporter.extentLoggerPass("STB_TC_48", "STB_TC_48 , Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00 validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchLocationPopupValidation_STB_TC_50() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Location popup Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("STB_TC_50, Send Money To ML Branch Location popup Validated");
			ExtentReporter.extentLoggerPass("STB_TC_50", "STB_TC_50, Send Money To ML Branch Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchLocationDenyFunctionality_STB_TC_51() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Location Deny Functionality Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("STB_TC_51, Send Money To ML Branch Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("STB_TC_51", "STB_TC_51, Send Money To ML Branch Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLBranchLocationPermissionDenyCloseBtnFunctionality_STB_TC_52() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Location Permission Deny Close Button Functionality Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(SendTransferPage.objKwartaPadala,getTextVal(SendTransferPage.objKwartaPadala,"Page"))){
				logger.info("STB_TC_52, Send Money To ML Branch Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STB_TC_52", "STB_TC_52, Send Money To ML Branch Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void sendMoneyToMLBranchLocationPermissionDenyOpenSettingsBtnFunctionality_STB_TC_53() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Location Permission Deny open Settings Button Functionality Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
				logger.info("STB_TC_53, Send Money To ML Branch Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STB_TC_53", "STB_TC_53, Send Money To ML Branch Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyToMLBranchLocationPopUpAllowFunctionality_STB_TC_54() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Location popup Allow Button Functionality Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("STB_TC_54, Send Money To ML Branch Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STB_TC_54", "STB_TC_54, Send Money To ML Branch Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyToMLBranchInternetInterruptionWhileEnteringOTP_STB_TC_55() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Internet Interruption While Entering OTP Validation");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		waitTime(15000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("STB_TC_55, Send Money To ML Branch Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("STB_TC_55", "STB_TC_55, Send Money To ML Branch Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLBranchTransactionValidationAfterMinimizingApp_STB_TC_56() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Branch Transaction Validation After Minimizing App");
		sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
		enterMLBranchDetails();
		enterAmountToKwartaPadala("100");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
			logger.info("STB_TC_56, Send Money To ML Branch Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("STB_TC_56", "STB_TC_56, Send Money To ML Branch Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}









//===============================================Send/Transfer To a ML Wallet User=============================//
//========================== Generalized methods for Send/Transfer To a ML Wallet User========================//

	public void sendMoneyMLWallet(String sTier) throws Exception {
		mlWalletLogin(sTier);
		click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
		verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
		verifyElementPresentAndClick(SendTransferPage.objToAMLWalletUser, getTextVal(SendTransferPage.objToAMLWalletUser, "Button"));
	}


	public void enterMobileNumberMLWallet(String nMobileNumber) throws Exception {
		explicitWaitVisible(SendTransferPage.objSendMoney, 10);
		verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
		type(SendTransferPage.objMobileNumberField, nMobileNumber, "Mobile Number Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));

	}

	public void enterAmountAndSendToMLWallet(String nAmount) throws Exception {
		explicitWaitVisible(SendTransferPage.objAmountTxtField, 5);
		if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
			type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
			click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			waitTime(5000);
			click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
//			verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
			Swipe("UP", 2);
			click(SendTransferPage.objSendPHPBtn, getTextVal(SendTransferPage.objSendPHPBtn, "Button"));
		}
	}


	//======================================================================================================================//
	public void sendToMLWalletUser_STW_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Wallet");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInWalletToWallet);
				assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
				logger.info("STW_TC_01, Successfully Amount sent from Wallet to Wallet and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("STW_TC_01", "STW_TC_01, Successfully Amount sent from Wallet to Wallet and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyAddToFavorites_STW_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Add To Favorites");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
			System.out.println(sReferenceNumber);
			Swipe("UP", 1);
			click(SendTransferPage.objSaveToMyFavorite, getTextVal(SendTransferPage.objSaveToMyFavorite, "Button"));
			if (verifyElementPresent(SendTransferPage.objAddedToFavoritesMsg, getTextVal(SendTransferPage.objAddedToFavoritesMsg, "Message"))) {
				click(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
			}
			if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
				verifyElementPresent(SendTransferPage.objFavoriteReceiver, "Added Favorites");
				logger.info("STW_TC_12, Added to favorites and displayed in Recent Favorites");
				ExtentReporter.extentLoggerPass("STW_TC_12", "STW_TC_12, Added to favorites and displayed in Recent Favorites");
				System.out.println("-----------------------------------------------------------");
			}

		}
	}


	public void sendMoneyMLWalletToExistingReceiver_STW_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet To Existing Receiver");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		verifyElementPresent(SendTransferPage.objSelectFavorite, getTextVal(SendTransferPage.objSelectFavorite, "Text"));
		click(SendTransferPage.objSelectFavorite, getTextVal(SendTransferPage.objSelectFavorite, "Text"));
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInWalletToWallet);
				assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
				logger.info("STW_TC_02, Successfully Amount sent from Wallet to Wallet to Recently added favorite and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("STW_TC_02", "STW_TC_02, Successfully Amount sent from Wallet to Wallet to Recently added favorite and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}

	}


	public void sendToMLWalletInvalidMobNumber_STW_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Send To ML Wallet to Invalid Mobile Number");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Invalid_MobileNumber"));
		explicitWaitVisible(SendTransferPage.objMobileNumberErrorMsg, 5);
		if (verifyElementPresent(SendTransferPage.objMobileNumberErrorMsg, getTextVal(SendTransferPage.objMobileNumberErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objMobileNumberErrorMsg);
			String sExpectedMsg = "Mobile number is invalid";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
			logger.info("STW_TC_03, Mobile number is invalid - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_03", "STW_TC_03, Mobile number is invalid - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendToMLWalletUnRegisteredNumber_STW_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Send To ML Wallet to Invalid Mobile Number");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Unregistered_MobileNumber"));
		explicitWaitVisible(SendTransferPage.objUnRegisteredMobNumber, 10);
		if (verifyElementPresent(SendTransferPage.objUnRegisteredMobNumber, getTextVal(SendTransferPage.objUnRegisteredMobNumber, "Error Message"))) {
			String sFirstNameErrorMsg = getText(SendTransferPage.objUnRegisteredMobNumber);
			String sExpectedMsg = "Receiver not Found!";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
			logger.info("STW_TC_04, Receiver not Found - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_04", "STW_TC_04, Receiver not Found - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendToMLWalletInvalidAmount_STW_TC_05(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objAmountTxtField, 5);
		type(SendTransferPage.objAmountTxtField, Amount, "Amount Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objInvalidAmountMsg, getTextVal(SendTransferPage.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(SendTransferPage.objInvalidAmountMsg);
			String sExpectedErrorMsg = "The amount should not be less than 1";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("STW_TC_05, The amount should not be less than 1 - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_05", "STW_TC_05, The amount should not be less than 1 - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendToMLWalletInsufficientAmount_STW_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money to any ML Branch");
		sendMoneyMLWallet("9999999999");
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("35000");
		explicitWaitVisible(SendTransferPage.objInsufficientAmountMsg, 5);
		if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg, getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
			String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
			String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
			assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
			logger.info("STW_TC_06, Insufficient Balance - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_06", "STW_TC_06, Insufficient Balance - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMLWalletMaximumAmount_STW_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Maximum Amount");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("100000");
		if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			logger.info("STW_TC_07, The maximum send money per transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_07", "STW_TC_07, The maximum send money per transaction - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyDeleteFromFavorites_STW_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Delete From Favorites");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
		click(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Text"));
		click(SendTransferPage.objEllipsisBtn, "Ellipsis Button");
		click(SendTransferPage.objDeleteBtn, getTextVal(SendTransferPage.objDeleteBtn, "Button"));
		click(SendTransferPage.objConfirmBtn, getTextVal(SendTransferPage.objConfirmBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.objFavRemovedMsg, getTextVal(SendTransferPage.objFavRemovedMsg, "Pop up Message"))) {
			String sRemovedSuccessfulMsg = getText(SendTransferPage.objFavRemovedMsg);
			String sExpectedMsg = "Successfully Removed";
			assertionValidation(sRemovedSuccessfulMsg, sExpectedMsg);
			logger.info("STW_TC_09, Successfully removed Favorite Contact from favorites list is validated");
			ExtentReporter.extentLoggerPass("STW_TC_09", "STW_TC_09, Successfully removed Favorite Contact from favorites list is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMLWalletUIValidation_STW_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
			verifyElementPresent(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Button"));
			verifyElementPresent(SendTransferPage.objRecentFavorites, getTextVal(SendTransferPage.objRecentFavorites, "Header"));
			verifyElementPresent(SendTransferPage.objReceiver, getTextVal(SendTransferPage.objReceiver, "Header"));
			verifyElementPresent(SendTransferPage.objMobileNumberField, "Mobile number input text field");
			verifyElementPresent(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			logger.info("STW_TC_10, Send Money ML Wallet Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_10", "STW_TC_10, Send Money ML Wallet Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyFavoritesUIValidation_STW_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Saved Favorites UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		verifyElementPresentAndClick(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Button"));
		if (verifyElementPresent(SendTransferPage.ObjFavorites, getTextVal(SendTransferPage.ObjFavorites, "Page"))) {
			verifyElementPresent(SendTransferPage.objSearchFavoriteField, "Search Favorites Field");
			logger.info("STW_TC_11, Send Money ML Wallet Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_11", "STW_TC_11, Send Money ML Wallet Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletCancelTransaction_STW_TC_13(String nAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Cancel Transaction");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objAmountTxtField, 5);
		if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
			type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
			click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
			verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
			Swipe("UP", 1);
			verifyElementPresentAndClick(SendTransferPage.objCancelTransaction, getTextVal(SendTransferPage.objCancelTransaction, "Button"));
			if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
				logger.info("STW_TC_13, Cancelled the current Transaction");
				ExtentReporter.extentLoggerPass("STW_TC_13", "STW_TC_13, Cancelled the current Transaction");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyMLWalletSearchUnFavoriteUser_STW_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Search UnFavorite User");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		verifyElementPresentAndClick(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Button"));
		verifyElementPresent(SendTransferPage.ObjFavorites, getTextVal(SendTransferPage.ObjFavorites, "Page"));
		type(SendTransferPage.objSearchFavoriteField, "ABCD", "Search Favorite Field");
		if (verifyElementPresent(SendTransferPage.objNoRecentTransactionTxt, getTextVal(SendTransferPage.objNoRecentTransactionTxt, "Added Favorite"))) {
			logger.info("STW_TC_14, Send Money ML Wallet Search UnFavorite User Validated");
			ExtentReporter.extentLoggerPass("STW_TC_14", "STW_TC_14, Send Money ML Wallet Search UnFavorite User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletSearchFavoriteUser_STW_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Search Favorite User");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		verifyElementPresentAndClick(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Button"));
		verifyElementPresent(SendTransferPage.ObjFavorites, getTextVal(SendTransferPage.ObjFavorites, "Page"));
		type(SendTransferPage.objSearchFavoriteField, prop.getproperty("Last_Name"), "Search Favorite Field");
		if (verifyElementPresent(SendTransferPage.objAddedFavorite, getTextVal(SendTransferPage.objAddedFavorite, "Added Favorite"))) {
			logger.info("STW_TC_15, Send Money ML Wallet Search Favorite User Validated");
			ExtentReporter.extentLoggerPass("STW_TC_15", "STW_TC_15, Send Money ML Wallet Search Favorite User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletSuccessPageUIValidation_STW_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Success Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			Swipe("UP", 2);
			verifyElementPresent(SendTransferPage.objSaveToMyFavorite, getTextVal(SendTransferPage.objSaveToMyFavorite, "Button"));
			verifyElementPresent(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			verifyElementPresent(SendTransferPage.objNewTransaction, getTextVal(SendTransferPage.objNewTransaction, "Button"));
			logger.info("STW_TC_16,Send Money ML Wallet Success Page UI Validated");
			ExtentReporter.extentLoggerPass("STW_TC_16", "STW_TC_16, Send Money ML Wallet Success Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletOTPPageUIValidation_STW_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet OTP PageUI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("STW_TC_17, One Time Pin page UI Validated");
			ExtentReporter.extentLoggerPass("STW_TC_17", "STW_TC_16, One Time Pin page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletConfirmDetailsPageUIValidation_STW_TC_18(String nAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Confirm Details Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objAmountTxtField, 5);
		verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"));
		type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
		click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
		click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
		if (verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"))) {
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			verifyElementPresent(SendTransferPage.objCancelTransaction, getTextVal(SendTransferPage.objCancelTransaction, "Button"));
			Swipe("UP", 1);
			verifyElementPresent(SendTransferPage.objSendPHPBtn, getTextVal(SendTransferPage.objSendPHPBtn, "Button"));
			logger.info("STW_TC_18, Send Money ML Wallet Confirm Details Page UI Validated");
			ExtentReporter.extentLoggerPass("STW_TC_18", "STW_TC_18, Send Money ML Wallet Confirm Details Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMlWalletEnterAmountPageUIValidation_STW_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To Ml Wallet Enter Amount Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(SendTransferPage.objAmountTxtField, 5);
		if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
			verifyElementPresent(SendTransferPage.objAmountToSend, getTextVal(SendTransferPage.objAmountToSend, "Header"));
			verifyElementPresent(SendTransferPage.objAmountTxtField, "Amount Text Field");
			verifyElementPresent(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			logger.info("STW_TC_19, Send Money To Ml Wallet Enter Amount Page UI Validated");
			ExtentReporter.extentLoggerPass("STW_TC_19", "STW_TC_19, Send Money To Ml Wallet Enter Amount Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLWalletPageUIValidation_STW_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		explicitWaitVisible(SendTransferPage.objSendMoney, 5);
		if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
			verifyElementPresent(SendTransferPage.objRecentFavorites, getTextVal(SendTransferPage.objRecentFavorites, "Header"));
			verifyElementPresent(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Button"));
			verifyElementPresent(SendTransferPage.objReceiver, getTextVal(SendTransferPage.objReceiver, "Header"));
			verifyElementPresent(SendTransferPage.objMobileNumberField, "Mobile Number Text Field");
			verifyElementPresent(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
			logger.info("STW_TC_20, Send Money To ML Wallet Page UI Validated");
			ExtentReporter.extentLoggerPass("STW_TC_20", "STW_TC_20, Send Money To ML Wallet Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMlWalletTransactionDetailsUIValidation_STW_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction Details of Wallet To Wallet Page UI Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
				verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
				verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
				logger.info("STW_TC_21, Transaction Details of Wallet To Wallet Page UI Validation Validated");
				ExtentReporter.extentLoggerPass("STW_TC_21", "STW_TC_21, Transaction Details of Wallet To Wallet Page UI Validation Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyMLWalletBuyerTierAccountUser_STW_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Buyer Tier Account User Transaction");
		sendMoneyMLWallet(prop.getproperty("Buyer_Tier"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "Send Money is not allowed for customers at this verification level. Please upgrade your account to use this service.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STW_TC_22, Send Money is not allowed for customers at this Buyer tier - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_22", "STW_TC_22, Send Money is not allowed for customers at this Buyer tier  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletSemiVerifiedAccountUser_STW_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Semi-Verified Account User Transaction");
		sendMoneyMLWallet(prop.getproperty("Semi_Verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
			logger.info("STW_TC_23, Send Money ML Wallet Semi-Verified Account User Transaction validated");
			ExtentReporter.extentLoggerPass("STW_TC_23", "STW_TC_23, Send Money ML Wallet Semi-Verified Account User Transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletBranchVerifiedAccountUser_STW_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Branch-Verified Account User Transaction");
		sendMoneyMLWallet(prop.getproperty("Branch_Verified"));
		enterMobileNumberMLWallet(prop.getproperty("Fully_verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
			logger.info("STW_TC_24, Send Money ML Wallet Branch-Verified Account User Transaction validated");
			ExtentReporter.extentLoggerPass("STW_TC_24", "STW_TC_24, Send Money ML Wallet Branch-Verified Account User Transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMLWalletFullyVerifiedAccountUser_STW_TC_25() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money ML Wallet Fully-Verified Account User Transaction");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
			logger.info("STW_TC_25, Send Money ML Wallet Fully-Verified Account User Transaction validated");
			ExtentReporter.extentLoggerPass("STW_TC_25", "STW_TC_25, Send Money ML Wallet Fully-Verified Account User Transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyMLWalletSemiVerifiedAccountMaxLimit_STW_TC_26() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Semi-Verified Account Maximum Limit Validation");
		sendMoneyMLWallet(prop.getproperty("Semi_Verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("60000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STB_TC_26, Send Money To ML Wallet Semi-Verified Account Maximum Limit - Error Message is validated");
			ExtentReporter.extentLoggerPass("STB_TC_26", "STB_TC_26, Send Money To ML Wallet Semi-Verified Account Maximum Limit  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMLWalletBranchVerifiedAccountMaxLimit_STW_TC_29() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Branch-Verified Account Maximum Limit Validation");
		sendMoneyMLWallet(prop.getproperty("Branch_Verified"));
		enterMobileNumberMLWallet(prop.getproperty("Fully_verified"));
		enterAmountAndSendToMLWallet("60000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STW_TC_29, Send Money To ML Wallet Branch-Verified Account Maximum Limit - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_29", "STW_TC_29, Send Money To ML Wallet Branch-Verified Account Maximum Limit  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyMLWalletFullyVerifiedAccountMaxLimit_STW_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Fully-Verified Account Maximum Limit Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("60000");
		if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
			String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
			logger.info("STW_TC_32, Send Money To ML Wallet Fully-Verified Account Maximum Limit - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_32", "STW_TC_32, Send Money To ML Wallet Fully-Verified Account Maximum Limit  - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void sendMoneyToMLWalletSuccessMsgValidation_STW_TC_35() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Success Message Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
		verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
		Swipe("UP", 2);
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
		if (verifyElementPresent(SendTransferPage.objWalletToWalletStatus, getTextVal(SendTransferPage.objWalletToWalletStatus, "Status"))) {
			String sSuccessStatus = getText(SendTransferPage.objWalletToWalletStatus);
			String sExpectedStatus = "Success";
			assertionValidation(sSuccessStatus, sExpectedStatus);
			logger.info("STW_TC_35, Send Money To ML Wallet Success Message validated");
			ExtentReporter.extentLoggerPass("STW_TC_35", "STW_TC_35, Send Money To ML Wallet Success Message validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletMaxTransactionReceivingLimitSemiVerifiedTier_STW_TC_36(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User");
		sendMoneyMLWallet(prop.getproperty("SemiVerified_MaxTransactionSender"));
		enterMobileNumberMLWallet(prop.getproperty("SemiVerified_MaxTransactionReceiver"));

		enterAmountAndSendToMLWallet(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
		verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
		String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
		Swipe("UP", 2);
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		mlWalletLogout();
		verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
		mlWalletLogin(prop.getproperty("SemiVerified_MaxTransactionReceiver"));
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
			assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
			String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
			String sActualReceivedAmount = sReceivedAmount.substring(4);
			assertionValidation(sActualReceivedAmount, "50,000.00");
			logger.info("STW_TC_36, Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User Validated");
			ExtentReporter.extentLoggerPass("STW_TC_36", "STW_TC_36, Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletMaxTransactionReceivingLimitBranchVerifiedTier_STW_TC_38(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User");
		sendMoneyMLWallet(prop.getproperty("BranchVerified_MaxTransactionSender"));
		enterMobileNumberMLWallet(prop.getproperty("BranchVerified_MaxTransactionReceiver"));

		enterAmountAndSendToMLWallet(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
		verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
		String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
		Swipe("UP", 2);
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		mlWalletLogout();
		verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
		mlWalletLogin(prop.getproperty("BranchVerified_MaxTransactionReceiver"));
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
			assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
			String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
			String sActualReceivedAmount = sReceivedAmount.substring(4);
			assertionValidation(sActualReceivedAmount, "50,000.00");
			logger.info("STW_TC_38, Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User Validated");
			ExtentReporter.extentLoggerPass("STW_TC_38", "STW_TC_38, Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletMaxTransactionReceivingLimitFullyVerifiedTier_STW_TC_40(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User");
		sendMoneyMLWallet(prop.getproperty("FullyVerified_MaxTransactionSender"));
		enterMobileNumberMLWallet(prop.getproperty("FullyVerified_MaxTransactionReceiver"));

		enterAmountAndSendToMLWallet(sAmount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
		verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
		String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
		Swipe("UP", 2);
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		mlWalletLogout();
		verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
		mlWalletLogin(prop.getproperty("FullyVerified_MaxTransactionReceiver"));
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
			assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
			String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
			String sActualReceivedAmount = sReceivedAmount.substring(4);
			assertionValidation(sActualReceivedAmount, "50,000.00");
			logger.info("STW_TC_40, Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User Validated");
			ExtentReporter.extentLoggerPass("STW_TC_40", "STW_TC_40, Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}



	public void sendMoneyToMLWalletLocationPopupValidation_STW_TC_42() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Location popup Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("STW_TC_42, Send Money To ML Wallet Location popup Validated");
			ExtentReporter.extentLoggerPass("STW_TC_42", "STW_TC_42, Send Money To ML Wallet Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletLocationDenyFunctionality_STW_TC_43() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Location Deny Functionality Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("STW_TC_43, Send Money To ML Wallet Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("STW_TC_43", "STW_TC_43, Send Money To ML Wallet Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletLocationPermissionDenyCloseBtnFunctionality_STW_TC_44() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Location Permission Deny Close Button Functionality Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(SendTransferPage.objMLWalletBalance,getTextVal(SendTransferPage.objMLWalletBalance,"Page"))){
				logger.info("STW_TC_44, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STW_TC_44", "STW_TC_44, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyToMLWalletLocationPermissionDenyOpenSettingsBtnFunctionality_STW_TC_45() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Location Permission Deny open Settings Button Functionality Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
				logger.info("STW_TC_45, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STW_TC_45", "STW_TC_45, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyToMLWalletLocationPopUpAllowFunctionality_STW_TC_46() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Location popup Allow Button Functionality Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("STW_TC_46, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("STW_TC_46", "STW_TC_46, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void sendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_STW_TC_47() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Internet Interruption While Entering OTP Validation");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		waitTime(15000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("STW_TC_47, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("STW_TC_47", "STW_TC_47, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void sendMoneyToMLWalletTransactionValidationAfterMinimizingApp_STW_TC_50() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money To ML Wallet Transaction Validation After Minimizing App");
		sendMoneyMLWallet(prop.getproperty("Fully_verified"));
		enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			logger.info("STW_TC_50, Send Money To ML Wallet Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("STW_TC_50", "STW_TC_50, Send Money To ML Wallet Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}




//================================================ Transaction History ===========================================//


	public void mlWallet_TransactionHistory_Generic_Steps(String billModule, String transaction) throws Exception {
		String PayBillsHistory = getText(MLWalletTransactionHistoryPage.objBillHistory(billModule, transaction));
		if (PayBillsHistory.equals(billModule))// "Pay Bills"
		{
			List<WebElement> values = findElements(MLWalletTransactionHistoryPage.objPayBillsTransctionList1(billModule));
			for (int i = 0; i < values.size(); i++) {
				String billPayTransaction = values.get(i).getText();
				logger.info(billModule + " All Transactions : " + billPayTransaction);
				ExtentReporter.extentLogger(" ", billModule + " All Transactions : " + billPayTransaction);
			}
		} else if (PayBillsHistory.equals(transaction))// "No Recent Transaction"
		{
			logger.info("No Recent Transactions Are Available for " + billModule + " Module");
			ExtentReporter.extentLogger("", "No Recent Transactions Are Available for " + billModule + " Module");
		}
	}

	public void mlWallet_TransactionHistory_TH_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("MLWallet_TransactionHistory_Scenario");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletTransactionHistoryPage.objRecentTransaction, getText(MLWalletTransactionHistoryPage.objRecentTransaction));
		Swipe("UP", 2);
		click(MLWalletTransactionHistoryPage.objSeeMoreBtn, "See More Button");
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionHistory, getTextVal(MLWalletTransactionHistoryPage.objTransactionHistory, "Page"))) {
			logger.info("TH_TC_01, All Transactions are displayed");
			ExtentReporter.extentLoggerPass("TH_TC_01", "'TH_TC_01', All Transactions are displayed");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void billsPayTransactionHHistory_TH_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Bills Pay Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objBillsPayTab, "Bills Pay");
		mlWallet_TransactionHistory_Generic_Steps("Pay Bills", "No Recent Transaction");
		logger.info("TH_TC_02, Bills pay Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_02", "'TH_TC_02', Bills pay Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void buyLoadTransactionHistory_TH_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Buy Eload Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objeLoadTab, "eLoad");
		mlWallet_TransactionHistory_Generic_Steps("Buy Eload", "No Recent Transaction");
		logger.info("TH_TC_03, eLoad Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_03", "'TH_TC_03', eLoad Transactions Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void sendMoneyTransactionHistory_TH_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Send Money Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objSendMoneyTab, "Send Money");
		mlWallet_TransactionHistory_Generic_Steps("Kwarta Padala", "No Recent Transaction"); // Kwarta Padala
		logger.info("TH_TC_04, Send Money Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_04", "'TH_TC_04', Send Money Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void cashInTransactionHistory_TH_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("Cash In");
		click(MLWalletTransactionHistoryPage.objCashInTab, "Cash In");
		Thread.sleep(3000);
		mlWallet_TransactionHistory_Generic_Steps("Cash In", "No Recent Transaction");
		logger.info("TH_TC_05, Cash In Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_05", "'TH_TC_05',  Cash In Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void cashOutTransactionHistory_TH_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Cash Out Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objCashOutTab, "Cash Out");
		Thread.sleep(3000);
		mlWallet_TransactionHistory_Generic_Steps("Cash Out", "No Recent Transaction");
		logger.info("TH_TC_06,  Cash Out Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_06", "'TH_TC_06', Cash Out Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void receiveMoneyTransactionHistory_TH_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Receive Money Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Receive Money");
		Thread.sleep(3000);
		mlWallet_TransactionHistory_Generic_Steps("Receive Money", "No Recent Transaction");
		logger.info("TH_TC_07, Receive Money Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_07", "'TH_TC_07', Receive Money Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void balanceAdjustmentTransactionHistory_TH_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Balance Adjustment Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("ML Shop");
		click(MLWalletTransactionHistoryPage.objBalanceAdjustmentTab, "Balance Adjustment");
		Thread.sleep(2000);
		mlWallet_TransactionHistory_Generic_Steps("Balance Adjustment", "No Recent Transaction");
		logger.info("TH_TC_08, Balance Adjustment Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_08", "'TH_TC_08', Balance Adjustment Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}

	public void mlShopTransactionHistory_TH_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("ML Shop Transaction History");
		mlWallet_TransactionHistory_TH_TC_01();
		click(MLWalletTransactionHistoryPage.objMlShopTab, "ML Shop");
		Thread.sleep(2000);
		mlWallet_TransactionHistory_Generic_Steps("ML Shop", "No Recent Transaction");
		logger.info("TH_TC_09, ML Shop Transactions are displayed");
		ExtentReporter.extentLoggerPass("TH_TC_09", "'TH_TC_09', ML Shop Transactions are displayed");
		System.out.println("-----------------------------------------------------------");
	}


	public void transactionHistoryUIValidation_TH_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History UI Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objAllTab, getTextVal(MLWalletTransactionHistoryPage.objAllTab, "Tab"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objBillsPayTab, getTextVal(MLWalletTransactionHistoryPage.objBillsPayTab, "Tab"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objeLoadTab, getTextVal(MLWalletTransactionHistoryPage.objeLoadTab, "Tab"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSendMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objSendMoneyTab, "Tab"));
			scrollToFirstHorizontalScrollableElement("Cash Out");
			verifyElementPresent(MLWalletTransactionHistoryPage.objCashInTab, getTextVal(MLWalletTransactionHistoryPage.objCashInTab, "Tab"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objCashOutTab, getTextVal(MLWalletTransactionHistoryPage.objCashOutTab, "Tab"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
			scrollToFirstHorizontalScrollableElement("ML Shop");
			verifyElementPresent(MLWalletTransactionHistoryPage.objBalanceAdjustmentTab, getTextVal(MLWalletTransactionHistoryPage.objBalanceAdjustmentTab, "Tab"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objMlShopTab, getTextVal(MLWalletTransactionHistoryPage.objMlShopTab, "Tab"));
			logger.info("TH_TC_10,Transaction History UI Validated");
			ExtentReporter.extentLoggerPass("TH_TC_10", "'TH_TC_10',Transaction History UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistoryBillsPayTransactionDetailsValidation_TH_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History BillsPay Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objBillsPayTab, getTextVal(MLWalletTransactionHistoryPage.objBillsPayTab, "Tab"));
		if (verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction")) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objBiller, getTextVal(MLWalletTransactionHistoryPage.objBiller, "Biller"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("TH_TC_13,Transaction History BillsPay Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_13", "'TH_TC_13',Transaction History BillsPay Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		} else {
			logger.info("No recent Transaction");
		}
	}

	public void transactionHistoryELoadTransactionDetailsValidation_TH_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History ELoad Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objeLoadTab, getTextVal(MLWalletTransactionHistoryPage.objeLoadTab, "Tab"));
		if (verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction")) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objLoadType, getTextVal(MLWalletTransactionHistoryPage.objLoadType, "Load Type"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("TH_TC_14,Transaction History ELoad Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_14", "'TH_TC_14',Transaction History ELoad Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistorySendMoneyWalletToWalletTransactionDetailsValidation_TH_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History Send Money Wallet To Wallet Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objSendMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objSendMoneyTab, "Tab"));
		swipeDownUntilElementVisible("Wallet to Wallet");
		if (verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objWalletToWallet, getTextVal(MLWalletTransactionHistoryPage.objWalletToWallet, "Transaction"))) {

			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("TH_TC_15,Transaction History Send Money Wallet To Wallet Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_15", "'TH_TC_15',Transaction History Send Money Wallet To Wallet Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistorySendMoneyKwartaPadalaTransactionDetailsValidation_TH_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History Send Money Kwarta Padala Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objSendMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objSendMoneyTab, "Tab"));
		swipeDownUntilElementVisible("Kwarta Padala");
		if (verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objKwartaPadala, getTextVal(MLWalletTransactionHistoryPage.objKwartaPadala, "Transaction"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("TH_TC_16,Transaction History Send Money Kwarta Padala Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_16", "'TH_TC_16',Transaction History Send Money Kwarta Padala Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistoryCashInTransactionDetailsValidation_TH_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History Cash In Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("Cash In");
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objCashInTab, getTextVal(MLWalletTransactionHistoryPage.objCashInTab, "Tab"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction");
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objBank, getTextVal(MLWalletTransactionHistoryPage.objBank, "Bank"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashIn, getTextVal(MLWalletTransactionHistoryPage.objTotalCashIn, "Total Cash In"));
			logger.info("TH_TC_17,Transaction History Cash In Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_17", "'TH_TC_17',Transaction History Cash In Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistoryCashOutTransactionDetailsValidation_TH_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History Cash Out Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("Cash Out");
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objCashOutTab, getTextVal(MLWalletTransactionHistoryPage.objCashOutTab, "Tab"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction");
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objBank, getTextVal(MLWalletTransactionHistoryPage.objBank, "Bank"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashOut, getTextVal(MLWalletTransactionHistoryPage.objTotalCashOut, "Total Cash Out"));
			logger.info("TH_TC_18,Transaction History Cash Out Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_18", "'TH_TC_18',Transaction History Cash Out Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistoryReceiveMoneyTransactionDetailsValidation_TH_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History Receive Money Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("Receive Money");
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction");
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Amount Received"));
			logger.info("TH_TC_19,Transaction History Receive Money Transaction Details Validated");
			ExtentReporter.extentLoggerPass("TH_TC_19", "'TH_TC_19',Transaction History Receive Money Transaction Details Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void transactionHistoryMLShopTransactionDetailsValidation_TH_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Transaction History ML Shop Transaction Details Validation");
		mlWallet_TransactionHistory_TH_TC_01();
		scrollToFirstHorizontalScrollableElement("ML Shop");
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objMlShopTab, getTextVal(MLWalletTransactionHistoryPage.objMlShopTab, "Tab"));
		verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objFirstTransaction, "First Transaction");
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Amount Received"));
		}
	}


//=================================== ML Wallet Shop Items ==========================================================//


	public void shopItemsNavigation() throws Exception {
		click(MLWalletShopItemsPage.objShopItemsTab, "Shop Items Icon");
		Thread.sleep(10000);
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMLShopPage, "ML Shop Page");
		Thread.sleep(10000);
	}
	public void selectItemAndAddToCart() throws Exception {
		Swipe("UP", 2);
		click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
		click(MLWalletShopItemsPage.objSelectItem, getTextVal(MLWalletShopItemsPage.objSelectItem, "Item"));
		Swipe("up", 2);
		click(MLWalletShopItemsPage.objAddToCartBtn, "Add to cart Button");
	}
	public void navigationToCart() throws Exception {
		click(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
		click(MLWalletShopItemsPage.objCart, "Cart");
	}

	public void editAddressAndPlaceTheOrder() throws Exception {
		click(MLWalletShopItemsPage.objCheckBox, "Check Box");
		click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
		click(MLWalletShopItemsPage.objEditAddress, "Edit Address Tab");
		verifyElementPresent(MLWalletShopItemsPage.objSelectBranchPage, getTextVal(MLWalletShopItemsPage.objSelectBranchPage, "Page"));
//		click(MLWalletShopItemsPage.objInputFieldOne, "Select Branch Field 1");
//		click(MLWalletShopItemsPage.objBranchName, getTextVal(MLWalletShopItemsPage.objBranchName, "Branch Name"));
//		click(MLWalletShopItemsPage.objInputFieldTwo, "Select Branch Field 2");
//		click(MLWalletShopItemsPage.objSubBranchName, getTextVal(MLWalletShopItemsPage.objSubBranchName, "Branch Name"));
//		click(MLWalletShopItemsPage.objInputFieldThree, "Select Branch Field 3");
//		click(MLWalletShopItemsPage.objSubBranchNameTwo, getTextVal(MLWalletShopItemsPage.objSubBranchNameTwo, "Branch Name"));
		click(MLWalletShopItemsPage.objSaveBtn, "Save Button");
		verifyElementPresent(MLWalletShopItemsPage.objAddressSuccessfulMsg, getTextVal(MLWalletShopItemsPage.objAddressSuccessfulMsg, "Message"));
		click(MLWalletShopItemsPage.objOkBtn, "OK Button");
		Swipe("UP",2);
		verifyElementPresent(MLWalletShopItemsPage.objSelectPaymentMethod,getTextVal(MLWalletShopItemsPage.objSelectPaymentMethod,"Header"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMLWallet,getTextVal(MLWalletShopItemsPage.objMLWallet,"Option"));
		Swipe("UP",2);
		click(MLWalletShopItemsPage.objPlaceOrderBtn, "Place Order Button");
	}



	public void mlWallet_ShopItems_Successful_Purchase() throws Exception {
		ExtentReporter.HeaderChildNode("mlWalletShopItems_Successful_Purchase");
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
		Thread.sleep(2000);
		click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
		handleOtp(prop.getproperty("otp"));
		click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
		// code for successful purchase message validation
	}

	public void mlWallet_ShopItems_with_Insufficient_Balance() throws Exception {
		ExtentReporter.HeaderChildNode("mlWallet_ShopItems_with_Insufficient_Balance");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
		Thread.sleep(2000);
		click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
		handleOtp(prop.getproperty("OTP"));
		click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
		String oOpsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUp);
		String supplyFieldsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUpMsg);
		logger.info(oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
		ExtentReporter.extentLogger("", oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
		logger.info("MLS_TC_02, Oops... Insufficient Balance. - Error message is validated ");
		ExtentReporter.extentLoggerPass("MLS_TC_02", "MLS_TC_02, Oops... Insufficient Balance. - Error message is validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void mlWallet_ShopItems_with_Incorrect_Otp() throws Exception {
		ExtentReporter.HeaderChildNode("mlWallet_ShopItems_with_Incorrect_Otp");
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
		Thread.sleep(2000);
		click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
		handleOtp(prop.getproperty("incorrectOtp"));
		click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
		// Code to be written to validate incorrect otp msg
	}

	public void shopItemsWithoutInputOtp_MLS_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("mlWallet_ShopItems_without_Input_Otp");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
		Thread.sleep(2000);
		click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
		String oOpsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUp);
		String supplyFieldsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUpMsg);
		logger.info(oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
		ExtentReporter.extentLogger("", oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
		logger.info("MLS_TC_04, Oops... Please supply all fields. - Error message is validated");
		ExtentReporter.extentLoggerPass("MLS_TC_04", "MLS_TC_04, Oops... Please supply all fields. - Error message is validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void shopItemsHamburgerMenuNavigation_MLS_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Hamburger Menu Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
		if(verifyElementPresent(MLWalletShopItemsPage.objSearch,getTextVal(MLWalletShopItemsPage.objSearch,"Button"))){
			verifyElementPresent(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objCart,getTextVal(MLWalletShopItemsPage.objCart,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objShop,getTextVal(MLWalletShopItemsPage.objShop,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objAbout,getTextVal(MLWalletShopItemsPage.objAbout,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objContact,getTextVal(MLWalletShopItemsPage.objContact,"Button"));
			logger.info("MLS_TC_12, Shop Items Hamburger Menu Navigation validated");
			ExtentReporter.extentLoggerPass("MLS_TC_12", "MLS_TC_12, Shop Items Hamburger Menu Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsRespectivePageNavigationAfterSelectingAnCategory_MLS_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Respective Page Navigation After Selecting An Category");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		Swipe("UP",1);
		click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
		if (verifyElementDisplayed(MLWalletShopItemsPage.objItems)) {
			List<WebElement> values = findElements(MLWalletShopItemsPage.objItems);
			for (int i = 0; i < values.size(); i++) {
				String displayedItem = values.get(i).getText();
				logger.info("Item : " + displayedItem + " is displayed");
				ExtentReporter.extentLogger(" ", "Item : " + displayedItem + " is displayed");
			}
		}
		verifyElementPresent(MLWalletShopItemsPage.objGenderDropdown,getTextVal(MLWalletShopItemsPage.objGenderDropdown,"Dropdown"));
		verifyElementPresent(MLWalletShopItemsPage.objColorDropdown,getTextVal(MLWalletShopItemsPage.objColorDropdown,"Dropdown"));
		verifyElementPresent(MLWalletShopItemsPage.objKaratDropdown,getTextVal(MLWalletShopItemsPage.objKaratDropdown,"Dropdown"));
		verifyElementPresent(MLWalletShopItemsPage.objPriceDropdown,getTextVal(MLWalletShopItemsPage.objPriceDropdown,"Dropdown"));
		logger.info("MLS_TC_15, Shop Items Respective Page Navigation After Selecting An Category validated");
		ExtentReporter.extentLoggerPass("MLS_TC_15", "MLS_TC_15, Shop Items Respective Page Navigation After Selecting An Category validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void shopItemsSelectedItemScreenUIValidation_MLS_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Selected Item Screen UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		Swipe("UP",1);
		click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
		click(MLWalletShopItemsPage.objSelectItem, getTextVal(MLWalletShopItemsPage.objSelectItem, "Item"));
		Swipe("UP",2);
		if(verifyElementPresent(MLWalletShopItemsPage.objProductImage,"Selected Product Image")){
			verifyElementPresent(MLWalletShopItemsPage.objProductPrice,getTextVal(MLWalletShopItemsPage.objProductPrice,"Product Price"));
			verifyElementPresent(MLWalletShopItemsPage.objShippingTo,getTextVal(MLWalletShopItemsPage.objShippingTo,"Shipping To"));
			verifyElementPresent(MLWalletShopItemsPage.objShippingFee,getTextVal(MLWalletShopItemsPage.objShippingFee,"Shipping Fee"));
			verifyElementPresent(MLWalletShopItemsPage.objAddToCartBtn,getTextVal(MLWalletShopItemsPage.objAddToCartBtn,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objViewShop,getTextVal(MLWalletShopItemsPage.objViewShop,"Button"));
			logger.info("MLS_TC_18, Shop Items Selected Item Screen UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_18", "MLS_TC_18, Shop Items Selected Item Screen UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsCartPageUIValidation_MLS_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Cart Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		if(verifyElementPresent(MLWalletShopItemsPage.objCartPageHeader,getTextVal(MLWalletShopItemsPage.objCartPageHeader,"Header"))){
			verifyElementPresent(MLWalletShopItemsPage.objCheckBox,"Item Check Box");
			verifyElementPresent(MLWalletShopItemsPage.objProductNameInCartPage,getTextVal(MLWalletShopItemsPage.objProductNameInCartPage,"Product Name in Cart Page"));
			verifyElementPresent(MLWalletShopItemsPage.objPriceInCartPage,getTextVal(MLWalletShopItemsPage.objPriceInCartPage,"Price"));
			verifyElementPresent(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
			verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,getTextVal(MLWalletShopItemsPage.objCheckOutBtn,"Button"));
			logger.info("MLS_TC_19, Shop Items Cart Page UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_19", "MLS_TC_19, Shop Items Cart Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsShippingDetailsPageUIValidation_MLS_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Shipping Details Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		click(MLWalletShopItemsPage.objCheckBox, "Check Box");
		click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
		if(verifyElementPresent(MLWalletShopItemsPage.objShippingDetails,getTextVal(MLWalletShopItemsPage.objShippingDetails,"Page"))){
			verifyElementPresent(MLWalletShopItemsPage.objAddAddress,getTextVal(MLWalletShopItemsPage.objAddAddress,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
			verifyElementPresent(MLWalletShopItemsPage.objHeader,getTextVal(MLWalletShopItemsPage.objHeader,"Header"));
			verifyElementPresent(MLWalletShopItemsPage.objProductNameInShippingDetails,getTextVal(MLWalletShopItemsPage.objProductNameInShippingDetails,"Product Name in Shipping Details"));
			verifyElementPresent(MLWalletShopItemsPage.objProductQuantity,getTextVal(MLWalletShopItemsPage.objProductQuantity,"Product Quantity"));
			verifyElementPresent(MLWalletShopItemsPage.objItemSubTotal,getTextVal(MLWalletShopItemsPage.objItemSubTotal,"Item SubTotal"));
			verifyElementPresent(MLWalletShopItemsPage.objTotalOrder,getTextVal(MLWalletShopItemsPage.objTotalOrder,"Total Order"));
			verifyElementPresent(MLWalletShopItemsPage.objServiceFee,getTextVal(MLWalletShopItemsPage.objServiceFee,"Service Fee"));
			verifyElementPresent(MLWalletShopItemsPage.objShippingFeeInShippingDetails,getTextVal(MLWalletShopItemsPage.objShippingFeeInShippingDetails,"Shipping Fee in Shipping Details"));
			Swipe("UP",2);
			verifyElementPresent(MLWalletShopItemsPage.objSelectPaymentMethod,getTextVal(MLWalletShopItemsPage.objSelectPaymentMethod,"Header"));
			verifyElementPresent(MLWalletShopItemsPage.objMLWallet,getTextVal(MLWalletShopItemsPage.objMLWallet,"Payment Method"));
			verifyElementPresent(MLWalletShopItemsPage.objOnlineBanking,getTextVal(MLWalletShopItemsPage.objOnlineBanking,"Payment Method"));
			Swipe("UP",1);
			verifyElementPresent(MLWalletShopItemsPage.objMerchandiseSubTotal,getTextVal(MLWalletShopItemsPage.objMerchandiseSubTotal,"Merchandise Subtotal"));
			verifyElementPresent(MLWalletShopItemsPage.objServiceTotal,getTextVal(MLWalletShopItemsPage.objServiceTotal,"Service Total"));
			verifyElementPresent(MLWalletShopItemsPage.objShippingTotal,getTextVal(MLWalletShopItemsPage.objShippingTotal,"Shipping Total"));
			verifyElementPresent(MLWalletShopItemsPage.objPaymentTotal,getTextVal(MLWalletShopItemsPage.objPaymentTotal,"Total Payment"));
			verifyElementPresent(MLWalletShopItemsPage.objPlaceOrderBtn,getTextVal(MLWalletShopItemsPage.objPlaceOrderBtn,"Button"));
			logger.info("MLS_TC_20, Shop Items Shipping Details Page UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_20", "MLS_TC_20, Shop Items Shipping Details Page UI validated");
			System.out.println("-----------------------------------------------------------");

		}
	}

	public void shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Select Branch Address Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		click(MLWalletShopItemsPage.objCheckBox, "Check Box");
		click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
		verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
		if(verifyElementPresent(MLWalletShopItemsPage.objInputFieldOne,getTextVal(MLWalletShopItemsPage.objInputFieldOne, "Select Branch Address Field 1"))) {
			verifyElementPresent(MLWalletShopItemsPage.objInputFieldTwo, getTextVal(MLWalletShopItemsPage.objInputFieldTwo,"Select Branch Address Field 2"));
			verifyElementPresent(MLWalletShopItemsPage.objInputFieldThree, getTextVal(MLWalletShopItemsPage.objInputFieldThree,"Select Branch Address Field 3"));
			verifyElementPresent(MLWalletShopItemsPage.objSaveBtn, getTextVal(MLWalletShopItemsPage.objSaveBtn, "Button"));
			verifyElementPresent(MLWalletShopItemsPage.objCancel, getTextVal(MLWalletShopItemsPage.objCancel, "Button"));
			logger.info("MLS_TC_21, Shop Items Select Branch Address Page UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_21", "MLS_TC_21, Shop Items Select Branch Address Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void shopItemsSaveBtnFunctionalityOnSelectBranchScreen_MLS_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Save Button Functionality On Select Branch Screen");
		shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21();
		click(MLWalletShopItemsPage.objSaveBtn, getTextVal(MLWalletShopItemsPage.objSaveBtn, "Button"));
		if(verifyElementPresent(MLWalletShopItemsPage.objAddressUpdateMsg,getTextVal(MLWalletShopItemsPage.objAddressUpdateMsg,"Message"))){
			verifyElementPresent(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
			logger.info("MLS_TC_22, Shop Items Select Branch Address Page UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_22", "MLS_TC_22, Shop Items Select Branch Address Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsCancelBtnFunctionalityOnSelectBranchScreen_MLS_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Cancel Button Functionality On Select Branch Screen");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		click(MLWalletShopItemsPage.objCheckBox, "Check Box");
		click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
		verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
		String sSelectAddressOne = getText(MLWalletShopItemsPage.objInputFieldOne);
		String sSelectAddressTwo = getText(MLWalletShopItemsPage.objInputFieldTwo);
		String sSelectAddressThree = getText(MLWalletShopItemsPage.objInputFieldThree);
		verifyElementPresentAndClick(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
		String sActualSelectAddressOne = getText(MLWalletShopItemsPage.objInputFieldOne);
		String sActualSelectAddressTwo = getText(MLWalletShopItemsPage.objInputFieldTwo);
		String sActualSelectAddressThree = getText(MLWalletShopItemsPage.objInputFieldThree);
		assertionValidation(sActualSelectAddressOne,sSelectAddressOne);
		assertionValidation(sActualSelectAddressTwo,sSelectAddressTwo);
		assertionValidation(sActualSelectAddressThree,sSelectAddressThree);
		logger.info("MLS_TC_23, Shop Items Cancel Button Functionality On Select Branch Screen validated");
		ExtentReporter.extentLoggerPass("MLS_TC_23", "MLS_TC_23, Shop Items Cancel Button Functionality On Select Branch Screen validated");
		System.out.println("-----------------------------------------------------------");
	}


	public void shopItemsPlaceOrderBtnFunctionalityOnShippingDetailsScreen_MLS_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Place Order Button Functionality On Shipping Details Screen");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		if(verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"))){
			logger.info("MLS_TC_24, Shop Items Place Order Button Functionality On Shipping Details Screen validated");
			ExtentReporter.extentLoggerPass("MLS_TC_24", "MLS_TC_24, Shop Items Place Order Button Functionality On Shipping Details Screen validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void shopItemsOneTimePinPageUIValidation_MLS_TC_25() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items One Time Pin Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		editAddressAndPlaceTheOrder();
		if(verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"))){
			verifyElementPresent(MLWalletShopItemsPage.objOTPMsg,getTextVal(MLWalletShopItemsPage.objOTPMsg,"Message"));
			verifyElementPresent(MLWalletShopItemsPage.objValidateBtn,getTextVal(MLWalletShopItemsPage.objValidateBtn,"Button"));
			verifyElementPresent(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Buttn"));
			logger.info("MLS_TC_25, Shop Items One Time Pin Page UI validated");
			ExtentReporter.extentLoggerPass("MLS_TC_25", "MLS_TC_25, Shop Items One Time Pin Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsCancelBtnFunctionalityOnOTPScreen_MLS_TC_26() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Cancel Button Functionality on OTP Screen");
		shopItemsOneTimePinPageUIValidation_MLS_TC_25();
		click(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
		if(verifyElementPresent(MLWalletShopItemsPage.objPlaceOrderBtn,getTextVal(MLWalletShopItemsPage.objPlaceOrderBtn,"Button"))){
			logger.info("MLS_TC_26, Shop Items Cancel Button Functionality on OTP Screen validated");
			ExtentReporter.extentLoggerPass("MLS_TC_26", "MLS_TC_26, Shop Items Cancel Button Functionality on OTP Screen validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Placing An Order without Selecting payment method");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		click(MLWalletShopItemsPage.objCheckBox, "Check Box");
		click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
		click(MLWalletShopItemsPage.objEditAddress, "Edit Address Tab");
		verifyElementPresent(MLWalletShopItemsPage.objSelectBranchPage, getTextVal(MLWalletShopItemsPage.objSelectBranchPage, "Page"));
		click(MLWalletShopItemsPage.objSaveBtn, "Save Button");
		verifyElementPresent(MLWalletShopItemsPage.objAddressSuccessfulMsg, getTextVal(MLWalletShopItemsPage.objAddressSuccessfulMsg, "Message"));
		click(MLWalletShopItemsPage.objOkBtn, "OK Button");
		Swipe("UP",3);
		click(MLWalletShopItemsPage.objPlaceOrderBtn, "Place Order Button");
		if(verifyElementPresent(MLWalletShopItemsPage.objErrorPopup,getTextVal(MLWalletShopItemsPage.objErrorPopup,"Error Popup"))){
			String sActualErrorPopup = getText(MLWalletShopItemsPage.objErrorPopup);
			String sExpectedErrorPopup = "Please select payment method.";
			assertionValidation(sActualErrorPopup,sExpectedErrorPopup);
			verifyElementPresent(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
			logger.info("MLS_TC_33, Please select payment method. Error message validated");
			ExtentReporter.extentLoggerPass("MLS_TC_33", "MLS_TC_33, Please select payment method. Error message validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsOkBtnFunctionalityOnErrorPopup_MLS_TC_34() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Ok Button Functionality On Error popup");
		shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33();
		click(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
		if(verifyElementNotPresent(MLWalletShopItemsPage.objErrorPopup,"Error popup",5)){
			logger.info("MLS_TC_34, Error popup disappeared after clicking on Ok Button is validated");
			ExtentReporter.extentLoggerPass("MLS_TC_34", "MLS_TC_34, Error popup disappeared after clicking on Ok Button is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsCartPageNavigation_MLS_TC_42() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Cart Page Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		if(verifyElementPresent(MLWalletShopItemsPage.objCartPageHeader,getTextVal(MLWalletShopItemsPage.objCartPageHeader,"Header"))){
			verifyElementPresent(MLWalletShopItemsPage.objCheckBox,"Item Check Box");
			verifyElementPresent(MLWalletShopItemsPage.objProductNameInCartPage,getTextVal(MLWalletShopItemsPage.objProductNameInCartPage,"Product Name in Cart Page"));
			verifyElementPresent(MLWalletShopItemsPage.objPriceInCartPage,getTextVal(MLWalletShopItemsPage.objPriceInCartPage,"Price"));
			verifyElementPresent(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
			verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,getTextVal(MLWalletShopItemsPage.objCheckOutBtn,"Button"));
			logger.info("MLS_TC_42, Shop Items Cart Page Navigation validated");
			ExtentReporter.extentLoggerPass("MLS_TC_42", "MLS_TC_42, Shop Items Cart Page Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsCancelBtnFunctionalityOnRemoveItemPopup_MLS_TC_43() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Cancel Button Functionality On Remove Item Popup");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
		verifyElementPresentAndClick(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
		if(verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,"Cart Page")){
			logger.info("MLS_TC_43, Shop Items Cancel Button Functionality On Remove Item Popup validated");
			ExtentReporter.extentLoggerPass("MLS_TC_43", "MLS_TC_43, Shop Items Cancel Button Functionality On Remove Item Popup validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void shopItemsOkBtnFunctionalityOnRemoveItemPopup_MLS_TC_44() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Ok Button Functionality On Remove Item Popup");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
		verifyElementPresentAndClick(MLWalletShopItemsPage.objYesBtn,getTextVal(MLWalletShopItemsPage.objYesBtn,"Button"));
		if(verifyElementNotPresent(MLWalletShopItemsPage.objProductNameInCartPage,"Cart Page",5)){
			logger.info("MLS_TC_44, Shop Items Ok Button Functionality On Remove Item Popup validated");
			ExtentReporter.extentLoggerPass("MLS_TC_44", "MLS_TC_44, Shop Items Ok Button Functionality On Remove Item Popup validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsNavigationToProfileOption_MLS_TC_46() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Navigation To Profile Option");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		click(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
		click(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Option"));
		if(verifyElementPresent(MLWalletShopItemsPage.objMyAccount,getTextVal(MLWalletShopItemsPage.objMyAccount,"Page"))){
			logger.info("MLS_TC_46, Shop Items Navigation To Profile Option validated");
			ExtentReporter.extentLoggerPass("MLS_TC_46", "MLS_TC_46, Shop Items Navigation To Profile Option validated");
			System.out.println("-----------------------------------------------------------");
		}
 	}

	public void shopItemsProfileScreenNavigation_MLS_TC_47() throws Exception {
		ExtentReporter.HeaderChildNode("Shop items Profile Screen Navigation");
		shopItemsNavigationToProfileOption_MLS_TC_46();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountDropdown,getTextVal(MLWalletShopItemsPage.objMyAccountDropdown,"Dropdown"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Dropdown Element"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
		if(verifyElementPresent(MLWalletShopItemsPage.objMyProfileHeader,getTextVal(MLWalletShopItemsPage.objMyProfileHeader,"Header"))){
			logger.info("MLS_TC_47, Shop items Profile Screen Navigation validated");
			ExtentReporter.extentLoggerPass("MLS_TC_47", "MLS_TC_47, Shop items Profile Screen Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsMyAccountPopupCrossBtnFunctionality_MLS_TC_48() throws Exception {
		ExtentReporter.HeaderChildNode("Shop items My Account Popup Cross Btn Functionality");
		shopItemsNavigationToProfileOption_MLS_TC_46();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountDropdown,getTextVal(MLWalletShopItemsPage.objMyAccountDropdown,"Dropdown"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Dropdown Element"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
		if(verifyElementPresent(MLWalletShopItemsPage.objMyProfileHeader,getTextVal(MLWalletShopItemsPage.objMyProfileHeader,"Header"))){
			logger.info("MLS_TC_48, Shop items My Account Popup Cross Btn Functionality validated");
			ExtentReporter.extentLoggerPass("MLS_TC_48", "MLS_TC_48, Shop items My Account Popup Cross Btn Functionality validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsMyPurchasePageNavigation_MLS_TC_49() throws Exception {
		ExtentReporter.HeaderChildNode("Shop items My Purchase Page Navigation");
		shopItemsNavigationToProfileOption_MLS_TC_46();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyPurchase,getTextVal(MLWalletShopItemsPage.objMyPurchase,"Option"));
		verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
		if(verifyElementPresent(MLWalletShopItemsPage.objOrderDetails,getTextVal(MLWalletShopItemsPage.objOrderDetails,"Button"))){
			logger.info("MLS_TC_49, Shop items My Purchase Page Navigation validated");
			ExtentReporter.extentLoggerPass("MLS_TC_49", "MLS_TC_49, Shop items My Purchase Page Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void shopItemsSubTotalVerificationWithOutSelectingTheItemsInCart_MLS_TC_59() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Total subTotal Verification without selecting the items in the Cart");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		if(verifyElementPresent(MLWalletShopItemsPage.objSubtotalAmount,getTextVal(MLWalletShopItemsPage.objSubtotalAmount,"SubTotal Items"))){
			String sActualSubtotalItems = getText(MLWalletShopItemsPage.objSubtotalAmount);
			String sExceptedSubtotalItems = "P0.00";
			assertionValidation(sActualSubtotalItems,sExceptedSubtotalItems);
			logger.info("MLS_TC_59, Shop Items Total subTotal Verification without selecting the items in the Cart validated");
			ExtentReporter.extentLoggerPass("MLS_TC_59", "MLS_TC_59, Shop Items Total subTotal Verification without selecting the items in the Cart validated");
			System.out.println("-----------------------------------------------------------");
		}
	}



	public void shopItemsSubTotalVerificationWithSelectingTheItemsInCart_MLS_TC_60() throws Exception {
		ExtentReporter.HeaderChildNode("Shop Items Total subTotal Verification with selecting the items in the Cart");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		shopItemsNavigation();
		selectItemAndAddToCart();
		Swipe("down", 4);
		navigationToCart();
		verifyElementPresentAndClick(MLWalletShopItemsPage.objCheckBox, "Check Box");
		waitTime(5000);
		if(verifyElementPresent(MLWalletShopItemsPage.objSubtotalAmount,getTextVal(MLWalletShopItemsPage.objSubtotalAmount,"SubTotal Items"))){
			String sActualSubtotalItems = getText(MLWalletShopItemsPage.objSubtotalAmount);
			String sExceptedSubtotalItems = "P500.00";
			assertionValidation(sActualSubtotalItems,sExceptedSubtotalItems);
			logger.info("MLS_TC_60, Shop Items Total subTotal Verification with selecting the items in the Cart validated");
			ExtentReporter.extentLoggerPass("MLS_TC_60", "MLS_TC_60, Shop Items Total subTotal Verification with selecting the items in the Cart validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

//=========================================== Cash In Via Bank ================================================//
//======================= Generalized methods for Cash In Via Bank ===========================================//

	public void selectBankAndInputAmount(String sAmount) throws Exception {
		if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"))) {
			click(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
			click(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Button"));
			verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"));
			click(MLWalletCashInBank.objTestBankOnline, getTextVal(MLWalletCashInBank.objTestBankOnline, "Bank"));
			verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"));
			verifyElementPresent(MLWalletCashInBank.objBankCashIn, getTextVal(MLWalletCashInBank.objBankCashIn, "Text"));
			type(MLWalletCashInBank.objAmountEditField, sAmount, "Amount Text Field");
			click(MLWalletCashInBank.objNextBtn, getTextVal(MLWalletCashInBank.objNextBtn, "Button"));
			Thread.sleep(3000);
		}
	}

	public void dragonPayChargesMsgValidation() throws Exception {
		if (verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"))) {
			String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
			String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
			assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
			click(MLWalletCashInBank.objContinueBtn, getTextVal(MLWalletCashInBank.objContinueBtn, "Button"));
		}
	}

	public void reviewTransactionValidation() throws Exception {
		verifyElementPresent(MLWalletCashInBank.objReviewTransaction, getTextVal(MLWalletCashInBank.objReviewTransaction, "Page"));
		Swipe("UP", 1);
		if (verifyElementPresent(MLWalletCashInBank.objBankTransferCutOffTime, getTextVal(MLWalletCashInBank.objBankTransferCutOffTime, "Message"))) {
			String sBankTransferTime = getText(MLWalletCashInBank.objBankTransferCutOffTime);
			String sExpectedBankTransferTime = "Bank transfers after 1:00PM are posted on the next banking day.";
			assertionValidation(sBankTransferTime, sExpectedBankTransferTime);
		}
		click(MLWalletCashInBank.objNextBtn, getTextVal(MLWalletCashInBank.objNextBtn, "Button"));
	}


	public void bankUserLogin(String sLoginId, String sPassword) throws Exception {
		explicitWaitVisible(MLWalletCashInBank.objReferenceNumberMsg, 5);
		if (verifyElementPresent(MLWalletCashInBank.objReferenceNumberMsg, getTextVal(MLWalletCashInBank.objReferenceNumberMsg, "Reference Information"))) {
			type(MLWalletCashInBank.objLoginIdTxtField, sLoginId, "Login Id Text Field");
			type(MLWalletCashInBank.objPasswordTxtField, sPassword, "Password Text Field");
		}
	}


	public void locationPopUpValidation() throws Exception {
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "PopUp Msg"))) {
			verifyElementPresent(MLWalletHomePage.obPopupAllowBtn, getTextVal(MLWalletHomePage.obPopupAllowBtn, "Button"));
			verifyElementPresent(MLWalletHomePage.objLocationPopUpAllowOnceBtn, getTextVal(MLWalletHomePage.objLocationPopUpAllowOnceBtn, "Button"));
			verifyElementPresent(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
		}
	}

	public void permissionDenyPopUp() throws Exception {
		locationPopUpValidation();
		click(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
		if (verifyElementPresent(MLWalletHomePage.objPermissionDeniedPopUp, getTextVal(MLWalletHomePage.objPermissionDeniedPopUp, "PopUp"))) {
			verifyElementPresent(MLWalletHomePage.objOpenSettingBtn, getTextVal(MLWalletHomePage.objOpenSettingBtn, "Button"));
			verifyElementPresent(MLWalletHomePage.objCloseBtn, getTextVal(MLWalletHomePage.objCloseBtn, "Button"));
		}
	}

	public void permissionDenyCloseBtnFunctionality() throws Exception {
		permissionDenyPopUp();
		click(MLWalletHomePage.objCloseBtn, getTextVal(MLWalletHomePage.objCloseBtn, "Button"));
	}

	public void permissionDenyOpenSettingsBtnFunctionality() throws Exception {
		permissionDenyPopUp();
		click(MLWalletHomePage.objOpenSettingBtn, getTextVal(MLWalletHomePage.objOpenSettingBtn, "Button"));
	}

	public void locationPopUpAllowFunctionality() throws Exception {
		locationPopUpValidation();
		click(MLWalletHomePage.obPopupAllowBtn, getTextVal(MLWalletHomePage.obPopupAllowBtn, "Button"));
	}

	public void internetConnectionError() throws Exception {
		verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"));
		verifyElementPresent(MLWalletHomePage.objInternetConnectionMsg, getTextVal(MLWalletHomePage.objInternetConnectionMsg, "Error message"));
		verifyElementPresent(MLWalletHomePage.objOkBtn, getTextVal(MLWalletHomePage.objOkBtn, "Button"));
		setWifiConnectionToONOFF("ON");
	}

//===================================================================================================================//

	public void cashInViaBank_CIBA_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
		click(MLWalletCashInBank.objWebContinueBtn, "Continue Button");
		click(MLWalletCashInBank.objPayBtn, getTextVal(MLWalletCashInBank.objPayBtn, "Button"));
		verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber, getTextVal(MLWalletCashInBank.objBankReferenceNumber, "Reference Number"));
		verifyElementPresent(MLWalletCashInBank.objStatus, getTextVal(MLWalletCashInBank.objStatus, "Status"));
		verifyElementPresent(MLWalletCashInBank.objMessage, getTextVal(MLWalletCashInBank.objMessage, "Message"));
		if (verifyElementPresent(MLWalletCashInBank.objSuccessMsg, getTextVal(MLWalletCashInBank.objSuccessMsg, "Message"))) {
			logger.info("CIBA_TC_01, Cash In Via Bank validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_01", "CIBA_TC_01, Cash In Via Bank validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankMinimumTransactionLimit_CIBA_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Minimum Transaction Limit");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("20");
		if (verifyElementPresent(MLWalletCashInBank.objMinimumTransactionPopupMsg, getTextVal(MLWalletCashInBank.objMinimumTransactionPopupMsg, "Pop Message"))) {
			String sMinimumTransactionPopupMsg = getText(MLWalletCashInBank.objMinimumTransactionPopupMsg);
			String sExpectedPopupMsg = "The supplied amount is less than the required minimum transaction limit";
			assertionValidation(sMinimumTransactionPopupMsg, sExpectedPopupMsg);
			logger.info("CIBA_TC_03, Minimum transaction limit pop up message is validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_03", "CIBA_TC_03, Minimum transaction limit pop up message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankMaximumTransaction_CIBA_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Maximum Transaction");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("60000");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		if (verifyElementPresent(MLWalletCashInBank.objMaxLimitErrorMsg, getTextVal(MLWalletCashInBank.objMaxLimitErrorMsg, "Error Message"))) {
			String sMaximumLimitErrorMsg = getText(MLWalletCashInBank.objMaxLimitErrorMsg);
			String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
			logger.info("CIBA_TC_04, The maximum send money per transaction - Error Message is validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_04", "CIBA_TC_04, The maximum send money per transaction - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashInViaBankInvalidAmount_STW_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Invalid Amount");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("0");
		if (verifyElementPresent(MLWalletCashInBank.objInvalidAmountMsg, getTextVal(MLWalletCashInBank.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(MLWalletCashInBank.objInvalidAmountMsg);
			String sExpectedErrorMsg = "The amount should not be less than 1";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("STW_TC_05, The amount should not be less than 1 - Error Message is validated");
			ExtentReporter.extentLoggerPass("STW_TC_05", "STW_TC_05, The amount should not be less than 1 - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankNavigation_STW_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		explicitWaitVisibility(MLWalletCashInBank.objCashIn,5);
		if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Page"))) {
			logger.info("STW_TC_06, Navigated to Cash In Page Validated");
			ExtentReporter.extentLoggerPass("STW_TC_06", "STW_TC_06, Navigated to Cash In Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInUIValidation_STW_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		explicitWaitVisibility(MLWalletCashInBank.objCashIn,5);
		if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Page"))) {
			verifyElementPresent(MLWalletCashInBank.objCashInOption, getTextVal(MLWalletCashInBank.objCashInOption, "Header"));
			verifyElementPresent(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Option"));
			verifyElementPresent(MLWalletCashInBank.objBranchName, getTextVal(MLWalletCashInBank.objBranchName, "Option"));
			logger.info("STW_TC_07, Cash In Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_07", "STW_TC_07, Cash In Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInPageBackArrowBtnValidation_STW_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Page Back Button Validation");
		cashInUIValidation_STW_TC_07();
		verifyElementPresentAndClick(MLWalletCashInBank.objCashInBackArrowBtn, "Cash In Back Button");
		explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("STW_TC_08, Cash In Page Back Button validated");
			ExtentReporter.extentLoggerPass("STW_TC_08", "STW_TC_08, Cash In Page Back Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInSelectBankPageUIValidation_STW_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Select Bank Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		verifyElementPresentAndClick(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Option"));
		if (verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Header"))) {
			verifyElementPresent(MLWalletCashInBank.objSearchBank, "Search Bank Input Field");
			if (verifyElementDisplayed(MLWalletCashInBank.objBanks)) {
				List<WebElement> values = findElements(MLWalletCashInBank.objBanks);
				for (int i = 0; i < values.size(); i++) {
					String savedRecipientName = values.get(i).getText();
					logger.info("Bank : " + savedRecipientName + " is displayed");
					ExtentReporter.extentLogger(" ", "Bank : " + savedRecipientName + " is displayed");
				}
			}
			logger.info("STW_TC_09, Cash In Select Bank Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_09", "STW_TC_09, Cash In Select Bank Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashInViaBankSearchInvalidBank_STW_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Invalid Bank");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		verifyElementPresentAndClick(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Option"));
		if (verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Header"))) {
			type(MLWalletCashInBank.objSearchBank, prop.getproperty("Invalid_BankName"), "Search Bank Input Field");
			if (verifyElementPresent(MLWalletCashInBank.objNoRecentTransactionTxt, getTextVal(MLWalletCashInBank.objNoRecentTransactionTxt, "Text"))) {
				logger.info("STW_TC_10, Cash In Select Bank Page UI validated");
				ExtentReporter.extentLoggerPass("STW_TC_10", "STW_TC_10, Cash In Select Bank Page UI validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Select Bank Page BackArrow Button Validation");
		cashInViaBankSearchInvalidBank_STW_TC_10();
		verifyElementPresentAndClick(MLWalletCashInBank.objSelectBankBackBtn, "Select A Bank Page Back Button");
		if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Page"))) {
			logger.info("STW_TC_11, Select Bank Page Back Button validated");
			ExtentReporter.extentLoggerPass("STW_TC_11", "STW_TC_11, Select Bank Page Back Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankDragonPayPageUIValidation_STW_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Dragon Pay Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		click(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
		click(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Button"));
		verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"));
		click(MLWalletCashInBank.objTestBankOnline, getTextVal(MLWalletCashInBank.objTestBankOnline, "Bank"));
		if (verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"))) {
			verifyElementPresent(MLWalletCashInBank.objBankCashIn, getTextVal(MLWalletCashInBank.objBankCashIn, "Text"));
			verifyElementPresent(MLWalletCashInBank.objAmountEditField, "Amount Text Field");
			verifyElementPresent(MLWalletCashInBank.objNextBtn, getTextVal(MLWalletCashInBank.objNextBtn, "Button"));
			logger.info("STW_TC_12, Cash In Via Bank Dragon Pay Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_12", "STW_TC_12, Cash In Via Bank Dragon Pay Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankDragonPayBackBtnValidation_STW_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Dragon Pay Back Button Validation");
		cashInViaBankDragonPayPageUIValidation_STW_TC_12();
		verifyElementPresentAndClick(MLWalletCashInBank.objDragonPayBackBtn, "Dragon Pay Back Button");
		if (verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"))) {
			logger.info("STW_TC_13, Cash In Via Bank Dragon Pay Back Button validated");
			ExtentReporter.extentLoggerPass("STW_TC_13", "STW_TC_13, Cash In Via Bank Dragon Pay Back Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashInViaBankReviewTransactionPageUIValidation_STW_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Review Transaction Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		if (verifyElementPresent(MLWalletCashInBank.objReviewTransaction, getTextVal(MLWalletCashInBank.objReviewTransaction, "Page"))) {
			verifyElementPresent(MLWalletCashInBank.objReceiverName, getTextVal(MLWalletCashInBank.objReceiverName, "Receiver's Name"));
			verifyElementPresent(MLWalletCashInBank.objBankName, getTextVal(MLWalletCashInBank.objBankName, "Bank Name"));
			verifyElementPresent(MLWalletCashInBank.objPrincipalAmount, getTextVal(MLWalletCashInBank.objPrincipalAmount, "Principal Amount"));
			verifyElementPresent(MLWalletCashInBank.objServiceFee, getTextVal(MLWalletCashInBank.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletCashInBank.objEmail, getTextVal(MLWalletCashInBank.objEmail, "Email"));
			Swipe("UP", 1);
			verifyElementPresent(MLWalletCashInBank.objBankTransferCutOffTime, getTextVal(MLWalletCashInBank.objBankTransferCutOffTime, "Message"));
			verifyElementPresent(MLWalletCashInBank.objNextBtn, getTextVal(MLWalletCashInBank.objNextBtn, "Button"));
			logger.info("STW_TC_14, Cash In Via Bank Review Transaction Page UI validated");
			ExtentReporter.extentLoggerPass("STW_TC_14", "STW_TC_14, Cash In Via Bank Review Transaction Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Review Transaction Back Button Validation");
		cashInViaBankReviewTransactionPageUIValidation_STW_TC_14();
		verifyElementPresentAndClick(MLWalletCashInBank.objReviewTransactionBackBtn, "Review Transaction Back Button");
		if (verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"))) {
			logger.info("STW_TC_15, Cash In Via Bank Review Transaction Back Button validated");
			ExtentReporter.extentLoggerPass("STW_TC_15", "STW_TC_15, Cash In Via Bank Review Transaction Back Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankPendingTransaction_CIBA_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Pending Transaction");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
		click(MLWalletCashInBank.objWebContinueBtn, "Continue Button");
		click(MLWalletCashInBank.objPayBtn, getTextVal(MLWalletCashInBank.objPayBtn, "Button"));
		verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber, getTextVal(MLWalletCashInBank.objBankReferenceNumber, "Reference Number"));
		verifyElementPresent(MLWalletCashInBank.objStatus, getTextVal(MLWalletCashInBank.objStatus, "Status"));
		verifyElementPresent(MLWalletCashInBank.objMessage, getTextVal(MLWalletCashInBank.objMessage, "Message"));
		verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn, getTextVal(MLWalletCashInBank.objCompleteTransactionBtn, "Button"));
		Swipe("DOWN", 2);
		if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Transaction"))) {
			verifyElementPresent(MLWalletCashInBank.objPendingStatus, getTextVal(MLWalletCashInBank.objPendingStatus, "Status"));
			String sStatus = getText(MLWalletCashInBank.objPendingStatus);
			String sExpectedStatus = "Pending";
			assertionValidation(sStatus, sExpectedStatus);
			logger.info("CIBA_TC_17, An entry in recent transaction for current Cash In should be present with status pending validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_17", "CIBA_TC_17, An entry in recent transaction for current Cash In should be present with status pending validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankWithExistingPendingTransaction_CIBA_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank With Existing Pending Transaction");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletTransactionHistoryPage.objRecentTransaction, getText(MLWalletTransactionHistoryPage.objRecentTransaction));
		Swipe("UP", 2);
		click(MLWalletTransactionHistoryPage.objSeeMoreBtn, "See More Button");
		scrollToFirstHorizontalScrollableElement("Cash In");
		click(MLWalletTransactionHistoryPage.objCashInTab, "Cash In");
		Thread.sleep(3000);
		scrollToVertical("Pending");
		if (verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"))) {
			click(MLWalletCashInBank.objTransactionHistoryBackBtn, "Transaction History Back Button");
			Swipe("DOWN", 1);
			selectBankAndInputAmount("100");
			dragonPayChargesMsgValidation();
			reviewTransactionValidation();
			enterOTP(prop.getproperty("Valid_OTP"));
			enableLocation_PopUp();
			bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
			click(MLWalletCashInBank.objWebContinueBtn, "Continue Button");
			click(MLWalletCashInBank.objPayBtn, getTextVal(MLWalletCashInBank.objPayBtn, "Button"));
			verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber, getTextVal(MLWalletCashInBank.objBankReferenceNumber, "Reference Number"));
			verifyElementPresent(MLWalletCashInBank.objStatus, getTextVal(MLWalletCashInBank.objStatus, "Status"));
			verifyElementPresent(MLWalletCashInBank.objMessage, getTextVal(MLWalletCashInBank.objMessage, "Message"));
			verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn, getTextVal(MLWalletCashInBank.objCompleteTransactionBtn, "Button"));
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Transaction"))) {
				verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
				logger.info("CIBA_TC_20, Cash In Via Bank With Existing Pending Transaction Validated");
				ExtentReporter.extentLoggerPass("CIBA_TC_20", "CIBA_TC_20, Cash In Via Bank With Existing Pending Transaction validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cancelButtonValidationInDragonPayPopUp_CIBA_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Cancel Button Validation In Dragon Pay PopUp");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"));
		String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
		String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
		assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
		click(MLWalletCashInBank.objCancelBtn, getTextVal(MLWalletCashInBank.objCancelBtn, "Button"));
		if (verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"))) {
			logger.info("CIBA_TC_21, Cancel Button Validated In Dragon Pay PopUp");
			ExtentReporter.extentLoggerPass("CIBA_TC_21", "CIBA_TC_21, Cancel Button Validated In Dragon Pay PopUp");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankTappingOutsideTheDragonPayPopupValidation_CIBA_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Tapping Outside the Dragon Pay Popup Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"));
		String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
		String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
		assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
		tapUsingCoordinates(500,1000);
		logger.info("Clicked OutSide the Dragon Pay Popup");
		if (verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Popup Message"))) {
			logger.info("CIBA_TC_22, Cash In Via Bank, After Tapping Outside the Dragon Pay Popup, Popup doesn't closed");
			ExtentReporter.extentLoggerPass("CIBA_TC_22", "CIBA_TC_22, Cash In Via Bank, After Tapping Outside the Dragon Pay Popup, Popup doesn't closed");
			System.out.println("-----------------------------------------------------------");
		}
	}






	public void cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Invalid Amount Field Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("");
		if (verifyElementPresent(MLWalletCashInBank.objInvalidAmountMsg, getTextVal(MLWalletCashInBank.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(MLWalletCashInBank.objInvalidAmountMsg);
			String sExpectedErrorMsg = "Amount is required";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("CIBA_TC_23, Amount is required - Error Message is validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_23", "CIBA_TC_23, Amount is required - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankBuyerTierLevel_CIBA_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Buyer Tier Level");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		if (verifyElementPresent(MLWalletCashInBank.objMaxLimitTxt, getTextVal(MLWalletCashInBank.objMaxLimitTxt, "Text Message"))) {
			String sErrorMessage = getText(MLWalletCashInBank.objMaxLimitTxt);
			String ExpectedTxt = "Bank Cash-in is not allowed for customers at this verification level. Please upgrade your account to use this service.";
			assertionValidation(sErrorMessage, ExpectedTxt);
			verifyElementPresent(MLWalletCashInBank.objUpgradeNowBtn, getTextVal(MLWalletCashInBank.objUpgradeNowBtn, "Button"));
			logger.info("CIBA_TC_24, Branch Cash-In is not allowed for customers at this verification level. Error Message is Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_24", "CIBA_TC_24, Branch Cash-In is not allowed for customers at this verification level. Error Message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankSemiVerifiedUserMaxLimit_CIBA_TC_27() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Maximum Limit");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		selectBankAndInputAmount("60000");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		waitTime(5000);
		if (verifyElementPresent(MLWalletCashInBank.objBankMaxLimitTxt, getTextVal(MLWalletCashInBank.objBankMaxLimitTxt, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInBank.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("CIBA_TC_27, To validate Maximum Limit of transaction");
			ExtentReporter.extentLoggerPass("CIBA_TC_27", "CIBA_TC_27, To validate Maximum Limit of transaction");
		}
	}

	public void cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Maximum Limit");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		selectBankAndInputAmount("60000");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		waitTime(5000);
		if (verifyElementPresent(MLWalletCashInBank.objBankMaxLimitTxtFullyVerified, getTextVal(MLWalletCashInBank.objBankMaxLimitTxtFullyVerified, "Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInBank.objBankMaxLimitTxtFullyVerified);
			String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("CIBA_TC_28, To validate Maximum Limit of transaction");
			ExtentReporter.extentLoggerPass("CIBA_TC_28", "CIBA_TC_28, To validate Maximum Limit of transaction");
		}
	}

	public void cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Transaction Details Page UI Validation");
		cashInViaBank_CIBA_TC_01();
		verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn, getTextVal(MLWalletCashInBank.objCompleteTransactionBtn, "Button"));
		Swipe("DOWN", 2);
		Swipe("UP", 2);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		click(MLWalletCashInBank.objCashInTransaction, getTextVal(MLWalletCashInBank.objCashInTransaction, "Transaction"));
		if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objBank, getTextVal(MLWalletTransactionHistoryPage.objBank, "Bank"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashIn, getTextVal(MLWalletTransactionHistoryPage.objTotalCashIn, "Total Cash In"));
			logger.info("CIBA_TC_29, Cash In Via Bank Transaction Details Page UI Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_29", "CIBA_TC_29, Cash In Via Bank Transaction Details Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Dragon Pay charges popup Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		selectBankAndInputAmount("100");
		if (verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"))) {
			String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
			String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
			assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
			logger.info("CIBA_TC_32, Cash In Via Bank Dragon Pay charges popup Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_32", "CIBA_TC_32, Cash In Via Bank Dragon Pay charges popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankLocationPopupValidation_CIBA_TC_35() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Location popup Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("CIBA_TC_35, Cash In Via Bank Location popup Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_35", "CIBA_TC_35, Cash In Via Bank Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankLocationDenyFunctionality_CIBA_TC_36() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Location Deny Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("CIBA_TC_36, Cash In Via Bank Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_36", "CIBA_TC_36, Cash In Via Bank Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankLocationPermissionDenyCloseBtnFunctionality_CIBA_TC_37() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Location Permission Deny Close Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletCashInBank.objReviewTransaction,getTextVal(MLWalletCashInBank.objReviewTransaction,"Page"))){
				logger.info("CIBA_TC_37, Cash In Via Bank Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBA_TC_37", "CIBA_TC_37, Cash In Via Bank Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBankLocationPermissionDenyOpenSettingsBtnFunctionality_CIBA_TC_38() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Location Permission Deny open Settings Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(MLWalletCashInBank.objAppInfo, getTextVal(MLWalletCashInBank.objAppInfo, "Page"))) {
				logger.info("CIBA_TC_38, Cash In Via Bank Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBA_TC_38", "CIBA_TC_38, Cash In Via Bank Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBankLocationPopUpAllowFunctionality_CIBA_TC_39() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Location popup Allow Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletCashInBank.objLoginIdTxtField,"Bank Page")){
				logger.info("CIBA_TC_39, Cash In Via Bank Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBA_TC_39", "CIBA_TC_39, Cash In Via Bank Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBankInternetInterruptionWhileEnteringOTP_CIBA_TC_40() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Internet Interruption While Entering OTP Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		waitTime(15000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("CIBA_TC_40, Cash In Via Bank Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_40", "CIBA_TC_40, Cash In Via Bank Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}


	public void cashInViaBankTransactionValidationAfterMinimizingApp_CIBA_TC_43() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Bank Transaction Validation After Minimizing App");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		selectBankAndInputAmount("100");
		dragonPayChargesMsgValidation();
		reviewTransactionValidation();
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		waitTime(3000);
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if(verifyElementPresent(MLWalletCashInBank.objLoginIdTxtField,"Bank Page")){
			logger.info("CIBA_TC_43, Cash In Via Bank Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("CIBA_TC_43", "CIBA_TC_43, Cash In Via Bank Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}







//=============================================Pay Bills===============================================================//
//=============================Generalized Methods=====================================================//

	public void getBillers(By sWebElement){
		List<WebElement> list = findElements(sWebElement);

		for(int i=0 ;i<list.size(); i++){
			String billers= list.get(i).getText();
			logger.info(billers+ " Biller is displayed");
		}
	}

	public void payBillsNavigation() throws Exception {
		verifyElementPresent(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
		click(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
	}


	public void searchBiller() throws Exception {
		type(MLWalletPayBillsPage.objSearchBiller,prop.getproperty("Biller_Name"),"Search Biller");
		verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
		click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
	}

	public void billerDetails(String sFirstName,String sLastName,String sMiddleName, String sAmount) throws Exception {
		if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
			type(MLWalletPayBillsPage.objAccountNumberField, prop.getproperty("AccountNumber"),"Account Number Text Field");
			type(MLWalletPayBillsPage.objFirstNameField,sFirstName ,"First Name Text Field");
			type(MLWalletPayBillsPage.objMiddleNameField,sMiddleName ,"Middle Name Text Field");
			type(MLWalletPayBillsPage.objLastnameField,sLastName ,"Last Name Text Field");
			Swipe("UP",1);
			type(MLWalletPayBillsPage.objAmountField, sAmount,"Amount to Send Text Field");
			click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
		}
	}

	public void addSelectedBiller() throws Exception {
		if (verifyElementPresent(MLWalletPayBillsPage.objAddSeectedBiller, "Edit Biller")) {
			click(MLWalletPayBillsPage.objAddSeectedBiller, "Edit Biller");
			click(MLWalletPayBillsPage.objBillerListSearchBiller,"Biller List Search Biller");
			type(MLWalletPayBillsPage.objBillerListSearchBiller, prop.getproperty("Biller_Name"), "Biller List Search Biller");
			explicitWaitVisible(MLWalletPayBillsPage.objMisBillsPayBiller,5);
			click(MLWalletPayBillsPage.objMisBillsPayBiller, getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller, "Biller"));
			click(MLWalletPayBillsPage.objMisBillsPayBiller, getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller, "Biller"));
		}
	}

	public void addBiller() throws Exception {
		if(verifyElementPresent(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"))) {
			click(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"));
			explicitWaitVisible(MLWalletPayBillsPage.objAddBiller,5);
			click(MLWalletPayBillsPage.objAddBiller, getTextVal(MLWalletPayBillsPage.objAddBiller, "Button"));
			addSelectedBiller();
			if (verifyElementPresent(MLWalletPayBillsPage.objAddBillers, getTextVal(MLWalletPayBillsPage.objAddBillers, "Page"))) {
				type(MLWalletPayBillsPage.objAddAccountNumber, prop.getproperty("AccountNumber"), "Account Number in Add Biller");
				type(MLWalletPayBillsPage.objAddFirstName, prop.getproperty("First_Name"), "First Name in Add Biller");
				type(MLWalletPayBillsPage.objAddMiddleName, prop.getproperty("Middle_Name"), "Middle Name in Add Biller");
				type(MLWalletPayBillsPage.objAddLastName, prop.getproperty("Last_Name"), "Last Name in Add Biller");
				type(MLWalletPayBillsPage.objAddNickName, prop.getproperty("Nick_Name"), "Nick Name in Add Biller");
				click(MLWalletPayBillsPage.objProceedBtn, getTextVal(MLWalletPayBillsPage.objProceedBtn, "button"));
			}
		}
	}

	public void selectAddedBiler() throws Exception {
		verifyElementPresentAndClick(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objSavedBillers,getTextVal(MLWalletPayBillsPage.objSavedBillers,"Page"))) {
			type(MLWalletPayBillsPage.objSearchBillerInSavedBillers, prop.getproperty("Last_Name"), "Search Recipient Text Field");
			verifyElementPresent(MLWalletPayBillsPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), "Recipient"));
			click(MLWalletPayBillsPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Last_Name"), prop.getproperty("First_Name")), "Recipient"));
		}
	}

//================================================================================================================//


	public void payBillsPageValidation_PB_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Page Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		if(verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Button"));
			verifyElementExist(MLWalletPayBillsPage.objBillers,getTextVal(MLWalletPayBillsPage.objBillers,"Text"));
			logger.info("PB_TC_01, Pay Bills Page validated");
			ExtentReporter.extentLoggerPass("PB_TC_01", "PB_TC_01, Pay Bills Page validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void billerCategories_PB_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Biller Categories");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		click(MLWalletPayBillsPage.objCategories,getTextVal(MLWalletPayBillsPage.objCategories,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objBankingAndFinance,getTextVal(MLWalletPayBillsPage.objBankingAndFinance,"Button"))){
			click(MLWalletPayBillsPage.objBankingAndFinance,getTextVal(MLWalletPayBillsPage.objBankingAndFinance,"Biller Category"));
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objCharityAndReligious,getTextVal(MLWalletPayBillsPage.objCharityAndReligious,"Biller Category"))){
			click(MLWalletPayBillsPage.objCharityAndReligious,getTextVal(MLWalletPayBillsPage.objCharityAndReligious,"Biller Category"));
			getBillers(MLWalletPayBillsPage.objCharityAndReligiousBillers);
			click(MLWalletPayBillsPage.objCharityAndReligious,getTextVal(MLWalletPayBillsPage.objCharityAndReligious,"Biller Category"));
		}
		Swipe("UP",1);
		if(verifyElementPresent(MLWalletPayBillsPage.objUtilities,getTextVal(MLWalletPayBillsPage.objUtilities,"Biller Category"))) {
			click(MLWalletPayBillsPage.objUtilities, getTextVal(MLWalletPayBillsPage.objUtilities, "Biller Category"));
			Swipe("UP", 1);
			getBillers(MLWalletPayBillsPage.objUtilitiesBillers);
			click(MLWalletPayBillsPage.objUtilities, getTextVal(MLWalletPayBillsPage.objUtilities, "Biller Category"));
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objOthers,getTextVal(MLWalletPayBillsPage.objOthers,"Biller Category"))){
			click(MLWalletPayBillsPage.objOthers,getTextVal(MLWalletPayBillsPage.objOthers,"Biller Category"));
			Swipe("UP",1);
			getBillers(MLWalletPayBillsPage.objOthersBillers);
			click(MLWalletPayBillsPage.objOthers,getTextVal(MLWalletPayBillsPage.objOthers,"Biller Category"));
		}
		logger.info("PB_TC_02, Biller Categories validated");
		ExtentReporter.extentLoggerPass("PB_TC_02", "PB_TC_02, Biller Categories validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void billersInAlphabeticalOrder_PB_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Billers In Alphabetical Order");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		click(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
		swipeDownUntilElementVisible("SAGIP KAPAMILYA");
		swipeDownUntilElementVisible("ZYBITECH");
		logger.info("PB_TC_03, Billers swiped until Z Alphabet, and all the Billers are displayed in alphabetical order");
		ExtentReporter.extentLoggerPass("PB_TC_03", "PB_TC_03, Billers swiped until Z Alphabet, and all the Billers are displayed in alphabetical order");
		System.out.println("-----------------------------------------------------------");
	}

	public void selectBiller_PB_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Select Biller");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		click(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
		swipeDownUntilElementVisible("AIR ASIA BILLSPAYMENT");
		if(verifyElementPresent(MLWalletPayBillsPage.objAirAsia,getTextVal(MLWalletPayBillsPage.objAirAsia,"Biller"))){
			String sAirAsiaBillsPayment = getText(MLWalletPayBillsPage.objAirAsia);
			click(MLWalletPayBillsPage.objAirAsia,getTextVal(MLWalletPayBillsPage.objAirAsia,"Biller"));
			verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"));
			if(verifyElementPresent(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,"Biller Name"))){
				String sBillerInBillsPayInformation = getText(MLWalletPayBillsPage.objBillerNameInBillsPayInformation);
				assertionValidation(sAirAsiaBillsPayment,sBillerInBillsPayInformation);
				logger.info("PB_TC_04, Bills Pay Information page is displayed and Biller name is matched");
				ExtentReporter.extentLoggerPass("PB_TC_04", "PB_TC_04, Bills Pay Information page is displayed and Biller name is matched");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void searchBiller_PB_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Search Biller");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		Thread.sleep(5000);
		type(MLWalletPayBillsPage.objSearchBiller,prop.getproperty("Biller_Name"),"Search Biller");
		if(verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"))){
			String sMisBillsPayBiller = getText(MLWalletPayBillsPage.objMisBillsPayBiller);
			click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
			verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"));
			if(verifyElementPresent(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,"Biller Name"))){
				String sBillerInBillsPayInformation = getText(MLWalletPayBillsPage.objBillerNameInBillsPayInformation);
				assertionValidation(sMisBillsPayBiller,sBillerInBillsPayInformation);
				logger.info("PB_TC_05, Bills Pay Information page is displayed and Biller name is MIS BILLSPAY123456");
				ExtentReporter.extentLoggerPass("PB_TC_05", "PB_TC_05, Bills Pay Information page is displayed and Biller name is MIS BILLSPAY123456");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void billingInformationInput_PB_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Biller Information Input");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"),prop.getproperty("Middle_Name"),prop.getproperty("Last_Name"),"10");
		if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails,getTextVal(MLWalletPayBillsPage.objConfirmDetails,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objBillerName,getTextVal(MLWalletPayBillsPage.objBillerName,"Biller Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAccountName,getTextVal(MLWalletPayBillsPage.objAccountName,"Account holder Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAccountNumber,getTextVal(MLWalletPayBillsPage.objAccountNumber,"Account Number"));
			verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod,getTextVal(MLWalletPayBillsPage.objPaymentMethod,"Payment Method"));
			logger.info("PB_TC_06, Confirm Details page displayed with transaction details is validated");
			ExtentReporter.extentLoggerPass("PB_TC_06", "PB_TC_06, Confirm Details page displayed with transaction details is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsWithValidInputs_PB_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills With Valid Inputs");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"))) {
			Swipe("UP",1);
			click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		}
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletPayBillsPage.objSuccessPillPaymentMsg,getTextVal(MLWalletPayBillsPage.objSuccessPillPaymentMsg,"Message"))) {
			verifyElementPresent(MLWalletPayBillsPage.objAmountPaid, getTextVal(MLWalletPayBillsPage.objAmountPaid, "Amount"));
			verifyElementPresent(MLWalletPayBillsPage.objPaidDate, getTextVal(MLWalletPayBillsPage.objPaidDate, "Date"));
			verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails, getTextVal(MLWalletPayBillsPage.objTransactionDetails, "Header"));
			verifyElementPresent(MLWalletPayBillsPage.objTransactionNumber, getTextVal(MLWalletPayBillsPage.objTransactionNumber, "Transaction Number"));
			String sTransactionNumber = getText(MLWalletPayBillsPage.objTransactionNumber);
			verifyElementPresent(MLWalletPayBillsPage.objBillerName, getTextVal(MLWalletPayBillsPage.objBillerName, "Biller Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAccountName, getTextVal(MLWalletPayBillsPage.objAccountName, "Account Name"));
			verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod, getTextVal(MLWalletPayBillsPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletPayBillsPage.objAmountToPay, getTextVal(MLWalletPayBillsPage.objAmountToPay, "Amount"));
			verifyElementPresent(MLWalletPayBillsPage.objServiceFee, getTextVal(MLWalletPayBillsPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletPayBillsPage.objTotalAmount, getTextVal(MLWalletPayBillsPage.objTotalAmount, "Total Amount"));
			verifyElementPresentAndClick(MLWalletPayBillsPage.objBackToHomeBtn, getTextVal(MLWalletPayBillsPage.objBackToHomeBtn, "Button"));
			waitTime(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			click(MLWalletHomePage.objPayBills, getTextVal(MLWalletHomePage.objPayBills, "Text"));
			if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
				String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
				System.out.println(sReferenceNumberInCashOut);
				assertionValidation(sReferenceNumberInCashOut, sTransactionNumber);
				logger.info("PB_TC_07, Bills Payment Successful and validated with Recent Transaction");
				ExtentReporter.extentLoggerPass("PB_TC_07", "PB_TC_07, Bills Payment Successful and validated with Recent Transaction");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void payBillsInRecentTransactions_PB_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills In Recent Transactions");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		click(MLWalletHomePage.objPayBills, getTextVal(MLWalletHomePage.objPayBills, "Text"));
		if(verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails,getTextVal(MLWalletPayBillsPage.objTransactionDetails,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objReferenceNumber,getTextVal(MLWalletPayBillsPage.objReferenceNumber,"Reference Number"));
			verifyElementPresent(MLWalletPayBillsPage.objReceiverName,getTextVal(MLWalletPayBillsPage.objReceiverName,"Receiver Name"));
			verifyElementPresent(MLWalletPayBillsPage.objBillerName, getTextVal(MLWalletPayBillsPage.objBillerName, "Biller Name"));
			verifyElementPresent(MLWalletPayBillsPage.objReceiverMobNo, getTextVal(MLWalletPayBillsPage.objReceiverMobNo, "Receiver Mobile Number"));
			verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod, getTextVal(MLWalletPayBillsPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletPayBillsPage.objAmountToSend, getTextVal(MLWalletPayBillsPage.objAmountToSend, "Amount"));
			verifyElementPresent(MLWalletPayBillsPage.objServiceFee, getTextVal(MLWalletPayBillsPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletPayBillsPage.objTotalAmount, getTextVal(MLWalletPayBillsPage.objTotalAmount, "Total Amount"));
			logger.info("PB_TC_08, Pay Bills In Recent Transactions Validated");
			ExtentReporter.extentLoggerPass("PB_TC_08", "PB_TC_08, Pay Bills In Recent Transactions Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsInsufficientBalance_PB_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Insufficient Balance");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "39000");
		if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"))) {
			Swipe("UP",1);
			click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
			explicitWaitVisible(SendTransferPage.objInsufficientAmountMsg,5);
			if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg, getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
				String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
				String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
				assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
				logger.info("PB_TC_09, Insufficient Balance - Error Message is validated");
				ExtentReporter.extentLoggerPass("PB_TC_09", "PB_TC_09, Insufficient Balance - Error Message is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void billingInformationInvalidInput_PB_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Biller Information Invalid Input");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		searchBiller();
		click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objAccountNumberRequiredMsg,getTextVal(MLWalletPayBillsPage.objAccountNumberRequiredMsg,"Error Message"))){
			String sAccountNumberRequiredErrorMsg = getText(MLWalletPayBillsPage.objAccountNumberRequiredMsg);
			String sExceptedAccountNumberRequiredErrorMsg = "Account Number is required";
			assertionValidation(sAccountNumberRequiredErrorMsg,sExceptedAccountNumberRequiredErrorMsg);
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objFirstNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objFirstNameRequiredMsg,"Error Message"))){
			String sFirstNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objFirstNameRequiredMsg);
			String sExceptedFirstNameRequiredErrorMsg = "First name is required";
			assertionValidation(sFirstNameRequiredErrorMsg,sExceptedFirstNameRequiredErrorMsg);
		}

		if(verifyElementPresent(MLWalletPayBillsPage.objLastNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objLastNameRequiredMsg,"Error Message"))){
			String sLastNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objLastNameRequiredMsg);
			String sExceptedLastNameRequiredErrorMsg = "Last name is required";
			assertionValidation(sLastNameRequiredErrorMsg,sExceptedLastNameRequiredErrorMsg);
		}

		billerDetails(prop.getproperty("Invalid_First_Name"),prop.getproperty("Invalid_Middle_Name"),prop.getproperty("Invalid_Last_Name"),"0.99");
		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidFirstNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidFirstNameMsg,"Error Message"))){
			String sInvalidFirstNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidFirstNameMsg);
			String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
			assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidSecondNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidSecondNameMsg,"Error Message"))){
			String sInvalidSecondNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidSecondNameMsg);
			String sExceptedSecondNameErrorMsg = "Middle name must only contain letters and spaces";
			assertionValidation(sInvalidSecondNameErrorMsg,sExceptedSecondNameErrorMsg);
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidLastName,getTextVal(MLWalletPayBillsPage.objInvalidLastName,"Error Message"))){
			String sInvalidThirdNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidLastName);
			String sExceptedThirdNameErrorMsg = "Last name must only contain letters and spaces";
			assertionValidation(sInvalidThirdNameErrorMsg,sExceptedThirdNameErrorMsg);
		}
		billerDetails(prop.getproperty("First_Name"),prop.getproperty("Middle_Name"),prop.getproperty("Last_Name"),"0.99");
		Swipe("UP",1);

		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidAmount,getTextVal(MLWalletPayBillsPage.objInvalidAmount,"Error Message"))){
			String sInvalidAmountErrorMsg = getText(MLWalletPayBillsPage.objInvalidAmount);
			String sExceptedAmountErrorMsg = "The amount should not be less than 1";
			assertionValidation(sInvalidAmountErrorMsg,sExceptedAmountErrorMsg);
		}

		logger.info("PB_TC_10, Invalid biller Information Error messages validated");
		ExtentReporter.extentLoggerPass("PB_TC_10", "PB_TC_10, Invalid biller Information Error messages validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void addBillerToPayBills_PB_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Add Biller To Pay Bills");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		addBiller();
		click(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
		type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Last_Name"), "Search Biller Text Field");
		if (verifyElementPresent(MLWalletPayBillsPage.objSelectBillerInnSavedBiller(prop.getproperty("Last_Name")), getTextVal(MLWalletPayBillsPage.objSelectBillerInnSavedBiller(prop.getproperty("Last_Name")), "Recipient"))) {
			logger.info("PB_TC_12, The Added Biller is displayed in Saved Biller Page");
			ExtentReporter.extentLoggerPass("PB_TC_12", "PB_TC_12, The Added Biller is displayed in Saved Biller Page");
			System.out.println("-----------------------------------------------------------");
		}
	}
//
//	public void addBillerInvalidInputs_PB_TC_13() throws Exception {
//		ExtentReporter.HeaderChildNode("Add Biller Invalid Inputs");
//		mlWalletLogin(prop.getproperty("Branch_Verified"));
//		verifyElementPresent(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
//		click(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
//		click(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"));
//		explicitWaitVisible(MLWalletPayBillsPage.objAddBiller,5);
//		click(MLWalletPayBillsPage.objAddBiller, getTextVal(MLWalletPayBillsPage.objAddBiller, "Button"));
//		addSelectedBiller();
//
//		type(MLWalletPayBillsPage.objAddAccountNumber,"ABC","Account Number Input Field");
//		click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//		if(verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,getTextVal(MLWalletPayBillsPage.objAddAccountNumber,"Error Message"))){
//			String sAccountNumberRequiredErrorMsg = getText(MLWalletPayBillsPage.objAccountNumberRequiredMsg);
//			String sExceptedAccountNumberRequiredErrorMsg = "Account Number is required";
//			assertionValidation(sAccountNumberRequiredErrorMsg,sExceptedAccountNumberRequiredErrorMsg);
//		}
//
//		click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//		type(MLWalletPayBillsPage.objAddAccountNumber,prop.getproperty("AccountNumber"),"Account Number Input Field");
//		if(verifyElementPresent(MLWalletPayBillsPage.objFirstNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objFirstNameRequiredMsg,"Error Message"))){
//			String sFirstNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objFirstNameRequiredMsg);
//			String sExceptedFirstNameRequiredErrorMsg = "First name is required";
//			assertionValidation(sFirstNameRequiredErrorMsg,sExceptedFirstNameRequiredErrorMsg);
//		}
//
//		click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//		type(MLWalletPayBillsPage.objAddFirstName,prop.getproperty("Invalid_First_Name"),"First Name Input Field");
//		if(verifyElementPresent(MLWalletPayBillsPage.objLastNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objLastNameRequiredMsg,"Error Message"))){
//			String sLastNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objLastNameRequiredMsg);
//			String sExceptedLastNameRequiredErrorMsg = "Last name is required";
//			assertionValidation(sLastNameRequiredErrorMsg,sExceptedLastNameRequiredErrorMsg);
//		}
//
//		click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//		type(MLWalletPayBillsPage.objAddLastName,prop.getproperty("Invalid_Last_Name"),"Last Name Input Field");
////		billerDetails(prop.getproperty("Invalid_First_Name"),prop.getproperty("Invalid_Middle_Name"),prop.getproperty("Invalid_Last_Name"),"0.99");
//
//
//		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidFirstNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidFirstNameMsg,"Error Message"))){
//			String sInvalidFirstNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidFirstNameMsg);
//			String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
//			assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
//		}
//		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidSecondNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidSecondNameMsg,"Error Message"))){
//			String sInvalidSecondNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidSecondNameMsg);
//			String sExceptedSecondNameErrorMsg = "Middle name must only contain letters and spaces";
//			assertionValidation(sInvalidSecondNameErrorMsg,sExceptedSecondNameErrorMsg);
//		}
//		if(verifyElementPresent(MLWalletPayBillsPage.objInvalidLastName,getTextVal(MLWalletPayBillsPage.objInvalidLastName,"Error Message"))){
//			String sInvalidThirdNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidLastName);
//			String sExceptedThirdNameErrorMsg = "Last name must only contain letters and spaces";
//			assertionValidation(sInvalidThirdNameErrorMsg,sExceptedThirdNameErrorMsg);
//		}

	public void editAddedBillerToPayBills_PB_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Edit Added Biller to Pay bIlls");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		selectAddedBiler();
		click(MLWalletPayBillsPage.objEditBtn,getTextVal(MLWalletPayBillsPage.objEditBtn,"Button"));

		clearField(MLWalletPayBillsPage.objEditRecipientLastName,"Last Name Input Field");
		type(MLWalletPayBillsPage.objEditRecipientLastName, prop.getproperty("Edited_Last_name"), "Last Name Text Field");
		click(MLWalletPayBillsPage.ObjSaveBtn, getTextVal(MLWalletPayBillsPage.ObjSaveBtn, "Button"));
		click(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
		type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Edited_Last_name"), "Search Recipient Text Field");
		if (verifyElementPresent(MLWalletPayBillsPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")), getTextVal(SendTransferPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")), "Recipient"))) {
			logger.info("PB_TC_14, Successfully edited the Added Biller");
			ExtentReporter.extentLoggerPass("PB_TC_14", "PB_TC_14, Successfully edited the Added Biller");
			System.out.println("-----------------------------------------------------------");
		}

	}

	public void deleteAddedBillerPayBills_PB_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Delete Added Biller Pay Bills");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		selectAddedBiler();
		click(MLWalletPayBillsPage.objRemoveBtn,getTextVal(MLWalletPayBillsPage.objRemoveBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objConfirmPopup,getTextVal(MLWalletPayBillsPage.objConfirmPopup,"Pop up"))){
			verifyElementPresentAndClick(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
		}
		if(verifyElementPresent(MLWalletPayBillsPage.objSavedBillers,getTextVal(MLWalletPayBillsPage.objSavedBillers,"Page"))){
			type(MLWalletPayBillsPage.objSearchBillerInSavedBillers,prop.getproperty("Edited_Last_name"),"Search saved biller input field");
			if (verifyElementNotPresent(MLWalletPayBillsPage.objSelectLastName(prop.getproperty("Edited_Last_name"), prop.getproperty("First_Name")),"Saved Recipient",5)){
				logger.info("PB_TC_15, Successfully deleted the Added Biller");
				ExtentReporter.extentLoggerPass("PB_TC_15", "PB_TC_15, Successfully deleted the Added Biller");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

//================================ Phase 2=============================================================//

	public void payBillsUIValidation_PB_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		if(verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Header"));
			verifyElementPresent(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
			verifyElementPresent(MLWalletPayBillsPage.objBillers,getTextVal(MLWalletPayBillsPage.objBillers,"Header"));
			verifyElementPresent(MLWalletPayBillsPage.objCategories,getTextVal(MLWalletPayBillsPage.objCategories,"Button"));
			verifyElementPresent(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
			verifyElementPresent(MLWalletPayBillsPage.objSearchBiller,"Search Biller Input Field");
			logger.info("PB_TC_16, Pay Bills UI Page Validated");
			ExtentReporter.extentLoggerPass("PB_TC_16", "PB_TC_16, Pay Bills UI Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsAddBillerPageUIValidation_PB_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("PayBills Add Biller Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		verifyElementPresentAndClick(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
		verifyElementPresentAndClick(MLWalletPayBillsPage.objAddBiller,getTextVal(MLWalletPayBillsPage.objAddBiller,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objAddBillers,getTextVal(MLWalletPayBillsPage.objAddBillers,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objBillerInformation,getTextVal(MLWalletPayBillsPage.objBillerInformation,"Header"));
			verifyElementPresent(MLWalletPayBillsPage.objBiller,getTextVal(MLWalletPayBillsPage.objBiller,"Edit Field"));
			verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,"Account Number Input Field");
			verifyElementPresent(MLWalletPayBillsPage.objAddFirstName,"Account Holder First Name Input Field");
			verifyElementPresent(MLWalletPayBillsPage.objAddMiddleName,"Account Holder Middle Name Input Field");
			verifyElementPresent(MLWalletPayBillsPage.objAddLastName,"Account Holder Last Name Input Field");
			verifyElementPresent(MLWalletPayBillsPage.objAddNickName,"Nick Name Input Field");
			verifyElementPresent(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
			logger.info("PB_TC_18, PayBills Add Biller Page UI Validated");
			ExtentReporter.extentLoggerPass("PB_TC_18", "PB_TC_16, PayBills Add Biller Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void paybillsRecentTransaction_PB_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Recent Transaction validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails,getTextVal(MLWalletPayBillsPage.objTransactionDetails,"Page"));
		Swipe("UP",1);
		verifyElementPresentAndClick(MLWalletPayBillsPage.objNewTransactionBtn,getTextVal(MLWalletPayBillsPage.objNewTransactionBtn,"Button"));
		verifyElementPresentAndClick(MLWalletPayBillsPage.objRecentTransactionOne,"Recent Transaction");
		if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
			logger.info("PB_TC_19, Pay Bills Recent Transaction Validated");
			ExtentReporter.extentLoggerPass("PB_TC_19", "PB_TC_19, Pay Bills Recent Transaction validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsSavedBilerUIValidation_PB_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Saved Biler UI Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		selectAddedBiler();
		if(verifyElementPresent(MLWalletPayBillsPage.objAccountInfo,getTextVal(MLWalletPayBillsPage.objAccountInfo,"Page"))){
			verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,getTextVal(MLWalletPayBillsPage.objAddAccountNumber,"Account Number"));
			verifyElementPresent(MLWalletPayBillsPage.objAddFirstName,getTextVal(MLWalletPayBillsPage.objAddFirstName,"First Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAddMiddleName,getTextVal(MLWalletPayBillsPage.objAddMiddleName,"Middle Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAddLastName,getTextVal(MLWalletPayBillsPage.objAddLastName,"Last Name"));
			verifyElementPresent(MLWalletPayBillsPage.objAddNickName,getTextVal(MLWalletPayBillsPage.objAddNickName,"Nick Name"));
			verifyElementPresent(MLWalletPayBillsPage.objEditBtn,getTextVal(MLWalletPayBillsPage.objEditBtn,"Button"));
			verifyElementPresent(MLWalletPayBillsPage.objRemoveBtn,getTextVal(MLWalletPayBillsPage.objRemoveBtn,"Button"));
			verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Button"));
			logger.info("PB_TC_20, Pay Bills Saved Biler UI Validated");
			ExtentReporter.extentLoggerPass("PB_TC_20", "PB_TC_20, Pay Bills Saved Biler UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsMaxBillsPaymentPerTransactionBuyTierUser_PB_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "20000");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage,"Error Message"))){
			String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage);
			String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P10,000.00. Please try again.";
			assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
            verifyElementPresent(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
			logger.info("PB_TC_22, Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account Validated");
			ExtentReporter.extentLoggerPass("PB_TC_22", "PB_TC_22, Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsMaxBillsPaymentPerTransactionSemiVerifiedTier_PB_TC_25() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage,"Error Message"))){
			String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage);
			String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
			verifyElementPresent(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
			logger.info("PB_TC_25, Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account Validated");
			ExtentReporter.extentLoggerPass("PB_TC_25", "PB_TC_25, Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsMaxBillsPaymentPerTransactionBranchVerifiedTier_PB_TC_28() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage,"Error Message"))){
			String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage);
			String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P25,000.00. Please try again.";
			assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
			verifyElementPresent(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
			logger.info("PB_TC_26, Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account Validated");
			ExtentReporter.extentLoggerPass("PB_TC_26", "PB_TC_26, Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsMaxBillsPaymentPerTransactionFullyVerifiedTier_PB_TC_31() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage,"Error Message"))){
			String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage);
			String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
			verifyElementPresent(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
			logger.info("PB_TC_31, Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account Validated");
			ExtentReporter.extentLoggerPass("PB_TC_31", "PB_TC_31, Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void payBillsRecentTransaction_PB_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("PayBills Recent Transactions");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		payBillsNavigation();
		waitTime(5000);
		verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Header"));
		horizontalSwipeByPercentages(0.8,0.2,0.25);
		horizontalSwipeByPercentages(0.8,0.2,0.25);
		logger.info("PB_TC_21, PayBills Recent Transactions Swiped until Last Transaction");
		ExtentReporter.extentLoggerPass("PB_TC_21", "PB_TC_21, PayBills Recent Transactions Swiped until Last Transaction");
		System.out.println("-----------------------------------------------------------");
	}


	public void payBillsLocationPopupValidation_PB_TC_34() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Location popup Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("PB_TC_34, Pay Bills Location popup Validated");
			ExtentReporter.extentLoggerPass("PB_TC_34", "PB_TC_34, Pay Bills Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsLocationDenyFunctionality_PB_TC_35() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Location Deny Functionality Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsLocationPermissionDenyCloseBtnFunctionality_PB_TC_36() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Location Permission Deny Close Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
				logger.info("PB_TC_36, Pay Bills Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("PB_TC_36", "PB_TC_36, Pay Bills Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void payBillsLocationPermissionDenyOpenSettingsBtnFunctionality_PB_TC_37() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Location Permission Deny open Settings Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
				logger.info("PB_TC_37, Pay Bills Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("PB_TC_37", "PB_TC_37, Pay Bills Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void payBillsLocationPopUpAllowFunctionality_PB_TC_38() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Location popup Allow Button Functionality Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("PB_TC_38, Pay Bills Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("PB_TC_38", "PB_TC_38, Pay Bills Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void payBillsInternetInterruptionWhileEnteringOTP_PB_TC_39() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Internet Interruption While Entering OTP Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		enableLocation_PopUp();
		waitTime(15000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("PB_TC_39, Pay Bills Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("PB_TC_39", "PB_TC_39, Pay Bills Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void payBillsTransactionValidationAfterMinimizingApp_PB_TC_42() throws Exception {
		ExtentReporter.HeaderChildNode("Pay Bills Transaction Validation After Minimizing App");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		payBillsNavigation();
		searchBiller();
		billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
		verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
		Swipe("UP",1);
		click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if(verifyElementPresent(MLWalletPayBillsPage.objSuccessPillPaymentMsg,getTextVal(MLWalletPayBillsPage.objSuccessPillPaymentMsg,"Message"))) {
			logger.info("PB_TC_40, Pay Bills Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("PB_TC_40", "PB_TC_40, Pay Bills Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}






//============================ Settings Module ============================================================//
//================================Generalized methods=======================================================//


	public void navigateToProfile() throws Exception {
		click(MLWalletSettingsPage.objProfileIcon1, "Profile Icon");
		waitTime(5000);
		if (verifyElementPresent(MLWalletSettingsPage.objAccountDetails, "Account Details Page")) {
			logger.info("Navigated to settings");
		}
	}

	public static void handleMpin(String mPin, String validationText) throws Exception {
		for (int i = 0; i < mPin.length(); i++) {
			char ch = mPin.charAt(i);
			String ch1 = String.valueOf(ch);
			click(MLWalletSettingsPage.objEnterMpinVal(ch1),
					getTextVal(MLWalletSettingsPage.objEnterMpinVal(ch1), "MPIN"));
		}
		logger.info(validationText + " MPIN " + mPin + " Successfully");
		ExtentReporter.extentLogger("Enter MPIN", "" + validationText + " MPIN " + mPin + " Successfully");
	}
//===========================================================================================================//
	public void settingsAccountDetailsValidation_SS_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Account Details validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		click(MLWalletSettingsPage.objAccountDetails, "Account Details");
		verifyElementPresent(MLWalletSettingsPage.objAccountDetails, getTextVal(MLWalletSettingsPage.objAccountDetails,"Name"));
		verifyElementPresent(MLWalletSettingsPage.objNameOfAccountHolder,getTextVal(MLWalletSettingsPage.objNameOfAccountHolder, "Name"));
		verifyElementPresent(MLWalletSettingsPage.objMailAddressOfAccountHolder,getTextVal(MLWalletSettingsPage.objMailAddressOfAccountHolder, "Mail Address"));
		verifyElementPresent(MLWalletSettingsPage.objMobileNoOfAccountHolder,getTextVal(MLWalletSettingsPage.objMobileNoOfAccountHolder, "Mobile Number"));
		logger.info("SS_TC_01, Account Details validated");
		ExtentReporter.extentLoggerPass("SS_TC_01", "SS_TC_01, Account Details validated");
		System.out.println("-----------------------------------------------------------");
	}
	public void settingsInvalidMLPinValidation_SS_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Invalid ML Pin Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		click(MLWalletSettingsPage.objChangeMLPin, "Change ML Pin");
		verifyElementPresent(MLWalletSettingsPage.objChangeMLPin, "Change ML Pin");
		handleMpin(prop.getproperty("wrongMpin"), "Entered Invalid ML PIN");
		if(verifyElementPresent(MLWalletSettingsPage.objInvalaidMpinPopUp,getTextVal(MLWalletSettingsPage.objInvalaidMpinPopUp,"Error Popup Message"))){
			String sInvalidMPinPopUp = getText(MLWalletSettingsPage.objInvalaidMpinPopUp);
			String sExceptedErrorPOpUp = "You have entered an invalid PIN. Please try again.";
			assertionValidation(sInvalidMPinPopUp,sExceptedErrorPOpUp);
			logger.info("SS_TC_03, Invalid ML PIN validated");
			ExtentReporter.extentLoggerPass("SS_TC_03", "SS_TC_03, Invalid ML PIN validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void settingsValidMLPinValidation_SS_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Valid ML Pin Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		click(MLWalletSettingsPage.objChangeMLPin, "Change ML Pin");
		verifyElementPresent(MLWalletSettingsPage.objChangeMLPin, "Change ML Pin");
		waitTime(2000);
		handleMpin(prop.getproperty("mPin"), "Entered");
		waitTime(2000);
		if (verifyElementPresent(MLWalletSettingsPage.objEnterNewMMLpinText,
				getTextVal(MLWalletSettingsPage.objEnterNewMMLpinText, "Page"))) {
			waitTime(2000);
			handleMpin(prop.getproperty("newMpin"), "New ML PIN");
			waitTime(2000);
			handleMpin(prop.getproperty("newMpin"), "Confirmed New ML ");
			if(verifyElementPresent(MLWalletSettingsPage.objMLPinChangedPopup,
					getTextVal(MLWalletSettingsPage.objMLPinChangedPopup,"Success Popup"))){
				String sActualSuccessPopUp = getText(MLWalletSettingsPage.objMLPinChangedPopup);
				String sExpectedSuccessPopUp = "ML Pin Successfully Changed";
				assertionValidation(sActualSuccessPopUp,sExpectedSuccessPopUp);
				logger.info("SS_TC_02 To validate ML PIN validated");
				ExtentReporter.extentLoggerPass("SS_TC_02", "'SS_TC_02' To validate ML PIN validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void settingsBiometricsLogin_SS_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Biometrics Login");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
		verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsSwitch,"Biometrics Switch");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletSettingsPage.objActivateBiometricsLogin,getTextVal(MLWalletSettingsPage.objActivateBiometricsLogin,"Header"))){
			String sActualActiveMsg = getText(MLWalletSettingsPage.objActivateBiometricsLogin);
			String sExceptedActiveMsg = "Activate Biometrics Login";
			assertionValidation(sActualActiveMsg,sExceptedActiveMsg);
			logger.info("SS_TC_04, Settings Biometrics Login Validated");
			ExtentReporter.extentLoggerPass("SS_TC_04", "'SS_TC_04' Settings Biometrics Login Validated");
			System.out.println("-----------------------------------------------------------");
		}


	}


	public void settingAccRecovery_SS_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Invalid ML Pin Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		click(MLWalletSettingsPage.objAccountRecovery, "Account Recovery");
		verifyElementPresent(MLWalletSettingsPage.objMlWalletSupportPage,
				getTextVal(MLWalletSettingsPage.objMlWalletSupportPage, "Page"));
		click(MLWalletSettingsPage.objFullNameField, "First Name Field");
		type(MLWalletSettingsPage.objFullNameField, prop.getproperty("firstName"), "First Name Field");
		hideKeyboard();
		Swipe("UP",1);
		click(MLWalletSettingsPage.objRegisteredEmail, "Registered Email Field");
		type(MLWalletSettingsPage.objRegisteredEmail, prop.getproperty("eMailAddress"),
				"Registered Email Field");
		hideKeyboard();
		Swipe("UP",1);
		click(MLWalletSettingsPage.objMobileNumber, "Mobile Number Field");
		type(MLWalletSettingsPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Field");
		hideKeyboard();
		click(MLWalletSettingsPage.objNatureOfReqRadioBtn,
				getTextVal(MLWalletSettingsPage.objNatureOfReqRadioBtn, "Text"));
		scrollToVertical("Next");
		click(MLWalletSettingsPage.objNextBtn, "Next Button");
		verifyElementPresent(MLWalletSettingsPage.objStolenPage,
				getTextVal(MLWalletSettingsPage.objStolenPage, "Page"));
		click(MLWalletSettingsPage.objYourAnswer, "Lost/Stolen Mobile Number Field");
		type(MLWalletSettingsPage.objYourAnswer, prop.getproperty("Branch_Verified"),
				"Lost/Stolen Mobile Number Field");
		hideKeyboard();
		click(MLWalletSettingsPage.objNewMobNo, "New Mobile Number Field");
		type(MLWalletSettingsPage.objNewMobNo, prop.getproperty("newMobileNumber"), "New Mobile Number Field");
		hideKeyboard();
		click(MLWalletSettingsPage.objFacebookMessangerName, "Facebook Messenger Name Field");
		type(MLWalletSettingsPage.objFacebookMessangerName, prop.getproperty("messangerName"),
				"Facebook Messenger Name Field");
		hideKeyboard();
		scrollToVertical("Submit");
		click(MLWalletSettingsPage.objSumbitBtn, "Submit Button");
		verifyElementPresent(MLWalletSettingsPage.objReviewPage, "Review Page");
		String actualText = "Please allow us some time to review the details of your request. A customer service representative will contact you for updates and/or to get additional information.";
		String reviewExpectedText = getText(MLWalletSettingsPage.objReviewPage);
		assertionValidation(actualText, reviewExpectedText);
		logger.info("'SS_TC_05', To verify account recovery validated");
		ExtentReporter.extentLoggerPass("Account Recovery", "'SS_TC_05', To verify account recovery validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void settingsBackBtnValidation_SS_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Back Button Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objSettingsBackBtn,"Settings Back Button");
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("SS_TC_07, Settings Back Button Button validated");
			ExtentReporter.extentLoggerPass("SS_TC_07", "SS_TC_07, Settings Back Button Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsDeleteAccountPopUpUIValidation_SS_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Delete Account PopUp UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
		if(verifyElementPresent(MLWalletSettingsPage.objDeleteConfirmationPopUp,getTextVal(MLWalletSettingsPage.objDeleteConfirmationPopUp,"Confirmation Popup"))){
			String sActualConfirmationPopupMsg = getText(MLWalletSettingsPage.objDeleteConfirmationPopUp);
			String sExpectedConfirmationPopupMsg = "Are you sure you would like to delete your account?";
			assertionValidation(sActualConfirmationPopupMsg,sExpectedConfirmationPopupMsg);
			verifyElementPresent(MLWalletSettingsPage.objProceedBtn,getTextVal(MLWalletSettingsPage.objProceedBtn,"Button"));
			verifyElementPresent(MLWalletSettingsPage.objCancelBtn,getTextVal(MLWalletSettingsPage.objCancelBtn,"Button"));
			logger.info("SS_TC_10, Settings, Settings Delete Account PopUp UI validated");
			ExtentReporter.extentLoggerPass("SS_TC_10", "SS_TC_10, Settings Delete Account PopUp UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}
	public void settingsChangeMLPinNavigation_SS_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Change ML Pin Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"option"));
		Thread.sleep(5000);
		if(verifyElementPresent(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objEnterCurrentMLPin,getTextVal(MLWalletSettingsPage.objEnterCurrentMLPin,"Header"));
			verifyElementPresent(MLWalletSettingsPage.objKeyword,"Keyword to Enter MLPin");
			verifyElementPresent(MLWalletSettingsPage.objMLPinEditField,"ML Pin Edit Field");
			logger.info("SS_TC_12, Settings Change ML Pin Navigation validated");
			ExtentReporter.extentLoggerPass("SS_TC_12", "SS_TC_12, Settings Change ML Pin Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsChangeMlPinBackBtnValidation_SS_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Change ML Pin Back Button Validation");
		settingsChangeMLPinNavigation_SS_TC_12();
		verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPinBackBtn,"Back Button");
		if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			logger.info("SS_TC_13, Settings, After clicking on  Change ML Pin Back Button App Navigated to Settings Page validated");
			ExtentReporter.extentLoggerPass("SS_TC_13", "SS_TC_13, Settings, After clicking on  Change ML Pin Back Button App Navigated to Settings Page validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsPageUIValidation_SS_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Page UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objAccountDetails,getTextVal(MLWalletSettingsPage.objAccountDetails,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objNotification,getTextVal(MLWalletSettingsPage.objNotification,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
			logger.info("SS_TC_06, Settings Page UI validated");
			ExtentReporter.extentLoggerPass("SS_TC_06", "SS_TC_06, Settings Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsAccountDetailsNavigation_SS_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Account Details Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objAccountDetails,getTextVal(MLWalletSettingsPage.objAccountDetails,"Option"));
		if(verifyElementPresent(MLWalletSettingsPage.objAccountDetails, getTextVal(MLWalletSettingsPage.objAccountDetails,"Page"))){
			logger.info("SS_TC_14, Settings Account Details Navigation validated");
			ExtentReporter.extentLoggerPass("SS_TC_14", "SS_TC_14, Settings Account Details Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsAccountDetailsBackBtnValidation_SS_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Account details Back Button Validation");
		settingsAccountDetailsNavigation_SS_TC_14();
		verifyElementPresentAndClick(MLWalletSettingsPage.objAccountDetailsBackBtn,"Back Button");
		if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			logger.info("SS_TC_15, Settings Account Details Back Button Navigation validated");
			ExtentReporter.extentLoggerPass("SS_TC_15", "SS_TC_15, Settings Account Details Back Button Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingChangeMLPinUIValidation_SS_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Change ML Pin UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"option"));
		Thread.sleep(5000);
		if(verifyElementPresent(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objEnterCurrentMLPin,getTextVal(MLWalletSettingsPage.objEnterCurrentMLPin,"Header"));
			verifyElementPresent(MLWalletSettingsPage.objKeyword,"Keyword to Enter MLPin");
			verifyElementPresent(MLWalletSettingsPage.objMLPinEditField,"ML Pin Edit Field");
			logger.info("SS_TC_16, Settings Change ML Pin UI validated");
			ExtentReporter.extentLoggerPass("SS_TC_16", "SS_TC_16, Settings Change ML Pin UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsAccountRecoveryUIValidation_SS_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Account Recovery UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
		if(verifyElementPresent(MLWalletSettingsPage.objTroubleSigningIn,getTextVal(MLWalletSettingsPage.objTroubleSigningIn,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objMLWalletSupport,getTextVal(MLWalletSettingsPage.objMLWalletSupport,"Header"));
			verifyElementPresent(MLWalletSettingsPage.objFullNameField, "First Name Field");
			verifyElementPresent(MLWalletSettingsPage.objRegisteredEmail, "Registered Email Field");
			Swipe("UP",1);
			verifyElementPresent(MLWalletSettingsPage.objMobileNumber, "Mobile Number Field");
			Swipe("UP",2);
			if (verifyElementDisplayed(MLWalletSettingsPage.objNatureOfRequests)) {
				List<WebElement> values = findElements(MLWalletSettingsPage.objNatureOfRequests);
				for (int i = 0; i < values.size(); i++) {
					String savedRecipientName = values.get(i).getText();
					logger.info("Nature of Your Request : " + savedRecipientName + " is displayed");
					ExtentReporter.extentLogger(" ", "Nature of Your Request : " + savedRecipientName + " is displayed");
				}
			}
			logger.info("SS_TC_17, Settings Account Recovery UI validated");
			ExtentReporter.extentLoggerPass("SS_TC_17", "SS_TC_17, Settings Account Recovery UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsDeleteAccountCancelBtnValidation_SS_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Delete Account Cancel Button Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
		verifyElementPresent(MLWalletSettingsPage.objDeleteConfirmationPopUp,getTextVal(MLWalletSettingsPage.objDeleteConfirmationPopUp,"Delete Confirmation Popup"));
		verifyElementPresentAndClick(MLWalletSettingsPage.objCancelBtn,getTextVal(MLWalletSettingsPage.objCancelBtn,"Button"));
		if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			logger.info("SS_TC_18, Settings, After clicking on Cancel button on Delete Confirmation Popup App should navigate to Settings Page is validated");
			ExtentReporter.extentLoggerPass("SS_TC_18", "SS_TC_18, Settings, After clicking on Cancel button on Delete Confirmation Popup App should navigate to Settings Page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsBiometricsLoginUIValidation_SS_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Biometrics Login UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
		waitTime(3000);
		if(verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objBiometrics,getTextVal(MLWalletSettingsPage.objBiometrics,"Header"));
			verifyElementPresent(MLWalletSettingsPage.objBiometricsSwitch,"Biometrics Switch");
			verifyElementPresent(MLWalletSettingsPage.objActivateBiometricsLogin,getTextVal(MLWalletSettingsPage.objActivateBiometricsLogin,"Header"));
			logger.info("SS_TC_20, Settings, Settings Biometrics Login UI validated");
			ExtentReporter.extentLoggerPass("SS_TC_20", "SS_TC_20, Settings Biometrics Login UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsAccountRecoveryNavigation_SS_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Account Recovery Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
		if(verifyElementPresent(MLWalletSettingsPage.objTroubleSigningIn,getTextVal(MLWalletSettingsPage.objTroubleSigningIn,"Page"))){
			logger.info("SS_TC_21, Settings, After clicking on Account recovery, application navigated to Trouble Signing in page is validated");
			ExtentReporter.extentLoggerPass("SS_TC_21", "SS_TC_21, After clicking on Account recovery, application navigated to Trouble Signing in page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsTroubleSigningInBackBtnValidation_SS_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Trouble Signing In Bak Btn Validation");
		settingsAccountRecoveryNavigation_SS_TC_21();
		verifyElementPresentAndClick(MLWalletSettingsPage.objTroubleSigningInBackBtn,"Trouble Signing In Page Back Button");
		if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			logger.info("SS_TC_22, Settings Account Details Back Button Navigation validated");
			ExtentReporter.extentLoggerPass("SS_TC_22", "SS_TC_22, Settings Account Details Back Button Navigation validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsBiometricsLoginNavigation_SS_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Biometrics Login Navigation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		navigateToProfile();
		verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
		waitTime(3000);
		if(verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Page"))){
			logger.info("SS_TC_23, Settings, After clicking on Account recovery, application navigated to Biometrics Login page is validated");
			ExtentReporter.extentLoggerPass("SS_TC_23", "SS_TC_23, After clicking on Account recovery, application navigated to Biometrics Login page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void settingsBiometricsLoginBackBtnValidation_SS_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Settings Biometrics Login Back Button validation");
		settingsBiometricsLoginNavigation_SS_TC_23();
		verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLoginBackBtn,"Biometrics Login Page Back Button");
		if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			logger.info("SS_TC_24, Settings Biometrics Login Back Button validated");
			ExtentReporter.extentLoggerPass("SS_TC_24", "SS_TC_24, Settings Biometrics Login Back Button validated validated");
			System.out.println("-----------------------------------------------------------");
		}
	}














//=================================== Buy e - load ======================================================//
//==================================== Generalized methods ============================================//


	public void eLoad_generic(String sTier,String mobileNo, String status, int telcoOption) throws Exception
	{
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		waitTime(5000);
		if(status.equals("true")) {
			verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage, "eLoad Transaction Page");
			click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
		}
		click(MLWalletEloadPage.objMobileNoField, "Mobile Number Field");
		type(MLWalletEloadPage.objMobileNoField, mobileNo, "Mobile Number Field");
		hideKeyboard();
		explicitWaitVisible(MLWalletEloadPage.objNextBtn,5);
		click(MLWalletEloadPage.objNextBtn, "Next Button");
		click(MLWalletEloadPage.objNextBtn, "Next Button");
//		enableLocation_PopUp();
	}
//===================================================================================================//



	public void buyELoadTransactionDetails_BE_TC_01(String sTier,int promotab) throws Exception
	{
		ExtentReporter.HeaderChildNode("Transaction Details Validation after Buying eLoad");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))){
			verifyElementPresent(MLWalletEloadPage.objMobileNumberInTransactionDetails,getTextVal(MLWalletEloadPage.objMobileNumberInTransactionDetails,"Mobile Number"));
			verifyElementPresent(MLWalletEloadPage.objBuyELoadTime,getTextVal(MLWalletEloadPage.objBuyELoadTime,"Date and Time"));
			verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
			verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
			verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
			logger.info("BE_TC_01, Transaction Details Validated after Buying eLoad");
			ExtentReporter.extentLoggerPass("BE_TC_01", "BE_TC_01, Transaction Details Validated after Buying eLoad");
			System.out.println("-----------------------------------------------------------");
		}

	}


	public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
	{
		ExtentReporter.HeaderChildNode("Buying eLoad using invalid mobile number");
		eLoad_generic(prop.getproperty("Fully_Verified"),prop.getproperty("inValidMobNumber"),"true", 3);
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletEloadPage.objErrorMsg, getTextVal(MLWalletEloadPage.objErrorMsg, "Pop Up Message"))){
			String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsg);
			String sExceptedErrorMsg = "Network and Mobile number does not match";
			assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
			logger.info("BE_TC_02, Buy ELoad Using Invalid Mobile Number, Network and Mobile number does not match Error Message Validated");
			ExtentReporter.extentLoggerPass("BE_TC_02", "BE_TC_02, Buy ELoad Using Invalid Mobile Number, Network and Mobile number does not match Error Message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
	{
		ExtentReporter.HeaderChildNode("Buying eLoad without mobile number input");
		eLoad_generic(prop.getproperty("Fully_Verified"),"", "true", 3);
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletEloadPage.objErrorMsg, getTextVal(MLWalletEloadPage.objErrorMsg, "Pop Up Message"))) {
			String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsg);
			String sExceptedErrorMsg = "Mobile number is required";
			assertionValidation(sActualErrorMsg, sExceptedErrorMsg);
			logger.info("BE_TC_03, Buy ELoad without mobile number input, Mobile number is required Error Message is validated");
			ExtentReporter.extentLoggerPass("BE_TC_03", "BE_TC_03, Buy ELoad without mobile number input, Mobile number is required Error Message is validated");
		}
	}

	public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
	{
		ExtentReporter.HeaderChildNode("Buying eLoad without telecommunication selected");
		eLoad_generic(prop.getproperty("Fully_Verified"),prop.getproperty("sunMobileNumber"),"false", 3);
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletEloadPage.objErrorMsg, getTextVal(MLWalletEloadPage.objErrorMsg, "Pop Up Message"))) {
			String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsg);
			String sExceptedErrorMsg = "Network and Mobile number does not match";
			assertionValidation(sActualErrorMsg, sExceptedErrorMsg);
			logger.info("BE_TC_04, Buying eLoad without selecting telecommunication, Network and Mobile number does not match Error Msg Validated");
			ExtentReporter.extentLoggerPass("BE_TC_04", "BE_TC_04, Buying eLoad without selecting telecommunication, Network and Mobile number does not match Error Msg Validated");
		}
	}

	public void buyELoadInsufficientBalance_BE_TC_05(String sTier,int promotab) throws Exception
	{
		ExtentReporter.HeaderChildNode("Buying eLoad with insufficient balance");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		swipeDownUntilElementVisible("Smart Regular Load 3000");
		verifyElementPresentAndClick(MLWalletEloadPage.obj2000RegularLoad,getTextVal(MLWalletEloadPage.obj2000RegularLoad,"Load"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		waitTime(5000);
		verifyElementPresent(MLWalletEloadPage.objInsufficientBalPopup, getTextVal(MLWalletEloadPage.objInsufficientBalPopup, "Pop up"));
		logger.info("BE_TC_05, Insufficient Balance Error Message Validated");
		ExtentReporter.extentLoggerPass("BE_TC_05", "BE_TC_05, Insufficient Balance Error Message Validated");
	}


	public void buyELoadMaxLimitPerTransaction_BE_TC_09(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Maximum Limit per Transaction");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		swipeDownUntilElementVisible("Smart Regular Load 3000");
		verifyElementPresentAndClick(MLWalletEloadPage.obj3000RegularLoad,getTextVal(MLWalletEloadPage.obj3000RegularLoad,"Load"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		waitTime(5000);
		if(verifyElementPresent(MLWalletEloadPage.objMaxLimitPopupMsg,getTextVal(MLWalletEloadPage.objMaxLimitPopupMsg,"Error Popup Msg"))){
			String sActualPopupMsg = getText(MLWalletEloadPage.objMaxLimitPopupMsg);
			String sExceptedPopupMsg = "The maximum E-load per transaction set for your verification level is P2,000.00. Please try again.";
			assertionValidation(sActualPopupMsg,sExceptedPopupMsg);
			logger.info("BE_TC_09, Buy ELoad Maximum Limit per Transaction Validated");
			ExtentReporter.extentLoggerPass("BE_TC_09", "BE_TC_09, Buy ELoad Maximum Limit per Transaction Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadTransactionPageUIValidation_BE_TC_10(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Transaction Page UI Validation");
		mlWalletLogin(sTier);
		verifyElementPresentAndClick(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
			verifyElementPresent(MLWalletEloadPage.objSelectTelco,getTextVal(MLWalletEloadPage.objSelectTelco,"Header"));
			verifyElementPresent(MLWalletEloadPage.objPhoneToLoad,getTextVal(MLWalletEloadPage.objPhoneToLoad,"Header"));
			verifyElementPresent(MLWalletEloadPage.objMobileNoField,"Mobile Number Input Field");
			verifyElementPresent(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
			verifyElementPresent(MLWalletEloadPage.objNextBtn,getTextVal(MLWalletEloadPage.objNextBtn,"Button"));
			logger.info("BE_TC_10, Buy ELoad Transaction Page UI Validated");
			ExtentReporter.extentLoggerPass("BE_TC_10", "BE_TC_10, Buy ELoad Transaction Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(String sTier,String mobileNo,int telcoOption) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Next Button Functionality On ELoad Transaction Page");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage, "eLoad Transaction Page");
		click(MLWalletEloadPage.objMobileNoField, "Mobile Number Field");
		type(MLWalletEloadPage.objMobileNoField, mobileNo, "Mobile Number Field");
		hideKeyboard();
		click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
		click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
		click(MLWalletEloadPage.objNextBtn, "Next Button");
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page")){
			logger.info("BE_TC_11, Buy ELoad Next Button Functionality On ELoad Transaction Page Validated");
			ExtentReporter.extentLoggerPass("BE_TC_11", "BE_TC_11, Buy ELoad Next Button Functionality On ELoad Transaction Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Load Selection Back Arrow Button Validation");
		buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Fully_Verified"),prop.getproperty("sunMobileNumber"),4);
		verifyElementPresentAndClick(MLWalletEloadPage.objLoadSelectionBackArrowBtn,"Load Selection Back Arrow Button");
		if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
			logger.info("BE_TC_12, After clicking on Back Arrow Btn on Load Selection Page, Application Navigates to eLoad Transaction Page is Validated");
			ExtentReporter.extentLoggerPass("BE_TC_12", "BE_TC_12,  After clicking on Back Arrow Btn on Load Selection Page, Application Navigates to eLoad Transaction Page is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLoadSelectionPageUIValidation_BE_TC_13(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Load Selection Page UI Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage,getTextVal(MLWalletEloadPage.objLoadSelectionPage,"Page"))){
			verifyElementPresent(MLWalletEloadPage.objMobileNumberInLoadSelection,getTextVal(MLWalletEloadPage.objMobileNumberInLoadSelection,"Number"));
			verifyElementPresent(MLWalletEloadPage.objChange,getTextVal(MLWalletEloadPage.objChange,"button"));
			verifyElementPresent(MLWalletEloadPage.objWalletBalanceInLoadSeletion,getTextVal(MLWalletEloadPage.objWalletBalanceInLoadSeletion,"Balance"));
			verifyElementPresent(MLWalletEloadPage.objPromoLoadTab,getTextVal(MLWalletEloadPage.objPromoLoadTab,"Tab"));
			verifyElementPresent(MLWalletEloadPage.objRegularLoadTab,getTextVal(MLWalletEloadPage.objRegularLoadTab,"Tab"));
			List<WebElement> values = findElements(MLWalletEloadPage.objPromoLoads);
			for (int i = 0; i < values.size(); i++) {
				String sPromoLoads = values.get(i).getText();
				logger.info("Promo Load : " + sPromoLoads + " is displayed");
				ExtentReporter.extentLogger(" ", "Promo Load : " + sPromoLoads + " is displayed");
			}
			logger.info("BE_TC_13, Buy ELoad Load Selection Page UI Validated");
			ExtentReporter.extentLoggerPass("BE_TC_13", "BE_TC_13,  Buy ELoad Load Selection Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Load Selection Change Button Functionality Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresentAndClick(MLWalletEloadPage.objChange,getTextVal(MLWalletEloadPage.objChange,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
			logger.info("BE_TC_14, After clicking on Change Btn in Load Selection Page, Application navigates to eLoad Transaction page is Validated");
			ExtentReporter.extentLoggerPass("BE_TC_14", "BE_TC_14, After clicking on Change Btn in Load Selection Page, Application navigates to eLoad Transaction page is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadTransactionDetailsPageUIValidation_BE_TC_15(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Transaction Details Page UI Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		if(verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"))){
			verifyElementPresent(MLWalletEloadPage.objLoadWithAmount,getTextVal(MLWalletEloadPage.objLoadWithAmount,"Load With Amount"));
			verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Header"));
			verifyElementPresent(MLWalletEloadPage.objMobileNumber,getTextVal(MLWalletEloadPage.objMobileNumber,"Mobile Number"));
			verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
			verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
			verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
			verifyElementPresent(MLWalletEloadPage.objConfirmBtn,getTextVal(MLWalletEloadPage.objConfirmBtn,"Button"));
			logger.info("BE_TC_15, Buy ELoad Transaction Details Page UI Validated");
			ExtentReporter.extentLoggerPass("BE_TC_15", "BE_TC_15, Buy ELoad Transaction Details Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Select From Contacts Functionality");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"))){
			logger.info("BE_TC_16, Buy ELoad, After clicking on Select From Contacts Application Navigates to Contacts Page");
			ExtentReporter.extentLoggerPass("BE_TC_16", "BE_TC_16, Buy ELoad, After clicking on Select From Contacts Application Navigates to Contacts Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactsPageUIValidation_BE_TC_17(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contacts Page UI Validation");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"))) {
			for (int i = 1; i <= 7; i++) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1) {
						String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Name : " + sContactName + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
					}
					if (j == 2) {
						String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Number : " + sContactNumber + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
					}
				}
			}
			logger.info("BE_TC_17, Buy ELoad Contacts Page UI Validated");
			ExtentReporter.extentLoggerPass("BE_TC_17", "BE_TC_17, Buy ELoad Contacts Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactsPageBackBtnValidation_BE_TC_18(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contacts Page Back Arrow Button Validation");
		buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(sTier);
		verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contacts Page back arrow Button");
		if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
			logger.info("BE_TC_18, Buy ELoad Contacts Page Back Arrow Button Validated");
			ExtentReporter.extentLoggerPass("BE_TC_18", "BE_TC_18, Buy ELoad Contacts Page Back Arrow Button Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadSearchFieldValidation_BE_TC_19(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Search Field Validation");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
		if(verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search")) {
			type(MLWalletEloadPage.objSearch, prop.getproperty("contactName"), "Contact Search");
			verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
			verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
			logger.info("BE_TC_19, Buy ELoad Search Field Validated");
			ExtentReporter.extentLoggerPass("BE_TC_19", "BE_TC_19, Buy ELoad Search Field Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadAddContactToFavorites_BE_TC_20(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Add Contact To Favorites");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
		type(MLWalletEloadPage.objSearch, prop.getproperty("contactName"), "Contact Search");
		verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
		String sContactName = getText(MLWalletEloadPage.objSearchedContactName);
		verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
		String sContactNumber = getText(MLWalletEloadPage.objSearchedContactNumber);
		hideKeyboard();
		verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
		verticalSwipeByPercentages(0.5,0.9,0.5);
		verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"))){
			String sContactNameInFavorites = getText(MLWalletEloadPage.objSearchedContactName);
			assertionValidation(sContactName,sContactNameInFavorites);
			String sContactNumberInFavorites = getText(MLWalletEloadPage.objSearchedContactNumber);
			assertionValidation(sContactNumber,sContactNumberInFavorites);
			logger.info("BE_TC_20, Buy ELoad Add Contact To Favorites Validated");
			ExtentReporter.extentLoggerPass("BE_TC_20", "BE_TC_20, Buy ELoad Add Contact To Favorites Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadAddedContactToFavoritesValidation_BE_TC_21(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Added Contacts To Favorites validation");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
		type(MLWalletEloadPage.objSearch, prop.getproperty("contactNumber"), "Contact Search");
		verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
		verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
		hideKeyboard();
		verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
		verticalSwipeByPercentages(0.5,0.9,0.5);
		clearField(MLWalletEloadPage.objSearch,"Search field");
		if(verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"))){
			waitTime(3000);
			for (int i = 1; i <= 2; i++) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1) {
						String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Name : " + sContactName + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
					}
					if (j == 2) {
						String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Number : " + sContactNumber + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
					}
				}
			}
			logger.info("BE_TC_21, Buy ELoad Added Contacts To Favorites Validated");
			ExtentReporter.extentLoggerPass("BE_TC_21", "BE_TC_21, Buy ELoad Added Contacts To Favorites Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadSearchAddedFavoriteContact_BE_TC_22(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Search Added Favorite Contact");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
		type(MLWalletEloadPage.objSearch, prop.getproperty("favoriteContact"), "Contact Search");
		verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
		verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
		hideKeyboard();
		verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
		verticalSwipeByPercentages(0.5,0.9,0.5);
		clearField(MLWalletEloadPage.objSearch,"Search field");
		verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search")) {
			type(MLWalletEloadPage.objSearch, prop.getproperty("favoriteContactNumber"), "Contact Search");
			verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
			verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
			logger.info("BE_TC_22, Buy ELoad Search Added Favorite Contact Validated");
			ExtentReporter.extentLoggerPass("BE_TC_22", "BE_TC_22, Buy ELoad Search Added Favorite Contact Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadSearchUnFavoriteContact_BE_TC_23(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Search UnFavorite Contact");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
		verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
		verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search")) {
			type(MLWalletEloadPage.objSearch, prop.getproperty("unFavoriteNumber"), "Contact Search");
			verifyElementPresent(MLWalletEloadPage.objNoFavoritesFoundMsg,getTextVal(MLWalletEloadPage.objNoFavoritesFoundMsg,"Message"));
			logger.info("BE_TC_23, Buy ELoad Search UnFavorite Contact Validated");
			ExtentReporter.extentLoggerPass("BE_TC_23", "BE_TC_23, Buy ELoad Search UnFavorite Contact Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadPromoConfirmationPopupValidation_BE_TC_24(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Promo Confirmation Popup Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction, "Promo");
		waitTime(5000);
		if(verifyElementPresent(MLWalletEloadPage.objPromoConfirmationPopup,getTextVal(MLWalletEloadPage.objPromoConfirmationPopup,"Popup Msg"))){
			verifyElementPresent(MLWalletEloadPage.objConfirmBtn,getTextVal(MLWalletEloadPage.objConfirmBtn,"Button"));
			verifyElementPresent(MLWalletEloadPage.objCancelBtn,getTextVal(MLWalletEloadPage.objCancelBtn,"Button"));
			logger.info("BE_TC_24, Buy ELoad Promo Confirmation Popup Validated");
			ExtentReporter.extentLoggerPass("BE_TC_24", "BE_TC_24, Buy ELoad Promo Confirmation Popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadOTPPageUIValidation_BE_TC_25(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad OTP page UI Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		waitTime(5000);
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		waitTime(5000);
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction,  "Promo");
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("BE_TC_25, Buy ELoad One Time Pin page UI Validated");
			ExtentReporter.extentLoggerPass("BE_TC_25", "BE_TC_25, Buy ELoad One Time Pin page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadSuccessfulTransactionUIValidation_BE_TC_26(String sTier, int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Successful Transaction UI Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		waitTime(5000);
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction,  "Promo");
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))) {
			verifyElementPresent(MLWalletEloadPage.objMobileNumberInTransactionDetails, getTextVal(MLWalletEloadPage.objMobileNumberInTransactionDetails, "Mobile Number"));
			verifyElementPresent(MLWalletEloadPage.objBuyELoadTime, getTextVal(MLWalletEloadPage.objBuyELoadTime, "Date and Time"));
			verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
			verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
			verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
			logger.info("BE_TC_26, Buy ELoad Successful Transaction UI validated");
			ExtentReporter.extentLoggerPass("BE_TC_26", "BE_TC_26, Buy ELoad Successful Transaction UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadRecentTransactionUIValidation_BE_TC_27() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Recent Transaction UI Validation");
		buyELoadSuccessfulTransactionUIValidation_BE_TC_26(prop.getproperty("Fully_Verified"),3);
		verifyElementPresentAndClick(MLWalletEloadPage.objBackToHomeBtn,getTextVal(MLWalletEloadPage.objBackToHomeBtn,"Button"));
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"))){
			verifyElementPresent(MLWalletHomePage.objBuyELoadTransaction,getTextVal(MLWalletHomePage.objBuyELoadTransaction,"Transaction"));
			verifyElementPresent(MLWalletHomePage.objPosted,getTextVal(MLWalletHomePage.objPosted,"Status"));
			logger.info("BE_TC_27, Buy ELoad Recent Transaction UI validated");
			ExtentReporter.extentLoggerPass("BE_TC_27", "BE_TC_27, Buy ELoad Recent Transaction UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}



	public void buyELoadTransactionDetailsUIValidation_BE_TC_28() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Transaction Details UI Validation");
		buyELoadRecentTransactionUIValidation_BE_TC_27();
		click(MLWalletHomePage.objBuyELoadTransaction,getTextVal(MLWalletHomePage.objBuyELoadTransaction,"Transaction"));
		if(verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objLoadType, getTextVal(MLWalletTransactionHistoryPage.objLoadType, "Load Type"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("BE_TC_28, Buy ELoad Transaction Details UI validated");
			ExtentReporter.extentLoggerPass("BE_TC_28", "BE_TC_28, Buy ELoad Transaction Details UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationPopupValidation_BE_TC_51(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location Popup Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("BE_TC_51, Buy ELoad Location Popup Validated");
			ExtentReporter.extentLoggerPass("BE_TC_51", "BE_TC_51, Buy ELoad Location Popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationDenyFunctionality_BE_TC_52(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location Deny Functionality");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("BE_TC_52, Buy ELoad Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_52", "BE_TC_52, Buy ELoad Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location Permission Deny Close Button Functionality");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Page"))){
				logger.info("BE_TC_53, Buy ELoad Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("BE_TC_53", "BE_TC_53, Buy ELoad Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location Permission Deny open Settings Button Functionality Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(MLWalletEloadPage.objAppInfo, getTextVal(MLWalletEloadPage.objAppInfo, "Page"))) {
				logger.info("BE_TC_54, Buy ELoad Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("BE_TC_54", "BE_TC_54, Buy ELoad Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void buyELoadLocationPopUpAllowFunctionality_BE_TC_55(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location popup Allow Button Functionality Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage,getTextVal(MLWalletEloadPage.objLoadSelectionPage,"Page"))){
				logger.info("BE_TC_55, Buy ELoad Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("BE_TC_55", "BE_TC_55, Buy ELoad Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void buyELoadContactsPermissionPopup_BE_TC_56(String sTier) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contacts Permission Popup");
		mlWalletLogin(sTier);
		click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts, getTextVal(MLWalletEloadPage.objSelectFromContacts, "Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			verifyElementPresent(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
			verifyElementPresent(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
			logger.info("BE_TC_56, Buy ELoad Contacts Permission Popup Validated");
			ExtentReporter.extentLoggerPass("BE_TC_56", "BE_TC_56, Buy ELoad Location popup Allow Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contacts Permission Popup Allow Button Functionality");
		buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Fully_Verified"));
		click(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
		if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1) {
						String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Name : " + sContactName + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
					}
					if (j == 2) {
						String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Number : " + sContactNumber + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
					}
				}
			}
			logger.info("BE_TC_57, Buy ELoad Contacts Permission Popup Allow Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_57", "BE_TC_57, Buy ELoad Contacts Permission Popup Allow Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contacts Permission Popup Deny Button Functionality");
		buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Fully_Verified"));
		click(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contact Page Back Arrow Button");
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		if(verifyElementPresent(MLWalletEloadPage.objContactPopupMsg,getTextVal(MLWalletEloadPage.objContactPopupMsg,"Popup"))){
			verifyElementPresent(MLWalletEloadPage.objAllowAccess,getTextVal(MLWalletEloadPage.objAllowAccess,"Button"));
			verifyElementPresent(MLWalletEloadPage.objAskMeLater,getTextVal(MLWalletEloadPage.objAskMeLater,"Button"));
			logger.info("BE_TC_58, Buy ELoad Contacts Permission Popup Deny Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_58", "BE_TC_58, Buy ELoad Contacts Permission Popup Deny Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contact PermissionTwo Deny Button Functionality");
		buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
		click(MLWalletEloadPage.objAllowAccess,getTextVal(MLWalletEloadPage.objAllowAccess,"Button"));
		verifyElementPresentAndClick(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
		if(verifyElementPresent(MLWalletEloadPage.objNoContactsFoundMsg,getTextVal(MLWalletEloadPage.objNoContactsFoundMsg,"Message"))){
			logger.info("BE_TC_59, Buy ELoad Contact PermissionTwo Deny Button Functionality validated");
			ExtentReporter.extentLoggerPass("BE_TC_59", "BE_TC_59, Buy ELoad Contact PermissionTwo Deny Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contact PermissionTwo Allow Button Functionality");
		buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
		click(MLWalletEloadPage.objAllowAccess, getTextVal(MLWalletEloadPage.objAllowAccess, "Button"));
		verifyElementPresentAndClick(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
		if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1) {
						String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Name : " + sContactName + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
					}
					if (j == 2) {
						String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Number : " + sContactNumber + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
					}
				}
			}
			logger.info("BE_TC_60, Buy ELoad Contact PermissionTwo Allow Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_60", "BE_TC_60, Buy ELoad Contact PermissionTwo Allow Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61(String sTier,int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Internet Interruption While Entering OTP Validation");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 20);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("BE_TC_61, Buy ELoad Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("BE_TC_61", "BE_TC_61, Buy ELoad Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location Permission Ask Me Later Button Functionality");
		buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
		verifyElementPresentAndClick(MLWalletEloadPage.objAskMeLater,getTextVal(MLWalletEloadPage.objAskMeLater,"Button"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			verifyElementPresent(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
			verifyElementPresent(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
			logger.info("BE_TC_62, Buy ELoad Location Permission Ask Me Later Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_62", "BE_TC_62, Buy ELoad Location Permission Ask Me Later Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location PermissionTwo Deny Button Functionality");
		buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
		click(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
		if(verifyElementPresent(MLWalletEloadPage.objNoContactsFoundMsg,getTextVal(MLWalletEloadPage.objNoContactsFoundMsg,"Message"))){
			logger.info("BE_TC_63, Buy ELoad Location PermissionTwo Deny Button Functionality validated");
			ExtentReporter.extentLoggerPass("BE_TC_63", "BE_TC_63, Buy ELoad Location PermissionTwo Deny Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Location PermissionTwo Allow Button Functionality");
		buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
		click(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
		if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1) {
						String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Name : " + sContactName + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
					}
					if (j == 2) {
						String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
						logger.info("Contact Number : " + sContactNumber + " is displayed");
						ExtentReporter.extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
					}
				}
			}
			logger.info("BE_TC_64, Buy ELoad Location PermissionTwo Allow Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_64", "BE_TC_64, Buy ELoad Location PermissionTwo Allow Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Contact Popup Not displayed After Clicking On Deny Button Validation");
		buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
		click(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
		verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contacts Page Back Btn");
		verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"));
		verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
		if(verifyElementNotPresent(MLWalletEloadPage.objContactPopupMsg,"Contacts Popup",5)){
			logger.info("BE_TC_65, Buy ELoad Contact Popup Not displayed After Clicking On Deny Button is Validated");
			ExtentReporter.extentLoggerPass("BE_TC_65", "BE_TC_65, Buy ELoad Contact Popup Not displayed After Clicking On Deny Button is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadNewTransactionBtnFunctionality_BE_TC_66() throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad New Transaction Button Functionality");
		buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Fully_Verified"),3);
		verifyElementPresentAndClick(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
		if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
			logger.info("BE_TC_66, Buy ELoad New Transaction Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BE_TC_66", "BE_TC_66, Buy ELoad New Transaction Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069(String sTier, int promotab) throws Exception {
		ExtentReporter.HeaderChildNode("Buy ELoad Transaction Validation After Minimizing App");
		eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
		enableLocation_PopUp();
		verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
		click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
		waitTime(5000);
		click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
		verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
		waitTime(5000);
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
		click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))){
			logger.info("BE_TC_069, Buy ELoad Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("BE_TC_069", "BE_TC_069, Buy ELoad Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}









//=========================================== Cash In  Via Branch ================================//


	public void cashInViaBranchNavigation(String sTier) throws Exception {
		mlWalletLogin(sTier);
		click(MLWalletCashInViaBranch.objCashInMenu, "Cash In");
		verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
		click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
	}
		public void cashInViaBranchEnterAmount(String sAmount) throws Exception {
		click(MLWalletCashInViaBranch.objAmountTextField, "Amount Text Field");
		type(MLWalletCashInViaBranch.objAmountTextField, sAmount, "Amount Text Field");
		hideKeyboard();
		Swipe("up", 1);
		click(MLWalletCashInViaBranch.objNextButton, "Next Button");
	}

	public void maxTransactionLimitValidation(String sTier) throws Exception {
		cashInViaBranchNavigation(sTier);
		cashInViaBranchEnterAmount("50001");
		click(MLWalletCashInViaBranch.objContinueButton, getTextVal(MLWalletCashInViaBranch.objContinueButton, "Button"));
		Swipe("UP", 2);
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
	}


	public void cashInviaBranch_ValidAmount_Scenario_CIBR_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("ML_Wallet_Cash_In_Via_Barnch_ValidAmount_Scenario");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
			verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
			verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
			verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
			verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
			verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
			Swipe("DOWN",1);
			Swipe("UP",1);
			if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
				verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
				logger.info("'CIBR_TC_01', To validate valid Amount in Cash In ML Branch ");
				ExtentReporter.extentLoggerPass("'CIBR_TC_01", "'CIBR_TC_01', To validate valid Amount in Cash In ML Branch ");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBranchCancelTransactionScenario_CIBR_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Cash In Via Branch Cancel Transaction Scenario");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objBackToHomeBtn, getTextVal(MLWalletCashInViaBranch.objBackToHomeBtn, "Button"));
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
			verifyElementPresent(MLWalletCashInBank.objCancelled, getTextVal(MLWalletCashInBank.objCancelled, "Status"));
			logger.info("'CIBR_TC_02', To validate Cancel Transaction in Cash In ML Branch");
			ExtentReporter.extentLoggerPass("'CIBR_TC_02","'CIBR_TC_02', To validate Cancel Transaction in Cash In ML Branch");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInviaBranch_Invalid_Amount_CIBR_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Cash In via Branch Invalid Amount");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("0");
		if (verifyElementPresent(MLWalletCashInViaBranch.objInvalidAmountMsg, getTextVal(MLWalletCashInViaBranch.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(MLWalletCashInViaBranch.objInvalidAmountMsg);
			String sExpectedErrorMsg = "The amount should not be less than 1";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("'CIBR_TC_03', 'CIBR_TC_03' To validate Invalid Amount");
			ExtentReporter.extentLoggerPass("CIBR_TC_03", "'CIBR_TC_03', To validate Invalid Amount");
		}
	}


	public void cashInViaBranch_Maximum_Limit_Amount_CIBR_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Cash In via Branch Maximum Limit Amount");
		maxTransactionLimitValidation(prop.getproperty("Branch_Verified"));
		if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("'CIBR_TC_04', ML Wallet Cash In via Branch Maximum Limit Amount Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_04", "'CIBR_TC_04', ML Wallet Cash In via Branch Maximum Limit Amount Validated");
		}
	}


	public void cashInViaBRanchInvalidAmount_CIBR_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Cash In via Branch Invalid Amount");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("");
		if (verifyElementPresent(MLWalletCashInViaBranch.objInvalidAmountMsg, getTextVal(MLWalletCashInViaBranch.objInvalidAmountMsg, "Error Message"))) {
			String sInvalidAmountErrorMsg = getText(MLWalletCashInViaBranch.objInvalidAmountMsg);
			String sExpectedErrorMsg = "Amount is required";
			assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
			logger.info("CIBR_TC_05, Amount is required - Error Message is validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_05", "CIBR_TC_05, Amount is required - Error Message is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchUIValidation_CIBR_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch UI Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(MLWalletCashInViaBranch.objCashInMenu,5);
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInMenu,getTextVal(MLWalletCashInViaBranch.objCashInMenu,"Page"))){
			verifyElementPresent(MLWalletCashInViaBranch.objBranchCashIn,getTextVal(MLWalletCashInViaBranch.objBranchCashIn,"Header"));
			verifyElementPresent(MLWalletCashInViaBranch.objUserName,getTextVal(MLWalletCashInViaBranch.objUserName,"User Name"));
			verifyElementPresent(MLWalletCashInViaBranch.objUserNumber,getTextVal(MLWalletCashInViaBranch.objUserNumber,"User Number"));
			verifyElementPresent(MLWalletCashInViaBranch.objAmountTextField,"Amount Input Field");
			 verifyElementPresent(MLWalletCashInViaBranch.objNextButton, "Next Button");
			logger.info("CIBR_TC_06, Cash In Via Branch UI validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_06", "CIBR_TC_06, Cash In Via Branch UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchBackBtnValidation_CIBR_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Back Btn Validation");
		cashInViaBranchUIValidation_CIBR_TC_06();
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCashInBranchBackBtn,"Cash In Branch Back Button");
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInMenu,getTextVal(MLWalletCashInViaBranch.objCashInMenu,"Page"))){
			logger.info("CIBR_TC_07, Cash In Via Branch Back Btn validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_07", "CIBR_TC_07, Cash In Via Branch Back Btn validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void cashInViaBranchNavigationToHomePageFromQRPage_CIBR_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
		if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Header"))){
			logger.info("CIBR_TC_08, Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_08", "CIBR_TC_08, Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchQRCodePageUIValidation_CIBR_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch QR Code UI Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		explicitWaitVisible(MLWalletCashInViaBranch.objCashInMenu,5);
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))) {
			verifyElementPresent(MLWalletCashInViaBranch.objQRCode,"QR Code");
			verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
			verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
			verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
			verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
			verifyElementPresent(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
			logger.info("CIBR_TC_09, Cash In Via Branch QR Code UI validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_09", "CIBR_TC_09, Cash In Via Branch QR Code UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchPendingTransaction_CIBR_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
			verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
			click(MLWalletCashInViaBranch.objCashInMenu, "Cash In");
			verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
			click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
			if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))) {
				verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
				verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
				verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
				verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
				verifyElementPresent(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
				logger.info("CIBR_TC_11, Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_11", "CIBR_TC_11, Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBranchCancelBtnValidationOnCashInConfirmPopUp_CIBR_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Cancel Button validation on CashIn Confirm Popup");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		explicitWaitVisible(MLWalletCashInViaBranch.objCancelTransactionBtn,5);
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"))){
			logger.info("CIBR_TC_12, Cash In Via Branch, On clicking Cancel Button on CashIn Confirm Popup Application Navigates to CashIn Page with Cancelled Status");
			ExtentReporter.extentLoggerPass("CIBR_TC_12", "CIBR_TC_12, Cash In Via Branch, On clicking Cancel Button on CashIn Confirm Popup Application Navigates to CashIn Page with Cancelled Status");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchTappingOutsideTheCashInConfirmationPopUp_CIBR_TC_13(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount(sAmount);
		verifyElementPresent(MLWalletCashInViaBranch.objCashInConfirmationPopup,getTextVal(MLWalletCashInViaBranch.objCashInConfirmationPopup,"Popup"));
		tapUsingCoordinates(500,1000);
		logger.info("Clicked OutSide the Dragon Pay Popup");
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInConfirmationPopup,getTextVal(MLWalletCashInViaBranch.objCashInConfirmationPopup,"Popup"))){
			logger.info("CIBR_TC_13, Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup, Popup doesn't closed");
			ExtentReporter.extentLoggerPass("CIBR_TC_13", "CIBR_TC_13, Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup, Popup doesn't closed");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchGoBackBtnValidationOnCashInConfirmPopUp_CIBR_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Go Back Button validation on CashIn Confirm Popup");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objContinueButton,getTextVal(MLWalletCashInViaBranch.objContinueButton,"button"));
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objGoBackBtn,getTextVal(MLWalletCashInViaBranch.objGoBackBtn,"Button"));
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"))){
			logger.info("CIBR_TC_14, Cash In Via Branch, On clicking Go Back Button on CashIn Confirm Popup Application Navigates to CashIn Page with pending Status");
			ExtentReporter.extentLoggerPass("CIBR_TC_14", "CIBR_TC_14, Cash In Via Branch, On clicking Go Back Button on CashIn Confirm Popup Application Navigates to CashIn Page with pending Status");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBankTappingOutsideTheCancelTransactionConfirmationPopup_CIBR_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"));
		tapUsingCoordinates(500,1000);
		logger.info("Clicked OutSide the Dragon Pay Popup");
		if(verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"))){
			logger.info("CIBR_TC_15, Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup, Popup doesn't closed");
			ExtentReporter.extentLoggerPass("CIBR_TC_15", "CIBR_TC_15, Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup, Popup doesn't closed");
			System.out.println("-----------------------------------------------------------");
		}
	}




	public void cashInViaBranchBuyerTierUser_CIBR_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Buyer Tier User");
		cashInViaBranchNavigation(prop.getproperty("Buyer_Tier"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletCashInViaBranch.objMaxLimitTxt,10);
		if (verifyElementPresent(MLWalletCashInViaBranch.objMaxLimitTxt, getTextVal(MLWalletCashInViaBranch.objMaxLimitTxt, "Text Message"))) {
			String sErrorMessage = getText(MLWalletCashInViaBranch.objMaxLimitTxt);
			String ExpectedTxt = "Branch Cash-in not allowed. Please upgrade to a higher verification level to add more balance.";
			assertionValidation(sErrorMessage, ExpectedTxt);
			verifyElementPresent(MLWalletCashInViaBranch.objUpgradeNowBtn,getTextVal(MLWalletCashInViaBranch.objUpgradeNowBtn,"Button"));
			logger.info("CIBR_TC_16, Cash In Via Branch Buyer Tier User, Branch CashIn Not Allowed-Error message Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_16", "CIBR_TC_16, Cash In Via Branch Buyer Tier User, Branch CashIn Not Allowed-Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchSemiVerifiedTierUser_CIBR_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Semi verified Tier User");
		cashInViaBranchNavigation(prop.getproperty("Semi_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
			verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
			verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
			verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
			verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
			verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
			Swipe("DOWN",1);
			Swipe("UP",1);
			if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
				verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
				logger.info("CIBR_TC_17, Cash In Via Branch Semi verified Tier User Validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_17", "CIBR_TC_17, Cash In Via Branch Semi verified Tier User Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBranchFullyVerifiedTierUser_CIBR_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Fully verified Tier User");
		cashInViaBranchNavigation(prop.getproperty("Fully_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
			verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
			verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
			verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
			verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
			verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
			Swipe("DOWN",1);
			Swipe("UP",1);
			if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
				verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
				logger.info("CIBR_TC_18, Cash In Via Branch Fully verified Tier User Validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_18", "CIBR_TC_18, Cash In Via Branch Fully verified Tier User Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashInViaBranchMaxTransactionBuyerTierUser_CIBR_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Max Transaction Buyer Tier User");
		maxTransactionLimitValidation(prop.getproperty("Buyer_Tier"));
		waitTime(5000);
		if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P20,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("CIBR_TC_19, Cash In Via Branch Max Transaction Buyer Tier User,Branch CashIn Not Allowed-Error message Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_19", "CIBR_TC_19, Cash In Via Branch Max Transaction Buyer Tier User,Branch CashIn Not Allowed-Error message Validated");
		}
	}

	public void cashInViaBranchMaxTransactionSemiVerifiedTierUser_CIBR_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Max Transaction Limit Semi-verified Tier User");
		maxTransactionLimitValidation(prop.getproperty("Semi_Verified"));
		waitTime(5000);
		if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("CIBR_TC_20, Cash In Via Branch Max Transaction Limit Semi-verified Tier User Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_20", "CIBR_TC_20, Cash In Via Branch Max Transaction Limit Semi-verified Tier User Validated");
		}
	}


	public void cashInViaBranchMaxTransactionFullyVerifiedTierUser_CIBR_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Max Transaction Limit Fully-verified Tier User");
		maxTransactionLimitValidation(prop.getproperty("Fully_verified"));
		waitTime(5000);
		if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
			String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
			String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
			assertionValidation(sErrorMsg, sExpectedErrorMsg);
			logger.info("CIBR_TC_21, Cash In Via Branch Max Transaction Limit Fully-verified Tier User Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_21", "CIBR_TC_21, Cash In Via Branch Max Transaction Limit Fully-verified Tier User Validated");
		}
	}

	public void cashInViaBranchTransactionDetailsUIValidation_CIBR_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Transaction Details UI Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
		verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
		verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
		verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
		verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresentAndClick(MLWalletCashInBank.objCashInTransaction, getTextVal(MLWalletCashInBank.objCashInTransaction, "Transaction"));
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashIn, getTextVal(MLWalletTransactionHistoryPage.objTotalCashIn, "Total Cash In"));
			logger.info("CIBR_TC_22,Cash In Via Branch Transaction Details UI Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_22", "'CIBR_TC_22',Cash In Via Branch Transaction Details UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchTransactionPendingStatusValidation_CIBR_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Transaction Pending status Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
		verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
		verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
		verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
		verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
		Swipe("DOWN",2);
		Swipe("UP",1);
		if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
			verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
			logger.info("CIBR_TC_23,Cash In Via Branch Transaction Pending status Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_23", "'CIBR_TC_23',Cash In Via Branch Transaction Pending status Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchTransactionCancelledStatusValidation_CIBR_TC_26() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Transaction Cancelled Status Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
		verifyElementPresentAndClick(MLWalletCashInViaBranch.objBackToHomeBtn, getTextVal(MLWalletCashInViaBranch.objBackToHomeBtn, "Button"));
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
			verifyElementPresent(MLWalletCashInBank.objCancelled, getTextVal(MLWalletCashInBank.objCancelled, "Status"));
			logger.info("'CIBR_TC_26',Cash In Via Branch Transaction Cancelled Status Validated");
			ExtentReporter.extentLoggerPass("'CIBR_TC_26","'CIBR_TC_26', Cash In Via Branch Transaction Cancelled Status Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchLocationPopupValidation_CIBR_TC_28() throws Exception {
		cashInViaBranchCancelTransactionScenario_CIBR_TC_02();
		ExtentReporter.HeaderChildNode("Cash In Via Branch Location Pop up Validation");
		click(MLWalletCashInViaBranch.objCashInMenu, "Cash In");
		verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
		click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("CIBR_TC_28, Cash In Via Branch Location Pop up Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_28", "CIBR_TC_28, Cash In Via Branch Location Pop up Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchLocationDenyFunctionality_CIBR_TC_29() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Location Deny Functionality");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("CIBR_TC_29, Cash In Via Branch Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_29", "CIBR_TC_29, Cash In Via Branch Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void cashInViaBranchLocationPermissionDenyCloseBtnFunctionality_CIBR_TC_30() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Location Permission Deny Close Button Functionality");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(MLWalletCashInViaBranch.objBranchCashIn,getTextVal(MLWalletCashInViaBranch.objBranchCashIn,"Page"))){
				logger.info("CIBR_TC_30, Cash In Via Branch Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_30", "CIBR_TC_30, Cash In Via Branch Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}

	public void cashInViaBranchLocationPermissionDenyOpenSettingsBtnFunctionality_CIBR_TC_31() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Location Permission Deny Open Settings Button Functionality");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(MLWalletCashInViaBranch.objAppInfo, getTextVal(MLWalletCashInViaBranch.objAppInfo, "Page"))) {
				logger.info("CIBR_TC_31, Cash In Via Branch Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_31", "CIBR_TC_31, Cash In Via Branch Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashInViaBranchInternetInterruptionWhileEnteringOTP_CIBR_TC_33() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Internet Interruption While Entering OTP Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		waitTime(5000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("CIBR_TC_33, Cash In Via Branch Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_33", "CIBR_TC_33, Cash In Via Branch Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
		setWifiConnectionToONOFF("ON");
	}


	public void cashInViaBranchLocationPopUpAllowFunctionality_CIBR_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Location popup Allow Button Functionality Validation");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Bank Page"))){
				logger.info("CIBR_TC_32, Cash In Via Branch Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("CIBR_TC_32", "CIBR_TC_32, Cash In Via Branch Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void cashInViaBranchTransactionValidationAfterMinimizingApp_CIBR_TC_36() throws Exception {
		ExtentReporter.HeaderChildNode("Cash In Via Branch Transaction Validation After Minimizing App");
		cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
		cashInViaBranchEnterAmount("100");
		waitTime(2000);
		verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
				getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
		click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
		enterOTP(prop.getproperty("Valid_OTP"));
		enableLocation_PopUp();
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Bank Page"))){
			logger.info("CIBR_TC_36, Cash In Via Branch Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("CIBR_TC_36", "CIBR_TC_36, Cash In Via Branch Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}













//============================== Log Out  ===============================================//


	public void logOutMinimizeAndRelaunch_Lout_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Log Out Minimize and relaunch the application");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		logger.info("Application Minimized for 5 Seconds");
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Applicaton relaunched after 5 Seconds");
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("User should not be able to logout from the app");
			logger.info("Lout_TC_03, Log Out Minimize and relaunch the application validated");
			ExtentReporter.extentLoggerPass("Lout_TC_03", "Lout_TC_03, Log Out Minimize and relaunch the application validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logOutAppKillAndRelaunch_Lout_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Kill Application and Relaunch");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		DriverManager.getAppiumDriver().closeApp();
		logger.info("Killed the Application");
		DriverManager.getAppiumDriver().launchApp();
		logger.info("Relaunch the Application");
		if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"page"))){
			logger.info("Lout_TC_04, After Killing and relaunch the Application, Application got logged off");
			ExtentReporter.extentLoggerPass("Lout_TC_04", "Lout_TC_04, After Killing and relaunch the Application, Application got logged off");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logOUtPopUpValidation_Lout_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("LogOut Popup Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
		waitTime(3000);
		if(verifyElementPresent(MLWalletLogOutPage.objLogoOutPopupMsg,getTextVal(MLWalletLogOutPage.objLogoOutPopupMsg,"Pop up Message"))){
			String sLogOutPopupMsg = getText(MLWalletLogOutPage.objLogoOutPopupMsg);
			String sExpectedErrorMsg = "Are you sure you would like to logout?";
			assertionValidation(sLogOutPopupMsg,sExpectedErrorMsg);
			verifyElementPresent(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
			verifyElementPresent(MLWalletLogOutPage.objCancelBtn,getTextVal(MLWalletLogOutPage.objCancelBtn,"Button"));
			logger.info("Lout_TC_05, LogOut Popup validated");
			ExtentReporter.extentLoggerPass("Lout_TC_05", "Lout_TC_05, LogOut Popup validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logOutPopUpCancelBtnValidation_Lout_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("LogOut PopUp Cancel Button Validation");
		logOUtPopUpValidation_Lout_TC_05();
		click(MLWalletLogOutPage.objCancelBtn,getTextVal(MLWalletLogOutPage.objCancelBtn,"Button"));
		if(verifyElementPresent(MLWalletLogOutPage.objHome,getTextVal(MLWalletLogOutPage.objHome,"Button"))){
			logger.info("Lout_TC_06, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
			ExtentReporter.extentLoggerPass("Lout_TC_06", "Lout_TC_06, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logOutPopUpLogOutBtnValidation_Lout_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("LogOut PopUp LogOut Btn Validation");
		logOUtPopUpValidation_Lout_TC_05();
		click(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
		if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
			logger.info("Lout_TC_07, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
			ExtentReporter.extentLoggerPass("Lout_TC_07", "Lout_TC_07, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logoutChangeNumberUIValidation_Lout_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Log Out Change Number UI page Validation");
		logOUtPopUpValidation_Lout_TC_05();
		click(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
		if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
			verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Link"));
			verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Link"));
			verifyElementPresent(MLWalletLoginPage.objAppVersionChangeNumber,getTextVal(MLWalletLoginPage.objAppVersionChangeNumber,"App Version"));
			logger.info("Lout_TC_08, Log Out Change Number UI page validated");
			ExtentReporter.extentLoggerPass("Lout_TC_08", "Lout_TC_08, Log Out Change Number UI page validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void logInWithChangeNumber_Lout_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Log In With Change Number");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		mlWalletLogout();
		click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
		mlWalletLogin(prop.getproperty("Fully_verified"));
		if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
			logger.info("Lout_TC_09,Application Logged In Successfully with Change Number");
			ExtentReporter.extentLoggerPass("Lout_TC_09","Lout_TC_09, Application Logged In Successfully with Change Number");
			System.out.println("-----------------------------------------------------------");
		}
	}

//============================================== Branch Locator ===================================//

	public void branchLocatorNavigation() throws Exception {
		if(verifyElementPresentAndClick(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"))){
			enableLocation_PopUp();
			verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"));
			logger.info("Navigated to Branch Locator page");
		}else{
			logger.info("Not Navigated to Branch Locator Page");
		}
	}

	public void branchLocatorPageValidation() throws Exception {
		if(verifyElementPresent(MLWalletBranchLocator.objSearchBranch,getTextVal(MLWalletBranchLocator.objSearchBranch,"Header"))) {
			verifyElementPresent(MLWalletBranchLocator.obj24HoursOnly, getTextVal(MLWalletBranchLocator.obj24HoursOnly, "Option"));
			verifyElementPresent(MLWalletBranchLocator.objSearchBranchField, "Search Branch Input Field");
			verifyElementPresent(MLWalletBranchLocator.objLuzon, getTextVal(MLWalletBranchLocator.objLuzon, "Button"));
			click(MLWalletBranchLocator.objLuzon, getTextVal(MLWalletBranchLocator.objLuzon, "Button"));
			verifyElementPresent(MLWalletBranchLocator.objVisayas, getTextVal(MLWalletBranchLocator.objVisayas, "Button"));
			verifyElementPresent(MLWalletBranchLocator.objMindanao, getTextVal(MLWalletBranchLocator.objMindanao, "Button"));
			verifyElementPresent(MLWalletBranchLocator.objMLUS, getTextVal(MLWalletBranchLocator.objMLUS, "Button"));
			Swipe("UP",1);
			verifyElementPresent(MLWalletBranchLocator.objBranchesNearYou, getTextVal(MLWalletBranchLocator.objBranchesNearYou, "Map Header"));
		}
	}

//==========================================================================================================//

	public void branchLocatorNavigationFromMPinPage_BL_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator Page Navigation from MPin Page and UI Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		mlWalletLogout();
		branchLocatorNavigation();
		if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
			branchLocatorPageValidation();
			logger.info("BL_TC_01, Branch Locator Page Navigation from MPin Page and UI Validated");
			ExtentReporter.extentLoggerPass("BL_TC_01","BL_TC_01, Branch Locator Page Navigation from MPin Page and UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorNavigationFromLogInPage_BL_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator page Navigation From Login Page and UI validation");
		branchLocatorNavigation();
		if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
			branchLocatorPageValidation();
			logger.info("BL_TC_02, Branch Locator Page Navigation from Login Page and UI Validated");
			ExtentReporter.extentLoggerPass("BL_TC_02","BL_TC_02, Branch Locator Page Navigation from Login Page and UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorHamburgerFunctionality_BL_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator Hamburger Button Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		if(verifyElementPresent(MLWalletBranchLocator.objHome,getTextVal(MLWalletBranchLocator.objHome,"Option"))){
			verifyElementPresent(MLWalletBranchLocator.objBranches,getTextVal(MLWalletBranchLocator.objBranches,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objProductAndServices,getTextVal(MLWalletBranchLocator.objProductAndServices,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objPromos,getTextVal(MLWalletBranchLocator.objPromos,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objBlog,getTextVal(MLWalletBranchLocator.objBlog,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objNewsLetters,getTextVal(MLWalletBranchLocator.objNewsLetters,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objMLUSBtn,getTextVal(MLWalletBranchLocator.objMLUSBtn,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objFAQ,getTextVal(MLWalletBranchLocator.objFAQ,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objCareers,getTextVal(MLWalletBranchLocator.objCareers,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objLogin,getTextVal(MLWalletBranchLocator.objLogin,"Option"));
			verifyElementPresent(MLWalletBranchLocator.objDownloadApp,getTextVal(MLWalletBranchLocator.objDownloadApp,"Option"));
			logger.info("BL_TC_05, Branch Locator Hamburger Button Functionality Validated");
			ExtentReporter.extentLoggerPass("BL_TC_05","BL_TC_05, Branch Locator Hamburger Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorBranchesButtonFunctionality_BL_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator Branches Button Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranches,getTextVal(MLWalletBranchLocator.objBranches,"Option"));
		if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
			branchLocatorPageValidation();
			logger.info("BL_TC_07, Branch Locator Branches Button Functionality validated");
			ExtentReporter.extentLoggerPass("BL_TC_07","BL_TC_07, Branch Locator Branches Button Functionality validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorPromosFunctionality_BL_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator Promos Button Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		verifyElementPresentAndClick(MLWalletBranchLocator.objPromos,getTextVal(MLWalletBranchLocator.objPromos,"Option"));
		waitTime(10000);
		if(verifyElementPresent(MLWalletBranchLocator.objPromos,getTextVal(MLWalletBranchLocator.objPromos,"Page"))){
			logger.info("BL_TC_08, Branch Locator Promos Button Functionality validated and App Navigated to Promos Page");
			ExtentReporter.extentLoggerPass("BL_TC_08","BL_TC_08, Branch Locator Promos Button Functionality validated and App Navigated to Promos Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorBlogFunctionality_BL_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator Blog Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		verifyElementPresentAndClick(MLWalletBranchLocator.objBlog,getTextVal(MLWalletBranchLocator.objBlog,"Option"));
		waitTime(10000);
		if(verifyElementPresent(MLWalletBranchLocator.objBlog,getTextVal(MLWalletBranchLocator.objBlog,"Page"))){
			logger.info("BL_TC_09, Branch Locator Blog Button Functionality validated and App Navigated to Blog Page");
			ExtentReporter.extentLoggerPass("BL_TC_09","BL_TC_09, Branch Locator Blog Button Functionality validated and App Navigated to Blog Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorNewsLettersFunctionality_BL_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator NewsLetter Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		verifyElementPresentAndClick(MLWalletBranchLocator.objNewsLetters,getTextVal(MLWalletBranchLocator.objNewsLetters,"Option"));
		waitTime(10000);
		if(verifyElementPresent(MLWalletBranchLocator.objNewsLetters,getTextVal(MLWalletBranchLocator.objNewsLetters,"Page"))) {
			logger.info("BL_TC_10, Branch Locator NewsLetter Button Functionality validated and App Navigated to NewsLetter Page");
			ExtentReporter.extentLoggerPass("BL_TC_10", "BL_TC_10, Branch Locator NewsLetter Button Functionality validated and App Navigated to NewsLetter Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void branchLocatorFAQFunctionality_BL_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Branch Locator FAQ Functionality");
		branchLocatorNavigation();
		verifyElementPresentAndClick(MLWalletBranchLocator.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
		verifyElementPresentAndClick(MLWalletBranchLocator.objFAQ,getTextVal(MLWalletBranchLocator.objFAQ,"Option"));
		waitTime(10000);
		if(verifyElementPresent(MLWalletBranchLocator.objFrequentlyAskedQuestions,getTextVal(MLWalletBranchLocator.objFrequentlyAskedQuestions,"Page"))) {
			logger.info("BL_TC_11, Branch Locator FAQ Button Functionality validated and App Navigated to Frequently Asked Questions Page");
			ExtentReporter.extentLoggerPass("BL_TC_11", "BL_TC_11, Branch Locator FAQ Button Functionality validated and App Navigated to Frequently Asked Questions Page");
			System.out.println("-----------------------------------------------------------");
		}
	}


//============================ Home & DashBoard ========================================================//


	public void verificationTierPerksPageValidation() throws Exception {
		verifyElementPresent(MLWalletHomePage.objMaxBalanceText,getTextVal(MLWalletHomePage.objMaxBalanceText,"Header"));
		verifyElementPresent(MLWalletHomePage.objMaxBalanceAmount,getTextVal(MLWalletHomePage.objMaxBalanceAmount,"Max Balance"));
		verifyElementPresent(MLWalletHomePage.objSendingLimitsCashOut,getTextVal(MLWalletHomePage.objSendingLimitsCashOut,"Header"));
		List<WebElement> values = findElements(MLWalletHomePage.objSendingLimitTransactionTypeAndAmount);
		for (int i = 0; i < values.size(); i++) {
			if (i % 2 == 0) {
				String sTransactionType = values.get(i).getText();
				logger.info("Transaction Type : " + sTransactionType + " is displayed");
				ExtentReporter.extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
			}
			if (i % 2 != 0) {
				String sAmountRange = values.get(i).getText();
				logger.info("Amount Range : " + sAmountRange + " is displayed");
				ExtentReporter.extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
			}
		}
		Swipe("UP",1);
		verifyElementPresent(MLWalletHomePage.objReceivingLimitsCashIn,getTextVal(MLWalletHomePage.objReceivingLimitsCashIn,"Header"));
		List<WebElement> values1 = findElements(MLWalletHomePage.objReceivingLimitsTransactionTypeAndAmount);
		for (int i = 0; i < values1.size(); i++) {
			if (i % 2 == 0) {
				String sTransactionType = values1.get(i).getText();
				logger.info("Transaction Type : " + sTransactionType + " is displayed");
				ExtentReporter.extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
			}
			if (i % 2 != 0) {
				String sAmountRange = values1.get(i).getText();
				logger.info("Amount Range : " + sAmountRange + " is displayed");
				ExtentReporter.extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
			}
		}
		Swipe("UP",1);
		verifyElementPresent(MLWalletHomePage.objPurchaseLimits,getTextVal(MLWalletHomePage.objPurchaseLimits,"Header"));
		List<WebElement> values2 = findElements(MLWalletHomePage.objPurchaseLimitsTransactionTypeAndAmount);
		for (int i = 0; i < values2.size(); i++) {
			if (i % 2 == 0) {
				String sTransactionType = values2.get(i).getText();
				logger.info("Transaction Type : " + sTransactionType + " is displayed");
				ExtentReporter.extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
			}
			if (i % 2 != 0) {
				String sAmountRange = values2.get(i).getText();
				logger.info("Amount Range : " + sAmountRange + " is displayed");
				ExtentReporter.extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
			}
		}
	}






//=================================================================================================================//

	public void mlWalletHomePageUIValidation_HD_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page UI Validation");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		if (verifyElementPresent(MLWalletHomePage.objAvailableBalanceHeader, getTextVal(MLWalletHomePage.objAvailableBalanceHeader, "Header"))) {
			verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
			verifyElementPresent(MLWalletHomePage.objAvailableBalance,getTextVal(MLWalletHomePage.objAvailableBalance,"Available Balance"));
			verifyElementPresent(MLWalletHomePage.objCashInIcon,getTextVal(MLWalletHomePage.objCashInIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objSendTransferIcon,getTextVal(MLWalletHomePage.objSendTransferIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objCashOutIcon,getTextVal(MLWalletHomePage.objCashOutIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objPayBillsIcon,getTextVal(MLWalletHomePage.objPayBillsIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objBuyELoadIcon,getTextVal(MLWalletHomePage.objBuyELoadIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objShopItemsIcon,getTextVal(MLWalletHomePage.objShopItemsIcon,"Options"));
			verifyElementPresent(MLWalletHomePage.objMLLoans,getTextVal(MLWalletHomePage.objMLLoans,"Options"));
			verifyElementPresent(MLWalletHomePage.objUseQR,getTextVal(MLWalletHomePage.objUseQR,"Options"));
			verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"));
			Swipe("UP",1);
			List<WebElement> values = findElements(MLWalletHomePage.objTransactions);

			for(int i=4 ; i<values.size(); i+=4){
				String sTransactionType = values.get(i).getText();
				logger.info("Transaction Type : " + sTransactionType + " is displayed");
				ExtentReporter.extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
			}
			for(int i=2 ; i<values.size(); i+=4){
				String sAmount = values.get(i).getText();
				logger.info("Amount : " + sAmount + " is displayed");
				ExtentReporter.extentLogger(" ", "Amount : " + sAmount + " is displayed");
			}
			Swipe("UP",1);
			verifyElementPresent(MLWalletHomePage.objSeeMore,getTextVal(MLWalletHomePage.objSeeMore,"Button"));
			logger.info("HD_TC_01, ML Wallet Home Page UI Validated");
			ExtentReporter.extentLoggerPass("HD_TC_01", "HD_TC_01, ML Wallet Home Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletLogOutFromHomePage_HD_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Logout from Home Page Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
		waitTime(3000);
		verifyElementPresentAndClick(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button on Popup"));
		if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
			logger.info("HD_TC_02, ML Wallet Application Logged out from Home Page Validated");
			ExtentReporter.extentLoggerPass("HD_TC_02", "HD_TC_02, ML Wallet Application Logged out from Home Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletSettingsNavigationFromHomePage_HD_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Settings Navigation from Hom Page");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objProfileIcon1,"Profile Icon");
		if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
			verifyElementPresent(MLWalletSettingsPage.objAccountDetails,getTextVal(MLWalletSettingsPage.objAccountDetails,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objChangeMLPin,getTextVal(MLWalletSettingsPage.objChangeMLPin,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objNotification,getTextVal(MLWalletSettingsPage.objNotification,"Option"));
			verifyElementPresent(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
			logger.info("HD_TC_03, ML Wallet Settings Navigation from Hom Page Validated");
			ExtentReporter.extentLoggerPass("HD_TC_03", "HD_TC_03, ML Wallet Settings Navigation from Hom Page Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageEyeIconValidation_HD_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page Eye Icon Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
		verifyElementPresent(MLWalletHomePage.objAvailableBalance,getTextVal(MLWalletHomePage.objAvailableBalance,"Available Balance"));
		verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
		verifyElementPresent(MLWalletHomePage.objHiddenAvailableBalance,getTextVal(MLWalletHomePage.objHiddenAvailableBalance,"Available Balance"));
		logger.info("HD_TC_04, ML Wallet Home Page Eye Icon Validated");
		ExtentReporter.extentLoggerPass("HD_TC_04", "HD_TC_04, ML Wallet Home Page Eye Icon Validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void mlWalletAccountTierLevelVerification_HD_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Account Tier Level Verification");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objIIcon,"i Icon");
		if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
			verificationTierPerksPageValidation();
			logger.info("HD_TC_05, ML Wallet Account Tier Level Verification");
			ExtentReporter.extentLoggerPass("HD_TC_05", "HD_TC_05, ML Wallet Account Tier Level Verification");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageRecentTransaction_HD_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page Recent Transaction validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"));
		Swipe("UP",1);
		List<WebElement> values = findElements(MLWalletHomePage.objTransactions);

		for(int i=4 ; i<values.size(); i+=4){
			String sTransactionType = values.get(i).getText();
			logger.info("Transaction Type : " + sTransactionType + " is displayed");
			ExtentReporter.extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
		}
		for(int i=2 ; i<values.size(); i+=4){
			String sAmount = values.get(i).getText();
			logger.info("Amount : " + sAmount + " is displayed");
			ExtentReporter.extentLogger(" ", "Amount : " + sAmount + " is displayed");
		}
		Swipe("UP",1);
		verifyElementPresent(MLWalletHomePage.objSeeMore,getTextVal(MLWalletHomePage.objSeeMore,"Button"));
		logger.info("HD_TC_06, ML Wallet Home Page Recent Transaction validated");
		ExtentReporter.extentLoggerPass("HD_TC_06", "HD_TC_06, ML Wallet Home Page Recent Transaction validated");
		System.out.println("-----------------------------------------------------------");
	}

	public void mlWalletHomePageWithdrawCash_HD_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page WithDraw Cash");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
		verifyElementPresentAndClick(MLWalletHomePage.objWithdrawCash,getTextVal(MLWalletHomePage.objWithdrawCash,"Option"));
		if(verifyElementPresent(MLWalletCashOutPage.objCashOutPage,getTextVal(MLWalletCashOutPage.objCashOutPage,"Page"))){
			logger.info("HD_TC_07, After clicking on Withdraw cash from Hamburger menu of Home Page, Application Navigated directly to CashOut Page is validated");
			ExtentReporter.extentLoggerPass("HD_TC_07", "HD_TC_07, After clicking on Withdraw cash from Hamburger menu of Home Page, Application Navigated directly to CashOut Page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void mlWalletHomePageTopUpMLWallet_HD_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page Top Up ML Wallet");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
		verifyElementPresentAndClick(MLWalletHomePage.objTopUpMLWallet,getTextVal(MLWalletHomePage.objTopUpMLWallet,"Option"));
		if(verifyElementPresent(MLWalletCashInViaBranch.objBranchCashIn,getTextVal(MLWalletCashInViaBranch.objBranchCashIn,"Page"))){
			logger.info("HD_TC_08, After clicking on Top Up ML Wallet from Hamburger menu of Home Page, Application Navigated directly to CashIn ML Branch Page is validated");
			ExtentReporter.extentLoggerPass("HD_TC_08", "HD_TC_08, After clicking on Top Up ML Wallet from Hamburger menu of Home Page, Application Navigated directly to CashIn ML Branch Page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageShopHD_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page Shop");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objShop,getTextVal(MLWalletHomePage.objShop,"Option"));
		waitTime(10000);
		if(verifyElementPresent(MLWalletShopItemsPage.objMLShopPage,getTextVal(MLWalletShopItemsPage.objMLShopPage,"Page"))){
			logger.info("HD_TC_09, After clicking on Shop from Hamburger menu of Home Page, Application Navigated directly ML Shop Page is validated");
			ExtentReporter.extentLoggerPass("HD_TC_09", "HD_TC_09, After clicking on Shop from Hamburger menu of Home Page, Application Navigated directly to ML Shop Page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page Kwarta Padala Rates Validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objKwartaPadalaRatesBtn,"Kwarta Padala Rates");
		if (verifyElementPresent(MLWalletHomePage.objKwartaPadalaRates,"Kwarta Padala Rates")) {
			List<WebElement> values = findElements(MLWalletHomePage.objKwartaPadalaRates);
			for (int i = 0; i < values.size(); i++) {
				if (i % 2 != 0) {
					String sRates = values.get(i).getText();
					logger.info("Rates : " + sRates + " is displayed");
					ExtentReporter.extentLogger(" ", "Rates : " + sRates + " is displayed");
				}
				if (i % 2 == 0) {
					String sAmountRange = values.get(i).getText();
					logger.info("Amount Range : " + sAmountRange + " is displayed");
					ExtentReporter.extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
				}
			}
			logger.info("HD_TC_10, ML Wallet Home Page Kwarta Padala Rates validated");
			ExtentReporter.extentLoggerPass("HD_TC_10", "HD_TC_10, ML Wallet Home Page Kwarta Padala Rates validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objViewTier,getTextVal(MLWalletHomePage.objViewTier,"Option"));
		explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,5);
		if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
			verificationTierPerksPageValidation();
			logger.info("HD_TC_11, ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu Validated");
			ExtentReporter.extentLoggerPass("HD_TC_11", "HD_TC_11, ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletSupportPageNavigation_HD_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Support page Navigation From Home Page Hamburger Menu");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objSupport,getTextVal(MLWalletHomePage.objSupport,"Option"));
		explicitWaitVisible(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,20);
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,getTextVal(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,"Page"))){
			logger.info("HD_TC_12, After clicking on support Button on Hamburger menu, Application Navigated to Trouble Signing In Page");
			ExtentReporter.extentLoggerPass("HD_TC_12", "HD_TC_12, After clicking on support Button on Hamburger menu, Application Navigated to Trouble Signing In Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletAboutPageNavigation_HD_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet About page Navigation From Home Page Hamburger Menu");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu,"Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objAbout,getTextVal(MLWalletHomePage.objAbout,"Option"));
		explicitWaitVisible(MLWalletHomePage.objAbout,20);
		if(verifyElementPresent(MLWalletHomePage.objAbout,getTextVal(MLWalletHomePage.objAbout,"Page"))){
			logger.info("HD_TC_13, After clicking on About Button on Hamburger menu, Application Navigated to About Page");
			ExtentReporter.extentLoggerPass("HD_TC_13", "HD_TC_13, After clicking on About Button on Hamburger menu, Application Navigated to About Page");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Semi-verified User");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objViewTier, getTextVal(MLWalletHomePage.objViewTier, "Option"));
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			logger.info("HD_TC_14, ML Wallet Verification Tier Perks Validation By logging as Semi-verified User Validated");
			ExtentReporter.extentLoggerPass("HD_TC_14", "HD_TC_14, ML Wallet Verification Tier Perks Validation By logging as Semi-verified User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Fully-verified User");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenuForFullyVerifiedTier, "Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objViewTier, getTextVal(MLWalletHomePage.objViewTier, "Option"));
		explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,5);
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			scrollToFirstHorizontalScrollableElement("Branch Verified Tier Perks");
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			scrollToFirstHorizontalScrollableElement("Fully Verified Tier Perks");
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			logger.info("HD_TC_15, ML Wallet Verification Tier Perks Validation By logging as Fully-verified User Validated");
			ExtentReporter.extentLoggerPass("HD_TC_15", "HD_TC_15, ML Wallet Verification Tier Perks Validation By logging as Fully-verified User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Branch-verified User");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objViewTier, getTextVal(MLWalletHomePage.objViewTier, "Option"));
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			logger.info("HD_TC_17, ML Wallet Verification Tier Perks Validation By logging as Branch-verified User Validated");
			ExtentReporter.extentLoggerPass("HD_TC_17", "HD_TC_17, ML Wallet Verification Tier Perks Validation By logging as Branch-verified User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Buyer Tier User");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objViewTier, getTextVal(MLWalletHomePage.objViewTier, "Option"));
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			logger.info("HD_TC_18, ML Wallet Verification Tier Perks Validation By logging as Buyer Tier User Validated");
			ExtentReporter.extentLoggerPass("HD_TC_18", "HD_TC_18, ML Wallet Verification Tier Perks Validation By logging as Buyer Tier  User Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Hamburger Menu Transaction button validation");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
		if(verifyElementPresent(MLWalletHomePage.objWithdrawCash,getTextVal(MLWalletHomePage.objWithdrawCash,"Option"))){
			verifyElementPresent(MLWalletHomePage.objTopUpMLWallet,getTextVal(MLWalletHomePage.objTopUpMLWallet,"Option"));
			logger.info("HD_TC_19, After clicking on Transactions option, Withdraw Cash and TopUp Ml Wallet options are displayed");
			ExtentReporter.extentLoggerPass("HD_TC_19", "HD_TC_19, After clicking on Transactions option, Withdraw Cash and TopUp Ml Wallet options are displayed");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageIIconValidationAsBranchVerifiedTierUser_HD_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page I Icon Validation as Branch verified User");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objIIcon,"i Icon");
		if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
			verificationTierPerksPageValidation();
			logger.info("HD_TC_20, ML Wallet Home Page I Icon Validated as Branch verified User");
			ExtentReporter.extentLoggerPass("HD_TC_20", "HD_TC_20, ML Wallet Home Page I Icon Validated as Branch verified User");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void mlWalletHomePageIIconValidationAsBuyerTierUser_HD_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page I Icon Validation as BuyerTier verified User");
		mlWalletLogin(prop.getproperty("Buyer_Tier"));
		verifyElementPresentAndClick(MLWalletHomePage.objIIcon, "i Icon");
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();
			logger.info("HD_TC_21,ML Wallet Home Page I Icon Validated as BuyerTier verified User");
			ExtentReporter.extentLoggerPass("HD_TC_21", "HD_TC_21, ML Wallet Home Page I Icon Validated as BuyerTier verified User");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page I Icon Validation as Semi verified Tier User");
		mlWalletLogin(prop.getproperty("Semi_Verified"));
		verifyElementPresentAndClick(MLWalletHomePage.objIIcon, "i Icon");
		if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
			verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
			verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
			verificationTierPerksPageValidation();

			logger.info("HD_TC_22, ML Wallet Home Page I Icon Validated as Semi verified Tier User");
			ExtentReporter.extentLoggerPass("HD_TC_22", "HD_TC_22, ML Wallet Home Page I Icon Validated as Semi verified Tier User");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("ML Wallet Home Page I Icon Validation as Fully verified Tier User");
		mlWalletLogin(prop.getproperty("Fully_verified"));
		if(verifyElementNotPresent(MLWalletHomePage.objIIcon,"i Icon",5)){
			logger.info("HD_TC_23, ML Wallet Home Page I Icon not displayed For Fully-verified Tier user");
			ExtentReporter.extentLoggerPass("HD_TC_23", "HD_TC_23, ML Wallet Home Page I Icon not displayed For Fully-verified Tier user");
			System.out.println("-----------------------------------------------------------");
		}
	}

//========================== Trouble signing In =====================================================================//


	public void troubleSigningInPageNavigationFromMpinScreen_TS_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Page Navigation From Mpin Screen");
		mlWalletLogin(prop.getproperty("Branch_Verified"));
		mlWalletLogout();
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,getTextVal(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,"Page"))){
			logger.info("TS_TC_01, Trouble Signing In Page Navigation From Mpin Screen Validated");
			ExtentReporter.extentLoggerPass("TS_TC_01", "TS_TC_01, Trouble Signing In Page Navigation From Mpin Screen Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void troubleSigningInPageNavigationFromLoginScreen_TS_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Page Navigation From Login Screen");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,getTextVal(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,"Page"))){
			logger.info("TS_TC_10, Trouble Signing In Page Navigation From Login Screen Validated");
			ExtentReporter.extentLoggerPass("TS_TC_10", "TS_TC_10, Trouble Signing In Page Navigation From Login Screen Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void troubleSigningInPageBackArrowBtnFunctionality_TS_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Page Back Arrow Button Functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningInBackArrowBtn,"Back Arrow Button");
		if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"Button"))){
			logger.info("TS_TC_11, Trouble Signing In Page Back Arrow Button Functionality Validated");
			ExtentReporter.extentLoggerPass("TS_TC_11", "TS_TC_11, Trouble Signing In Page Back Arrow Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void troubleSigningInClearFormFunctionality_TS_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Clear form functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
//		String sFulName = getText(MLWalletTroubleSigningInPage.objFullNameField);
		type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
//		String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
		Swipe("UP",1);
		type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
//		String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
		Swipe("UP",2);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"))){
			verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopupMsg,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopupMsg,"Popup Msg"));
			verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
			verifyElementPresent(MLWalletTroubleSigningInPage.objCancelBtn,getTextVal(MLWalletTroubleSigningInPage.objCancelBtn,"Button"));
			logger.info("TS_TC_12, Trouble Signing In Clear form functionality Validated");
			ExtentReporter.extentLoggerPass("TS_TC_12", "TS_TC_12, Trouble Signing In Clear form functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void troubleSigningInClearFormButtonOnClearFormPopupFunctionality_TS_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Clear form Btn on Clear form popup functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
		String sFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
		type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
		String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
		Swipe("UP",1);
		type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
		String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
		Swipe("UP",2);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
		verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
		Swipe("DOWN",3);
		String sActualFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
		String sActualRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
		Swipe("UP",1);
		String sActualMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
		assertNotEquals(sActualFullName,sFullName);
		assertNotEquals(sActualRegisteredEmail,sRegisteredEmail);
		assertNotEquals(sActualMobileNumber,sMobileNumber);
		logger.info("TS_TC_13, Trouble Signing In Clear form Btn on Clear form popup functionality Validated");
		ExtentReporter.extentLoggerPass("TS_TC_13", "TS_TC_13, Trouble Signing In Clear form Btn on Clear form popup functionality Validated");
		System.out.println("-----------------------------------------------------------");
	}


	public void troubleSigningInCancelButtonOnClearFormPopupFunctionality_TS_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Cancel form Btn on Clear form popup functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
		String sFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
		type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
		String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
		Swipe("UP",1);
		type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
		String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
		Swipe("UP",2);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
		verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objCancelBtn,getTextVal(MLWalletTroubleSigningInPage.objCancelBtn,"Button"));
		Swipe("DOWN",3);
		String sActualFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
		String sActualRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
		Swipe("UP",1);
		String sActualMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
		assertionValidation(sActualFullName,sFullName);
		assertionValidation(sActualRegisteredEmail,sRegisteredEmail);
		assertionValidation(sActualMobileNumber,sMobileNumber);
		logger.info("TS_TC_14, Trouble Signing In Cancel Btn on Clear form popup functionality Validated");
		ExtentReporter.extentLoggerPass("TS_TC_14", "TS_TC_14, Trouble Signing In Cancel Btn on Clear form popup functionality Validated");
		System.out.println("-----------------------------------------------------------");
	}


	public void troubleSigningInEmptyFullNameFunctionality_TS_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Empty Full Name Functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		Swipe("UP",4);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
			String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
			String sExceptedErrorMsg = "This is a required question";
			assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
			logger.info("TS_TC_15, Trouble Signing In Empty Full Name Functionality Validated");
			ExtentReporter.extentLoggerPass("TS_TC_15", "TS_TC_15, Trouble Signing In Empty Full Name Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void troubleSigningInEmptyRegisteredEmailFunctionality_TS_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Empty Registered Email Functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
		Swipe("UP",4);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
			String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
			String sExceptedErrorMsg = "This is a required question";
			assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
			logger.info("TS_TC_16, Trouble Signing In Empty Registered Email Functionality Validated");
			ExtentReporter.extentLoggerPass("TS_TC_16", "TS_TC_16, Trouble Signing In Empty Registered Email Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void troubleSigningInEmptyRegisteredMobileNumberFunctionality_TS_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Trouble Signing In Empty Registered Mobile Number Functionality");
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
		type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
		type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
		Swipe("UP",4);
		verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
		if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
			String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
			String sExceptedErrorMsg = "This is a required question";
			assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
			logger.info("TS_TC_17, Trouble Signing In Empty Mobile Number Functionality Validated");
			ExtentReporter.extentLoggerPass("TS_TC_17", "TS_TC_17, Trouble Signing In Empty Mobile Number Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


//==================================== Registration =====================================================//


	public void registrationPageNavigation() throws Exception {
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		waitTime(6000);
		type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Registration_MobileNumber"), "Mobile Number Text Field");
		click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
		enterOTP(prop.getproperty("Valid_OTP"));
	}

	public void registrationInputName() throws Exception {
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
		type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
		type(MLWalletRegistration.objLastName,prop.getproperty("Last_Name"),"Last Name Input Field");
	}

	public void selectDate() throws Exception {
		verifyElementPresentAndClick(MLWalletRegistration.objBirthDate,"Birth Date Input Field");
		verifyElementPresentAndClick(MLWalletRegistration.objDatePickerYear,getTextVal(MLWalletRegistration.objDatePickerYear,"Year Section"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String year= dateFormat.format(date);
		int selectYear = Integer.parseInt(year)-18;
		verticalSwipeByPercentages(0.4,0.8,0.5);
		verticalSwipeByPercentages(0.4,0.8,0.5);
		verifyElementPresentAndClick(MLWalletRegistration.objYearSelector(Integer.toString(selectYear)),"Selected Year");
		verifyElementPresentAndClick(MLWalletRegistration.objOkBtn,getTextVal(MLWalletRegistration.objOkBtn,"Button"));
	}

	public void emailAndPlaceOfBirth() throws Exception {
		type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
		type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
		type(MLWalletRegistration.objPlaceOfBirth,prop.getproperty("Valid_PlaceOfBirth"),"Place of Birth Field");
	}

	public void swipeAndConfirm() throws Exception {
		swipeDownUntilElementVisible("Confirm");
		verifyElementPresentAndClick(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
	}

	public void selectNationality() throws Exception {
		verifyElementPresentAndClick(MLWalletRegistration.objNationality,"Nationality Field");
		waitTime(5000);
		type(MLWalletRegistration.objNationalitySearchField,"filipino","Nationality search field");
		verifyElementPresentAndClick(MLWalletRegistration.objFilipino,getTextVal(MLWalletRegistration.objFilipino,"Nationality"));
	}

	public void civilAndGenderSelection() throws Exception {
		verifyElementPresentAndClick(MLWalletRegistration.objCivilStatus,"Civil Status");
		verifyElementPresentAndClick(MLWalletRegistration.objSingleCivilStatus,getTextVal(MLWalletRegistration.objSingleCivilStatus,"Civil Status"));
		verifyElementPresentAndClick(MLWalletRegistration.objGender,"Gender Field");
		verifyElementPresentAndClick(MLWalletRegistration.objMaleGender,getTextVal(MLWalletRegistration.objMaleGender,"Gender"));
	}

	public void registrationAddressPageNavigation() throws Exception {
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		emailAndPlaceOfBirth();
		selectNationality();
		swipeDownUntilElementVisible("Confirm");
		civilAndGenderSelection();
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
	}


	public void registrationInvalidMobileNumber_RG_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Registration With Invalid Number");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		waitTime(10000);
		type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
		click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if (verifyElementPresent(MLWalletLoginPage.objInvalidMobNumberTxt, getTextVal(MLWalletLoginPage.objInvalidMobNumberTxt, "Error Message"))) {
			logger.info("RG_TC_02, Mobile number is Invalid Error Message is Displayed");
			ExtentReporter.extentLoggerPass("RG_TC_02", "RG_TC_02, Mobile number is Invalid Error Message is Displayed");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationFirstNameInputFieldValidation_RG_TC_04() throws Exception {
		ExtentReporter.HeaderChildNode("Registration First Name Input Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("Invalid_First_Name"),"First Name Input Field");
		swipeAndConfirm();
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletRegistration.objFirstNameErrorMsg,getTextVal(MLWalletRegistration.objFirstNameErrorMsg,"Error Message"))){
			String sInvalidFirstNameErrorMsg = getText(MLWalletRegistration.objFirstNameErrorMsg);
			String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
			assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
			logger.info("RG_TC_04, Registration First Name Input Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_04", "RG_TC_04, Registration First Name Input Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationMiddleNameInputFieldValidation_RG_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Middle Name Input Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
		type(MLWalletRegistration.objMiddleName,prop.getproperty("Invalid_Middle_Name"),"Middle Name Input Field");
		swipeAndConfirm();
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletRegistration.objMiddleNameErrorMsg,getTextVal(MLWalletRegistration.objMiddleNameErrorMsg,"Error Message"))){
			String sInvalidMiddleNameErrorMsg = getText(MLWalletRegistration.objMiddleNameErrorMsg);
			String sExceptedMiddleNameErrorMsg = "Middle name must only contain letters and spaces";
			assertionValidation(sInvalidMiddleNameErrorMsg,sExceptedMiddleNameErrorMsg);
			logger.info("RG_TC_05, Registration Middle Name Input Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_05", "RG_TC_05, Registration Middle Name Input Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationLastNameInputFieldValidation_RG_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Last Name Input Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
		type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
		type(MLWalletRegistration.objLastName,prop.getproperty("Invalid_Last_Name"),"Last Name Input Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objLastNameErrorMsg,getTextVal(MLWalletRegistration.objLastNameErrorMsg,"Error Message"))){
			String sInvalidLastNameErrorMsg = getText(MLWalletRegistration.objLastNameErrorMsg);
			String sExceptedLastNameErrorMsg = "Last name must only contain letters and spaces";
			assertionValidation(sInvalidLastNameErrorMsg,sExceptedLastNameErrorMsg);
			logger.info("RG_TC_06, Registration Last Name Input Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_06", "RG_TC_06, Registration Last Name Input Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationInvalidBirthDate_RG_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Invalid Birth Date");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		verifyElementPresentAndClick(MLWalletRegistration.objBirthDate,"Birth Date Input Field");
		verifyElementPresentAndClick(MLWalletRegistration.objOkBtn,getTextVal(MLWalletRegistration.objOkBtn,"Button"));
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objBirthDateErrorMsg,getTextVal(MLWalletRegistration.objBirthDateErrorMsg,"Error Message"))){
			String sInvalidBirthDateNameErrorMsg = getText(MLWalletRegistration.objBirthDateErrorMsg);
			String sExceptedBirthDateNameErrorMsg = "You must be 18 years old and above to register";
			assertionValidation(sInvalidBirthDateNameErrorMsg,sExceptedBirthDateNameErrorMsg);
			logger.info("RG_TC_07, Registration Invalid Birth Date Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_07", "RG_TC_07, Registration Invalid Birth Date Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationInvalidEmailAddress_RG_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Invalid Email Address");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		type(MLWalletRegistration.objEmailAddress,prop.getproperty("Invalid_Email_Address"),"Email Address Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objEmailAddressErrorMsg,"Error Message"))){
			String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objEmailAddressErrorMsg);
			String sExceptedEmailAddressNameErrorMsg = "Email address is invalid";
			assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
			logger.info("RG_TC_08, Registration Invalid Email Address Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_08", "RG_TC_08, Registration Invalid Email Address Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationInvalidReEnterEmailAddress_RG_TC_09() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Invalid Re-Enter Email Address");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
		type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("eMailAddress"), "Re-Enter Email Address Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objReEnterEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objReEnterEmailAddressErrorMsg,"Error Message"))){
			String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objReEnterEmailAddressErrorMsg);
			String sExceptedEmailAddressNameErrorMsg = "Email address did not match.";
			assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
			logger.info("RG_TC_09, Registration Invalid Re-Enter Email Address Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_09", "RG_TC_09, Registration Invalid Re-Enter Email Address Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationInvalidPlaceOfBirthValidation_RG_TC_10() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Invalid Place of Birth");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
		type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
		type(MLWalletRegistration.objPlaceOfBirth,prop.getproperty("Invalid_PlaceOfBirth"),"Place of Birth Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objPlaceOfBirthErrorMsg,getTextVal(MLWalletRegistration.objPlaceOfBirthErrorMsg,"Error Message"))){
			String sInvalidPlaceOfBirthErrorMsg = getText(MLWalletRegistration.objPlaceOfBirthErrorMsg);
			String sExceptedPlaceOfBirthErrorMsg = "Place of Birth must only contain letters and spaces";
			assertionValidation(sInvalidPlaceOfBirthErrorMsg,sExceptedPlaceOfBirthErrorMsg);
			logger.info("RG_TC_10, Registration Invalid Place of Birth Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_10", "RG_TC_10, Registration Invalid Place of Birth Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyNationalityFieldValidation_RG_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Nationality Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		emailAndPlaceOfBirth();
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objNationalityErrorMsg,getTextVal(MLWalletRegistration.objNationalityErrorMsg,"Error Message"))){
			String sInvalidNationalityErrorMsg = getText(MLWalletRegistration.objNationalityErrorMsg);
			String sExceptedNationalityErrorMsg = "Nationality is required";
			assertionValidation(sInvalidNationalityErrorMsg,sExceptedNationalityErrorMsg);
			logger.info("RG_TC_11, Registration Invalid Empty Nationality Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_11", "RG_TC_11, Registration Empty Nationality Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyCivilStatusFieldValidation_RG_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Civil status Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		emailAndPlaceOfBirth();
		selectNationality();
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objCivilStatusErrorMsg,getTextVal(MLWalletRegistration.objCivilStatusErrorMsg,"Error Message"))){
			String sInvalidCivilStatusErrorMsg = getText(MLWalletRegistration.objCivilStatusErrorMsg);
			String sExceptedCivilStatusErrorMsg = "Civil Status is required";
			assertionValidation(sInvalidCivilStatusErrorMsg,sExceptedCivilStatusErrorMsg);
			logger.info("RG_TC_12, Registration Empty Civil status Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_12", "RG_TC_12, Registration Empty Civil status Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationEmptyGenderFieldValidation_RG_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Gender Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		emailAndPlaceOfBirth();
		selectNationality();
		swipeDownUntilElementVisible("Confirm");
		verifyElementPresentAndClick(MLWalletRegistration.objCivilStatus,"Civil Status");
		verifyElementPresentAndClick(MLWalletRegistration.objSingleCivilStatus,getTextVal(MLWalletRegistration.objSingleCivilStatus,"Civil Status"));
		swipeAndConfirm();
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if(verifyElementPresent(MLWalletRegistration.objGenderErrorMsg,getTextVal(MLWalletRegistration.objGenderErrorMsg,"Error Message"))){
			String sActualGenderErrorMsg = getText(MLWalletRegistration.objGenderErrorMsg);
			String sExceptedGenderErrorMsg = "Gender is required";
			assertionValidation(sActualGenderErrorMsg,sExceptedGenderErrorMsg);
			logger.info("RG_TC_13, Registration Empty Gender Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_13", "RG_TC_13, Registration Empty Gender Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyProvinceFieldValidation_RG_TC_14() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Province Field Validation");
		registrationAddressPageNavigation();
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if(verifyElementPresent(MLWalletRegistration.ObjProvinceErrorMsg,getTextVal(MLWalletRegistration.ObjProvinceErrorMsg,"Error Message"))){
			String sInvalidProvinceNameErrorMsg = getText(MLWalletRegistration.ObjProvinceErrorMsg);
			String sExceptedProvinceNameErrorMsg = "Province / State is required";
			assertionValidation(sInvalidProvinceNameErrorMsg,sExceptedProvinceNameErrorMsg);
			logger.info("RG_TC_14, Registration Empty Province Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_14", "RG_TC_14, Registration Empty Province Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationEmptyCityFieldValidation_RG_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty City Field Validation");
		registrationAddressPageNavigation();
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
		type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
		verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if(verifyElementPresent(MLWalletRegistration.objCityErrorMsg,getTextVal(MLWalletRegistration.objCityErrorMsg,"Error Message"))){
			String sInvalidCityNameErrorMsg = getText(MLWalletRegistration.objCityErrorMsg);
			String sExceptedCityNameErrorMsg = "City / Town is required";
			assertionValidation(sInvalidCityNameErrorMsg,sExceptedCityNameErrorMsg);
			logger.info("RG_TC_15, Registration Empty City Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_15", "RG_TC_15, Registration Empty City Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyHouseNoFieldValidation_RG_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty House No Field Validation");
		registrationAddressPageNavigation();
		explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
		type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
		verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
		verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
		verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if(verifyElementPresent(MLWalletRegistration.objHouseNoErrorMsg,getTextVal(MLWalletRegistration.objHouseNoErrorMsg,"Error Message"))){
			String sInvalidHouseNoNameErrorMsg = getText(MLWalletRegistration.objHouseNoErrorMsg);
			String sExceptedHouseNoNameErrorMsg = "Unit, House No., St. is required";
			assertionValidation(sInvalidHouseNoNameErrorMsg,sExceptedHouseNoNameErrorMsg);
			logger.info("RG_TC_16, Registration Empty House No Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_16", "RG_TC_16, Registration Empty House No Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyFirstNameFieldValidation_RG_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty First Name Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		swipeAndConfirm();
		Swipe("DOWN",2);
		if (verifyElementPresent(MLWalletRegistration.objFirstNameErrorMsg, getTextVal(MLWalletRegistration.objFirstNameErrorMsg, "Error Message"))) {
			String sFirstNameErrorMsg = getText(MLWalletRegistration.objFirstNameErrorMsg);
			String sExpectedMsg = "First name is required";
			assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
			logger.info("RG_TC_18, Registration Empty First Name Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_18", "RG_TC_18, Registration Empty First Name Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationEmptyMiddleNameFieldValidation_RG_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Middle Name Input Validation");
		registrationPageNavigation();
		waitTime(6000);
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
		swipeAndConfirm();
		Swipe("DOWN",2);
		if(verifyElementPresent(MLWalletRegistration.objMiddleNameErrorMsg,getTextVal(MLWalletRegistration.objMiddleNameErrorMsg,"Error Message"))){
			String sInvalidMiddleNameErrorMsg = getText(MLWalletRegistration.objMiddleNameErrorMsg);
			String sExceptedMiddleNameErrorMsg = "Middle name should be at least 2 characters long";
			assertionValidation(sInvalidMiddleNameErrorMsg,sExceptedMiddleNameErrorMsg);
			logger.info("RG_TC_19, Registration Empty Middle Name Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_19", "RG_TC_19, Registration Empty Middle Name Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationEmptyLastNameFieldValidation_RG_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Last Name Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
		verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
		type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
		type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objLastNameErrorMsg,getTextVal(MLWalletRegistration.objLastNameErrorMsg,"Error Message"))){
			String sInvalidLastNameErrorMsg = getText(MLWalletRegistration.objLastNameErrorMsg);
			String sExceptedLastNameErrorMsg = "Last name is required";
			assertionValidation(sInvalidLastNameErrorMsg,sExceptedLastNameErrorMsg);
			logger.info("RG_TC_20, Registration Empty Last Name Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_20", "RG_TC_06, Registration Empty Last Name Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyEmailAddress_RG_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Email Address Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objEmailAddressErrorMsg,"Error Message"))){
			String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objEmailAddressErrorMsg);
			String sExceptedEmailAddressNameErrorMsg = "Email address is required";
			assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
			logger.info("RG_TC_22, Registration Empty Email Address Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_22", "RG_TC_22, Registration Empty Email Address Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationEmptyPlaceOfBirthValidation_RG_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Empty Place of Birth Field Validation");
		registrationPageNavigation();
		waitTime(5000);
		registrationInputName();
		selectDate();
		type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
		type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
		swipeAndConfirm();
		if(verifyElementPresent(MLWalletRegistration.objPlaceOfBirthErrorMsg,getTextVal(MLWalletRegistration.objPlaceOfBirthErrorMsg,"Error Message"))){
			String sInvalidPlaceOfBirthNameErrorMsg = getText(MLWalletRegistration.objPlaceOfBirthErrorMsg);
			String sExceptedPlaceOfBirthNameErrorMsg = "Place of Birth is required";
			assertionValidation(sInvalidPlaceOfBirthNameErrorMsg,sExceptedPlaceOfBirthNameErrorMsg);
			logger.info("RG_TC_23, Registration Empty Place of Birth Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_23", "RG_TC_23, Registration Empty Place of Birth Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationInvalidHouseNoFieldValidation_RG_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Invalid House No Field Validation");
		registrationAddressPageNavigation();
		explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
		type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
		verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
		verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
		verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
		type(MLWalletRegistration.objHouseNo,prop.getproperty("Invalid_HouseNo"),"House No Field");
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if(verifyElementPresent(MLWalletRegistration.objHouseNoErrorMsg,getTextVal(MLWalletRegistration.objHouseNoErrorMsg,"Error Message"))){
			String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objHouseNoErrorMsg);
			String sExceptedEmailAddressNameErrorMsg = "Address must contain letters and numbers only";
			assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
			logger.info("RG_TC_24, Registration Invalid House No Field Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_24", "RG_TC_24, Registration Invalid House No Field Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}
	
	public void registrationCreatingAccountWithExistingMLWalletAccount_RG_TC_25() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Creating Account With Existing ML Wallet Account");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		explicitWaitVisibility(MLWalletRegistration.objMobileNumberField, 10);
		type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletRegistration.objExistingAccErrorMsg,getTextVal(MLWalletRegistration.objExistingAccErrorMsg,"Error message"))) {
			String sActualErrorMsg = getText(MLWalletRegistration.objExistingAccErrorMsg);
			String sExpectedErrorMsg = "As an existing M.Lhuillier Customer, you can directly log-in using this mobile number.";
			assertionValidation(sActualErrorMsg,sExpectedErrorMsg);
			verifyElementPresent(MLWalletRegistration.objGOBackToLoginScreenBtn,getTextVal(MLWalletRegistration.objGOBackToLoginScreenBtn,"Button"));
			logger.info("RG_TC_25, Registration Creating Account With Existing ML Wallet Account Error message Validated");
			ExtentReporter.extentLoggerPass("RG_TC_25", "RG_TC_25, Registration Creating Account With Existing ML Wallet Account Error message Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationNumberPageUIValidation_RG_TC_27() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Number Page UI Validation");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		if(verifyElementPresent(MLWalletRegistration.objRegistration,getTextVal(MLWalletRegistration.objRegistration,"page"))){
			verifyElementPresent(MLWalletRegistration.objMobileNumberField,"Mobile Number Input Field");
			verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
			logger.info("RG_TC_27, Registration Number Page UI Validated");
			ExtentReporter.extentLoggerPass("RG_TC_27", "RG_TC_27, Registration Number Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationOTPPageUIValidation_RG_TC_28() throws Exception {
		ExtentReporter.HeaderChildNode("Registration OTP Page UI Validation");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("RG_TC_28, Registration One Time Pin page UI Validated");
			ExtentReporter.extentLoggerPass("RG_TC_28", "RG_TC_28, Registration One Time Pin page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationPersonalInfoPageUIValidation_RG_TC_30() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Personal info Page UI Validation");
		registrationPageNavigation();
		waitTime(5000);
		if(verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"))){
			verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
			verifyElementPresent(MLWalletRegistration.objFirstName,"First Name Input Field");
			verifyElementPresent(MLWalletRegistration.objMiddleName,"Middle Name Input Field");
			verifyElementPresent(MLWalletRegistration.objLastName,"Last Name Input Field");
			verifyElementPresent(MLWalletRegistration.objBirthDate,"Birth date Input Field");
			verifyElementPresent(MLWalletRegistration.objEmailAddress,"Email Address Input Field");
			verifyElementPresent(MLWalletRegistration.objReEnterEmailAddress,"Re-Email Address Input Field");
			verifyElementPresent(MLWalletRegistration.objPlaceOfBirth,"Place of Birth Input Field");
			Swipe("UP",1);
			verifyElementPresent(MLWalletRegistration.objNationality,"Choose Nationality Field");
			verifyElementPresent(MLWalletRegistration.objCivilStatus,"Choose Civil Status Field");
			verifyElementPresent(MLWalletRegistration.objGender,"Choose Gender Field");
			verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
			logger.info("RG_TC_30, Registration Personal info Page UI Validated");
			ExtentReporter.extentLoggerPass("RG_TC_30", "RG_TC_30, Registration Personal info Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationNumberPageBackBtnValidation_RG_TC_31() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Number Page Back Arrow Button Validation");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		verifyElementPresentAndClick(MLWalletRegistration.objRegistrationBackBtn,"Registration Page Back Arrow Btn");
		if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"Button"))){
			logger.info("RG_TC_31, Registration Number Page Back Arrow Button Validated");
			ExtentReporter.extentLoggerPass("RG_TC_31", "RG_TC_31, Registration Number Page Back Arrow Button Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationOTPPageBackBtnValidation_RG_TC_32() throws Exception {
		ExtentReporter.HeaderChildNode("Registration OTP Page Back Arrow Button Validation");
		verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
		type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
		click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
		verifyElementPresentAndClick(MLWalletRegistration.objOTPPageBackBtn,"OTP Page Back arrow Btn");
		if(verifyElementPresent(MLWalletRegistration.objRegistration,getTextVal(MLWalletRegistration.objRegistration,"Page"))){
			logger.info("RG_TC_32, Registration OTP Page Back Arrow Button Validated");
			ExtentReporter.extentLoggerPass("RG_TC_32", "RG_TC_32, Registration OTP Page Back Arrow Button Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationPersonalInfoPageBackBtnValidationRG_TC_33() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Personal Info Page Back Btn Validation");
		registrationPageNavigation();
		waitTime(5000);
		waitTime(5000);
		verifyElementPresentAndClick(MLWalletRegistration.objRegistrationPersonalInfoBackBtn,"OTP Page Back arrow Btn");
		if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
			logger.info("RG_TC_33, Registration Personal Info Page Back Btn Validated");
			ExtentReporter.extentLoggerPass("RG_TC_33", "RG_TC_33, Registration Personal Info Page Back Btn Validated");
			System.out.println("-----------------------------------------------------------");
		}

	}

	public void registrationAddressPageUIValidation_RG_TC_34() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Address Page UI Validation");
		registrationAddressPageNavigation();
		if(verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"))){
			verifyElementPresent(MLWalletRegistration.objProvince,"Select State Or Province Field");
			verifyElementPresent(MLWalletRegistration.objCity,"Select City or Town");
			verifyElementPresent(MLWalletRegistration.objHouseNo,"Unit, House No., St.");
			verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
			logger.info("RG_TC_34, Registration Address Page UI Validated");
			ExtentReporter.extentLoggerPass("RG_TC_34", "RG_TC_34, Registration Address Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void registrationAddressPageBackBtnValidation_RG_TC_35() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Address Page Back btn Validation");
		registrationAddressPageNavigation();
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress, getTextVal(MLWalletRegistration.objRegistrationAddress, "Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objRegistrationAddressBackBtn,"Registration Address Back Btn");
		if(verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"))){
			logger.info("RG_TC_35, Registration Address Page Back btn Validated");
			ExtentReporter.extentLoggerPass("RG_TC_35", "RG_TC_35, Registration Address Page Back btn Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void registrationTermsAndConditionPageBackBtnValidation_RG_TC_37() throws Exception {
		ExtentReporter.HeaderChildNode("Registration Terms and Condition Page Back Btn Validation");
		registrationAddressPageNavigation();
		explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
		verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
		type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
		verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
		verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
		verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
		type(MLWalletRegistration.objHouseNo,prop.getproperty("HouseNo"),"House No Field");
		click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
		verifyElementPresent(MLWalletRegistration.objTermsAndCondition,getTextVal(MLWalletRegistration.objTermsAndCondition,"Page"));
		verifyElementPresentAndClick(MLWalletRegistration.objTermsAndConditionBackBtn,"Terms And Condition back Btn");
		if(verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"))){
			logger.info("RG_TC_37, Registration Terms and Condition Page Back Btn Validated");
			ExtentReporter.extentLoggerPass("RG_TC_37", "RG_TC_37, Registration Terms and Condition Page Back Btn Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


//==================================== Use QR ========================================================//


	public void useQRCodeNavigation(String sTier) throws Exception {
		mlWalletLogin(sTier);
		verifyElementPresentAndClick(MLWalletUseQR.objUseQR,getTextVal(MLWalletUseQR.objUseQR,"Icon"));
		verifyElementPresent(MLWalletUseQR.objUseQR,getTextVal(MLWalletUseQR.objUseQR,"Page"));
	}

	public void scanQR(String url){
		switchPlatformToWeb(url);
		waitTime(5000);
		closeWebBrowser();
		switchPlatformToAndroid();
	}


	public void useQRGenerateQRCode_QR_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Generate QR Code");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,getTextVal(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,"Option"));
		if(verifyElementPresent(MLWalletUseQR.objGenerateQR,getTextVal(MLWalletUseQR.objGenerateQR,"Page"))){
			verifyElementPresent(MLWalletUseQR.objQR,"Generated QR");
			verifyElementPresent(MLWalletUseQR.objReceiverName,getTextVal(MLWalletUseQR.objReceiverName,"Receiver Name"));
			verifyElementPresent(MLWalletUseQR.objReceiverMobileNumber,getTextVal(MLWalletUseQR.objReceiverMobileNumber,"Receiver Mobile Number"));
			logger.info("QR_TC_01, Use QR, Generate QR Code Validated");
			ExtentReporter.extentLoggerPass("QR_TC_01", "QR_TC_01, Use QR, Generate QR Code Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQRSendMoneyToAnyMLWalletUser_QR_TC_02() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To Any ML Wallet User");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			Thread.sleep(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInWalletToWallet);
				assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
				logger.info("QR_TC_02, Successfully Amount sent from Wallet to Wallet using QR code and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("QR_TC_02", "QR_TC_02, Successfully Amount sent from Wallet to Wallet using QR code and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void useQRInvalidQRCodeValidation_QR_TC_03() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Invalid QR Code Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Invalid_QR"));
		if (verifyElementPresent(MLWalletUseQR.objInvalidQRCodeMsg, getTextVal(MLWalletUseQR.objInvalidQRCodeMsg, "Error Msg"))) {
			String sActualErrorMsg = getText(MLWalletUseQR.objInvalidQRCodeMsg);
			String sExpectedErrorMsg = "Invalid QR code";
			assertionValidation(sActualErrorMsg,sExpectedErrorMsg);
			verifyElementPresent(MLWalletUseQR.objOKBtn,getTextVal(MLWalletUseQR.objOKBtn,"Button"));
			logger.info("QR_TC_03, Use QR, Invalid QR Code validated");
			ExtentReporter.extentLoggerPass("QR_TC_03", "QR_TC_03, Use QR, Invalid QR Code validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_WalletToWalletReceivedMoneyValidation_QR_TC_04(String Amount) throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Wallet to Wallet Receive Money Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet(Amount);
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
		String sReferenceNumberInSender = getText(SendTransferPage.objMLWalletReferenceNumber);
		String sAmountToSend = getText(SendTransferPage.objAmount);
		swipeDownUntilElementVisible("Back To Home");
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		mlWalletLogout();
		click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
		mlWalletLogin(prop.getproperty("New_Branch_Verified"));
		verifyElementPresent(MLWalletTransactionHistoryPage.objRecentTransaction,getTextVal(MLWalletTransactionHistoryPage.objRecentTransaction,"Header"));
		verifyElementPresent(MLWalletTransactionHistoryPage.objReceiveMoneyTab,getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab,"Transaction"));
		verifyElementPresent(MLWalletUseQR.objSuccess,getTextVal(MLWalletUseQR.objSuccess,"Status"));
		click(MLWalletTransactionHistoryPage.objReceiveMoneyTab,getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab,"Transaction"));
		String sReferenceNumberInReceiver = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
		assertionValidation(sReferenceNumberInReceiver, sReferenceNumberInSender);
		String sAmountReceived = getText(MLWalletTransactionHistoryPage.objAmountReceived);
		assertionValidation(sAmountReceived,sAmountToSend);
		logger.info("QR_TC_04, Use QR, Wallet to Wallet Receive Money validated");
		ExtentReporter.extentLoggerPass("QR_TC_04", "QR_TC_04, Use QR, Wallet to Wallet Receive Money validated");
		System.out.println("-----------------------------------------------------------");
	}


	public void useQRScanningReceiverQRCOde_QR_TC_05() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Scanning Receiver QR Code");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
			logger.info("QR_TC_05, Use QR, Scanning Receiver QR Code validated");
			ExtentReporter.extentLoggerPass("QR_TC_05", "QR_TC_05, Use QR, Scanning Receiver QR Code validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQRPageUIValidation_QR_TC_06() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR Page UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		if(verifyElementPresent(MLWalletUseQR.objUseQR,getTextVal(MLWalletUseQR.objUseQR,"Page"))){
			verifyElementPresent(MLWalletUseQR.objUseQROptions,getTextVal(MLWalletUseQR.objUseQROptions,"Header"));
			verifyElementPresent(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,getTextVal(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,"Option"));
			verifyElementPresent(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
			verifyElementPresent(MLWalletUseQR.objReadQRCodeToSignInToWeb,getTextVal(MLWalletUseQR.objReadQRCodeToSignInToWeb,"Option"));
			logger.info("QR_TC_06, Use QR Page UI validated");
			ExtentReporter.extentLoggerPass("QR_TC_06", "QR_TC_06, Use QR Page UI validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQRPageBackBtnValidation_QR_TC_07() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR Page Back Btn Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objUseQRBackBtn,"Use QR page Back Arrow Button");
		if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Header"))){
			logger.info("QR_TC_07, Use QR, After clicking On  Use QR Page Back Btn, App navigates to Home page is validated");
			ExtentReporter.extentLoggerPass("QR_TC_07", "QR_TC_07, Use QR, After clicking On  Use QR Page Back Btn, App navigates to Home page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_GenerateQRPageBackBtnValidation_QR_TC_08() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Generate QR page Back Arrow Btn Validation");
		useQRGenerateQRCode_QR_TC_01();
		verifyElementPresentAndClick(MLWalletUseQR.objGenerateQRBackBtn, "Generate QR Page Back Arrow button");
		if (verifyElementPresent(MLWalletUseQR.objUseQR, getTextVal(MLWalletUseQR.objUseQR, "Page"))) {
			logger.info("QR_TC_08, Use QR, After clicking On  Generate QR Page Back Btn, App navigates to Use QR page is validated");
			ExtentReporter.extentLoggerPass("QR_TC_08", "QR_TC_08, Use QR, After clicking On  Generate QR Page Back Btn, App navigates to Use QR page is validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_GenerateQRPageUIValidation_QR_TC_11() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Generate QR Page UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,getTextVal(MLWalletUseQR.objGenerateQRCodeToReceiveMoney,"Option"));
		if(verifyElementPresent(MLWalletUseQR.objGenerateQR,getTextVal(MLWalletUseQR.objGenerateQR,"Page"))){
			verifyElementPresent(MLWalletUseQR.objQR,"Generated QR");
			verifyElementPresent(MLWalletUseQR.objReceiverName,getTextVal(MLWalletUseQR.objReceiverName,"Receiver Name"));
			verifyElementPresent(MLWalletUseQR.objReceiverMobileNumber,getTextVal(MLWalletUseQR.objReceiverMobileNumber,"Receiver Mobile Number"));
			verifyElementPresent(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
			logger.info("QR_TC_11, Use QR, Generate QR Page UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_11", "QR_TC_11, Use QR, Generate QR Page UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_ToMLWalletScreenUIValidation_QR_TC_12() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, To ML Wallet Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
			verifyElementPresent(MLWalletUseQR.objReceiverNameInEnterAmount,getTextVal(MLWalletUseQR.objReceiverNameInEnterAmount,"Receiver Name"));
			verifyElementPresent(MLWalletUseQR.objReceiverMobileNumberInEnterAmount,getTextVal(MLWalletUseQR.objReceiverMobileNumberInEnterAmount,"Receiver Mobile Number"));
			verifyElementPresent(MLWalletUseQR.objAmountInputField,"Amount Entering Field");
			verifyElementPresent(MLWalletUseQR.objNextBtn,getTextVal(MLWalletUseQR.objNextBtn,"Button"));
			logger.info("QR_TC_12, Use QR, To ML Wallet Screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_12", "QR_TC_12, Use QR, To ML Wallet Screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_EmptyAmountFieldValidation_QR_TC_13() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Empty Amount Input Field Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"));
		click(MLWalletUseQR.objNextBtn,getTextVal(MLWalletUseQR.objNextBtn,"Button"));
		if (verifyElementPresent(MLWalletUseQR.objAmountRequiredErrorMsg, getTextVal(MLWalletUseQR.objAmountRequiredErrorMsg, "Error Message"))) {
			String sAmountRequiredErrorMsg = getText(MLWalletUseQR.objAmountRequiredErrorMsg);
			String sExceptedErrorMsg = "Amount is required";
			assertionValidation(sAmountRequiredErrorMsg, sExceptedErrorMsg);
			logger.info("QR_TC_13, Amount is required - Error message is Validated");
			ExtentReporter.extentLoggerPass("QR_TC_13", "QR_TC_13, Amount is required - Error message is Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_ConfirmDetailsScreenUIValidation_QR_TC_14(String sAmount) throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Confirm Details Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		type(MLWalletUseQR.objAmountInputField,sAmount,"Amount Input Field");
		click(MLWalletUseQR.objNextBtn, getTextVal(MLWalletUseQR.objNextBtn, "Button"));
		click(MLWalletUseQR.objMLWalletBalance, getTextVal(MLWalletUseQR.objMLWalletBalance, "Button"));
		if (verifyElementPresent(MLWalletUseQR.objConfirmDetails, getTextVal(MLWalletUseQR.objConfirmDetails, "Page"))) {
			verifyElementPresent(MLWalletUseQR.objReceiverNameInConfirmDetails, getTextVal(MLWalletUseQR.objReceiverNameInConfirmDetails, "Receiver Name"));
			verifyElementPresent(MLWalletUseQR.objReceiverMobileNo, getTextVal(MLWalletUseQR.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletUseQR.objPaymentMethod, getTextVal(MLWalletUseQR.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletUseQR.objAmount, getTextVal(MLWalletUseQR.objAmount, "Amount"));
			verifyElementPresent(MLWalletUseQR.objServiceFee, getTextVal(MLWalletUseQR.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletUseQR.objTotalAmount, getTextVal(MLWalletUseQR.objTotalAmount, "Total Amount"));
			verifyElementPresent(MLWalletUseQR.objCancelTransaction, getTextVal(MLWalletUseQR.objCancelTransaction, "Button"));
			Swipe("UP", 1);
			verifyElementPresent(MLWalletUseQR.objSendPHPBtn, getTextVal(MLWalletUseQR.objSendPHPBtn, "Button"));
			logger.info("QR_TC_14, Use QR, Confirm Details Screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_14", "QR_TC_14, Use QR, Confirm Details Screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_OneTimePinScreenUIValidation_QR_TC_15() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, one Time Pin Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
		if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
//			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
			logger.info("QR_TC_15,Use QR, One Time Pin screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_15", "QR_TC_15,Use QR, One Time Pin screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_SendMoneyToMlWalletSuccessScreenUIValidation_QR_TC_16() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Success Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney, getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney, "Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
			verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
			verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
			Swipe("UP", 2);
			verifyElementPresent(SendTransferPage.objSaveToMyFavorite, getTextVal(SendTransferPage.objSaveToMyFavorite, "Button"));
			verifyElementPresent(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			verifyElementPresent(SendTransferPage.objNewTransaction, getTextVal(SendTransferPage.objNewTransaction, "Button"));
			logger.info("QR_TC_16,Use QR, Send Money To ML Wallet Success Screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_16", "QR_TC_16, Use QR, Send Money To ML Wallet Success Screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_RecentTransactionInHomeScreenUIValidation_QR_TC_17() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Recent Transaction In Home Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		swipeDownUntilElementVisible("Back To Home");
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		Swipe("DOWN", 2);
		if(verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"))) {
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			verifyElementPresent(MLWalletUseQR.objSuccess,getTextVal(MLWalletUseQR.objSuccess,"Status"));
			logger.info("QR_TC_17, Use QR, Recent Transaction In Home Screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_17", "QR_TC_17, Use QR, Recent Transaction In Home Screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_TransactionDetailsScreenUIValidation_QR_TC_18() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Transaction Details Screen UI Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		swipeDownUntilElementVisible("Back To Home");
		click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
		Thread.sleep(3000);
		Swipe("DOWN", 2);
		Swipe("UP", 1);
		verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
		verifyElementPresentAndClick(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
		if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
			verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
			verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
			logger.info("QR_TC_18, Use QR, Transaction Details Screen UI Validated");
			ExtentReporter.extentLoggerPass("QR_TC_18", "QR_TC_18, Use QR, Transaction Details Screen UI Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_CameraPermissionPopupValidation_QR_TC_19() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Camera Permission Popup Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		if(verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"))){
			verifyElementPresent(MLWalletUseQR.objWhileUsingApp,getTextVal(MLWalletUseQR.objWhileUsingApp,"Button"));
			verifyElementPresent(MLWalletUseQR.objOnlyThisTime,getTextVal(MLWalletUseQR.objOnlyThisTime,"Button"));
			verifyElementPresent(MLWalletUseQR.objDenyButton,getTextVal(MLWalletUseQR.objDenyButton,"Button"));
			logger.info("QR_TC_19, Use QR, Camera Permission Popup Validated");
			ExtentReporter.extentLoggerPass("QR_TC_19", "QR_TC_19, Use QR, Camera Permission Popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_CameraPermissionPopupDenyButtonFunctionality_QR_TC_20() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Camera Permission Popup Deny Button Functionality");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletUseQR.objDenyButton,getTextVal(MLWalletUseQR.objDenyButton,"Button"));
		if (verifyElementPresent(MLWalletUseQR.objCameraNotAuthorized, getTextVal(MLWalletUseQR.objCameraNotAuthorized, "Message"))) {
			String sActualMsg = getText(MLWalletUseQR.objCameraNotAuthorized);
			String sExceptedMsg = "Camera not authorized";
			assertionValidation(sActualMsg,sExceptedMsg);
			logger.info("QR_TC_20, Use QR, Camera Permission Popup Deny Button Functionality Validated");
			ExtentReporter.extentLoggerPass("QR_TC_20", "QR_TC_20, Use QR, Camera Permission Popup Deny Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_CameraPermissionPopupOnlyThisTimeButtonFunctionality_QR_TC_21() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Camera Permission Popup Only This Time Button Functionality");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletUseQR.objOnlyThisTime,getTextVal(MLWalletUseQR.objOnlyThisTime,"Button"));
		if(verifyElementPresent(MLWalletUseQR.objScanQR,getTextVal(MLWalletUseQR.objScanQR,"Page"))){
			verifyElementPresent(MLWalletUseQR.objScanQRSuggestion,getTextVal(MLWalletUseQR.objScanQRSuggestion,"Message"));
			String sActualMsg = getText(MLWalletUseQR.objScanQRSuggestion);
			String sExpectedMsg = "Make sure that the QR code is within the frame";
			assertionValidation(sActualMsg,sExpectedMsg);
			logger.info("QR_TC_21, Use QR, Camera Permission Popup Only This Time Button Functionality Validated");
			ExtentReporter.extentLoggerPass("QR_TC_21", "QR_TC_21, Use QR, Camera Permission Popup Only This Time Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_CameraPermissionPopupWhileUsingTheAppButtonFunctionality_QR_TC_22() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Camera Permission Popup While Using The App Button Functionality");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletUseQR.objWhileUsingApp,getTextVal(MLWalletUseQR.objWhileUsingApp,"Button"));
		if(verifyElementPresent(MLWalletUseQR.objScanQR,getTextVal(MLWalletUseQR.objScanQR,"Page"))){
			verifyElementPresent(MLWalletUseQR.objScanQRSuggestion,getTextVal(MLWalletUseQR.objScanQRSuggestion,"Message"));
			String sActualMsg = getText(MLWalletUseQR.objScanQRSuggestion);
			String sExpectedMsg = "Make sure that the QR code is within the frame";
			assertionValidation(sActualMsg,sExpectedMsg);
			logger.info("QR_TC_22, Use QR, Camera Permission Popup While Using The App Button Functionality Validated");
			ExtentReporter.extentLoggerPass("QR_TC_22", "QR_TC_22, Use QR, Camera Permission Popup While Using The App Button Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_SendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_QR_TC_23() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Internet Interruption While Entering OTP Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		waitTime(15000);
		setWifiConnectionToONOFF("OFF");
		enterOTP(prop.getproperty("Valid_OTP"));
		if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
			internetConnectionError();
			logger.info("QR_TC_23, Use QR, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
			ExtentReporter.extentLoggerPass("QR_TC_23", "QR_TC_23, Use QR, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_ScanQRCodeAfterClickingOnDenyButton_QR_TC_24() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Scan QR Code After Clicking On Deny Button");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletUseQR.objDenyButton,getTextVal(MLWalletUseQR.objDenyButton,"Button"));
		verifyElementPresentAndClick(MLWalletUseQR.objScanQRBackBtn,"Scan QR Page Back Arrow Button");
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		if(verifyElementPresent(MLWalletUseQR.objCameraPermissionRequiredPopup,getTextVal(MLWalletUseQR.objCameraPermissionRequiredPopup,"Popup"))){
			verifyElementPresent(MLWalletUseQR.objCameraPermissionRequiredMsg,getTextVal(MLWalletUseQR.objCameraPermissionRequiredMsg,"Message"));
			verifyElementPresent(MLWalletUseQR.objOKBtn,getTextVal(MLWalletUseQR.objOKBtn,"Button"));
			logger.info("QR_TC_24, Use QR, Scan QR Code After Clicking On Deny Button Validated");
			ExtentReporter.extentLoggerPass("QR_TC_24", "QR_TC_24, Use QR, Scan QR Code After Clicking On Deny Button Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void UseQR_SendMoneyToMLWalletLocationPopupValidation_QR_TC_026() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Location popup Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpValidation();
			logger.info("QR_TC_026, Use QR, Send Money To ML Wallet Location popup Validated");
			ExtentReporter.extentLoggerPass("QR_TC_026", "QR_TC_026, Use QR, Send Money To ML Wallet Location popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_SendMoneyToMLWalletLocationDenyFunctionality_QR_TC_027() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Location Deny Functionality Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyPopUp();
			logger.info("QR_TC_027, Use QR, Send Money To ML Wallet Location Deny Functionality Validated");
			ExtentReporter.extentLoggerPass("QR_TC_027", "QR_TC_027, Use QR, Send Money To ML Wallet Location Deny Functionality Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}

	public void useQR_SendMoneyToMLWalletLocationPermissionDenyCloseBtnFunctionality_QR_TC_028() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyCloseBtnFunctionality();
			if(verifyElementPresent(SendTransferPage.objMLWalletBalance,getTextVal(SendTransferPage.objMLWalletBalance,"Page"))){
				logger.info("QR_TC_028, Use QR, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
				ExtentReporter.extentLoggerPass("QR_TC_028", "QR_TC_028, Use QR, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}



	public void useQR_SendMoneyToMLWalletLocationPermissionDenyOpenSettingsBtnFunctionality_QR_TC_029() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Location Permission Deny open Settings Button Functionality Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			permissionDenyOpenSettingsBtnFunctionality();
			if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
				logger.info("QR_TC_029, Use QR, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
				ExtentReporter.extentLoggerPass("QR_TC_029", "QR_TC_029, Use QR, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}



	public void useQR_SendMoneyToMLWalletLocationPopUpAllowFunctionality_QR_TC_030() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To ML Wallet Location popup Allow Button Functionality Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
			locationPopUpAllowFunctionality();
			if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
				logger.info("QR_TC_030, Use QR, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
				ExtentReporter.extentLoggerPass("QR_TC_030", "QR_TC_030, Use QR, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}


	public void useQR_CameraPermissionRequiredPopupValidation_QR_TC_31() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Camera Permission Required Popup Validation");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"));
		verifyElementPresentAndClick(MLWalletUseQR.objDenyButton,getTextVal(MLWalletUseQR.objDenyButton,"Button"));
		verifyElementPresent(MLWalletUseQR.objCameraNotAuthorized, getTextVal(MLWalletUseQR.objCameraNotAuthorized, "Message"));
		verifyElementPresentAndClick(MLWalletUseQR.objScanQRBackBtn,"Scan QR Page Back Arrow Button");
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		verifyElementPresent(MLWalletUseQR.objCameraPermissionRequiredPopup,getTextVal(MLWalletUseQR.objCameraPermissionRequiredPopup,"Popup"));
		verifyElementPresent(MLWalletUseQR.objCameraPermissionRequiredMsg,getTextVal(MLWalletUseQR.objCameraPermissionRequiredMsg,"Message"));
		verifyElementPresentAndClick(MLWalletUseQR.objOKBtn,getTextVal(MLWalletUseQR.objOKBtn,"Button"));
		if(verifyElementPresent(MLWalletUseQR.objCameraPopup,getTextVal(MLWalletUseQR.objCameraPopup,"Popup"))) {
			verifyElementPresent(MLWalletUseQR.objWhileUsingApp, getTextVal(MLWalletUseQR.objWhileUsingApp, "Button"));
			verifyElementPresent(MLWalletUseQR.objOnlyThisTime, getTextVal(MLWalletUseQR.objOnlyThisTime, "Button"));
			verifyElementPresent(MLWalletUseQR.objDenyButton, getTextVal(MLWalletUseQR.objDenyButton, "Button"));
			logger.info("QR_TC_31, Use QR, Camera Permission Required Popup Validated");
			ExtentReporter.extentLoggerPass("QR_TC_31", "QR_TC_31, Use QR, Camera Permission Required Popup Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_TransactionValidationAfterMinimizingTheApp_QR_TC_34() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Transaction Validation After Minimizing The app");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
		logger.info("Application relaunched after 5 Seconds");
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			logger.info("QR_TC_34, Use QR, Transaction Validation After Minimizing App Validated");
			ExtentReporter.extentLoggerPass("QR_TC_34", "QR_TC_34, Use QR, Transaction Validation After Minimizing App Validated");
			System.out.println("-----------------------------------------------------------");
		}
	}


	public void useQR_SendMoneyToMLWalletWhenReceiverQRCodeCapturedByCamera_QR_TC_40() throws Exception {
		ExtentReporter.HeaderChildNode("Use QR, Send Money To Any ML Wallet When Receiver QR Code Captured By Camera");
		useQRCodeNavigation(prop.getproperty("Branch_Verified"));
		verifyElementPresentAndClick(MLWalletUseQR.objReadQRCodeToSendMoney,getTextVal(MLWalletUseQR.objReadQRCodeToSendMoney,"Option"));
		enableCameraPopup();
		scanQR(prop.getproperty("Valid_QR"));
		enterAmountAndSendToMLWallet("10");
		enableLocation_PopUp();
		enterOTP(prop.getproperty("Valid_OTP"));
		if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
			verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
			verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
			String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
			System.out.println(sReferenceNumber);
			Swipe("UP", 2);
			click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
			waitTime(3000);
			Swipe("DOWN", 2);
			Swipe("UP", 1);
			verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
			verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
			if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
				String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
				System.out.println(sReferenceNumberInWalletToWallet);
				assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
				logger.info("QR_TC_40, Successfully Amount sent from Wallet to Wallet using QR Code Captured By Camera and Transaction Details is validated");
				ExtentReporter.extentLoggerPass("QR_TC_40", "QR_TC_40, Successfully Amount sent from Wallet to Wallet using QR Code Captured By Camera and Transaction Details is validated");
				System.out.println("-----------------------------------------------------------");
			}
		}
	}








	public void switchplatform(){
		HeaderChildNode("Switch platform");
		System.out.println("Started");
		Utilities.setPlatform = "Web";
		new CommandBase("Chrome","","");
		waitTime(4000);
		DriverManager.getDriver().get("https://prnt.sc/yCg4bTRXjDQp");
		System.out.println("end");

	}



}







