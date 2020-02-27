package com.orangehrm.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.orangehrm.utilities.PropertyUtil;

public class ExtentReportsUtil {
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	
	public static ExtentTest getTest() {
		return test;
	}


	public static void intitalizeExtent() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/OrangeHRM_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);	
		htmlReporter.config().setDocumentTitle("OrangeHRM Execution Report");
		htmlReporter.config().setReportName("Orange HRM");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", PropertyUtil.readProperty("browserName"));
	}
	
	
	public static void addTestCaseInExtentReport(String testName) {
		test = extent.createTest(testName);
	}
	
	
	

	public static void logStep(Status status,String stepName) {		
		test.log(status, stepName);	
		
		
	
		
	}
	
	public static void endReport() {
		extent.flush();
	}
	
	

	
}
