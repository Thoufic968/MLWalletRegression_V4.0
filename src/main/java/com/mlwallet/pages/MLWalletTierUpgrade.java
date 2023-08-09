package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletTierUpgrade {

//  Verification Tier perk page back button
    public static By objVerificationTierPerksBackBtn = By.xpath("//*[@text='Verification Tier Perks']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Semi verified Tab
    public static By objSemiVerifiedTab = By.xpath("//*[@text='Semi Verified']");
//  Upgrade Tier Level Button
    public static By objUpgradeTierLevel = By.xpath("//*[@text='Upgrade Tier Level']");
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
    public static By objTierUpgrade = By.xpath("//*[@text='Tier Upgrade']");
}
