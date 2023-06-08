package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletUseQR {

    public static By objUseQR = By.xpath("//*[@text='Use QR']");
    public static By objGenerateQRCodeToReceiveMoney = By.xpath("//*[@text='Generate QR Code to Receive Money']");
    public static By objReadQRCodeToSendMoney = By.xpath("//*[@text='Read QR Code to Send/Pay Money']");
    public static By objReadQRCodeToSignInToWeb = By.xpath("//*[@text='Read QR Code to Sign In to Web']");
    public static By objUseQROptions = By.xpath("//*[@text='Use QR options']");
    public static By objGenerateQR = By.xpath("//*[@text='Generate QR']");
    public static By objQR = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*/*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text]])[1]");
    public static By objReceiverName = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[2]/*[@text])[2]");
    public static By objReceiverMobileNumber = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[2]/*[@text])[2]/following-sibling::android.widget.TextView");

    public static By objAllowCamera = By.xpath("//*[@text='WHILE USING THE APP']");
    public static By objInvalidQRCodeMsg = By.xpath("//*[@text='Ok' or @text='OK']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
    public static By objOKBtn = By.xpath("//*[@text='Ok' or @text='OK']");
    public static By objSuccess = By.xpath("//*[@text='Success']");

    public static By objUseQRBackBtn = By.xpath("//*[@text='Use QR']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
    public static By objGenerateQRBackBtn = By.xpath("//*[@text='Generate QR']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objReceiverNameInEnterAmount = By.xpath("//*[@resource-id='EE1R6R']");
    public static By objReceiverMobileNumberInEnterAmount = By.xpath("//*[@resource-id='6EF7FX']");
    public static By objAmountInputField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objNextBtn = By.xpath("//*[@text='Next']");
    public static By objAmountRequiredErrorMsg = By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");
    public static By objConfirmDetails = By.xpath("//*[@text='Confirm Details']");
    public static By objMLWalletBalance = By.xpath("//*[@text='ML Wallet balance']");
    public static By objReceiverNameInConfirmDetails = By.xpath("(//*[@resource-id='Receiver Name'])[2]");

    public static By objReceiverMobileNo= By.xpath("(//*[@resource-id='Receiver Mobile No.'])[2]");
    public static By objPaymentMethod = By.xpath("(//*[@resource-id='Payment Method'])[2]");

    public static By objAmount = By.xpath("(//*[@resource-id='Amount to Send'])[2]");

    public static By objServiceFee = By.xpath("(//*[@resource-id='Service Fee'])[2]");

    public static By objTotalAmount = By.xpath("(//*[@resource-id='Total'])[2]");
    public static By objCancelTransaction = By.xpath("//*[@text='Cancel Transaction']");
    public static By objSendPHPBtn = By.xpath("//*[contains(@text,'Send Php')]");

    public static By objCameraPopup = By.xpath("//*[@text='Allow ML Wallet to take pictures and record video?']");
    public static By objWhileUsingApp = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']");
    public static By objOnlyThisTime = By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']");
    public static By objDenyButton = By.xpath("//*[@text='Deny']");
    public static By objCameraNotAuthorized = By.xpath("//*[@text='Camera not authorized']");
    public static By objScanQR = By.xpath("//*[@text='Scan QR']");
    public static By objScanQRBackBtn = By.xpath("//*[@text='Scan QR']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objScanQRSuggestion = By.xpath("//*[@text='Make sure that the QR code is within the frame']");
    public static By objCameraPermissionRequiredPopup = By.xpath("//*[@resource-id='android:id/alertTitle']");
    public static By objCameraPermissionRequiredMsg = By.xpath("//*[@resource-id='android:id/message']");

}
