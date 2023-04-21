package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletCashInBank {

    public static By objCashIn = By.xpath("//*[@text='Cash In']");
    public static By objCashInTransaction = By.xpath("(//*[@text='Cash In'])[1]");
    public static By objCashInOption = By.xpath("//*[@text='Cash In options']");

    public static By objMyBankAccount = By.xpath("//*[@text='My Bank Account']");
    public static By objBranchName=By.xpath("//*[@text='ML Branch']");

    public static By objSelectABank = By.xpath("//*[@text='Select a Bank']");
    public static By objTestBankOnline = By.xpath("//*[@text='Test Bank Online']");

    public static By objDragonPay = By.xpath("//*[@text='Dragon Pay']");

    public static By objBankCashIn = By.xpath("//*[@text='Bank Cash In']");

    public static By objAmountEditField = By.xpath("//*[@text='PHP']/following-sibling::android.widget.EditText");

    public static By objNextBtn = By.xpath("//*[@text='Next']");

    public static By objServiceFeeMsg = By.xpath("(//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView)[2]");

    public static By objDragonPayChargesMsg = By.xpath("(//*[@text='Continue']/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView)[2]");

    public static By objContinueBtn = By.xpath("//*[@text='Continue']");

    public static By objReviewTransaction = By.xpath("//*[@text='Review Transaction']");

    public static By objBankTransferCutOffTime = By.xpath("//*[@text='BANK TRANSFER CUT-OFF TIME']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");

    public static By objReferenceNumberMsg = By.xpath("//*[@resource-id='ContentPlaceHolder1_mainMsg']");

    public static By objLoginIdTxtField = By.xpath("//*[@resource-id='ContentPlaceHolder1_userIdTextBox']");

    public static By objPasswordTxtField = By.xpath("//*[@resource-id='ContentPlaceHolder1_passwdTextBox']");

    public static By objWebContinueBtn = By.xpath("//*[@resource-id='ContentPlaceHolder1_getAccountsButton']");

    public static By objPayBtn = By.xpath("//*[@resource-id='ContentPlaceHolder1_transferButton']");

    public static By objBankReferenceNumber = By.xpath("//*[@resource-id='ContentPlaceHolder1_refnoTextBox']");

    public static By objStatus = By.xpath("//*[@resource-id='ContentPlaceHolder1_statusTextBox']");

    public static By objMessage = By.xpath("//*[@resource-id='ContentPlaceHolder1_msgTextBox']");
    public static By objCompleteTransactionBtn = By.xpath("//*[@text='Complete Transaction']");

    public static By objSuccessMsg = By.xpath("//*[@resource-id='ContentPlaceHolder1_Label1']");

    public static By objMinimumTransactionPopupMsg = By.xpath("(//*[@text='Ok']/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView)[2]");

    public static By objInvalidAmountMsg = By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");

    public static By objMaxLimitErrorMsg = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");
    public static By objCashInBackArrowBtn = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]]]]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*/*[@text and ./parent::*[@class='android.view.ViewGroup']])[1]");
    public static By objSearchBank = By.xpath("//*[@class='android.widget.EditText']");
    public static By objBanks = By.xpath("//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*[@class='android.view.ViewGroup' and ./*[@text]]/child::android.widget.TextView");
    public static By objNoRecentTransactionTxt = By.xpath("//*[@text='No Recent Transaction']");
    public static By objSelectBankBackBtn = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]]]]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*/*[@class='android.widget.TextView' and ./parent::*[@class='android.view.ViewGroup']])[1]");
    public static By objDragonPayBackBtn = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[./*[@class='android.widget.ScrollView']]]]]]/*/*[@class='android.widget.TextView' and ./parent::*[@class='android.view.ViewGroup']])[1]");
    public static By objReceiverName = By.xpath("//*[@resource-id='fullName']");
    public static By objBankName = By.xpath("//*[@resource-id='longName']");
    public static By objPrincipalAmount = By.xpath("//*[@resource-id='initialAmount']");
    public static By objServiceFee = By.xpath("//*[@resource-id='serviceFee']");
    public static By objEmail = By.xpath("//*[@resource-id='email']");
    public static By objReviewTransactionBackBtn = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[./*[@class='android.widget.ScrollView']]]]]]/*/*[@text and ./parent::*[@class='android.view.ViewGroup']])[1]");
    public static By objPendingStatus = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]/*[@class='android.widget.TextView'])[19]");
    public static By objPending = By.xpath("//*[@text='Pending']");
    public static By objCancelled = By.xpath("//*[@text='Cancelled']");
    public static By objTransactionHistoryBackBtn = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]]]]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*/*[@text and ./parent::*[@class='android.view.ViewGroup']])[1]");
    public static By objCancelBtn = By.xpath("//*[@text='Cancel']");
    public static By objUpgradeNowBtn = By.xpath("//*[@text='Upgrade Now']");
    public static By objMaxLimitTxt = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");
    public static By objBankMaxLimitTxt = By.xpath("//*[@resource-id='something']/following-sibling::android.widget.TextView");



}
