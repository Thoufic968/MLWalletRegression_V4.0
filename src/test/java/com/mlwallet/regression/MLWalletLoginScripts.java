package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.mlwallet.pages.MLWalletLoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletLoginScripts {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
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

}
