package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import com.driverInstance.DriverInstance;
import org.testng.annotations.*;

import static com.utility.Utilities.initDriver;

public class MLWalletUseQR {

    public static String deviceName;
    public static String portno;
    public  static com.business.mlwallet.MLWalletBusinessLogic MLWalletBusinessLogic;




    //@BeforeSuite(groups = { "All" })
    @Parameters({"deviceName","portno"})
    @BeforeMethod
    public void before(String deviceName,String portno) throws Exception {
        AppiumServer.startServer();
        MLWalletUseQR.deviceName=deviceName;
        MLWalletUseQR.portno= portno;
        MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
    }


//    @Test(priority = 1)
//    public void useQRGenerateQRCode_QR_TC_01() throws Exception {
//        MLWalletBusinessLogic.useQRGenerateQRCode_QR_TC_01();
//    }
//
//    @Test(priority = 2)
//    public void useQRSendMoneyToAnyMLWalletUser_QR_TC_02() throws Exception {
//        MLWalletBusinessLogic.useQRSendMoneyToAnyMLWalletUser_QR_TC_02();
//    }
//
//    @Test(priority = 3)
//    public void useQRInvalidQRCodeValidation_QR_TC_03() throws Exception {
//        MLWalletBusinessLogic.useQRInvalidQRCodeValidation_QR_TC_03();
//    }
//
//    @Test(priority = 4)
//    public void useQR_WalletToWalletReceivedMoneyValidation_QR_TC_04() throws Exception {
//        MLWalletBusinessLogic.useQR_WalletToWalletReceivedMoneyValidation_QR_TC_04("10");
//    }
//
//    @Test(priority = 5)
//    public void useQRScanningReceiverQRCOde_QR_TC_05() throws Exception {
//        MLWalletBusinessLogic.useQRScanningReceiverQRCOde_QR_TC_05();
//    }
//
//    @Test(priority = 6)
//    public void useQRPageUIValidation_QR_TC_06() throws Exception {
//        MLWalletBusinessLogic.useQRPageUIValidation_QR_TC_06();
//    }
//
//    @Test(priority = 7)
//    public void useQRPageBackBtnValidation_QR_TC_07() throws Exception {
//        MLWalletBusinessLogic.useQRPageBackBtnValidation_QR_TC_07();
//    }
//
//    @Test(priority = 8)
//    public void useQR_GenerateQRPageBackBtnValidation_QR_TC_08() throws Exception {
//        MLWalletBusinessLogic.useQR_GenerateQRPageBackBtnValidation_QR_TC_08();
//    }
//
//    @Test(priority = 9)
//    public void useQR_GenerateQRPageUIValidation_QR_TC_11() throws Exception {
//        MLWalletBusinessLogic.useQR_GenerateQRPageUIValidation_QR_TC_11();
//    }
//
//    @Test(priority = 10)
//    public void useQR_ToMLWalletScreenUIValidation_QR_TC_12() throws Exception {
//        MLWalletBusinessLogic.useQR_ToMLWalletScreenUIValidation_QR_TC_12();
//    }
//
//    @Test(priority = 11)
//    public void useQR_EmptyAmountFieldValidation_QR_TC_13() throws Exception {
//        MLWalletBusinessLogic.useQR_EmptyAmountFieldValidation_QR_TC_13();
//    }
//
//    @Test(priority = 12)
//    public void useQR_ConfirmDetailsScreenUIValidation_QR_TC_14() throws Exception {
//        MLWalletBusinessLogic.useQR_ConfirmDetailsScreenUIValidation_QR_TC_14("10");
//    }
//
//    @Test(priority = 13)
//    public void useQR_OneTimePinScreenUIValidation_QR_TC_15() throws Exception {
//        MLWalletBusinessLogic.useQR_OneTimePinScreenUIValidation_QR_TC_15();
//    }
//
//    @Test(priority = 14)
//    public void useQR_SendMoneyToMlWalletSuccessScreenUIValidation_QR_TC_16() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMlWalletSuccessScreenUIValidation_QR_TC_16();
//    }
//
//    @Test(priority = 15)
//    public void useQR_RecentTransactionInHomeScreenUIValidation_QR_TC_17() throws Exception {
//        MLWalletBusinessLogic.useQR_RecentTransactionInHomeScreenUIValidation_QR_TC_17();
//    }
//
//    @Test(priority = 16)
//    public void useQR_TransactionDetailsScreenUIValidation_QR_TC_18() throws Exception {
//        MLWalletBusinessLogic.useQR_TransactionDetailsScreenUIValidation_QR_TC_18();
//    }
//
//    @Test(priority = 17)
//    public void useQR_CameraPermissionPopupValidation_QR_TC_19() throws Exception {
//        MLWalletBusinessLogic.useQR_CameraPermissionPopupValidation_QR_TC_19();
//    }
//
//    @Test(priority = 18)
//    public void useQR_CameraPermissionPopupDenyButtonFunctionality_QR_TC_20() throws Exception {
//        MLWalletBusinessLogic.useQR_CameraPermissionPopupDenyButtonFunctionality_QR_TC_20();
//    }
//
//    @Test(priority = 19)
//    public void useQR_CameraPermissionPopupOnlyThisTimeButtonFunctionality_QR_TC_21() throws Exception {
//        MLWalletBusinessLogic.useQR_CameraPermissionPopupOnlyThisTimeButtonFunctionality_QR_TC_21();
//    }
//
//    @Test(priority = 20)
//    public void useQR_CameraPermissionPopupWhileUsingTheAppButtonFunctionality_QR_TC_22() throws Exception {
//        MLWalletBusinessLogic.useQR_CameraPermissionPopupWhileUsingTheAppButtonFunctionality_QR_TC_22();
//    }
//
//    @Test(priority = 21)
//    public void useQR_SendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_QR_TC_23() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_QR_TC_23();
//    }
//
//    @Test(priority = 22)
//    public void useQR_ScanQRCodeAfterClickingOnDenyButton_QR_TC_24() throws Exception {
//        MLWalletBusinessLogic.useQR_ScanQRCodeAfterClickingOnDenyButton_QR_TC_24();
//    }
//
//    @Test(priority = 23)
//    public void UseQR_SendMoneyToMLWalletLocationPopupValidation_QR_TC_026() throws Exception {
//        MLWalletBusinessLogic.UseQR_SendMoneyToMLWalletLocationPopupValidation_QR_TC_026();
//    }
//
//    @Test(priority = 24)
//    public void useQR_SendMoneyToMLWalletLocationDenyFunctionality_QR_TC_027() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletLocationDenyFunctionality_QR_TC_027();
//    }
//
//    @Test(priority = 25)
//    public void useQR_SendMoneyToMLWalletLocationPermissionDenyCloseBtnFunctionality_QR_TC_028() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletLocationPermissionDenyCloseBtnFunctionality_QR_TC_028();
//    }
//
//
    @Test(priority = 26)
    public void useQR_SendMoneyToMLWalletLocationPermissionDenyOpenSettingsBtnFunctionality_QR_TC_029() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletLocationPermissionDenyOpenSettingsBtnFunctionality_QR_TC_029();
    }

//    @Test(priority = 27)
//    public void useQR_SendMoneyToMLWalletLocationPopUpAllowFunctionality_QR_TC_030() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletLocationPopUpAllowFunctionality_QR_TC_030();
//    }

    @Test(priority = 28)
    public void useQR_CameraPermissionRequiredPopupValidation_QR_TC_31() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionRequiredPopupValidation_QR_TC_31();
    }
//
//    @Test(priority = 29)
//    public void useQR_TransactionValidationAfterMinimizingTheApp_QR_TC_34() throws Exception {
//        MLWalletBusinessLogic.useQR_TransactionValidationAfterMinimizingTheApp_QR_TC_34();
//    }
//
//    @Test(priority = 30)
//    public void useQR_SendMoneyToMLWalletWhenReceiverQRCodeCapturedByCamera_QR_TC_40() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletWhenReceiverQRCodeCapturedByCamera_QR_TC_40();
//    }






    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }
}
