package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import com.mlwallet.pages.MLWalletPayBillsPage;
import org.testng.annotations.*;

public class MLWalletRegressionPayBillsScripts {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;



    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletRegressionPayBillsScripts.deviceName=deviceName;
        MLWalletRegressionPayBillsScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }


//============================== Pay Bills Scripts==========================================================//


//    @Test(priority = 1)
//    public void payBillsPageValidation_PB_TC_01() throws Exception {
//        MLWalletBusinessLogic.payBillsPageValidation_PB_TC_01();
//    }
//
//    @Test(priority = 2)
//    public void billerCategories_PB_TC_02() throws Exception {
//        MLWalletBusinessLogic.billerCategories_PB_TC_02();
//    }
//
//    @Test(priority = 3)
//    public void billersInAlphabeticalOrder_PB_TC_03() throws Exception {
//        MLWalletBusinessLogic.billersInAlphabeticalOrder_PB_TC_03();
//    }
//
//    @Test(priority = 4)
//    public void selectBiller_PB_TC_04() throws Exception {
//        MLWalletBusinessLogic.selectBiller_PB_TC_04();
//    }
//
//    @Test(priority = 5)
//    public void searchBiller_PB_TC_05() throws Exception {
//        MLWalletBusinessLogic.searchBiller_PB_TC_05();
//    }
//
//    @Test(priority = 6)
//    public void billingInformationInput_PB_TC_06() throws Exception {
//        MLWalletBusinessLogic.billingInformationInput_PB_TC_06();
//    }
//
//
//   @Test(priority = 7)
//    public void payBillsWithValidInputs_PB_TC_07() throws Exception {
//        MLWalletBusinessLogic.payBillsWithValidInputs_PB_TC_07();
//    }
//
//    @Test(priority = 8)
//    public void payBillsInRecentTransactions_PB_TC_08() throws Exception {
//        MLWalletBusinessLogic.payBillsInRecentTransactions_PB_TC_08();
//    }
//
//    @Test(priority = 9)
//    public void payBillsInsufficientBalance_PB_TC_09() throws Exception {
//        MLWalletBusinessLogic.payBillsInsufficientBalance_PB_TC_09();
//    }
//
//    @Test(priority = 10)
//    public void billingInformationInvalidInput_PB_TC_10() throws Exception {
//        MLWalletBusinessLogic.billingInformationInvalidInput_PB_TC_10();
//    }
//
//
//   @Test(priority = 11)
//    public void addBillerToPayBills_PB_TC_12() throws Exception {
//        MLWalletBusinessLogic.addBillerToPayBills_PB_TC_12();
//    }
//
//
//
//    @Test(priority = 12)
//    public void editAddedBillerToPayBills_PB_TC_14() throws Exception {
//        MLWalletBusinessLogic.editAddedBillerToPayBills_PB_TC_14();
//    }
//
//
//    @Test(priority = 13)
//    public void deleteAddedBillerPayBills_PB_TC_15() throws Exception {
//        MLWalletBusinessLogic.deleteAddedBillerPayBills_PB_TC_15();
//    }
//
////============================================================================================================//
//
//
//    @Test(priority = 14)
//    public void payBillsUIValidation_PB_TC_16() throws Exception {
//        MLWalletBusinessLogic.payBillsUIValidation_PB_TC_16();
//    }
//
//    @Test(priority = 15)
//    public void payBillsAddBillerPageUIValidation_PB_TC_18() throws Exception {
//        MLWalletBusinessLogic.payBillsAddBillerPageUIValidation_PB_TC_18();
//    }
//
//    @Test(priority = 16)
//    public void paybillsRecentTransaction_PB_TC_19() throws Exception {
//        MLWalletBusinessLogic.paybillsRecentTransaction_PB_TC_19();
//    }
//
//    @Test(priority = 17)
//    public void payBillsSavedBilerUIValidation_PB_TC_20() throws Exception {
//        MLWalletBusinessLogic.payBillsSavedBilerUIValidation_PB_TC_20();
//    }
//
//    @Test(priority = 18)
//    public void payBillsMaxBillsPaymentPerTransactionBuyTierUser_PB_TC_22() throws Exception {
//        MLWalletBusinessLogic.payBillsMaxBillsPaymentPerTransactionBuyTierUser_PB_TC_22();
//    }
//
//    @Test(priority = 19)
//    public void payBillsMaxBillsPaymentPerTransactionSemiVerifiedTier_PB_TC_25() throws Exception {
//        MLWalletBusinessLogic.payBillsMaxBillsPaymentPerTransactionSemiVerifiedTier_PB_TC_25();
//    }
//
//    @Test(priority = 20)
//    public void payBillsMaxBillsPaymentPerTransactionBranchVerifiedTier_PB_TC_28() throws Exception {
//        MLWalletBusinessLogic.payBillsMaxBillsPaymentPerTransactionBranchVerifiedTier_PB_TC_28();
//    }
//
//    @Test(priority = 21)
//    public void payBillsMaxBillsPaymentPerTransactionFullyVerifiedTier_PB_TC_31() throws Exception {
//        MLWalletBusinessLogic.payBillsMaxBillsPaymentPerTransactionFullyVerifiedTier_PB_TC_31();
//    }
//
//    @Test(priority = 22)
//    public void payBillsRecentTransaction_PB_TC_21() throws Exception {
//        MLWalletBusinessLogic.payBillsRecentTransaction_PB_TC_21();
//    }
//
//    @Test(priority = 23)
//    public void payBillsLocationPopupValidation_PB_TC_34() throws Exception {
//        MLWalletBusinessLogic.payBillsLocationPopupValidation_PB_TC_34();
//    }
//
//    @Test(priority = 24)
//    public void payBillsLocationDenyFunctionality_PB_TC_35() throws Exception {
//        MLWalletBusinessLogic.payBillsLocationDenyFunctionality_PB_TC_35();
//    }
//
//    @Test(priority = 25)
//    public void payBillsLocationPermissionDenyCloseBtnFunctionality_PB_TC_36() throws Exception {
//        MLWalletBusinessLogic.payBillsLocationPermissionDenyCloseBtnFunctionality_PB_TC_36();
//    }
////
//    @Test(priority = 26)
//    public void payBillsLocationPermissionDenyOpenSettingsBtnFunctionality_PB_TC_37() throws Exception {
//        MLWalletBusinessLogic.payBillsLocationPermissionDenyOpenSettingsBtnFunctionality_PB_TC_37();
//    }
//
//    @Test(priority = 27)
//    public void payBillsLocationPopUpAllowFunctionality_PB_TC_38() throws Exception {
//        MLWalletBusinessLogic.payBillsLocationPopUpAllowFunctionality_PB_TC_38();
//    }
//
//    @Test(priority = 28)
//    public void payBillsInternetInterruptionWhileEnteringOTP_PB_TC_39() throws Exception {
//        MLWalletBusinessLogic.payBillsInternetInterruptionWhileEnteringOTP_PB_TC_39();
//    }
//
//    @Test(priority = 29)
//    public void payBillsTransactionValidationAfterMinimizingApp_PB_TC_42() throws Exception {
//        MLWalletBusinessLogic.payBillsTransactionValidationAfterMinimizingApp_PB_TC_42();
//    }
//
    @Test(priority = 30)
    public void payBillsTransactionWithValidMLPin_PB_TC_48() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionWithValidMLPin_PB_TC_48();
    }

    @Test(priority = 31)
    public void payBillsTransactionWithInValidMLPin_PB_TC_49() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionWithInValidMLPin_PB_TC_49();
    }

    @Test(priority = 32)
    public void payBillsInOTPPopupValidation_PB_TC_55() throws Exception {
        MLWalletBusinessLogic.payBillsInOTPPopupValidation_PB_TC_55();
    }

    @Test(priority = 33)
    public void payBillsTransactionInAppOTPPopupUIValidation_PB_TC_56() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionInAppOTPPopupUIValidation_PB_TC_56();
    }

    @Test(priority = 34)
    public void payBillsTransactionNewOTPAfterSixtySecondsValidation_PB_TC_57() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionNewOTPAfterSixtySecondsValidation_PB_TC_57();
    }

    @Test(priority = 35)
    public void payBillsTransactionOTPCancelBtnFunctionality_PB_TC_58() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionOTPCancelBtnFunctionality_PB_TC_58();
    }

    @Test(priority = 36)
    public void payBillsTransactionOTPContinueBtnFunctionality_PB_TC_59() throws Exception {
        MLWalletBusinessLogic.payBillsTransactionOTPContinueBtnFunctionality_PB_TC_59();
    }




    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }






}
