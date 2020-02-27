package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.utilities.Log;

public class LoginPage {
	
	

	@FindBy(id="txtUsername")
	private WebElement txtboxUserName;
	
	@FindBy(id="txtPassword")
	private WebElement txtboxPassword;
	
	@FindBy(id="btnLogin")
	private WebElement btnLogin;
	
	@FindBy(id="welcome")
	private WebElement linkWelcome;
	
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement linkLogout;
	
	@FindBy(id="spanMessage")
	private WebElement msgLoginError;
	
	@FindBy(id="spanCopyright")
	private WebElement msgFooter;
	
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	public void login(String userName,String password) {
		try {
			txtboxUserName.sendKeys(userName);
			Log.info("Entered username: " +userName );
			txtboxPassword.sendKeys(password);
			Log.info("Entered Password: " + password);
			btnLogin.click();
			Log.info("Click on Login");
		}catch(Exception e) {
			Log.error("Failed to Login:"+e.getMessage());
		}
		
	}
	
	public String getWelComeMsg() {
		return linkWelcome.getText();
	}
	
	public void logout() {
		linkLogout.click();
	}
	
	public String getLoginErrorMsg() {
		return msgLoginError.getText();
	}
	
	public String getFooterMsg() {
		return msgFooter.getText();
	}
	
	
}
