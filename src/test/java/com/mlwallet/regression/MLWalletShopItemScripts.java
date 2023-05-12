package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletShopItemScripts {
    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletShopItemScripts.deviceName=deviceName;
        MLWalletShopItemScripts.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }
//=========================================================================================================//



//    @Test(priority = 1)
//    public void mlWalletShopItems_Scenario() throws Exception
//    {
//        MLWalletBusinessLogic.shopItemsWithoutInputOtp_MLS_TC_04();
//    }
//
//    @Test(priority = 2)
//    public void shopItemsHamburgerMenuNavigation_MLS_TC_12() throws Exception {
//        MLWalletBusinessLogic.shopItemsHamburgerMenuNavigation_MLS_TC_12();
//    }
//
//    @Test(priority = 3)
//    public void shopItemsRespectivePageNavigationAfterSelectingAnCategory_MLS_TC_15() throws Exception {
//       MLWalletBusinessLogic.shopItemsRespectivePageNavigationAfterSelectingAnCategory_MLS_TC_15();
//    }
//
//    @Test(priority = 4)
//    public void shopItemsSelectedItemScreenUIValidation_MLS_TC_18() throws Exception {
//        MLWalletBusinessLogic.shopItemsSelectedItemScreenUIValidation_MLS_TC_18();
//    }
//
//    @Test(priority = 5)
//    public void shopItemsCartPageUIValidation_MLS_TC_19() throws Exception {
//        MLWalletBusinessLogic.shopItemsCartPageUIValidation_MLS_TC_19();
//    }
//
//    @Test(priority = 6)
//    public void shopItemsShippingDetailsPageUIValidation_MLS_TC_20() throws Exception {
//        MLWalletBusinessLogic.shopItemsShippingDetailsPageUIValidation_MLS_TC_20();
//    }
//
//
//    @Test(priority = 7)
//    public void shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21() throws Exception {
//        MLWalletBusinessLogic.shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21();
//    }
//
//    @Test(priority = 8)
//    public void shopItemsSaveBtnFunctionalityOnSelectBranchScreen_MLS_TC_22() throws Exception {
//        MLWalletBusinessLogic.shopItemsSaveBtnFunctionalityOnSelectBranchScreen_MLS_TC_22();
//    }
//
//    @Test(priority = 9)
//    public void shopItemsCancelBtnFunctionalityOnSelectBranchScreen_MLS_TC_23() throws Exception {
//        MLWalletBusinessLogic.shopItemsCancelBtnFunctionalityOnSelectBranchScreen_MLS_TC_23();
//    }
//
//    @Test(priority = 10)
//    public void shopItemsPlaceOrderBtnFunctionalityOnShippingDetailsScreen_MLS_TC_24() throws Exception {
//        MLWalletBusinessLogic.shopItemsPlaceOrderBtnFunctionalityOnShippingDetailsScreen_MLS_TC_24();
//    }
//
//    @Test(priority = 11)
//    public void shopItemsOneTimePinPageUIValidation_MLS_TC_25() throws Exception {
//        MLWalletBusinessLogic.shopItemsOneTimePinPageUIValidation_MLS_TC_25();
//    }
//
//    @Test(priority = 12)
//    public void shopItemsCancelBtnFunctionalityOnOTPScreen_MLS_TC_26() throws Exception {
//        MLWalletBusinessLogic.shopItemsCancelBtnFunctionalityOnOTPScreen_MLS_TC_26();
//    }
//
//    @Test(priority = 13)
//    public void shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33() throws Exception {
//        MLWalletBusinessLogic.shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33();
//    }
//
//    @Test(priority = 14)
//    public void shopItemsOkBtnFunctionalityOnErrorPopup_MLS_TC_34() throws Exception {
//        MLWalletBusinessLogic.shopItemsOkBtnFunctionalityOnErrorPopup_MLS_TC_34();
//    }
//
//    @Test(priority = 15)
//    public void shopItemsCartPageNavigation_MLS_TC_42() throws Exception {
//        MLWalletBusinessLogic.shopItemsCartPageNavigation_MLS_TC_42();
//    }
//
//    @Test(priority = 16)
//    public void shopItemsCancelBtnFunctionalityOnRemoveItemPopup_MLS_TC_43() throws Exception {
//        MLWalletBusinessLogic.shopItemsCancelBtnFunctionalityOnRemoveItemPopup_MLS_TC_43();
//    }
//
//    @Test(priority = 17)
//    public void shopItemsOkBtnFunctionalityOnRemoveItemPopup_MLS_TC_44() throws Exception {
//        MLWalletBusinessLogic.shopItemsOkBtnFunctionalityOnRemoveItemPopup_MLS_TC_44();
//    }
//
//    @Test(priority = 18)
//    public void shopItemsNavigationToProfileOption_MLS_TC_46() throws Exception {
//        MLWalletBusinessLogic.shopItemsNavigationToProfileOption_MLS_TC_46();
//    }
//
//    @Test(priority = 19)
//    public void shopItemsProfileScreenScreenNavigation_MLS_TC_47() throws Exception {
//        MLWalletBusinessLogic.shopItemsProfileScreenNavigation_MLS_TC_47();
//    }
//
//    @Test(priority = 20)
//    public void shopItemsMyAccountPopupCrossBtnFunctionality_MLS_TC_48() throws Exception {
//        MLWalletBusinessLogic.shopItemsMyAccountPopupCrossBtnFunctionality_MLS_TC_48();
//    }
//
//    @Test(priority = 21)
//    public void shopItemsMyPurchasePageNavigation_MLS_TC_49() throws Exception {
//        MLWalletBusinessLogic.shopItemsMyPurchasePageNavigation_MLS_TC_49();
//    }
//
//    @Test(priority = 22)
//    public void shopItemsSubTotalVerificationWithOutSelectingTheItemsInCart_MLS_TC_59() throws Exception {
//        MLWalletBusinessLogic.shopItemsSubTotalVerificationWithOutSelectingTheItemsInCart_MLS_TC_59();
//    }

    @Test(priority = 23)
    public void shopItemsSubTotalVerificationWithSelectingTheItemsInCart_MLS_TC_60() throws Exception {
        MLWalletBusinessLogic.shopItemsSubTotalVerificationWithSelectingTheItemsInCart_MLS_TC_60();
    }

}
