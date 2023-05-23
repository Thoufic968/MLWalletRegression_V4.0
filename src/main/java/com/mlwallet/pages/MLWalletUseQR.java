package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletUseQR {

    public static By objUseQR = By.xpath("//*[@text='Use QR']");
    public static By objGenerateQRCodeToReceiveMoney = By.xpath("//*[@text='Generate QR Code to Receive Money']");
    public static By objReadQRCodeToSendMoney = By.xpath("//*[@text='Read QR Code to Send Money']");
    public static By objGenerateQR = By.xpath("//*[@text='Generate QR']");
    public static By objQR = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*/*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@text]])[1]");
    public static By objReceiverName = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[2]/*[@text])[1]");
    public static By objReceiverMobileNumber = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[2]/*[@text])[2]");
    public static By objAllowCamera = By.xpath("//*[@text='WHILE USING THE APP']");
}
