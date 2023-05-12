package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletShopItemsPage {

	public static By objShopItemsTab=By.xpath("//*[@text='Shop Items']");
	public static By objMLShopPage=By.xpath("//*[@text='ML Shop']");
	public static By objItemMenu=By.xpath("//*[@text='Rings']");
	public static By objSelectItem=By.xpath("(//*[android.view.View]/child::android.view.View/descendant::android.view.View/child::android.view.View)[8]");
	public static By objAddToCartBtn=By.xpath("//*[@resource-id='btnAddToCart']");
	public static By objHambergerMenu=By.xpath("//*[@resource-id='nav-links-hamberger']");
	public static By objCart=By.xpath("//*[@text=' Cart']");
	public static By objCheckBox=By.xpath("(//*[@class='android.widget.CheckBox'])[1]");
	public static By objEditAddress=By.xpath("//*[@text='   ']");
	public static By objCheckOutBtn=By.xpath("//*[@resource-id='checkoutbutton']");
	public static By objSelectBranchPage=By.xpath("//*[@resource-id='exampleModalLabel']");
	public static By objInputFieldOne=By.xpath("//*[@resource-id='dropEditProvince']"); 
	public static By objBranchName=By.xpath("//*[@text='AURORA']");
	public static By objInputFieldTwo=By.xpath("//*[@resource-id='btnChangeAddress']");
	public static By objSubBranchName=By.xpath("//*[@text='BALER']");
	public static By objInputFieldThree=By.xpath("//*[@resource-id='dropEditBranchname']");
	public static By objSubBranchNameTwo=By.xpath("//*[@text='ML BALER 2, AURORA']");
	public static By objSaveBtn=By.xpath("//*[@resource-id='submitEditDeliveryAddresButton']");
	public static By objAddressSuccessfulMsg=By.xpath("//*[@text='Successfully Update Delivery Address.']");
	public static By objOkBtn=By.xpath("//*[@text='Ok' or @text='OK']");
	public static By objYesBtn=By.xpath("//*[@text='Yes']");
	public static By objPlaceOrderBtn=By.xpath("//*[@resource-id='PlaceOrder']");
	public static By objOtpPage=By.xpath("//*[@text='One Time Pin']");
	public static By objOtpTextField=By.xpath("//*[@class='android.widget.EditText']");
	public static By objValidateBtn=By.xpath("//*[@text='Validate']");
	
	public static By objInvalidOtpPopUp=By.xpath("//*[@resource-id='swal2-title']");
	public static By objInvalidOtpPopUpMsg=By.xpath("//*[@resource-id='swal2-html-container']");
	
	public static By objCanceBtn=By.xpath("//*[@resource-id='btnConfirmCancelCheckout']");
	public static By objBackArrowBtn=By.xpath("//*[@text='']");
	public static By objAvailableBalance=By.xpath("//*[@text='Available Balance']");
	public static By objSelectPaymentMethod = By.xpath("//*[@text='Select Payment Method']");
	public static By objMLWallet = By.xpath("//*[@text='ML Wallet']");

	public static By objSearch=By.xpath("//*[@text=' Search']");
	public static By objProfile=By.xpath("//*[@text=' Profile']");
	public static By objShop = By.xpath("//*[@text='Shop']");
	public static By objAbout = By.xpath("//*[@text='About']");
	public static By objContact = By.xpath("//*[@text='Contact']");
	public static By objItems = By.xpath("//*[@id='imahehome']/child::android.view.View/child::android.view.View/child::android.view.View/following-sibling::android.view.View");
	public static By objGenderDropdown = By.xpath("//*[@resource-id='drpGender']");
	public static By objColorDropdown = By.xpath("//*[@resource-id='drpColor']");
	public static By objKaratDropdown = By.xpath("//*[@resource-id='drpKarat']");
	public static By objPriceDropdown = By.xpath("//*[@resource-id='drpKarat']");

	public static By objProductImage = By.xpath("//*[@resource-id='crewimage']");
	public static By objProductPrice = By.xpath("//*[@resource-id='productPriceDisplay']");
	public static By objShippingTo = By.xpath("//*[@resource-id='ship']");
	public static By objShippingFee = By.xpath("//*[@resource-id='shippingfee']");
	public static By objViewShop = By.xpath("//*[@text='\uF54E  View Shop']");

	public static By objCartPageHeader = By.xpath("(//*[@class='android.widget.Image'])[1]/following-sibling::android.view.View");
	public static By objDeleteIcon = By.xpath("(//*[@class='android.widget.CheckBox']/following-sibling::android.view.View)[2]");
	public static By objProductNameInCartPage = By.xpath("(//*[@class='android.widget.CheckBox']/following-sibling::android.widget.TextView)[1]");
	public static By objPriceInCartPage = By.xpath("(//*[@class='android.widget.CheckBox']/following-sibling::android.widget.TextView)[2]");
	
	public static By objShippingDetails = By.xpath("//*[@resource-id='font-sd']");
	public static By objAddAddress = By.xpath("//*[@resource-id='btnChangeAddress']");
	public static By objProductNameInShippingDetails = By.xpath("((//*/*[@class='android.view.View'])[21]/*[@text and @class='android.view.View'])[1]");
	public static By objProductQuantity = By.xpath("((//*/*[@class='android.view.View'])[21]/*[@text and @class='android.view.View'])[2]");
	public static By objItemSubTotal = By.xpath("((//*/*[@class='android.view.View'])[21]/*[@text and @class='android.view.View'])[3]");
	public static By objHeader = By.xpath("//*[@resource-id='captionproductorder']");
	public static By objTotalOrder = By.xpath("//*[@resource-id='product-totalorder2_0']");
	public static By objServiceFee = By.xpath("//*[@resource-id='product-servicefee2_0']");
	public static By objShippingFeeInShippingDetails = By.xpath("//*[@resource-id='product-shippingfee2_0']");

	public static By objOnlineBanking = By.xpath("//*[@text='Online Banking']");
	public static By objMerchandiseSubTotal = By.xpath("//*[@text='Merchandise Subtotal :']/following-sibling::android.view.View");
	public static By objServiceTotal = By.xpath("//*[@text='Service Total :']/following-sibling::android.view.View");
	public static By objShippingTotal = By.xpath("//*[@text='Shipping Total :']/following-sibling::android.view.View");
	public static By objPaymentTotal = By.xpath("//*[@resource-id='product-final-total']");
	public static By objCancel = By.xpath("//*[@text='Cancel']");
	public static By objAddressUpdateMsg = By.xpath("//*[@resource-id='swal2-html-container']");

	public static By objOTPMsg = By.xpath("//*[@text='Enter the 6-digit OTP sent to your mobile number']");
	public static By objErrorPopup = By.xpath("//*[@resource-id='swal2-html-container']");
	public static By objMyAccount = By.xpath("//*[@text='MY ACCOUNT']");
	public static By objMyAccountDropdown = By.xpath("//*[@text='My Account']");
	public static By objMyAccountPageCrossBtn = By.xpath("//*[@resource-id='close-sidebar']/child::android.widget.TextView");
	public static By objMyProfileHeader = By.xpath("(//*[@text='My Profile'])[2]");
	public static By objMyPurchase = By.xpath("//*[@text='My Purchases']");
	public static By objOrderDetails = By.xpath("//*[@resource-id='orderDetails']");
	public static By objSubtotalAmount = By.xpath("//*[@resource-id='cart-subtotal']");
}
