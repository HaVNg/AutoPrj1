package com.qa.opencart.tests;

import com.qa.opencart.bases.BaseTest;

public class ShoppingCartPageTest extends BaseTest {
	
	public void cartPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		resultPage = accountPage.doSearch("HP");
		productInfoPage = resultPage.selectProductFromResult("HP LP3065");
		cartPage = productInfoPage.navigateToViewCartPage();	
	}
}
