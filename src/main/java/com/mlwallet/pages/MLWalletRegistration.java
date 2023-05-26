package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletRegistration {

    public static By objCreateOne = By.xpath("//*[@text=' Create one']");
    public static By objRegistration = By.xpath("//*[@text='Registration Number']");
    public static By objRegistrationBackBtn = By.xpath("//*[@text='Registration Number']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
    public static By objOTPPageBackBtn = By.xpath("//*[@text='One Time Pin']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objMobileNumberField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objConfirm = By.xpath("//*[@text='Confirm']");
    public static By objRegistrationPersonalInfo = By.xpath("//*[@text='Registration Personal Info']");
    public static By objRegistrationPersonalInfoBackBtn = By.xpath("//*[@text='Registration Personal Info']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objPersonalInfo = By.xpath("//*[@resource-id='LQWMWP']");
    public static By objFirstName = By.xpath("//*[@resource-id='5PFQ86']");
    public static By objFirstNameErrorMsg = By.xpath("//*[@resource-id='5PFQ86']/following-sibling::android.widget.TextView");
    public static By objMiddleName = By.xpath("//*[@resource-id='NFEGGM']");
    public static By objMiddleNameErrorMsg = By.xpath("//*[@resource-id='NFEGGM']/following-sibling::android.widget.TextView");
    public static By objLastName = By.xpath("//*[@resource-id='Q1TDKI']");
    public static By objLastNameErrorMsg = By.xpath("//*[@resource-id='Q1TDKI']/following-sibling::android.widget.TextView");
    public static By objBirthDate = By.xpath("//*[@resource-id='0L347S']");
    public static By objBirthDateErrorMsg = By.xpath("//*[@resource-id='0L347S']/child::android.widget.TextView");
    public static By objYearSelector(String year){
       return By.xpath("//*[@text='"+year+"']");
    }
    public static By objOkBtn = By.xpath("//*[@text='OK']");
    public static By objDatePickerYear = By.xpath("//*[@resource-id='android:id/date_picker_header_year']");
    public static By objEmailAddress = By.xpath("//*[@resource-id='YDP4RE']");
    public static By objEmailAddressErrorMsg = By.xpath("//*[@resource-id='YDP4RE']/following-sibling::android.widget.TextView");
    public static By objReEnterEmailAddress = By.xpath("//*[@resource-id='0VBCUG']");
    public static By objReEnterEmailAddressErrorMsg = By.xpath("//*[@resource-id='0VBCUG']/following-sibling::android.widget.TextView");
    public static By objPlaceOfBirth = By.xpath("//*[@resource-id='SYFG77']");
    public static By objPlaceOfBirthErrorMsg = By.xpath("//*[@resource-id='SYFG77']/following-sibling::android.widget.TextView");

    public static By objNationality = By.xpath("//*[@resource-id='9SWVJ6']");
    public static By objNationalityErrorMsg = By.xpath("(//*[@resource-id='9SWVJ6']/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objNationalitySearchField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objFilipino = By.xpath("//*[@text='FILIPINO']");
    public static By  objCivilStatus = By.xpath("(//*[@resource-id='9SWVJ6'])[3]");
    public static By objCivilStatusErrorMsg = By.xpath("((//*[@resource-id='9SWVJ6'])[4]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");

    public static By objSingleCivilStatus = By.xpath("//*[@text='Single']");

    public static By objGender = By.xpath("(//*[@resource-id='9SWVJ6'])[6]");
    public static By objGenderErrorMsg = By.xpath("((//*[@resource-id='9SWVJ6'])[6]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objMaleGender = By.xpath("//*[@text='Male']");
    public static By objRegistrationAddress = By.xpath("//*[@text='Registration Address']");
    public static By objRegistrationAddressBackBtn = By.xpath("//*[@text='Registration Address']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");


    public static By objProvince = By.xpath("(//*[@resource-id='D5ZBS3'])[1]");
    public static By ObjProvinceErrorMsg = By.xpath("((//*[@resource-id='D5ZBS3'])[2]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objProvinceSearchField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objAuroraProvince = By.xpath("//*[@text='AURORA']");


    public static By objCity = By.xpath("(//*[@resource-id='0Y7K8H'])[1]");
    public static By objCityErrorMsg = By.xpath("((//*[@resource-id='0Y7K8H'])[2]/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]");
    public static By objMariaAurora = By.xpath("//*[@text='MARIA AURORA']");

    public static By objHouseNo = By.xpath("(//*[@resource-id='SRVX6B'])[1]");
    public static By objHouseNoErrorMsg = By.xpath("//*[@resource-id='SRVX6B']/following-sibling::android.widget.TextView");

    public static By objExistingAccErrorMsg = By.xpath("(//*[@resource-id='something']/following-sibling::android.widget.TextView)[2]");
    public static By objGOBackToLoginScreenBtn = By.xpath("//*[@resource-id='modal-confirm-button']/child::android.widget.TextView");
    public static By objTermsAndCondition = By.xpath("//*[@text='Terms and Conditions']");
    public static By objTermsAndConditionBackBtn = By.xpath("//*[@text='Terms and Conditions']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

}
