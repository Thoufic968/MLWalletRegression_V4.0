package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletCashOutPage {

    public static By objCashOut = By.xpath("//*[@text='Cashout /\n" +
            "Withdraw']");
//=========================================== ML Branch ========================================================//
    public static By objToAnyMLBranch = By.xpath("//*[@text='To any ML Branch']");

    public static By objCashOutToBranch = By.xpath("//*[@text='Cash Out To Branch']");

    public static By objAmountField = By.xpath("//*[@class='android.widget.EditText']");

    public static By objNextBtn = By.xpath("//*[@text='Next']");

    public static By objContinueBtn = By.xpath("//*[@text='Continue']");

    public static By objLocationPermission = By.xpath("//*[@text='WHILE USING THE APP' or @text='While using the app']");

    public static By objBackToHomeBtn = By.xpath("//*[@text='Back To Home']");

    public static By objCreatedDate = By.xpath("//*[contains(@text,'Created')]");

    public static By objReferenceNumber = By.xpath("//*[contains(@text,'KPTN')]");

    public static By objReferenceNumberInCashOut = By.xpath("(//*[@resource-id='Reference Number'])[2]");

    public static By objTransactionDetails = By.xpath("(//*[@text='Transaction Details'])");

    public static By objBackArrowBtn = By.xpath("//*[@text='Transaction Details']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By objMaxLimitTxt = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");

    public static By objInsufficientBalance = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");
    public static By objOkBtn = By.xpath("//*[@resource-id='modal-confirm-button']");

    public static By objCashOutPageBackArrowBtn = By.xpath("//*[@text='Cash Out']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By objCashOutOptionsBackArrowBtn = By.xpath("//*[@text='Cash out / Withdraw']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

//============================================ ML Bank ===================================================================//

    public static By objToAnyBank = By.xpath("//*[@text='To Any Bank']");

    public static By BogusBank = By.xpath("//*[@text='BogusBank']");

    public static By objBankInformation = By.xpath("//*[@text='Bank Information']");

    public static By objAccountNumberField = By.xpath("(//*[@text='BogusBank']/following-sibling::android.widget.EditText)[1]");

    public static By objFirstname = By.xpath("(//*[@text='BogusBank']/following-sibling::android.widget.EditText)[2]");

    public static By objMiddleName = By.xpath("(//*[@text='BogusBank']/following-sibling::android.widget.EditText)[3]");

    public static By objLastName = By.xpath("(//*[@text='BogusBank']/following-sibling::android.widget.EditText)[4]");

    public static By objEmailAddress = By.xpath("(//*[@text='BogusBank']/following-sibling::android.widget.EditText)[5]");

    public static By objCheckBox = By.xpath("//*[@class='android.widget.ImageView']");
    public static By objViewAllBtn = By.xpath("//*[@text='View All']");
    public static By objViewAllBackBtn = By.xpath("(//*[@text and ./parent::*[@class='android.view.ViewGroup' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]])[2]");
    public static By objConfirmBtn = By.xpath("//*[@text='Confirm']");
    public static By objDragonPage = By.xpath("//*[@text='Dragon Pay']");
    public static By objBankCashOut = By.xpath("//*[@text='Bank Cash Out']");
    public static By objBank = By.xpath("(//*[@text='Bank Information']/following-sibling::android.widget.TextView)[2]");
    public static By objTransactionNo = By.xpath("//*[@text='Transaction No.']/following-sibling::android.widget.TextView");

    public static By objTransactionReferenceNo = By.xpath("");

    public static By objTransactionSuccessMessage = By.xpath("//*[@text='Transaction Successful']");


    public static By objTransactionReceipt = By.xpath("//*[@text='Transaction Receipt']");

    public static By objAccInvalidErrorMsg = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");

    public static By objBackArrow = By.xpath("//*[@class='android.widget.TextView' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[./*[./*[@class='android.widget.ScrollView']]]]]]]");

    public static By objBankListBackArrow = By.xpath("//*[@text='Bank List']/preceding-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By objDragonPayPopUpMsg = By.xpath("//*[@resource-id='something']/following-sibling::android.widget.TextView");

    public static By objBankMaxLimitTxt = By.xpath("//*[@resource-id='something']/following-sibling::android.widget.TextView");

    public static By objMinimumTransactionErrorMsg = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");

//    public static By objReceiversName = By.xpath("//*[@text='Review Transaction']");





//============================================= Phase 2 ==============================================//

    public static By objSearchBank = By.xpath("//*[@class='android.widget.EditText']");

    public static By objNoRecentTransactionTxt = By.xpath("//*[@text='No Recent Transaction']");

    public static By objAmountRequiredErrorMsg = By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");

    public static By objNickName = By.xpath("//*[@class='android.widget.EditText']");

    public static By objSaveRecipientBtn = By.xpath("//*[@text='Save Recipient']");

    public static By objSavedBankAccount = By.xpath("//*[@text='Saved Bank Accounts']");

    public static By objNickNameInSavedBankAcc(String sNickName){
        return By.xpath("//*[@text='"+sNickName+"']");
    }

    public static By objRecipientExistMsg = By.xpath("//*[@text='Recipient already exists.']");

    public static By objCashOutWithdraw = By.xpath("//*[@text='Cash out / Withdraw']");

    public static By objCashOutOptions = By.xpath("//*[@text='Cash out options']");

    public static By objCashOutOngoingTransaction = By.xpath("//*[@text='Ongoing Transactions']");
    public static By objReferenceNumberCashOutBranch = By.xpath("(//*[@text='Reference Number']/following-sibling::android.widget.TextView)[1]");
    public static By objCashOutPage = By.xpath("//*[@text='Cash Out']");

    public static By ObjCashOutToBranch = By.xpath("//*[@text='Cash Out To Branch']");
    public static By objUserName = By.xpath("(//*[@text='Cash Out To Branch']/following-sibling::android.widget.TextView)[1]");

    public static By objUserMobileNumber = By.xpath("(//*[@text='Cash Out To Branch']/following-sibling::android.widget.TextView)[2]");

    public static By objMLWalletBalance = By.xpath("//*[contains(@text,'ML Wallet Balance')]");

    public static  By objCashOutInstructions = By.xpath("//*[@text='Cash Out']//following-sibling::android.view.ViewGroup/child::android.widget.TextView");

    public static By cashOutBackArrowBtn = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[@class='android.widget.ScrollView']]]]]/*/*[@text and ./parent::*[@class='android.view.ViewGroup']])[1]");

    public static By objCashOutToBranchBackBtn = By.xpath("//*[@text='Cash Out']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objResendCode = By.xpath("//*[contains(@text,'Resend Code')]");

    public static By objOneTimePinBackBtn = By.xpath("//*[@text='One Time Pin']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");

    public static By objReviewTransaction = By.xpath("//*[@text='Review Transaction']");
    public static By objReceiverName = By.xpath("//*[@resource-id='fullName']");
    public static By objBankName = By.xpath("//*[@resource-id='bankName']");
    public static By objAccountNumber = By.xpath("//*[@resource-id='accountNumber']");
    public static By objAmount = By.xpath("//*[@resource-id='amount']");
    public static By objServiceFee = By.xpath("//*[@resource-id='serviceFee']");
    public static By objTotalAmountDeduct = By.xpath("//*[@resource-id='netAmount']");
    public static By objEmailAddressUpdated = By.xpath("//*[@resource-id='email']");
    public static By objNewTransaction = By.xpath("//*[@text='New Transaction']");
    public static By objPHP = By.xpath("//*[contains(@text,'PHP')]");
    public static By objCancelIcon = By.xpath("//*[@text='Cash Out']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
    public static By objUpgradeNowBtn = By.xpath("//*[@text='Upgrade Now']");
    public static By objAccountNumberRequiredMsg = By.xpath("//*[@text='Account Number is required']");
    public static By objFirstNameRequiredMsg = By.xpath("//*[@text='First name is required']");
    public static By objMiddleNameRequiredMsg = By.xpath("//*[@text='Middle name is required']");
    public static By objLastNameRequiredMsg = By.xpath("//*[@text='Last name is required']");
    public static By objEmailAddressRequiredMsg = By.xpath("//*[@text='Email address is required']");
    public static By objReceiverNameInTransactionReceipt = By.xpath("(//*[@text=concat('Receiver', \"'\", 's Name')]/following-sibling::android.widget.TextView)[1]");
    public static By objBankNamInTransactionReceipt = By.xpath("(//*[@text='Bank Name']/following-sibling::android.widget.TextView)[1]");
    public static By objAccountNumberInTransactionReceipt = By.xpath("(//*[@text='Account Number']/following-sibling::android.widget.TextView)[1]");
    public static By objPrincipalAmount = By.xpath("(//*[@text='Principal Amount']/following-sibling::android.widget.TextView)[1]");
    public static By objServiceFeeInTransactionReceipt = By.xpath("(//*[@text='Service Fee']/following-sibling::android.widget.TextView)[1]");
    public static By objNetAmount = By.xpath("(//*[@text='Net Amount']/following-sibling::android.widget.TextView)[1]");
    public static By objEmailInTransactionReceipt = By.xpath("(//*[@text='Email']/following-sibling::android.widget.TextView)[1]");
    public static By objDate = By.xpath("(//*[@text='Date']/following-sibling::android.widget.TextView)[1]");
    public static By objType = By.xpath("(//*[@text='Type']/following-sibling::android.widget.TextView)[1]");
    public static By objAddToSavedRecipients = By.xpath("//*[@text='Add to Saved Recipients']");



    public static By objAccountNumberInSavedBankAccount = By.xpath("(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*[@text])[3]");
    public static By objThreeDotMenuButton = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup']]]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']]])[1]/*/*/*/*[@text and ./parent::*[@class='android.view.ViewGroup']])[4]");
    public static By objEditBtn = By.xpath("//*[@text='Edit']");
    public static By objDeleteBtn = By.xpath("//*[@text='Delete']");
    public static By objAccountNumber(String sAccountNumber){
        return By.xpath("//*[@text='"+sAccountNumber+"']");
    }
    public static By objAccountNumberInEditRecipient = By.xpath("//*[@text='Account Number']/following-sibling::android.widget.EditText");
    public static By objEditRecipientBackBtn = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[@class='android.view.ViewGroup']]]]]/*/*[@class='android.widget.TextView' and ./parent::*[@class='android.view.ViewGroup']])[1]");
}
