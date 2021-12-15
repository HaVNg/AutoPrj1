package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Utils;

// HaN
public class ShoppingCartPage extends BasePage {
	private WebDriver driver;
	private Utils util;
	private By aesterisk = By.xpath("//span[text()='***']");
	private By productNotInStockText = By.xpath("//div[contains(text(), ' Products marked with ')]");
	private By checkOutButton = By.cssSelector("a.btn-primary");
	private By images = By.cssSelector("img.img-thumbnail");
//	private By productInfo = By.cssSelector("table.table-bordered tbody:nth-child(2) tr td");
//	private By products = By.cssSelector("table.table-bordered tbody:nth-child(2) tr");
	private By productName = By.xpath("//td[@class='text-left']//a");
	
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		util= new Utils(this.driver);	
	}
	
	public boolean isProductNotInStock() {
		return util.isDisplayed(aesterisk);
	}
	
	public String doGetNotInStockErrorText() {
		return util.doGetText(productNotInStockText);
	}
	
	public void doCheckOut() {
		util.doClick(checkOutButton);
	}
	
	public int doGetNumberOfProducts() {
		return util.getElements(images).size();
	}
	
	public int doGetNumberOfProductImages() {
		return util.getElements(images).size();
	}

	
}
