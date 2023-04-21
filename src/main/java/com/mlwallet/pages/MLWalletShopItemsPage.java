package com.mlwallet.pages;

import org.openqa.selenium.By;

public class MLWalletShopItemsPage {

	public static By objShopItemsTab=By.xpath("//*[@text='Shop Items']");
	public static By objMLShopPage=By.xpath("//*[@text='ML Shop']");
	public static By objItemMenu=By.xpath("//*[@text='Rings']");
	public static By objSelectItem=By.xpath("(//*[android.view.View]/child::android.view.View/descendant::android.view.View/child::android.view.View)[8]");
	public static By objAddToCartBtn=By.xpath("//*[@resource-id='btnAddToCart']");
	public static By objHambergerMenu=By.xpath("//*[@resource-id='nav-links-hamberger']");
	public static By objyourBagMenu=By.xpath("//*[@text=' Your Bag']");
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
	public static By objPlaceOrderBtn=By.xpath("//*[@resource-id='PlaceOrder']");
	public static By objOtpPage=By.xpath("//*[@text='One Time Pin']");
	public static By objOtpTextField=By.xpath("//*[@class='android.widget.EditText']");
	public static By objValidateBtn=By.xpath("//*[@text='Validate']");
	
	public static By objInvalidOtpPopUp=By.xpath("//*[@resource-id='swal2-title']");
	public static By objInvalidOtpPopUpMsg=By.xpath("//*[@resource-id='swal2-html-container']");
	
	public static By objCanceBtn=By.xpath("//*[@resource-id='btnConfirmCancelCheckout']");
	public static By objBackArrowBtn=By.xpath("//*[@text='']");
	public static By objAvailableBalance=By.xpath("//*[@text='Available Balance']");
	
	
	
}
