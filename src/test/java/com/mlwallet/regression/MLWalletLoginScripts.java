package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.DriverInstance;
import com.driverInstance.DriverManager;
import com.mlwallet.pages.MLWalletLoginPage;
import org.testng.annotations.*;
import com.driverInstance.AppiumServer;
import static com.business.mlwallet.MLWalletBusinessLogic.prop;
import static com.utility.Utilities.setWifiConnectionToONOFF;

public class MLWalletLoginScripts {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;



    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletLoginScripts.deviceName=deviceName;
        MLWalletLoginScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

//====================================================================================================//

    @Test(priority = 1)
    public void LogInScenarioWithValidMobNumber() throws Exception
    {
        MLWalletBusinessLogic.logInScenarioWithValidMobNumber_Lgn_TC_01();
    }
    @Test(priority = 2)
    public void LogInScenarioWithInvalidMobNumber() throws Exception {
        MLWalletBusinessLogic.logInScenarioWithInvalidMobNumber_Lgn_TC_02();
    }
    @Test(priority = 3)
    public void LogInScenarioWithValidOTP() throws Exception {
        MLWalletBusinessLogic.logInScenarioWithValidOTP_Lgn_TC_03();
    }
//==============================================================================================================//
//


    @Test(priority = 4)
    public void appLaunch_Lgn_TC_05() throws Exception {
        MLWalletBusinessLogic.appLaunch_Lgn_TC_05();
    }

    @Test(priority = 5)
    public void loginPageUIValidation_Lgn_TC_06() throws Exception {
        MLWalletBusinessLogic.loginPageUIValidation_Lgn_TC_06();
    }

    @Test(priority = 6)
    public void loginTroubleSigningIn_Lgn_TC_07() throws Exception {
        MLWalletBusinessLogic.loginTroubleSigningIn_Lgn_TC_07();
    }

    @Test(priority = 7)
    public void loginCreateOne_Lgn_TC_08() throws Exception {
        MLWalletBusinessLogic.loginCreateOne_Lgn_TC_08();
    }

    @Test(priority = 8)
    public void loginBranchLocator_Lgn_TC_09() throws Exception {
        MLWalletBusinessLogic.loginBranchLocator_Lgn_TC_09();
    }

    @Test(priority = 9)
    public void loginOTPPageUIValidation_Lgn_TC_10() throws Exception {
        MLWalletBusinessLogic.loginOTPPageUIValidation_Lgn_TC_10();
    }


    @Test(priority = 10)
    public void logInScenarioWithInvalidMobNumber_Lgn_TC_15() throws Exception {
        MLWalletBusinessLogic.logInScenarioWithInvalidMobNumber_Lgn_TC_15();
    }


    @Test(priority = 11)
    public void loginWithExistingMobileNumber_Lgn_TC_17() throws Exception {
        MLWalletBusinessLogic.loginWithExistingMobileNumber_Lgn_TC_17();
    }

    @Test(priority = 12)
    public void loginMPinPageUIValidation_Lgn_TC_18() throws Exception {
        MLWalletBusinessLogic.loginMPinPageUIValidation_Lgn_TC_18();
    }

    @Test(priority = 13)
    public void loginNetworkInterruptionValidation_Lgn_TC_19() throws Exception {
        MLWalletBusinessLogic.loginNetworkInterruptionWhileLoggingInValidation_Lgn_TC_19(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 14)
    public void loginInternetInterruptionWhileLaunchingApp_Lgn_TC_20() throws Exception {
        MLWalletBusinessLogic.loginInternetInterruptionWhileLaunchingApp_Lgn_TC_20();
        setWifiConnectionToONOFF("ON");
    }

    @Test(priority = 15)
    public void loginInAppOTPNavigation_Lgn_TC_22() throws Exception {
        setWifiConnectionToONOFF("ON");
        MLWalletBusinessLogic.loginInAppOTPNavigation_Lgn_TC_22();
    }

    @Test(priority = 16)
    public void loginInAppOTPPageUIValidation_Lgn_TC_23() throws Exception {
        MLWalletBusinessLogic.loginInAppOTPPageUIValidation_Lgn_TC_23();
    }

    @Test(priority = 17)
    public void loginNewOTPAfterSixtySecondsValidation_Lgn_TC_24() throws Exception {
        MLWalletBusinessLogic.loginNewOTPAfterSixtySecondsValidation_Lgn_TC_24();
    }

    @Test(priority = 18)
    public void loginOTPCancelBtnFunctionality_Lgn_TC_25() throws Exception {
        MLWalletBusinessLogic.loginOTPCancelBtnFunctionality_Lgn_TC_25();
    }

    @Test(priority = 19)
    public void loginOTPContinueBtnFunctionality_Lgn_TC_26() throws Exception {
        MLWalletBusinessLogic.loginOTPContinueBtnFunctionality_Lgn_TC_26();
    }

    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }

}
