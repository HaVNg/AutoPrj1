package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;



public class ProductInfoPageTest extends BaseTest {
	
	@Description("PPT - Login to the website")
	@BeforeClass
	public void productInfoPageSetup() {
		loginPage.loginWithCredentials(prop.getProperty("username"), prop.getProperty("password"));
		accountPage = loginPage.navigateToAccountPage();	
	}

	@Description("PPT1 - Search a product then verify title of Product Info page")
	@Test(priority = 1)
	public void productInfoPageTitleTest() {
		
		resultPage = accountPage.doSearch("Macbook");
		productInfoPage = resultPage.selectProductFromResult("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("MacBook Pro"), "MacBook Pro");
	}

	@Description("PPT2 - Verify number of images shown for a searched product")
	@Test(priority = 2)
	public void verifyProductImagesCountTest() {
		log.info("Getting images count for a searched product...");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 3);
	}
	
	@DataProvider
	public Object[][] getMacbookProInfo() {
		return ExcelUtil.getTestData(Constants.PRODUCT_INFO_MBPRO_SHEET_NAME);
	}
	
	@Description("PPT3 - Verify metadata of a searched product - MacBookPro")
	@Test(priority = 3, dataProvider = "getMacbookProInfo")
	public void verifyProductInfoTest_MacbookPro(String searchedProduct, String selectedProduct, String name, String brand, String productCode, String rewardPoints
			, String availability, String price, String exTax) {
		
		resultPage = accountPage.doSearch(searchedProduct);
		productInfoPage = resultPage.selectProductFromResult(selectedProduct);
		Map<String, String> productInfoMap = productInfoPage.getProductInfo();
		
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_NAME), name);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_BRAND), brand);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_CODE), productCode);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_REWARD_POINTS), rewardPoints);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_AVAILABILITY), availability);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_PRICE), price);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_TAX_PRICE), exTax);

		sa.assertAll();
	}
	
	
	@DataProvider
	public Object[][] getiMacInfo() {
		return ExcelUtil.getTestData(Constants.PRODUCT_INFO_IMAC_SHEET_NAME);
	}
	
	
	@Description("PPT4 - Verify metadata of a searched product - iMac")
	@Test(priority = 4, dataProvider = "getiMacInfo")
	public void verifyProductInfoTest_iMac(String searchedProduct, String selectedProduct, String name, String brand, String productCode
			, String availability, String price, String exTax) {
		
		resultPage = accountPage.doSearch(searchedProduct);
		productInfoPage = resultPage.selectProductFromResult(selectedProduct);
		Map<String, String> productInfoMap = productInfoPage.getProductInfo();
		

		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_NAME), name);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_BRAND), brand);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_CODE), productCode);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_AVAILABILITY), availability);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_PRICE), price);
		sa.assertEquals(productInfoMap.get(Constants.PRODUCT_TAX_PRICE), exTax);
		sa.assertAll();
	}
	
	// HaN
	@Description("PPT5 - Verify add to cart action by checking the message returned - Macbook Pro")
	@Test(priority = 5)
	public void addToCartTestMacBookPro() {
		resultPage = accountPage.doSearch("Macbook");
		productInfoPage = resultPage.selectProductFromResult("MacBook Pro");
		String msgReturned = productInfoPage.getAddToCartSuccessText("3");
		Assert.assertTrue(msgReturned.contains(Constants.ADD_TO_CART_SUCCESS_MSG1));
		Assert.assertTrue(msgReturned.contains("MacBook Pro"));
		Assert.assertTrue(msgReturned.contains(Constants.ADD_TO_CART_SUCCESS_MSG2));
	}
	
	// HaN
	@Description("PPT6 - Verify add to cart action by checking the message returned - HP")
	@Test(priority = 6)
	public void addToCartTestHP() {
		resultPage = accountPage.doSearch("HP");
		productInfoPage = resultPage.selectProductFromResult("HP LP3065");
		String msgReturned = productInfoPage.getAddToCartSuccessText("3");
		Assert.assertTrue(msgReturned.contains(Constants.ADD_TO_CART_SUCCESS_MSG1));
		Assert.assertTrue(msgReturned.contains("HP LP3065"));
		Assert.assertTrue(msgReturned.contains(Constants.ADD_TO_CART_SUCCESS_MSG2));
	}
	
	// HaN
	@DataProvider
	public Object[][] getHPInfoCartInfoButton() {
		return ExcelUtil.getTestData("product - HP");
	}
	
	// HaN
	@Description("PPT7 - Verify product info under cart info button")
	@Test (priority = 7, dataProvider = "getHPInfoCartInfoButton")
	public void verifyProductInfoTestInCartButton(String subTotal, String total) {
		
		Map<String, String> productMap = productInfoPage.getProductInfoInCartButton();
		
		Assert.assertEquals(productMap.get(Constants.PRODUCT_SUBTOTAL), subTotal);
		Assert.assertEquals(productMap.get(Constants.PRODUCT_TOTAL), total);
	}
}
