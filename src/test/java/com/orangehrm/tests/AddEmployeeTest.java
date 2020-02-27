package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeeListPage;
import com.orangehrm.utilities.Log;


public class AddEmployeeTest extends BaseTest {

	
	
	@Test
	public void verifyAddEmployee() {
		
		login();
		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
		addEmployeePage.navigateToPim();
		addEmployeePage.navigateToAddEmployee();
		String expectedEmpId = addEmployeePage.addEmployeeDetails("Rahul","Reddy");
		EmployeeListPage employeeListPage = new EmployeeListPage(getDriver());
		employeeListPage.navigateToEmployeeList();
		String actualEmpId = employeeListPage.getEmpRecord(expectedEmpId);
		Assert.assertEquals(actualEmpId, expectedEmpId, "Verify employee record in Web Table");
	    
	
	}

	
	
	

}
