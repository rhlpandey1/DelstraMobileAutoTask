package com.pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ebaydemo.EbayMobileAutomation.utils.Utilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EbayPageObject {
	   private AndroidDriver<MobileElement> driver;
	    public EbayPageObject() {
	    }
	    public EbayPageObject(AndroidDriver<MobileElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	    public static Logger log = Logger.getLogger(EbayPageObject.class);
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in")
	    private MobileElement buttonSignIn;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement buttonUserName;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement textboxUserName;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement textboxPassword;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_classic")
	    private MobileElement buttonSignInSubmit;
	    @AndroidFindBy(id = "com.ebay.mobile:id/search_box")
	    private MobileElement textboxSearch;
	    @AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	    private List<MobileElement> textboxSearchInside;
	    @AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	    private List<MobileElement> saveText;
	    @AndroidFindBy(id = "com.ebay.mobile:id/textview_item_name")
	    private MobileElement itemNameOnAddToCartPage;
	    @AndroidFindBy(id = "com.ebay.mobile:id/textview_item_price")
	    private MobileElement itemPriceOnAddToCartPage;
	    @AndroidFindBy(id = "com.ebay.mobile:id/button_add_to_cart")
	    private MobileElement buttonAddToCart;
	    
	    /*@AndroidFindBy(xpath = "/*[text()='65 inch tv TV, Video & Home Audio Electronics']")
	    private MobileElement textTvSearch;*/
	    @AndroidFindBy(id = "com.ebay.mobile:id/textview_item_title")
	    private List<MobileElement> listOfTvs;
	    
	    public void clickOnSignIn()
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn));
			buttonSignIn.click();
			log.info("Clicked on sign in button");
	    }
	    public void clickOnUserName()
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(buttonUserName));
			Assert.assertTrue(buttonUserName.isDisplayed());
			buttonUserName.click();
	    }
	    //Unable to capture the locators of login page using both Appium session window and UIAutomatorviewer
	    //Hence commenting out login functionality 
	    public void enterUserNameAndPassword(String userName,String password)
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	    	wait.until(ExpectedConditions.elementToBeClickable(textboxUserName));
	    	textboxUserName.sendKeys(userName);
	    	textboxPassword.sendKeys(password);
	    	buttonSignInSubmit.click();
	    }
	    public void SignIn(String userName,String password)
	    {
	    	clickOnSignIn();
	    	clickOnUserName();
	    	enterUserNameAndPassword(userName,password);
	    }
	    public void searchItem(String item)
	    {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(textboxSearch));
				textboxSearch.click();
				List<MobileElement> els1 = textboxSearchInside;
				els1.get(0).click();
				Utilities.staticWait(5000);
				//seendKeys is not working , so i had to take this approach
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
				driver.pressKeyCode(AndroidKeyCode.SPACE);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_I);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_N);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_C);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_H);
				driver.pressKeyCode(AndroidKeyCode.SPACE);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_T);
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_V);
				driver.pressKeyCode(AndroidKeyCode.ENTER);
	    	
	    }
	    public void selectItemAndAddToCart(String itemName,String modelNo)
	    {
	    	log.info("");
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.elementToBeClickable(saveText.get(1)));
			//driver.tap(1,134,526,1);
	    	//	saveText.get(1).click();
	    //	TouchActions action = new TouchActions(driver);
	    	for(MobileElement ie:listOfTvs)
	    	{
	    		if(ie.getText().contains(modelNo))
	    		{
	    			/*action.scroll(ie, 10, 100);
	    	    	action.perform();*/
	    			driver.scrollTo(itemName);
	    	    	ie.click();
	    		}
	    	}
	    }
	    
	    
}
