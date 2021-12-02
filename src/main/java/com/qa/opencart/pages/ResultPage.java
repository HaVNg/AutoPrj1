package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Utils;

import io.qameta.allure.Step;

public class ResultPage extends BasePage {
	private WebDriver driver;
	private Utils util;
	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.caption a");
			
			
	public ResultPage(WebDriver driver) {
		this.driver = driver;
		util = new Utils(this.driver);
	}
	
	@Step("Get Result search page header")
	public String getSearchPageHeader() {
		return util.doGetText(searchHeader);	
	}
	
	@Step("Count the search result products")
	public int getSearchProductListCount() {
		return util.getElements(productResults).size();
	}
	
	@Step("Select the product from search result")
	public ProductInfoPage selectProductFromResult(String selectedProductName) {
		List<WebElement> searchResultList = util.getElements(productResults);
		for(WebElement productName : searchResultList) {
			if (productName.getText().trim().equals(selectedProductName)) {
				productName.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}

