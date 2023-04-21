package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletCashInViaBankScripts {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletCashInViaBankScripts.deviceName=deviceName;
        MLWalletCashInViaBankScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

//==================================================================================================//


    @Test(priority = 1)
    public void cashInViaBank() throws Exception {
        MLWalletBusinessLogic.cashInViaBank_CIBA_TC_01();
    }

    @Test(priority = 2)
    public void cashInViaBankMinimumTransactionLimit() throws Exception {
        MLWalletBusinessLogic.cashInViaBankMinimumTransactionLimit_CIBA_TC_03();
    }

    @Test(priority = 3)
    public void cashInViaBankMaximumTransaction() throws Exception {
        MLWalletBusinessLogic.cashInViaBankMaximumTransaction_CIBA_TC_04();
    }


//=========================================================================================================//

    @Test(priority = 4)
    public void cashInViaBankInvalidAmount() throws Exception {
        MLWalletBusinessLogic.cashInViaBankInvalidAmount_STW_TC_05();
    }

    @Test(priority = 5)
    public void cashInViaBankNavigation_STW_TC_06() throws Exception {
        MLWalletBusinessLogic.cashInViaBankNavigation_STW_TC_06();
    }

    @Test(priority = 6)
    public void cashInUIValidation_STW_TC_07() throws Exception {
        MLWalletBusinessLogic.cashInUIValidation_STW_TC_07();
    }

    @Test(priority = 7)
    public void cashInPageBackArrowBtnValidation_STW_TC_08() throws Exception {
        MLWalletBusinessLogic.cashInPageBackArrowBtnValidation_STW_TC_08();
    }

    @Test(priority = 8)
    public void cashInSelectBankPageUIValidation_STW_TC_09() throws Exception {
        MLWalletBusinessLogic.cashInSelectBankPageUIValidation_STW_TC_09();
    }

    @Test(priority = 9)
    public void cashInViaBankSearchInvalidBank_STW_TC_10() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSearchInvalidBank_STW_TC_10();
    }

    @Test(priority = 10)
    public void cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11();
    }

    @Test(priority = 11)
    public void cashInViaBankDragonPayPageUIValidation_STW_TC_12() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayPageUIValidation_STW_TC_12();
    }

    @Test(priority = 12)
    public void cashInViaBankDragonPayBackBtnValidation_STW_TC_13() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayBackBtnValidation_STW_TC_13();
    }

    @Test(priority = 13)
    public void cashInViaBankReviewTransactionPageUIValidation_STW_TC_14() throws Exception {
        MLWalletBusinessLogic.cashInViaBankReviewTransactionPageUIValidation_STW_TC_14();
    }

    @Test(priority = 14)
    public void cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15() throws Exception {
        MLWalletBusinessLogic.cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15();
    }

    @Test(priority = 15)
    public void cashInViaBankPendingTransaction_CIBA_TC_17() throws Exception {
        MLWalletBusinessLogic.cashInViaBankPendingTransaction_CIBA_TC_17();
    }

    @Test(priority = 16)
    public void cashInViaBankWithExistingPendingTransaction_CIBA_TC_20() throws Exception {
        MLWalletBusinessLogic.cashInViaBankWithExistingPendingTransaction_CIBA_TC_20();
    }

    @Test(priority = 17)
    public void cancelButtonValidationInDragonPayPopUp_CIBA_TC_21() throws Exception {
        MLWalletBusinessLogic.cancelButtonValidationInDragonPayPopUp_CIBA_TC_21();
    }

    @Test(priority = 18)
    public void cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23() throws Exception {
        MLWalletBusinessLogic.cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23();
    }

    @Test(priority = 19)
    public void cashInViaBankBuyerTierLevel_CIBA_TC_24() throws Exception {
        MLWalletBusinessLogic.cashInViaBankBuyerTierLevel_CIBA_TC_24();
    }

    @Test(priority = 20)
    public void cashInViaBankMaxLimit_CIBA_TC_27() throws Exception {
        MLWalletBusinessLogic.cashInViaBankSemiVerifiedUserMaxLimit_CIBA_TC_27();
    }

    @Test(priority = 21)
    public void cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28() throws Exception {
        MLWalletBusinessLogic.cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28();
    }

    @Test(priority = 22)
    public void cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29() throws Exception {
        MLWalletBusinessLogic.cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29();
    }


    @Test(priority = 23)
    public void cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32() throws Exception {
        MLWalletBusinessLogic.cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32();
    }







}
