package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Utils;

import io.qameta.allure.Step;

public class RegistrationPage extends BasePage {
	private WebDriver driver;
	private Utils util;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.cssSelector("input[value='Continue']");
	private By regSuccessMsg = By.cssSelector("#content > h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		util = new Utils(this.driver);
	}
	
	@Step("Register with multiple different accounts")
	public boolean accountRegistration(String firstName, String lastName, String email, 
			String telephone, String password, String subscribe) {
		System.out.println("Registering with multiple different accounts...");
		util.doSendKeys(this.firstName, firstName);
		util.doSendKeys(this.lastName, lastName);
		util.doSendKeys(this.email, email);
		util.doSendKeys(this.telephone, telephone);
		util.doSendKeys(this.password, password);
		util.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			util.doClick(subscribeYes);
		} else {
			util.doClick(subscribeNo);
		}
		
		util.doClick(agreeCheckbox);
		util.doClick(continueButton);
		
		String regMsg = util.waitForElementToPresentThenGetText(regSuccessMsg);
		if (regMsg.equalsIgnoreCase(Constants.REGISTER_SUCCESS_MSG)) {
			util.doClick(logoutLink);
			util.doClick(registerLink);
			return true;
		}
		return false;
	}
	
}

