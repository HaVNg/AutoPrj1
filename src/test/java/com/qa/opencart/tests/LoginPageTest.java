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
	public void verifyLoginPageTitleTest() {
		String loginTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginTitle, Constants.LOGIN_PAGE_TITLE);
	}

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

	@Description("LPT3 - Click Forgot Password Link Test")
	@Test(priority = 3, enabled = false)
	public void doClickForgotPasswordLinkTest() {
		loginPage.doClickForgotPwdLink();
	}

	@Description("LPT4 - Verify Login Without Credentials Test")
	@Test(priority = 4)
	public void doLoginWithoutCredentitalsTest() {
		String loginErrorMsg = loginPage.doLogin();
		Assert.assertEquals(loginErrorMsg, Constants.LOGIN_ERROR_MSG);
	}
	
	// HaN
	@DataProvider
	public Object[][] getInvalidLoginCredentials() {
		return ExcelUtil.getTestData(Constants.LOGIN_CREDENTIALS_SHEET_NAME);
	}
	
	// HaN
	@Test(priority = 5, dataProvider = "getInvalidLoginCredentials")
	public void doLoginWithInvalidCredentialsTest(String invalidUsername, String invalidPassword) {
		String loginErrorMsg = loginPage.doLoginInvalidCredentials(invalidUsername, invalidPassword);
		Assert.assertEquals(loginErrorMsg, Constants.LOGIN_ERROR_MSG);
	}

	@Description("LPT5 - Login With Valid Credentials and check if logout link exists")
	@Test(priority = 6)
	public void doLoginCorrectCredentialsTest() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountPage.isLogoutLinkExisted());

	}
}
