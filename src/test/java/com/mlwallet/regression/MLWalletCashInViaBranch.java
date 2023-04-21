package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletCashInViaBranch {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletCashInViaBranch.deviceName=deviceName;
        MLWalletCashInViaBranch.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

//======================================================================================================//


    //******************* Cash In Via Branch ====================================/

    @Test(priority = 1)
    public void cashInviaBranch_ValidAmount_Scenario_CIBR_TC_01() throws Exception
    {
        MLWalletBusinessLogic.cashInviaBranch_ValidAmount_Scenario_CIBR_TC_01();
    }

    @Test(priority = 2)
    public void cashInViaBranchCancelTransactionScenario_CIBR_TC_02() throws Exception
    {
        MLWalletBusinessLogic.cashInViaBranchCancelTransactionScenario_CIBR_TC_02();
    }

    @Test(priority = 3)
    public void cashInviaBranch_Invalid_Amount_CIBR_TC_03() throws Exception
    {
        MLWalletBusinessLogic.cashInviaBranch_Invalid_Amount_CIBR_TC_03();
    }

    @Test(priority = 4)
    public void cashInViaBranch_Maximum_Limit_Amount_CIBR_TC_04() throws Exception
    {
        MLWalletBusinessLogic.cashInViaBranch_Maximum_Limit_Amount_CIBR_TC_04();
    }


//============================================================================================================//


    @Test(priority = 5)
    public void cashInViaBRanchInvalidAmount_CIBR_TC_05() throws Exception {
        MLWalletBusinessLogic.cashInViaBRanchInvalidAmount_CIBR_TC_05();
    }

    @Test(priority = 6)
    public void cashInViaBranchUIValidation_CIBR_TC_06() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchUIValidation_CIBR_TC_06();
    }

    @Test(priority = 7)
    public void cashInViaBranchBackBtnValidation_CIBR_TC_07() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchBackBtnValidation_CIBR_TC_07();
    }

    @Test(priority = 8)
    public void cashInViaBranchNavigationToHomePageFromQRPage_CIBR_TC_08() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchNavigationToHomePageFromQRPage_CIBR_TC_08();
    }

    @Test(priority = 9)
    public void cashInViaBranchQRCodePageUIValidation_CIBR_TC_09() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchQRCodePageUIValidation_CIBR_TC_09();
    }

    @Test(priority = 10)
    public void cashInViaBranchPendingTransaction_CIBR_TC_11() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchPendingTransaction_CIBR_TC_11();
    }

    @Test(priority = 11)
    public void cashInViaBranchCancelBtnValidationOnCashInConfirmPopUp_CIBR_TC_12() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchCancelBtnValidationOnCashInConfirmPopUp_CIBR_TC_12();
    }

    @Test(priority = 12)
    public void cashInViaBranchGoBackBtnValidationOnCashInConfirmPopUp_CIBR_TC_14() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchGoBackBtnValidationOnCashInConfirmPopUp_CIBR_TC_14();
    }

    @Test(priority = 13)
    public void cashInViaBranchMaxTransactionBuyerTierUser_CIBR_TC_19() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchMaxTransactionBuyerTierUser_CIBR_TC_19();
    }

    @Test(priority = 14)
    public void cashInViaBranchBuyerTierUser_CIBR_TC_16() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchBuyerTierUser_CIBR_TC_16();
    }


    @Test(priority = 15)
    public void cashInViaBranchMaxTransactionSemiVerifiedTierUser_CIBR_TC_19() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchMaxTransactionSemiVerifiedTierUser_CIBR_TC_20();
    }

    @Test(priority = 16)
    public void cashInViaBranchSemiVerifiedTierUser_CIBR_TC_17() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchSemiVerifiedTierUser_CIBR_TC_17();
    }

    @Test(priority = 17)
    public void cashInViaBranchMaxTransactionFullyVerifiedTierUser_CIBR_TC_21() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchMaxTransactionFullyVerifiedTierUser_CIBR_TC_21();
    }

    @Test(priority = 18)
    public void cashInViaBranchFullyVerifiedTierUser_CIBR_TC_18() throws Exception {
        MLWalletBusinessLogic.cashInViaBranchFullyVerifiedTierUser_CIBR_TC_18();
    }


}
