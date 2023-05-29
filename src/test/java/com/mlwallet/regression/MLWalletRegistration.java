package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletRegistration {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;



    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletRegistration.deviceName=deviceName;
        MLWalletRegistration.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

    @Test(priority = 1)
    public void registrationInvalidMobileNumber_RG_TC_02() throws Exception {
        MLWalletBusinessLogic.registrationInvalidMobileNumber_RG_TC_02();
    }

    @Test(priority = 2)
    public void registrationFirstNameInputFieldValidation_RG_TC_04() throws Exception {
        MLWalletBusinessLogic.registrationFirstNameInputFieldValidation_RG_TC_04();
    }

    @Test(priority = 3)
    public void registrationMiddleNameInputFieldValidation_RG_TC_05() throws Exception {
        MLWalletBusinessLogic.registrationMiddleNameInputFieldValidation_RG_TC_05();
    }

    @Test(priority = 4)
    public void registrationLastNameInputFieldValidation_RG_TC_06() throws Exception {
        MLWalletBusinessLogic.registrationLastNameInputFieldValidation_RG_TC_06();
    }

    @Test(priority = 5)
    public void registrationInvalidBirthDate_RG_TC_07() throws Exception {
        MLWalletBusinessLogic.registrationInvalidBirthDate_RG_TC_07();
    }

    @Test(priority = 6)
    public void registrationInvalidEmailAddress_RG_TC_08() throws Exception {
        MLWalletBusinessLogic.registrationInvalidEmailAddress_RG_TC_08();
    }

    @Test(priority = 7)
    public void registrationInvalidReEnterEmailAddress_RG_TC_09() throws Exception {
        MLWalletBusinessLogic.registrationInvalidReEnterEmailAddress_RG_TC_09();
    }

    @Test(priority = 8)
    public void registrationInvalidPlaceOfBirthValidation_RG_TC_10() throws Exception {
        MLWalletBusinessLogic.registrationInvalidPlaceOfBirthValidation_RG_TC_10();
    }

    @Test(priority = 9)
    public void registrationEmptyNationalityFieldValidation_RG_TC_11() throws Exception {
        MLWalletBusinessLogic.registrationEmptyNationalityFieldValidation_RG_TC_11();
    }

    @Test(priority = 10)
    public void registrationEmptyCivilStatusFieldValidation_RG_TC_12() throws Exception {
        MLWalletBusinessLogic.registrationEmptyCivilStatusFieldValidation_RG_TC_12();
    }

    @Test(priority = 11)
    public void registrationEmptyGenderFieldValidation_RG_TC_13() throws Exception {
        MLWalletBusinessLogic.registrationEmptyGenderFieldValidation_RG_TC_13();
    }

    @Test(priority = 12)
    public void registrationEmptyProvinceFieldValidation_RG_TC_14() throws Exception {
        MLWalletBusinessLogic.registrationEmptyProvinceFieldValidation_RG_TC_14();
    }

    @Test(priority = 13)
    public void registrationEmptyCityFieldValidation_RG_TC_15() throws Exception {
        MLWalletBusinessLogic.registrationEmptyCityFieldValidation_RG_TC_15();
    }

    @Test(priority = 14)
    public void registrationEmptyHouseNoFieldValidation_RG_TC_16() throws Exception {
        MLWalletBusinessLogic.registrationEmptyHouseNoFieldValidation_RG_TC_16();
    }

    @Test(priority = 15)
    public void registrationEmptyFirstNameFieldValidation_RG_TC_18() throws Exception {
        MLWalletBusinessLogic.registrationEmptyFirstNameFieldValidation_RG_TC_18();
    }

    @Test(priority = 16)
    public void registrationEmptyMiddleNameFieldValidation_RG_TC_19() throws Exception {
        MLWalletBusinessLogic.registrationEmptyMiddleNameFieldValidation_RG_TC_19();
    }

    @Test(priority = 17)
    public void registrationEmptyLastNameFieldValidation_RG_TC_20() throws Exception {
        MLWalletBusinessLogic.registrationEmptyLastNameFieldValidation_RG_TC_20();
    }

    @Test(priority = 18)
    public void registrationEmptyEmailAddress_RG_TC_22() throws Exception {
        MLWalletBusinessLogic.registrationEmptyEmailAddress_RG_TC_22();
    }

    @Test(priority = 19)
    public void registrationEmptyPlaceOfBirthValidation_RG_TC_23() throws Exception {
        MLWalletBusinessLogic.registrationEmptyPlaceOfBirthValidation_RG_TC_23();
    }

    @Test(priority = 20)
    public void registrationInvalidHouseNoFieldValidation_RG_TC_24() throws Exception {
        MLWalletBusinessLogic.registrationInvalidHouseNoFieldValidation_RG_TC_24();
    }

    @Test(priority = 21)
    public void registrationCreatingAccountWithExistingMLWalletAccount_RG_TC_25() throws Exception {
        MLWalletBusinessLogic.registrationCreatingAccountWithExistingMLWalletAccount_RG_TC_25();
    }

    @Test(priority = 22)
    public void registrationNumberPageUIValidation_RG_TC_27() throws Exception {
        MLWalletBusinessLogic.registrationNumberPageUIValidation_RG_TC_27();
    }

    @Test(priority = 23)
    public void registrationOTPPageUIValidation_RG_TC_28() throws Exception {
        MLWalletBusinessLogic.registrationOTPPageUIValidation_RG_TC_28();
    }

    @Test(priority = 24)
    public void registrationPersonalInfoPageUIValidation_RG_TC_30() throws Exception {
        MLWalletBusinessLogic.registrationPersonalInfoPageUIValidation_RG_TC_30();
    }

    @Test(priority = 25)
    public void registrationNumberPageBackBtnValidation_RG_TC_31() throws Exception {
        MLWalletBusinessLogic.registrationNumberPageBackBtnValidation_RG_TC_31();
    }

    @Test(priority = 26)
    public void registrationOTPPageBackBtnValidation_RG_TC_32() throws Exception {
        MLWalletBusinessLogic.registrationOTPPageBackBtnValidation_RG_TC_32();
    }

    @Test(priority = 27)
    public void registrationPersonalInfoPageBackBtnValidationRG_TC_33() throws Exception {
        MLWalletBusinessLogic.registrationPersonalInfoPageBackBtnValidationRG_TC_33();
    }

    @Test(priority = 28)
    public void registrationAddressPageUIValidation_RG_TC_34() throws Exception {
        MLWalletBusinessLogic.registrationAddressPageUIValidation_RG_TC_34();
    }

    @Test(priority = 29)
    public void registrationAddressPageBackBtnValidation_RG_TC_35() throws Exception {
        MLWalletBusinessLogic.registrationAddressPageBackBtnValidation_RG_TC_35();
    }

    @Test(priority = 30)
    public void registrationTermsAndConditionPageBackBtnValidation_RG_TC_37() throws Exception {
        MLWalletBusinessLogic.registrationTermsAndConditionPageBackBtnValidation_RG_TC_37();
    }

}
