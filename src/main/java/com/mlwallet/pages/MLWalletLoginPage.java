package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletLoginPage {
	
	public static By objMobileNumberTextField=By.xpath("//*[android.view.ViewGroup]/descendant::android.widget.EditText");
	public static By objLoginBtn=By.xpath("//*[@text='Login']");
	public static By objOtpTextField(int i){
		return By.xpath("(//*[@class='android.widget.EditText'])["+i+"]");
	}
	public static By objCameraPopup = By.xpath("//*[@text='Allow ML Wallet to take pictures and record video?']");
	public static By objContinueBtn = By.xpath("//*[@text='CONTINUE']");
	public static By objCancelBtn = By.xpath("//*[@text='CANCEL']");
	public static By objAvailableBalance = By.xpath("//*[@text='BALANCE']");
	public static By objInvalidMobNumberTxt = By.xpath("//*[@text='Mobile number is invalid']");
	public static By objOneTimePin = By.xpath("(//*[@text='One Time Pin'])[1]");
	public static By objMLPin = By.xpath("//*[@text='ML Pin']");
	public static By objMLPinEditField(int i) {
		return  By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]])[1]/*[@class='android.view.ViewGroup'])["+i+"]");
	}
	public static By objOneBtn = By.xpath("//*[@text='1']");
	public static By objLocationPopup=By.xpath("//*[@text='Allow ML Wallet to access this device’s location?']");
	public static By objTroubleSigningIn = By.xpath("//*[@text='Trouble signing in?']");
	public static By objTroubleSigningPage = By.xpath("//*[@text='Trouble Signing In']");

	public static By objMLWalletSupport = By.xpath("(//*[@text='ML Wallet Support'])[2]");

	public static By objBranchLocator = By.xpath("//*[@text='Branch Locator']");

	public static By objAppVersion = By.xpath("//*[@resource-id='346187']");
	public static By objAppVersionChangeNumber = By.xpath("//*[@resource-id='216094']");

	public static By objHaveAnAccountMsg = By.xpath("//*[@text='Don’t have an account?']");

	public static By objCreateOneBtn = By.xpath("//*[@text=' Create one']");

	public static By objRegistrationNumber = By.xpath("//*[@text='Registration Number']");

	public static By objResendCode = By.xpath("//*[contains(@text,'Resend Code')]");
	public static By objErrorPopup = By.xpath("//*[@text='Ok']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	public static By objOkBtn = By.xpath("//*[@text='Ok']");

	public static By objNoInternetConnectionMsg = By.xpath("(//*[@text='Connection Error']/following-sibling::android.widget.TextView)[1]");
	public static By objConnectionError = By.xpath("//*[@text='Connection Error']");
	public static By objNoConnectionAppVersion = By.xpath("(//*[@text='Connection Error']/following-sibling::android.widget.TextView)[2]");
	public static By objOTP = By.xpath("((//*[@text='One Time Pin']/following-sibling::android.view.ViewGroup)[1]/child::android.widget.TextView)[1]");
	public static By objSeconds = By.xpath("(//*[@class='com.horcrux.svg.SvgView'])[2]/following-sibling::android.view.ViewGroup/child::android.widget.TextView");

}
