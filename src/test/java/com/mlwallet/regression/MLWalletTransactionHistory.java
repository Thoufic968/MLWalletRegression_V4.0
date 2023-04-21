package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletTransactionHistory {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletTransactionHistory.deviceName=deviceName;
        MLWalletTransactionHistory.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }
//===================================================================================================//


    @Test(priority = 1)
    public void mlWalletTransactionHistoryScenario__TH_TC_01() throws Exception
    {
        MLWalletBusinessLogic.mlWallet_TransactionHistory_TH_TC_01();
    }

    @Test(priority = 2)
    public void billsPayTransactionHHistory_TH_TC_02() throws Exception {
        MLWalletBusinessLogic.billsPayTransactionHHistory_TH_TC_02();
    }

    @Test(priority = 3)
    public void buyLoadTransactionHistory_TH_TC_03() throws Exception {
        MLWalletBusinessLogic.buyLoadTransactionHistory_TH_TC_03();
    }

    @Test(priority = 4)
    public void sendMoneyTransactionHistory_TH_TC_04() throws Exception {
        MLWalletBusinessLogic.sendMoneyTransactionHistory_TH_TC_04();
    }

    @Test(priority = 5)
    public void cashInTransactionHistory_TH_TC_05() throws Exception {
        MLWalletBusinessLogic.cashInTransactionHistory_TH_TC_05();
    }

    @Test(priority = 6)
    public void cashOutTransactionHistory_TH_TC_06() throws Exception {
        MLWalletBusinessLogic.cashOutTransactionHistory_TH_TC_06();
    }

    @Test(priority = 7)
    public void receiveMoneyTransactionHistory_TH_TC_07() throws Exception {
        MLWalletBusinessLogic.receiveMoneyTransactionHistory_TH_TC_07();
    }

    @Test(priority = 8)
    public void balanceAdjustmentTransactionHistory_TH_TC_08() throws Exception {
        MLWalletBusinessLogic.balanceAdjustmentTransactionHistory_TH_TC_08();
    }

    @Test(priority = 9)
    public void mlShopTransactionHistory_TH_TC_09() throws Exception {
        MLWalletBusinessLogic.mlShopTransactionHistory_TH_TC_09();
    }


//======================================================================================================//

    @Test(priority = 10)
    public void transactionHistoryUIValidation() throws Exception {
        MLWalletBusinessLogic.transactionHistoryUIValidation_TH_TC_10();
    }

    @Test(priority = 11)
    public void transactionHistoryBillsPayTransactionDetailsValidation_TH_TC_13() throws Exception {
        MLWalletBusinessLogic.transactionHistoryBillsPayTransactionDetailsValidation_TH_TC_13();
    }

    @Test(priority = 12)
    public void transactionHistoryELoadTransactionDetailsValidation_TH_TC_14() throws Exception {
        MLWalletBusinessLogic.transactionHistoryELoadTransactionDetailsValidation_TH_TC_14();
    }

    @Test(priority = 13)
    public void transactionHistorySendMoneyWalletToWalletTransactionDetailsValidation_TH_TC_15() throws Exception {
        MLWalletBusinessLogic.transactionHistorySendMoneyWalletToWalletTransactionDetailsValidation_TH_TC_15();
    }

    @Test(priority = 14)
    public void transactionHistorySendMoneyKwartaPadalaTransactionDetailsValidation_TH_TC_16() throws Exception {
        MLWalletBusinessLogic.transactionHistorySendMoneyKwartaPadalaTransactionDetailsValidation_TH_TC_16();
    }

    @Test(priority = 15)
    public void transactionHistoryCashInTransactionDetailsValidation_TH_TC_17() throws Exception {
        MLWalletBusinessLogic.transactionHistoryCashInTransactionDetailsValidation_TH_TC_17();
    }

    @Test(priority = 16)
    public void transactionHistoryCashOutTransactionDetailsValidation_TH_TC_18() throws Exception {
        MLWalletBusinessLogic.transactionHistoryCashOutTransactionDetailsValidation_TH_TC_18();
    }

    @Test(priority = 17)
    public void transactionHistoryReceiveMoneyTransactionDetailsValidation_TH_TC_19() throws Exception {
        MLWalletBusinessLogic.transactionHistoryReceiveMoneyTransactionDetailsValidation_TH_TC_19();
    }

    @Test(priority = 18)
    public void transactionHistoryMLShopTransactionDetailsValidation_TH_TC_20() throws Exception {
        MLWalletBusinessLogic.transactionHistoryMLShopTransactionDetailsValidation_TH_TC_20();
    }









}
