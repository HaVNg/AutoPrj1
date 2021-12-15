package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;



public class LoginPageTest extends BaseTest {

	@Description("LPT1 - Verify Login Page Title Test")
	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		String loginTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginTitle, Constants.LOGIN_PAGE_TITLE);
	}

	// HaN
	@Description("LPT2 - Verify All Texts Under Login Block Test")
	@Test(priority = 2)
	public void verifyLoginBlockTextTest() {

		sa.assertEquals(loginPage.getLoginBlockHeadingText(), Constants.LOGIN_BLOCK_HEADING);
		sa.assertEquals(loginPage.getLoginBlockTitleText(), Constants.LOGIN_BLOCK_TITLE);
		sa.assertEquals(loginPage.getEmailAddressText(), Constants.LOGIN_BLOCK_EMAIL);
		sa.assertEquals(loginPage.getPasswordText(), Constants.LOGIN_BLOCK_PASSWORD);
		sa.assertEquals(loginPage.getTextAttributeInEmailTextbox(), Constants.LOGIN_BLOCK_EMAIL_ATTRIBUTE);
		sa.assertEquals(loginPage.getTextAttributeInPasswordTextbox(), Constants.LOGIN_BLOCK_PASSWORD_ATTRIBUTE);
		sa.assertAll();
	}

	// To revisit this to add TC and assertions
	@Description("LPT3 - Click Forgot Password Link Test")
	@Test(priority = 3, enabled = false)
	public void doClickForgotPasswordLinkTest() {
		loginPage.doClickForgotPwdLink();
	}


	@Description("LPT4 - Verify error msg when logging without credentials")
	@Test(priority = 4)
	public void loginWithoutCredentitals() {
		loginPage.loginWithCredentials(prop.getProperty("nullUserName"), prop.getProperty("nullUserName"));
		String loginErrorMsg = loginPage.getErrorMsg();
		Assert.assertEquals(loginErrorMsg, Constants.LOGIN_ERROR_MSG);
	}
	

	@DataProvider
	public Object[][] getInvalidLoginCredentials() {
		return ExcelUtil.getTestData(Constants.LOGIN_CREDENTIALS_SHEET_NAME);
	}
	
	
	@Test(priority = 5, dataProvider = "getInvalidLoginCredentials")
	public void loginWithInvalidCredentials(String invalidUsername, String invalidPassword) {
		loginPage.loginWithCredentials(invalidUsername, invalidPassword);
		String loginErrorMsg = loginPage.getErrorMsg();
		Assert.assertEquals(loginErrorMsg, Constants.LOGIN_ERROR_MSG);
	}

	@Description("LPT5 - Login With Valid Credentials and check if logout link exists")
	@Test(priority = 6)
	public void loginWithCorrectCredentials() {
		loginPage.loginWithCredentials(prop.getProperty("username"), prop.getProperty("password"));
		accountPage = loginPage.navigateToAccountPage();
		Assert.assertTrue(accountPage.isLogoutLinkExisted());

	}
}
