package com.qa.opencart.bases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultPage;
import com.qa.opencart.pages.ShoppingCartPage;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	public SoftAssert sa;
	
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public ResultPage resultPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage regPage;
	public ShoppingCartPage cartPage;
	
	public static final Logger log = Logger.getLogger(BaseTest.class);

	
	@Parameters({ "browser", "browserVersion" })
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {

		basePage = new BasePage();
		prop = basePage.initProp();
		sa = new SoftAssert();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserVersion", browserVersion);
		}

		driver = basePage.initDriver(prop);
		driver.get(prop.getProperty("appURL"));
		log.info("Navigating to: " + prop.getProperty("appURL"));
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("Test execution is completed!");
	}
}
