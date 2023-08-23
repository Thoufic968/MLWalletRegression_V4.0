package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.*;

public class MLWalletCashOutWithdrawScripts {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;




    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletCashOutWithdrawScripts.deviceName=deviceName;
        MLWalletCashOutWithdrawScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }

//============================================================================================================//


    @Test(priority = 1)
    public void cashOutWithdrawBank_WM_TC_01() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBank_WM_TC_01("100");
    }

    @Test(priority = 2)
    public void cashOutWithInvalidAccNumber_WM_TC_02() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithInvalidAccNumber_WM_TC_02();
    }

    @Test(priority = 3)
    public void cashOutWithdrawBankMaxAmount_WM_TC_03() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBankMaxAmount_WM_TC_03("60000");
    }

    @Test(priority = 4)
    public void cashOutWithdrawMinTransactionLimit_WM_TC_04() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawMinTransactionLimit_WM_TC_04("10");
    }

    @Test(priority = 5)
    public void cashOutWithdrawBranch_WM_TC_05() throws Exception
    {
        MLWalletBusinessLogic.cashOutWithdrawBranch_WM_TC_05();
    }

    @Test(priority = 6)
    public void cashOutMaxLimit_WM_TC_06() throws Exception
    {
        MLWalletBusinessLogic.cashOutMaxLimit_WM_TC_06();
    }

    @Test(priority = 7)
    public void cashOutInsufficientBalance_WM_TC_07() throws Exception {
        MLWalletBusinessLogic.cashOutInsufficientBalance_WM_TC_07();
    }

    @Test(priority = 8)
    public void cashOutBuyerTierLevelAcc_WM_TC_09() throws Exception
    {
        MLWalletBusinessLogic.cashOutBuyerTierLevelAcc_WM_TC_09();
    }

//=========================== Phase 2=================================================================//


    @Test(priority = 9)
    public void cashOutInvalidBank_WM_TC_10() throws Exception {
        MLWalletBusinessLogic.cashOutInvalidBank_WM_TC_10();
    }

    @Test(priority = 10)
    public void searchAndSelectBank_WM_TC_11() throws Exception {
        MLWalletBusinessLogic.searchAndSelectBank_WM_TC_11();
    }

    @Test(priority = 11)
    public void cashOutInvalidAmount_WM_TC_12() throws Exception {
       MLWalletBusinessLogic.cashOutInvalidAmount_WM_TC_12();
    }

    @Test(priority = 12)
    public void cashOutSaveRecipient_WM_TC_13() throws Exception {
        MLWalletBusinessLogic.cashOutSaveRecipient_WM_TC_13("100");
    }

    @Test(priority = 13)
    public void cashOutRecipientDuplicate_WM_TC_14() throws Exception {
        MLWalletBusinessLogic.cashOutRecipientDuplicate_WM_TC_14("100");
    }

    @Test(priority = 14)
    public void cashOutUIValidation_WM_TC_16() throws Exception {
        MLWalletBusinessLogic.cashOutUIValidation_WM_TC_16();
    }

    @Test(priority = 15)
    public void cashOutWithdrawBackBtnValidation_WM_TC_17() throws Exception {
        MLWalletBusinessLogic.cashOutWithdrawBackBtnValidation_WM_TC_17();
    }

    @Test(priority = 16)
    public void cashOutToBranchUIValidation_WM_TC_18() throws Exception {
        MLWalletBusinessLogic.cashOutToBranchUIValidation_WM_TC_18();
    }

    @Test(priority = 17)
    public void cashOutToBranchBackBtnValidation_WM_TC_19 () throws Exception {
        MLWalletBusinessLogic.cashOutToBranchBackBtnValidation_WM_TC_19();
    }

    @Test(priority = 18)
    public void cashOutOTPPageUIValidation_WM_TC_20() throws Exception {
        MLWalletBusinessLogic.cashOutOTPPageUIValidation_WM_TC_20("100");
    }

    @Test(priority = 19)
    public void cashOutOTPPageBackBtnValidation() throws Exception {
        MLWalletBusinessLogic.cashOutOTPPageBackBtnValidation_WM_TC_21("100");
    }

    @Test(priority = 20)
    public void cashOutMlBranchQRPageUIValidation_WM_TC_22() throws Exception {
        MLWalletBusinessLogic.cashOutMlBranchQRPageUIValidation_WM_TC_22();
    }

    @Test(priority = 21)
    public void cashOutCancelIconValidation_WM_TC_23() throws Exception {
        MLWalletBusinessLogic.cashOutCancelIconValidation_WM_TC_23();
    }

    @Test(priority = 22)
    public void cashOutPendingTransactionValidation_WM_TC_24() throws Exception {
        MLWalletBusinessLogic.cashOutPendingTransactionValidation_WM_TC_24("100");
    }


    @Test(priority = 23)
    public void cashOutMLBranchBuyerTier_WM_TC_27() throws Exception {
        MLWalletBusinessLogic.cashOutMLBankBuyerTier_WM_TC_27("100");
    }


    @Test(priority = 24)
    public void cashOutSemiVerifiedTier_WM_TC_28() throws Exception {
        MLWalletBusinessLogic.cashOutSemiVerifiedTier_WM_TC_28("100");
    }

    @Test(priority = 25)
    public void cashOutFullyVerifiedTier_WM_TC_29() throws Exception {
        MLWalletBusinessLogic.cashOutFullyVerifiedTier_WM_TC_29("100");
    }


    @Test(priority = 26)
    public void cashOutMLBranchSemiVerifiedTier_WM_TC_31() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchSemiVerifiedTier_WM_TC_31();
    }

    @Test(priority = 27)
    public void cashOutMLBranchFullyVerifiedTier_WM_TC_32() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchFullyVerifiedTier_WM_TC_32();
    }

    @Test(priority = 28)
    public void cashOutMaxLimitSemiVerifiedTier_WM_TC_33() throws Exception {
        MLWalletBusinessLogic.cashOutMaxLimitSemiVerifiedTier_WM_TC_33();
    }

    @Test(priority = 29)
    public void cashOutMaxLimitFullyVerifiedTier_WM_TC_36() throws Exception {
        MLWalletBusinessLogic.cashOutMaxLimitFullyVerifiedTier_WM_TC_36();
    }

    @Test(priority = 30)
    public void cashOutMLBranchMaxLimitSemiVerifiedTier_WM_TC_39() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchMaxLimitSemiVerifiedTier_WM_TC_39();
    }

    @Test(priority = 31)
    public void cashOutMLBranchMaxLimitFullyVerifiedTier_WM_TC_42() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchMaxLimitFullyVerifiedTier_WM_TC_42();
    }

    @Test(priority = 32)
    public void cashOutWithDrawBankRequiredDetails_WM_TC_47() throws Exception {
        MLWalletBusinessLogic.cashOutWithDrawBankRequiredDetails_WM_TC_47();
    }

    @Test(priority = 33)
    public void cashOutMiddleNameCheckBoxValidation_WM_TC_48() throws Exception {
        MLWalletBusinessLogic.cashOutMiddleNameCheckBoxValidation_WM_TC_48();
    }

    @Test(priority = 34)
    public void cashOutBankPageUIValidation_WM_TC_49() throws Exception {
        MLWalletBusinessLogic.cashOutBankPageUIValidation_WM_TC_49();
    }

    @Test(priority = 35)
    public void cashOutDragonPayPageUIValidation_WM_TC_50() throws Exception {
        MLWalletBusinessLogic.cashOutDragonPayPageUIValidation_WM_TC_50();
    }

    @Test(priority = 36)
    public void cashOutBankReviewTransactionUIValidation_WM_TC_51() throws Exception {
        MLWalletBusinessLogic.cashOutBankReviewTransactionUIValidation_WM_TC_51("100");
    }

    @Test(priority = 37)
    public void cashOutBankTransactionReceiptUIValidation_WM_TC_52() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionReceiptUIValidation_WM_TC_52();
    }

    @Test(priority = 38)
    public void cashOutRecentTransactionDetailsUIValidation_WM_TC_53() throws Exception {
        MLWalletBusinessLogic.cashOutRecentTransactionDetailsUIValidation_WM_TC_53();
    }

    @Test(priority = 39)
    public void cashOutMLBranchBackToHomeBtnValidation_WM_TC_54() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchBackToHomeBtnValidation_WM_TC_54();
    }

    @Test(priority = 40)
    public void cashOutMLBranchNewTransactionBtnValidation_WM_TC_55() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchNewTransactionBtnValidation_WM_TC_55();
    }

    @Test(priority = 41)
    public void cahOutBankViewAllPageBackBtnValidation_WM_TC_56() throws Exception {
        MLWalletBusinessLogic.cahOutBankViewAllPageBackBtnValidation_WM_TC_56();
    }

    @Test(priority = 42)
    public void cashOutBankSavedRecipientEditBackButton_WM_TC_57() throws Exception {
        MLWalletBusinessLogic.cashOutBankSavedRecipientEditBackButton_WM_TC_57();
    }

    @Test(priority = 43)
    public void cashOutBankEditRecipient_WM_TC_58() throws Exception {
        MLWalletBusinessLogic.cashOutBankEditRecipient_WM_TC_58();
    }

    @Test(priority = 44)
    public void cashOutBankDeleteRecipient_WM_TC_59() throws Exception {
        MLWalletBusinessLogic.cashOutBankDeleteRecipient_WM_TC_59();
    }

    @Test(priority = 45)
    public void cashOutMLBranchOTPPageUiValidation_WM_TC_60() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchOTPPageUiValidation_WM_TC_60();
    }

    @Test(priority = 46)
    public void cashOutMLBranchTransactionDetailsUIValidation_WM_TC_61() throws Exception {
        MLWalletBusinessLogic.cashOutMLBranchTransactionDetailsUIValidation_WM_TC_61();
    }

    @Test(priority = 47)
    public void cashOutBankBackHomeBtnValidation_WM_TC_62() throws Exception {
        MLWalletBusinessLogic.cashOutBankBackHomeBtnValidation_WM_TC_62();
    }

    @Test(priority = 48)
    public void cashOutBankNewTransactionBtnValidation_WM_TC_63() throws Exception {
        MLWalletBusinessLogic.cashOutBankNewTransactionBtnValidation_WM_TC_63();
    }

    @Test(priority = 49)
    public void cashOutBankDragonPayMessageValidation_WM_TC_64() throws Exception {
        MLWalletBusinessLogic.cashOutBankDragonPayMessageValidation_WM_TC_64("100");
    }

    @Test(priority = 50)
    public void cashOutBankReviewTransactionTotalAmountValidation_WM_TC_65() throws Exception {
        MLWalletBusinessLogic.cashOutBankReviewTransactionTotalAmountValidation_WM_TC_65("100",35);
    }

    @Test(priority = 51)
    public void cashOutBankLocationPopupValidation_WM_TC_69() throws Exception {
        MLWalletBusinessLogic.cashOutBankLocationPopupValidation_WM_TC_69("100");
    }

    @Test(priority = 52)
    public void cashOutBankLocationDenyFunctionality_WM_TC_70() throws Exception {
        MLWalletBusinessLogic.cashOutBankLocationDenyFunctionality_WM_TC_70("100");
    }
    @Test(priority = 53)
    public void cashOutBankLocationPermissionDenyCloseBtnFunctionality_WM_TC_71() throws Exception {
        MLWalletBusinessLogic.cashOutBankLocationPermissionDenyCloseBtnFunctionality_WM_TC_71("100");
    }

    @Test(priority = 54)
    public void cashOutBankLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_72() throws Exception {
        MLWalletBusinessLogic.cashOutBankLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_72("100");
    }

    @Test(priority = 55)
    public void cashOutBankLocationPopUpAllowFunctionality_WM_TC_73() throws Exception {
        MLWalletBusinessLogic.cashOutBankLocationPopUpAllowFunctionality_WM_TC_73("100");
    }

    @Test(priority = 56)
    public void cashOutBranchLocationPopupValidation_WM_TC_74() throws Exception {
        MLWalletBusinessLogic.cashOutBranchLocationPopupValidation_WM_TC_74("10");
    }

    @Test(priority = 57)
    public void cashOutBranchLocationDenyFunctionality_WM_TC_75() throws Exception {
        MLWalletBusinessLogic.cashOutBranchLocationDenyFunctionality_WM_TC_75("10");
    }

    @Test(priority = 58)
    public void cashOutBranchLocationPermissionDenyCloseBtnFunctionality_WM_TC_76() throws Exception {
        MLWalletBusinessLogic.cashOutBranchLocationPermissionDenyCloseBtnFunctionality_WM_TC_76("10");
    }

    @Test(priority = 59)
    public void cashOutBranchLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_77() throws Exception {
        MLWalletBusinessLogic.cashOutBranchLocationPermissionDenyOpenSettingsBtnFunctionality_WM_TC_77("10");
    }

    @Test(priority = 60)
    public void cashOutBranchLocationPopUpAllowFunctionality_WM_TC_78() throws Exception {
        MLWalletBusinessLogic.cashOutBranchLocationPopUpAllowFunctionality_WM_TC_78("10");
    }

    @Test(priority = 61)
    public void cashOutBankInternetInterruptionWhileEnteringOTP_WM_TC_79() throws Exception {
        MLWalletBusinessLogic.cashOutBankInternetInterruptionWhileEnteringOTP_WM_TC_79("100");
    }

    @Test(priority = 62)
    public void cashOutBranchInternetInterruptionWhileEnteringOTP_WM_TC_80() throws Exception {
        MLWalletBusinessLogic.cashOutBranchInternetInterruptionWhileEnteringOTP_WM_TC_80("10");
    }

    @Test(priority = 63)
    public void cashOutBankTransactionValidationAfterMinimizingApp_WM_TC_83() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionValidationAfterMinimizingApp_WM_TC_83("100");
    }

    @Test(priority = 64)
    public void cashOutBranchTransactionValidationAfterMinimizingApp_WM_TC_91() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionValidationAfterMinimizingApp_WM_TC_91();
    }

    @Test(priority = 65)
    public void cashOutBranchMaximumTransactionBranchVerifiedTier_WM_TC_08() throws Exception {
        MLWalletBusinessLogic.cashOutBranchMaximumTransactionBranchVerifiedTier_WM_TC_08();
    }

    @Test(priority = 66)
    public void cashOutBankAmountFieldValidation_WM_TC_97() throws Exception {
        MLWalletBusinessLogic.cashOutBankAmountFieldValidation_WM_TC_97("100.123");
    }

    @Test(priority = 67)
    public void cashOutBranchAmountFieldValidation_WM_TC_98() throws Exception {
        MLWalletBusinessLogic.cashOutBranchAmountFieldValidation_WM_TC_98("100.123");
    }

    @Test(priority = 68)
    public void cashOutBankTransactionWithValidMLPin_WM_TC_99() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionWithValidMLPin_WM_TC_99("100");
    }

    @Test(priority = 69)
    public void cashOutBranchTransactionWithValidMLPin_WM_TC_100() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionWithInValidMLPin_WM_TC_100("100");
    }

    @Test(priority = 70)
    public void cashOutBranchTransactionWithValidMLPin_WM_TC_106() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionWithValidMLPin_WM_TC_106();
    }

    @Test(priority = 71)
    public void cashOutBranchTransactionWithInValidMLPin_WM_TC_107() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionWithInValidMLPin_WM_TC_107();
    }

    @Test(priority = 72)
    public void cashOutBranchInAppOTPPopupValidation_WM_TC_113() throws Exception {
        MLWalletBusinessLogic.cashOutBranchInAppOTPPopupValidation_WM_TC_113();
    }

    @Test(priority = 73)
    public void cashOutBranchTransactionInAppOTPPopupUIValidation_WM_TC_114() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionInAppOTPPopupUIValidation_WM_TC_114();
    }

    @Test(priority = 74)
    public void cashOutBranchTransactionNewOTPAfterSixtySecondsValidation_WM_TC_115() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionNewOTPAfterSixtySecondsValidation_WM_TC_115();
    }

    @Test(priority = 75)
    public void cashOutBranchTransactionOTPCancelBtnFunctionality_WM_TC_116() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionOTPCancelBtnFunctionality_WM_TC_116();
    }

    @Test(priority = 76)
    public void cashOutBranchTransactionOTPContinueBtnFunctionality_WM_TC_117() throws Exception {
        MLWalletBusinessLogic.cashOutBranchTransactionOTPContinueBtnFunctionality_WM_TC_117();
    }

    @Test(priority = 77)
    public void cashOutBankInAppOTPPopupValidation_WM_TC_119() throws Exception {
        MLWalletBusinessLogic.cashOutBankInAppOTPPopupValidation_WM_TC_119("100");
    }

    @Test(priority = 78)
    public void cashOutBankTransactionInAppOTPPopupUIValidation_WM_TC_120() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionInAppOTPPopupUIValidation_WM_TC_120("100");
    }

    @Test(priority = 79)
    public void cashOutBankTransactionNewOTPAfterSixtySecondsValidation_WM_TC_121() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionNewOTPAfterSixtySecondsValidation_WM_TC_121("100");
    }

    @Test(priority = 80)
    public void cashOutBankTransactionOTPCancelBtnFunctionality_WM_TC_122() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionOTPCancelBtnFunctionality_WM_TC_122("100");
    }
    @Test(priority = 81)
    public void cashOutBankTransactionOTPContinueBtnFunctionality_WM_TC_123() throws Exception {
        MLWalletBusinessLogic.cashOutBankTransactionOTPContinueBtnFunctionality_WM_TC_123("100");
    }






    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }







}
