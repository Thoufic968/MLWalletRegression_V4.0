package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletSendTransferToMLWalletUserScripts {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletSendTransferToMLWalletUserScripts.deviceName=deviceName;
        MLWalletSendTransferToMLWalletUserScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }


//===========================================================================================================//

    @Test(priority = 1)
    public void sendToMLWalletUser() throws Exception
    {
        MLWalletBusinessLogic.sendToMLWalletUser_STW_TC_01();
    }

    @Test(priority = 2)
    public void sendMoneyAddToFavorites_STW_TC_12() throws Exception {
        MLWalletBusinessLogic.sendMoneyAddToFavorites_STW_TC_12();
    }

    @Test(priority = 3)
    public void sendMoneyMLWalletToExistingReceiver() throws Exception
    {
        MLWalletBusinessLogic.sendMoneyMLWalletToExistingReceiver_STW_TC_02();

    }

    @Test(priority = 4)
    public void sendToMLWalletInvalidMobNumber() throws Exception
    {
        MLWalletBusinessLogic.sendToMLWalletInvalidMobNumber_STW_TC_03();
    }

    @Test(priority = 5)
    public void sendToMLWalletUnRegisteredNumber() throws Exception
    {
        MLWalletBusinessLogic.sendToMLWalletUnRegisteredNumber_STW_TC_04();
    }

    @Test(priority = 6)
    public void sendToMLWalletInvalidAmount() throws Exception
    {
        MLWalletBusinessLogic.sendToMLWalletInvalidAmount_STW_TC_05("0");
    }

    @Test(priority = 7)
    public void sendToMLWalletInsufficientAmount() throws Exception
    {
        MLWalletBusinessLogic.sendToMLWalletInsufficientAmount_STW_TC_06();
    }

    @Test(priority = 8)
    public void sendMoneyMLWalletMaximumAmount() throws Exception
    {
        MLWalletBusinessLogic.sendMoneyMLWalletMaximumAmount_STW_TC_07();
    }


//================================================================================================//

    @Test(priority = 9)
      public void sendMoneyDeleteFromFavorites() throws Exception
    {
        MLWalletBusinessLogic.sendMoneyDeleteFromFavorites_STW_TC_09();
    }

    @Test(priority = 10)
    public void sendMoneyMLWalletUIValidation_STW_TC_10() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletUIValidation_STW_TC_10();
    }

    @Test(priority = 11)
    public void sendMoneyFavoritesUIValidation_STW_TC_11() throws Exception {
        MLWalletBusinessLogic.sendMoneyFavoritesUIValidation_STW_TC_11();
    }

    @Test(priority = 12)
    public void sendMoneyMLWalletCancelTransaction_STW_TC_13() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletCancelTransaction_STW_TC_13("100");
    }

    @Test(priority = 13)
    public void sendMoneyMLWalletSearchUnFavoriteUser_STW_TC_14() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletSearchUnFavoriteUser_STW_TC_14();
    }

    @Test(priority = 14)
    public void sendMoneyMLWalletSearchFavoriteUser_STW_TC_15() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletSearchFavoriteUser_STW_TC_15();
    }

    @Test(priority = 15)
    public void SendMoneyMLWalletSuccessPageUIValidation_STW_TC_16() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletSuccessPageUIValidation_STW_TC_16();
    }

    @Test(priority = 16)
    public void sendMoneyMLWalletOTPPageUIValidation_STW_TC_16() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletOTPPageUIValidation_STW_TC_17();
    }

    @Test(priority = 17)
    public void sendMoneyMLWalletConfirmDetailsPageUIValidation_STW_TC_18() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletConfirmDetailsPageUIValidation_STW_TC_18("100");
    }

    @Test(priority = 18)
    public void sendMoneyToMlWalletEnterAmountPageUIValidation_STW_TC_19() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMlWalletEnterAmountPageUIValidation_STW_TC_19();
    }

    @Test(priority = 19)
    public void sendMoneyToMLWalletPageUIValidation_STW_TC_20() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLWalletPageUIValidation_STW_TC_20();
    }

    @Test(priority = 20)
    public void sendMoneyMlWalletTransactionDetailsUIValidation_STW_TC_21() throws Exception {
        MLWalletBusinessLogic.sendMoneyMlWalletTransactionDetailsUIValidation_STW_TC_21();
    }


    @Test(priority = 21)
    public void sendMoneyMLWalletBuyerTierAccountUser_STW_TC_22() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletBuyerTierAccountUser_STW_TC_22();
    }

    @Test(priority = 22)
    public void sendMoneyMLWalletSemiVerifiedAccountUser_STW_TC_23() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletSemiVerifiedAccountUser_STW_TC_23();
    }

    @Test(priority = 23)
    public void sendMoneyMLWalletBranchVerifiedAccountUser_STW_TC_24() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletBranchVerifiedAccountUser_STW_TC_24();
    }

    @Test(priority = 24)
    public void sendMoneyMLWalletFullyVerifiedAccountUser_STW_TC_25() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletFullyVerifiedAccountUser_STW_TC_25();
    }

    @Test(priority = 25)
    public void sendMoneyMlWalletSemiVerifiedAccountMaxLimit_STW_TC_26() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletSemiVerifiedAccountMaxLimit_STW_TC_26();
    }

    @Test(priority = 26)
    public void sendMoneyMlWalletBranchVerifiedAccountMaxLimit_STW_TC_29() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletBranchVerifiedAccountMaxLimit_STW_TC_29();
    }

    @Test(priority = 27)
    public void sendMoneyMLWalletFullyVerifiedAccountMaxLimit_STW_TC_32() throws Exception {
        MLWalletBusinessLogic.sendMoneyMLWalletFullyVerifiedAccountMaxLimit_STW_TC_32();
    }

    @Test(priority = 28)
    public void sendMoneyToMLWalletSuccessMsgValidation_STW_TC_35() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLWalletSuccessMsgValidation_STW_TC_35();
    }

//    @Test(priority = 29)
//    public void sendMoneyToMLWalletMaxTransactionReceivingLimitSemiVerifiedTier_STW_TC_36() throws Exception {
//        MLWalletBusinessLogic.sendMoneyToMLWalletMaxTransactionReceivingLimitSemiVerifiedTier_STW_TC_36("50000");
//    }












}
