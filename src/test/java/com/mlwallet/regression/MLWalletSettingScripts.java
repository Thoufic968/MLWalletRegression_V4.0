package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.*;

public class MLWalletSettingScripts {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;



    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletSettingScripts.deviceName=deviceName;
        MLWalletSettingScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }
//=========================================================================================================//

    @Test(priority = 1)
    public void accountDetails_SS_TC_01() throws Exception {
        MLWalletBusinessLogic.settingsAccountDetailsValidation_SS_TC_01();
    }

    @Test(priority = 2)
    public void settingsValidMLPinValidation_SS_TC_02() throws Exception {
        MLWalletBusinessLogic.settingsValidMLPinValidation_SS_TC_02();
    }

    @Test(priority = 3)
    public void settingsInvalidMLPinValidation_SS_TC_03() throws Exception {
        MLWalletBusinessLogic.settingsInvalidMLPinValidation_SS_TC_03();
    }

    @Test(priority = 4)
    public void settingsBiometricsLogin_SS_TC_04() throws Exception {
        MLWalletBusinessLogic.settingsBiometricsLogin_SS_TC_04();
    }

    @Test(priority = 5)
    public void settingAccRecovery_SS_TC_05() throws Exception {
        MLWalletBusinessLogic.settingAccRecovery_SS_TC_05();
    }


    @Test(priority = 6)
    public void settingsPageUIValidation_SS_TC_06() throws Exception {
        MLWalletBusinessLogic.settingsPageUIValidation_SS_TC_06();
    }


    @Test(priority = 7)
    public void settingsBackBtnValidation_SS_TC_07() throws Exception {
        MLWalletBusinessLogic.settingsBackBtnValidation_SS_TC_07();
    }


    @Test(priority = 8)
    public void settingsDeleteAccountPopUpUIValidation_SS_TC_10() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountPopUpUIValidation_SS_TC_10();
    }
    @Test(priority = 9)
    public void settingsChangeMLPinNavigation_SS_TC_12() throws Exception {
        MLWalletBusinessLogic.settingsChangeMLPinNavigation_SS_TC_12();
    }

    @Test(priority = 10)
    public void settingsChangeMlPinBackBtnValidation_SS_TC_13() throws Exception {
        MLWalletBusinessLogic.settingsChangeMlPinBackBtnValidation_SS_TC_13();
    }

    @Test(priority = 11)
    public void settingsAccountDetailsNavigation_SS_TC_14() throws Exception {
        MLWalletBusinessLogic.settingsAccountDetailsNavigation_SS_TC_14();
    }

    @Test(priority = 12)
    public void settingsAccountDetailsBackBtnValidation_SS_TC_15() throws Exception {
        MLWalletBusinessLogic.settingsAccountDetailsBackBtnValidation_SS_TC_15();
    }

    @Test(priority = 13)
    public void settingChangeMLPinUIValidation_SS_TC_16() throws Exception {
        MLWalletBusinessLogic.settingChangeMLPinUIValidation_SS_TC_16();
    }

    @Test(priority = 14)
    public void settingsAccountRecoveryUIValidation_SS_TC_17() throws Exception {
        MLWalletBusinessLogic.settingsAccountRecoveryUIValidation_SS_TC_17();
    }

    @Test(priority = 15)
    public void settingsDeleteAccountCancelBtnValidation_SS_TC_18() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountCancelBtnValidation_SS_TC_18();
    }

    @Test(priority = 16)
    public void settingsBiometricsLoginUIValidation_SS_TC_20() throws Exception {
        MLWalletBusinessLogic.settingsBiometricsLoginUIValidation_SS_TC_20();
    }

    @Test(priority = 17)
    public void settingsAccountRecoveryNavigation_SS_TC_21() throws Exception {
        MLWalletBusinessLogic.settingsAccountRecoveryNavigation_SS_TC_21();
    }

    @Test(priority = 18)
    public void settingsTroubleSigningInBackBtnValidation_SS_TC_22() throws Exception {
        MLWalletBusinessLogic.settingsTroubleSigningInBackBtnValidation_SS_TC_22();
    }

    @Test(priority = 19)
    public void settingsBiometricsLoginNavigation_SS_TC_23() throws Exception {
        MLWalletBusinessLogic.settingsBiometricsLoginNavigation_SS_TC_23();
    }

    @Test(priority = 20)
    public void settingsBiometricsLoginBackBtnValidation_SS_TC_24() throws Exception {
        MLWalletBusinessLogic.settingsBiometricsLoginBackBtnValidation_SS_TC_24();
    }

    @Test(priority = 21)
    public void settingsDeleteAccountPageUIValidation_SS_TC_25() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountPageUIValidation_SS_TC_25();
    }

    @Test(priority = 22)
    public void settingsDeleteAccountIWantToStayButtonFunctionality_SS_TC_26() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountIWantToStayButtonFunctionality_SS_TC_26();
    }

    @Test(priority = 23)
    public void settingsDeleteAccountPageBackButtonValidation_SS_TC_27() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountPageBackButtonValidation_SS_TC_27();
    }

    @Test(priority = 24)
    public void settingsDeleteAccountButtonFunctionality_SS_TC_28() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountButtonFunctionality_SS_TC_28();
    }

    @Test(priority = 25)
    public void settingsDeleteAccountConfirmationPopupCancelBtnFunctionality_SS_TC_29() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountConfirmationPopupCancelBtnFunctionality_SS_TC_29();
    }

    @Test(priority = 26)
    public void settingsDeleteAccountConfirmationPopupProceedBtnFunctionality_SS_TC_30() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountConfirmationPopupProceedBtnFunctionality_SS_TC_30();
    }

    @Test(priority = 27)
    public void settingsDeleteAccountMLPinPageUIValidation_SS_TC_31() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountMLPinPageUIValidation_SS_TC_31();
    }

    @Test(priority = 28)
    public void settingsDeleteAccountMLPinPageBackArrowBtnFunctionality_SS_TC_32() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountMLPinPageBackArrowBtnFunctionality_SS_TC_32();
    }

    @Test(priority = 29)
    public void settingsDeleteAccountInvalidMLPin_SS_TC_34() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountInvalidMLPin_SS_TC_34();
    }

    @Test(priority = 30)
    public void settingsDeleteAccountInvalidMLPinPopupOkBtnFunctionality_SS_TC_35() throws Exception {
        MLWalletBusinessLogic.settingsDeleteAccountInvalidMLPinPopupOkBtnFunctionality_SS_TC_35();
    }
    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }






}
