package com.mlwallet.pages;

import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.openqa.selenium.By;

public class MLWalletSettingsPage {
	public static By objProfileIcon=By.xpath("(//*[android.widget.ImageView]/ancestor::android.view.ViewGroup/descendant::android.view.ViewGroup/child::android.view.ViewGroup/following-sibling::android.view.ViewGroup)[1]");
	public static By objProfileIcon1=By.xpath("(//*[android.widget.ImageView]/ancestor::android.view.ViewGroup/descendant::android.view.ViewGroup/child::android.view.ViewGroup/following-sibling::android.view.ViewGroup)[2]");
	public static By objSettings = By.xpath("//*[@text='Settings']");
	public static By objProfileSettings = By.xpath("//*[@text='Profile Settings']");
	public static By objNotification = By.xpath("//*[@text='Notification']");
	public static By objKeyword = By.xpath("//*[@resource-id='803512']");
	public static By objMLPinEditField = By.xpath("//*[@resource-id='847305']");
	public static By objDeleteAccount = By.xpath("//*[@text='Delete Account']");
	public static By objProceedBtn = By.xpath("//*[@text='Proceed']");
	public static By objAccountDetails=By.xpath("//*[@text='Account Details']");
	public static By objBiometricsLogin = By.xpath("//*[@text='Biometrics Login']");
	public static By objBiometricsSwitch = By.xpath("//*[@class='android.widget.Switch']");
	public static By objNameOfAccountHolder=By.xpath("(//*[@text='Name']/following-sibling::android.widget.TextView)[1]");
	public static By objMailAddressOfAccountHolder=By.xpath("(//*[@text='Email']/following-sibling::android.widget.TextView)[1]");
	public static By objMobileNoOfAccountHolder=By.xpath("(//*[@text='Mobile Number']/following-sibling::android.widget.TextView)[1]");
	public static By objChangeMLPin=By.xpath("//*[@text='Change ML PIN']");
	public static By objEnterCurrentMLPin = By.xpath("//*[@text='Enter Current ML PIN']");
	public static By objEnterMpinVal (String mPin)
	{
		return By.xpath("//*[@text='"+mPin+"']");
	}
	public static By objInvalaidMpinPopUp=By.xpath("//*[@text='You have entered an invalid PIN. Please try again.']");
	public static By objEnterNewMMLpinText=By.xpath("//*[@text='Enter New ML PIN']");
	public static By objReEnterNewMMLpinText=By.xpath("//*[@text='Re-Enter New ML PIN']");
	public static By objOKBtn=By.xpath("//*[@text='Ok']");
	public static By objMLPinChangedPopup=By.xpath("//*[@text='ML Pin Successfully Changed']");
	public static By objGotItBtn=By.xpath("//*[@text='Got It']");
	public static By objSettingsBackBtn = By.xpath("//*[@text='Settings']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objChangeMLPinBackBtn = By.xpath("//*[@text='Change ML PIN']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objAccountDetailsBackBtn = By.xpath("//*[@text='Account Details']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objAccountRecovery=By.xpath("//*[@text='Account Recovery']");
	public static By objTroubleSigningInBackBtn = By.xpath("//*[@text='Trouble Signing In']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objBiometricsLoginBackBtn = By.xpath("//*[@text='Biometrics Login']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objMlWalletSupportPage=By.xpath("//*[@text='ML Wallet Support']");
	public static By objFullNameField=By.xpath("//*[@resource-id='i1']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
	public static By objRegisteredEmail=By.xpath("//*[@resource-id='i5']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
	public static By objMobileNumber=By.xpath("//*[@resource-id='i9']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
	public static By objNatureOfReqRadioBtn=By.xpath("//*[@resource-id='i17']");
	public static By objNextBtn=By.xpath("//*[@text='Next']");
	public static By objYourAnswer=By.xpath("//*[@class='android.widget.EditText' and ./parent::*[./parent::*[./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='Lost/Stolen Mobile Number Required question']]]]]]]");
	public static By objNewMobNo=By.xpath("//*[@class='android.widget.EditText' and ./parent::*[./parent::*[./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='New Mobile Number Required question']]]]]]]");
	public static By objFacebookMessangerName=By.xpath("//*[@class='android.widget.EditText' and ./parent::*[./parent::*[./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='Facebook Messenger Name Required question']]]]]]]");
	public static By objSumbitBtn=By.xpath("//*[@text='Submit']");
	public static By objStolenPage=By.xpath("//*[@text='Reporting a Lost/Stolen Device']");
	public static By objReviewPage=By.xpath("//*[@text='ML Wallet Support']/following-sibling::android.widget.TextView");
	public static By objActivateBiometricsLogin = By.xpath("//*[@text='Activate Biometrics Login']");
	public static By objTroubleSigningIn = By.xpath("//*[@text='Trouble Signing In']");
	public static By objMLWalletSupport = By.xpath("//*[@text='ML Wallet Support' and @class='android.widget.TextView']");
	public static By objNatureOfRequests = By.xpath("//*[@class='android.widget.RadioGroup']/child::android.view.View/child::android.widget.TextView");
	public static By objDeleteConfirmationPopUp = By.xpath("//*[@resource-id='something']/following-sibling::android.widget.TextView");
	public static By objCancelBtn = By.xpath("//*[@text='Cancel']");
	public static By objBiometrics = By.xpath("//*[@text='Biometrics']");

}
