package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletTierUpgrade {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletTierUpgrade.deviceName=deviceName;
        MLWalletTierUpgrade.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

    @Test(priority = 1)
    public void tierUpgradeHomePageIIconValidationAsBuyerTierUser_TU_TC_01() throws Exception {
        MLWalletBusinessLogic.tierUpgradeHomePageIIconValidationAsBuyerTierUser_TU_TC_01();
    }

    @Test(priority = 2)
    public void tierUpgradeHomePageIIconValidationAsSemiVerifiedTierUser_TU_TC_02() throws Exception {
        MLWalletBusinessLogic.tierUpgradeHomePageIIconValidationAsSemiVerifiedTierUser_TU_TC_02();
    }

    @Test(priority = 3)
    public void tierUpgradeHomePageIIconValidationAsFullyVerifiedTierUser_TU_TC_03() throws Exception {
        MLWalletBusinessLogic.tierUpgradeHomePageIIconValidationAsFullyVerifiedTierUser_TU_TC_03();
    }

    @Test(priority = 4)
    public void tierUpgradeVerificationTierPerksPageNavigationAsBuyerTierUser_TU_TC_04() throws Exception {
        MLWalletBusinessLogic.tierUpgradeVerificationTierPerksPageNavigationAsBuyerTierUser_TU_TC_04();
    }

    @Test(priority = 5)
    public void tierUpgradeVerificationTierPerksPageNavigationAsSemiVerifiedTierUser_TU_TC_05() throws Exception {
        MLWalletBusinessLogic.tierUpgradeVerificationTierPerksPageNavigationAsSemiVerifiedTierUser_TU_TC_05();
    }


    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }





}
