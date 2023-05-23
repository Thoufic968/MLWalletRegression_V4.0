package com.mlwallet.regression;

import com.business.mlwallet.MLWalletBusinessLogic;
import com.driverInstance.AppiumServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

//
//    @Test(priority = 1)
//    public void useQRGenerateQRCode_QR_TC_01() throws Exception {
//        MLWalletBusinessLogic.useQRGenerateQRCode_QR_TC_01();
//    }

    @Test(priority = 2)
    public void useQRSendMoneyToAnyMLWalletUser_QR_TC_02() throws Exception {
        MLWalletBusinessLogic.useQRSendMoneyToAnyMLWalletUser_QR_TC_02();
    }

    @AfterMethod
    public void afterMethod(){
        AppiumServer.stopServer();
    }
}
