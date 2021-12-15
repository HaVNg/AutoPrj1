package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;



public class AccountPageTest extends BaseTest {
	
	@Description("APT - Login to the website")
	@BeforeClass
	public void accountPageSetup() {
		loginPage.loginWithCredentials(prop.getProperty("username"), prop.getProperty("password"));
		accountPage = loginPage.navigateToAccountPage();
	}
	
	@Description("APT1 - Verify the Account page title")
	@Test (priority = 1)
	public void verifyAccountPageTitleTest() {
		String title = accountPage.getAccountPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("APT2 - Verify left menu Account section list")
	@Test(priority = 2)
	public void verifyAccountSectionListTest() {
		Assert.assertEquals(accountPage.getAccountSectionList(), Constants.EXPECTED_ACC_SEC_LIST);
	}

	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"Macbook Pro"}, 
			{"Macbook Air"}, 
			{"Apple"}
			};
	}
	
	
	@Description("APT3 - Search a product and verify if the result header contains the searched product i.e. Search Mac")
	@Test(priority = 3, dataProvider = "getSearchData")
	public void searchProductTest(String productName) {
		
		resultPage = accountPage.doSearch(productName);
		String resultHeader = resultPage.getSearchPageHeader();
		Assert.assertTrue(resultHeader.contains(productName));

	}

	
	@Description("APT4 - Verify total number of search results")
	@Test(priority = 4)
	public void getSearchProductListCountTest() {
		Assert.assertEquals(resultPage.getSearchProductListCount(), 4);
	}
	

	
	@DataProvider
	public Object[][] getSelectedProductData() {
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
		
	}
	
	@Description("APT5 - Search a product then select and verify the product header text i.e. MacBook Pro")
	@Test(priority = 5, dataProvider = "getSelectedProductData")
	public void selectProductFromResultTest(String productName, String selectedProductName){
		
		resultPage = accountPage.doSearch(productName);
		productInfoPage = resultPage.selectProductFromResult(selectedProductName);
		String productHeaderText = productInfoPage.getProductHeaderText();
		Assert.assertEquals(productHeaderText, selectedProductName);
	}
	
}
