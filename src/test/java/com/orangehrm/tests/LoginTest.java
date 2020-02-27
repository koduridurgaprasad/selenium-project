package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExcelUtil;
import com.orangehrm.utilities.PropertyUtil;

public class LoginTest extends BaseTest {

	@Test
	public void verifyLoginWithValidCredentials() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(PropertyUtil.readProperty("userName"),PropertyUtil.readProperty("password"));
		String actualMsg = loginPage.getWelComeMsg();
		Assert.assertEquals(actualMsg, "Welcome Vishnu", "Verify Welcome Message");
		loginPage.logout();

	}
	
	
	@Test
	public void verifyLoginWithInvalidCredentials() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(ExcelUtil.readData(1,1),ExcelUtil.readData(1,2));
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, ExcelUtil.readData(1,3), "Verify Error Message for Invalid Credentials");

	}
	
	
	@Test
	public void verifyLoginWithBlankUserName() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("", "Vishnu");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "Username cannot be empty", "Verify Error Message if user leaves username blank");

	}
	
	@Test
	public void verifyLoginWithBlankPassword() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("Vishnu", "");
		String actualMsg = loginPage.getLoginErrorMsg();
		Assert.assertEquals(actualMsg, "Password cannot be empty", "Verify Error Message if user leaves Password blank");

	}
	
	@Test
	public void verifyFooterMessage() {
		LoginPage loginPage = new LoginPage(getDriver());
		String actualMsg= loginPage.getFooterMsg();
		
		Assert.assertEquals(actualMsg, "OrangeHRM ver 3.0.1 © OrangeHRM Inc. 2005 - 2019 All rights reserved.", "Verify Footer Message");
	}
	

}
