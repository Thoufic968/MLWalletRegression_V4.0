package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.*;

import static com.business.mlwallet.MLWalletBusinessLogic.prop;

public class MLWalletBuyEload {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;



    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletBuyEload.deviceName=deviceName;
        MLWalletBuyEload.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }




    //===================================buy eload=======================================


    @Test(priority = 1)
    public void buyELoadTransactionDetails_BE_TC_01() throws Exception
    {
        MLWalletBusinessLogic.buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 2)
    public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
    {
        MLWalletBusinessLogic.buyELoadInvalidMobileNumber_BE_TC_02();
    }

    @Test(priority = 3)
    public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutInputMobNumber_BE_TC_03();
    }

    @Test(priority = 4)
    public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutTelecommunicationSelected_BE_TC_04();
    }

    @Test(priority = 5)
    public void buyELoadInsufficientBalance() throws Exception
    {
        MLWalletBusinessLogic.buyELoadInsufficientBalance_BE_TC_05(prop.getproperty("Semi_Verified_ELoad_LowBalance"),2);
    }

    @Test(priority = 6)
    public void buyELoadMaxLimitPerTransaction_BE_TC_09() throws Exception {
        MLWalletBusinessLogic.buyELoadMaxLimitPerTransaction_BE_TC_09(prop.getproperty("Fully_Verified"),2);
    }

    @Test(priority = 7)
    public void buyELoadTransactionPageUIValidation_BE_TC_10() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionPageUIValidation_BE_TC_10(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 8)
    public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11() throws Exception {
        MLWalletBusinessLogic.buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Fully_Verified"),prop.getproperty("sunMobileNumber"),3);
    }

    @Test(priority = 9)
    public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12();
    }

    @Test(priority = 10)
    public void buyELoadLoadSelectionPageUIValidation_BE_TC_13() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageUIValidation_BE_TC_13(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 11)
    public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 12)
    public void buyELoadTransactionDetailsPageUIValidation__BE_TC_15() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsPageUIValidation_BE_TC_15(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 13)
    public void buyELoadSelectFromContactsBtnFunctionality_BE_TC_16() throws Exception {
        MLWalletBusinessLogic.buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 14)
    public void buyELoadContactsPageUIValidation_BE_TC_17() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageUIValidation_BE_TC_17(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 15)
    public void buyELoadContactsPageBackBtnValidation_BE_TC_18() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageBackBtnValidation_BE_TC_18(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 16)
    public void buyELoadSearchFieldValidation_BE_TC_19() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchFieldValidation_BE_TC_19(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 17)
    public void buyELoadAddContactToFavorites_BE_TC_20() throws Exception {
        MLWalletBusinessLogic.buyELoadAddContactToFavorites_BE_TC_20(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 18)
    public void buyELoadAddedContactToFavoritesValidation_BE_TC_21() throws Exception {
        MLWalletBusinessLogic.buyELoadAddedContactToFavoritesValidation_BE_TC_21(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 19)
    public void buyELoadSearchAddedFavoriteContact_BE_TC_22() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchAddedFavoriteContact_BE_TC_22(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 20)
    public void buyELoadSearchUnFavoriteContact_BE_TC_23() throws Exception {
        MLWalletBusinessLogic.buyELoadSearchUnFavoriteContact_BE_TC_23(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 21)
    public void buyELoadPromoConfirmationPopupValidation_BE_TC_24() throws Exception {
        MLWalletBusinessLogic.buyELoadPromoConfirmationPopupValidation_BE_TC_24(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 22)
    public void buyELoadOTPPageUIValidation_BE_TC_25() throws Exception {
        MLWalletBusinessLogic.buyELoadOTPPageUIValidation_BE_TC_25(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 23)
    public void buyELoadSuccessfulTransactionUIValidation_BE_TC_26() throws Exception {
        MLWalletBusinessLogic.buyELoadSuccessfulTransactionUIValidation_BE_TC_26(prop.getproperty("Fully_Verified"),3);
    }

    @Test(priority = 24)
    public void buyELoadRecentTransactionUIValidation_BE_TC_27() throws Exception {
        MLWalletBusinessLogic.buyELoadRecentTransactionUIValidation_BE_TC_27();
    }

    @Test(priority = 25)
    public void buyELoadTransactionDetailsUIValidation_BE_TC_28() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsUIValidation_BE_TC_28();
    }

    @Test(priority = 26)
    public void buyELoadLocationPopupValidation_BE_TC_51() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPopupValidation_BE_TC_51(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 27)
    public void buyELoadLocationDenyFunctionality_BE_TC_52() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationDenyFunctionality_BE_TC_52(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 28)
    public void buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 29)
    public void buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 30)
    public void buyELoadLocationPopUpAllowFunctionality_BE_TC_55() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPopUpAllowFunctionality_BE_TC_55(prop.getproperty("Fully_Verified"), 3);
    }

    @Test(priority = 31)
    public void buyELoadContactsPermissionPopup_BE_TC_56() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Fully_Verified"));
    }

    @Test(priority = 32)
    public void buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57() throws Exception {
        MLWalletBusinessLogic.buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57();
    }

    @Test(priority = 33)
    public void buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58() throws Exception {
        MLWalletBusinessLogic.buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
    }

    @Test(priority = 34)
    public void buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59() throws Exception {
        MLWalletBusinessLogic.buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59();
    }

    @Test(priority = 35)
    public void buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60() throws Exception {
        MLWalletBusinessLogic.buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60();
    }

    @Test(priority = 36)
    public void buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61() throws Exception {
        MLWalletBusinessLogic.buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61(prop.getproperty("Fully_Verified"), 3);
    }

    @Test(priority = 37)
    public void buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
    }

    @Test(priority = 38)
    public void buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63();
    }

    @Test(priority = 39)
    public void buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64() throws Exception {
        MLWalletBusinessLogic.buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64();
    }

    @Test(priority = 40)
    public void buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65() throws Exception {
        MLWalletBusinessLogic.buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65();
    }

    @Test(priority = 41)
    public void buyELoadNewTransactionBtnFunctionality_BE_TC_66() throws Exception {
        MLWalletBusinessLogic.buyELoadNewTransactionBtnFunctionality_BE_TC_66();
    }

    @Test(priority = 42)
    public void buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069(prop.getproperty("Fully_Verified"), 3);
    }

    @Test(priority = 43)
    public void buyELoadTransactionWithValidMLPin_BE_TC_78() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionWithValidMLPin_BE_TC_78(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 44)
    public void buyELoadTransactionWithInValidMLPin_BE_TC_79() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionWithInValidMLPin_BE_TC_79(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 45)
    public void buyELoadInOTPPopupValidation_BE_TC_089() throws Exception {
        MLWalletBusinessLogic.buyELoadInOTPPopupValidation_BE_TC_089(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 46)
    public void buyELoadTransactionInAppOTPPopupUIValidation_BE_TC_090() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionInAppOTPPopupUIValidation_BE_TC_090(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 47)
    public void buyELoadTransactionNewOTPAfterSixtySecondsValidation_BE_TC_091() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionNewOTPAfterSixtySecondsValidation_BE_TC_091(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 48)
    public void buyELoadTransactionOTPCancelBtnFunctionality_BE_TC_092() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionOTPCancelBtnFunctionality_BE_TC_092(prop.getproperty("Fully_Verified"), 4);
    }

    @Test(priority = 49)
    public void buyELoadTransactionOTPContinueBtnFunctionality_BE_TC_093() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionOTPContinueBtnFunctionality_BE_TC_093(prop.getproperty("Fully_Verified"), 4);
    }

    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }





}
