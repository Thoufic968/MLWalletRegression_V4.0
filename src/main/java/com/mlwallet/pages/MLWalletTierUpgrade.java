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
//  Place of Birth
    public static By objPlaceOfBirth = By.xpath("//*[@text='Place of Birth']/following-sibling::android.widget.EditText");
//  Nationality
    public static By objNationality = By.xpath("//*[@text='Nationality']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Civil Status
    public static By objCivilStatus = By.xpath("//*[@text='Civil Status']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Gender at Birth
    public static By objGenderAtBirth = By.xpath("//*[@text='Gender at Birth']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Source of Income
    public static By objSourceOfIncome = By.xpath("//*[@text='Source of Income']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Company/Employer Name
    public static By objCompanyOrEmployerName = By.xpath("//*[@text='Company/Employer Name']/following-sibling::android.widget.EditText");
//  Work Address
    public static By objWorkAddress = By.xpath("//*[@text='Work Address']/following-sibling::android.widget.EditText");
//  Account Details
    public static By objPersonalInformation = By.xpath("//*[@text='Personal Information']");
//  Product/Service Offered
    public static By objProductServiceOffered = By.xpath("//*[@text='Product/Services Offered']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Position At work
    public static By objPositionAtWork = By.xpath("//*[@text='Position at Work']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Nature of work
    public static By ObjNatureOfWork = By.xpath("(//*[@text='Nature of Work'])[2]/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Select any valid ID
    public static By objSelectAnyValidID = By.xpath("//*[@text='Select Any Valid ID']");
//  Select ID
    public static By objSelectID = By.xpath("//*[@text='Select ID']");
//  Select ID Selection Btn
    public static By objSelectIDSelectionBtn = By.xpath("//*[@text='Select ID']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");
//  Tier upgrade Back btn
    public static By objTierUpgradeBackBtn = By.xpath("//*[@text='Tier Upgrade']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Upload ID Photos
    public static By objUploadIDPhotos = By.xpath("//*[@text='Upload ID Photos']");
//  Select ID
    public static By objSelectIDField = By.xpath("//*[@text='Select ID']/preceding-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
//  Card Number Input Field
    public static By objCardInputField = By.xpath("//*[@class='android.widget.EditText']");
//  Scan Front of ID
    public static By objScanFrontOfID = By.xpath("//*[@text='Scan Front of ID']");
//  Scan Front of ID Back Arrow Button
    public static By objScanFrontOfIDBackBtn = By.xpath("//*[@text='Scan Front of ID']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Scan Back of ID
    public static By objScanBackOfID = By.xpath("//*[@text='Scan Back of ID']");
//  Scan Front of ID Back Arrow Button
    public static By objScanBackOfIDBackBtn = By.xpath("//*[@text='Scan Back of ID']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
//  Take a Face Identity Photo
    public static By objTakeAFaceIdentityPhoto = By.xpath("//*[@text='Take a Face Identity Photo']");
//  Phil Health
    public static By objPhilHealth = By.xpath("//*[@text='PHILHEALTH']");
//  Scan Front of ID Info
    public static By objScanFrontOfIDInfo = By.xpath("(//*[@resource-id='com.mlhuillier.mlwallet:id/texture_view']/parent::android.widget.FrameLayout/following-sibling::android.widget.TextView)[2]");
//  Capture Screen
    public static By objCaptureScreen = By.xpath("(//*[@resource-id='com.mlhuillier.mlwallet:id/texture_view']/parent::android.widget.FrameLayout/following-sibling::android.view.ViewGroup)[1]");
//  Capture button
    public static By objCaptureButton = By.xpath("(//*[@resource-id='com.mlhuillier.mlwallet:id/texture_view']/parent::android.widget.FrameLayout/following-sibling::android.view.ViewGroup)[2]");
//  Tier Upgrade Take a Face Identity Photo Info
    public static By objTakeAFaceIdentityPhotoInfo = By.xpath("//*[@resource-id='com.mlhuillier.mlwallet:id/texture_view']/parent::android.widget.FrameLayout/following-sibling::android.widget.TextView");
}
