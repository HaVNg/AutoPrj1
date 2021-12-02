package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;


public class RegistrationPageTest extends BaseTest {
	
	@Description("RPT - Navigate to Register page")
	@BeforeClass
	public void regPageSetUp() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	@Description("RPT - Get random email data")
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automation" + random.nextInt(5000) + "@gmail.com";
		System.out.println("Random email is: " + email);
		return email;
	}
	
	public String getRandomEmailWithFaker() {
		Faker faker = new Faker();
		return faker.internet().emailAddress();
	}
	
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_PAGE_SHEET_NAME);
	}
	
	@Description("RPT1 - Register with multiple diffrent account on the website")
	@Test(priority = 1, dataProvider = "getRegisterData")
	public void registrationTest(String firstName, String lastName,
			String telephone, String password, String subscribe) {
		
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomEmailWithFaker(), telephone, password, subscribe));
		
	}
}
