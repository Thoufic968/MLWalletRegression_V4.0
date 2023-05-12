package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MLWalletSendTransferToAnyMLBranchScripts {

    public static String deviceName;
    public static String portno;
    public static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName", "portno"})
    @BeforeMethod
    public void before(String deviceName, String portno) throws Exception {
        MLWalletSendTransferToAnyMLBranchScripts.deviceName = deviceName;
        MLWalletSendTransferToAnyMLBranchScripts.portno = portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet", deviceName, portno);
    }

//============================================================================================//


    @Test(priority = 1)
    public void sendMoneyToMLBranch() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranch_STB_TC_01();
    }

    @Test(priority = 2)
    public void sendMoneyAddRecipient() throws Exception {
        MLWalletBusinessLogic.sendMoneyAddRecipient_STB_TC_03();
    }

    @Test(priority = 3)
    public void sendMoneyContactDuplicate() throws Exception {
        MLWalletBusinessLogic.sendMoneyContactDuplicate_STB_TC_04();
    }

    @Test(priority = 4)
    public void sendMoneyToSavedRecipient() throws Exception {
        MLWalletBusinessLogic.sendMoneyToSavedRecipient_STB_TC_02();
    }

    @Test(priority = 5)
    public void sendMoneyEditRecipient() throws Exception {
        MLWalletBusinessLogic.sendMoneyEditRecipient_STB_TC_06();
    }

    @Test(priority = 6)
    public void sendMoneyDeleteRecipient() throws Exception {
        MLWalletBusinessLogic.sendMoneyDeleteRecipient_STB_TC_05();
    }

    @Test(priority = 7)
    public void sendMoneyInvalidAmount() throws Exception {
        MLWalletBusinessLogic.sendMoneyInvalidAmount_STB_TC_09("0");
    }

    @Test(priority = 8)
    public void sendMoneyInsufficientAmount() throws Exception {
        MLWalletBusinessLogic.sendMoneyInsufficientAmount_STB_TC_10();
    }

    @Test(priority = 9)
    public void sendMoneyMaximumAmount() throws Exception {
        MLWalletBusinessLogic.sendMoneyMaximumAmount_STB_TC_12();
    }

    @Test(priority = 10)
    public void sendMoneyRequiredDetails() throws Exception {
        MLWalletBusinessLogic.sendMoneyRequiredDetails_STB_TC_08();
    }

    @Test(priority = 11)
    public void sendMoneyInvalidDetails() throws Exception {
        MLWalletBusinessLogic.sendMoneyInvalidDetails_STB_TC_07();
    }


    //========================================================================================================//
    @Test(priority = 12)
    public void sendTransferUIValidation_STB_TC_13() throws Exception {
        MLWalletBusinessLogic.sendTransferUIValidation_STB_TC_13();
    }


    @Test(priority = 13)
    public void sendMoneyToBranchUIValidation_STB_TC_14() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchUIValidation_STB_TC_14();
    }

    @Test(priority = 14)
    public void sendMoneyToBranchSaveRecipientPageUIValidation_STB_TC_15() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchSaveRecipientPageUIValidation_STB_TC_15();
    }

    @Test(priority = 15)
    public void sendMoneyToBranchSuccessUIValidation_STB_TC_16() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchSuccessUIValidation_STB_TC_16();
    }

    @Test(priority = 16)
    public void sendMoneyToBranchConfirmDetailsPageUIValidation_STB_TC_17() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchConfirmDetailsPageUIValidation_STB_TC_17("100");
    }

    @Test(priority = 17)
    public void sendMoneyToBranchSelectPaymentMethodPageUIValidation_STB_TC_18() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchSelectPaymentMethodPageUIValidation_STB_TC_18("100");
    }

    @Test(priority = 18)
    public void sendMoneyToBranchEnterAmountPageUIValidation_STB_TC_19() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchEnterAmountPageUIValidation_STB_TC_19();
    }

    @Test(priority = 19)
    public void kwartaPadalaTransactionDetailsUIValidation_STB_TC_20() throws Exception {
        MLWalletBusinessLogic.kwartaPadalaTransactionDetailsUIValidation_STB_TC_20();
    }

    @Test(priority = 20)
    public void sendMoneyToBranchAddRecipientPageUIValidation_STB_TC_21() throws Exception {
        MLWalletBusinessLogic.sendMoneyToBranchAddRecipientPageUIValidation_STB_TC_21();
    }
//====================================================================================
    @Test(priority = 21)
    public void sendMoneyToMLBranchBuyerTierAccount_STB_TC_22() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchBuyerTierAccount_STB_TC_22();
    }

    @Test(priority = 22)
    public void sendMoneyToMLBranchSemiVerifiedTierAccount_STB_TC_23() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchSemiVerifiedTierAccount_STB_TC_23();
    }

    @Test(priority = 23)
    public void sendMoneyToMLBranchBranchVerifiedAccount_STB_TC_24() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchBranchVerifiedAccount_STB_TC_24();
    }

    @Test(priority = 24)
    public void sendMoneyToMLBranchFullyVerifiedAccount_STB_TC_25() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchFullyVerifiedAccount_STB_TC_25();
    }

    @Test(priority = 25)
    public void sendMoneyToMLBranchSemiVerifiedTierAccountMaxAmount_STB_TC_26() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchSemiVerifiedTierAccountMaxAmount_STB_TC_26();
    }

    @Test(priority = 26)
    public void sendMoneyToMLBranchBranchVerifiedTierAccountMaxAmount_STB_TC_29() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchBranchVerifiedTierAccountMaxAmount_STB_TC_29();
    }

    @Test(priority = 27)
    public void sendMoneyToMLBranchFullyVerifiedTierAccountMaxAmount_STB_TC_32() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchFullyVerifiedTierAccountMaxAmount_STB_TC_32();
    }

    @Test(priority = 28)
    public void kwartaPadalaRates_STB_TC_35() throws Exception {
        MLWalletBusinessLogic.kwartaPadalaRates_STB_TC_35();
    }

    @Test(priority = 29)
    public void sendMoneyToMLBranchRatesValidationScenarioOne_STB_TC_36() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioOne_STB_TC_36("10");
    }

    @Test(priority = 30)
    public void sendMoneyToMLBranchRatesValidationScenarioTwo_STB_TC_37() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioTwo_STB_TC_37("70");
    }

    @Test(priority = 31)
    public void sendMoneyToMLBranchRatesValidationScenarioThree_STB_TC_38() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioThree_STB_TC_38("200");
    }

    @Test(priority = 32)
    public void sendMoneyToMLBranchRatesValidationScenarioFour_STB_TC_39() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioFour_STB_TC_39("350");
    }

    @Test(priority = 33)
    public void sendMoneyToMLBranchRatesValidationScenarioFive_STB_TC_40() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioFive_STB_TC_40("450");
    }

    @Test(priority = 34)
    public void sendMoneyToMLBranchRatesValidationScenarioSix_STB_TC_41() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioSix_STB_TC_41("550");
    }


    @Test(priority = 35)
    public void sendMoneyToMLBranchRatesValidationScenarioSeven_STB_TC_42() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioSeven_STB_TC_42("650");
    }

    @Test(priority = 36)
    public void sendMoneyToMLBranchRatesValidationScenarioEight_STB_TC_43() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioEight_STB_TC_43("800");
    }

    @Test(priority = 37)
    public void sendMoneyToMLBranchRatesValidationScenarioNine_STB_TC_44() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioNine_STB_TC_44("950");
    }

    @Test(priority = 38)
    public void sendMoneyToMLBranchRatesValidationScenarioTen_STB_TC_45() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioTen_STB_TC_45("1300");
    }

    @Test(priority = 39)
    public void sendMoneyToMLBranchRatesValidationScenarioEleven_STB_TC_46() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioEleven_STB_TC_46("1800");
    }

    @Test(priority = 40)
    public void sendMoneyToMLBranchRatesValidationScenarioTwelve_STB_TC_47() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioTwelve_STB_TC_47("2300");
    }

    @Test(priority = 41)
    public void sendMoneyToMLBranchRatesValidationScenarioThirteen_STB_TC_48() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchRatesValidationScenarioThirteen_STB_TC_48("25000");
    }

    @Test(priority = 42)
    public void sendMoneyToMLBranchLocationPopupValidation_STB_TC_50() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchLocationPopupValidation_STB_TC_50();
    }

    @Test(priority = 43)
    public void sendMoneyToMLBranchLocationDenyFunctionality_STB_TC_51() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchLocationDenyFunctionality_STB_TC_51();
    }

    @Test(priority = 44)
    public void sendMoneyToMLBranchLocationPermissionDenyCloseBtnFunctionality_STB_TC_52() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchLocationPermissionDenyCloseBtnFunctionality_STB_TC_52();
    }

    @Test(priority = 45)
    public void sendMoneyToMLBranchLocationPermissionDenyOpenSettingsBtnFunctionality_STB_TC_53() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchLocationPermissionDenyOpenSettingsBtnFunctionality_STB_TC_53();
    }

    @Test(priority = 46)
    public void sendMoneyToMLBranchLocationPopUpAllowFunctionality_STB_TC_54() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchLocationPopUpAllowFunctionality_STB_TC_54();
    }

    @Test(priority = 47)
    public void sendMoneyToMLBranchInternetInterruptionWhileEnteringOTP_STB_TC_55() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchInternetInterruptionWhileEnteringOTP_STB_TC_55();
    }

    @Test(priority = 48)
    public void sendMoneyToMLBranchTransactionValidationAfterMinimizingApp_STB_TC_56() throws Exception {
        MLWalletBusinessLogic.sendMoneyToMLBranchTransactionValidationAfterMinimizingApp_STB_TC_56();
    }




}