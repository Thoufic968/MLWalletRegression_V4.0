package com.mlwallet.pages;

import org.openqa.selenium.By;

import java.security.PublicKey;
import java.security.spec.DSAPublicKeySpec;

public class MLWalletTopUpGames {


    public static By objTopUpGames = By.xpath("//*[@text='Top up\n" +
            " Games']");
    public static By objTopGamesPage = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]/*[@class='android.view.ViewGroup'])[2]");
    public static By objGames(int i){
        return By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[4]/*/*/*/*[@text and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[./*[@class='android.widget.ImageView']]])["+i+"]");
    }

    public static By objGames1(int j){
        return By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]]/*[@class='android.view.ViewGroup'])[5]/*/*/*/*[@text and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[./*[@class='android.widget.ImageView']]])["+j+"]");
    }
    public static By objValorant = By.xpath("//*[@text='Valorant ']");
    public static By objBuyPHP149 = By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])[1]/*[@text])[1]");
    public static By objGameUserID = By.xpath("(//*[@text='*Game User ID']/following-sibling::android.widget.EditText)[1]");
    public static By objEmailAddress = By.xpath("(//*[@text='*Email Addresses']/following-sibling::android.widget.EditText)[1]");
    public static By objMobileNumber = By.xpath("(//*[@text='*Mobile Number']/following-sibling::android.widget.EditText)[1]");
    public static By objContinue = By.xpath("//*[@text='Continue']");
    public static By objConfirm = By.xpath("//*[@text='Confirm']");
    public static By objTransactionDetails = By.xpath("//*[@text='Transaction Details']");
    public static By objTransactionCode = By.xpath("(//*[@resource-id='Transaction Code'])[2]");
    public static By objGameUserIDInTransactionDetails = By.xpath("(//*[@resource-id='Game User ID'])[2]");
    public static By objMobileNumberInTransactionDetails = By.xpath("(//*[@resource-id='Mobile Number'])[2]");
    public static By objEmailInTransactionDetails = By.xpath("(//*[@resource-id='Email'])[2]");
    public static By objAmountInTransactionDetails = By.xpath("(//*[@resource-id='Amount'])[2]");
    public static By objRedeemCode = By.xpath("(//*[@resource-id='Redeem Code'])[2]");
    public static By objAvailableBalance = By.xpath("//*[@resource-id='0619JV']");
    public static By objBackToHome = By.xpath("//*[@text='Back To Home']");
    public static By objGameIDRequiredMsg = By.xpath("(//*[@text='*Game User ID']/following-sibling::android.widget.TextView)[1]");
    public static By objEmailRequiredMsg = By.xpath("(//*[@text='*Email Addresses']/following-sibling::android.widget.TextView)[1]");
    public static By objEmailAddressInvalidMsg = By.xpath("(//*[@text='*Email Addresses']/following-sibling::android.widget.TextView)[1]");
    public static By objMobileNumberInvalidMsg = By.xpath("(//*[@text='*Mobile Number']/following-sibling::android.widget.TextView)[1]");
    public static By objMobileNumberRequiredMsg = By.xpath("(//*[@text='*Mobile Number']/following-sibling::android.widget.TextView)[1]");
    public static By objSaveToMyFavorite = By.xpath("//*[@text='Save to my favorite']");
    public static By objAddToFavoritesMsg = By.xpath("//*[@text='Successfully Added to Favorites']");
    public static By objDuplicateFavorites = By.xpath("//*[@text='Recipient already exists.']");
    public static By objOkButton = By.xpath("//*[@text='Ok']");
    public static By objMyFavorites = By.xpath("//*[@text='My Favorites']");
    public static By objFavoriteRecipient = By.xpath("//*[@text='09999999996']");
    public static By objSearchFavField = By.xpath("//*[@class='android.widget.EditText']");
    public static By objLoadType(int i){
        return By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*[@class='android.view.ViewGroup' and ./*[@text] and ./*[@class='android.view.ViewGroup']])["+i+"]");
    }

    public static By objLoadTypeAndPoints(int i,int j){
        return By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup']])["+i+"]/*[@text])["+j+"]");
    }

    public static By objHamburgerMenu = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[./*[@class='android.view.ViewGroup']]]]]]]/*/*[@class='android.widget.TextView' and ./parent::*[@class='android.view.ViewGroup']])[3]");
    public static By objRemoveBtn = By.xpath("//*[@text='Remove']");
    public static By objTopUpAgainBtn = By.xpath("//*[@text='Top Up Again']");
    public static By objTopUpGamesTransaction = By.xpath("//*[@text='Top Up Games']");
    public static By objCompleteStatus = By.xpath("//*[@text='Complete']");


}
