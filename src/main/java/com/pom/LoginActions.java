package com.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.DriverManager;
import com.utils.CommonMethods;

public class LoginActions  {
/**
 * This class will have all locators as per POM strategy and all actions of associated class
 */
	
	private WebDriver driver;
	CommonMethods common = new CommonMethods(DriverManager.getdriver());
	//need consturctor for this class. one to access all the locators with driver
	public LoginActions(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(xpath="(//*[@id='ShopifyMainNav']//*[contains(text(),'Log in')])[2]")
	WebElement login;
	
	@CacheLookup
	@FindBy(xpath="//a[contains(text(),'Get started')]")
	WebElement getstarted;
	
	
	
	@CacheLookup
	@FindBy(xpath="//input[@autocomplete='username']")
	public WebElement username;
	
	
	@CacheLookup
	@FindBy(xpath="//input[@autocomplete='given-name']")
	WebElement firstname;
	
	
	@CacheLookup
	@FindBy(xpath="//input[@autocomplete='family-name']")
	WebElement lastname;
	
	

	@CacheLookup
	@FindBy(xpath="//input[@id='account_password']")
	WebElement password;
	
	@CacheLookup
	@FindBy(xpath="//input[@id='password-confirmation']")
	WebElement confirmpassword;
	
	
	
	
	public void clicklogin() {
		elementEnabledclick(login);
	}
	
	public void clickgetstarted() {
		elementEnabledclick(getstarted);
	}
	
	public void sendUn(String un) {
		passinput(username,un);
	}
	public void sendfn(String fn) {
		passinput(firstname, fn);
	}
	
	public void sendlastname(String ln) {
		passinput(lastname, ln);
	}
	
	public void sendpassword(String pwd) {
		passinput(password, pwd);
	}
	public void sendconfrimPwd() {
		//Actions act = new Actions(driver);
		password.sendKeys(Keys.CONTROL,"a");
		password.sendKeys(Keys.CONTROL,"c");
		common.higlightement(confirmpassword);
		confirmpassword.sendKeys(Keys.CONTROL,"v");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void elementEnabledclick(WebElement ele) {
		common.waitforElement(ele);
		common.higlightement(ele);
		if (ele.isEnabled()) {
			ele.click();
		}else {
			System.out.println("Elemenet is not enabled thus not clickable");
		}
	}
		public void passinput(WebElement ele, String keys) {
			common.waitforElement(ele);
			common.higlightement(ele);
			if (ele.isEnabled()) {
				ele.clear();
				ele.sendKeys(keys);
			}else {
				System.out.println("Element is not enables and keys can not be passed ");
			}
			
		}
		
	
	
	
	
	
	

}
