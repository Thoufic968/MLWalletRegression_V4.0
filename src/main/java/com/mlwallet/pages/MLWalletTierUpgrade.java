package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletTierUpgrade {

//  Verification Tier perk page back button
    public static By objVerificationTierPerksBackBtn = By.xpath("//*[@text='Verification Tier Perks']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Semi verified Tab
    public static By objSemiVerifiedTab = By.xpath("//*[@text='Semi Verified']");
//  Upgrade Tier Level Button
    public static By objUpgradeTierLevel = By.xpath("//*[@text='Upgrade Tier Level']/parent::android.view.ViewGroup");
//  Fully verified Tab
    public static By objFullyVerifiedTab = By.xpath("//*[@text='Fully Verified']");
//  Branch verified Tab
    public static By objBranchVerifiedTab = By.xpath("//*[@text='Branch Verified']");
//  Account Details
    public static By objAccountDetails = By.xpath("//*[@text='Account Details']");
//  Account Details page back button
    public static By objAccountDetailsBackBtn = By.xpath("//*[@text='Account Details']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Search field
    public static By objSearchFieldInput = By.xpath("//*[@class='android.widget.EditText']");
//  Matching elements for Product/Services
    public static By objMatchingElementsProductServices(int i){
        return By.xpath("((//*[@class='android.widget.ScrollView']/child::android.view.ViewGroup/child::android.view.ViewGroup)["+i+"]/child::android.view.ViewGroup)[1]/child::android.view.ViewGroup/child::android.widget.TextView");
    }
//  Matching Elements
    public static By objMatchingElements = By.xpath("//*[@class='android.widget.ScrollView']/child::android.view.ViewGroup/child::android.view.ViewGroup");
//  Product/Services Offered selection button
    public static By objProductServiceSelectionBtn = By.xpath("//*[@text='Product/Services Offered']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");
//  Position at Work selection button
    public static By objPositionAtWokSelectionBtn = By.xpath("//*[@text='Position at Work']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");
//  Nature of Work selection button
    public static By objNatureOfWorkSelectionBtn = By.xpath("(//*[@text='Nature of Work']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView)[2]");
//  Confirm details btn
    public static By objConfirmDetails = By.xpath("//*[@text='Confirm Details']");
//  Tier Upgrade btn
    public static By objTierUpgrade = By.xpath("//*[@text='Upgrade Tier Level']");
//  Acc details text
    public static By objAccDetailsText = By.xpath("//*[@text='Account Details']");
//  Select any valid id txt
    public static By objSelectAnyValidText = By.xpath("//*[@text='Select Any Valid ID']");
//  Select ID txt
    public static By objSelectIDText = By.xpath("//*[@text='Select ID']");
    // Back button of Select ID Page
    public static By objSelectIDPageBackBtn = By.xpath("//*[@text='Tier Upgrade']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  ID's
    public static By getObjElementsID(int i){
    return By.xpath("((//*[@class='android.widget.ScrollView']/child::android.view.ViewGroup/child::android.view.ViewGroup)["+i+"]/child::android.view.ViewGroup)[1]/child::android.view.ViewGroup/child::android.widget.TextView");
    }
//  Take a picture btn
    public static By objTakePicBtn = By.xpath("//*[contains(@text, \"Let's Take a Picture\")]");
// Select Specific ID
    public static By getObjSelectedID(String ID){
        return By.xpath("//*[@text='"+ID+"']");
    }
//
    public static By objBackBtnTakePic= By.xpath("//*[@text='Take ID Picture']/preceding-sibling::android.widget.LinearLayout");
    public static By objLeaveBtn = By.xpath("//*[@text='Leave']");
    public static By objUploadPage= By.xpath("//*[@text='Upload ID Photos']");
    public static By objTextTiertext = By.xpath("//*[@text='Tier Upgrade']");
    public static By objBackPicIdBackBtn = By.xpath("//*[@text='Tier Upgrade']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
    //
    public static By objScanFront = By.xpath("//*[@text='Scan Front of ID']");
    //
    public static By objScanBack = By.xpath("//*[@text='Scan Back of ID']");
    //
    public static By objScanFace = By.xpath("//*[@text='Take a Face Identity Photo']");
    //
}
