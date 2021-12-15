package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Utils;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	private WebDriver driver;
	private Utils util;

	private By emailId = By.id("input-email");
	private By pass = By.name("password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By loginErrorMsg = By.cssSelector(".alert-dismissible");

	private By loginBlockHeadingText = By.cssSelector("div#content .col-sm-6:nth-of-type(2) h2");
	private By loginBlockTitleText = By.cssSelector("div#content .col-sm-6:nth-of-type(2) strong");
	private By emailAddressText = By.xpath("//label[contains(text(),'E-Mail')]");
	private By passwordText = By.xpath("//label[contains(text(),'Password')]");
	private By forgotPasswordLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private By regiterLink = By.cssSelector(".list-group > a:nth-child(2)");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new Utils(this.driver);
	}

	@Step("Get Login Page Title")
	public String getLoginPageTitle() {
		return util.waitForTitleToPresent(Constants.LOGIN_PAGE_TITLE);
	}

	@Step("LOGIN BLOCK - Get Login Block Heading Text")
	public String getLoginBlockHeadingText() {
		return util.doGetText(loginBlockHeadingText);
	}

	@Step("LOGIN BLOCK - Get Login Block Title Text")
	public String getLoginBlockTitleText() {
		return util.doGetText(loginBlockTitleText);
	}

	@Step("LOGIN BLOCK - Get Login Block Email Address Text")
	public String getEmailAddressText() {
		return util.doGetText(emailAddressText);
	}

	@Step("LOGIN BLOCK - Get Login Block Password Text")
	public String getPasswordText() {
		return util.doGetText(passwordText);
	}

	@Step("LOGIN BLOCK - Get Text Attribute In Email Textbox")
	public String getTextAttributeInEmailTextbox() {
		return util.getElement(emailId).getAttribute("placeholder");
	}

	@Step("LOGIN BLOCK - Get Text Attribute In Password Textbox")
	public String getTextAttributeInPasswordTextbox() {
		return util.getElement(pass).getAttribute("placeholder");
	}

	@Step("Click on Forgot Password Link")
	public ForgotPasswordPage doClickForgotPwdLink() {
		if (util.getElement(forgotPasswordLink).isDisplayed()) {
			util.doClick(forgotPasswordLink);
		}
		return new ForgotPasswordPage(driver);
	}

	public String getErrorMsg() {
		return util.doGetText(loginErrorMsg);
	}

	public void loginWithCredentials(String username, String password) {
		util.doSendKeys(emailId, username);
		util.doSendKeys(pass, password);
		util.doClick(loginButton);
	}

	public AccountPage navigateToAccountPage() {
		return new AccountPage(driver);
	}


	@Step("Navigate to Registration page")
	public RegistrationPage navigateToRegisterPage() {
		util.doClick(regiterLink);
		return new RegistrationPage(driver);
	}

}