package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletPayBillsPage {

    public static By objPayBills = By.xpath("//*[@text='Pay Bills']");

    public static By objSelectBiller = By.xpath("//*[@text='Select Biller']");
    public static By objBillers = By.xpath("//*[@text='Billers']");
    public static By objBiller = By.xpath("//*[@text='Biller']");
    public static By objBillerInformation = By.xpath("//*[@text='Biller Information']");

    public static By objRecentTransactions = By.xpath("//*[@text='Recent Transactions']");

    public static By objSavedBiller = By.xpath("//*[@text='Saved Biller']");

    public static By objSavedBillers = By.xpath("//*[@text='Saved Billers']");

    public static By objCategories = By.xpath("//*[@text='Categories']");

    public static By objAlphabetical = By.xpath("//*[@text='Alphabetical']");

    public static By objBankingAndFinance = By.xpath("//*[@text=' Banking/Finance']");

    public static By objCharityAndReligious = By.xpath("//*[@text='Charity/Religious']");
    public static By objCharityAndReligiousBillers = By.xpath("//*[@text='Charity/Religious']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
    public static By objUtilities = By.xpath("//*[@text='Utilities']");
    public static By objUtilitiesBillers = By.xpath("//*[@text='Utilities']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
    public static By objOthers = By.xpath("//*[@text='Others']");
    public static By objOthersBillers = By.xpath("//*[@text='Others']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
    public static By objAirAsia = By.xpath("//*[@text='AIR ASIA BILLSPAYMENT']");
    public static By objBillsPayInformation = By.xpath("//*[@text='Bills Pay Information']");
    public static By objBillerNameInBillsPayInformation = By.xpath("//*[@text='Biller']/following-sibling::android.widget.EditText");
    public static By objSearchBiller = By.xpath("//*[@text='Billers']/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
    public static By objSearchBillerInSavedBillers = By.xpath("//*[@class='android.widget.EditText']");
    public static By objMisBillsPayBiller = By.xpath("//*[@text='MIS BILLSPAY123456']");
    public static By objEditBtn = By.xpath("//*[@text='Edit']");
    public static By objAccountNumberField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]");
    public static By objFirstNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]");
    public static By objMiddleNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[3]");
    public static By objLastnameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[4]");
    public static By objNickNameField = By.xpath("(//*[@text='Billing Information']/following-sibling::android.widget.EditText)[5]");
    public static By objAmountField = By.xpath("//*[@text='PHP']/following-sibling::android.widget.EditText");
    public static By objConfirmBtn = By.xpath("//*[@text='Confirm']");
    public static By objConfirmDetails = By.xpath("//*[@text='Confirm Details']");
    public static By objInvalidFirstNameMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[1]");
    public static By objInvalidSecondNameMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[2]");
    public static By objInvalidLastName = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[2]/following-sibling::android.widget.TextView)[3]");
    public static By objInvalidAmount = By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");
    public static By objAccountNumberRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[1]");
    public static By objFirstNameRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[2]");
    public static By objLastNameRequiredMsg = By.xpath("((//*[@text='Billing Information']/following-sibling::android.widget.EditText)[1]/following-sibling::android.widget.TextView)[3]");
    public static By objBillerName = By.xpath("(//*[@resource-id='Biller'])[2]");
    public static By objAccountName = By.xpath("(//*[@resource-id='Account Name'])[2]");
    public static By objAccountNumber = By.xpath("(//*[@resource-id='Account Number'])[2]");
    public static By objPaymentMethod = By.xpath("(//*[@resource-id='Payment Method'])[2]");
    public static By objPayBtn = By.xpath("//*[contains(@text,'Pay P')]");
    public static By objMaxLimitErrorMessage = By.xpath("(//*[@resource-id='modal-confirm-button']/preceding-sibling::android.widget.TextView)[2]");
    public static By objAddBiller = By.xpath("//*[@text='Add Biller']");
    public static By objAddBillers = By.xpath("//*[@text='Add Billers']");
    public static By objAddSeectedBiller = By.xpath("//*[@text='Biller']/preceding-sibling::android.view.ViewGroup");
    public static By objBillerListSearchBiller = By.xpath("//*[@class='android.widget.EditText']");
    public static By objAddAccountNumber = By.xpath("(//*[@class='android.widget.EditText'])[1]");
    public static By objAddFirstName = By.xpath("(//*[@class='android.widget.EditText'])[2]");
    public static By objAddMiddleName = By.xpath("(//*[@class='android.widget.EditText'])[3]");
    public static By objAddLastName = By.xpath("(//*[@class='android.widget.EditText'])[4]");
    public static By objAddNickName = By.xpath("(//*[@class='android.widget.EditText'])[5]");
    public static By objAddAccountNumberRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[1]/following-sibling::android.widget.TextView");
    public static By objAddFirstNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[2]/following-sibling::android.widget.TextView");
    public static By ObjAddLastNameRequiredMsg = By.xpath("(//*[@class='android.widget.EditText'])[3]/following-sibling::android.widget.TextView");
    public static By objProceedBtn = By.xpath("//*[@text='Proceed']");
    public static By objSuccessPillPaymentMsg = By.xpath("//*[@text='Bills Payment Successful']");
    public static By objAmountPaid = By.xpath("(//*[@text='Bills Payment Successful']/following-sibling::android.widget.TextView)[1]");
    public static By objPaidDate = By.xpath("(//*[@text='Bills Payment Successful']/following-sibling::android.widget.TextView)[2]");
    public static By objTransactionDetails = By.xpath("//*[@text='Transaction Details']");
    public static By objServiceFee = By.xpath("(//*[@resource-id='Service Fee'])[2]");
    public static By objTotalAmount = By.xpath("(//*[@resource-id='Total'])[2]");
    public static By objAmountToPay = By.xpath("(//*[@resource-id='Amount To Pay'])[2]");
    public static By objAmountToSend = By.xpath("(//*[@resource-id='Amount to Send'])[2]");
    public static By objBackToHomeBtn = By.xpath("//*[@text='Back To Home']");
    public static By objTransactionNumber = By.xpath("(//*[@resource-id='Transaction No.'])[2]");
    public static By objReferenceNumber = By.xpath("(//*[@resource-id='Reference Number'])[2]");
    public static By objReceiverName = By.xpath("(//*[@resource-id='Receiver Name'])[2]");
    public static By objReceiverMobNo = By.xpath("(//*[@resource-id='Receiver Mobile No.'])[2]");
    public static By objSearchSavedBiller  = By.xpath("//*[@class='android.widget.EditText']");
    public static By objOKBtn=By.xpath("//*[@text='Ok']");
    public static By objSelectLastName(String sLastName,String sFirstName){
        return By.xpath("//*[contains(@text,'"+sLastName+", "+sFirstName+"')]");
    }
    public static By objEditRecipientLastName = By.xpath("(//*[@class='android.widget.EditText'])[4]");
    public static  By ObjSaveBtn = By.xpath("//*[@text='Save']");
    public static By objSelectBillerInnSavedBiller (String text){
        return By.xpath("//*[@text='"+text+"']");
    }
    public static By objRemoveBtn = By.xpath("//*[@text='Remove']");
    public static By objConfirmPopup = By.xpath("//*[@text='Are you sure to remove this record?']");
    public static By objNewTransactionBtn = By.xpath("//*[@text='New Transaction']");
    public static By objRecentTransactionOne = By.xpath("//*[@class='android.view.ViewGroup' and ./*[@text] and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.HorizontalScrollView']]]]]");
    public static By objAccountInfo = By.xpath("//*[@text='Account Info']");
    public static By objUpgradeNowBtn = By.xpath("//*[@text='Upgrade Now']");

}
