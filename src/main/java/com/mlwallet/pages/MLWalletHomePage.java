package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletHomePage {

    public static By objRecentTransactions = By.xpath("(//*[@text='Recent Transactions'])");
    public static By objCashOutButton = By.xpath("(//*[@text='Cash Out'])[1]");
    public static By objPendingStatusForCashOut = By.xpath("((//*[@text='Cash Out'])[1]/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView)[2]");
    public static By objKwartaPadala = By.xpath("(//*[@text='Kwarta Padala'])[1]");
    public static By objWalletToWallet = By.xpath("(//*[@text='Wallet to Wallet'])[1]");
    public static By objPayBills = By.xpath("(//*[@text='Posted']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView)[1]");


//=================================== Icons ==================================================================//

    public static By objCashInIcon = By.xpath("//*[@text='Cash In']");
    public static By objCashOutIcon = By.xpath("//*[@text='Cashout /\n" +
            "Withdraw']");
    public static By objBuyELoadIcon =By.xpath("//*[@text='Buy eLoad']");
    public static By objPayBillsIcon = By.xpath("//*[@text='Pay Bills']");
    public static By objShopItemsIcon = By.xpath("//*[@text='Shop Items']");
    public static By objSendTransferIcon = By.xpath("//*[@text='Send /\n" +
            "Transfer']");
    public static By objMLLoans = By.xpath("//*[@text='ML Loans']");
    public static By objUseQR = By.xpath("//*[@text='Use QR']");
    public static By objHamburgerMenu = By.xpath("(//*[android.view.ViewGroup]/child::android.view.ViewGroup/child::android.widget.TextView)[3]");
    public static By objProfileIcon1=By.xpath("(//*[android.widget.ImageView]/ancestor::android.view.ViewGroup/descendant::android.view.ViewGroup/child::android.view.ViewGroup/following-sibling::android.view.ViewGroup)[2]");
    public static By objAvailableBalanceHeader = By.xpath("//*[@resource-id='UTJ9TN']");
    public static By objEyeIcon = By.xpath("//*[@resource-id='JKRBGQ']");
    public static By objAvailableBalance = By.xpath("//*[@resource-id='0619JV']");
    public static By objHiddenAvailableBalance = By.xpath("//*[@resource-id='3P4KX3']");
    public static By objTransactions = By.xpath("//*[@resource-id='8VFRG4']/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
    public static By objSeeMore = By.xpath("//*[@text='See More']");
    public static By objIIcon = By.xpath("//*[@text='Learn more about your account level by clicking here.']");
    public static By objVerificationTierPerks = By.xpath("//*[@text='Verification Tier Perks']");
    public static By objMaxBalanceText = By.xpath("//*[@text='Max. Balance:']");
    public static By objMaxBalanceAmount = By.xpath("//*[@text='Max. Balance:']/following-sibling::android.widget.TextView");
    public static By objSendingLimitsCashOut = By.xpath("//*[@text='Sending Limits (cash-out)']");
    public static By objSendingLimitTransactionTypeAndAmount = By.xpath("//*[@text='Sending Limits (cash-out)']/following-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
    public static By objReceivingLimitsCashIn = By.xpath("//*[@text='Receiving Limits (cash-in)']");
    public static By objReceivingLimitsTransactionTypeAndAmount = By.xpath("//*[@text='Receiving Limits (cash-in)']/following-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
    public static By objPurchaseLimits = By.xpath("//*[@text='Purchase Limits']");
    public static By objPurchaseLimitsTransactionTypeAndAmount = By.xpath("//*[@text='Purchase Limits']/following-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
    public static By objTransaction = By.xpath("//*[@text='Transaction']");
    public static By objWithdrawCash = By.xpath("//*[@text='Withdraw Cash']");
    public static By objTopUpMLWallet = By.xpath("//*[@text='Top Up ML Wallet']");
    public static By objShop = By.xpath("//*[@text='Shop']");
    public static By objKwartaPadalaRatesBtn = By.xpath("//*[@text='Kwarta Padala Rates']");
    public static By objKwartaPadalaRates = By.xpath("//*[@class='android.widget.ScrollView']/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView");
    public static By objViewTier = By.xpath("//*[@text='View Tier']");
    public static By objSupport = By.xpath("//*[@text='Support']");
    public static By objAbout = By.xpath("//*[@text='About']");
    public static By objTier = By.xpath("//*[contains(@text,'Verified Tier Perks')]");
    public static By objSemiVerified = By.xpath("//*[@text='Semi Verified']");
    public static By objBranchVerified = By.xpath("//*[@text='Branch Verified']");
    public static By objFullyVerified = By.xpath("//*[@text='Fully Verified']");
    public static By objUpgradeTierLevelBtn = By.xpath("//*[@text='Upgrade Tier Level']");



}
