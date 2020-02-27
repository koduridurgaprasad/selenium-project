package com.orangehrm.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.utilities.Log;

public class EmployeeListPage {

	@FindBy(id = "menu_pim_viewEmployeeList")
	private WebElement linkEmployeeList;

	@FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td[2]")
	private List<WebElement> empIds;

	public EmployeeListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void navigateToEmployeeList() {
		linkEmployeeList.click();
		Log.info("Successfully navigate to Employee List");
	}

	public String getEmpRecord(String expectedEmpId) {
		String actualEmpId = null;
		boolean status = false;
		for (WebElement id : empIds) {
			actualEmpId = id.getText();
			if (expectedEmpId.equals(actualEmpId)) {
				status = true;
				break;
			}
		}
		if (!status) {
			actualEmpId = null;
		}
		Log.info("Emp Id: " + actualEmpId);
		return actualEmpId;
	}

}
