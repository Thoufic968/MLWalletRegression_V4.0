package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletTroubleSingingIn {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletTroubleSingingIn.deviceName=deviceName;
        MLWalletTroubleSingingIn.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

    @Test(priority = 1)
    public void troubleSigningInPageNavigationFromMpinScreen_TS_TC_01() throws Exception {
        MLWalletBusinessLogic.troubleSigningInPageNavigationFromMpinScreen_TS_TC_01();
    }

    @Test(priority = 2)
    public void troubleSigningInPageNavigationFromLoginScreen_TS_TC_10() throws Exception {
        MLWalletBusinessLogic.troubleSigningInPageNavigationFromLoginScreen_TS_TC_10();
    }

    @Test(priority = 3)
    public void troubleSigningInPageBackArrowBtnFunctionality_TS_TC_11() throws Exception {
        MLWalletBusinessLogic.troubleSigningInPageBackArrowBtnFunctionality_TS_TC_11();
    }

    @Test(priority = 4)
    public void troubleSigningInClearFormFunctionality_TS_TC_12() throws Exception {
        MLWalletBusinessLogic.troubleSigningInClearFormFunctionality_TS_TC_12();
    }

    @Test(priority = 5)
    public void troubleSigningInClearFormButtonOnClearFormPopupFunctionality_TS_TC_13() throws Exception {
        MLWalletBusinessLogic.troubleSigningInClearFormButtonOnClearFormPopupFunctionality_TS_TC_13();
    }

    @Test(priority = 6)
    public void troubleSigningInCancelButtonOnClearFormPopupFunctionality_TS_TC_14() throws Exception {
        MLWalletBusinessLogic.troubleSigningInCancelButtonOnClearFormPopupFunctionality_TS_TC_14();
    }


    @Test(priority = 7)
    public void troubleSigningInEmptyFullNameFunctionality_TS_TC_15() throws Exception {
        MLWalletBusinessLogic.troubleSigningInEmptyFullNameFunctionality_TS_TC_15();
    }

    @Test(priority = 8)
    public void troubleSigningInEmptyRegisteredEmailFunctionality_TS_TC_16() throws Exception {
        MLWalletBusinessLogic.troubleSigningInEmptyRegisteredEmailFunctionality_TS_TC_16();
    }

    @Test(priority = 9)
    public void troubleSigningInEmptyRegisteredMobileNumberFunctionality_TS_TC_17() throws Exception {
        MLWalletBusinessLogic.troubleSigningInEmptyRegisteredMobileNumberFunctionality_TS_TC_17();
    }




}
