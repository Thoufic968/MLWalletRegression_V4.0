package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletEloadPage {

	public static By objEloadTab=By.xpath("//*[@text='eLoad']");
	public static By objEloadtransactionPage=By.xpath("//*[@text='eLoad Transaction']");
	public static By objSelectTelco = By.xpath("//*[@text='Select Telco']");
	public static By objPhoneToLoad = By.xpath("//*[@text='Phone to load']");
	public static By objSelectFromContacts = By.xpath("//*[@text='Select from Contacts']");
	public static By telcoOptions(int indexTab)
	{
		return By.xpath("(//*[android.view.ViewGroup]/following-sibling::android.view.ViewGroup/(descendant::android.widget.ImageView)[1])["+indexTab+"]");
	}

	public static By objPromoLoads=By.xpath("//*[@text='PHP']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/(descendant::android.widget.TextView)[1]");

	
//	public static By objTelecommunicationTab=By.xpath("//*[android.view.ViewGroup]/following-sibling::android.view.ViewGroup/(descendant::android.widget.ImageView)[4]");
	public static By objMobileNoField=By.xpath("//*[@class='android.widget.EditText']");
	public static By objNextBtn=By.xpath("//*[@text='Next']");
	public static By objLoadSelectionPage=By.xpath("//*[@text='Load Selection']");
	public static By objPromoLoadTab=By.xpath("//*[@text='Promo Load']");
	public static By objRegularLoadTab = By.xpath("//*[@text='Regular Load']");
	public static By objTransaction=By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*[@class='android.view.ViewGroup' and ./*[./*[@text]]])[1]");
	public static By objContinuePromoPopUp=By.xpath("//*[@text='Would you like to continue with this promo?']");
	public static By objPromoName=By.xpath("(//*[android.view.ViewGroup]/child::android.view.ViewGroup/child::android.widget.TextView)[5]");
	public static By objPromoConfirmationPopup = By.xpath("//*[@text='Confirm']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	public static By objConfirmBtn=By.xpath("//*[@text='Confirm']");
	public static By objCancelBtn = By.xpath("//*[@text='Cancel']");
	public static By objTransactionDetailsPage=By.xpath("//*[@text='Transaction Details']");
	public static By objTypeOfPromoUsed=By.xpath("(//*[@resource-id='Type'])[2]");
	public static By objMobileNumber=By.xpath("(//*[@resource-id='Mobile Number'])[2]");
	public static By objAmountToSend=By.xpath("(//*[@resource-id='Amount to Send'])[2]");
	public static By objServiceFee=By.xpath("(//*[@resource-id='Service Fee'])[2]");
	public static By objTotalAmount=By.xpath("(//*[@resource-id='Total'])[2]");
	public static By objErrorMsg=By.xpath("//*[android.widget.EditText]/following-sibling::android.widget.TextView");
	public static By objInsufficientBalPopup=By.xpath("//*[@text='Ok']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	public static By objOkBtn=By.xpath("//*[@text='Ok']");
	public static By objMobileNumberInTransactionDetails = By.xpath("(//*[@text='Successfully bought load for']/following-sibling::android.widget.TextView)[1]");
	public static By objELoadAmount = By.xpath("(//*[@text='Successfully bought load for']/following-sibling::android.widget.TextView)[2]");
	public static By objBuyELoadTime = By.xpath("(//*[@text='Successfully bought load for']/following-sibling::android.widget.TextView)[3]");
	public static By objBuyELoadStatus = By.xpath("(//*[@text='Successfully bought load for']/following-sibling::android.widget.TextView)[4]");

	public static By objMaxLimitPopupMsg = By.xpath("//*[@text='Ok']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	public static By obj3000RegularLoad = By.xpath("(//*[@text='Globe Regular Load 3000'])[1]");
	public static By obj2000RegularLoad = By.xpath("(//*[@text='Globe Regular Load 2000'])[1]");

	public static By obj2000PromoLoad = By.xpath("(//*[@text='LD2000'])[1]");
	public static By obj3000PromoLoad = By.xpath("(//*[@text='LD3000'])[1]");
	public static By objBuyLoad = By.xpath("//*[@text='Buy Load']");
	public static By objLoadSelectionBackArrowBtn = By.xpath("//*[@text='Load Selection']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objMobileNumberInLoadSelection = By.xpath("//*[@text='+63']/following-sibling::android.widget.TextView");
	public static By objChange =By.xpath("//*[@text='Change']");
	public static By objWalletBalanceInLoadSeletion = By.xpath("//*[@text='Change']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");
	public static By objLoadWithAmount = By.xpath("(//*[@text='Transaction Details']//parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup)[1]/child::android.widget.TextView");
	public static By objContacts =By.xpath("//*[@text='Contacts']");
	public static By objAllowBtn = By.xpath("//*[@text='Allow']");
	public static By objSearch = By.xpath("//*[@class='android.widget.EditText']");
	public static By objContactsBtn = By.xpath("(//*[@text='Contacts'])[2]");
	public static By objFavorites = By.xpath("//*[@text='Favorites']");
	public static By objContactsAndNumber(int i,int j){
		return By.xpath("(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[./parent::*[./parent::*[@class='android.widget.FrameLayout']]]]]/*[@class='android.view.ViewGroup'])["+i+"]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])[1]/*[@class='android.widget.TextView'])["+j+"]");
	}
	public static By objContactsPageBackBtn = By.xpath("(//*[@text='Contacts'])[1]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	public static By objSearchedContactName = By.xpath("(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[./parent::*[./parent::*[@class='android.widget.FrameLayout']]]]]/*[@class='android.view.ViewGroup'])[1]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])[1]/*[@class='android.widget.TextView'])[1]");
	public static By objSearchedContactNumber = By.xpath("(((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[./parent::*[./parent::*[@class='android.widget.FrameLayout']]]]]/*[@class='android.view.ViewGroup'])[1]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])[1]/*[@class='android.widget.TextView'])[2]");
	public static By objAddToFavoriteIcon = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView' and ./parent::*[./parent::*[./parent::*[@class='android.widget.FrameLayout']]]]]/*[@class='android.view.ViewGroup'])[1]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])[2]/child::android.widget.TextView");
	public static By objNoFavoritesFoundMsg = By.xpath("//*[@text='No favorites found']");
	public static By objNoContactsFoundMsg = By.xpath("//*[@text='No contacts found']");
	public static By objBackToHomeBtn = By.xpath("//*[@text='Back To Home']");
	public static By objAppInfo = By.xpath("//*[@text='App info']");
	public static By objContactPopupMsg = By.xpath("//*[@resource-id='android:id/message']");
	public static By objAskMeLater = By.xpath("//*[@resource-id='android:id/button3']");
	public static By objAllowAccess = By.xpath("//*[@resource-id='android:id/button1']");
	public static By objNewTransaction = By.xpath("//*[@text='New Transaction']");
	public static By objInvalidPINMsg = By.xpath("//*[@text='Ok']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	public static By objMaxLimitErrorMsg = By.xpath("//*[@text='Upgrade Now' or @text='Ok']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");

}
