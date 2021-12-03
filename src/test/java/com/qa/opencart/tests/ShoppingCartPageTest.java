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
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		resultPage = accountPage.doSearch("HP");
		productInfoPage = resultPage.selectProductFromResult("HP LP3065");
		
		accountPage.doSearch("Mac");
		resultPage.selectProductFromResult("MacBook Pro");
		
		cartPage = productInfoPage.navigateToViewCartPage();	
	}
	/*
	TC1 - Verify error msg of product not in stock
	1. Check if any products have *** desc
	2. Expected:  “Products marked with *** are not available in the desired quantity or not in stock!” msg is shown for that product
	3. Click on “Checkout” button
	4. Expected: the error msg is still shown
	*/
	
	@Description ("CPT1 - Verify product not in stock error msg to be displayed in Cart page")
	@Test (priority = 1)
	public void verifyNotInStockErrorMsg() {
		Assert.assertTrue(cartPage.isProductNotInStock());
		Assert.assertTrue(cartPage.doGetNotInStockErrorText().contains(Constants.CART_PAGE_NOT_IN_STOCK_MSG));
		
		cartPage.doCheckOut();
		Assert.assertTrue(cartPage.doGetNotInStockErrorText().contains(Constants.CART_PAGE_NOT_IN_STOCK_MSG));	
	}
	
	/*
	TC2 - Check product info 
	1. Add 2 products in cart
	2. Check number of products 
	3. Expected: 2 products */
	
	@Test (priority = 2)
	public void verifyNumberOfProductsAddedInCart() {
		Assert.assertEquals(cartPage.doGetNumberOfProducts(), Constants.CART_PAGE_NUMBER_OF_PRODUCTS);
	}
	
	@Test (priority = 3)
	public void verifyNumberOfProductImages() {
		Assert.assertEquals(cartPage.doGetNumberOfProductImages(), Constants.CART_PAGE_NUMBER_OF_PRODUCT_IMG);
	}

	
	
	
}

