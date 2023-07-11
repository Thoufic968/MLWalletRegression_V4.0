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

	public static By objProductImage = By.xpath("(//*[@text='product image'])[1]");
	public static By objProductPrice = By.xpath("//*[@resource-id='productPriceDisplay']");
	public static By objShippingTo = By.xpath("//*[@resource-id='ship']");
	public static By objShippingFee = By.xpath("//*[@resource-id='shippingfee']");
	public static By objViewShop = By.xpath("//*[contains(@text,'View Shop')]");

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
	public static By objJewelry = By.xpath("//*[@text='Jewelry']");
	public static By objContactUs = By.xpath("//*[@text='Contact Us']");
	public static By objGreatDealsForFineWatches = By.xpath("//*[@text='Great Deals for Fine Watches']");
	public static By objRolex = By.xpath("//*[@text='Rolex']");
	public static By objHamilton = By.xpath("//*[@text='Hamilton']");
	public static By objOmega = By.xpath("//*[@text='Omega']");
	public static By objTagHeuer = By.xpath("//*[@text='Tag Heuer']");
	public static By objDiscountPercentage = By.xpath("(//*[@text='product image'])[1]/parent::android.view.View/parent::android.view.View/parent::android.view.View/child::android.widget.TextView");
	public static By objProductName = By.xpath("((//*[@text='product image'])[1]/parent::android.view.View/following-sibling::android.view.View)[1]");
	public static By objOriginalPrice = By.xpath("((//*[@text='product image'])[1]/parent::android.view.View/following-sibling::android.view.View)[2]/child::android.view.View");
	public static By objDiscountPrice = By.xpath("(//*[@text='product image'])[1]/parent::android.view.View/following-sibling::android.widget.TextView");

	public static By objCategoriesHamburgerMenu = By.xpath("//*[@resource-id='show-sidebar']");
	public static By objCategory = By.xpath("//*[@text='CATEGORY']");
	public static By objAmparitoCollections = By.xpath("//*[@text='Amparito Collections']");
	public static By objFineJewelry = By.xpath("//*[@text='Fine Jewelry']");
	public static By objAmparitoCollectionsProductTypes(int i) {
		return By.xpath("(//*[@text='Amparito Collections']/parent::android.view.View/following-sibling::android.view.View/child::android.widget.TextView)["+i+"]");
	}
	public static By objRing = By.xpath("//*[@text='Ring']");
	public static By objRingsSubTypes(int i){
		return By.xpath("(//*[@resource-id='amparitoCollRing1']/child::android.widget.Button)["+i+"]");
	}
	public static By objNecklace = By.xpath("//*[@text='Necklace']");
	public static By objNecklaceSubTypes(int i){
		return By.xpath("(//*[@text='Necklace']/following-sibling::android.view.View/child::android.widget.Button)["+i+"]");
	}
	public static By objBraceletAndBangle = By.xpath("//*[@text='Bracelet & Bangle']");
	public static By objBraceletAndBangleSubTypes(int i){
		return By.xpath("(//*[@text='Bracelet & Bangle']/following-sibling::android.view.View/child::android.widget.Button)["+i+"]");
	}
	public static By objEarrings = By.xpath("//*[@text='Earrings']");
	public static By objEarringsSubTypes(int i){
		return By.xpath("(//*[@text='Earrings']/following-sibling::android.view.View/child::android.widget.Button)["+i+"]");
	}
	public static By objPendant = By.xpath("//*[@text='Pendant']");
	public static By objPendantSubTypes(int i){
		return By.xpath("(//*[@text='Pendant']/following-sibling::android.view.View/child::android.widget.Button)["+i+"]");
	}
	public static By objTernoAndSet = By.xpath("//*[@text='Terno & Set']");
	public static By objTernoAndSetSubTypes = By.xpath("//*[@text='Terno & Set']/following-sibling::android.view.View/child::android.widget.Button");
	public static By objWeddingRing = By.xpath("//*[@text='Wedding Ring']");
	public static By objWeddingRingSubTypes = By.xpath("//*[@text='Wedding Ring']/following-sibling::android.view.View/child::android.widget.Button");

	public static By objPinBrouchAndScalpings = By.xpath("//*[@text='Pin, Brouch & Scaplings']");
	public static By objPinBrouchAndScalpingsSubTypes = By.xpath("//*[@text='Pin, Brouch & Scaplings']/following-sibling::android.view.View/child::android.widget.Button");

	public static By objWatches = By.xpath("//*[@text='Watches']");
	public static By objWatchesSubTypes(int i){
		return By.xpath("(//*[@text='Watches']/following-sibling::android.view.View/child::android.widget.Button)["+i+"]");
	}

	public static By objGender = By.xpath("//*[@resource-id='drpGender']");
	public static By objFilterCheckBox(int i) {
		return By.xpath("(//*[@resource-id='android:id/text1'])["+i+"]");
	}
	public static By objColor = By.xpath("//*[@resource-id='drpColor']");
	public static By objKarat = By.xpath("//*[@resource-id='drpKarat']");
	public static By objPrice = By.xpath("//*[@resource-id='drpPrice']");
	public static By objMainProductImage = By.xpath("//*[@resource-id='crewimage']");
	public static By objOptionalProductImage = By.xpath("//*[@text='Card image cap']");
	public static By objProductNameInProductDetails = By.xpath("//*[@resource-id='mlproduct-description']");
	public static By objColorInProductDetails = By.xpath("//*[@resource-id='mlproduct-color']");
	public static By objKaratInProductDetails = By.xpath("//*[@resource-id='mlproduct-karat']");
	public static By objGenderInProductDetails = By.xpath("//*[@resource-id='mlproduct-gender']");
	public static By objPriceInProductDetails = By.xpath("//*[@text='Price']");
	public static By objActualPriceInProductDetails = By.xpath("//*[@resource-id='compareAtPriceDisplay']/child::android.view.View");
	public static By objDiscountPriceInProductDetails = By.xpath("//*[@resource-id='productPriceDisplay']");
	public static By objShopName = By.xpath("//*[@resource-id='ml-shopname']");
	public static By objShopMobileNumber = By.xpath("(//*[@resource-id='ml-shopname']/following-sibling::android.view.View)[1]");
	public static By objShopEmailID =By.xpath("(//*[@resource-id='ml-shopname']/following-sibling::android.view.View)[2]");
	public static By objShopAddress1 = By.xpath("(//*[@resource-id='ml-shopname']/following-sibling::android.view.View)[3]");
	public static By objShopAddress2 = By.xpath("(//*[@resource-id='ml-shopname']/following-sibling::android.view.View)[4]");
	public static By objTotalProducts = By.xpath("(//*[contains(@text,'Products')])[1]");
	public static By objSoldProducts = By.xpath("(//*[contains(@text,'Products')])[2]");

	public static By objProductImageInInterestedIn =  By.xpath("(//*[@text='product image'])[1]");
	public static By objProductNameInInterestedIn = By.xpath("((//*[@text='product image'])[1]/following-sibling::android.view.View)[1]");
	public static By objActualPriceInInterestedIn = By.xpath("((//*[@text='product image'])[1]/following-sibling::android.view.View)[2]/child::android.view.View");
	public static By objDiscountPriceInInterestedIn = By.xpath("(//*[@text='product image'])[1]/following-sibling::android.widget.TextView");
	public static By objDiscountPercentageInInterestedIn = By.xpath("(//*[@text='product image'])[1]/preceding-sibling::android.widget.TextView");





}
