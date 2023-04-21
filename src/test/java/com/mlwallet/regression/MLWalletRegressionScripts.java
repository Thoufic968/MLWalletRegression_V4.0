package com.mlwallet.regression;


import com.driverInstance.DriverManager;
import com.utility.Utilities;
import org.testng.annotations.*;
import com.business.mlwallet.MLWalletBusinessLogic;


public class MLWalletRegressionScripts {

	public static String deviceName;
	public static String portno;
	public  static MLWalletBusinessLogic MLWalletBusinessLogic;
	
	
	//@BeforeSuite(groups = { "All" })
	 @Parameters({"deviceName","portno"})
	 @BeforeMethod
	public void before(String deviceName,String portno) throws Exception {
		MLWalletRegressionScripts.deviceName=deviceName;
		MLWalletRegressionScripts.portno= portno;
		 MLWalletBusinessLogic = new MLWalletBusinessLogic("MLWallet",deviceName,portno);
	 }


	@Test(priority = 0)
	public void LogInScenarioWithValidMobNumber() throws Exception
	{
		MLWalletBusinessLogic.logInScenarioWithValidMobNumber_Lgn_TC_01();
	}
	@Test(priority = 1)
	public void LogInScenarioWithInvalidMobNumber() throws Exception {
		MLWalletBusinessLogic.logInScenarioWithInvalidMobNumber_Lgn_TC_02();
	}
	@Test(priority = 2)
	public void LogInScenarioWithValidOTP() throws Exception {
		 MLWalletBusinessLogic.logInScenarioWithValidOTP_Lgn_TC_03();
	}

//===================================== CashOut/Withdraw =======================================================//
	@Test(priority = 3)
	public void cashOutWithdrawBank() throws Exception
	{
		MLWalletBusinessLogic.cashOutWithdrawBank_WM_TC_01("100");
	}

	@Test(priority = 4)
	public void cashOutWithInvalidAccNumber() throws Exception
	{
		MLWalletBusinessLogic.cashOutWithInvalidAccNumber_WM_TC_02();
	}

	@Test(priority = 5)
	public void cashOutWithdrawBankMaxAmount() throws Exception
	{
		MLWalletBusinessLogic.cashOutWithdrawBankMaxAmount_WM_TC_03("60000");
	}

	@Test(priority = 6)
	public void cashOutWithdrawMinTransactionLimit() throws Exception
	{
		MLWalletBusinessLogic.cashOutWithdrawMinTransactionLimit_WM_TC_04("10");
	}

	@Test(priority = 7)
	public void cashOutWithdrawBranch() throws Exception
	{
		MLWalletBusinessLogic.cashOutWithdrawBranch_WM_TC_05();
	}

	@Test(priority = 8)
	public void cashOutMaxLimit() throws Exception
	{
		MLWalletBusinessLogic.cashOutMaxLimit_WM_TC_06();
	}

	@Test(priority = 9)
	public void cashOutBuyerTierLevelAcc() throws Exception
	{
		MLWalletBusinessLogic.cashOutBuyerTierLevelAcc_WM_TC_08();
	}


//========================================================================================================================

	@Test(priority = 10)
	public void sendMoneyToMLBranch() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyToMLBranch_STB_TC_01();
	}

	@Test(priority = 11)
	public void sendMoneyAddRecipient() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyAddRecipient_STB_TC_03();
	}

	@Test(priority = 12)
	public void sendMoneyContactDuplicate() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyContactDuplicate_STB_TC_04();
	}

	@Test(priority = 13)
	public void sendMoneyToSavedRecipient() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyToSavedRecipient_STB_TC_02();
	}

	@Test(priority = 14)
	public void sendMoneyEditRecipient() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyEditRecipient_STB_TC_06();
	}
	@Test(priority = 15)
	public void sendMoneyDeleteRecipient() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyDeleteRecipient_STB_TC_05();
	}

	@Test(priority = 16)
	public void sendMoneyInvalidAmount() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyInvalidAmount_STB_TC_09("0");
	}

	@Test(priority = 17)
	public void sendMoneyInsufficientAmount() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyInsufficientAmount_STB_TC_10();
	}

	@Test(priority = 18)
	public void sendMoneyMaximumAmount() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyMaximumAmount_STB_TC_12();
	}

	@Test(priority = 19)
	public void sendMoneyRequiredDetails() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyRequiredDetails_STB_TC_08();
	}

	@Test(priority = 20)
	public void sendMoneyInvalidDetails() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyInvalidDetails_STB_TC_07();
	}

////============================================================================================================


	@Test(priority = 21)
	public void sendToMLWalletUser() throws Exception
	{
		MLWalletBusinessLogic.sendToMLWalletUser_STW_TC_01();
	}

	@Test(priority = 22)
	public void sendMoneyMLWalletToExistingReceiver() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyMLWalletToExistingReceiver_STW_TC_02();

	}

	@Test(priority = 23)
	public void sendToMLWalletInvalidMobNumber() throws Exception
	{
		MLWalletBusinessLogic.sendToMLWalletInvalidMobNumber_STW_TC_03();
	}


	@Test(priority = 24)
	public void sendToMLWalletUnRegisteredNumber() throws Exception
	{
		MLWalletBusinessLogic.sendToMLWalletUnRegisteredNumber_STW_TC_04();
	}

	@Test(priority = 25)
	public void sendToMLWalletInvalidAmount() throws Exception
	{
		MLWalletBusinessLogic.sendToMLWalletInvalidAmount_STW_TC_05("0");
	}

	@Test(priority = 26)
	public void sendToMLWalletInsufficientAmount() throws Exception
	{
		MLWalletBusinessLogic.sendToMLWalletInsufficientAmount_STW_TC_06();
	}

	@Test(priority = 27)
	public void sendMoneyMLWalletMaximumAmount() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyMLWalletMaximumAmount_STW_TC_07();
	}

	@Test(priority = 28)
	public void sendMoneyDeleteFromFavorites() throws Exception
	{
		MLWalletBusinessLogic.sendMoneyDeleteFromFavorites_STW_TC_09();
	}
//
	@Test(priority = 29)
	public void mlWalletTransactionHistory_Scenario() throws Exception
	{
		MLWalletBusinessLogic.mlWallet_TransactionHistory_TH_TC_01();
	}

	@Test(priority = 30)
	public void mlWalletShopItems_Scenario() throws Exception
	{
		MLWalletBusinessLogic.mlWallet_ShopItems_without_Input_Otp();

	}

	@Test(priority = 31)
	public void mlWallet_ShopItems_with_Insufficient_Balance() throws Exception {
		MLWalletBusinessLogic.mlWallet_ShopItems_with_Insufficient_Balance();
	}


//==================================================================================================================//


	@Test(priority = 32)
	public void cashInViaBank() throws Exception {
		MLWalletBusinessLogic.cashInViaBank_CIBA_TC_01();
	}

	@Test(priority = 33)
	public void cashInViaBankMinimumTransactionLimit() throws Exception {
		MLWalletBusinessLogic.cashInViaBankMinimumTransactionLimit_CIBA_TC_03();
	}

	@Test(priority = 34)
	public void cashInViaBankMaximumTransaction() throws Exception {
		MLWalletBusinessLogic.cashInViaBankMaximumTransaction_CIBA_TC_04();
	}
//===============================================================================================================//
	@Test(priority = 35)
	public void payBillsValidation() throws Exception {
		MLWalletBusinessLogic.payBillsPageValidation_PB_TC_01();
	}

	@Test(priority = 36)
	public void billerCategoriesValidation() throws Exception {
		MLWalletBusinessLogic.billerCategories_PB_TC_02();
	}

	@Test(priority = 37)
	public void billersInAlphabeticalOrder() throws Exception {
		MLWalletBusinessLogic.billersInAlphabeticalOrder_PB_TC_03();
	}

	@Test(priority = 38)
	public void selectBiller() throws Exception {
		MLWalletBusinessLogic.selectBiller_PB_TC_04();
	}

	@Test(priority = 39)
	public void searchBiller() throws Exception {
		MLWalletBusinessLogic.searchBiller_PB_TC_05();
	}

	@Test(priority = 40)
	public void billingInformationInput() throws Exception {
		MLWalletBusinessLogic.billingInformationInput_PB_TC_06();
	}


	@Test(priority = 41)
	public void billingInformationInvalidInput() throws Exception {
		MLWalletBusinessLogic.billingInformationInvalidInput_PB_TC_10();
	}

//   @Test(priority = 42)
//    public void payBillsWithValidInputs() throws Exception {
//        MLWalletBusinessLogic.payBillsWithValidInputs();
//    }
//
//   @Test(priority = 43)
//    public void addBillerToPayBills() throws Exception {
//        MLWalletBusinessLogic.addBillerToPayBills();
//    }

//	@Test(priority = 44)
//	public void addBillerInvalidInputs() throws Exception {
//		MLWalletBusinessLogic.addBillerInvalidInputs_PB_TC_13();
//	}



}