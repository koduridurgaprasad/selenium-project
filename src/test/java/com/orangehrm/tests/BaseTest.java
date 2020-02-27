package com.orangehrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.reports.ExtentReportsUtil;
import com.orangehrm.reports.ScreenshotUtil;
import com.orangehrm.utilities.ExcelUtil;

import com.orangehrm.utilities.Log;
import com.orangehrm.utilities.PropertyUtil;

public class BaseTest {

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void intialize() throws IOException {
		DOMConfigurator.configure("log4j.xml");
		PropertyUtil.intializePropertyFile();
		ExcelUtil.initilizeExcel();
		ExtentReportsUtil.intitalizeExtent();
	}

	@BeforeMethod
	public void openBrowser(Method method) throws MalformedURLException {
		ExtentReportsUtil.addTestCaseInExtentReport(method.getName());
		String runMode = PropertyUtil.readProperty("runMode");
		String browserName = PropertyUtil.readProperty("browserName");
		if (runMode.equalsIgnoreCase("Local")) {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + PropertyUtil.readProperty("chromeDriverPath"));
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + PropertyUtil.readProperty("firefoxDriverPath"));
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + PropertyUtil.readProperty("ieDriverPath"));
				driver = new InternetExplorerDriver();

			} else {
				Log.error(
						"Looks like you provided Invalid Browser Name.PLease check config file for the property -->browserName");
			}
		} else if (runMode.equalsIgnoreCase("Grid")) {
			String hubUrl = PropertyUtil.readProperty("HubUrl");
			if (browserName.equalsIgnoreCase("chrome")) {
				DesiredCapabilities capability = DesiredCapabilities.chrome();
				capability.setBrowserName("chrome");
				capability.setPlatform(Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL(hubUrl), capability);
			}
		}

		Log.startTestCase(method.getName());
		Log.info("Successfully launched the Browser");
		ExtentReportsUtil.logStep(Status.INFO, "Successfully launched the Browser");
		String timeOut = PropertyUtil.readProperty("timeOut");
		long time = Long.parseLong(timeOut);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	@BeforeMethod(dependsOnMethods = "openBrowser")
	public void openApplication() {

		driver.get(PropertyUtil.readProperty("url"));
		Log.info("Successfully launch the application");
		ExtentReportsUtil.logStep(Status.INFO, "Successfully launch the application");

	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = ScreenshotUtil.getScreenshot(driver);
			try {
				ExtentReportsUtil.getTest().fail(result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} catch (IOException e) {
				Log.error("Failed to capture the Screenshot" + e.getMessage());
			}
		}
		driver.quit();
		Log.info("Successfully close the browser");
		ExtentReportsUtil.logStep(Status.INFO, "Successfully close the browser");
		Log.endTestCase();
	}

	public void login() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(PropertyUtil.readProperty("userName"), PropertyUtil.readProperty("password"));
		Log.info("Successfully Login into application");
	}

	@AfterSuite
	public void tearDown() {
		ExtentReportsUtil.endReport();
	}

}
