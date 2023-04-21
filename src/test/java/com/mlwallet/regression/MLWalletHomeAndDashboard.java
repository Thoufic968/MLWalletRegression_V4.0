package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletHomeAndDashboard {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletHomeAndDashboard.deviceName=deviceName;
        MLWalletHomeAndDashboard.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

// ====================================================================================================//


    @Test(priority = 1)
    public void mlWalletHomePageUIValidation() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageUIValidation_HD_TC_01();
    }

    @Test(priority = 2)
    public void mlWalletLogOutFromHomePage_HD_TC_02() throws Exception {
        MLWalletBusinessLogic.mlWalletLogOutFromHomePage_HD_TC_02();
    }

    @Test(priority = 3)
    public void mlWalletSettingsNavigationFromHomePage_HD_TC_03() throws Exception {
        MLWalletBusinessLogic.mlWalletSettingsNavigationFromHomePage_HD_TC_03();
    }

    @Test(priority = 4)
    public void mlWalletHomePageEyeIconValidation_HD_TC_04() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageEyeIconValidation_HD_TC_04();
    }

    @Test(priority = 5)
    public void mlWalletAccountTierLevelVerification_HD_TC_05() throws Exception {
        MLWalletBusinessLogic.mlWalletAccountTierLevelVerification_HD_TC_05();
    }

    @Test(priority = 6)
    public void mlWalletHomePageRecentTransaction_HD_TC_06() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageRecentTransaction_HD_TC_06();
    }

    @Test(priority = 7)
    public void mlWalletHomePageWithdrawCash_HD_TC_07() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageWithdrawCash_HD_TC_07();
    }

    @Test(priority = 8)
    public void mlWalletHomePageTopUpMLWallet_HD_TC_08() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageTopUpMLWallet_HD_TC_08();
    }

    @Test(priority = 9)
    public void mlWalletHomePageShopHD_TC_09() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageShopHD_TC_09();
    }

    @Test(priority = 10)
    public void mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10();
    }

    @Test(priority = 11)
    public void mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11();
    }

    @Test(priority = 12)
    public void mlWalletSupportPageNavigation_HD_TC_12() throws Exception {
        MLWalletBusinessLogic.mlWalletSupportPageNavigation_HD_TC_12();
    }

    @Test(priority = 13)
    public void mlWalletAboutPageNavigation_HD_TC_13() throws Exception {
        MLWalletBusinessLogic.mlWalletAboutPageNavigation_HD_TC_13();
    }

    @Test(priority = 14)
    public void mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14();
    }

    @Test(priority = 15)
    public void mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15();
    }
    @Test(priority = 16)
    public void mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17();
    }

    @Test(priority = 17)
    public void mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18();
    }

    @Test(priority = 18)
    public void mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19() throws Exception {
        MLWalletBusinessLogic.mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19();
    }

    @Test(priority = 19)
    public void mlWalletHomePageIIconValidationAsBranchVerifiedUser_HD_TC_20() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsBranchVerifiedTierUser_HD_TC_20();
    }

    @Test(priority = 20)
    public void mlWalletHomePageIIconValidationAsBuyerTierVerifiedUser_HD_TC_21() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsBuyerTierUser_HD_TC_21();
    }

    @Test(priority = 21)
    public void mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22();
    }

    @Test(priority = 22)
    public void mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23();
    }


}
