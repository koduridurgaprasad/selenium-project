package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.utilities.Log;

public class AddEmployeePage{
	
	

	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement linkPim;

	@FindBy(id = "menu_pim_addEmployee")
	private WebElement linkAddEmployee;

	@FindBy(id = "firstName")
	private WebElement txtFirstName;

	@FindBy(id = "lastName")
	private WebElement txtLastName;

	@FindBy(id = "btnSave")
	private WebElement btnSave;
	
	@FindBy(id="employeeId")
	private WebElement txtEmployeeId;
	
	public AddEmployeePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public void navigateToPim() {
		linkPim.click();
		Log.info("Successfully navigate to PIM");
	}

	public void navigateToAddEmployee() {
		linkAddEmployee.click();
		Log.info("Successfully navigate to Add Employee page");
	}
	
	public String addEmployeeDetails(String fname,String lname){
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
		String expectedEmpId= txtEmployeeId.getAttribute("value");
		btnSave.click();
		Log.info("Successfully added employee details");
	    return expectedEmpId;
	}

}
