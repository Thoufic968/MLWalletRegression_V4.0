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



    @Test(priority = 1)
    public void mlWalletShopItems_Scenario() throws Exception
    {
        MLWalletBusinessLogic.mlWallet_ShopItems_without_Input_Otp();
    }

    @Test(priority = 2)
    public void mlWallet_ShopItems_with_Insufficient_Balance() throws Exception {
        MLWalletBusinessLogic.mlWallet_ShopItems_with_Insufficient_Balance();
    }



}
