package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.business.mlwallet.MLWalletBusinessLogic.prop;

public class MLWalletBuyEload {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;


    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        MLWalletBuyEload.deviceName=deviceName;
        MLWalletBuyEload.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }




    //===================================buy eload=======================================


//    @Test(priority = 1)
//    public void buyELoadTransactionDetails_BE_TC_01() throws Exception
//    {
//        MLWalletBusinessLogic.buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Fully_Verified"),3);
//    }
//
//    @Test(priority = 2)
//    public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
//    {
//        MLWalletBusinessLogic.buyELoadInvalidMobileNumber_BE_TC_02();
//    }
//
//    @Test(priority = 3)
//    public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
//    {
//        MLWalletBusinessLogic.buyELoadWithoutInputMobNumber_BE_TC_03();
//    }
//
//    @Test(priority = 4)
//    public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
//    {
//        MLWalletBusinessLogic.buyELoadWithoutTelecommunicationSelected_BE_TC_04();
//    }
//
//    @Test(priority = 5)
//    public void mlWalet_insufficient_balance() throws Exception
//    {
//        MLWalletBusinessLogic.buying_eload_insufficient_balance(4);
//    }
//
//    @Test(priority = 6)
//    public void buyELoadMaxLimitPerTransaction_BE_TC_09() throws Exception {
//        MLWalletBusinessLogic.buyELoadMaxLimitPerTransaction_BE_TC_09(prop.getproperty("Fully_Verified"),3);
//    }
//
//    @Test(priority = 7)
//    public void buyELoadTransactionPageUIValidation_BE_TC_10() throws Exception {
//        MLWalletBusinessLogic.buyELoadTransactionPageUIValidation_BE_TC_10(prop.getproperty("Fully_Verified"));
//    }
//
//    @Test(priority = 8)
//    public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11() throws Exception {
//        MLWalletBusinessLogic.buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Fully_Verified"),prop.getproperty("sunMobileNumber"),3);
//    }
//
//    @Test(priority = 9)
//    public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
//        MLWalletBusinessLogic.buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12();
//    }
//
//    @Test(priority = 10)
//    public void buyELoadLoadSelectionPageUIValidation_BE_TC_13() throws Exception {
//        MLWalletBusinessLogic.buyELoadLoadSelectionPageUIValidation_BE_TC_13(prop.getproperty("Fully_Verified"),3);
//    }
//
//    @Test(priority = 11)
//    public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14() throws Exception {
//        MLWalletBusinessLogic.buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(prop.getproperty("Fully_Verified"),3);
//    }

    @Test(priority = 12)
    public void buyELoadTransactionDetailsPageUIValidation() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsPageUIValidation(prop.getproperty("Fully_Verified"),3);
    }



}
