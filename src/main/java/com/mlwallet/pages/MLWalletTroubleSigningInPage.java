package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletTroubleSigningInPage {

    public static By objTroubleSigningIn = By.xpath("//*[@text='Trouble signing in?']");
    public static By objTroubleSingingInPAge = By.xpath("//*[@text='Trouble Signing In']");
    public static By objTroubleSigningInBackArrowBtn = By.xpath("//*[@text='Trouble Signing In']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
    public static By objNatureOfRequests = By.xpath("//*[@class='android.widget.RadioGroup']/child::android.view.View/child::android.widget.TextView");
    public static By objNatureOfRequest = By.xpath("(//*[@class='android.widget.RadioGroup']/child::android.view.View/child::android.widget.TextView)[1]");
    public static By objFullNameField=By.xpath("//*[@resource-id='i1']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
    public static By objRegisteredEmail=By.xpath("//*[@resource-id='i5']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
    public static By objMobileNumber=By.xpath("//*[@resource-id='i9']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.EditText");
    public static By objClearFormButton = By.xpath("//*[@text='Clear form']");
    public static By objClearFormPopup = By.xpath("//*[@resource-id='Qf7yGf0']");
    public static By objClearFormPopupMsg = By.xpath("//*[@resource-id='Qf7yGf1']");
    public static By objCancelBtn = By.xpath("//*[@text='Cancel']");
    public static By objNextBtn = By.xpath("//*[@text='Next']");


}
