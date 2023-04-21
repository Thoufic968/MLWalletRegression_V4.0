package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletLogoutScripts {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletLogoutScripts.deviceName=deviceName;
        MLWalletLogoutScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

//=======================================================================================================================//


    @Test(priority = 1)
    public void logOutMinimizeAndRelaunch_Lout_TC_03() throws Exception {
        MLWalletBusinessLogic.logOutMinimizeAndRelaunch_Lout_TC_03();
    }

    @Test(priority = 2)
    public void logOutAppKillAndRelaunch() throws Exception {
        MLWalletBusinessLogic.logOutAppKillAndRelaunch_Lout_TC_04();
    }

    @Test(priority = 3)
    public void logOUtPopUpValidation_Lout_TC_05() throws Exception {
        MLWalletBusinessLogic.logOUtPopUpValidation_Lout_TC_05();
    }

    @Test(priority = 4)
    public void logOutPopUpCancelBtnValidation_Lout_TC_06() throws Exception {
        MLWalletBusinessLogic.logOutPopUpCancelBtnValidation_Lout_TC_06();
    }

    @Test(priority = 5)
    public void logOutPopUpLogOutBtnValidation_Lout_TC_07() throws Exception {
        MLWalletBusinessLogic.logOutPopUpLogOutBtnValidation_Lout_TC_07();
    }

    @Test(priority = 6)
    public void logoutChangeNumberUIValidation_Lout_TC_08() throws Exception {
        MLWalletBusinessLogic.logoutChangeNumberUIValidation_Lout_TC_08();
    }

    @Test(priority = 7)
    public void logInWithChangeNumber_Lout_TC_09() throws Exception {
        MLWalletBusinessLogic.logInWithChangeNumber_Lout_TC_09();
    }




}
