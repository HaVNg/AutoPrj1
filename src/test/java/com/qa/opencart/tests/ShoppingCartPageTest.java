package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;

//HaN
public class ShoppingCartPageTest extends BaseTest {
	
	@BeforeClass
	public void cartPageSetUp() {
		loginPage.loginWithCredentials(prop.getProperty("username"), prop.getProperty("password"));
		accountPage = loginPage.navigateToAccountPage();
		
		resultPage = accountPage.doSearch("HP");
		productInfoPage = resultPage.selectProductFromResult("HP LP3065");
		
		accountPage.doSearch("Mac");
		resultPage.selectProductFromResult("MacBook Pro");
		
		cartPage = productInfoPage.navigateToViewCartPage();	
	}
	
	@Description ("CPT1 - Verify product not in stock error msg to be displayed in Cart page")
	@Test (priority = 1)
	public void verifyNotInStockErrorMsg() {
		Assert.assertTrue(cartPage.isProductNotInStock());
		Assert.assertTrue(cartPage.doGetNotInStockErrorText().contains(Constants.CART_PAGE_NOT_IN_STOCK_MSG));
		
		cartPage.doCheckOut();
		Assert.assertTrue(cartPage.doGetNotInStockErrorText().contains(Constants.CART_PAGE_NOT_IN_STOCK_MSG));	
	}
	
	
	@Test (priority = 2)
	public void verifyNumberOfProductsAddedInCart() {
		Assert.assertEquals(cartPage.doGetNumberOfProducts(), Constants.CART_PAGE_NUMBER_OF_PRODUCTS);
	}
	
	@Test (priority = 3)
	public void verifyNumberOfProductImages() {
		Assert.assertEquals(cartPage.doGetNumberOfProductImages(), Constants.CART_PAGE_NUMBER_OF_PRODUCT_IMG);
	}

	
	
	
}

