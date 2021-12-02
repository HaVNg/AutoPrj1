package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Utils;

import io.qameta.allure.Step;

public class AccountPage extends BasePage {
	private WebDriver driver;

	private Utils util;
	private By accountPgTitle = By.xpath("//a[text()='Your Store']");
	private By accountSectionTitles = By.cssSelector("#content h2");
	private By search = By.cssSelector("input[name='search']");
	private By searchButton = By.cssSelector(".btn-default.btn-lg");
	private By searchResult = By.cssSelector(".row:nth-of-type(3)");
	private By logoutLink = By.linkText("Logout");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		util = new Utils(this.driver);
	}
	
	@Step("Get Account page title")
	public String getAccountPageTitle() {
		return util.waitForTitleToPresent(Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Step("Get Account section list")
	public List<String> getAccountSectionList() {
		List<WebElement> acctSecList = util.getElements(accountSectionTitles);
		List<String> acctSecValueList = new ArrayList<String>();
		
		for(WebElement acctSec : acctSecList) {
			acctSecValueList.add(acctSec.getText());
		}
		
		return acctSecValueList;
	}
	
	@Step("Check if logout link exists")
	public boolean isLogoutLinkExisted() {
		return util.isDisplayed(logoutLink);
	}
	
	@Step("Search and select the product: {0}")
	public ResultPage doSearch(String productName) {
		//util.getElement(search).clear();
		log.info("Searching the product: " + productName);
		util.doSendKeys(search, productName);
		util.doClick(searchButton);
		
		return new ResultPage(driver);
		
//		List<WebElement> searchResultList = util.getElements(searchResult);
//		if (searchResultList.size() >= 0) {
//			return true;
//		}
//		return false;
	}
	
	
}


